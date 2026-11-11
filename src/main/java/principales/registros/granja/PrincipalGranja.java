/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principales.registros.granja;

import registros.granja.GestorGranja;

/**
 *
 * @author Usuario
 */
public class PrincipalGranja {
    public static void main(String[] args) {
        GestorGranja granja = new GestorGranja ();
        granja.agregarCampesino("David");
        granja.agregarCampesino("Ignacio");
        granja.agregarCampesino("Becerra");
        
        granja.ejecutarMenu();
        
        
    }
}
