/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principales.utiles.audio;

/**
 *
 * @author Usuario
 */
public class PrincipalAudio {
    public static void main(String[] args) {
        // Ruta relativa del archivo de audio original
        //String archivoOriginal = "C:/Users/Usuario/Documents/NetBeansProjects/ed_4b_25/audioDIBM.wav";

        // Crear una instancia de AudioFileRecord
        //AudioFileRecord audio = new AudioFileRecord(archivoOriginal);

        // Leer el archivo de audio
        //Salida.salidaPorDefecto("Leyendo archivo de audio...");
        //audio.leerAudio();

        // Aplicar preénfasis
        //Salida.salidaPorDefecto("Aplicando preénfasis...");
        //audio.preEnfasis(0.95);

        // Subir el volumen
        //Salida.salidaPorDefecto("Subiendo el volumen...");
        //audio.subirVolumen(2);

        // Acelerar el audio
        //Salida.salidaPorDefecto("Acelerando el audio...");
        //audio.acelerarAudio(2.0);
        
        // Retrasar el audio
        //Salida.salidaPorDefecto("Retrasando el audio...");
        //audio.retrasarAudio(2.0);

        //Eliminar silencios
        //Salida.salidaPorDefecto("Eliminando silencios...");
        //audio.eliminarSilencios(0.01);

        // Invertir en el eje X (tiempo)
        //Salida.salidaPorDefecto("Invertiendo en el eje X...");
        //audio.invertirEjeX();

        // Invertir en el eje Y (amplitud)
        //Salida.salidaPorDefecto("Invertiendo en el eje Y...");
        //audio.invertirEjeY();
        
        //audio.escribirAudio();

        //Salida.salidaPorDefecto("Proceso completado. Archivo modificado guardado.");
        
        /*
        
        AudioFileRecord audioa1 = new AudioFileRecord("C:/Users/Usuario/Documents/NetBeansProjects/ed_4b_25/probando_metodos_audio/vocales/a1.wav");
        audioa1.leerAudio();
        AudioFileRecord audioa2 = new AudioFileRecord("C:/Users/Usuario/Documents/NetBeansProjects/ed_4b_25/probando_metodos_audio/vocales/a2.wav");
        audioa2.leerAudio();
        AudioFileRecord audioa3 = new AudioFileRecord("C:/Users/Usuario/Documents/NetBeansProjects/ed_4b_25/probando_metodos_audio/vocales/a3.wav");
        audioa3.leerAudio();
        AudioFileRecord audioa4 = new AudioFileRecord("C:/Users/Usuario/Documents/NetBeansProjects/ed_4b_25/probando_metodos_audio/vocales/a4.wav");
        audioa4.leerAudio();
        AudioFileRecord audioa5 = new AudioFileRecord("C:/Users/Usuario/Documents/NetBeansProjects/ed_4b_25/probando_metodos_audio/vocales/a5.wav");
        audioa5.leerAudio();
        
        //AudioFileRecord audioe1 = new AudioFileRecord("C:/Users/Usuario/Documents/NetBeansProjects/ed_4b_25/probando_metodos_audio/vocales/e1.wav");
        //audioe1.leerAudio();
        AudioFileRecord audioe2 = new AudioFileRecord("C:/Users/Usuario/Documents/NetBeansProjects/ed_4b_25/probando_metodos_audio/vocales/e2.wav");
        audioe2.leerAudio();
        AudioFileRecord audioe3 = new AudioFileRecord("C:/Users/Usuario/Documents/NetBeansProjects/ed_4b_25/probando_metodos_audio/vocales/e3.wav");
        audioe3.leerAudio();
        AudioFileRecord audioe4 = new AudioFileRecord("C:/Users/Usuario/Documents/NetBeansProjects/ed_4b_25/probando_metodos_audio/vocales/e4.wav");
        audioe4.leerAudio();
        AudioFileRecord audioe5 = new AudioFileRecord("C:/Users/Usuario/Documents/NetBeansProjects/ed_4b_25/probando_metodos_audio/vocales/e5.wav");
        audioe5.leerAudio();
        
        AudioFileRecord audioi1 = new AudioFileRecord("C:/Users/Usuario/Documents/NetBeansProjects/ed_4b_25/probando_metodos_audio/vocales/i1.wav");
        audioi1.leerAudio();
        AudioFileRecord audioi2 = new AudioFileRecord("C:/Users/Usuario/Documents/NetBeansProjects/ed_4b_25/probando_metodos_audio/vocales/i2.wav");
        audioi2.leerAudio();
        AudioFileRecord audioi3 = new AudioFileRecord("C:/Users/Usuario/Documents/NetBeansProjects/ed_4b_25/probando_metodos_audio/vocales/i3.wav");
        audioi3.leerAudio();
        AudioFileRecord audioi4 = new AudioFileRecord("C:/Users/Usuario/Documents/NetBeansProjects/ed_4b_25/probando_metodos_audio/vocales/i4.wav");
        audioi4.leerAudio();
        AudioFileRecord audioi5 = new AudioFileRecord("C:/Users/Usuario/Documents/NetBeansProjects/ed_4b_25/probando_metodos_audio/vocales/i5.wav");
        audioi5.leerAudio();
        
        AudioFileRecord audioo1 = new AudioFileRecord("C:/Users/Usuario/Documents/NetBeansProjects/ed_4b_25/probando_metodos_audio/vocales/o1.wav");
        audioo1.leerAudio();
        AudioFileRecord audioo2 = new AudioFileRecord("C:/Users/Usuario/Documents/NetBeansProjects/ed_4b_25/probando_metodos_audio/vocales/o2.wav");
        audioo2.leerAudio();
        AudioFileRecord audioo3 = new AudioFileRecord("C:/Users/Usuario/Documents/NetBeansProjects/ed_4b_25/probando_metodos_audio/vocales/o3.wav");
        audioo3.leerAudio();
        AudioFileRecord audioo4 = new AudioFileRecord("C:/Users/Usuario/Documents/NetBeansProjects/ed_4b_25/probando_metodos_audio/vocales/o4.wav");
        audioo4.leerAudio();
        AudioFileRecord audioo5 = new AudioFileRecord("C:/Users/Usuario/Documents/NetBeansProjects/ed_4b_25/probando_metodos_audio/vocales/o5.wav");
        audioo5.leerAudio();
        
        AudioFileRecord audiou1 = new AudioFileRecord("C:/Users/Usuario/Documents/NetBeansProjects/ed_4b_25/probando_metodos_audio/vocales/u1.wav");
        audiou1.leerAudio();
        AudioFileRecord audiou2 = new AudioFileRecord("C:/Users/Usuario/Documents/NetBeansProjects/ed_4b_25/probando_metodos_audio/vocales/u2.wav");
        audiou2.leerAudio();
        AudioFileRecord audiou3 = new AudioFileRecord("C:/Users/Usuario/Documents/NetBeansProjects/ed_4b_25/probando_metodos_audio/vocales/u3.wav");
        audiou3.leerAudio();
        AudioFileRecord audiou4 = new AudioFileRecord("C:/Users/Usuario/Documents/NetBeansProjects/ed_4b_25/probando_metodos_audio/vocales/u4.wav");
        audiou4.leerAudio();
        AudioFileRecord audiou5 = new AudioFileRecord("C:/Users/Usuario/Documents/NetBeansProjects/ed_4b_25/probando_metodos_audio/vocales/u5.wav");
        audiou5.leerAudio();

        // Calcular la energía del audio
        double energiaa1 = audioa1.calcularEnergia();
        Salida.salidaPorDefecto("Energía del audio a: " + energiaa1);
        double energiaa2 = audioa2.calcularEnergia();
        Salida.salidaPorDefecto("   " + energiaa2);
        double energiaa3 = audioa3.calcularEnergia();
        Salida.salidaPorDefecto("   " + energiaa3);
        double energiaa4 = audioa4.calcularEnergia();
        Salida.salidaPorDefecto("   " + energiaa4);
        double energiaa5 = audioa5.calcularEnergia();
        Salida.salidaPorDefecto("   " + energiaa5);
        Salida.salidaPorDefecto("\n");
        
        //double energiae1 = audioe1.calcularEnergia();
        //Salida.salidaPorDefecto("Energía del audio e: " + energiae1);
        double energiae2 = audioe2.calcularEnergia();
        Salida.salidaPorDefecto("Energía del audio e: " + energiae2);
        double energiae3 = audioe3.calcularEnergia();
        Salida.salidaPorDefecto("   " + energiae3);
        double energiae4 = audioe4.calcularEnergia();
        Salida.salidaPorDefecto("   " + energiae4);
        double energiae5 = audioe5.calcularEnergia();
        Salida.salidaPorDefecto("   " + energiae5);
        Salida.salidaPorDefecto("\n");
        
        double energiai1 = audioi1.calcularEnergia();
        Salida.salidaPorDefecto("Energía del audio i: " + energiai1);
        double energiai2 = audioi2.calcularEnergia();
        Salida.salidaPorDefecto("   " + energiai2);
        double energiai3 = audioi3.calcularEnergia();
        Salida.salidaPorDefecto("   " + energiai3);
        double energiai4 = audioi4.calcularEnergia();
        Salida.salidaPorDefecto("   " + energiai4);
        double energiai5 = audioi5.calcularEnergia();
        Salida.salidaPorDefecto("   " + energiai5);
        Salida.salidaPorDefecto("\n");
        
        double energiao1 = audioo1.calcularEnergia();
        Salida.salidaPorDefecto("Energía del audio o: " + energiao1);
        double energiao2 = audioo2.calcularEnergia();
        Salida.salidaPorDefecto("   " + energiao2);
        double energiao3 = audioo3.calcularEnergia();
        Salida.salidaPorDefecto("   " + energiao3);
        double energiao4 = audioo4.calcularEnergia();
        Salida.salidaPorDefecto("   " + energiao4);
        double energiao5 = audioo5.calcularEnergia();
        Salida.salidaPorDefecto("   " + energiao5);
        Salida.salidaPorDefecto("\n");
        
        double energiau1 = audiou1.calcularEnergia();
        Salida.salidaPorDefecto("Energía del audio u: " + energiau1);
        double energiau2 = audiou2.calcularEnergia();
        Salida.salidaPorDefecto("   " + energiau2);
        double energiau3 = audiou3.calcularEnergia();
        Salida.salidaPorDefecto("   " + energiau3);
        double energiau4 = audiou4.calcularEnergia();
        Salida.salidaPorDefecto("   " + energiau4);
        double energiau5 = audiou5.calcularEnergia();
        Salida.salidaPorDefecto("   " + energiau5);
        Salida.salidaPorDefecto("\n");
        */
        
    }
}
