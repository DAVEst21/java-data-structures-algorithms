package principales.registros.ventas;

import registros.ventas.Articulo;
import registros.ventas.Cliente;
import registros.ventas.GestorTienda2;

public class PrincipalVentas2 {
    public static void main(String[] args) {
        GestorTienda2 tienda = new GestorTienda2("PatitoSA", 5, 2);
        tienda.agregarVendedor("BESA12345", "Juancho", 1, "12/12/2000");
        tienda.agregarVendedor("OSTIA2893", "Pepe Grillo", 2, "12/12/1990");
        Cliente cliente1 = new Cliente("DIOSHELP2123", "Maria Crucero", 'M');
        tienda.agregarNota(cliente1, "12/2/2025", 10);
        Articulo articulo1 = new Articulo("001", "Agua", 10);
        Articulo articulo2 = new Articulo("002", "Jugo", 20);
        Articulo articulo3 = new Articulo("003", "Papas", 21);
        tienda.agregarArticuloNota(1, articulo1, 2);
        tienda.agregarArticuloNota(1, articulo2, 1);
        tienda.agregarArticuloNota(1, articulo3, 4);

        tienda.imprimirNota(1);
    }
}