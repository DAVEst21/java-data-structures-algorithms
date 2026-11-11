/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils.entradasalida.cadenas;

import java.io.*;

/**
 *
 * @author Usuario
 */
public class Entrada {
    private static InputStream entradaActual;
    private static BufferedReader buffer;
    public static String terminalCadenas(){
        if (entradaActual != System.in) {
            entradaActual = System.in;
            buffer = new BufferedReader(new InputStreamReader(entradaActual, java.nio.charset.StandardCharsets.UTF_8));
        }
        try { return buffer.readLine(); }
        catch (IOException e) { throw new UncheckedIOException("No se pudo leer la entrada", e); }
    }

    public static Double terminalDouble(){
        String cadenaEntrada = terminalCadenas();
        if (cadenaEntrada == null) throw new IllegalArgumentException("No hay un número en la entrada");
        double entrada = Double.parseDouble(cadenaEntrada);
        if (!Double.isFinite(entrada)) throw new IllegalArgumentException("El número debe ser finito");
        return entrada;
    }
}
