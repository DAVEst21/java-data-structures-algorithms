package utils.clasificadores.knn;

import colecciones.edlineal.arreglos.Arreglo;
import colecciones.edlineal.arreglos.ArregloOrdenado;

// Clase que implementa el algoritmo K-NN con pesado de variables.
// Clasifica un nuevo dato basado en los K vecinos más cercanos.
public class KNN {
    private ConjuntoDatos conjuntoDatos; // Conjunto de datos de entrenamiento
    private int valorK; // Número de vecinos a considerar
    private Arreglo pesos; // Pesos de las variables predictoras

    // Constructor que inicializa el clasificador K-NN.
    public KNN(ConjuntoDatos conjunto, int k) {
        if (conjunto == null || k < 1 || k > conjunto.obtenerCantidadDatos())
            throw new IllegalArgumentException("K debe estar entre 1 y la cantidad de datos cargados");
        conjuntoDatos = conjunto;
        valorK = k;
        pesos = conjuntoDatos.calcularPesos(); // Calcula los pesos al iniciar
    }

    // Calcula la distancia ponderada entre dos objetos.
    // Usa la fórmula: sum(w_i * (x_i - x_ri)^2)
    private double calcularDistanciaPonderada(Dato dato1, Dato dato2) {
        double suma = 0.0;
        for (int col = 0; col < dato1.obtenerNumCaracteristicas(); col++) {
            double valor1 = ((Number) dato1.obtenerValor(col)).doubleValue();
            double valor2 = ((Number) dato2.obtenerValor(col)).doubleValue();
            double peso = ((Number) pesos.obtener(col)).doubleValue();
            suma += peso * Math.pow(valor1 - valor2, 2); // Suma término ponderado
        }
        return suma;
    }

    // Clasifica un nuevo dato usando K-NN con pesado de variables.
    public String clasificar(Dato nuevoDato) {
        if (nuevoDato == null || nuevoDato.obtenerNumCaracteristicas() != conjuntoDatos.obtenerDatosNumericos().obtenerColumnas())
            throw new IllegalArgumentException("El dato debe tener las mismas características que el conjunto");
        ArregloOrdenado distancias = new ArregloOrdenado(conjuntoDatos.obtenerCantidadDatos(),
                ArregloOrdenado.TipoOrdenamiento.INC);
        Arreglo clases = conjuntoDatos.obtenerClases();

        // Calcular distancias a todos los datos de entrenamiento
        for (int fila = 0; fila < conjuntoDatos.obtenerCantidadDatos(); fila++) {
            Dato datoEntrenamiento = new Dato(nuevoDato.obtenerNumCaracteristicas());
            for (int col = 0; col < nuevoDato.obtenerNumCaracteristicas(); col++) {
                datoEntrenamiento.establecerValor(col,
                        ((Number) conjuntoDatos.obtenerDatosNumericos().obtener(fila, col)).doubleValue());
            }
            datoEntrenamiento.establecerClase((String) clases.obtener(fila));
            double distancia = calcularDistanciaPonderada(nuevoDato, datoEntrenamiento);
            if (!Double.isFinite(distancia)) throw new IllegalArgumentException("La distancia excede el rango numérico");
            distancias.poner(new ParDistanciaClase(distancia, datoEntrenamiento.obtenerClase(), fila));
        }

        // Encontrar la clase más frecuente entre los K vecinos
        Arreglo clasesVecinos = new Arreglo(valorK);
        for (int i = 0; i < valorK && i < distancias.cantidad(); i++) {
            ParDistanciaClase par = (ParDistanciaClase) distancias.obtener(i);
            clasesVecinos.poner(par.obtenerClase());
        }

        // Contar frecuencias y elegir la clase más común
        String claseGanadora = "";
        int maxFrecuencia = 0;
        for (int i = 0; i < clasesVecinos.cantidad(); i++) {
            String clase = (String) clasesVecinos.obtener(i);
            int frecuencia = clasesVecinos.contar(clase);
            // En caso de empate, usar la clase del vecino más cercano entre las empatadas.
            if (frecuencia > maxFrecuencia) {
                maxFrecuencia = frecuencia;
                claseGanadora = clase;
            }
        }
        return claseGanadora;
    }
}

// Clase auxiliar para almacenar una distancia y su clase asociada.
// Usada para ordenar distancias en el algoritmo K-NN.
class ParDistanciaClase implements Comparable<ParDistanciaClase> {
    private double distancia; // Distancia calculada entre dos datos
    private String clase; // Clase asociada al dato
    private int indice; // Desempate estable: conservar cada fila, incluso con igual distancia.

    // Constructor que inicializa la distancia y la clase.
    public ParDistanciaClase(double distancia, String clase) {
        this(distancia, clase, 0);
    }

    public ParDistanciaClase(double distancia, String clase, int indice) {
        this.distancia = distancia;
        this.clase = clase;
        this.indice = indice;
    }

    // Obtiene la distancia.
    public double obtenerDistancia() {
        return distancia;
    }

    // Obtiene la clase.
    public String obtenerClase() {
        return clase;
    }

    /// Representación en cadena del par distancia-clase.
    @Override
    public String toString() {
        return distancia + "," + clase;
    }
    @Override
    public int compareTo(ParDistanciaClase otro) {
        int comparacion = Double.compare(distancia, otro.distancia);
        return comparacion != 0 ? comparacion : Integer.compare(indice, otro.indice);
    }
}
