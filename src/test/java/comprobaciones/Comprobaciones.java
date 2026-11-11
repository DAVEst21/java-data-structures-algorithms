package comprobaciones;

import colecciones.edlineal.arreglos.Arreglo;
import colecciones.edlineal.arreglos.ArregloNumerico;
import colecciones.edlineal.arreglos.ArregloOrdenado;
import colecciones.edlineal.colas.ColaDin;
import colecciones.edlineal.colas.ColaFija;
import colecciones.edlineal.colas.colapriori.ColaPrioridad;
import colecciones.edlineal.listas.ListaDin;
import colecciones.edlineal.listas.listadinclave.ListaDinClave;
import colecciones.edlineal.pilas.PilaFija;
import colecciones.ednolineal.Arreglo2D;
import colecciones.ednolineal.Arreglo2DNumerico;
import colecciones.ednolineal.Arreglo3D;
import registros.calculadora.Calculadora;
import registros.casas.CalculadorPearson;
import utils.conversores.ConvertidorBinario;
import utils.conversores.ConvertidorHexadecimal;
import utils.encriptadores.EncriptadorRecursivo;
import utils.imagenes.ProcesadorImagen;
import utils.ordenamiento.MergeSort;
import utils.regresion.RegresionLineal;
import utils.validadores.ValidadorBinario;

import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import javax.imageio.ImageIO;

/** Comprobaciones pequeñas, con entradas conocidas y sin frameworks. */
public class Comprobaciones {
    private static int grupos;
    private static int fallos;

    public static void main(String[] args) throws Exception {
        probar("Arreglo: operaciones, duplicados y límites", Comprobaciones::arreglo);
        probar("Arreglo: inversión vacía y de un elemento", Comprobaciones::inversionVacia);
        probar("ArregloOrdenado: orden creciente sin duplicados", Comprobaciones::ordenado);
        probar("PilaFija: LIFO, capacidad y vaciado", Comprobaciones::pila);
        probar("ColaFija: FIFO y vuelta circular", Comprobaciones::colaCircular);
        probar("ColaDin: FIFO con duplicados", Comprobaciones::colaDinamica);
        probar("ColaPrioridad: ambos órdenes y empates", Comprobaciones::prioridad);
        probar("ListaDin y ListaDinClave: operaciones básicas", Comprobaciones::listas);
        probar("Matrices: límites, transpuesta y producto", Comprobaciones::matrices);
        probar("Merge Sort: resultados conocidos", Comprobaciones::ordenamiento);
        probar("Recursión: bases numéricas y texto del reporte", Comprobaciones::recursion);
        probar("Calculadora: precedencia y ejemplo del reporte", Comprobaciones::calculadora);
        probar("Pearson y regresión: datos sintéticos conocidos", Comprobaciones::estadistica);
        probar("Imagen: componentes, grises y rotación", Comprobaciones::imagen);
        System.out.println("Groups passed: " + (grupos - fallos) + "/" + grupos);
        if (fallos != 0) throw new AssertionError("Failed groups: " + fallos);
    }

    private static void probar(String nombre, Prueba prueba) throws Exception {
        grupos++;
        try {
            prueba.ejecutar();
            System.out.println("PASS " + nombre);
        } catch (AssertionError | RuntimeException e) {
            fallos++;
            System.out.println("FAIL " + nombre + ": " + e);
        }
    }

    private static void arreglo() {
        Arreglo a = new Arreglo(3);
        igual(true, a.vacia());
        igual(null, a.quitar());
        igual(null, a.obtener(0));
        igual(-1, a.buscar("x"));
        igual(0, a.poner("A"));
        igual(1, a.poner("a"));
        igual(2, a.poner("B"));
        igual(true, a.lleno());
        igual(-1, a.poner("C"));
        igual(2, a.contar("a"));
        igual(0, a.buscar("a"));
        igual(2, a.buscarValores("a").cantidad());
        igual(null, a.obtener(-1));
        igual(null, a.obtener(3));
        igual(false, a.modificar(3, "C"));
        igual(null, a.quitar(-1));
        igual("A", a.quitar((Object) "a"));
        igual(2, a.cantidad());
        igual("a", a.obtener(0));
        Arreglo copia = (Arreglo) a.clonar();
        copia.modificar(0, "otro");
        igual("a", a.obtener(0));
        igual(2, ((Arreglo) a.subLista(0, 1)).cantidad());
        igual(null, a.subLista(1, 0));
        a.invertir();
        igual("B", a.obtener(0));
        igual(true, a.redimensionar(1));
        igual(1, a.cantidad());
        igual("B", a.quitar());
        igual(true, a.vacia());
        igual(false, a.redimensionar(-1));
        a.poner("X");
        a.vaciar();
        igual(0, a.cantidad());
        Arreglo cero = new Arreglo(0);
        igual(true, cero.vacia());
        igual(true, cero.lleno());
        igual(-1, cero.poner("x"));
    }

    private static void inversionVacia() {
        for (int capacidad : new int[]{0, 1, 3}) {
            Arreglo a = new Arreglo(capacidad);
            a.invertir();
            igual(0, a.cantidad());
        }
        Arreglo uno = new Arreglo(1);
        uno.poner("único");
        uno.invertir();
        igual("único", uno.obtener(0));
    }

    private static void ordenado() {
        ArregloOrdenado a = new ArregloOrdenado(3, ArregloOrdenado.TipoOrdenamiento.INC);
        igual(-1, a.buscar(2));
        a.poner(3); a.poner(1);
        igual(-1, a.poner(1));
        a.poner(2);
        for (int i = 0; i < 3; i++) igual(i + 1, a.obtener(i));
        igual(2, a.buscar(2)); // buscar usa posiciones de base uno en esta clase.
        igual(-4, a.buscar(4));
        igual(2, a.quitar((Object) 2));
        igual(null, a.quitar((Object) 9));
        igual(2, a.cantidad());
    }

    private static void pila() {
        PilaFija p = new PilaFija(2);
        igual(null, p.verTope());
        igual(null, p.quitar());
        igual(true, p.poner(5.0));
        igual(true, p.poner(10.0));
        igual(false, p.poner(15.0));
        igual(10.0, p.verTope());
        igual(10.0, p.quitar());
        igual(5.0, p.quitar());
        igual(true, p.vacio());
        p.poner(1); p.vaciar(); p.vaciar();
        igual(null, p.verTope());
        igual(false, new PilaFija(0).poner(1));
    }

    private static void colaCircular() {
        ColaFija c = new ColaFija(3);
        igual(true, c.vacio());
        igual(null, c.quitar());
        c.poner("A"); c.poner("B"); c.poner("B");
        igual(true, c.lleno());
        igual(false, c.poner("X"));
        igual("A", c.quitar());
        igual(true, c.poner("C")); // Reutiliza la primera celda.
        igual("B", c.quitar());
        igual("B", c.quitar());
        igual("C", c.quitar());
        igual(null, c.quitar());
        igual(true, c.vacio());
        ColaFija uno = new ColaFija(1);
        igual(true, uno.poner(7));
        igual(false, uno.poner(8));
        igual(7, uno.quitar());
        igual(true, uno.poner(8));
        igual(8, uno.quitar());
    }

    private static void colaDinamica() {
        ColaDin c = new ColaDin();
        igual(null, c.verTope());
        igual(null, c.quitar());
        c.poner("uno"); c.poner("dos"); c.poner("uno");
        igual("uno", c.verTope());
        igual("uno", c.quitar());
        igual("dos", c.quitar());
        igual("uno", c.quitar()); // Debe conservar el duplicado pendiente.
        igual(true, c.vacio());
        igual(null, c.quitar());
        c.poner(1); c.poner(1);
        igual(1, c.quitar());
        igual(1, c.verTope());
        c.vaciar(); c.vaciar();
        igual(null, c.verTope());
        igual(false, c.lleno());
    }

    private static void prioridad() {
        for (boolean mayor : new boolean[]{true, false}) {
            ColaPrioridad c = new ColaPrioridad(3, mayor);
            igual(null, c.quitar());
            igual(null, c.verPrioridadTope());
            igual(false, c.poner("sin prioridad"));
            c.agregar("A", 5); c.agregar("B", 5); c.agregar("C", 1);
            igual(false, c.agregar("D", 8));
            igual(mayor ? "A" : "C", c.verTope());
            String[] orden = mayor ? new String[]{"A", "B", "C"} : new String[]{"C", "A", "B"};
            for (String s : orden) igual(s, c.quitar());
            igual(true, c.vacio());
            c.agregar("X", 1); c.vaciar();
            igual(null, c.verTope());
        }
    }

    private static void listas() {
        ListaDin l = new ListaDin();
        igual(null, l.verInicio());
        igual(null, l.quitar(0));
        l.poner("dos"); l.ponerInicio("uno"); l.poner("uno");
        igual(2, l.contar("UNO"));
        igual("uno", l.buscar("UNO"));
        igual(false, l.validaPosicion(-1));
        igual(null, l.obtener(3));
        igual(null, l.quitar(-1));
        ListaDin copia = (ListaDin) l.clonar();
        copia.cambiar(0, "otro");
        igual("uno", l.verInicio());
        l.invertir();
        l.iniciaIterador();
        for (String s : new String[]{"uno", "dos", "uno"}) {
            igual(true, l.iteradorValido());
            igual(s, l.obtenerIterador()); l.moverIterador();
        }
        igual(false, l.iteradorValido());
        igual("uno", l.quitar(2));
        igual("dos", l.verFinal());
        l.quitar((Object) "uno");
        igual("dos", l.verInicio());
        l.vaciar();
        igual(true, l.vacia());
        ListaDinClave claves = new ListaDinClave();
        igual(null, claves.buscar("A"));
        claves.poner("A", 1); claves.poner("a", 2); claves.poner("B", 2);
        igual(2, claves.cantidad());
        igual(2, claves.buscar("A"));
        igual(2, claves.quitar("a"));
        igual(null, claves.quitar("a"));
        igual(2, claves.buscar("B"));
        claves.vaciar();
        igual(true, claves.vacia());
    }

    private static void matrices() {
        Arreglo2D a = new Arreglo2D(2, 3, 0);
        igual(false, a.cambiar(-1, 0, 9));
        igual(null, a.obtener(2, 0));
        igual(true, a.cambiar(1, 2, 7));
        a.transpuesta();
        igual(3, a.obtenerRenglones()); igual(2, a.obtenerColumnas());
        igual(7, a.obtener(2, 1));
        Arreglo2DNumerico n = new Arreglo2DNumerico(2, 2);
        n.cambiar(0, 0, 1); n.cambiar(0, 1, 2);
        n.cambiar(1, 0, 3); n.cambiar(1, 1, 4);
        igual(false, n.cambiar(0, 0, "texto"));
        igual(true, n.multiplicar(n.clonar()));
        igual(7.0, n.obtener(0, 0)); igual(10.0, n.obtener(0, 1));
        igual(15.0, n.obtener(1, 0)); igual(22.0, n.obtener(1, 1));
        igual(false, n.multiplicar(new Arreglo2DNumerico(3, 1)));
        igual(false, new Arreglo2DNumerico(0, 0).porEscalar(2));
        Arreglo3D cubo = new Arreglo3D(1, 1, 1);
        igual(true, cubo.cambiarCelda(0, 0, 0, "x"));
        igual("x", cubo.obtenerCelda(0, 0, 0));
        igual(false, cubo.cambiarCelda(1, 0, 0, "x"));
        igual(null, cubo.obtenerCelda(0, 0, -1));
    }

    private static void ordenamiento() {
        int[][] entradas = {{}, {7}, {3, 2, 1}, {1, 2, 3}, {2, 2, 2}, {5, 2, 4, 6, 1, 3, 2, 6}, {-1, 0, -3}};
        int[][] esperadas = {{}, {7}, {1, 2, 3}, {1, 2, 3}, {2, 2, 2}, {1, 2, 2, 3, 4, 5, 6, 6}, {-3, -1, 0}};
        for (int j = 0; j < entradas.length; j++) {
            Arreglo a = new Arreglo(entradas[j].length);
            for (int x : entradas[j]) a.poner(x);
            MergeSort.mergeSort(a);
            igual(esperadas[j].length, a.cantidad());
            for (int i = 0; i < a.cantidad(); i++) cerca(esperadas[j][i], ((Number) a.obtener(i)).doubleValue(), 0);
        }
    }

    private static void recursion() {
        igual("10010110", ConvertidorBinario.decimalABinario(150));
        igual("0", ConvertidorBinario.decimalABinario(0));
        igual("111", ConvertidorBinario.decimalABinario(7));
        igual("11111111", ConvertidorBinario.decimalABinario(255));
        igual("10000000000", ConvertidorBinario.decimalABinario(1024));
        igual("1".repeat(63), ConvertidorBinario.decimalABinario(Long.MAX_VALUE));
        igual(null, ConvertidorBinario.decimalABinario(-1));
        igual("FE05", ConvertidorHexadecimal.decimalAHex(65029));
        igual("0", ConvertidorHexadecimal.decimalAHex(0));
        igual("FF", ConvertidorHexadecimal.decimalAHex(255));
        igual("10", ConvertidorHexadecimal.decimalAHex(16));
        igual("3E8", ConvertidorHexadecimal.decimalAHex(1000));
        igual("7FFFFFFFFFFFFFFF", ConvertidorHexadecimal.decimalAHex(Long.MAX_VALUE));
        igual(null, ConvertidorHexadecimal.decimalAHex(-1));
        for (long x : new long[]{0, 1, 100, 101001, 1111}) igual(true, ValidadorBinario.esBinario(x));
        for (long x : new long[]{-1, 2, 123001, 10201}) igual(false, ValidadorBinario.esBinario(x));
        ArregloNumerico posiciones = numeros(16, 3, 10);
        ArregloNumerico tamanios = numeros(3, 2, 4);
        String texto = "Curso de Informática";
        String cifrado = EncriptadorRecursivo.encriptar(texto, posiciones, tamanios);
        igual("Cur(os) de I(rofn)má(cit)a", cifrado);
        igual(texto, EncriptadorRecursivo.desencriptar(cifrado));
        igual(null, EncriptadorRecursivo.encriptar(texto, numeros(1, 2), numeros(3, 2)));
        igual(null, EncriptadorRecursivo.encriptar(texto, numeros(-1), numeros(1)));
        igual(null, EncriptadorRecursivo.encriptar(texto, numeros(20), numeros(1)));
        igual(null, EncriptadorRecursivo.encriptar(texto, numeros(1), numeros(0)));
        igual(null, EncriptadorRecursivo.encriptar(texto, numeros(1), numeros(1, 2)));
        igual("", EncriptadorRecursivo.encriptar("", numeros(), numeros()));
        igual("", EncriptadorRecursivo.desencriptar(""));
    }

    private static void calculadora() {
        CalculadoraPrueba c = new CalculadoraPrueba();
        c.expresion("(1-2.0)*3/2^(12.0-8)");
        igual("1 2.0 - 3 * 2 12.0 8 - ^ /", c.convertirPostfija());
        cerca(-0.1875, c.evaluarExpresion(), 0);
        c.expresion("10+2*3"); c.convertirPostfija();
        cerca(16, c.evaluarExpresion(), 0);
    }

    private static void estadistica() {
        ListaDin x = new ListaDin(), y = new ListaDin();
        CalculadorPearson p = new CalculadorPearson();
        igual(0.0, p.calcular(x, y)); // Convención de la implementación para vacío.
        for (double v : new double[]{1, 2, 3}) { x.poner(v); y.poner(2 * v + 1); }
        cerca(1, p.calcular(x, y), 1e-12);
        RegresionLineal r = new RegresionLineal();
        r.entrenar(numeros(1, 2, 3), numeros(3, 5, 7));
        cerca(1, r.obtenerInterseccion(), 1e-6);
        cerca(2, r.obtenerPendiente(), 1e-6);
        cerca(9, r.predecir(4), 1e-6);
    }

    private static void imagen() throws Exception {
        int pixel = ProcesadorImagen.construirPixel(255, 30, 60, 90);
        int[] canales = ProcesadorImagen.extraerComponentes(pixel);
        igual(255, canales[0]); igual(30, canales[1]); igual(60, canales[2]); igual(90, canales[3]);
        Path carpeta = Files.createTempDirectory("comprobacion-imagen-");
        Path entrada = carpeta.resolve("entrada.png"), salida = carpeta.resolve("salida.jpg");
        try {
            BufferedImage original = new BufferedImage(8, 4, BufferedImage.TYPE_INT_RGB);
            for (int y = 0; y < 4; y++) for (int x = 0; x < 8; x++) original.setRGB(x, y, pixel);
            ImageIO.write(original, "png", entrada.toFile());
            ProcesadorImagen p = new ProcesadorImagen(entrada.toString());
            p.convertirAGrises(); p.rotar90Grados(); p.guardarImagen(salida.toString());
            BufferedImage resultado = ImageIO.read(salida.toFile());
            igual(4, resultado.getWidth()); igual(8, resultado.getHeight());
            int[] gris = ProcesadorImagen.extraerComponentes(resultado.getRGB(0, 0));
            for (int i = 1; i <= 3; i++) cerca(60, gris[i], 2); // JPG puede redondear.
        } finally {
            Files.deleteIfExists(salida); Files.deleteIfExists(entrada); Files.deleteIfExists(carpeta);
        }
    }

    private static ArregloNumerico numeros(int... valores) {
        ArregloNumerico a = new ArregloNumerico(valores.length);
        for (int n : valores) a.poner(n);
        return a;
    }

    private static void igual(Object esperado, Object actual) {
        if (!Objects.equals(esperado, actual)) throw new AssertionError("Esperado: " + esperado + "; obtenido: " + actual);
    }

    private static void cerca(double esperado, double actual, double tolerancia) {
        if (!Double.isFinite(actual) || Math.abs(esperado - actual) > tolerancia)
            throw new AssertionError("Esperado: " + esperado + "; obtenido: " + actual);
    }

    @FunctionalInterface
    private interface Prueba { void ejecutar() throws Exception; }

    private static class CalculadoraPrueba extends Calculadora {
        CalculadoraPrueba() { super(100); }
        void expresion(String texto) { expresionInfija = texto; }
    }
}
