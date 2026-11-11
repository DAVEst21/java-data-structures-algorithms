package registros.casas;

import colecciones.edlineal.listas.ListaDin;
import utils.entradasalida.cadenas.Salida;

public class CalculadorPearson {
    public double calcular(ListaDin habitaciones, ListaDin precios) {
        validarDatos(habitaciones, precios);
        if (habitaciones.vacia() || precios.vacia()) {
            return 0.0;
        }

        double sumaHabitaciones = 0, sumaPrecios = 0, sumaProducto = 0, sumaHabitacionesCuadrado = 0, sumaPreciosCuadrado = 0;
        int totalDatos = 0;

        // Inicializar iteradores
        habitaciones.iniciaIterador();
        precios.iniciaIterador();

        // Calcular sumas
        while (habitaciones.iteradorValido() && precios.iteradorValido()) {
            double numHabitaciones = ((Number) habitaciones.obtenerIterador()).doubleValue();
            double precioCasa = ((Number) precios.obtenerIterador()).doubleValue();

            sumaHabitaciones += numHabitaciones;
            sumaPrecios += precioCasa;
            sumaProducto += numHabitaciones * precioCasa;
            sumaHabitacionesCuadrado += numHabitaciones * numHabitaciones;
            sumaPreciosCuadrado += precioCasa * precioCasa;
            totalDatos++;

            habitaciones.moverIterador();
            precios.moverIterador();
        }

        // Verificar si hay datos
        if (totalDatos == 0) return 0.0;

        // Calcular coeficiente de Pearson
        double numerador = totalDatos * sumaProducto - sumaHabitaciones * sumaPrecios;
        double denominador = Math.sqrt((totalDatos * sumaHabitacionesCuadrado - sumaHabitaciones * sumaHabitaciones) *
                (totalDatos * sumaPreciosCuadrado - sumaPrecios * sumaPrecios));

        return denominador == 0 ? 0.0 : numerador / denominador;
    }

    public void interpretar(double coeficiente) {
        Salida.salidaPorDefecto("Interpretación: ");
        if (coeficiente == 0) {
            Salida.salidaPorDefecto("Ninguna correlación\n");
        } else if (coeficiente == 1) {
            Salida.salidaPorDefecto("Correlación positiva perfecta\n");
        } else if (coeficiente == -1) {
            Salida.salidaPorDefecto("Correlación negativa perfecta\n");
        } else if (coeficiente > 0) {
            Salida.salidaPorDefecto("Correlación positiva\n");
        } else {
            Salida.salidaPorDefecto("Correlación negativa\n");
        }

        // Interpretación de la fuerza de la correlación
        double valorAbsoluto = Math.abs(coeficiente);
        if (valorAbsoluto == 0) {
            Salida.salidaPorDefecto("Fuerza: Asociación nula\n");
        } else if (valorAbsoluto <= 0.1) {
            Salida.salidaPorDefecto("Fuerza: Asociación pequeña\n");
        } else if (valorAbsoluto <= 0.3) {
            Salida.salidaPorDefecto("Fuerza: Asociación mediana\n");
        } else if (valorAbsoluto <= 0.5) {
            Salida.salidaPorDefecto("Fuerza: Asociación moderada\n");
        } else if (valorAbsoluto <= 0.7) {
            Salida.salidaPorDefecto("Fuerza: Asociación alta\n");
        } else {
            Salida.salidaPorDefecto("Fuerza: Asociación muy alta\n");
        }
    }
    public static void validarDatos(ListaDin habitaciones, ListaDin precios) {
        if (habitaciones == null || precios == null || habitaciones.cantidad() != precios.cantidad())
            throw new IllegalArgumentException("Las listas deben tener la misma cantidad de valores");
        habitaciones.iniciaIterador();
        precios.iniciaIterador();
        while (habitaciones.iteradorValido()) {
            Object x = habitaciones.obtenerIterador(), y = precios.obtenerIterador();
            if (!(x instanceof Number) || !(y instanceof Number) ||
                    !Double.isFinite(((Number) x).doubleValue()) || !Double.isFinite(((Number) y).doubleValue()))
                throw new IllegalArgumentException("Los datos deben ser números finitos");
            habitaciones.moverIterador(); precios.moverIterador();
        }
    }
}
