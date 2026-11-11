package principales.registros.casas;

import colecciones.edlineal.arreglos.Arreglo;
import registros.casas.DatosCasas;
import utils.entradasalida.archivos.ArchivoTextoD;
import utils.graficadores.GraficadorDispersion;

public class PrincipalGCasas {
    public static void main(String[] args) {
        // Leer datos de archivos
        Arreglo arregloHabitaciones = ArchivoTextoD.leer("src/main/java/resources/casas/habitaciones.txt");
        Arreglo arregloPrecios = ArchivoTextoD.leer("src/main/java/resources/casas/precios.txt");

        // Crear objeto de datos
        DatosCasas datos = new DatosCasas(arregloHabitaciones, arregloPrecios);

        // Generar gráfico
        GraficadorDispersion graficador = new GraficadorDispersion();
        graficador.generarGraficoDispersion(datos.getHabitaciones(), datos.getPrecios(), "target/grafico_casas.png");
    }
}
