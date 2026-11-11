package principales.utiles.conversores;

import colecciones.edlineal.arreglos.ArregloNumerico;
import utils.entradasalida.cadenas.Salida;

import static utils.conversores.ConvertidorBinario.decimalABinario;

public class PrincipalConvertidorBinario {
    public static void main(String[] args) {
        // Casos de prueba usando ArregloNumerico
        ArregloNumerico pruebas = new ArregloNumerico(5);
        pruebas.poner(150);
        pruebas.poner(7);
        pruebas.poner(0);
        pruebas.poner(255);
        pruebas.poner(1024);

        for (int i = 0; i < pruebas.cantidad(); i++) {
            long num = ((Number) pruebas.obtener(i)).longValue();
            String binario = decimalABinario(num);
            Salida.salidaPorDefecto("Decimal " + num + " en binario: " + binario + "\n");
        }
    }
}
