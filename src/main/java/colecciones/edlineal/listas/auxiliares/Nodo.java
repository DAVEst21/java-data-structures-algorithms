package colecciones.edlineal.listas.auxiliares;

public class Nodo {
    protected Object dato;
    protected Nodo ligaDer;

    public Nodo(Object valor) {
        this.dato = valor;
        this.ligaDer = null;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public Nodo getLigaDer() {
        return ligaDer;
    }

    public void setLigaDer(Nodo ligaDer) {
        this.ligaDer = ligaDer;
    }

    //Comparar datos entre nodos como String en lugar de Object
    @Override
    public String toString() {
        return dato.toString();
    }
}