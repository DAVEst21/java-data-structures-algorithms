/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package principales.arreglos;

import colecciones.edlineal.arreglos.Arreglo;
import utils.entradasalida.cadenas.Salida;

/**
 *
 * @author Usuario
 */
public class PrincipalArreglo {

    public static void main(String[] args) {
        //Declara una lista de tipo arreglo con tamaño de 10
        Arreglo lista = new Arreglo(10);
        // Agrega elementos a la lista
        lista.poner("David");
        lista.poner("Ignacio");
        lista.poner("Becerra");
        lista.poner("Programación");
        lista.poner("Java");
        // Imprime la lista original
        Salida.salidaPorDefecto("Lista original:\n");
        lista.imprimir();
        Salida.salidaPorDefecto("\n");
        
        // Prueba el método esIgual
        Arreglo lista2 = new Arreglo(10);
        lista2.poner("David");
        lista2.poner("Ignacio");
        lista2.poner("Becerra");
        lista2.poner("Programación");
        lista2.poner("Java");
        Salida.salidaPorDefecto("Las listas son iguales? " + lista.esIgual(lista2) + "\n\n");

        // Prueba el método invertir
        Salida.salidaPorDefecto("Lista invertida:\n");
        lista.invertir();
        lista.imprimir();
        Salida.salidaPorDefecto("\n");

        // Prueba el método buscarValores
        Salida.salidaPorDefecto("Posiciones donde aparece 'David':\n");
        Arreglo posiciones = lista.buscarValores("David");
        posiciones.imprimir();
        Salida.salidaPorDefecto("\n");

        // Prueba el método contar
        Salida.salidaPorDefecto("Número de veces que aparece 'Ignacio': " + lista.contar("Ignacio") + "\n\n");

        // Prueba el método modificar
        Salida.salidaPorDefecto("Modificando 'David' por 'Diana':\n");
        lista.modificar("David", "Diana", 1);
        lista.imprimir();
        Salida.salidaPorDefecto("\n");

        // Prueba el método quitar(int indice)
        Salida.salidaPorDefecto("Quitando el elemento en la posición 2:\n");
        lista.quitar(2);
        lista.imprimir();
        Salida.salidaPorDefecto("\n");

        // Prueba el método vaciar
        Salida.salidaPorDefecto("Vaciando la lista:\n");
        lista.vaciar();
        lista.imprimir();
        Salida.salidaPorDefecto("\n");

        // Prueba el método agregarLista
        Salida.salidaPorDefecto("Agregando lista2 a lista:\n");
        lista.agregarLista(lista2);
        lista.imprimir();
        Salida.salidaPorDefecto("\n");

        // Prueba el método clonar
        Salida.salidaPorDefecto("Clonando la lista:\n");
        Arreglo listaClonada = (Arreglo) lista.clonar();
        listaClonada.imprimir();
        Salida.salidaPorDefecto("\n");

        // Prueba el método subLista
        Salida.salidaPorDefecto("SubLista desde la posición 1 hasta la 3:\n");
        Arreglo sublista = (Arreglo) lista.subLista(1, 3);
        sublista.imprimir();
        Salida.salidaPorDefecto("\n");

        // Prueba el método redimensionar
        Salida.salidaPorDefecto("Redimensionando la lista a tamaño 5:\n");
        lista.redimensionar(5);
        lista.imprimir();
        Salida.salidaPorDefecto("\n");

        // Prueba el método modificar(int indice, Object valor)
        Salida.salidaPorDefecto("Modificando el elemento en la posición 1 por 'Ignacio':\n");
        lista.modificar(1, "Ignacio");
        lista.imprimir();
        Salida.salidaPorDefecto("\n");

        // Prueba el método modificarLista
        Arreglo indicesBusqueda = new Arreglo(2);
        indicesBusqueda.poner(0);
        indicesBusqueda.poner(2);

        Arreglo valoresNuevos = new Arreglo(2);
        valoresNuevos.poner("David");
        valoresNuevos.poner("Becerra");

        Salida.salidaPorDefecto("Modificando elementos en las posiciones 0 y 2:\n");
        lista.modificarLista(indicesBusqueda, valoresNuevos);
        lista.imprimir();
        Salida.salidaPorDefecto("\n");

        // Prueba el método quitar()
        Salida.salidaPorDefecto("Quitando el último elemento:\n");
        lista.quitar();
        lista.imprimir();
        Salida.salidaPorDefecto("\n");

        // Prueba el método borrarLista
        Salida.salidaPorDefecto("Borrando elementos de lista2 en lista:\n");
        lista.borrarLista(lista2);
        lista.imprimir();
        Salida.salidaPorDefecto("\n");

        // Prueba el método rellenar
        Salida.salidaPorDefecto("Rellenando la lista con 'X' (3 veces):\n");
        lista.rellenar("X", 3);
        lista.imprimir();
        Salida.salidaPorDefecto("\n");

        // Prueba el método substituir
        Salida.salidaPorDefecto("Substituyendo la lista por lista2:\n");
        lista.substituir(lista2);
        lista.imprimir();
        Salida.salidaPorDefecto("\n");
    }
}