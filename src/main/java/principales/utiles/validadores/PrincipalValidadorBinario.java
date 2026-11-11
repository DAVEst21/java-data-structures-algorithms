package principales.utiles.validadores;

import colecciones.edlineal.arreglos.ArregloNumerico;
import utils.entradasalida.cadenas.Salida;

import static utils.validadores.ValidadorBinario.esBinario;

public class PrincipalValidadorBinario {
    public static void main(String[] args) {
        // Casos de prueba
        ArregloNumerico pruebas = new ArregloNumerico(5);
        pruebas.poner(101001);
        pruebas.poner(123001);
        pruebas.poner(1111);
        pruebas.poner(100);
        pruebas.poner(10201);

        for (int i = 0; i < pruebas.cantidad(); i++) {
            long num = ((Number) pruebas.obtener(i)).longValue();
            boolean resultado = esBinario(num);
            Salida.salidaPorDefecto("Número " + num + " es binario: " + resultado + "\n");
        }
    }
}
