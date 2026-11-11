package utils.clasificadores.knn;

import colecciones.edlineal.arreglos.Arreglo;
import colecciones.ednolineal.Arreglo2DNumerico;
import utils.entradasalida.archivos.ArchivoTextoD;

// Clase que gestiona el conjunto de datos de entrenamiento para K-NN.
// Almacena los datos numéricos y calcula pesos basados en información mutua.
public class ConjuntoDatos {
    private Arreglo2DNumerico datosNumericos; // Matriz para datos numéricos: filas=objetos, columnas=características
    private Arreglo clases; // Arreglo para las clases de cada objeto
    private Arreglo clasesUnicas; // Arreglo para almacenar las clases únicas
    private int numCaracteristicas; // Número de variables predictoras

    // Constructor que inicializa la matriz de datos y los arreglos de clases
    public ConjuntoDatos(int numFilas, int numCaracteristicas) {
        if (numFilas <= 0 || numCaracteristicas <= 0)
            throw new IllegalArgumentException("Las dimensiones del conjunto deben ser positivas");
        this.numCaracteristicas = numCaracteristicas;
        datosNumericos = new Arreglo2DNumerico(numFilas, numCaracteristicas);
        clases = new Arreglo(numFilas);
        clasesUnicas = new Arreglo(numFilas);
    }

    // Lee los datos de entrenamiento de un archivo .txt
    public boolean leerDatos(String nombreArchivo) {
        if (nombreArchivo == null || !new java.io.File(nombreArchivo).isFile()) return false;
        Arreglo lineas = ArchivoTextoD.leer(nombreArchivo);
        if (lineas == null || lineas.vacia() || lineas.cantidad() > datosNumericos.obtenerRenglones()) return false;
        Arreglo2DNumerico nuevosDatos = new Arreglo2DNumerico(datosNumericos.obtenerRenglones(), numCaracteristicas);
        Arreglo nuevasClases = new Arreglo(lineas.cantidad());
        Arreglo nuevasClasesUnicas = new Arreglo(lineas.cantidad());
        for (int fila = 0; fila < lineas.cantidad(); fila++) {
            String[] partes = lineas.obtener(fila).toString().split(",", -1);
            if (partes.length != numCaracteristicas + 1 || partes[numCaracteristicas].isBlank()) return false;
            // Llenar variables predictoras en la matriz.
            for (int col = 0; col < numCaracteristicas; col++) {
                try {
                    double valor = Double.parseDouble(partes[col]);
                    if (!Double.isFinite(valor)) return false;
                    nuevosDatos.cambiar(fila, col, valor);
                } catch (NumberFormatException e) { return false; }
            }
            // Guardar la clase de la fila; usar la misma igualdad que Arreglo.contar.
            String clase = partes[numCaracteristicas].trim();
            int existente = (int) nuevasClasesUnicas.buscar(clase);
            if (existente >= 0) clase = (String) nuevasClasesUnicas.obtener(existente);
            else nuevasClasesUnicas.poner(clase);
            nuevasClases.poner(clase);
        }
        // Una carga inválida no destruye el conjunto anterior.
        datosNumericos = nuevosDatos;
        clases = nuevasClases;
        clasesUnicas = nuevasClasesUnicas;
        return true;
    }

    // Calcula la información mutua I(X_i, C) para una variable predictora
    // Mide la dependencia entre la variable X_i y la clase C
    private double calcularInformacionMutua(int columna) {
        Arreglo valoresDiscretos = discretizarColumna(columna);
        double informacionMutua = 0.0;
        int totalDatos = obtenerCantidadDatos();

        // Calcular probabilidades p_C(c) para cada clase
        Arreglo probabilidadesClase = new Arreglo(clasesUnicas.cantidad());
        for (int i = 0; i < clasesUnicas.cantidad(); i++) {
            String clase = (String) clasesUnicas.obtener(i);
            int conteo = clases.contar(clase);
            probabilidadesClase.poner((double) conteo / totalDatos);
        }

        // Calcular p_Xi(X_i) y p_(Xi,C)(X_i,c) para cada valor y clase
        for (int i = 0; i < valoresDiscretos.cantidad(); i++) {
            double valor = (double) valoresDiscretos.obtener(i);
            int conteoValor = contarValorEnColumna(columna, valor);
            double pXi = (double) conteoValor / totalDatos;

            for (int j = 0; j < clasesUnicas.cantidad(); j++) {
                String clase = (String) clasesUnicas.obtener(j);
                int conteoConjunto = contarValorYClase(columna, valor, clase);
                double pXiC = (double) conteoConjunto / totalDatos;
                double pC = (double) probabilidadesClase.obtener(j);

                // Sumar término de la fórmula I(X_i, C)
                if (pXiC > 0 && pXi > 0 && pC > 0) {
                    informacionMutua += pXiC * Math.log(pXiC / (pXi * pC));
                }
            }
        }
        return informacionMutua;
    }

    // Discretiza los valores de una columna para facilitar el cálculo de probabilidades.
    // Usa redondeo para convertir valores continuos a discretos.
    private Arreglo discretizarColumna(int columna) {
        Arreglo valores = new Arreglo(obtenerCantidadDatos());
        for (int fila = 0; fila < obtenerCantidadDatos(); fila++) {
            double valor = ((Number) datosNumericos.obtener(fila, columna)).doubleValue();
            double discretizado = Math.round(valor); // Redondea para discretizar
            if ((int) valores.buscar(discretizado) < 0) {
                valores.poner(discretizado); // Agrega si no existe
            }
        }
        return valores;
    }

    // Cuenta las ocurrencias de un valor en una columna.
    private int contarValorEnColumna(int columna, double valor) {
        int conteo = 0;
        for (int fila = 0; fila < obtenerCantidadDatos(); fila++) {
            double valorActual = Math.round(((Number) datosNumericos.obtener(fila, columna)).doubleValue());
            if (Math.abs(valorActual - valor) < 0.001) {
                conteo++; // Incrementa si los valores son casi iguales
            }
        }
        return conteo;
    }

    // Cuenta las ocurrencias conjuntas de un valor y una clase.
    private int contarValorYClase(int columna, double valor, String clase) {
        int conteo = 0;
        for (int fila = 0; fila < obtenerCantidadDatos(); fila++) {
            double valorActual = Math.round(((Number) datosNumericos.obtener(fila, columna)).doubleValue());
            String claseActual = (String) clases.obtener(fila);
            if (Math.abs(valorActual - valor) < 0.001 && claseActual.equals(clase)) {
                conteo++; // Incrementa si coinciden valor y clase
            }
        }
        return conteo;
    }

    // Calcula los pesos w_i para cada variable predictora, proporcionales a la información mutua.
    // Los normaliza para que sumen 1.
    public Arreglo calcularPesos() {
        if (obtenerCantidadDatos() == 0) throw new IllegalStateException("Primero debe cargar datos");
        Arreglo pesos = new Arreglo(numCaracteristicas);
        double sumaInformacion = 0.0;

        // Calcular I(X_i, C) para cada variable
        for (int col = 0; col < numCaracteristicas; col++) {
            double infoMutua = calcularInformacionMutua(col);
            pesos.poner(infoMutua);
            sumaInformacion += infoMutua;
        }

        // Normalizar pesos para que sumen 1
        if (sumaInformacion > 0) {
            for (int col = 0; col < numCaracteristicas; col++) {
                double infoMutua = (double) pesos.obtener(col);
                pesos.modificar(col, infoMutua / sumaInformacion);
            }
        }
        else {
            // Sin información discriminante, todas las variables tienen el mismo peso.
            for (int col = 0; col < numCaracteristicas; col++) pesos.modificar(col, 1.0 / numCaracteristicas);
        }
        return pesos;
    }

    // Obtiene la matriz de datos numéricos.
    public Arreglo2DNumerico obtenerDatosNumericos() {
        return datosNumericos;
    }

    // Obtiene el arreglo de clases.
    public Arreglo obtenerClases() {
        return clases;
    }

    // Obtiene el arreglo de clases únicas.
    public Arreglo obtenerClasesUnicas() {
        return clasesUnicas;
    }
    public int obtenerCantidadDatos() {
        return clases.cantidad();
    }
}