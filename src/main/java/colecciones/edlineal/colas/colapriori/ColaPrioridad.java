package colecciones.edlineal.colas.colapriori;

import colecciones.edlineal.arreglos.Arreglo;
import colecciones.interfaces.LoteDatos;
import utils.entradasalida.cadenas.Salida;

public class ColaPrioridad implements LoteDatos {
    private Arreglo elementos;
    private boolean mayorPrioridadPrimero;

    public ColaPrioridad(int tamanio, boolean mayorPrioridadPrimero) {
        this.elementos = new Arreglo(tamanio);
        this.mayorPrioridadPrimero = mayorPrioridadPrimero;
    }

    @Override
    public boolean vacio() {
        return elementos.vacia();
    }

    @Override
    public boolean lleno() {
        return elementos.lleno();
    }

    @Override
    public boolean poner(Object valor) {
        // Validamos que el elemento sea del tipo correcto
        if (valor instanceof ElementoPrioritario) {
            // El método poner de Arreglo devuelve la posición de inserción o -1 si falla
            Integer resultado = elementos.poner(valor);
            // Convertimos el resultado a booleano (true si se insertó, false si no)
            return resultado >= 0;
        }
        return false;
    }

    public boolean agregar(Object contenido, int prioridad) {
        if (lleno()) {
            return false;
        }
        // Creamos un nuevo ElementoPrioritario y lo agregamos al arreglo
        Integer resultado = elementos.poner(new ElementoPrioritario(contenido, prioridad));
        return resultado >= 0;
    }

    @Override
    public Object quitar() {
        if (vacio()) {
            return null;
        }

        // Encontramos la posición del elemento con mayor prioridad
        int indiceElementoPrioritario = encontrarIndiceElementoPrioritario();
        // Obtenemos el elemento completo
        ElementoPrioritario elemento = (ElementoPrioritario) elementos.obtener(indiceElementoPrioritario);

        // Removemos el elemento del arreglo
        elementos.quitar(indiceElementoPrioritario);

        // Devolvemos solo el contenido (sin la prioridad)
        return elemento.getContenido();
    }

    private int encontrarIndiceElementoPrioritario() {
        // Inicializamos con el primer elemento como referencia
        int indicePrioritario = 0;
        ElementoPrioritario elementoPrioritario = (ElementoPrioritario) elementos.obtener(0);

        // Recorremos el arreglo desde el segundo elemento
        for (int posicionActual = 1; posicionActual < elementos.cantidad(); posicionActual++) {
            ElementoPrioritario elementoActual = (ElementoPrioritario) elementos.obtener(posicionActual);

            // Comparamos según la configuración de prioridad
            if (mayorPrioridadPrimero) {
                // Buscamos el elemento con el valor de prioridad más alto
                if (elementoActual.getValorPrioridad() > elementoPrioritario.getValorPrioridad()) {
                    elementoPrioritario = elementoActual;
                    indicePrioritario = posicionActual;
                }
            } else {
                // Buscamos el elemento con el valor de prioridad más bajo
                if (elementoActual.getValorPrioridad() < elementoPrioritario.getValorPrioridad()) {
                    elementoPrioritario = elementoActual;
                    indicePrioritario = posicionActual;
                }
            }
        }

        return indicePrioritario;
    }

    @Override
    public void vaciar() {
        elementos.vaciar();
    }

    @Override
    public void imprimir() {
        if (vacio()) {
            Salida.salidaPorDefecto("Cola de prioridad vacía\n");
            return;
        }

        // Mostramos el tipo de ordenamiento configurado
        Salida.salidaPorDefecto("Cola de Prioridad" +
                (mayorPrioridadPrimero ? "mayor prioridad primero" : "menor prioridad primero") + ":\n");

        // Creamos una copia para no afectar la cola original
        Arreglo copiaElementos = (Arreglo) elementos.clonar();

        // Extraemos y mostramos los elementos en orden de prioridad
        while (!copiaElementos.vacia()) {
            int indicePrioritario = encontrarIndicePrioritarioEnArreglo(copiaElementos);
            ElementoPrioritario elemento = (ElementoPrioritario) copiaElementos.obtener(indicePrioritario);
            Salida.salidaPorDefecto(elemento.toString() + "\n");
            copiaElementos.quitar(indicePrioritario);
        }
    }

    private int encontrarIndicePrioritarioEnArreglo(Arreglo arreglo) {
        int indicePrioritario = 0;
        ElementoPrioritario elementoPrioritario = (ElementoPrioritario) arreglo.obtener(0);

        for (int posicionActual = 1; posicionActual < arreglo.cantidad(); posicionActual++) {
            ElementoPrioritario elementoActual = (ElementoPrioritario) arreglo.obtener(posicionActual);

            if (mayorPrioridadPrimero) {
                if (elementoActual.getValorPrioridad() > elementoPrioritario.getValorPrioridad()) {
                    elementoPrioritario = elementoActual;
                    indicePrioritario = posicionActual;
                }
            } else {
                if (elementoActual.getValorPrioridad() < elementoPrioritario.getValorPrioridad()) {
                    elementoPrioritario = elementoActual;
                    indicePrioritario = posicionActual;
                }
            }
        }

        return indicePrioritario;
    }

    @Override
    public Object verTope() {
        if (vacio()) {
            return null;
        }

        int indicePrioritario = encontrarIndiceElementoPrioritario();
        ElementoPrioritario elemento = (ElementoPrioritario) elementos.obtener(indicePrioritario);

        return elemento.getContenido();
    }

    public ElementoPrioritario verElementoTope() {
        if (vacio()) {
            return null;
        }

        int indicePrioritario = encontrarIndiceElementoPrioritario();
        return (ElementoPrioritario) elementos.obtener(indicePrioritario);
    }

    public Integer verPrioridadTope() {
        if (vacio()) {
            return null;
        }

        int indicePrioritario = encontrarIndiceElementoPrioritario();
        ElementoPrioritario elemento = (ElementoPrioritario) elementos.obtener(indicePrioritario);

        return elemento.getValorPrioridad();
    }
}