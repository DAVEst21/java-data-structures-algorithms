package utils.clasificadores.knn;

import colecciones.edlineal.arreglos.Arreglo;

/// Clase que representa un dato con variables predictoras y una clase asociada.
//Usada para almacenar tanto datos de entrenamiento como nuevos objetos a clasificar.
public class Dato {
    private Arreglo valoresPredictoras; // Almacena valores numéricos de las características
    private String clase; // Clase del dato

    // Constructor que inicializa el arreglo de valores predictoras con un tamaño dado.
    public Dato(int numCaracteristicas) {
        valoresPredictoras = new Arreglo(numCaracteristicas);
        clase = ""; // Inicializa la clase como vacía
        // Inicializar el arreglo con ceros para evitar valores nulos
        for (int i = 0; i < numCaracteristicas; i++) {
            valoresPredictoras.poner(0.0);
        }
    }

    // Establece el valor de una característica en la posición indicada.
    public boolean establecerValor(int indice, double valor) {
        return valoresPredictoras.modificar(indice, valor);
    }

    // Obtiene el valor de una característica en la posición indicada.
    public Object obtenerValor(int indice) {
        return valoresPredictoras.obtener(indice);
    }

    // Establece la clase del dato.
    public void establecerClase(String claseNueva) {
        clase = claseNueva;
    }

    // Obtiene la clase del dato.
    public String obtenerClase() {
        return clase;
    }

    // Obtiene el número de características predictoras.
    public int obtenerNumCaracteristicas() {
        return valoresPredictoras.capacidad();
    }
}