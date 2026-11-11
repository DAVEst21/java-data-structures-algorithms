/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principales.arreglos;

import colecciones.edlineal.arreglos.ArregloOrdenado;
import colecciones.interfaces.ListaDatos;
import utils.entradasalida.cadenas.Salida;

/**
 *
 * @author Usuario
 */
public class PrincipalAOrdenadoMetodos {
    public static void main(String[] args) {
        //Crear un arreglo ordenado incremental
        ArregloOrdenado arreglo = new ArregloOrdenado(10, ArregloOrdenado.TipoOrdenamiento.INC);

        //1. Probar el método poner(Object valor)
        Salida.salidaPorDefecto("\n--- Prueba de poner(Object valor) ---\n");
        arreglo.poner(5);
        arreglo.poner(3);
        arreglo.poner(8);
        arreglo.poner(1);
        Salida.salidaPorDefecto("Arreglo después de insertar 5, 3, 8, 1:\n");
        arreglo.imprimir();

        //2. Probar el método buscar(Object valor)
        Salida.salidaPorDefecto("\n--- Prueba de buscar(Object valor) ---\n");
        int posicion = (int) arreglo.buscar(3);
        Salida.salidaPorDefecto("El valor 3 está en la posición: " + posicion + "\n");

        //3. Probar el método modificar(int indice, Object valor)
        Salida.salidaPorDefecto("\n--- Prueba de modificar(int indice, Object valor) ---\n");
        arreglo.modificar(1, 10);
        Salida.salidaPorDefecto("Arreglo después de modificar el indice 1 con el valor 10:\n");
        arreglo.imprimir();

        //4. Probar el método quitar(Object valor)
        Salida.salidaPorDefecto("\n--- Prueba de quitar(Object valor) ---\n");
        arreglo.quitar((Object) 5);
        Salida.salidaPorDefecto("Arreglo después de quitar el valor 5:\n");
        arreglo.imprimir();

        //5. Probar el método agregarLista(ListaDatos lista2)
        Salida.salidaPorDefecto("\n--- Prueba de agregarLista(ListaDatos lista2) ---\n");
        ArregloOrdenado otraLista = new ArregloOrdenado(5, ArregloOrdenado.TipoOrdenamiento.INC);
        otraLista.poner(2);
        otraLista.poner(7);
        arreglo.agregarLista(otraLista);
        Salida.salidaPorDefecto("Arreglo después de agregar otra lista (2, 7):\n");
        arreglo.imprimir();

        //6. Probar el método invertir()
        Salida.salidaPorDefecto("\n--- Prueba de invertir() ---\n");
        arreglo.invertir();
        Salida.salidaPorDefecto("Arreglo después de invertir:\n");
        arreglo.imprimir();

        // 7. Probar el método rellenar(Object valor)
        Salida.salidaPorDefecto("\n--- Prueba de rellenar(Object valor) ---\n");
        // Caso 1 Valor numérico positivo (orden INC)
        Salida.salidaPorDefecto("Rellenar con 5 (orden INC):\n");
        ArregloOrdenado arreglo1 = new ArregloOrdenado(10, ArregloOrdenado.TipoOrdenamiento.INC);
        arreglo1.rellenar(5);
        arreglo1.imprimir();

        // Caso 2 Valor numérico positivo (orden DEC)
        Salida.salidaPorDefecto("\nRellenar con 5 (orden DEC):\n");
        ArregloOrdenado arreglo2 = new ArregloOrdenado(10, ArregloOrdenado.TipoOrdenamiento.DEC);
        arreglo2.rellenar(5);
        arreglo2.imprimir();

        // Caso 3 Valor numérico negativo (orden INC)
        Salida.salidaPorDefecto("\nRellenar con -5 (orden INC):\n");
        ArregloOrdenado arreglo3 = new ArregloOrdenado(10, ArregloOrdenado.TipoOrdenamiento.INC);
        arreglo3.rellenar(-5);
        arreglo3.imprimir();

        // Caso 4 Valor numérico negativo (orden DEC)
        Salida.salidaPorDefecto("\nRellenar con -5 (orden DEC):\n");
        ArregloOrdenado arreglo4 = new ArregloOrdenado(10, ArregloOrdenado.TipoOrdenamiento.DEC);
        arreglo4.rellenar(-5);
        arreglo4.imprimir();

        // Caso 5 Valor de tipo Character (orden INC)
        Salida.salidaPorDefecto("\nRellenar con 'H' (orden INC):\n");
        ArregloOrdenado arreglo5 = new ArregloOrdenado(10, ArregloOrdenado.TipoOrdenamiento.INC);
        arreglo5.rellenar('H');
        arreglo5.imprimir();

        // Caso 6 Valor de tipo Character (orden DEC)
        Salida.salidaPorDefecto("\nRellenar con 'H' (orden DEC):\n");
        ArregloOrdenado arreglo6 = new ArregloOrdenado(10, ArregloOrdenado.TipoOrdenamiento.DEC);
        arreglo6.rellenar('H');
        arreglo6.imprimir();

        // Caso 7 Valor de tipo String
        Salida.salidaPorDefecto("\nRellenar con \"hola\":\n");
        ArregloOrdenado arreglo7 = new ArregloOrdenado(10, ArregloOrdenado.TipoOrdenamiento.INC);
        arreglo7.rellenar("hola");
        arreglo7.imprimir();

        //8. Probar el método arregloDesordenado()
        Salida.salidaPorDefecto("\n--- Prueba de arregloDesordenado() ---\n");
        ListaDatos desordenado = arreglo.arregloDesordenado();
        Salida.salidaPorDefecto("Arreglo desordenado:\n");
        desordenado.imprimir();

        //9. Probar el método esSublista(ListaDatos lista2)
        Salida.salidaPorDefecto("\n--- Prueba de esSublista(ListaDatos lista2) ---\n");
        ArregloOrdenado sublista = new ArregloOrdenado(3, ArregloOrdenado.TipoOrdenamiento.INC);
        sublista.poner(2);
        sublista.poner(7);
        boolean esSublista = arreglo.esSublista(sublista);
        Salida.salidaPorDefecto("¿La lista (2, 7) es sublista del arreglo actual? " + esSublista + "\n");

        //10. Probar el método modificarLista(ListaDatos lista2, ListaDatos lista2Nuevos)
        Salida.salidaPorDefecto("\n--- Prueba de modificarLista(ListaDatos lista2, ListaDatos lista2Nuevos) ---\n");
        ArregloOrdenado listaVieja = new ArregloOrdenado(2, ArregloOrdenado.TipoOrdenamiento.INC);
        listaVieja.poner(2);
        listaVieja.poner(7);
        ArregloOrdenado listaNueva = new ArregloOrdenado(2, ArregloOrdenado.TipoOrdenamiento.INC);
        listaNueva.poner(20);
        listaNueva.poner(70);
        arreglo.modificarLista(listaVieja, listaNueva);
        Salida.salidaPorDefecto("Arreglo después de modificar la lista (2, 7) con (20, 70):\n");
        arreglo.imprimir();
    }
}
