/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registros.ventas;

import colecciones.edlineal.arreglos.Arreglo;

/**
 *
 * @author Usuario
 */
public class GestorTienda {
    protected String nombre;
    protected Arreglo notasVenta;
    protected Arreglo vendedores;

    public GestorTienda(String Nombre, int cantNotas, int cantVendedores) {
        this.nombre = nombre;
        notasVenta = new Arreglo (cantNotas);
        vendedores = new Arreglo (cantVendedores);
    }
    
    public boolean agregarVendedor (String nombre, String RFC, int numeroVendedor, String fechaNacimiento) {
        Vendedor vendedorTemp = new Vendedor(nombre, RFC, numeroVendedor, fechaNacimiento);
        int retorno = vendedores.poner(vendedorTemp);
        if (retorno < 0) { // Error
            return false;
        }
        else {
            return true;
        }
    }
    
    public boolean agregarNota(Cliente cliente, String fecha, int cantArtVend) {
        //  Ahorita ya tengo el cliente, fecha
        //  Voy a obtener internamente el folio y el vendedor
        Vendedor vendedorTemp = (Vendedor) vendedores.obtener(0);
        
        if (vendedorTemp == null) {
            return false;
        }
        
        int cantidadNotasExistentes = notasVenta.cantidad();
        int folioTemp = 0;
        NotaVenta notaTemp = null;
        
        if (cantidadNotasExistentes == 0) { // No hay notas
            folioTemp = 1;
        }
        else { //Sí hay notas
            //Obtener la nota de arriba
            notaTemp = (NotaVenta)notasVenta.obtener(cantidadNotasExistentes - 1);
            folioTemp = notaTemp.getFolio() + 1;
            
        }
        
        NotaVenta notaNueva = new NotaVenta(cantArtVend, cliente, vendedorTemp, fecha, folioTemp);
        //Agregar la nota nueva al arreglo de las notas
        int retorno = notasVenta.poner (notaNueva);
        if (retorno < 0) { //   ERROR
            return false;
        }
        else {
            return true;
        }
        
    }
    
    public boolean agregarArticuloNota (int folio, Articulo artComprado, int cant) {
        //  Encontrar la nota que tenga el folio proporcionado
        int posicionBusqueda = (int) notasVenta.buscar(folio);
        
        if (posicionBusqueda < 0) {
            return false;
        }
        else { //Sí está
            //Extraer la nota
            NotaVenta notaTemp = (NotaVenta)notasVenta.obtener(posicionBusqueda);
            //  Usamos método de afrefar Artículo de una nota
            return notaTemp.agregarArticulo(artComprado, cant);
        }
    }
    
    public void imprimirNota (int folio) {
        // Primero encontrar
        int posicionNota = (int) notasVenta.buscar(folio);
        
        if (posicionNota >= 0) {
            //Existe
            NotaVenta notaTemp = (NotaVenta) notasVenta.obtener(posicionNota);
            //imprimirla
            notaTemp.imprimirNota();
        }
    }
    
}
