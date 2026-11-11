package utils.regresion;

import colecciones.edlineal.arreglos.Arreglo;
import colecciones.edlineal.arreglos.ArregloNumerico;
import utils.entradasalida.archivos.ArchivoTextoD;

//
public class RegresionLineal {
    protected double interseccion; // Valor de la intersección (p o theta0)
    protected double pendiente;    // Valor de la pendiente (b o theta1)
    protected static final double TASA_APRENDIZAJE = 0.009; // Tasa de aprendizaje (alpha)
    protected static final int NUMERO_ITERACIONES = 100000; // Cantidad de iteraciones para el descenso de gradiente

    // Entrena el modelo de regresión lineal usando descenso de gradiente.
    // Calcula los parámetros interseccion y pendiente para la ecuación y = p + b*x.
    public void entrenar(ArregloNumerico numHabitaciones, ArregloNumerico precios) {
        // Obtener el número total de muestras
        int numMuestras = numHabitaciones.cantidad();

        // Inicializar los parámetros en cero
        interseccion = 0.0;
        pendiente = 0.0;

        // Realizar el descenso de gradiente por el número de iteraciones especificado
        for (int iteracion = 0; iteracion < NUMERO_ITERACIONES; iteracion++) {
            // Calcular la hipótesis: h(x) = interseccion + pendiente * x para cada muestra
            ArregloNumerico hipotesis = new ArregloNumerico(numMuestras);
            for (int posicion = 0; posicion < numMuestras; posicion++) {
                double valorX = ((Number) numHabitaciones.obtener(posicion)).doubleValue();
                hipotesis.poner(interseccion + pendiente * valorX);
            }

            // Calcular las sumas para las derivadas parciales
            double sumaErrorInterseccion = 0.0; // Para la derivada respecto a theta0
            double sumaErrorPendiente = 0.0; // Para la derivada respecto a theta1
            for (int posicion = 0; posicion < numMuestras; posicion++) {
                double valorX = ((Number) numHabitaciones.obtener(posicion)).doubleValue();
                double valorY = ((Number) precios.obtener(posicion)).doubleValue();
                double valorHipotesis = ((Number) hipotesis.obtener(posicion)).doubleValue();
                sumaErrorInterseccion += (valorHipotesis - valorY);
                sumaErrorPendiente += (valorHipotesis - valorY) * valorX;
            }

            // Calcular las derivadas parciales de la función de costo
            double derivadaInterseccion = (1.0 / numMuestras) * sumaErrorInterseccion;
            double derivadaPendiente = (1.0 / numMuestras) * sumaErrorPendiente;

            // Actualizar los parámetros simultáneamente
            interseccion = interseccion - TASA_APRENDIZAJE * derivadaInterseccion;
            pendiente = pendiente - TASA_APRENDIZAJE * derivadaPendiente;
        }
    }

    // Predice el precio de una casa según el número de habitaciones usando
    // la ecuación y = interseccion + pendiente * x.
    public double predecir(double numHabitaciones) {
        return interseccion + pendiente * numHabitaciones;
    }

    // Obtiene el valor de la intersección (p o theta0).
    public double obtenerInterseccion() {
        return interseccion;
    }


    // Obtiene el valor de la pendiente (b o theta1).
    public double obtenerPendiente() {
        return pendiente;
    }

    // Carga los datos de los archivos de texto y los convierte en arreglos numéricos.
    // Estática para no tener que requerir crear la instancia de RegresionLineal al cargar los datos.
    public static ArregloNumerico[] cargarDatos(String archivoHabitaciones, String archivoPrecios) {
        // Leer los datos de los archivos
        Arreglo datosHabitaciones = ArchivoTextoD.leer(archivoHabitaciones);
        Arreglo datosPrecios = ArchivoTextoD.leer(archivoPrecios);

        // Crear arreglos numéricos para almacenar los valores
        ArregloNumerico numHabitaciones = new ArregloNumerico(datosHabitaciones.cantidad());
        ArregloNumerico precios = new ArregloNumerico(datosPrecios.cantidad());

        // Convertir los datos a valores numéricos
        for (int posicion = 0; posicion < datosHabitaciones.cantidad(); posicion++) {
            double valorHabitaciones = Double.parseDouble(datosHabitaciones.obtener(posicion).toString());
            double valorPrecios = Double.parseDouble(datosPrecios.obtener(posicion).toString());
            numHabitaciones.poner(valorHabitaciones);
            precios.poner(valorPrecios);
        }

        return new ArregloNumerico[]{numHabitaciones, precios};
    }
}