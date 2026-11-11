package principales.utiles.regresion;

import colecciones.edlineal.arreglos.ArregloNumerico;
import utils.entradasalida.cadenas.*;
import utils.regresion.RegresionLineal;

public class PrincipalRegresion {
    public static void main(String[] args) {
        // Cargar los datos de los archivos de texto
        ArregloNumerico[] datos =
                RegresionLineal.cargarDatos("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\ed_4b_25\\src\\main\\java\\resources\\casas\\habitaciones.txt",
                        "C:\\Users\\Usuario\\Documents\\NetBeansProjects\\ed_4b_25\\src\\main\\java\\resources\\casas\\precios.txt");
        ArregloNumerico numHabitaciones = datos[0];
        ArregloNumerico precios = datos[1];

        // Crear y entrenar el modelo de regresión lineal
        RegresionLineal modelo = new RegresionLineal();
        modelo.entrenar(numHabitaciones, precios);

        // Mostrar los parámetros calculados
        Salida.salidaPorDefecto("Parámetros calculados:\n");
        Salida.salidaPorDefecto(String.format("p (intersección) = %.3f\n", modelo.obtenerInterseccion()));
        Salida.salidaPorDefecto(String.format("b (pendiente) = %.3f\n", modelo.obtenerPendiente()));
        Salida.salidaPorDefecto(String.format("Ecuación: y = %.3f + %.3f * x\n",
                modelo.obtenerInterseccion(), modelo.obtenerPendiente()));

        // Solicitar el número de habitaciones
        Salida.salidaPorDefecto("\nIngresa el número de habitaciones: ");

        double valorHabitaciones = Entrada.terminalDouble();
        double precioEstimado = modelo.predecir(valorHabitaciones);
        Salida.salidaPorDefecto(String.format("Precio estimado para %.2f habitaciones: %.2f miles de dólares\n",
                valorHabitaciones, precioEstimado));
    }
}