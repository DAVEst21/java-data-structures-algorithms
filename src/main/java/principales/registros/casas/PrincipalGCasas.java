package principales.registros.casas;

import colecciones.edlineal.arreglos.Arreglo;
import registros.casas.DatosCasas;
import utils.entradasalida.archivos.ArchivoTextoD;
import utils.graficadores.GraficadorDispersion;

public class PrincipalGCasas {
    public static void main(String[] args) {
        // Leer datos de archivos
        Arreglo arregloHabitaciones = ArchivoTextoD.leer("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\ed_4b_25\\src\\main\\java\\resources\\casas\\habitaciones.txt");
        Arreglo arregloPrecios = ArchivoTextoD.leer("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\ed_4b_25\\src\\main\\java\\resources\\casas\\precios.txt");

        // Crear objeto de datos
        DatosCasas datos = new DatosCasas(arregloHabitaciones, arregloPrecios);

        // Generar gráfico
        GraficadorDispersion graficador = new GraficadorDispersion();
        graficador.generarGraficoDispersion(datos.getHabitaciones(), datos.getPrecios(), "C:\\Users\\Usuario\\Documents\\NetBeansProjects\\ed_4b_25\\resultados\\graficos\\grafico_casas.png");
    }
}
