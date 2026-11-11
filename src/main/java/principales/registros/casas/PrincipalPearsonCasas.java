package principales.registros.casas;

import colecciones.edlineal.arreglos.Arreglo;
import registros.casas.*;
import utils.entradasalida.archivos.ArchivoTextoD;
import utils.entradasalida.cadenas.Salida;

public class PrincipalPearsonCasas {
    public static void main(String[] args) {
        // Leer datos de archivos
        Arreglo arregloHabitaciones = ArchivoTextoD.leer("src/main/java/resources/casas/habitaciones.txt");
        Arreglo arregloPrecios = ArchivoTextoD.leer("src/main/java/resources/casas/precios.txt");

        // Crear objeto de datos
        DatosCasas datos = new DatosCasas(arregloHabitaciones, arregloPrecios);

        // Calcular e interpretar correlación
        CalculadorPearson calculador = new CalculadorPearson();
        double coeficiente = calculador.calcular(datos.getHabitaciones(), datos.getPrecios());
        Salida.salidaPorDefecto("Coeficiente de Correlación de Pearson: " + coeficiente + "\n");
        calculador.interpretar(coeficiente);
    }
}
