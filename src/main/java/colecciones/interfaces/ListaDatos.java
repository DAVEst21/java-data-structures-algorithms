/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package colecciones.interfaces;

import colecciones.edlineal.listas.ListaDin;
import colecciones.edlineal.arreglos.Arreglo;
import colecciones.edlineal.arreglos.ArregloNumerico;
import colecciones.ednolineal.Arreglo2D;

/**
 *
 * @author Usuario
 */
public interface ListaDatos {
    public boolean vacia ();
    public Integer poner(Object valor);
    public Object buscar(Object valor);
    public void imprimir ();
    public void imprimirDes();
    public Object quitar ();

    boolean redimensionar(int maximo);

    Object quitar(int indice);

    public Object quitar (Object valor);

    boolean cambiar(Object valorViejo, Object valorNuevo, int numVeces);

    boolean cambiar(int indice, Object valor);

    Object obtener(int indice);

    public boolean esIgual (ListaDatos lista2);
    public boolean modificar(Object valorViejo, Object valorNuevo, int numVeces);
    public Arreglo buscarValores(Object valor);

    boolean agregarMatriz2D(Arreglo2D tabla, ListaDin.TipoTabla enumTipoTabla);

    public void vaciar();

    void mostrarOI();

    ListaDin encontrarLista(Object valor);

    Arreglo aListaEstatica();

    Arreglo aListaEstatica(Arreglo elementosADescartar);

    Arreglo2D aMatriz2D(int filas, int columnas);

    public boolean agregarLista(ListaDatos lista2);
    public void invertir();
    public int contar(Object valor);
    public boolean borrarLista(ListaDatos lista2);
    public void rellenar(Object valor, int cantidad);
    public ListaDatos clonar();
    public ListaDatos subLista(int indiceInicial, int indiceFinal);
    public boolean substituir(ListaDatos lista2);
    public Object promedio ();
    public Object encontrarMayor ();
    public Object suma ();
    public boolean validaPosicion (int indice);
    public void rellenar(Object valor);
    public ListaDatos arregloDesordenado();
    public boolean esSublista(ListaDatos lista2);
    public boolean modificarLista(ListaDatos lista2, ListaDatos lista2Nuevos);
    public boolean retenerLista(ListaDatos lista2);
    public boolean poner(int indice, Object info);
    public Arreglo subLista(ArregloNumerico listaIndices);
    public Object verFinal();
    public Object[] leerArreglo();

    //Método de la clase 01/04/2025

    public void imprimirDesc();

    public Integer ponerInicio(Object valor);

    //Métodos de clase 07/04/2025
    public void iniciaIterador();
    public boolean iteradorValido();
    public Object obtenerIterador();
    public void moverIterador();

    //Métodos de clase 28/04/2025
    public Object verInicio();
}
