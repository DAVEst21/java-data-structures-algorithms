package principales.clasificadores.knn;

import colecciones.edlineal.arreglos.Arreglo;
import utils.clasificadores.knn.*;
import utils.entradasalida.cadenas.*;

// Clase principal para ejecutar el clasificador K-NN
// Lee datos, permite clasificar un nuevo objeto y muestra resultados
public class PrincipalKnnFrutas {
    public static void main(String[] args) {
        // Configuración inicial
        int numCaracteristicas = 4; // Número de características: peso, diámetro, color, textura
        int numDatos = 140; // Número estimado de datos de entrenamiento
        int valorK = 3; // Valor de K para K-NN

        // Cargar datos de entrenamiento
        ConjuntoDatos conjunto = new ConjuntoDatos(numDatos, numCaracteristicas);
        Salida.salidaPorDefecto("Cargando datos de entrenamiento...\n");
        if (!conjunto.leerDatos("src/main/java/resources/frutas/frutas.txt")) {
            Salida.salidaPorDefecto("Error al leer el archivo .txt\n");
            return; // Termina si hay error en la lectura
        }

        // Crear clasificador K-NN
        KNN clasificador = new KNN(conjunto, valorK);

        // Pedir datos del nuevo objeto al usuario
        Salida.salidaPorDefecto("Ingrese los datos de la nueva fruta:\n");
        Dato nuevoDato = new Dato(numCaracteristicas);
        Salida.salidaPorDefecto("Peso (gramos): ");
        nuevoDato.establecerValor(0, Entrada.terminalDouble());
        Salida.salidaPorDefecto("Diámetro (cm): ");
        nuevoDato.establecerValor(1, Entrada.terminalDouble());
        Salida.salidaPorDefecto("Color (1=rojo, 2=verde, 3=amarillo, 4=naranja, 5=morado, 6=azul, 7=marrón, 8=blanco, 9=rosa): ");
        nuevoDato.establecerValor(2, Entrada.terminalDouble());
        Salida.salidaPorDefecto("Textura (1=lisa, 2=rugosa): ");
        nuevoDato.establecerValor(3, Entrada.terminalDouble());

        // Clasificar el nuevo dato
        String clasePredicha = clasificador.clasificar(nuevoDato);
        Salida.salidaPorDefecto("Clase predicha: " + clasePredicha + "\n");

        // Mostrar clases disponibles
        Salida.salidaPorDefecto("Clases disponibles:\n");
        Arreglo clasesUnicas = conjunto.obtenerClasesUnicas();
        for (int i = 0; i < clasesUnicas.cantidad(); i++) {
            Salida.salidaPorDefecto(clasesUnicas.obtener(i) + "\n");
        }
    }
}
