package registros.casas;

import colecciones.edlineal.listas.ListaDin;
import utils.entradasalida.cadenas.Salida;

public class AnalizadorPrecios {
    private static final int MIN_HABITACIONES = 4;
    private static final int MAX_HABITACIONES = 13;

    public void calcularPromedioPorHabitaciones(ListaDin habitaciones, ListaDin precios) {
        // Crear una ListaDin para cada número de habitaciones
        ListaDin[] listasPrecios = new ListaDin[MAX_HABITACIONES - MIN_HABITACIONES + 1];
        for (int i = 0; i < listasPrecios.length; i++) {
            listasPrecios[i] = new ListaDin();
        }

        // Agrupar precios por número de habitaciones redondeado
        habitaciones.iniciaIterador();
        precios.iniciaIterador();
        while (habitaciones.iteradorValido() && precios.iteradorValido()) {
            double numHabitaciones = ((Number) habitaciones.obtenerIterador()).doubleValue();
            double precioCasa = ((Number) precios.obtenerIterador()).doubleValue();
            int habitacionesRedondeadas = (int) Math.round(numHabitaciones);
            if (habitacionesRedondeadas >= MIN_HABITACIONES && habitacionesRedondeadas <= MAX_HABITACIONES) {
                listasPrecios[habitacionesRedondeadas - MIN_HABITACIONES].poner(precioCasa);
            }
            habitaciones.moverIterador();
            precios.moverIterador();
        }

        // Calcular y mostrar promedios
        Salida.salidaPorDefecto("Precio Promedio por Número de Habitaciones:\n");
        for (int i = 0; i < listasPrecios.length; i++) {
            if (!listasPrecios[i].vacia()) {
                double promedio = ((Number) listasPrecios[i].promedio()).doubleValue();
                Salida.salidaPorDefecto("Habitaciones: " + (i + MIN_HABITACIONES) + ", Precio Promedio: " + promedio + " mil dólares\n");
            }
        }
    }

    public void calcularMinMaxPorHabitaciones(ListaDin habitaciones, ListaDin precios) {
        // Crear una ListaDin para cada número de habitaciones
        ListaDin[] listasPrecios = new ListaDin[MAX_HABITACIONES - MIN_HABITACIONES + 1];
        for (int i = 0; i < listasPrecios.length; i++) {
            listasPrecios[i] = new ListaDin();
        }

        // Agrupar precios por número de habitaciones redondeado
        habitaciones.iniciaIterador();
        precios.iniciaIterador();
        while (habitaciones.iteradorValido() && precios.iteradorValido()) {
            double numHabitaciones = ((Number) habitaciones.obtenerIterador()).doubleValue();
            double precioCasa = ((Number) precios.obtenerIterador()).doubleValue();
            int habitacionesRedondeadas = (int) Math.round(numHabitaciones);
            if (habitacionesRedondeadas >= MIN_HABITACIONES && habitacionesRedondeadas <= MAX_HABITACIONES) {
                listasPrecios[habitacionesRedondeadas - MIN_HABITACIONES].poner(precioCasa);
            }
            habitaciones.moverIterador();
            precios.moverIterador();
        }

        // Calcular y mostrar mínimo y máximo
        Salida.salidaPorDefecto("Precio Mínimo y Máximo por Número de Habitaciones:\n");
        for (int i = 0; i < listasPrecios.length; i++) {
            if (!listasPrecios[i].vacia()) {
                double maximo = ((Number) listasPrecios[i].encontrarMayor()).doubleValue();
                double minimo = maximo; // Inicializar con el máximo
                // Encontrar el mínimo iterando
                listasPrecios[i].iniciaIterador();
                while (listasPrecios[i].iteradorValido()) {
                    double precio = ((Number) listasPrecios[i].obtenerIterador()).doubleValue();
                    if (precio < minimo) minimo = precio;
                    listasPrecios[i].moverIterador();
                }
                Salida.salidaPorDefecto("Habitaciones: " + (i + MIN_HABITACIONES) + ", Precio Mínimo: " + minimo + ", Precio Máximo: " + maximo + " mil dólares\n");
            }
        }
    }

    public double calcularPearsonMuestral(ListaDin habitaciones, ListaDin precios) {
        if (habitaciones.vacia() || precios.vacia()) return 0.0;

        double mediaHabitaciones = ((Number) habitaciones.promedio()).doubleValue();
        double mediaPrecios = ((Number) precios.promedio()).doubleValue();
        double numerador = 0, sumaDesviacionesHabitaciones2 = 0, sumaDesviacionesPrecios2 = 0;
        int totalDatos = 0;

        habitaciones.iniciaIterador();
        precios.iniciaIterador();
        while (habitaciones.iteradorValido() && precios.iteradorValido()) {
            double numHabitaciones = ((Number) habitaciones.obtenerIterador()).doubleValue();
            double precioCasa = ((Number) precios.obtenerIterador()).doubleValue();
            double desviacionHabitaciones = numHabitaciones - mediaHabitaciones;
            double desviacionPrecios = precioCasa - mediaPrecios;
            numerador += desviacionHabitaciones * desviacionPrecios;
            sumaDesviacionesHabitaciones2 += desviacionHabitaciones * desviacionHabitaciones;
            sumaDesviacionesPrecios2 += desviacionPrecios * desviacionPrecios;
            totalDatos++;
            habitaciones.moverIterador();
            precios.moverIterador();
        }

        double denominador = Math.sqrt(sumaDesviacionesHabitaciones2 * sumaDesviacionesPrecios2);
        return denominador == 0 ? 0.0 : numerador / denominador;
    }

    public double calcularPearsonPoblacional(ListaDin habitaciones, ListaDin precios) {
        if (habitaciones.vacia() || precios.vacia()) return 0.0;

        double mediaHabitaciones = ((Number) habitaciones.promedio()).doubleValue();
        double mediaPrecios = ((Number) precios.promedio()).doubleValue();
        double covarianza = 0;
        double varianzaHabitaciones = 0, varianzaPrecios = 0;
        int totalDatos = 0;

        habitaciones.iniciaIterador();
        precios.iniciaIterador();
        while (habitaciones.iteradorValido() && precios.iteradorValido()) {
            double numHabitaciones = ((Number) habitaciones.obtenerIterador()).doubleValue();
            double precioCasa = ((Number) precios.obtenerIterador()).doubleValue();
            double desviacionHabitaciones = numHabitaciones - mediaHabitaciones;
            double desviacionPrecios = precioCasa - mediaPrecios;
            covarianza += desviacionHabitaciones * desviacionPrecios;
            varianzaHabitaciones += desviacionHabitaciones * desviacionHabitaciones;
            varianzaPrecios += desviacionPrecios * desviacionPrecios;
            totalDatos++;
            habitaciones.moverIterador();
            precios.moverIterador();
        }

        covarianza /= totalDatos; // Covarianza poblacional
        double desviacionEstandarHabitaciones = Math.sqrt(varianzaHabitaciones / totalDatos);
        double desviacionEstandarPrecios = Math.sqrt(varianzaPrecios / totalDatos);
        return desviacionEstandarHabitaciones * desviacionEstandarPrecios == 0 ? 0.0 : covarianza / (desviacionEstandarHabitaciones * desviacionEstandarPrecios);
    }

    public void compararCoeficientesPearson(double coeficienteMuestral, double coeficientePoblacional) {
        Salida.salidaPorDefecto("Comparación de Coeficientes de Pearson:\n");
        Salida.salidaPorDefecto("El coeficiente muestral (r) asume que los datos son una muestra, dividiendo por n-1 para la covarianza.\n");
        Salida.salidaPorDefecto("El coeficiente poblacional (ρ) asume que los datos son la población total, dividiendo por n.\n");
        if (Math.abs(coeficienteMuestral - coeficientePoblacional) < 0.01) {
            Salida.salidaPorDefecto("Los valores son muy similares (" + coeficienteMuestral + " vs " + coeficientePoblacional + "), indicando un tamaño de muestra grande donde n ≈ n-1.\n");
        } else {
            Salida.salidaPorDefecto("Los valores difieren (" + coeficienteMuestral + " vs " + coeficientePoblacional + "), reflejando la suposición de muestra vs. población.\n");
        }
        Salida.salidaPorDefecto("Conclusión: La elección de muestra vs. población afecta ligeramente el coeficiente, pero ambos indican una fuerza de correlación similar.\n");
    }
}
