package utils.ordenamiento;

import colecciones.edlineal.arreglos.Arreglo;

public class MergeSort {
    // Ordena un arreglo usando Merge Sort
    public static void mergeSort(Arreglo arregloNumeros) {
        // Si el arreglo tiene 1 elemento o menos, ya está ordenado
        if (arregloNumeros.cantidad() <= 1) {
            return;
        }

        // Calcular el punto medio
        int indiceMedio = arregloNumeros.cantidad() / 2;

        // Crear subarreglos usando subLista
        Arreglo subarregloIzquierdo = (Arreglo) arregloNumeros.subLista(0, indiceMedio - 1);
        Arreglo subarregloDerecho = (Arreglo) arregloNumeros.subLista(indiceMedio, arregloNumeros.cantidad() - 1);

        // Ordenar recursivamente
        mergeSort(subarregloIzquierdo);
        mergeSort(subarregloDerecho);

        // Combinar los subarreglos ordenados
        combinar(subarregloIzquierdo, subarregloDerecho, arregloNumeros);
    }

    // Combina dos subarreglos ordenados en el arreglo original
    private static void combinar(Arreglo subarregloIzquierdo, Arreglo subarregloDerecho, Arreglo arregloDestino) {
        int tamanoIzquierdo = subarregloIzquierdo.cantidad();
        int tamanoDerecho = subarregloDerecho.cantidad();
        int indiceIzquierdo = 0;
        int indiceDerecho = 0;
        int indiceDestino = 0;

        // Comparar y combinar elementos en orden
        while (indiceIzquierdo < tamanoIzquierdo && indiceDerecho < tamanoDerecho) {
            double valorIzquierdo = ((Number) subarregloIzquierdo.obtener(indiceIzquierdo)).doubleValue();
            double valorDerecho = ((Number) subarregloDerecho.obtener(indiceDerecho)).doubleValue();
            if (valorIzquierdo <= valorDerecho) {
                arregloDestino.modificar(indiceDestino, valorIzquierdo);
                indiceIzquierdo++;
            } else {
                arregloDestino.modificar(indiceDestino, valorDerecho);
                indiceDerecho++;
            }
            indiceDestino++;
        }

        // Copiar elementos restantes de subarregloIzquierdo, si los hay
        while (indiceIzquierdo < tamanoIzquierdo) {
            arregloDestino.modificar(indiceDestino, subarregloIzquierdo.obtener(indiceIzquierdo));
            indiceIzquierdo++;
            indiceDestino++;
        }

        // Copiar elementos restantes de subarregloDerecho, si los hay
        while (indiceDerecho < tamanoDerecho) {
            arregloDestino.modificar(indiceDestino, subarregloDerecho.obtener(indiceDerecho));
            indiceDerecho++;
            indiceDestino++;
        }
    }
}