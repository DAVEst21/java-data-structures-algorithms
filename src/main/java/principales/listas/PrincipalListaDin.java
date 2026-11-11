package principales.listas;

import colecciones.edlineal.arreglos.Arreglo;
import colecciones.edlineal.arreglos.ArregloNumerico;
import colecciones.interfaces.ListaDatos;
import colecciones.edlineal.listas.ListaDin;
import colecciones.ednolineal.Arreglo2D;
import utils.entradasalida.cadenas.Salida;

public class PrincipalListaDin {
    public static void main(String[] args) {
        Salida.salidaPorDefecto("=== Prueba de ListaDin ===\n");

        // Crear una lista dinámica
        ListaDin lista = new ListaDin();

        // Probar vacia()
        Salida.salidaPorDefecto("1. ¿La lista está vacía? " + lista.vacia() + "\n");

        // Probar poner() y llenar la lista
        Salida.salidaPorDefecto("2. Agregando elementos (5, 10, 5, 15)...\n");
        lista.poner(5);
        lista.poner(10);
        lista.poner(5);
        lista.poner(15);
        Salida.salidaPorDefecto("Lista: ");
        lista.imprimir();

        // Probar mostrarOI()
        Salida.salidaPorDefecto("3. Imprimir en orden inverso:\n");
        lista.mostrarOI();

        // Probar encontrarLista()
        Salida.salidaPorDefecto("4. Encontrar todas las ocurrencias de 5:\n");
        ListaDin ocurrencias = lista.encontrarLista(5);
        Salida.salidaPorDefecto("Ocurrencias: ");
        ocurrencias.imprimir();

        // Probar aListaEstatica()
        Salida.salidaPorDefecto("5. Convertir a Arreglo:\n");
        Arreglo arreglo = lista.aListaEstatica();
        Salida.salidaPorDefecto("Arreglo: ");
        arreglo.imprimir();

        // Probar aListaEstatica(Arreglo elementosADescartar)
        Salida.salidaPorDefecto("6. Convertir a Arreglo descartando [10, 15]:\n");
        Arreglo descartar = new Arreglo(2);
        descartar.poner(10);
        descartar.poner(15);
        Arreglo filtrado = lista.aListaEstatica(descartar);
        Salida.salidaPorDefecto("Arreglo filtrado: ");
        filtrado.imprimir();

        // Probar aMatriz2D()
        Salida.salidaPorDefecto("7. Convertir a matriz 2D (2x3):\n");
        Arreglo2D matriz = lista.aMatriz2D(2, 3);
        Salida.salidaPorDefecto("Matriz:\n");
        matriz.imprimirXRenglones();

        // Probar agregarLista()
        Salida.salidaPorDefecto("8. Agregar lista [20, 25]:\n");
        ListaDin lista2 = new ListaDin();
        lista2.poner(20);
        lista2.poner(25);
        lista.agregarLista(lista2);
        Salida.salidaPorDefecto("Lista actualizada: ");
        lista.imprimir();

        // Probar clonar()
        Salida.salidaPorDefecto("9. Clonar la lista:\n");
        ListaDatos copia = lista.clonar();
        Salida.salidaPorDefecto("Copia: ");
        copia.imprimir();

        // Probar agregarMatriz2D()
        Salida.salidaPorDefecto("10. Agregar matriz 2x2 por renglones:\n");
        Arreglo2D matriz2 = new Arreglo2D(2, 2);
        matriz2.cambiar(0, 0, 1);
        matriz2.cambiar(0, 1, 2);
        matriz2.cambiar(1, 0, 3);
        matriz2.cambiar(1, 1, 4);
        lista.agregarMatriz2D(matriz2, ListaDin.TipoTabla.RENGLON);
        Salida.salidaPorDefecto("Lista tras agregar matriz: ");
        lista.imprimir();

        // Probar vaciar()
        Salida.salidaPorDefecto("11. Vaciando la lista...\n");
        lista.vaciar();
        Salida.salidaPorDefecto("¿La lista está vacía? " + lista.vacia() + "\n");

        // Probar rellenar()
        Salida.salidaPorDefecto("12. Rellenar con 'A' (3 veces):\n");
        lista.rellenar("A", 3);
        Salida.salidaPorDefecto("Lista: ");
        lista.imprimir();

        // Probar contar()
        Salida.salidaPorDefecto("13. Contar ocurrencias de 'A': " + lista.contar("A") + "\n");

        // Probar invertir()
        Salida.salidaPorDefecto("14. Invertir la lista:\n");
        lista.invertir();
        Salida.salidaPorDefecto("Lista invertida: ");
        lista.imprimir();

        // Probar cambiar(Object, Object, int)
        Salida.salidaPorDefecto("15. Cambiar 2 'A' por 'B':\n");
        lista.cambiar("A", "B", 2);
        Salida.salidaPorDefecto("Lista: ");
        lista.imprimir();

        // Probar cambiar(int, Object)
        Salida.salidaPorDefecto("16. Cambiar índice 1 por 'C':\n");
        lista.cambiar(1, "C");
        Salida.salidaPorDefecto("Lista: ");
        lista.imprimir();

        // Probar obtener()
        Salida.salidaPorDefecto("17. Obtener elemento en índice 1: " + lista.obtener(1) + "\n");

        // Probar esIgual()
        Salida.salidaPorDefecto("18. ¿Es igual a [B, C, B]? ");
        ListaDin lista3 = new ListaDin();
        lista3.poner("B");
        lista3.poner("C");
        lista3.poner("B");
        Salida.salidaPorDefecto(lista.esIgual(lista3) + "\n");

        // Probar redimensionar()
        Salida.salidaPorDefecto("19. Redimensionar a 2 elementos:\n");
        lista.redimensionar(2);
        Salida.salidaPorDefecto("Lista: ");
        lista.imprimir();

        // Probar quitar(int)
        Salida.salidaPorDefecto("20. Quitar elemento en índice 0:\n");
        Object eliminado = lista.quitar(0);
        Salida.salidaPorDefecto("Eliminado: " + eliminado + "\nLista: ");
        lista.imprimir();

        // Probar quitar(Object)
        Salida.salidaPorDefecto("21. Quitar todas las 'C':\n");
        ListaDin eliminados = lista.quitar("C");
        Salida.salidaPorDefecto("Eliminados: ");
        eliminados.imprimir();
        Salida.salidaPorDefecto("Lista: ");
        lista.imprimir();

        // Probar otros métodos de ListaDatos
        Salida.salidaPorDefecto("22. Probar otros métodos de ListaDatos:\n");
        lista.poner(1.0);
        lista.poner(2.0);
        lista.poner(3.0);
        Salida.salidaPorDefecto("Lista numérica: ");
        lista.imprimir();

        // buscar()
        Salida.salidaPorDefecto("   Buscar 2.0: " + lista.buscar(2.0) + "\n");

        // buscarValores()
        Salida.salidaPorDefecto("   Buscar posiciones de 2.0: ");
        lista.buscarValores(2.0).imprimir();

        // borrarLista()
        ListaDin lista4 = new ListaDin();
        lista4.poner(2.0);
        lista.borrarLista(lista4);
        Salida.salidaPorDefecto("   Tras borrar [2.0]: ");
        lista.imprimir();

        // subLista()
        Salida.salidaPorDefecto("   SubLista (índices 0-1): ");
        lista.subLista(0, 1).imprimir();

        // promedio()
        Salida.salidaPorDefecto("   Promedio: " + lista.promedio() + "\n");

        // encontrarMayor()
        Salida.salidaPorDefecto("   Mayor: " + lista.encontrarMayor() + "\n");

        // suma()
        Salida.salidaPorDefecto("   Suma: " + lista.suma() + "\n");

        // validaPosicion()
        Salida.salidaPorDefecto("   ¿Índice 1 válido? " + lista.validaPosicion(1) + "\n");

        // rellenar(Object)
        Salida.salidaPorDefecto("   Rellenar con 0:\n");
        lista.rellenar(0);
        Salida.salidaPorDefecto("   Lista: ");
        lista.imprimir();

        // arregloDesordenado() (implementación básica)
        Salida.salidaPorDefecto("   Arreglo desordenado: ");
        lista.arregloDesordenado().imprimir();

        // esSublista()
        ListaDin sublista = new ListaDin();
        sublista.poner(0);
        Salida.salidaPorDefecto("   ¿[0] es sublista? " + lista.esSublista(sublista) + "\n");

        // modificarLista()
        ListaDin vieja = new ListaDin();
        vieja.poner(0);
        ListaDin nueva = new ListaDin();
        nueva.poner(99);
        lista.modificarLista(vieja, nueva);
        Salida.salidaPorDefecto("   Tras modificar 0 por 99: ");
        lista.imprimir();

        // retenerLista()
        ListaDin retener = new ListaDin();
        retener.poner(99);
        lista.retenerLista(retener);
        Salida.salidaPorDefecto("   Retener [99]: ");
        lista.imprimir();

        // poner(int, Object)
        Salida.salidaPorDefecto("   Poner 100 en índice 0:\n");
        lista.poner(0, 100);
        Salida.salidaPorDefecto("   Lista: ");
        lista.imprimir();

        // subLista(ArregloNumerico)
        ArregloNumerico indices = new ArregloNumerico(2);
        indices.poner(0);
        indices.poner(1);
        Salida.salidaPorDefecto("   SubLista en índices [0, 1]: ");
        lista.subLista(indices).imprimir();

        // verFinal(), verInicio()
        Salida.salidaPorDefecto("   Inicio: " + lista.verInicio() + "\n");
        Salida.salidaPorDefecto("   Final: " + lista.verFinal() + "\n");

        // Iterador
        Salida.salidaPorDefecto("   Iterador:\n");
        lista.iniciaIterador();
        while (lista.iteradorValido()) {
            Salida.salidaPorDefecto("   - " + lista.obtenerIterador() + "\n");
            lista.moverIterador();
        }

        Salida.salidaPorDefecto("\n=== Fin de la prueba ===\n");
    }
}
