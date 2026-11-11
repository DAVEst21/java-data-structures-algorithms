package colecciones.edlineal.listas.auxiliares;

//Clase como contenedor sin constructor
public class NodoABuscar {
    protected Nodo encontrado;
    protected Nodo anterior;

    public Nodo getEncontrado() {
        return encontrado;
    }

    public void setEncontrado(Nodo encontrado) {
        this.encontrado = encontrado;
    }

    public Nodo getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }
}