package colecciones.edlineal.colas.colapriori;

public class ElementoPrioritario {
    private Object contenido;
    private int valorPrioridad;

    public ElementoPrioritario(Object contenido, int valorPrioridad) {
        this.contenido = contenido;
        this.valorPrioridad = valorPrioridad;
    }

    public Object getContenido() {
        return contenido;
    }

    public int getValorPrioridad() {
        return valorPrioridad;
    }

    @Override
    public String toString() {
        return "(" + contenido.toString() + ", prioridad:" + valorPrioridad + ")";
    }
}