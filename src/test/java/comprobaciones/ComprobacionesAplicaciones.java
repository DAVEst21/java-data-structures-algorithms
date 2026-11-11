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
import colecciones.edlineal.listas.ListaDin;
import registros.carreras.GestorCarreras;
import registros.granja.*;
import registros.casas.*;
import registros.ventas.*;
import utils.regresion.RegresionLineal;
import utils.audio.AudioFileRecord;
import utils.audio.wavfile.WavFile;
import utils.imagenes.ProcesadorImagen;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

class ComprobacionesAplicaciones {
    static void ejecutar() throws Exception {
        entradaSalida();
        calculadora();
        registros();
        estadistica();
        multimedia();
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

    private static void registros() throws Exception {
        GestorCarreras carreras = new GestorCarreras(2, 3, 2);
        carreras.agregarCorredor(100, "uno", 20, 'M'); carreras.agregarCorredor(101, "dos", 20, 'F');
        carreras.agregarEvento("A", "lugar", 2000); carreras.agregarEvento("B", "lugar", 2000); carreras.agregarEvento("C", "lugar", 2000);
        carreras.agregarAnio(2024); carreras.agregarAnio(2025);
        igual(true, carreras.agregarKilometros(90, 101, "C", 2024));
        igual(true, carreras.agregarKilometros(10, 101, "C", 2025));
        igual(100.0, carreras.kilometrosXCorredor(101, arreglo("C"), arreglo(2024, 2025)));
        igual(null, carreras.kilometrosXCorredor(404, arreglo("C"), arreglo(2024)));
        igual(false, carreras.agregarKilometros(-1, 101, "C", 2024));

        Campesino campesino = new Campesino("uno");
        for (int anio = 1; anio <= 5; anio++) for (int mes = 0; mes < 12; mes++)
            campesino.getToneladasAnio(anio).modificar(mes, (mes + 1.0) * anio);
        igual(6.5, campesino.calcularPromedioToneladasAnio(1));
        igual(12, campesino.calcularMesProductivo(5));
        igual(26.0, campesino.calcularToneladasSegundoCuatrimestre(1));
        igual(60.0, ((Arreglo) campesino.calcularToneladasUltimoMes()).obtener(4));
        igual(null, campesino.getToneladasAnio(6));
        GranjaPrueba granja = new GranjaPrueba(); granja.agregar(campesino);
        igual(true, capturar(granja::imprimirToneladasUltimoMes).contains("Año 5: 60.0"));
        igual(true, capturar(granja::determinarAnioConMayoresDividendos).contains("año 5"));
        igual(true, capturar(granja::determinarEpocaConMayorProduccion).contains("Otoño"));
        igual(true, capturar(granja::determinarPeorCampesinoPorAnio).contains("uno"));
        igual(true, capturar(new GestorGranja()::determinarPeorCampesinoPorAnio).contains("No hay campesinos"));

        Cliente cliente = new Cliente("C", "Cliente", 'M');
        Vendedor vendedor = new Vendedor("Vendedor", "RFC", 1, "2000");
        Articulo articulo = new Articulo("A", "Artículo", 7);
        NotaVenta nota = new NotaVenta(1, cliente, vendedor, "2025", 1);
        igual(false, nota.agregarArticulo(null, 1)); igual(false, nota.agregarArticulo(articulo, 0));
        igual(true, nota.agregarArticulo(articulo, 2)); igual(false, nota.agregarArticulo(articulo, 3));
        igual(1, nota.getArticulosVenta().cantidad()); igual(1, nota.getCantidadArticulos().cantidad());
        igual(true, capturar(nota::imprimirNota).contains("Total: $14.0"));
        NotaVenta2 dinamica = new NotaVenta2(cliente, vendedor, "2025", 1);
        igual(false, dinamica.agregarArticulo(articulo, -1)); igual(true, dinamica.agregarArticulo(articulo, 2));
        igual(true, capturar(dinamica::imprimirNota).contains("Total: 14.0"));
        GestorTienda2 tienda = new GestorTienda2("ejemplo", 1, 1);
        tienda.agregarVendedor("RFC", "Nombre", 1, "2000"); tienda.agregarNota(cliente, "2025", 1);
        igual(true, capturar(() -> tienda.imprimirNota(1)).contains("Cajero: Nombre"));
        System.out.println("PASS race aggregation, deterministic farming queries and sales records");
    }

    private static void estadistica() throws Exception {
        ListaDin x = new ListaDin(), y = new ListaDin();
        for (double v : new double[]{1, 2, 3}) { x.poner(v); y.poner(4 - v); }
        CalculadorPearson pearson = new CalculadorPearson();
        igual(-1.0, pearson.calcular(x, y));
        AnalizadorPrecios analizador = new AnalizadorPrecios();
        igual(-1.0, analizador.calcularPearsonMuestral(x, y));
        cerca(-1, analizador.calcularPearsonPoblacional(x, y), 1e-12);
        ListaDin corta = new ListaDin(); corta.poner(1);
        rechaza(() -> pearson.calcular(x, corta));
        rechaza(() -> analizador.calcularPearsonMuestral(x, corta));
        rechaza(() -> new DatosCasas(arreglo("1"), arreglo()));
        rechaza(() -> new DatosCasas(arreglo("NaN"), arreglo("1")));
        ListaDin habitaciones = new ListaDin(), precios = new ListaDin();
        for (double n : new double[]{4, 4, 5}) habitaciones.poner(n);
        for (double n : new double[]{10, 30, 25}) precios.poner(n);
        igual(true, capturar(() -> analizador.calcularPromedioPorHabitaciones(habitaciones, precios)).contains("Habitaciones: 4, Precio Promedio: 20.0"));
        igual(true, capturar(() -> analizador.calcularMinMaxPorHabitaciones(habitaciones, precios)).contains("Precio Mínimo: 10.0, Precio Máximo: 30.0"));
        RegresionLineal regresion = new RegresionLineal();
        rechaza(() -> regresion.entrenar(new ArregloNumerico(0), new ArregloNumerico(0)));
        rechaza(() -> regresion.predecir(Double.NaN));
        System.out.println("PASS housing pairs, negative correlation, grouped prices and regression input guards");
    }

    private static class GranjaPrueba extends GestorGranja {
        void agregar(Campesino campesino) { campesinos.poner(campesino); }
    }

    private static void multimedia() throws Exception {
        Path carpeta = Files.createTempDirectory("media-regresion-");
        Path mono = carpeta.resolve("mono.wav"), salida = carpeta.resolve("salida.wav");
        Path stereo = carpeta.resolve("stereo.wav"), entradaImagen = carpeta.resolve("entrada.png"), salidaImagen = carpeta.resolve("salida.jpg");
        try {
            crearWav(mono, 1, new double[]{-0.5, 0, 0.25, 0.5});
            AudioFileRecord audio = leerAudio(mono);
            cerca(0.5625, audio.calcularEnergia(), 1e-4);
            audio.acelerarAudio(2); igual(2, audio.getBuffer().cantidad());
            audio.escribirAudio(salida.toString());
            WavFile wav = WavFile.openWavFile(salida.toFile());
            try { igual(2L, wav.getNumFrames()); igual(1, wav.getNumChannels()); } finally { wav.close(); }
            audio = leerAudio(mono); audio.retrasarAudio(2); igual(8, audio.getBuffer().cantidad());
            audio.escribirAudio(salida.toString());
            wav = WavFile.openWavFile(salida.toFile());
            try { igual(8L, wav.getNumFrames()); } finally { wav.close(); }
            audio = leerAudio(mono); audio.eliminarSilencios(0.1); igual(3, audio.getBuffer().cantidad());
            audio.eliminarSilencios(1); igual(0, audio.getBuffer().cantidad());
            audio.preEnfasis(0.95); audio.escribirAudio(salida.toString());
            wav = WavFile.openWavFile(salida.toFile());
            try { igual(0L, wav.getNumFrames()); } finally { wav.close(); }
            AudioFileRecord valido = leerAudio(mono);
            rechaza(() -> valido.acelerarAudio(0)); rechaza(() -> valido.retrasarAudio(Double.NaN));
            rechaza(() -> valido.bajarVolumen(0)); rechaza(() -> valido.eliminarSilencios(-1));
            crearWav(stereo, 2, new double[]{0.25, -0.25, 0.5, -0.5, 0, 0});
            audio = leerAudio(stereo); audio.invertirEjeX();
            cerca(0, ((Number) audio.getBuffer().obtener(0)).doubleValue(), 1e-4);
            cerca(0.25, ((Number) audio.getBuffer().obtener(4)).doubleValue(), 1e-4);
            cerca(-0.25, ((Number) audio.getBuffer().obtener(5)).doubleValue(), 1e-4);
            audio.eliminarSilencios(0.1); igual(4, audio.getBuffer().cantidad());
            audio.escribirAudio(salida.toString());
            wav = WavFile.openWavFile(salida.toFile());
            try { igual(2L, wav.getNumFrames()); igual(2, wav.getNumChannels()); } finally { wav.close(); }

            BufferedImage original = new BufferedImage(16, 24, BufferedImage.TYPE_INT_RGB);
            for (int y = 0; y < 24; y++) for (int x = 0; x < 16; x++) {
                int gris = 20 + (y / 8 * 2 + x / 8) * 30;
                original.setRGB(x, y, 0xff000000 | gris << 16 | gris << 8 | gris);
            }
            ImageIO.write(original, "png", entradaImagen.toFile());
            for (int operacion = 0; operacion < 7; operacion++) {
                ProcesadorImagen imagen = new ProcesadorImagen(entradaImagen.toString());
                int esperado;
                switch (operacion) {
                    case 0 -> { imagen.invertirHorizontal(); esperado = 50; }
                    case 1 -> { imagen.invertirVertical(); esperado = 140; }
                    case 2 -> { imagen.rotar90Grados(); esperado = 140; }
                    case 3 -> { imagen.rotar180Grados(); esperado = 170; }
                    case 4 -> { imagen.rotar270Grados(); esperado = 50; }
                    case 5 -> { imagen.aplicarTranspuesta(); esperado = 20; }
                    default -> { imagen.aplicarNegativo(); esperado = 235; }
                }
                imagen.guardarImagen(salidaImagen.toString());
                BufferedImage resultado = ImageIO.read(salidaImagen.toFile());
                cerca(esperado, resultado.getRGB(3, 3) & 255, 10); // Tolerancia a compresión JPEG.
            }
            ProcesadorImagen imagen = new ProcesadorImagen(entradaImagen.toString());
            rechaza(() -> imagen.redimensionar(Double.NaN)); rechaza(() -> imagen.agregarMarco(-1, 0));
            imagen.redimensionar(1, 1); imagen.guardarImagen(salidaImagen.toString());
            igual(1, ImageIO.read(salidaImagen.toFile()).getWidth());
            imagen.agregarMarco(1, 0xff000000); imagen.guardarImagen(salidaImagen.toString());
            igual(3, ImageIO.read(salidaImagen.toFile()).getWidth());
            imagen.ajustarBrillo(Integer.MAX_VALUE); imagen.guardarImagen(salidaImagen.toString());
            cerca(255, ImageIO.read(salidaImagen.toFile()).getRGB(0, 0) & 255, 2);
        } finally {
            for (Path archivo : new Path[]{mono, salida, stereo, entradaImagen, salidaImagen}) Files.deleteIfExists(archivo);
            Files.deleteIfExists(carpeta);
        }
        System.out.println("PASS WAV frame counts, stereo frames, empty audio and known image transforms");
    }

    private static AudioFileRecord leerAudio(Path archivo) throws Exception {
        AudioFileRecord audio = new AudioFileRecord(archivo.toString());
        capturar(audio::leerAudio);
        return audio;
    }

    private static void crearWav(Path archivo, int canales, double[] muestras) throws Exception {
        WavFile wav = WavFile.newWavFile(archivo.toFile(), canales, muestras.length / canales, 16, 8000);
        try { wav.writeFrames(muestras, muestras.length / canales); } finally { wav.close(); }
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
