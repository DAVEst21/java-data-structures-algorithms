package principales.registros.casas;

import colecciones.edlineal.arreglos.Arreglo;
import registros.casas.*;
import utils.entradasalida.archivos.ArchivoTextoD;
import utils.entradasalida.cadenas.Salida;

public class PrincipalAnalisisCasas {
    public static void main(String[] args) {
        // Leer datos de archivos
        Arreglo arregloHabitaciones = ArchivoTextoD.leer("src/main/java/resources/casas/habitaciones.txt");
        Arreglo arregloPrecios = ArchivoTextoD.leer("src/main/java/resources/casas/precios.txt");

        // Crear objeto de datos
        DatosCasas datos = new DatosCasas(arregloHabitaciones, arregloPrecios);

        // Realizar análisis
        AnalizadorPrecios analizador = new AnalizadorPrecios();

        // a) Precio promedio por número de habitaciones
        analizador.calcularPromedioPorHabitaciones(datos.getHabitaciones(), datos.getPrecios());

        // b) Precio mínimo y máximo por número de habitaciones
        analizador.calcularMinMaxPorHabitaciones(datos.getHabitaciones(), datos.getPrecios());

        // c) Coeficientes de Pearson
        double coeficienteMuestral = analizador.calcularPearsonMuestral(datos.getHabitaciones(), datos.getPrecios());
        double coeficientePoblacional = analizador.calcularPearsonPoblacional(datos.getHabitaciones(), datos.getPrecios());
        Salida.salidaPorDefecto("Coeficiente de Pearson Muestral (r): " + coeficienteMuestral + "\n");
        Salida.salidaPorDefecto("Coeficiente de Pearson Poblacional (ρ): " + coeficientePoblacional + "\n");

        // Comparar resultados
        analizador.compararCoeficientesPearson(coeficienteMuestral, coeficientePoblacional);

        // d) Interpretar coeficiente muestral
        Salida.salidaPorDefecto("Interpretación del Coeficiente de Pearson Muestral (r):\n");
        CalculadorPearson calculador = new CalculadorPearson();
        calculador.interpretar(coeficienteMuestral);
    }
}
