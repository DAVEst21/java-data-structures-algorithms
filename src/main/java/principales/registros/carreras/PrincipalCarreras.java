/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principales.registros.carreras;

import colecciones.edlineal.arreglos.Arreglo;
import utils.entradasalida.cadenas.Salida;
import registros.carreras.GestorCarreras;

/**
 *
 * @author Usuario
 */
public class PrincipalCarreras {
    public static void main(String asistentes[]){
        GestorCarreras carreras=new GestorCarreras(4,
                                     5, 3);
        
        carreras.agregarCorredor(100, 
                                    "Jelipe", 25, 'M');
        carreras.agregarCorredor(101, 
                                    "Ana", 20, 'F');
        carreras.agregarCorredor(102, 
                                    "Lupe", 19, 'F');
        carreras.agregarCorredor(103, 
                                    "Mario", 29, 'M');
        
        carreras.agregarEvento("La paz", "NoWhere", 2010);
        carreras.agregarEvento("Bicentenario", "Chinchisbravas", 2011);
        carreras.agregarEvento("Riqueza", "Polanco", 2000);
        carreras.agregarEvento("Centenario", "Susticacán", 1990);
        carreras.agregarEvento("Calma AC", "Arbolines", 1995);

        carreras.agregarAnio(2010);
        carreras.agregarAnio(2000);
        carreras.agregarAnio(1996);

        carreras.agregarKilometros(90.0, 102, 
                                "Bicentenario", 2010);
        carreras.mostrarDatos();
        
        Arreglo carrerasC = new Arreglo (2);
        carrerasC.poner("Bicentenario");
        carrerasC.poner("Calma AC");
        
        Arreglo aniosC = new Arreglo (2);
        aniosC.poner(2010);
        aniosC.poner(1996);
        
        Double kms = carreras.kilometrosXCorredor(102, carrerasC, aniosC);
        Salida.salidaPorDefecto("Recorridos " + kms);
    }
}
