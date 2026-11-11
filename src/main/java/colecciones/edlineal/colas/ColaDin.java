package colecciones.edlineal.colas;

import colecciones.edlineal.listas.ListaDin;
import colecciones.interfaces.LoteDatos;

public class ColaDin implements LoteDatos {
    protected ListaDin cola; // La cola usa una lista dinámica para funcionar

    public ColaDin() {
        cola = new ListaDin();
    }

    @Override
    public boolean vacio() {
        return cola.vacia();
    }

    @Override
    public boolean lleno() {
        // Una cola dinámica no tiene límite de tamaño, por lo que nunca está llena
        return false;
    }

    @Override
    public boolean poner(Object valor) {
        // Agrega el elemento al final de la lista (final de la cola)
        Integer resultado = cola.poner(valor);
        if (resultado >= 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object quitar() {
        // Quita el primer elemento de la lista (inicio de la cola)
        if (!cola.vacia()) {
            Object respaldo = cola.verInicio();
            cola.quitar(0);
            return respaldo;
        }
        return null;
    }

    @Override
    public void vaciar() {
        cola.vaciar();
    }

    @Override
    public void imprimir() {
        // Imprime los elementos en orden desde el inicio
        cola.imprimir();
    }

    @Override
    public Object verTope() {
        // Devuelve el primer elemento de la lista (inicio de la cola)
        return cola.verInicio();
    }
}