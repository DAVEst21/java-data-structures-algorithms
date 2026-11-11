package registros.ventas;

import colecciones.edlineal.listas.ListaDin;

//En lugar de Arreglos usamos Listas
//Modificado el 28/04/2025 Lunes 28 de abril
public class GestorTienda2{
    protected String nombre;
    protected ListaDin notasVenta;
    protected ListaDin vendedores;

    public GestorTienda2(String nombre, int cantNotas, int cantVendedores){
        this.nombre = nombre;
        notasVenta = new ListaDin();
        vendedores = new ListaDin();
    }

    public boolean agregarVendedor(String RFC, String nombre, int numeroVendedor, String fechaNacimiento){
        Vendedor vendedorTemp = new Vendedor(nombre, RFC, numeroVendedor, fechaNacimiento);
        int retorno = vendedores.poner(vendedorTemp);
        if(retorno < 0){
            return false;
        } else {
            return true;
        }
    }

    //Método modificado 29/04/2025 Martes 29 de abril
    public boolean agregarNota(Cliente cliente, String fecha, int cantidadArticulosVendidos){
        //Se tiene cliente y fecha pero se obtendra internamente cliente, folio y vendedor;

        Vendedor vendedorTemp = (Vendedor)vendedores.verInicio();
        if(vendedorTemp == null){ //No hay vendedor
            return false;
        }

        int folioTemp = 0;
        NotaVenta2 notaTemp = (NotaVenta2) notasVenta.verFinal();

        if(notaTemp == null){ //No hay notas
            folioTemp = 1;
        }else{ //Si hay notas
            //Obtener la nota de arriba
            folioTemp = notaTemp.getFolio() + 1;
        }

        NotaVenta2 notaNueva = new NotaVenta2(cliente, vendedorTemp, fecha, folioTemp);

        //Agregar esta nota nueva al arreglo de las notas
        int retorno = notasVenta.poner(notaNueva);
        if(retorno < 0){
            return false;
        }else{
            return true;
        }
    }

    public boolean agregarArticuloNota(int folio, Articulo artComprado, int cant){
        //Encontrar la nota con el folio dado
        NotaVenta2 notaTemp = (NotaVenta2) notasVenta.buscar(folio);

        // comapra si no existe
        if(notaTemp == null){
            return false;
        } else {
            //Usamos el metodo agregarArticulo
            return notaTemp.agregarArticulo(artComprado, cant);
        }
    }

    public void imprimirNota(int folio){
        NotaVenta2 notaTemp = (NotaVenta2)notasVenta.buscar(folio);
        if(notaTemp != null){
            notaTemp.imprimirNota();
        }
    }
}