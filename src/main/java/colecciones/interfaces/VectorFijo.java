/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package colecciones.interfaces;

import colecciones.edlineal.arreglos.Arreglo;

/**
 *
 * @author Usuario
 */
//Maneja los metodos particulares de memoria estatica
public interface VectorFijo extends ListaDatos {
    public boolean lleno ();
    public int capacidad ();
    public int cantidad ();
    public Object obtener (int indice);
    public boolean modificar(int indice, Object valor);
    public boolean modificarLista(Arreglo indicesBusqueda, Arreglo valoresNuevos);
    public Object quitar(int indice);
    public boolean redimensionar(int maximo);
}
