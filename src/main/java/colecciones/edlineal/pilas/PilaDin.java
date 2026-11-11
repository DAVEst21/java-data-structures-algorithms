package colecciones.edlineal.pilas;

import colecciones.edlineal.listas.ListaDin;
import colecciones.interfaces.LoteDatos;

public class PilaDin implements LoteDatos {
    protected ListaDin pila; // La pila usa una lista dinámica para funcionar

    public PilaDin() {
        pila = new ListaDin();
    }

    @Override
    public boolean vacio() {
        return pila.vacia();
    }

    @Override
    public boolean lleno() {
        // Una pila dinámica no tiene límite de tamaño, por lo que nunca está llena
        return false;
    }

    @Override
    public boolean poner(Object valor) {
        // Agrega el elemento al final de la lista (como tope de la pila)
        Integer resultado = pila.poner(valor);
        if (resultado >= 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object quitar() {
        // Quita el último elemento de la lista (tope de la pila)
        return pila.quitar();
    }

    @Override
    public void vaciar() {
        pila.vaciar();
    }

    @Override
    public void imprimir() {
        // Imprime los elementos en orden descendente (desde el tope)
        pila.imprimirDesc();
    }

    @Override
    public Object verTope() {
        // Devuelve el último elemento de la lista (tope de la pila)
        return pila.verFinal();
    }
}
