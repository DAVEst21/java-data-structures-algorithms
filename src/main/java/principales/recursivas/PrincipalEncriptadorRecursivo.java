package principales.recursivas;

import colecciones.edlineal.arreglos.Arreglo;
import colecciones.edlineal.arreglos.ArregloNumerico;
import utils.entradasalida.archivos.ArchivoTextoD;
import utils.entradasalida.cadenas.Salida;

import static utils.encriptadores.EncriptadorRecursivo.*;

public class PrincipalEncriptadorRecursivo {
    public static void main(String[] args) {
        // Leer el archivo de entrada
        Arreglo lineas = ArchivoTextoD.leer("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\ed_4b_25\\src\\main\\java\\resources\\encriptacion\\entrada.txt");
        if (lineas == null || lineas.vacia()) {
            Salida.salidaPorDefecto("Error al leer el archivo o archivo vacío.\n");
            return;
        }

        // Posiciones y tamaños de ejemplo
        ArregloNumerico posiciones = new ArregloNumerico(3);
        posiciones.poner(3);
        posiciones.poner(10);
        posiciones.poner(16);
        ArregloNumerico tamanios = new ArregloNumerico(3);
        tamanios.poner(2); // para "os"
        tamanios.poner(4); // para "rofn"
        tamanios.poner(3); // para "cit"

        // Encriptar cada línea
        Arreglo lineasEncriptadas = new Arreglo(lineas.cantidad());
        for (int i = 0; i < lineas.cantidad(); i++) {
            String linea = (String) lineas.obtener(i);
            String encriptada = encriptar(linea, posiciones, tamanios);
            if (encriptada != null) {
                lineasEncriptadas.poner(encriptada);
                Salida.salidaPorDefecto("Línea encriptada: " + encriptada + "\n");
            } else {
                Salida.salidaPorDefecto("Error al encriptar la línea: " + linea + "\n");
                lineasEncriptadas.poner(linea); // Almacena la original si falla
            }
        }
        ArchivoTextoD.escribir(lineasEncriptadas, "C:\\Users\\Usuario\\Documents\\NetBeansProjects\\ed_4b_25\\src\\main\\java\\resources\\encriptacion\\encriptado.txt");

        // Desencriptar el archivo encriptado
        Arreglo lineasEncriptadasLeidas = ArchivoTextoD.leer("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\ed_4b_25\\src\\main\\java\\resources\\encriptacion\\encriptado.txt");
        Arreglo lineasDesencriptadas = new Arreglo(lineasEncriptadasLeidas.cantidad());
        for (int i = 0; i < lineasEncriptadasLeidas.cantidad(); i++) {
            String linea = (String) lineasEncriptadasLeidas.obtener(i);
            String desencriptada = desencriptar(linea);
            lineasDesencriptadas.poner(desencriptada);
            Salida.salidaPorDefecto("Línea desencriptada: " + desencriptada + "\n");
        }
        ArchivoTextoD.escribir(lineasDesencriptadas, "C:\\Users\\Usuario\\Documents\\NetBeansProjects\\ed_4b_25\\src\\main\\java\\resources\\encriptacion\\desencriptado.txt");
    }
}