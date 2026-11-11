package colecciones.edlineal.listas.listadinclave;

// Clase para el nodo con clave, valor y enlace
class NodoClaveValor {
    private Object clave;
    private Object valor;
    private NodoClaveValor ligaDer;

    public NodoClaveValor(Object clave, Object valor) {
        this.clave = clave;
        this.valor = valor;
        this.ligaDer = null;
    }

    public Object getClave() {
        return clave;
    }

    public void setClave(Object clave) {
        this.clave = clave;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public NodoClaveValor getLigaDer() {
        return ligaDer;
    }

    public void setLigaDer(NodoClaveValor ligaDer) {
        this.ligaDer = ligaDer;
    }
}
