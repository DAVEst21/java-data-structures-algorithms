package principales.utiles.imagenes;

import utils.entradasalida.cadenas.Entrada;
import utils.entradasalida.cadenas.Salida;
import utils.imagenes.ProcesadorImagen;

import java.io.IOException;

public class PrincipalImagen {
    public static void main(String[] args) {
        try {
            // Cargar imagen
            ProcesadorImagen procesador = new ProcesadorImagen("C:/Users/Usuario/Documents/NetBeansProjects/ed_4b_25/cristiano-ronaldo-silencio.jpg");

            Salida.salidaPorDefecto("Seleccione la operación a realizar:\n");
            Salida.salidaPorDefecto("1. Convertir a escala de grises\n");
            Salida.salidaPorDefecto("2. Ajustar brillo\n");
            Salida.salidaPorDefecto("3. Invertir horizontal\n");
            Salida.salidaPorDefecto("4. Invertir vertical\n");
            Salida.salidaPorDefecto("5. Rotar 90 grados\n");
            Salida.salidaPorDefecto("6. Rotar 180 grados\n");
            Salida.salidaPorDefecto("7. Rotar 270 grados\n");
            Salida.salidaPorDefecto("8. Aplicar transpuesta\n");
            Salida.salidaPorDefecto("9. Redimensionar la imagen\n");
            Salida.salidaPorDefecto("11. Agregar marco rojo\n");
            Salida.salidaPorDefecto("12. Aplicar efecto negativo\n");

            int opcion = Entrada.terminalDouble().intValue();
            switch (opcion) {
                case 1 -> {
                    procesador.convertirAGrises();
                    procesador.guardarImagen("src/grises.jpg");
                    Salida.salidaPorDefecto("Imagen convertida a escala de grises.\n");
                }
                case 2 -> {
                    procesador.ajustarBrillo(30);
                    procesador.guardarImagen("src/brillo.jpg");
                    Salida.salidaPorDefecto("Brillo ajustado.\n");
                }
                case 3 -> {
                    procesador.invertirHorizontal();
                    procesador.guardarImagen("src/horizontal.jpg");
                    Salida.salidaPorDefecto("Inversión horizontal realizada.\n");
                }
                case 4 -> {
                    procesador.invertirVertical();
                    procesador.guardarImagen("src/vertical.jpg");
                    Salida.salidaPorDefecto("Inversión vertical realizada.\n");
                }
                case 5 -> {
                    procesador.rotar90Grados();
                    procesador.guardarImagen("src/rot90.jpg");
                    Salida.salidaPorDefecto("Imagen rotada 90 grados.\n");
                }
                case 6 -> {
                    procesador.rotar180Grados();
                    procesador.guardarImagen("src/rot180.jpg");
                    Salida.salidaPorDefecto("Imagen rotada 180 grados.\n");
                }
                case 7 -> {
                    procesador.rotar270Grados();
                    procesador.guardarImagen("src/rot270.jpg");
                    Salida.salidaPorDefecto("Imagen rotada 270 grados.\n");
                }
                case 8 -> {
                    procesador.aplicarTranspuesta();
                    procesador.guardarImagen("src/transpuesta.jpg");
                    Salida.salidaPorDefecto("Transpuesta aplicada.\n");
                }
                case 9 -> {
                    // Usando enumerados
                    procesador.redimensionar(ProcesadorImagen.FactorEscala.DOBLE); // Al doble de tamaño
                    procesador.guardarImagen("src/redDOBLE.jpg");
                    procesador.redimensionar(ProcesadorImagen.FactorEscala.MITAD); // A la mitad
                    procesador.guardarImagen("src/redMITAD.jpg");
                    // Usando factor numérico
                    procesador.redimensionar(1.5); // Aumentar 50%
                    procesador.guardarImagen("src/red50porciento.jpg");
                    // Usando dimensiones específicas
                    procesador.redimensionar(800, 600); // Redimensionar a 800x600 píxeles
                    procesador.guardarImagen("src/red800x600.jpg");
                    Salida.salidaPorDefecto("Imagen redimensionada.\n");
                }
                case 10 -> {
                    procesador.agregarMarco(20, 0xFFFF0000);
                    procesador.guardarImagen("src/marco.jpg");
                    Salida.salidaPorDefecto("Marco rojo agregado.\n");
                }
                case 11 -> {
                    procesador.aplicarNegativo();
                    procesador.guardarImagen("src/negativo.jpg");
                    Salida.salidaPorDefecto("Efecto negativo aplicado.\n");
                }
                default -> Salida.salidaPorDefecto("Opción no válida.\n");
            }

        } catch (IOException e) {
            Salida.salidaPorDefecto("Error al procesar la imagen: " + e.getMessage());
        }
    }
}