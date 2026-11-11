package utils.audio;

import java.io.*;

import utils.audio.wavfile.*;
import colecciones.edlineal.arreglos.ArregloNumerico;
import utils.audio.wavfile.WavFile;

public class AudioFileRecord {

    private long numFrames;
    private long sampleRate;
    private int numChannels;
    private ArregloNumerico buffer; // Usamos ArregloNumerico en lugar de double[]
    private WavFile wavFileR;
    private WavFile wavFileW;
    private String nomArchivo;
    private String nomRuta;
    private int validBits;

    public AudioFileRecord(String archivo) {
        try {
            File file = new File(archivo);
            wavFileR = WavFile.openWavFile(file);
            nomArchivo = file.getName();
            nomRuta = file.getAbsoluteFile().getParent();
        } catch (Exception e) {
            throw new IllegalArgumentException("No se pudo abrir el audio", e);
        }
        numChannels = wavFileR.getNumChannels();
        numFrames = wavFileR.getNumFrames();
        sampleRate = wavFileR.getSampleRate();
        validBits = wavFileR.getValidBits();
    }

    public void leerAudio() {
        if (buffer != null) throw new IllegalStateException("El audio ya fue leído");
        if (numFrames > Integer.MAX_VALUE / numChannels) throw new IllegalArgumentException("El audio excede la capacidad del arreglo");
        try {
            wavFileR.display();

            // Crear un ArregloNumerico para almacenar los datos
            buffer = new ArregloNumerico((int) numFrames * numChannels);

            // Leer los datos del archivo y almacenarlos en el ArregloNumerico
            double[] tempBuffer = new double[(int) numFrames * numChannels];
            int framesRead = wavFileR.readFrames(tempBuffer, (int) numFrames);

            for (int s = 0; s < framesRead * numChannels; s++) {
                buffer.poner(tempBuffer[s]);
            }

            wavFileR.close();
        } catch (Exception e) {
            throw new IllegalStateException("No se pudo procesar el audio", e);
        }
    }

    public void escribirAudio() {
        escribirAudio(new File(nomRuta, "2_" + nomArchivo).getPath());
    }

    // Método para obtener el ArregloNumerico
    public ArregloNumerico getBuffer() {
        return buffer;
    }

    // Método para aplicar un filtro FIR de primer orden (preénfasis)
    public void preEnfasis(double a) {
        validarBuffer();
        if (!Double.isFinite(a)) throw new IllegalArgumentException("El coeficiente debe ser finito");
        ArregloNumerico nuevoBuffer = new ArregloNumerico(buffer.cantidad());
        for (int i = 0; i < numChannels && i < buffer.cantidad(); i++) nuevoBuffer.poner(buffer.obtener(i)); // La primera muestra de cada canal no cambia

        for (int i = numChannels; i < buffer.cantidad(); i++) {
            double y = (double) buffer.obtener(i) + a * (double) buffer.obtener(i - numChannels);
            nuevoBuffer.poner(y);
        }

        buffer = nuevoBuffer;
    }

    // Método para subir el volumen
    public void subirVolumen(int intensidad) {
        validarBuffer();
        if (intensidad < 0) throw new IllegalArgumentException("La intensidad no puede ser negativa");
        for (int i = 0; i < buffer.cantidad(); i++) {
            double valor = (double) buffer.obtener(i) * intensidad;
            buffer.modificar(i, valor);
        }
    }

    // Método para bajar el volumen
    public void bajarVolumen(int intensidad) {
        validarBuffer();
        if (intensidad <= 0) throw new IllegalArgumentException("La intensidad debe ser positiva");
        for (int i = 0; i < buffer.cantidad(); i++) {
            double valor = (double) buffer.obtener(i) / intensidad;
            buffer.modificar(i, valor);
        }
    }

    // Método para acelerar el audio
    public void acelerarAudio(double factor) {
        validarBuffer();
        if (!Double.isFinite(factor) || factor <= 0) throw new IllegalArgumentException("El factor debe ser positivo y finito");
        int tramas = buffer.cantidad() / numChannels;
        double nuevasTramas = tramas / factor;
        if (nuevasTramas > Integer.MAX_VALUE / numChannels) throw new IllegalArgumentException("El resultado excede la capacidad del arreglo");
        int nuevaCapacidad = (int) nuevasTramas * numChannels;
        ArregloNumerico nuevoBuffer = new ArregloNumerico(nuevaCapacidad);
        for (int i = 0; i < (int) nuevasTramas; i++) {
            int indice = Math.min(tramas - 1, (int) (i * factor));
            for (int canal = 0; canal < numChannels; canal++) nuevoBuffer.poner(buffer.obtener(indice * numChannels + canal));
        }
        buffer = nuevoBuffer;
    }

    // Método para retrasar el audio
    public void retrasarAudio(double factor) {
        validarBuffer();
        if (!Double.isFinite(factor) || factor <= 0) throw new IllegalArgumentException("El factor debe ser positivo y finito");
        int tramas = buffer.cantidad() / numChannels;
        double nuevasTramas = tramas * factor;
        if (nuevasTramas > Integer.MAX_VALUE / numChannels) throw new IllegalArgumentException("El resultado excede la capacidad del arreglo");
        int nuevaCapacidad = (int) nuevasTramas * numChannels;
        ArregloNumerico nuevoBuffer = new ArregloNumerico(nuevaCapacidad);
        for (int i = 0; i < (int) nuevasTramas; i++) {
            int indice = Math.min(tramas - 1, (int) (i / factor));
            for (int canal = 0; canal < numChannels; canal++) nuevoBuffer.poner(buffer.obtener(indice * numChannels + canal));
        }
        buffer = nuevoBuffer;
    }

    // Método para eliminar silencios
    public void eliminarSilencios(double umbral) {
        validarBuffer();
        if (!Double.isFinite(umbral) || umbral < 0) throw new IllegalArgumentException("El umbral debe ser no negativo y finito");
        ArregloNumerico nuevoBuffer = new ArregloNumerico(buffer.cantidad());
        for (int i = 0; i < buffer.cantidad(); i += numChannels) {
            boolean conservar = false;
            for (int canal = 0; canal < numChannels; canal++) {
                if (Math.abs(((Number) buffer.obtener(i + canal)).doubleValue()) > umbral) conservar = true;
            }
            if (conservar) for (int canal = 0; canal < numChannels; canal++) nuevoBuffer.poner(buffer.obtener(i + canal));
        }
        buffer = nuevoBuffer;
    }

    // Método para invertir el audio en el eje X (tiempo)
    public void invertirEjeX() {
        validarBuffer();
        int tramas = buffer.cantidad() / numChannels;
        for (int i = 0; i < tramas / 2; i++) {
            for (int canal = 0; canal < numChannels; canal++) {
                int inicio = i * numChannels + canal;
                int fin = (tramas - i - 1) * numChannels + canal;
                Object temporal = buffer.obtener(inicio);
                buffer.modificar(inicio, buffer.obtener(fin));
                buffer.modificar(fin, temporal);
            }
        }
    }

    // Método para invertir el audio en el eje Y (amplitud)
    public void invertirEjeY() {
        validarBuffer();
        for (int i = 0; i < buffer.cantidad(); i++) {
            double valor = -(double) buffer.obtener(i);
            buffer.modificar(i, valor);
        }
    }

    // Método para calcular la energía del audio
    public double calcularEnergia() {
        validarBuffer();
        double energia = 0;
        for (int i = 0; i < buffer.cantidad(); i++) {
            double valor = (double) buffer.obtener(i);
            energia += valor * valor;
        }
        return energia;
    }
    public void escribirAudio(String destino) {
        validarBuffer();
        if (buffer.cantidad() % numChannels != 0) throw new IllegalStateException("Las muestras deben formar tramas completas");
        numFrames = buffer.cantidad() / numChannels;
        try {
            wavFileW = WavFile.newWavFile(new File(destino), numChannels, numFrames, validBits, sampleRate);
            // Obtener una copia de los datos usando leerArreglo().
            Object[] copiaDatos = buffer.leerArreglo();
            double[] tempBuffer = new double[copiaDatos.length];
            // Convertir los datos a double[].
            for (int i = 0; i < copiaDatos.length; i++) tempBuffer[i] = ((Number) copiaDatos[i]).doubleValue();
            // Escribir los datos en el archivo .wav.
            wavFileW.writeFrames(tempBuffer, (int) numFrames);
        } catch (Exception e) { throw new IllegalStateException("No se pudo escribir el audio", e); }
        finally {
            if (wavFileW != null) {
                try { wavFileW.close(); } catch (IOException e) { throw new UncheckedIOException(e); }
            }
        }
    }

    private void validarBuffer() {
        if (buffer == null) throw new IllegalStateException("Primero debe leer el audio");
    }
}
