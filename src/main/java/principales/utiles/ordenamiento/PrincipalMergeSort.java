package principales.utiles.ordenamiento;

import colecciones.edlineal.arreglos.Arreglo;
import utils.entradasalida.cadenas.Salida;

import static utils.ordenamiento.MergeSort.mergeSort;

public class PrincipalMergeSort {
    public static void main(String[] args) {
        // Crear arreglo de prueba
        Arreglo arregloPrueba = new Arreglo(8);
        arregloPrueba.poner(5);
        arregloPrueba.poner(2);
        arregloPrueba.poner(4);
        arregloPrueba.poner(6);
        arregloPrueba.poner(1);
        arregloPrueba.poner(3);
        arregloPrueba.poner(2);
        arregloPrueba.poner(6);

        // Mostrar arreglo original
        Salida.salidaPorDefecto("Arreglo original: ");
        for (int indice = 0; indice < arregloPrueba.cantidad(); indice++) {
            Salida.salidaPorDefecto(arregloPrueba.obtener(indice) + " ");
        }
        Salida.salidaPorDefecto("\n");

        // Ordenar
        mergeSort(arregloPrueba);

        // Mostrar arreglo ordenado
        Salida.salidaPorDefecto("Arreglo ordenado: ");
        for (int indice = 0; indice < arregloPrueba.cantidad(); indice++) {
            Salida.salidaPorDefecto(arregloPrueba.obtener(indice) + " ");
        }
        Salida.salidaPorDefecto("\n");
    }
}
