/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package colecciones.edlineal.arreglos;

/**
 *
 * @author Usuario
 */
public class ArregloNumerico extends Arreglo {

    // Constructor que inicializa el arreglo con un tamaño dado
    public ArregloNumerico(int tamanio) {
        super(tamanio);
    }

    // Inserta un valor en el arreglo
    @Override
    public Integer poner(Object valor) {
        if (valor instanceof Number) { // Validar que el valor sea numérico
            if (lleno() == false) {
                indiceSuperior++;
                datos[indiceSuperior] = ((Number) valor).doubleValue(); // Convertir a double
                return indiceSuperior; // Devuelve la posición donde se insertó
            } else {
                return -1; // Devuelve -1 si el arreglo está lleno
            }
        } else {
            return -1; // Devuelve -1 si el valor no es numérico
        }
    }

    // Inserta un valor en un índice específico del arreglo
    @Override
    public boolean poner(int indice, Object info) {
        if (info instanceof Number) { // Validar que el valor sea numérico
            if (validaPosicion(indice)) {
                datos[indice] = ((Number) info).doubleValue(); // Convertir a double
                return true;
            } else {
                return false; // Devuelve false si el índice no es válido
            }
        } else {
            return false; // Devuelve false si el valor no es numérico
        }
    }

    // Busca un valor en el arreglo y devuelve su posición
    @Override
    public Object buscar(Object valor) {
        if (valor instanceof Number) { // Validar que el valor sea numérico
            int recorredor = 0;
            double valorBuscado = ((Number) valor).doubleValue(); // Convertir a double
            while (recorredor <= indiceSuperior
                    && Double.compare(valorBuscado, ((Number) datos[recorredor]).doubleValue()) != 0) {
                recorredor++;
            }
            if (recorredor > indiceSuperior) {
                return -1; // Devuelve -1 si no se encuentra el valor
            } else {
                return recorredor; // Devuelve la posición del valor encontrado
            }
        } else {
            return -1; // Devuelve -1 si el valor no es numérico
        }
    }

    // Multiplica cada elemento del arreglo por un escalar
    public boolean porEscalar(Number escalar) {
        if (vacia()) {
            return false;
        }
        for (int i = 0; i <= indiceSuperior; i++) {
            datos[i] = ((Number) datos[i]).doubleValue() * escalar.doubleValue();
        }
        return true;
    }

    // Suma un escalar a cada elemento del arreglo
    public boolean sumarEscalar(Number escalar) {
        if (vacia()) {
            return false;
        }
        for (int i = 0; i <= indiceSuperior; i++) {
            datos[i] = ((Number) datos[i]).doubleValue() + escalar.doubleValue();
        }
        return true;
    }

    // Suma dos arreglos posición por posición
    public boolean sumar(ArregloNumerico lista2) {
        if (vacia() || lista2.vacia() || cantidad() != lista2.cantidad()) {
            return false;
        }
        for (int i = 0; i <= indiceSuperior; i++) {
            datos[i] = ((Number) datos[i]).doubleValue() + ((Number) lista2.obtener(i)).doubleValue();
        }
        return true;
    }

    // Multiplica dos arreglos posición por posición
    public boolean multiplicar(ArregloNumerico lista2) {
        if (vacia() || lista2.vacia() || cantidad() != lista2.cantidad()) {
            return false;
        }
        for (int i = 0; i <= indiceSuperior; i++) {
            datos[i] = ((Number) datos[i]).doubleValue() * ((Number) lista2.obtener(i)).doubleValue();
        }
        return true;
    }

    // Aplica una potencia a cada elemento del arreglo
    public boolean aplicarPotencia(Number escalar) {
        if (vacia()) {
            return false;
        }
        for (int i = 0; i <= indiceSuperior; i++) {
            datos[i] = Math.pow(((Number) datos[i]).doubleValue(), escalar.doubleValue());
        }
        return true;
    }

    // Aplica una potencia a cada elemento del arreglo usando otro arreglo de exponentes
    public boolean aplicarPotencia(ArregloNumerico listaEscalares) {
        if (vacia() || listaEscalares.vacia() || cantidad() != listaEscalares.cantidad()) {
            return false;
        }
        for (int i = 0; i <= indiceSuperior; i++) {
            datos[i] = Math.pow(((Number) datos[i]).doubleValue(), ((Number) listaEscalares.obtener(i)).doubleValue());
        }
        return true;
    }

    // Calcula el producto escalar entre dos arreglos
    public double productoEscalar(ArregloNumerico lista2) {
        if (vacia() || lista2.vacia() || cantidad() != lista2.cantidad()) {
            return 0.0;
        }
        double resultado = 0.0;
        for (int i = 0; i <= indiceSuperior; i++) {
            resultado += ((Number) datos[i]).doubleValue() * ((Number) lista2.obtener(i)).doubleValue();
        }
        return resultado;
    }

    // Calcula la norma L2 del arreglo
    public double norma() {
        if (vacia()) {
            return 0.0;
        }
        double sumaCuadrados = 0.0;
        for (int i = 0; i <= indiceSuperior; i++) {
            sumaCuadrados += Math.pow(((Number) datos[i]).doubleValue(), 2);
        }
        return Math.sqrt(sumaCuadrados);
    }

    // Calcula la norma euclidiana entre dos arreglos
    public double normaEuclidiana(ArregloNumerico arreglo2) {
        if (vacia() || arreglo2.vacia() || cantidad() != arreglo2.cantidad()) {
            return 0.0;
        }
        double sumaDiferenciasCuadradas = 0.0;
        for (int i = 0; i <= indiceSuperior; i++) {
            double diferencia = ((Number) datos[i]).doubleValue() - ((Number) arreglo2.obtener(i)).doubleValue();
            sumaDiferenciasCuadradas += Math.pow(diferencia, 2);
        }
        return Math.sqrt(sumaDiferenciasCuadradas);
    }

    // Suma múltiples arreglos al arreglo actual
    public boolean sumarListaEstatica(Arreglo listas) {
        if (vacia() || listas.vacia()) {
            return false;
        }
        for (int i = 0; i < listas.cantidad(); i++) {
            ArregloNumerico arreglo = (ArregloNumerico) listas.obtener(i);
            if (!sumar(arreglo)) {
                return false;
            }
        }
        return true;
    }

    // Suma múltiples escalares al arreglo actual
    public boolean sumarEscalares(ArregloNumerico escalares) {
        if (vacia() || escalares.vacia()) {
            return false;
        }
        for (int i = 0; i <= indiceSuperior; i++) {
            datos[i] = ((Number) datos[i]).doubleValue() + ((Number) escalares.obtener(i)).doubleValue();
        }
        return true;
    }

    // Suma los elementos del arreglo actual indicados por los índices en listaIndices
    public double sumarIndices(ArregloNumerico listaIndices) {
        if (vacia() || listaIndices.vacia()) {
            return 0.0;
        }
        double suma = 0.0;
        for (int i = 0; i < listaIndices.cantidad(); i++) {
            int indice = ((Number) listaIndices.obtener(i)).intValue();
            if (validaPosicion(indice)) {
                suma += ((Number) datos[indice]).doubleValue();
            }
        }
        return suma;
    }

    // Devuelve una sublista con los elementos indicados por listaIndices
    @Override
    public Arreglo subLista(ArregloNumerico listaIndices) {
        if (vacia() || listaIndices.vacia()) {
            return null;
        }
        ArregloNumerico sublista = new ArregloNumerico(listaIndices.cantidad());
        for (int i = 0; i < listaIndices.cantidad(); i++) {
            int indice = ((Number) listaIndices.obtener(i)).intValue();
            if (validaPosicion(indice)) {
                sublista.poner(datos[indice]);
            }
        }
        return sublista;
    }

    // Determina si un conjunto de arreglos es linealmente dependiente
    public boolean sonLinealmenteDependientes(Arreglo listaVectores) {
        if (vacia() || listaVectores.vacia()) {
            return false; // No hay vectores para evaluar
        }

        // Verificar si el número de escalares coincide con el número de vectores
        if (cantidad() != listaVectores.cantidad()) {
            return false;
        }

        // Crear un arreglo para almacenar los escalares
        ArregloNumerico escalares = new ArregloNumerico(cantidad());
        for (int i = 0; i < cantidad(); i++) {
            escalares.poner(obtener(i)); // Copiar los escalares del arreglo actual
        }

        // Verificar si existe una combinación lineal no trivial que dé como resultado el vector nulo
        return existeCombinacionLinealNoTrivial(escalares, listaVectores);
    }

    // Determina si un conjunto de arreglos es linealmente independiente
    public boolean sonLinealmenteIndependientes(Arreglo listaVectores) {
        return !sonLinealmenteDependientes(listaVectores); // Son independientes si no son dependientes
    }

    // Método auxiliar para verificar si existe una combinación lineal no trivial
    private boolean existeCombinacionLinealNoTrivial(ArregloNumerico escalares, Arreglo listaVectores) {
        // Si todos los escalares son cero, la combinación es trivial
        boolean todosCero = true;
        for (int i = 0; i < escalares.cantidad(); i++) {
            if (((Number) escalares.obtener(i)).doubleValue() != 0.0) {
                todosCero = false;
                break;
            }
        }
        if (todosCero) {
            return false; // Combinación trivial (no es dependiente)
        }

        // Calcular la combinación lineal de los vectores con los escalares
        ArregloNumerico combinacionLineal = new ArregloNumerico(((ArregloNumerico) listaVectores.obtener(0)).capacidad());
        for (int i = 0; i < listaVectores.cantidad(); i++) {
            ArregloNumerico vector = (ArregloNumerico) listaVectores.obtener(i);
            double escalar = ((Number) escalares.obtener(i)).doubleValue();

            for (int j = 0; j < vector.cantidad(); j++) {
                double valorVector = ((Number) vector.obtener(j)).doubleValue();
                double valorCombinacion = escalar * valorVector;

                if (combinacionLineal.vacia() || j >= combinacionLineal.cantidad()) {
                    combinacionLineal.poner(valorCombinacion);
                } else {
                    double valorActual = ((Number) combinacionLineal.obtener(j)).doubleValue();
                    combinacionLineal.modificar(j, valorActual + valorCombinacion);
                }
            }
        }

        // Verificar si la combinación lineal es el vector nulo
        for (int i = 0; i < combinacionLineal.cantidad(); i++) {
            if (((Number) combinacionLineal.obtener(i)).doubleValue() != 0.0) {
                return false; // No es el vector nulo
            }
        }

        return true; // Es el vector nulo (dependientes)
    }

    // Determina si el arreglo actual es ortogonal a otro arreglo
    public boolean esOrtogonal(ArregloNumerico lista2) {
        if (vacia() || lista2.vacia()) {
            return false;
        }
        return productoEscalar(lista2) == 0.0;
    }

    // Determina si el arreglo actual es paralelo a otro arreglo
    public boolean esParalelo(ArregloNumerico lista2) {
        if (vacia() || lista2.vacia()) {
            return false;
        }
        double factor = ((Number) datos[0]).doubleValue() / ((Number) lista2.obtener(0)).doubleValue();
        for (int i = 1; i <= indiceSuperior; i++) {
            if (((Number) datos[i]).doubleValue() / ((Number) lista2.obtener(i)).doubleValue() != factor) {
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean modificar(int indice, Object valor) {
        return poner(indice, valor);
    }

    @Override
    public Integer ponerInicio(Object valor) {
        if (!(valor instanceof Number)) return -1;
        return super.ponerInicio(((Number) valor).doubleValue());
    }

}
