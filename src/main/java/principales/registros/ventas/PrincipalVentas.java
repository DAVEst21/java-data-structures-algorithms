/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principales.registros.ventas;

import registros.ventas.Articulo;
import registros.ventas.Cliente;
import registros.ventas.GestorTienda;

/**
 *
 * @author Usuario
 */
public class PrincipalVentas {
    public static void main(String[] args) {
        GestorTienda tienda = new GestorTienda("Patito SA", 5, 2);
        tienda.agregarVendedor("Pedro","BESA349511F5", 1, "12/12/2000");
        tienda.agregarVendedor("Lupe","BESA349511F5", 2, "11/11/2001");
        Cliente cliente1 = new Cliente("BESA349511F5", "Jelipe",'M');
        tienda.agregarNota(cliente1, "12/2/2025", 10);
        
        Articulo articulo1 = new Articulo("001", "Agua", 10.0);
        Articulo articulo2 = new Articulo("067", "Jugo", 20.0);
        Articulo articulo3 = new Articulo("003", "Papas", 19.0);

        tienda.agregarArticuloNota(1,articulo1,2);
        tienda.agregarArticuloNota(1,articulo2,1);
        tienda.agregarArticuloNota(1,articulo3,4);

        tienda.imprimirNota(1);
    }
}
