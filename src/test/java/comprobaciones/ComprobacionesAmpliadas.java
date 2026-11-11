package comprobaciones;

import colecciones.edlineal.arreglos.*;
import colecciones.edlineal.colas.*;
import colecciones.edlineal.listas.*;
import colecciones.edlineal.pilas.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.nio.file.*;
import utils.clasificadores.knn.*;

/** Regresiones para los módulos completados después de la revisión inicial. */
public class ComprobacionesAmpliadas {
    private static int comprobaciones;

    public static void main(String[] args) throws Exception {
        lineales();
        arreglos();
        ordenados();
        knn();
        ComprobacionesAplicaciones.ejecutar();
        limitesAdicionales();
        System.out.println("Extended checks passed: " + comprobaciones);
    }

    private static void lineales() throws Exception {
        PilaDin pila = new PilaDin();
        igual(null, pila.quitar());
        for (int i = 0; i < 15; i++) pila.poner(i);
        String salida = capturar(pila::imprimir);
        igual(true, salida.startsWith("14->13->12->"));
        igual(true, salida.endsWith("1->0->null\n"));
        igual(14, pila.verTope());
        for (int i = 14; i >= 0; i--) igual(i, pila.quitar());
        igual(true, pila.vacio());
        pila.poner(42); igual(42, pila.quitar());
        pila.poner(1); pila.vaciar(); igual(null, pila.verTope());

        ListaDin lista = new ListaDin();
        for (int i = 0; i < 15; i++) lista.poner(i);
        igual(15, lista.aListaEstatica().cantidad());
        igual(15, lista.leerArreglo().length);
        igual(null, lista.obtener(-1));
        igual(false, lista.cambiar(-1, 99));
        igual(true, lista.modificar(0, 99, 1));
        igual(99, lista.obtener(0));
        igual(15, lista.aListaEstatica(new Arreglo(0)).cantidad());
        lista.vaciar(); lista.rellenar("x", 15);
        igual(15, lista.buscarValores("x").cantidad());
        igual(15, ((ListaDin) lista.arregloDesordenado()).cantidad());
        lista.vaciar(); lista.poner("a"); lista.poner("a"); lista.poner("b");
        ListaDin patron = new ListaDin(); patron.poner("A"); patron.poner("b");
        igual(true, lista.esSublista(patron));
        igual(true, lista.agregarLista(lista));
        igual(6, lista.cantidad());
        igual(0, lista.aMatriz2D(2, 0).obtenerColumnas());
        igual(null, lista.aMatriz2D(-1, 2));

        ColaFija cola = new ColaFija(2);
        igual(null, cola.verTope());
        cola.poner("uno"); cola.poner("dos");
        igual("uno", cola.verTope()); igual("uno", cola.quitar());
        cola.poner("tres"); cola.vaciar(); cola.vaciar();
        igual(true, cola.vacio()); igual(null, cola.verTope());
        igual(true, cola.poner("nuevo")); igual("nuevo", cola.quitar());
        ColaFija cero = new ColaFija(0);
        igual(true, cero.lleno()); igual(false, cero.poner("x"));
        cero.vaciar(); igual(null, cero.verTope());
        System.out.println("PASS completed linked stack, list helpers and circular queue");
    }

    private static void arreglos() throws Exception {
        Arreglo a = new Arreglo(5);
        igual(false, a.iteradorValido());
        igual(null, a.verInicio());
        igual(0, a.ponerInicio("b")); igual(0, a.ponerInicio("a")); a.poner("c");
        igual(true, a.cambiar(1, "B"));
        igual(true, a.cambiar("c", "C", 1));
        igual(false, a.cambiar(-1, "x"));
        a.iniciaIterador();
        for (String valor : new String[]{"a", "B", "C"}) {
            igual(true, a.iteradorValido()); igual(valor, a.obtenerIterador()); a.moverIterador();
        }
        igual(false, a.iteradorValido()); igual(null, a.obtenerIterador());
        igual("C\nB\na\n", capturar(a::imprimirDesc));
        igual("C\nB\na\n", capturar(a::mostrarOI));
        igual(1, a.encontrarLista("b").cantidad());
        Arreglo copia = a.aListaEstatica(); copia.modificar(0, "otra");
        igual("a", a.obtener(0));
        igual(2, a.aListaEstatica(arreglo("b")).cantidad());
        igual("C", a.aMatriz2D(2, 2).obtener(1, 0));
        igual(null, a.aMatriz2D(2, 2).obtener(1, 1));
        igual(null, a.aMatriz2D(-1, 1));
        igual(true, a.esSublista(arreglo("b", "a")));
        igual(false, a.esSublista(arreglo("d")));
        igual(true, a.modificarLista((colecciones.interfaces.ListaDatos) arreglo("a"), arreglo("A1")));
        igual("A1", a.obtener(0));
        igual(true, a.retenerLista(arreglo("A1", "C"))); igual(2, a.cantidad());
        igual(2, ((Arreglo) a.arregloDesordenado()).cantidad());
        a.rellenar("x", 99); igual(5, a.cantidad()); igual(5, a.contar("x"));
        igual(-1, a.ponerInicio("sin espacio")); a.vaciar(); igual(false, a.iteradorValido());
        a.rellenar("z"); igual(5, a.cantidad());
        Arreglo cero = new Arreglo(0); cero.rellenar("x");
        igual(0, cero.cantidad()); igual(-1, cero.ponerInicio("x"));
        igual(null, cero.suma()); igual(null, cero.promedio()); igual(null, cero.encontrarMayor());
        Arreglo negativos = arreglo(-3, -1, -2);
        igual(-1.0, negativos.encontrarMayor()); igual(-6.0, negativos.suma()); igual(-2.0, negativos.promedio());
        colecciones.ednolineal.Arreglo2D tabla = new colecciones.ednolineal.Arreglo2D(2, 2);
        tabla.cambiar(0, 0, "a"); tabla.cambiar(0, 1, "b"); tabla.cambiar(1, 0, "c"); tabla.cambiar(1, 1, "d");
        Arreglo filas = new Arreglo(4), columnas = new Arreglo(4);
        igual(true, filas.agregarMatriz2D(tabla, ListaDin.TipoTabla.RENGLON));
        igual(true, columnas.agregarMatriz2D(tabla, ListaDin.TipoTabla.COLUMNA));
        igual("b", filas.obtener(1)); igual("c", columnas.obtener(1));
        igual(false, new Arreglo(3).agregarMatriz2D(tabla, ListaDin.TipoTabla.RENGLON));
        ArregloNumerico numeros = new ArregloNumerico(3);
        numeros.ponerInicio(3); numeros.rellenar(2, 9);
        igual(3, numeros.cantidad()); igual(false, numeros.modificar(0, "texto"));
        igual(-1, numeros.ponerInicio("texto"));
        Arreglo nulos = arreglo(null, "A", null);
        igual(2, nulos.contar(null)); igual(0, nulos.buscar(null));
        System.out.println("PASS completed array interface methods and bounded fills");
    }

    private static void ordenados() {
        for (ArregloOrdenado.TipoOrdenamiento orden : ArregloOrdenado.TipoOrdenamiento.values()) {
            boolean asc = orden == ArregloOrdenado.TipoOrdenamiento.INC;
            ArregloOrdenado a = new ArregloOrdenado(5, orden);
            a.invertir(); a.invertir();
            for (int n : new int[]{3, 1, 5, 2, 4}) a.poner(n);
            for (int i = 0; i < 5; i++) igual(asc ? i + 1 : 5 - i, a.obtener(i));
            igual(-1, a.poner(3));
            igual(asc ? 2 : 4, a.buscar(2));
            igual(false, a.modificar(0, 3)); igual(5, a.cantidad());
            igual(true, a.modificar(0, 0)); igual(5, a.cantidad());
            comprobarOrden(a, asc);
            igual(true, a.poner(0, 8)); comprobarOrden(a, asc);
            igual(true, a.cambiar(0, 9)); comprobarOrden(a, asc);
            igual(false, a.agregarLista(arreglo(20))); igual(5, a.cantidad());
            a.quitar((Object) 3); igual(true, a.ponerInicio(7) >= 0); comprobarOrden(a, asc);
            a.invertir(); comprobarOrden(a, !asc);
            igual(true, (int) a.buscar(7) > 0);
            a.vaciar(); a.rellenar(9); igual(5, a.cantidad()); comprobarOrden(a, !asc);
            a.vaciar(); a.rellenar(-9); igual(5, a.cantidad()); comprobarOrden(a, !asc);
            a.vaciar(); a.rellenar('H'); igual(5, a.cantidad());
            a.vaciar(); a.rellenar("único", 9); igual(1, a.cantidad());
            a.vaciar(); a.poner(1); a.poner(2); a.poner(3);
            igual(false, a.modificarLista(arreglo(0), arreglo(a.obtener(1)))); igual(3, a.cantidad());
            igual(true, a.modificarLista(arreglo(0), arreglo(12))); comprobarOrden(a, !asc);
            igual(true, a.modificarLista((colecciones.interfaces.ListaDatos) arreglo(12), arreglo(15)));
            comprobarOrden(a, !asc);
            igual(false, a.substituir(arreglo(2, 2))); igual(3, a.cantidad());
            igual(true, a.substituir(arreglo(9, 3, 6))); comprobarOrden(a, !asc);
        }
        System.out.println("PASS ascending and descending ordered array mutations");
    }

    private static void comprobarOrden(Arreglo a, boolean asc) {
        for (int i = 1; i < a.cantidad(); i++) {
            double anterior = ((Number) a.obtener(i - 1)).doubleValue();
            double actual = ((Number) a.obtener(i)).doubleValue();
            igual(true, asc ? anterior < actual : anterior > actual);
        }
    }

    private static void knn() throws Exception {
        Path archivo = Files.createTempFile("knn-regresion-", ".txt");
        try {
            ConjuntoDatos conjunto = new ConjuntoDatos(10, 1);
            Files.writeString(archivo, "2,A\n10,B\n");
            igual(true, conjunto.leerDatos(archivo.toString()));
            igual(2, conjunto.obtenerCantidadDatos());
            Dato consulta = new Dato(1); consulta.establecerValor(0, 0);
            igual("A", new KNN(conjunto, 1).clasificar(consulta));
            igual(false, consulta.establecerValor(0, Double.NaN));
            rechaza(() -> new KNN(conjunto, 0)); rechaza(() -> new KNN(conjunto, 3));
            rechaza(() -> new KNN(conjunto, 1).clasificar(new Dato(2)));
            Files.writeString(archivo, "1,A\n-1,B\n-1,B\n");
            igual(true, conjunto.leerDatos(archivo.toString()));
            igual("B", new KNN(conjunto, 3).clasificar(consulta));
            igual("A", new KNN(conjunto, 1).clasificar(consulta));
            Files.writeString(archivo, "0,A\n1,C\n2,B\n3,B\n4,C\n");
            igual(true, conjunto.leerDatos(archivo.toString()));
            igual("C", new KNN(conjunto, 5).clasificar(consulta));
            Files.writeString(archivo, "NaN,A\n");
            igual(false, conjunto.leerDatos(archivo.toString())); igual(5, conjunto.obtenerCantidadDatos());
            Files.writeString(archivo, "1,\n"); igual(false, conjunto.leerDatos(archivo.toString()));
            Files.writeString(archivo, ""); igual(false, conjunto.leerDatos(archivo.toString()));
            ConjuntoDatos ponderado = new ConjuntoDatos(10, 2);
            Files.writeString(archivo, "0.2,10,A\n0.4,10,A\n1.2,10,B\n1.4,10,B\n");
            igual(true, ponderado.leerDatos(archivo.toString()));
            cerca(1, ((Number) ponderado.calcularPesos().obtener(0)).doubleValue(), 1e-12);
            cerca(0, ((Number) ponderado.calcularPesos().obtener(1)).doubleValue(), 1e-12);
            Files.writeString(archivo, "0,0,A\n0,0,A\n");
            igual(true, ponderado.leerDatos(archivo.toString()));
            cerca(0.5, ((Number) ponderado.calcularPesos().obtener(0)).doubleValue(), 1e-12);
            cerca(0.5, ((Number) ponderado.calcularPesos().obtener(1)).doubleValue(), 1e-12);
            igual(false, new ConjuntoDatos(1, 2).leerDatos(archivo.toString()));
            igual(false, ponderado.leerDatos(archivo + ".missing"));
        } finally { Files.deleteIfExists(archivo); }
        System.out.println("PASS numeric KNN distances, discretization, ties and input validation");
    }

    private static void limitesAdicionales() {
        ArregloOrdenado letras = new ArregloOrdenado(3, ArregloOrdenado.TipoOrdenamiento.DEC);
        letras.poner("b"); letras.poner("a"); igual(-1, letras.poner("A"));
        igual("b", letras.obtener(0)); igual(2, letras.buscar("A"));
        ArregloNumerico uno = new ArregloNumerico(2), dos = new ArregloNumerico(2), corto = new ArregloNumerico(1);
        uno.poner(0); uno.poner(2); dos.poner(0); dos.poner(4); corto.poner(1);
        igual(true, uno.esParalelo(dos)); igual(false, uno.esOrtogonal(corto));
        igual(false, uno.esParalelo(corto)); igual(false, uno.sumarEscalares(corto));
        ArregloNumerico cero = new ArregloNumerico(2); cero.poner(0); cero.poner(0);
        igual(false, uno.esParalelo(cero));
        ListaDin original = new ListaDin(), viejos = new ListaDin(), nuevos = new ListaDin();
        original.poner("x"); viejos.poner("x"); nuevos.poner("y"); nuevos.poner("z");
        igual(false, original.modificarLista(viejos, nuevos)); igual("x", original.verInicio());
        original.poner(null); ListaDin retener = new ListaDin(); retener.poner(null);
        igual(true, original.retenerLista(retener)); igual(1, original.cantidad());
        colecciones.edlineal.listas.listadinclave.ListaDinClave claves = new colecciones.edlineal.listas.listadinclave.ListaDinClave();
        igual(false, claves.poner(null, "x")); igual(false, claves.poner("x", null));
        igual(null, claves.buscar(null)); igual(false, claves.agregarListasDinamicas(viejos, nuevos));
        igual(0, claves.cantidad());
        System.out.println("PASS inherited mutation guards, textual ordering and vector dimensions");
    }

    static void rechaza(Runnable accion) {
        comprobaciones++;
        try { accion.run(); } catch (IllegalArgumentException e) { return; }
        throw new AssertionError("Se esperaba IllegalArgumentException");
    }

    static Arreglo arreglo(Object... valores) {
        Arreglo a = new Arreglo(valores.length);
        for (Object valor : valores) a.poner(valor);
        return a;
    }

    static void igual(Object esperado, Object actual) {
        comprobaciones++;
        if (!Objects.equals(esperado, actual))
            throw new AssertionError("Esperado: " + esperado + "; obtenido: " + actual);
    }

    static void cerca(double esperado, double actual, double tolerancia) {
        comprobaciones++;
        if (!Double.isFinite(actual) || Math.abs(esperado - actual) > tolerancia)
            throw new AssertionError("Esperado: " + esperado + "; obtenido: " + actual);
    }

    static String capturar(Runnable accion) throws Exception {
        PrintStream anterior = System.out;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (PrintStream salida = new PrintStream(bytes, true, StandardCharsets.UTF_8)) {
            System.setOut(salida); accion.run();
        } finally { System.setOut(anterior); }
        return bytes.toString(StandardCharsets.UTF_8).replace("\r\n", "\n");
    }
}
