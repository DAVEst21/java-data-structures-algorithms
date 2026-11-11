package utils.imagenes;

import colecciones.ednolineal.Arreglo2D;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ProcesadorImagen {
    private Arreglo2D matrizPixeles;
    private int ancho;
    private int alto;

    // Enumerado para los factores de redimensionamiento
    public enum FactorEscala {
        MITAD(0.5),
        TERCIO(1.0 / 3),
        CUARTO(0.25),
        DOBLE(2.0),
        TRIPLE(3.0),
        CUADRUPLE(4.0);

        private final double valor;

        FactorEscala(double valor) {
            this.valor = valor;
        }

        public double getValor() {
            return this.valor;
        }
    }

    public ProcesadorImagen(String rutaImagen) throws IOException {
        BufferedImage imagen = ImageIO.read(new File(rutaImagen));
        if (imagen == null) throw new IOException("Formato de imagen no reconocido");
        this.ancho = imagen.getWidth();
        this.alto = imagen.getHeight();
        this.matrizPixeles = new Arreglo2D(alto, ancho);

        // Almacenar todos los píxeles en el TDA
        for (int fila = 0; fila < alto; fila++) {
            for (int columna = 0; columna < ancho; columna++) {
                matrizPixeles.cambiar(fila, columna, imagen.getRGB(columna, fila));
            }
        }
    }

    // Método para extraer los componentes de color de un píxel
    public static int[] extraerComponentes(int pixel) {
        int[] componentes = new int[4];
        // Alfa (8 bits más significativos)
        componentes[0] = (pixel >> 24) & 0xFF;
        // Rojo
        componentes[1] = (pixel >> 16) & 0xFF;
        // Verde
        componentes[2] = (pixel >> 8) & 0xFF;
        // Azul (8 bits menos significativos)
        componentes[3] = pixel & 0xFF;
        return componentes;
    }

    // Método para reconstruir un píxel a partir de sus componentes
    public static int construirPixel(int alfa, int rojo, int verde, int azul) {
        return (alfa << 24) | (rojo << 16) | (verde << 8) | azul;
    }

    // Convertir imagen a escala de grises
    public void convertirAGrises() {
        for (int fila = 0; fila < alto; fila++) {
            for (int columna = 0; columna < ancho; columna++) {
                int pixel = (int) matrizPixeles.obtener(fila, columna);
                int[] componentes = extraerComponentes(pixel);

                // Calcular promedio para escala de grises
                int promedio = (componentes[1] + componentes[2] + componentes[3]) / 3;

                // Reconstruir píxel con el mismo valor para R, G y B
                int nuevoPixel = construirPixel(componentes[0], promedio, promedio, promedio);
                matrizPixeles.cambiar(fila, columna, nuevoPixel);
            }
        }
    }

    // Ajustar brillo de la imagen
    public void ajustarBrillo(int valor) {
        for (int fila = 0; fila < alto; fila++) {
            for (int columna = 0; columna < ancho; columna++) {
                int pixel = (int) matrizPixeles.obtener(fila, columna);
                int[] componentes = extraerComponentes(pixel);

                // Ajustar cada componente de color (excepto alfa)
                for (int componente = 1; componente < 4; componente++) {
                    componentes[componente] = (int) Math.max(0L, Math.min(255L, (long) componentes[componente] + valor));
                }

                int nuevoPixel = construirPixel(componentes[0], componentes[1],
                        componentes[2], componentes[3]);
                matrizPixeles.cambiar(fila, columna, nuevoPixel);
            }
        }
    }

    // Invertir imagen horizontalmente
    public void invertirHorizontal() {
        for (int fila = 0; fila < alto; fila++) {
            for (int columna = 0; columna < ancho / 2; columna++) {
                // Intercambiar píxeles simétricos
                Object temp = matrizPixeles.obtener(fila, columna);
                matrizPixeles.cambiar(fila, columna, matrizPixeles.obtener(fila, ancho - 1 - columna));
                matrizPixeles.cambiar(fila, ancho - 1 - columna, temp);
            }
        }
    }

    // Invertir imagen verticalmente
    public void invertirVertical() {
        for (int fila = 0; fila < alto / 2; fila++) {
            for (int columna = 0; columna < ancho; columna++) {
                // Intercambiar píxeles simétricos
                Object temp = matrizPixeles.obtener(fila, columna);
                matrizPixeles.cambiar(fila, columna, matrizPixeles.obtener(alto - 1 - fila, columna));
                matrizPixeles.cambiar(alto - 1 - fila, columna, temp);
            }
        }
    }

    // Rotar imagen 90 grados
    public void rotar90Grados() {
        Arreglo2D nuevaMatriz = new Arreglo2D(ancho, alto);

        for (int fila = 0; fila < alto; fila++) {
            for (int columna = 0; columna < ancho; columna++) {
                nuevaMatriz.cambiar(columna, alto - 1 - fila, matrizPixeles.obtener(fila, columna));
            }
        }

        // Actualizar dimensiones y matriz
        int temp = ancho;
        ancho = alto;
        alto = temp;
        matrizPixeles = nuevaMatriz;
    }

    // Rotar imagen 180 grados
    public void rotar180Grados() {
        invertirHorizontal();
        invertirVertical();
    }

    // Rotar imagen 270 grados
    public void rotar270Grados() {
        Arreglo2D nuevaMatriz = new Arreglo2D(ancho, alto);

        for (int fila = 0; fila < alto; fila++) {
            for (int columna = 0; columna < ancho; columna++) {
                nuevaMatriz.cambiar(ancho - 1 - columna, fila, matrizPixeles.obtener(fila, columna));
            }
        }

        // Actualizar dimensiones y matriz
        int temp = ancho;
        ancho = alto;
        alto = temp;
        matrizPixeles = nuevaMatriz;
    }

    // Aplicar transpuesta a la imagen
    public void aplicarTranspuesta() {
        matrizPixeles.transpuesta();
        // Actualizar dimensiones
        int temp = ancho;
        ancho = alto;
        alto = temp;
    }

    // Método 1: Redimensionar por factor enumerado
    public void redimensionar(FactorEscala factor) {
        if (factor == null) throw new IllegalArgumentException("El factor es obligatorio");
        redimensionar(factor.getValor());
    }

    // Método 2: Redimensionar por factor numérico (sobrecargado)
    public void redimensionar(double factor) {
        if (!Double.isFinite(factor) || factor <= 0 || ancho * factor > Integer.MAX_VALUE || alto * factor > Integer.MAX_VALUE)
            throw new IllegalArgumentException("El factor debe producir dimensiones positivas dentro del rango entero");
        int nuevoAncho = (int) Math.round(ancho * factor);
        int nuevoAlto = (int) Math.round(alto * factor);
        redimensionar(nuevoAncho, nuevoAlto);
    }

    // Método 3: Redimensionar a dimensiones específicas (sobrecargado)
    public void redimensionar(int nuevoAncho, int nuevoAlto) {
        if (nuevoAncho <= 0 || nuevoAlto <= 0) {
            throw new IllegalArgumentException("Las dimensiones deben ser positivas");
        }

        Arreglo2D nuevaMatriz = new Arreglo2D(nuevoAlto, nuevoAncho);

        for (int nuevaFila = 0; nuevaFila < nuevoAlto; nuevaFila++) {
            for (int nuevaColumna = 0; nuevaColumna < nuevoAncho; nuevaColumna++) {
                // Mapear coordenadas a la imagen original con interpolación
                double relacionY = nuevoAlto == 1 ? 0 : (double) nuevaFila / (nuevoAlto - 1);
                double relacionX = nuevoAncho == 1 ? 0 : (double) nuevaColumna / (nuevoAncho - 1);

                int filaOriginal = (int) (relacionY * (alto - 1));
                int columnaOriginal = (int) (relacionX * (ancho - 1));

                // Asegurar que no nos salimos de los límites
                filaOriginal = Math.min(filaOriginal, alto - 1);
                columnaOriginal = Math.min(columnaOriginal, ancho - 1);

                nuevaMatriz.cambiar(nuevaFila, nuevaColumna,
                        matrizPixeles.obtener(filaOriginal, columnaOriginal));
            }
        }

        // Actualizar dimensiones y matriz
        ancho = nuevoAncho;
        alto = nuevoAlto;
        matrizPixeles = nuevaMatriz;
    }

    // Añadir marco a la imagen
    public void agregarMarco(int grosor, int color) {
        if (grosor < 0 || (long) ancho + 2L * grosor > Integer.MAX_VALUE || (long) alto + 2L * grosor > Integer.MAX_VALUE)
            throw new IllegalArgumentException("El grosor debe ser no negativo y producir dimensiones válidas");
        Arreglo2D nuevaMatriz = new Arreglo2D(alto + 2 * grosor, ancho + 2 * grosor);

        // Rellenar todo con el color del marco
        nuevaMatriz.rellenar(color);

        // Copiar la imagen original en el centro
        for (int fila = 0; fila < alto; fila++) {
            for (int columna = 0; columna < ancho; columna++) {
                nuevaMatriz.cambiar(fila + grosor, columna + grosor, matrizPixeles.obtener(fila, columna));
            }
        }

        // Actualizar dimensiones y matriz
        ancho += 2 * grosor;
        alto += 2 * grosor;
        matrizPixeles = nuevaMatriz;
    }

    // Efecto Negativo
    public void aplicarNegativo() {
        for (int fila = 0; fila < alto; fila++) {
            for (int columna = 0; columna < ancho; columna++) {
                int pixel = (int) matrizPixeles.obtener(fila, columna);
                int[] componentes = extraerComponentes(pixel);

                // Invertir cada componente de color (excepto alfa)
                int nuevoRojo = 255 - componentes[1];
                int nuevoVerde = 255 - componentes[2];
                int nuevoAzul = 255 - componentes[3];

                int nuevoPixel = construirPixel(componentes[0], nuevoRojo, nuevoVerde, nuevoAzul);
                matrizPixeles.cambiar(fila, columna, nuevoPixel);
            }
        }
    }

    // Guardar la imagen procesada
    public void guardarImagen(String rutaDestino) throws IOException {
        BufferedImage imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);

        for (int fila = 0; fila < alto; fila++) {
            for (int columna = 0; columna < ancho; columna++) {
                imagen.setRGB(columna, fila, (int) matrizPixeles.obtener(fila, columna));
            }
        }

        ImageIO.write(imagen, "jpg", new File(rutaDestino));
    }
}