package registros.ventas;

import colecciones.edlineal.listas.ListaDin;
import utils.entradasalida.cadenas.Salida;

//En lugar de Arreglos usamos Listas
public class NotaVenta2{
    protected int folio;
    protected String fecha;
    protected Vendedor vendedorVenta;
    protected Cliente clienteVenta;
    protected ListaDin articulosVenta;
    protected ListaDin cantidadArticulos;
    protected double total;

    //Quitamos un parametro
    public NotaVenta2(Cliente cliente, Vendedor vendedor, String fecha, int folio){
        clienteVenta = cliente;
        vendedorVenta = vendedor;
        this.fecha = fecha;
        this.folio = folio;
        articulosVenta = new ListaDin(); //En la lista no se necesitan saber cuantos "int cantidadArticulosVenta"
        cantidadArticulos = new ListaDin(); //En la lista no se necesitan saber cuantos "int cantidadArticulosVenta"
        total = 0.0;
    }

    //No cambia nada
    public boolean agregarArticulo(Articulo articuloVendido, int cuantos){
        if (articuloVendido == null || cuantos <= 0 || !Double.isFinite(articuloVendido.getPrecioUni()) || articuloVendido.getPrecioUni() < 0) return false;
        int resultadoInsercionA = articulosVenta.poner(articuloVendido);
        int resultadoInsercionC = cantidadArticulos.poner(cuantos);

        if(resultadoInsercionA == -1 || resultadoInsercionC == -1){
            //Hubo error
            return false;
        }else{
            return true;
        }
    }

    //Cambiamos el método de Arreglos a ListaDIn
    private double calcularTotal(){
        //Variable del total acumulado
        double total = 0.0;
        //Recorrer las listas paralelas y obtener los articulos y sus costos para multiplicarlo por la cantidad vendida

        //Inicializar ListasDin
        articulosVenta.iniciaIterador();
        cantidadArticulos.iniciaIterador();
        //Se quita el for por un while
        while (articulosVenta.iteradorValido() == true && cantidadArticulos.iteradorValido() == true){
            //Extraer un articulo guardado en la lista de articulos
            Articulo articuloTemp = (Articulo)articulosVenta.obtenerIterador();

            //Obtener el precio de ese articulo
            double precioUnitario = articuloTemp.getPrecioUni();

            //Obtener la cantidad de elementos comprados de la lista de cantidad
            int cantidad = (int)cantidadArticulos.obtenerIterador();

            //Multiplicar para obtener subtotal
            double subtotal = precioUnitario * cantidad;

            //acumular el subtotal dentro del total
            total = total + subtotal;
            articulosVenta.moverIterador();
            cantidadArticulos.moverIterador();
        }
        return total;
    }

    //Modificado 28/04/2025 Lunes 28 de abril
    public void imprimirNota(){
        //Encabezado
        Salida.salidaPorDefecto("***Nota de venta***\n");
        Salida.salidaPorDefecto("folio: " + this.folio);
        Salida.salidaPorDefecto("\t\t\t Fecha: " + this.fecha + "\n");
        Salida.salidaPorDefecto("Cajero: " + vendedorVenta.getNombre() + "\n");
        Salida.salidaPorDefecto("Cliente: " + clienteVenta.getNombre() + "\n");

        //Detalle

        //Unos títulos de columna
        Salida.salidaPorDefecto("No.Articulos\t Clave\tDescripcion\tCantidad\tPrecio U.\tSubtotal\n");

        //imprimir todos los artículos


        articulosVenta.iniciaIterador();
        cantidadArticulos.iniciaIterador();
        int cadaArticulo = 1;
        while (articulosVenta.iteradorValido() == true){

            //Mostrar el consecutivo (No.Art)
            Salida.salidaPorDefecto("" + (cadaArticulo));

            //Extraer cada articulo y sus propiedades para imprimirlas
            Articulo articuloTemp = (Articulo)articulosVenta.obtenerIterador();
            Salida.salidaPorDefecto("" + articuloTemp.getClave()+"\t");
            Salida.salidaPorDefecto("" + articuloTemp.getDescripcion()+"\t");

            //Extraer la cantidad de cada articulo vendido
            int cantidadTemp = (int)cantidadArticulos.obtenerIterador();
            Salida.salidaPorDefecto(cantidadTemp+"\t");

            //Mostrar el subtotal
            Salida.salidaPorDefecto("" + articuloTemp.getPrecioUni()+"\t");
            double subtotal = articuloTemp.getPrecioUni()*cantidadTemp;
            Salida.salidaPorDefecto(subtotal+"\n");
            articulosVenta.moverIterador();
            cantidadArticulos.moverIterador();
        }
        //Mostrar el total
        total = calcularTotal();
        Salida.salidaPorDefecto("\t\t\t\t\t\t Total: "+ total);

    }

    @Override
    public String toString(){
        return "" + folio;
    }

    public int getFolio(){
        return folio;
    }

}