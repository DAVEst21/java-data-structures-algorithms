package principales.clasificadores.knn;

import colecciones.edlineal.arreglos.Arreglo;
import utils.clasificadores.knn.*;
import utils.entradasalida.cadenas.*;

public class PrincipalKnnVehiculos {
    public static void main(String[] args) {
        int numCaracteristicas = 3; // Peso, longitud, color
        int numDatos = 147; // Número de datos en vehiculos.txt
        int valorK = 3; // Valor de K para K-NN

        // Cargar datos de entrenamiento
        ConjuntoDatos conjunto = new ConjuntoDatos(numDatos, numCaracteristicas);
        Salida.salidaPorDefecto("Cargando datos de entrenamiento...\n");
        if (!conjunto.leerDatos("src/main/java/resources/vehiculos/vehiculos.txt")) {
            Salida.salidaPorDefecto("Error al leer el archivo vehiculos.txt\n");
            return;
        }

        // Crear clasificador K-NN
        KNN clasificador = new KNN(conjunto, valorK);

        // Pedir datos del nuevo vehículo
        Salida.salidaPorDefecto("Ingrese los datos del nuevo vehículo:\n");
        Dato nuevoDato = new Dato(numCaracteristicas);
        boolean datosValidos = true;

        // Ingresar peso
        Salida.salidaPorDefecto("Peso (kg): ");
        if (!nuevoDato.establecerValor(0, Entrada.terminalDouble())) {
            datosValidos = false;
        }
        // Ingresar longitud
        Salida.salidaPorDefecto("Longitud (m): ");
        if (!nuevoDato.establecerValor(1, Entrada.terminalDouble())) {
            datosValidos = false;
        }
        // Ingresar color
        Salida.salidaPorDefecto("Color (1=rojo, 2=verde, 3=amarillo, 4=naranja, 5=morado, 6=azul, 7=marrón, 8=blanco, 9=negro): ");
        if (!nuevoDato.establecerValor(2, Entrada.terminalDouble())) {
            datosValidos = false;
        }

        // Verificar si los datos son válidos
        if (!datosValidos) {
            Salida.salidaPorDefecto("Error: No se pudieron establecer todos los valores.\n");
            return;
        }

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