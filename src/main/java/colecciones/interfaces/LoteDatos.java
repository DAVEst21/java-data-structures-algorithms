/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package colecciones.interfaces;

/**
 *
 * @author Usuario
 */
public interface LoteDatos {
    public boolean vacio();
    public boolean lleno();
    public boolean poner(Object valor);
    public Object quitar();

    void vaciar();

    public void imprimir();
    public Object verTope();
}
