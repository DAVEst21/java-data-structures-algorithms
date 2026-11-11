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
            nomRuta = file.getParent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        numChannels = wavFileR.getNumChannels();
        numFrames = wavFileR.getNumFrames();
        sampleRate = wavFileR.getSampleRate();
        validBits = wavFileR.getValidBits();
    }

    public void leerAudio() {
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
            System.err.println(e);
        }
    }

    public void escribirAudio() {
        try {
            File file = new File(nomRuta + "/2_" + nomArchivo);
            wavFileW = WavFile.newWavFile(file, numChannels, numFrames, validBits, sampleRate);

            // Obtener una copia de los datos usando leerArreglo()
            Object[] copiaDatos = buffer.leerArreglo();
            double[] tempBuffer = new double[copiaDatos.length];

            // Convertir los datos a double[]
            for (int i = 0; i < copiaDatos.length; i++) {
                tempBuffer[i] = (double) copiaDatos[i];
            }

            // Escribir los datos en el archivo .wav
            wavFileW.writeFrames(tempBuffer, (int) numFrames);
            wavFileW.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    // Método para obtener el ArregloNumerico
    public ArregloNumerico getBuffer() {
        return buffer;
    }

    // Método para aplicar un filtro FIR de primer orden (preénfasis)
    public void preEnfasis(double a) {
        ArregloNumerico nuevoBuffer = new ArregloNumerico(buffer.cantidad());
        nuevoBuffer.poner(buffer.obtener(0)); // La primera muestra no cambia

        for (int i = 1; i < buffer.cantidad(); i++) {
            double y = (double) buffer.obtener(i) + a * (double) buffer.obtener(i - 1);
            nuevoBuffer.poner(y);
        }

        buffer = nuevoBuffer;
    }

    // Método para subir el volumen
    public void subirVolumen(int intensidad) {
        for (int i = 0; i < buffer.cantidad(); i++) {
            double valor = (double) buffer.obtener(i) * intensidad;
            buffer.modificar(i, valor);
        }
    }

    // Método para bajar el volumen
    public void bajarVolumen(int intensidad) {
        for (int i = 0; i < buffer.cantidad(); i++) {
            double valor = (double) buffer.obtener(i) / intensidad;
            buffer.modificar(i, valor);
        }
    }

    // Método para acelerar el audio
    public void acelerarAudio(double factor) {
        int nuevaCapacidad = (int) (buffer.cantidad() / factor);
        ArregloNumerico nuevoBuffer = new ArregloNumerico(nuevaCapacidad);

        for (int i = 0; i < nuevaCapacidad; i++) {
            int indice = (int) (i * factor);
            nuevoBuffer.poner(buffer.obtener(indice));
        }

        buffer = nuevoBuffer;
    }

    // Método para retrasar el audio
    public void retrasarAudio(double factor) {
        int nuevaCapacidad = (int) (buffer.cantidad() * factor);
        ArregloNumerico nuevoBuffer = new ArregloNumerico(nuevaCapacidad);

        for (int i = 0; i < nuevaCapacidad; i++) {
            int indice = (int) (i / factor);
            nuevoBuffer.poner(buffer.obtener(indice));
        }

        buffer = nuevoBuffer;
    }

    // Método para eliminar silencios
    public void eliminarSilencios(double umbral) {
        ArregloNumerico nuevoBuffer = new ArregloNumerico(buffer.cantidad());

        for (int i = 0; i < buffer.cantidad(); i++) {
            double valor = (double) buffer.obtener(i);
            if (Math.abs(valor) > umbral) {
                nuevoBuffer.poner(valor);
            }
        }

        buffer = nuevoBuffer;
    }

    // Método para invertir el audio en el eje X (tiempo)
    public void invertirEjeX() {
        buffer.invertir();
    }

    // Método para invertir el audio en el eje Y (amplitud)
    public void invertirEjeY() {
        for (int i = 0; i < buffer.cantidad(); i++) {
            double valor = -(double) buffer.obtener(i);
            buffer.modificar(i, valor);
        }
    }

    // Método para calcular la energía del audio
    public double calcularEnergia() {
        double energia = 0;
        for (int i = 0; i < buffer.cantidad(); i++) {
            double valor = (double) buffer.obtener(i);
            energia += valor * valor;
        }
        return energia;
    }
}
