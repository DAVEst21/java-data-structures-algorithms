package comprobaciones;

import static comprobaciones.ComprobacionesAmpliadas.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import colecciones.edlineal.arreglos.*;
import registros.calculadora.Calculadora;
import utils.entradasalida.archivos.ArchivoTextoD;
import utils.entradasalida.cadenas.Entrada;
import utils.matematicas.TratamientoExpresiones;

class ComprobacionesAplicaciones {
    static void ejecutar() throws Exception {
        entradaSalida();
        calculadora();
    }

    private static void entradaSalida() throws Exception {
        Path archivo = Files.createTempFile("texto-regresion-", ".txt");
        try {
            ArchivoTextoD.escribir(arreglo("línea uno", "dos"), archivo.toString());
            Arreglo leido = ArchivoTextoD.leer(archivo.toString());
            igual(2, leido.cantidad()); igual("línea uno", leido.obtener(0));
            ArchivoTextoD.escribir(new Arreglo(0), archivo.toString());
            igual(0L, Files.size(archivo)); igual(0, ArchivoTextoD.leer(archivo.toString()).cantidad());
            igual(null, ArchivoTextoD.leer(archivo + ".missing"));
        } finally { Files.deleteIfExists(archivo); }
        InputStream anterior = System.in;
        try {
            System.setIn(new ByteArrayInputStream("texto\n42.5\núltima\n".getBytes(StandardCharsets.UTF_8)));
            igual("texto", Entrada.terminalCadenas()); igual(42.5, Entrada.terminalDouble());
            igual("última", Entrada.terminalCadenas()); igual(null, Entrada.terminalCadenas());
            rechaza(Entrada::terminalDouble);
            System.setIn(new ByteArrayInputStream("incorrecto\n".getBytes(StandardCharsets.UTF_8)));
            rechaza(Entrada::terminalDouble);
        } finally { System.setIn(anterior); }
        System.out.println("PASS UTF-8 files, empty files and consecutive terminal prompts");
    }

    private static void calculadora() {
        igual("12 3 +", TratamientoExpresiones.convertirInfijaPostfija("12+3"));
        igual(15.0, TratamientoExpresiones.evaluarInfija("12+3"));
        igual(15.0, TratamientoExpresiones.evaluarPostfija("12 3 +"));
        igual(-2.0, TratamientoExpresiones.evaluarPostfija("2242/*+23^-"));
        igual(512.0, TratamientoExpresiones.evaluarInfija("2^3^2"));
        for (String invalida : new String[]{"", "1+", "(1+2", "1+2)", "1 2", "2(3)", "-1", "1@2", "1/0", "()"})
            igual(null, TratamientoExpresiones.evaluarInfija(invalida));
        for (String invalida : new String[]{"", "+", "1+", "12", "10 0 /", "1 2 #"})
            igual(null, TratamientoExpresiones.evaluarPostfija(invalida));
        rechaza(() -> new Calculadora(1).convertirPostfija("((1))"));
        Calculadora c = new Calculadora(10);
        c.convertirPostfija("2+3"); igual(5.0, c.evaluarExpresion());
        c.convertirPostfija("4*5"); igual(20.0, c.evaluarExpresion());
        System.out.println("PASS completed infix converter, calculator errors and exponent associativity");
    }
}
