/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principales.pilas;

import colecciones.edlineal.pilas.PilaFija;

/**
 *
 * @author Usuario
 */
public class PrincipalPila {
    public static void main(String[] args) {
        PilaFija pila = new PilaFija (6);
        
        pila.poner("A");
        pila.poner("S");
        pila.poner("T");
        pila.poner("M");
        pila.poner("R");
        
        pila.imprimir();
    }
}
