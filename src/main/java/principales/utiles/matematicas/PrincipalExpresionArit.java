/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principales.utiles.matematicas;

import utils.entradasalida.cadenas.Salida;
import utils.matematicas.TratamientoExpresiones;

/**
 *
 * @author Usuario
 */
public class PrincipalExpresionArit {
    public static void main(String[] args) {
        Salida.salidaPorDefecto("" + TratamientoExpresiones.evaluarPostfija("2242/*+23^-")); 
    }
}
