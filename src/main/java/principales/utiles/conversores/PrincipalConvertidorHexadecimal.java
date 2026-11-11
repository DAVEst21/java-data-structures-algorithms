package principales.utiles.conversores;

import colecciones.edlineal.arreglos.ArregloNumerico;
import utils.entradasalida.cadenas.Salida;

import static utils.conversores.ConvertidorHexadecimal.decimalAHex;

public class PrincipalConvertidorHexadecimal {
    public static void main(String[] args) {
        // Casos de prueba usando ArregloNumerico
        ArregloNumerico pruebas = new ArregloNumerico(5);
        pruebas.poner(65029);
        pruebas.poner(255);
        pruebas.poner(0);
        pruebas.poner(16);
        pruebas.poner(1000);

        for (int i = 0; i < pruebas.cantidad(); i++) {
            long num = ((Number) pruebas.obtener(i)).longValue();
            String hex = decimalAHex(num);
            Salida.salidaPorDefecto("Decimal " + num + " en hexadecimal: " + hex + "\n");
        }
    }
}
