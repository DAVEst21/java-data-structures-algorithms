/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registros.ventas;

import colecciones.edlineal.arreglos.Arreglo;
import utils.entradasalida.cadenas.Salida;

/**
 *
 * @author Usuario
 */
public class NotaVenta {
    protected int folio;
    protected String fecha;
    protected Vendedor vendedorVenta;
    protected Cliente clienteVenta;
    protected Arreglo articulosVenta;
    protected Arreglo cantidadArticulos;
    protected double total;

    public NotaVenta(int cantidadArticulosVenta, Cliente cliente, Vendedor vendedor,
        String fecha, int folio){
            clienteVenta = cliente;
            vendedorVenta = vendedor;
            this.fecha = fecha;
            this.folio = folio;
            articulosVenta = new Arreglo(cantidadArticulosVenta);
            cantidadArticulos = new Arreglo(cantidadArticulosVenta);
            total = 0.0;

    }

    public boolean agregarArticulo(Articulo articuloVendido, int cuantos){
        
        int resultadoInsercionA = articulosVenta.poner(articuloVendido);
        int resultadoInsercionC = cantidadArticulos.poner(cuantos);

        if (resultadoInsercionA == -1 || resultadoInsercionC == -1){
            return false;
        } else{
            return true;
        }
    }


    private double calcularTotal(){
        //variable del total acumulado
        double total = 0.0;
        /*
         * recorrer los arreglos paralelos y obtener los
         * articulos y sus costros para multiplicarlo por la
         * cantidad vendida
         */
        
for(int posicionArreglo = 0; posicionArreglo < articulosVenta.cantidad(); posicionArreglo++){
            //extraer el articulo guardado en el arreglo de los articulos
            Articulo articuloTemp = (Articulo)articulosVenta.obtener(posicionArreglo);
            //Obtener precio de ese artículo
            double precioUnitario = articuloTemp.getPrecioUni();
            //obtener la cantidad de elementos comprados del arreglo de cantidad
            int cantidad = (int)cantidadArticulos.obtener(posicionArreglo);
            //multiplicar para obtener subtotal
            double subtotal = precioUnitario*cantidad;
            //acumular el subtotal dentro del total
            total = total + subtotal;
        }
        return total;
    }

    public void imprimirNota(){
        //Encabezado
        Salida.salidaPorDefecto("*******NOTA DE VENTA*******\n");
        Salida.salidaPorDefecto("Folio: " + folio);
        Salida.salidaPorDefecto("\t\t\t Fecha: " + fecha);
        Salida.salidaPorDefecto("\t\t\t Vendedor: " + vendedorVenta.getNombre() + "\n");
        Salida.salidaPorDefecto("Cliente: " + clienteVenta.getNombre() + "\n");

        //detalle
        //unos titulos de las columnas
        
        Salida.salidaPorDefecto("No. art \t\t Clave \t\t Descripción \t\t Cantidad \t\t Precio U. \t\t Subtotal\n");
        //imprimir todos los artículos
        for(int cadaArticulo = 0; cadaArticulo < articulosVenta.cantidad(); cadaArticulo++){
            //mostrar el consecutivo
            Salida.salidaPorDefecto(""+ (cadaArticulo+1) );
            //TOCA EXTRAER CADA ARTICULO Y SUS PROPIEDADES
            //para imprimirlas
            Articulo articuloTemp = (Articulo)articulosVenta.obtener(cadaArticulo);
            Salida.salidaPorDefecto(articuloTemp.getClave()+"\t");
            Salida.salidaPorDefecto(articuloTemp.getDescripcion()+"\t");
            //extraer la cantidad de cada articulo vendido
            int cantidadTemp = (int)cantidadArticulos.obtener(cadaArticulo);
            Salida.salidaPorDefecto(cantidadTemp+"\t");

            Salida.salidaPorDefecto(articuloTemp.getPrecioUni()+"\t");
            double subtotal = articuloTemp.getPrecioUni()*cantidadTemp;
            Salida.salidaPorDefecto(subtotal+"\n");

            //mostrar el subtotal

        }
        total = calcularTotal();
        Salida.salidaPorDefecto("\t\t\t\t\t Total: $"+ total);
    }

    public String toString(){
        return ""+folio;
    }

    public int getFolio() {
        return folio;
    }

    public Arreglo getArticulosVenta() {
        return articulosVenta;
    }

    public Arreglo getCantidadArticulos() {
        return cantidadArticulos;
    }  
}
