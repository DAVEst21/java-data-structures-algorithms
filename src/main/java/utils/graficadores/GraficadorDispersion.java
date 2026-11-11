package utils.graficadores;

import colecciones.edlineal.listas.ListaDin;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import utils.entradasalida.cadenas.Salida;

import java.io.File;
import java.io.IOException;

public class GraficadorDispersion {
    // Variables para modificar los datos del gráfico
    private static final String NOMBRE = "Casas";
    private static final String TITULO = "Relación entre Habitaciones y Precios";
    private static final String ETIQUETA_X = "Número de Habitaciones";
    private static final String ETIQUETA_Y = "Precio (mil dólares)";


    public void generarGraficoDispersion(ListaDin habitaciones, ListaDin precios, String rutaImagen) {
        registros.casas.CalculadorPearson.validarDatos(habitaciones, precios);
        // Crear serie de datos
        XYSeries serie = new XYSeries(NOMBRE);
        habitaciones.iniciaIterador();
        precios.iniciaIterador();
        while (habitaciones.iteradorValido() && precios.iteradorValido()) {
            double numHabitaciones = ((Number) habitaciones.obtenerIterador()).doubleValue();
            double precioCasa = ((Number) precios.obtenerIterador()).doubleValue();
            serie.add(numHabitaciones, precioCasa);
            habitaciones.moverIterador();
            precios.moverIterador();
        }

        // Crear colección de datos
        XYSeriesCollection datos = new XYSeriesCollection();
        datos.addSeries(serie);

        // Crear gráfico de dispersión
        JFreeChart grafico = ChartFactory.createScatterPlot(
                TITULO,
                ETIQUETA_X,
                ETIQUETA_Y,
                datos,
                PlotOrientation.VERTICAL,               // Orientación
                true,                                   // Incluir leyenda
                true,                                   // Incluir tooltips
                false                                   // Incluir URLs
        );

        // Guardar gráfico como imagen
        try {
            ChartUtils.saveChartAsPNG(new File(rutaImagen), grafico, 800, 600);
            Salida.salidaPorDefecto("Gráfico generado y guardado en: " + rutaImagen + "\n");
        } catch (IOException e) {
            Salida.salidaPorDefecto("Error al guardar el gráfico: " + e.getMessage() + "\n");
        }
    }
}
