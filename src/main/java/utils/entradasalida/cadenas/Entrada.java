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
    public static String terminalCadenas(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(isr);
        String cadenaEntrada="";

        try{
            cadenaEntrada= buffer.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            return cadenaEntrada;
        }
    }

    public static Double terminalDouble(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(isr);
        double entrada=0.0;

        try{
            entrada= Double.parseDouble(buffer.readLine());
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            return entrada;
        }
    }
}
