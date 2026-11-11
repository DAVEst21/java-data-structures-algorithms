/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registros.granja;

import colecciones.edlineal.arreglos.Arreglo;
import utils.entradasalida.cadenas.Entrada;
import utils.entradasalida.cadenas.Salida;

/**
 *
 * @author Usuario
 */
public class GestorGranja {
    protected Arreglo campesinos;

    public GestorGranja() {
        campesinos = new Arreglo (3);
    }

    public boolean agregarCampesino (String nombre) {
        Campesino campesinoTemp = new Campesino(nombre);
        int retorno = campesinos.poner(campesinoTemp);
        if (retorno < 0) { // Error
            return false;
        }
        else {
            return true;
        }
    }
    
    public void mostrarMenu() {
        Salida.salidaPorDefecto("Seleccione una opción:\n");
        Salida.salidaPorDefecto("A. Promedio anual de toneladas por campesino.\n");
        Salida.salidaPorDefecto("B. Mes más productivo de cada año del campesino X.\n");
        Salida.salidaPorDefecto("C. Toneladas obtenidas el último mes de cada año del campesino X.\n");
        Salida.salidaPorDefecto("D. Toneladas obtenidas en el segundo cuatrimestre de cada año del campesino X.\n");
        Salida.salidaPorDefecto("E. Campesino con peor desempeño en cada año.\n");
        Salida.salidaPorDefecto("F. Año con mayores dividendos.\n");
        Salida.salidaPorDefecto("G. Época con mayor producción.\n");
        Salida.salidaPorDefecto("X. SALIR.\n");
    }
    
    public void ejecutarMenu() {
        while (true) {
            mostrarMenu();
            String opcion = Entrada.terminalCadenas();
            switch (opcion) {
                case "A":
                    imprimirPromedioAnual();
                    break;
                case "B":
                    imprimirMesProductivo();
                    break;
                case "C":
                    imprimirToneladasUltimoMes();
                    break;
                case "D":
                    imprimirToneladasSegundoCuatrimestre();
                    break;
                case "E":
                    determinarPeorCampesinoPorAnio();
                    break;
                case "F":
                    determinarAnioConMayoresDividendos();
                    break;
                case "G":
                    determinarEpocaConMayorProduccion();
                    break;
                case "X":
                    Salida.salidaPorDefecto("Saliendo del programa\n");
                    System.exit(0);
                    break;
                default:
                    Salida.salidaPorDefecto("Opción no válida.\n");
            }
        }
    }
    
    public void imprimirPromedioAnual () {
        for (int i = 0; i < campesinos.cantidad(); i++) {
            Campesino campesinoTemp = (Campesino) campesinos.obtener(i);
            Salida.salidaPorDefecto("Promedios anuales de " + campesinoTemp.nombre + ":\n");
            for (int j = 1; j <= 5; j++) {
                Salida.salidaPorDefecto("Año " + j + ": " + campesinoTemp.calcularPromedioToneladasAnio(j) + " toneladas\n");
            }
        }
    }
    
    public void imprimirMesProductivo() {
        for (int i = 0; i < campesinos.cantidad(); i++) { 
            Campesino campesinoTemp = (Campesino) campesinos.obtener(i);
            Salida.salidaPorDefecto("Meses más productivos de " + campesinoTemp.nombre + ":\n");

            for (int j = 1; j <= 5; j++) {
                Salida.salidaPorDefecto("Año " + j + ": Mes " + campesinoTemp.calcularMesProductivo(j) + "\n");
            }
        }
    }
    
    public void imprimirToneladasUltimoMes() {
        for (int i = 0; i < campesinos.cantidad(); i++) { 
            Campesino campesinoTemp = (Campesino) campesinos.obtener(i);
            Salida.salidaPorDefecto("Toneladas del último mes (diciembre) de " + campesinoTemp.nombre + ":\n");

            for (int j = 1; j <= 5; j++) {
                double toneladas = (double) campesinoTemp.calcularToneladasUltimoMes();
                Salida.salidaPorDefecto("Año " + j + ": " + toneladas + " toneladas\n");
            }
        }
    }

    
    public void imprimirToneladasSegundoCuatrimestre() {
        for (int i = 0; i < campesinos.cantidad(); i++) { 
            Campesino campesinoTemp = (Campesino) campesinos.obtener(i);
            Salida.salidaPorDefecto("Toneladas del segundo cuatrimestre de " + campesinoTemp.nombre + ":\n");

            for (int j = 1; j <= 5; j++) {
                // Llama a calcularToneladasSegundoCuatrimestre y muestra el total
                double toneladas = (double) campesinoTemp.calcularToneladasSegundoCuatrimestre(j);
                Salida.salidaPorDefecto("Año " + j + ": " + toneladas + " toneladas\n");
            }
        }
    }
    
    public void determinarPeorCampesinoPorAnio() {
        for (int anio = 1; anio <= 5; anio++) {
            double menorProduccion = 1000;
            Campesino peorCampesino = null;

            for (int i = 0; i < campesinos.cantidad(); i++) {
                Campesino campesinoTemp = (Campesino) campesinos.obtener(i);
                double promedio = campesinoTemp.calcularPromedioToneladasAnio(anio);
                if (promedio < menorProduccion) {
                    menorProduccion = promedio;
                    peorCampesino = campesinoTemp;
                }
            }

            Salida.salidaPorDefecto("En el año " + anio + ", el campesino con peor desempeño fue: " + peorCampesino.nombre + " con " + menorProduccion + " toneladas de maíz.\n");
        
        }
    }
    
    public void determinarAnioConMayoresDividendos() {
        double mayorProduccion = 0;
        int anioMayorProduccion = 0;
        for (int anio = 1; anio <= 5; anio++) {
            double produccionTotal = 0;
            for (int i = 0; i < campesinos.cantidad(); i++) {
                Campesino campesinoTemp = (Campesino) campesinos.obtener(i);
                produccionTotal = produccionTotal + (double) campesinoTemp.calcularPromedioToneladasAnio(anio) * 12;
            }
            if (produccionTotal > mayorProduccion) {
                mayorProduccion = produccionTotal;
                anioMayorProduccion = anio;
            }
        }
        Salida.salidaPorDefecto("El año con mayores dividendos fue el año " + anioMayorProduccion + "\n");
    }
    
    public void determinarEpocaConMayorProduccion() {
        String[] estaciones = {"Primavera", "Verano", "Otoño", "Invierno"};
        double[] produccionEstaciones = new double[4];
    
        for (int i = 0; i < campesinos.cantidad(); i++) {
            Campesino campesinoTemp = (Campesino) campesinos.obtener(i);
            for (int anio = 1; anio <= 5; anio++) {
                Arreglo toneladasAnio = campesinoTemp.getToneladasAnio(anio);
                for (int mes = 0; mes < 12; mes++) {
                    double toneladas = (double) toneladasAnio.obtener(mes);
                    if (mes >= 2 && mes <= 4) { // Primavera
                        produccionEstaciones[0] += toneladas;
                    } else if (mes >= 5 && mes <= 7) { // Verano
                        produccionEstaciones[1] += toneladas;
                    } else if (mes >= 8 && mes <= 10) { // Otoño
                        produccionEstaciones[2] += toneladas;
                    } else { // Invierno
                        produccionEstaciones[3] += toneladas;
                    }
                }
            }
        }
    
        int indiceMayor = 0;
        for (int i = 1; i < produccionEstaciones.length; i++) {
            if (produccionEstaciones[i] > produccionEstaciones[indiceMayor]) {
                indiceMayor = i;
            }
        }
        Salida.salidaPorDefecto("La época del año con mayor producción es: " + estaciones[indiceMayor] + "\n");
    }
    
}
