package principales.listas;

import colecciones.edlineal.arreglos.Arreglo;
import colecciones.edlineal.listas.ListaDin;
import colecciones.edlineal.listas.listadinclave.ListaDinClave;
import colecciones.ednolineal.Arreglo2D;
import utils.entradasalida.cadenas.Salida;

public class PrincipalListaDinClave {
    public static void main(String[] args) {
        ListaDinClave lista = new ListaDinClave();

        // Test o) Verificar si está vacía
        Salida.salidaPorDefecto("¿Lista vacía? " + lista.vacia() + "\n");

        // Test a) Poner elementos (clave, valor)
        Salida.salidaPorDefecto("\nInsertando elementos...\n");
        lista.poner("C1", "Valor1");
        lista.poner("C2", "Valor2");
        lista.poner("C3", "Valor3");
        lista.poner("C1", "Valor1Modificado"); // Actualizar valor existente
        lista.poner("C4", "Valor2"); // Duplicado en valor, permitido

        // Test g) Mostrar lista completa
        Salida.salidaPorDefecto("Lista completa:\n");
        lista.mostrar();

        // Test h) Mostrar claves
        Salida.salidaPorDefecto("Claves:\n");
        lista.mostrarClaves();

        // Test i) Mostrar valores
        Salida.salidaPorDefecto("Valores:\n");
        lista.mostrarValores();

        // Test q) Cantidad de elementos
        Salida.salidaPorDefecto("Cantidad de elementos: " + lista.cantidad() + "\n");

        // Test d) Buscar por clave
        Salida.salidaPorDefecto("\nBuscando por clave 'C2': " + lista.buscar("C2") + "\n");
        Salida.salidaPorDefecto("Buscando por clave 'C5': " + lista.buscar("C5") + "\n");

        // Test e) Buscar por contenido
        Salida.salidaPorDefecto("Buscando por valor 'Valor2': " + lista.buscarContenido("Valor2") + "\n");
        Salida.salidaPorDefecto("Buscando por valor 'ValorX': " + lista.buscarContenido("ValorX") + "\n");

        // Test n) Obtener valor por clave
        Salida.salidaPorDefecto("Obteniendo valor para clave 'C3': " + lista.obtener("C3") + "\n");

        // Test f) Cambiar valor por clave
        Salida.salidaPorDefecto("\nCambiando valor para clave 'C3' a 'Valor3Modificado': ");
        Salida.salidaPorDefecto(lista.cambiar("C3", "Valor3Modificado") ? "Éxito\n" : "Fallo\n");
        lista.mostrar();

        // Test f) Cambiar valor viejo por nuevo
        Salida.salidaPorDefecto("Cambiando valor 'Valor2' por 'Valor2Cambiado': ");
        Salida.salidaPorDefecto(lista.cambiarValor("Valor2", "Valor2Cambiado") ? "Éxito\n" : "Fallo\n");
        lista.mostrar();

        // Test b) Quitar por clave
        Salida.salidaPorDefecto("\nQuitando elemento con clave 'C2': " + lista.quitar("C2") + "\n");
        lista.mostrar();

        // Test c) Quitar por contenido
        Salida.salidaPorDefecto("Quitando elemento con valor 'Valor3Modificado': " +
                lista.quitarContenido("Valor3Modificado") + "\n");
        lista.mostrar();

        // Test p) Agregar otra lista
        Salida.salidaPorDefecto("\nAgregando otra lista...\n");
        ListaDinClave lista2 = new ListaDinClave();
        lista2.poner("C5", "Valor5");
        lista2.poner("C6", "Valor6");
        lista.agregarLista(lista2);
        lista.mostrar();

        // Test r) Agregar desde arreglos
        Salida.salidaPorDefecto("\nAgregando desde arreglos...\n");
        Arreglo claves = new Arreglo(2);
        Arreglo valores = new Arreglo(2);
        claves.poner("C7");
        claves.poner("C8");
        valores.poner("Valor7");
        valores.poner("Valor8");
        lista.agregarArreglo(claves, valores);
        lista.mostrar();

        // Test s) Agregar desde listas dinámicas
        Salida.salidaPorDefecto("\nAgregando desde listas dinámicas...\n");
        ListaDin listaClaves = new ListaDin();
        ListaDin listaValores = new ListaDin();
        listaClaves.poner("C9");
        listaClaves.poner("C10");
        listaValores.poner("Valor9");
        listaValores.poner("Valor10");
        lista.agregarListasDinamicas(listaClaves, listaValores);
        lista.mostrar();

        // Test t) Agregar desde matriz 2D
        Salida.salidaPorDefecto("\nAgregando desde matriz 2D...\n");
        Arreglo2D matriz = new Arreglo2D(2, 2);
        matriz.cambiar(0, 0, "C11");
        matriz.cambiar(0, 1, "Valor11");
        matriz.cambiar(1, 0, "C12");
        matriz.cambiar(1, 1, "Valor12");
        lista.agregarMatriz2D(matriz);
        lista.mostrar();

        // Test j) Convertir a arreglos estáticos
        Salida.salidaPorDefecto("\nConvirtiendo a arreglos estáticos:\n");
        Arreglo arreglos = lista.aListasEstaticas();
        Salida.salidaPorDefecto("Claves: ");
        ((Arreglo) arreglos.obtener(0)).imprimir();
        Salida.salidaPorDefecto("Valores: ");
        ((Arreglo) arreglos.obtener(1)).imprimir();

        // Test k) Convertir a listas dinámicas
        Salida.salidaPorDefecto("\nConvirtiendo a listas dinámicas:\n");
        ListaDin listas = lista.aListasDinamicas();
        Salida.salidaPorDefecto("Claves: ");
        ((ListaDin) listas.obtener(0)).imprimir();
        Salida.salidaPorDefecto("Valores: ");
        ((ListaDin) listas.obtener(1)).imprimir();

        // Test l) Convertir a matriz 2D
        Salida.salidaPorDefecto("\nConvirtiendo a matriz 2D:\n");
        Arreglo2D matriz2 = lista.aMatriz2();
        matriz2.imprimirXRenglones();

        // Test m) Vaciar lista
        Salida.salidaPorDefecto("\nVaciando lista...\n");
        lista.vaciar();
        Salida.salidaPorDefecto("¿Lista vacía? " + lista.vacia() + "\n");
        lista.mostrar();
    }
}