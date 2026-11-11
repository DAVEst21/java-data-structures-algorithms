package comprobaciones;

import colecciones.edlineal.arreglos.*;
import colecciones.edlineal.colas.*;
import colecciones.edlineal.listas.*;
import colecciones.edlineal.pilas.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/** Regresiones para los módulos completados después de la revisión inicial. */
public class ComprobacionesAmpliadas {
    private static int comprobaciones;

    public static void main(String[] args) throws Exception {
        lineales();
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
