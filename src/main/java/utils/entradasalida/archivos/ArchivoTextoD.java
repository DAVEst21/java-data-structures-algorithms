package utils.entradasalida.archivos;

import colecciones.edlineal.arreglos.Arreglo;
import java.io.*;

public class ArchivoTextoD {

    public static Arreglo leer(String archivo){
        if (archivo == null) return null;
        Arreglo datos;
        try (BufferedReader buffer = new BufferedReader(new FileReader(archivo, java.nio.charset.StandardCharsets.UTF_8))) {
            datos = new Arreglo((int) buffer.lines().count());
        } catch (IOException | UncheckedIOException e) { return null; }
        try (BufferedReader buffer = new BufferedReader(new FileReader(archivo, java.nio.charset.StandardCharsets.UTF_8))) {
            String cadena;
            while ((cadena = buffer.readLine()) != null) {
                if (datos.poner(cadena) < 0) throw new IllegalStateException("El archivo cambió mientras se leía");
            }
        } catch (IOException e) { return null; }
        return datos;
    }
    public static void escribir(Arreglo arreglo, String archivo){
        if (arreglo == null || archivo == null) throw new IllegalArgumentException("Arreglo y archivo son obligatorios");
        try (FileWriter output = new FileWriter(archivo, java.nio.charset.StandardCharsets.UTF_8)) {
            for (int posicion = 0; posicion < arreglo.cantidad(); posicion++) {
                if (posicion > 0) output.write("\n");
                output.write(String.valueOf(arreglo.obtener(posicion)));
            }
        } catch (IOException e) { throw new UncheckedIOException("No se pudo escribir el archivo", e); }
    }
}