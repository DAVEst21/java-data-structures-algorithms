/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package colecciones.edlineal.pilas;

import colecciones.edlineal.arreglos.Arreglo;
import colecciones.interfaces.LoteDatos;

/**
 *
 * @author Usuario
 */
public class PilaFija implements LoteDatos {
    protected Arreglo pila; //La pila usa un arreglo para funcionar
    
    public PilaFija (int tamanio) {
        pila = new Arreglo(tamanio);
    }
    
    @Override
    public boolean vacio() {
        return pila.vacia();
    }

    @Override
    public boolean lleno() {
        return pila.lleno();
    }

    @Override
    public boolean poner(Object valor) {
        int retorno = pila.poner(valor);
        
        if (retorno >= 0) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Object quitar() {
        return pila.quitar();
    }

    @Override
    public void vaciar() {
        pila.vaciar();
    }

    @Override
    public void imprimir() {
        pila.imprimirDes();
    }

    @Override
    public Object verTope() {
        return pila.verFinal();
    }
}
