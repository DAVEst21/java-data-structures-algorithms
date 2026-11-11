package colecciones.edlineal.listas;

import colecciones.edlineal.pilas.PilaFija;
import colecciones.edlineal.arreglos.Arreglo;
import colecciones.edlineal.arreglos.ArregloNumerico;
import colecciones.edlineal.listas.auxiliares.*;
import colecciones.ednolineal.Arreglo2D;
import colecciones.interfaces.ListaDatos;
import utils.entradasalida.cadenas.Salida;

public class ListaDin implements ListaDatos {

    protected Nodo primero;
    protected Nodo ultimo;
    protected Nodo iterador;

    public ListaDin() {
        this.primero = null;
        this.ultimo = null;
        this.iterador = null;
    }

    public boolean vacia() {
        return primero == null;
    }

    @Override
    public Integer ponerInicio(Object valor) {
        Nodo nuevoNodo = new Nodo(valor);
        if (nuevoNodo != null) {
            if (vacia()) {
                primero = nuevoNodo;
                ultimo = nuevoNodo;
            } else {
                nuevoNodo.setLigaDer(primero);
                primero = nuevoNodo;
            }
            return 0;
        }
        return -1;
    }

    @Override
    public Integer poner(Object valor) {
        Nodo nuevoNodo = new Nodo(valor);
        if (nuevoNodo != null) {
            if (vacia()) {
                primero = nuevoNodo;
                ultimo = nuevoNodo;
            } else {
                ultimo.setLigaDer(nuevoNodo);
                ultimo = nuevoNodo;
            }
            return 0;
        }
        return -1;
    }

    @Override
    public void imprimir() {
        Nodo temporal = primero;
        while (temporal != null) {
            Object info = temporal.getDato();
            Salida.salidaPorDefecto(info + "->");
            temporal = temporal.getLigaDer();
        }
        Salida.salidaPorDefecto("null\n");
    }

    @Override
    public void mostrarOI() {
        PilaFija pila = new PilaFija(cantidad()); // Capacidad para todos los nodos actuales
        Nodo temporal = primero;

        // Guardar elementos en la pila
        while (temporal != null) {
            pila.poner(temporal.getDato());
            temporal = temporal.getLigaDer();
        }

        // Imprimir elementos desde la pila
        while (!pila.vacio()) {
            Salida.salidaPorDefecto(pila.quitar() + "->");
        }
        Salida.salidaPorDefecto("null\n");
    }

    @Override
    public ListaDin encontrarLista(Object valor) {
        ListaDin resultado = new ListaDin();
        Nodo temporal = primero;

        // Recorrer la lista y agregar coincidencias
        while (temporal != null) {
            if (iguales(valor, temporal.getDato())) {
                resultado.poner(temporal.getDato());
            }
            temporal = temporal.getLigaDer();
        }
        return resultado;
    }

    @Override
    public Arreglo aListaEstatica() {
        Arreglo resultado = new Arreglo(cantidad()); // Capacidad para todos los nodos actuales
        Nodo temporal = primero;

        // Copiar elementos al arreglo
        while (temporal != null) {
            resultado.poner(temporal.getDato());
            temporal = temporal.getLigaDer();
        }
        return resultado;
    }

    @Override
    public Arreglo aListaEstatica(Arreglo elementosADescartar) {
        Arreglo resultado = new Arreglo(cantidad()); // Capacidad para todos los nodos actuales
        Nodo temporal = primero;

        // Copiar elementos no presentes en elementosADescartar
        while (temporal != null) {
            Object dato = temporal.getDato();
            if ((int) elementosADescartar.buscar(dato) < 0) {
                resultado.poner(dato);
            }
            temporal = temporal.getLigaDer();
        }
        return resultado;
    }

    @Override
    public Arreglo2D aMatriz2D(int filas, int columnas) {
        if (filas < 0 || columnas < 0) return null;
        if (filas == 0 || columnas == 0) return new Arreglo2D(filas, columnas);
        Arreglo2D matriz = new Arreglo2D(filas, columnas);
        Nodo temporal = primero;
        int i = 0, j = 0;

        // Llenar la matriz por renglones
        while (temporal != null && i < filas) {
            matriz.cambiar(i, j, temporal.getDato());
            j++;
            if (j >= columnas) {
                j = 0;
                i++;
            }
            temporal = temporal.getLigaDer();
        }
        // Rellenar con null si faltan elementos
        while (i < filas) {
            while (j < columnas) {
                matriz.cambiar(i, j, null);
                j++;
            }
            j = 0;
            i++;
        }
        return matriz;
    }

    @Override
    public boolean agregarLista(ListaDatos listaDatos2) {
        if (listaDatos2 instanceof ListaDin) {
            ListaDin lista = (ListaDin) listaDatos2;
            if (lista == this) lista = (ListaDin) clonar();
            Nodo temporal = lista.primero;

            // Agregar cada elemento al final
            while (temporal != null) {
                if (poner(temporal.getDato()) < 0) {
                    return false;
                }
                temporal = temporal.getLigaDer();
            }
            return true;
        } else if (listaDatos2 instanceof Arreglo) {
            Arreglo arreglo = (Arreglo) listaDatos2;
            for (int i = 0; i < arreglo.cantidad(); i++) {
                if (poner(arreglo.obtener(i)) < 0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public ListaDatos clonar() {
        ListaDin copia = new ListaDin();
        Nodo temporal = primero;

        // Copiar cada elemento
        while (temporal != null) {
            copia.poner(temporal.getDato());
            temporal = temporal.getLigaDer();
        }
        return copia;
    }

    @Override
    public boolean agregarMatriz2D(Arreglo2D tabla, TipoTabla enumTipoTabla) {
        int filas = tabla.obtenerRenglones();
        int columnas = tabla.obtenerColumnas();

        if (enumTipoTabla == TipoTabla.RENGLON) {
            // Agregar por renglones
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    if (poner(tabla.obtener(i, j)) < 0) {
                        return false;
                    }
                }
            }
        } else {
            // Agregar por columnas
            for (int j = 0; j < columnas; j++) {
                for (int i = 0; i < filas; i++) {
                    if (poner(tabla.obtener(i, j)) < 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void vaciar() {
        primero = null;
        ultimo = null;
        iterador = null;
    }

    @Override
    public void rellenar(Object valor, int cantidad) {
        vaciar(); // Limpiar la lista primero
        for (int i = 0; i < cantidad; i++) {
            poner(valor);
        }
    }

    @Override
    public int contar(Object valor) {
        int contador = 0;
        Nodo temporal = primero;

        // Contar coincidencias
        while (temporal != null) {
            if (iguales(valor, temporal.getDato())) {
                contador++;
            }
            temporal = temporal.getLigaDer();
        }
        return contador;
    }

    @Override
    public void invertir() {
        if (primero == null || primero == ultimo) {
            return;
        }

        Nodo anterior = null;
        Nodo actual = primero;
        ultimo = primero;

        // Invertir los enlaces
        while (actual != null) {
            Nodo siguiente = actual.getLigaDer();
            actual.setLigaDer(anterior);
            anterior = actual;
            actual = siguiente;
        }
        primero = anterior;
    }

    @Override
    public boolean cambiar(Object valorViejo, Object valorNuevo, int numVeces) {
        int contador = 0;
        Nodo temporal = primero;

        // Reemplazar hasta numVeces
        while (temporal != null && contador < numVeces) {
            if (iguales(valorViejo, temporal.getDato())) {
                temporal.setDato(valorNuevo);
                contador++;
            }
            temporal = temporal.getLigaDer();
        }
        return contador > 0;
    }

    @Override
    public boolean cambiar(int indice, Object valor) {
        if (indice < 0) return false;
        Nodo temporal = primero;
        int contador = 0;

        // Buscar el nodo en la posición indice
        while (temporal != null && contador < indice) {
            temporal = temporal.getLigaDer();
            contador++;
        }
        if (temporal != null) {
            temporal.setDato(valor);
            return true;
        }
        return false;
    }

    @Override
    public Object obtener(int indice) {
        if (indice < 0) return null;
        Nodo temporal = primero;
        int contador = 0;

        // Buscar el nodo en la posición indice
        while (temporal != null && contador < indice) {
            temporal = temporal.getLigaDer();
            contador++;
        }
        return temporal != null ? temporal.getDato() : null;
    }

    @Override
    public boolean esIgual(ListaDatos listaDatos2) {
        if (!(listaDatos2 instanceof ListaDin)) {
            return false;
        }
        ListaDin lista2 = (ListaDin) listaDatos2;
        Nodo temp1 = primero;
        Nodo temp2 = lista2.primero;

        // Comparar elemento por elemento
        while (temp1 != null && temp2 != null) {
            if (!iguales(temp1.getDato(), temp2.getDato())) {
                return false;
            }
            temp1 = temp1.getLigaDer();
            temp2 = temp2.getLigaDer();
        }
        return temp1 == null && temp2 == null;
    }

    @Override
    public boolean modificar(Object valorViejo, Object valorNuevo, int numVeces) {
        return cambiar(valorViejo, valorNuevo, numVeces);
    }

    @Override
    public boolean redimensionar(int maximo) {
        if (maximo < 0) {
            return false;
        }

        ListaDin nueva = new ListaDin();
        Nodo temporal = primero;
        int contador = 0;

        // Copiar hasta maximo elementos
        while (temporal != null && contador < maximo) {
            nueva.poner(temporal.getDato());
            temporal = temporal.getLigaDer();
            contador++;
        }

        primero = nueva.primero;
        ultimo = nueva.ultimo;
        iterador = null;
        return true;
    }

    @Override
    public Object quitar(int indice) {
        if (vacia() || indice < 0) {
            return null;
        }

        Nodo anterior = null;
        Nodo actual = primero;
        int contador = 0;

        // Buscar el nodo en la posición indice
        while (actual != null && contador < indice) {
            anterior = actual;
            actual = actual.getLigaDer();
            contador++;
        }

        if (actual == null) {
            return null;
        }

        Object respaldo = actual.getDato();
        if (anterior == null) {
            primero = actual.getLigaDer();
        } else {
            anterior.setLigaDer(actual.getLigaDer());
        }
        if (actual == ultimo) {
            ultimo = anterior;
        }
        return respaldo;
    }

    @Override
    public ListaDin quitar(Object valor) {
        ListaDin eliminados = new ListaDin();
        Nodo anterior = null;
        Nodo actual = primero;

        // Buscar y eliminar todas las ocurrencias
        while (actual != null) {
            if (iguales(valor, actual.getDato())) {
                eliminados.poner(actual.getDato());
                if (anterior == null) {
                    primero = actual.getLigaDer();
                } else {
                    anterior.setLigaDer(actual.getLigaDer());
                }
                if (actual == ultimo) {
                    ultimo = anterior;
                }
            } else {
                anterior = actual;
            }
            actual = actual.getLigaDer();
        }
        return eliminados;
    }

    // Métodos restantes de ListaDatos
    @Override
    public Object buscar(Object valor) {
        NodoABuscar busqueda = buscarNodo(valor);
        return busqueda != null ? busqueda.getEncontrado().getDato() : null;
    }

    @Override
    public void imprimirDes() {
        mostrarOI(); // Reutiliza mostrarOI
    }

    @Override
    public Object quitar() {
        if (vacia()) return null;
        Nodo anterior = null;
        Nodo actual = primero;
        while (actual.getLigaDer() != null) {
            anterior = actual;
            actual = actual.getLigaDer();
        }
        Object respaldo = actual.getDato();
        if (anterior == null) primero = null;
        else anterior.setLigaDer(null);
        ultimo = anterior;
        iterador = null;
        return respaldo;
    }

    @Override
    public Arreglo buscarValores(Object valor) {
        Arreglo posiciones = new Arreglo(cantidad());
        Nodo temporal = primero;
        int indice = 0;

        while (temporal != null) {
            if (iguales(valor, temporal.getDato())) {
                posiciones.poner(indice);
            }
            temporal = temporal.getLigaDer();
            indice++;
        }
        return posiciones;
    }

    @Override
    public boolean borrarLista(ListaDatos lista2) {
        if (!(lista2 instanceof ListaDin)) {
            return false;
        }
        ListaDin lista = (ListaDin) lista2;
        Nodo temporal = lista.primero;

        while (temporal != null) {
            quitar(temporal.getDato());
            temporal = temporal.getLigaDer();
        }
        return true;
    }

    @Override
    public ListaDatos subLista(int indiceInicial, int indiceFinal) {
        if (indiceInicial < 0 || indiceFinal < indiceInicial) {
            return null;
        }

        ListaDin sublista = new ListaDin();
        Nodo temporal = primero;
        int contador = 0;

        while (temporal != null && contador <= indiceFinal) {
            if (contador >= indiceInicial) {
                sublista.poner(temporal.getDato());
            }
            temporal = temporal.getLigaDer();
            contador++;
        }
        return sublista;
    }

    @Override
    public boolean substituir(ListaDatos lista2) {
        if (!(lista2 instanceof ListaDin)) {
            return false;
        }
        ListaDin lista = (ListaDin) lista2;
        primero = lista.primero;
        ultimo = lista.ultimo;
        iterador = null;
        return true;
    }

    @Override
    public Object promedio() {
        if (vacia() || !(primero.getDato() instanceof Number)) {
            return null;
        }

        double suma = 0;
        int contador = 0;
        Nodo temporal = primero;

        while (temporal != null) {
            suma += ((Number) temporal.getDato()).doubleValue();
            contador++;
            temporal = temporal.getLigaDer();
        }
        return suma / contador;
    }

    @Override
    public Object encontrarMayor() {
        if (vacia() || !(primero.getDato() instanceof Number)) {
            return null;
        }

        double mayor = ((Number) primero.getDato()).doubleValue();
        Nodo temporal = primero.getLigaDer();

        while (temporal != null) {
            double valor = ((Number) temporal.getDato()).doubleValue();
            if (valor > mayor) {
                mayor = valor;
            }
            temporal = temporal.getLigaDer();
        }
        return mayor;
    }

    @Override
    public Object suma() {
        if (vacia() || !(primero.getDato() instanceof Number)) {
            return null;
        }

        double suma = 0;
        Nodo temporal = primero;

        while (temporal != null) {
            suma += ((Number) temporal.getDato()).doubleValue();
            temporal = temporal.getLigaDer();
        }
        return suma;
    }

    @Override
    public boolean validaPosicion(int indice) {
        if (indice < 0) {
            return false;
        }

        Nodo temporal = primero;
        int contador = 0;

        while (temporal != null && contador <= indice) {
            if (contador == indice) {
                return true;
            }
            temporal = temporal.getLigaDer();
            contador++;
        }
        return false;
    }

    @Override
    public void rellenar(Object valor) {
        vaciar();
        poner(valor);
    }

    @Override
    public ListaDatos arregloDesordenado() {
        ListaDin desordenada = (ListaDin) clonar();
        int total = cantidad();
        // Mezclar usando intercambio aleatorio (simple para evitar librerías)
        // Nota: Implementación básica, puede mejorarse
        for (int i = total - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            Object temporal = desordenada.obtener(i);
            desordenada.cambiar(i, desordenada.obtener(j));
            desordenada.cambiar(j, temporal);
        }
        return desordenada;
    }

    @Override
    public boolean esSublista(ListaDatos lista2) {
        if (!(lista2 instanceof ListaDin)) return false;
        ListaDin lista = (ListaDin) lista2;
        if (lista.vacia()) return true;
        for (Nodo inicio = primero; inicio != null; inicio = inicio.getLigaDer()) {
            Nodo actual = inicio;
            Nodo buscado = lista.primero;
            while (actual != null && buscado != null &&
                    iguales(actual.getDato(), buscado.getDato())) {
                actual = actual.getLigaDer();
                buscado = buscado.getLigaDer();
            }
            if (buscado == null) return true;
        }
        return false;
    }

    @Override
    public boolean modificarLista(ListaDatos lista2, ListaDatos lista2Nuevos) {
        if (!(lista2 instanceof ListaDin) || !(lista2Nuevos instanceof ListaDin)) {
            return false;
        }
        ListaDin vieja = (ListaDin) lista2;
        ListaDin nueva = (ListaDin) lista2Nuevos;
        if (vieja.cantidad() != nueva.cantidad()) return false;
        Nodo temp1 = primero;
        Nodo temp2 = vieja.primero;
        Nodo temp3 = nueva.primero;

        while (temp1 != null && temp2 != null && temp3 != null) {
            if (iguales(temp1.getDato(), temp2.getDato())) {
                temp1.setDato(temp3.getDato());
                temp2 = temp2.getLigaDer();
                temp3 = temp3.getLigaDer();
            }
            temp1 = temp1.getLigaDer();
        }
        return temp2 == null;
    }

    @Override
    public boolean retenerLista(ListaDatos lista2) {
        if (!(lista2 instanceof ListaDin)) {
            return false;
        }
        ListaDin lista = (ListaDin) lista2;
        ListaDin nueva = new ListaDin();
        Nodo temporal = primero;

        while (temporal != null) {
            if (lista.buscarNodo(temporal.getDato()) != null) {
                nueva.poner(temporal.getDato());
            }
            temporal = temporal.getLigaDer();
        }
        primero = nueva.primero;
        ultimo = nueva.ultimo;
        iterador = null;
        return true;
    }

    @Override
    public boolean poner(int indice, Object info) {
        if (indice < 0) {
            return false;
        }

        Nodo nuevo = new Nodo(info);
        if (nuevo == null) {
            return false;
        }

        if (indice == 0) {
            nuevo.setLigaDer(primero);
            primero = nuevo;
            if (ultimo == null) {
                ultimo = nuevo;
            }
            return true;
        }

        Nodo anterior = null;
        Nodo actual = primero;
        int contador = 0;

        while (actual != null && contador < indice) {
            anterior = actual;
            actual = actual.getLigaDer();
            contador++;
        }

        if (contador == indice) {
            anterior.setLigaDer(nuevo);
            nuevo.setLigaDer(actual);
            if (actual == null) {
                ultimo = nuevo;
            }
            return true;
        }
        return false;
    }

    @Override
    public Arreglo subLista(ArregloNumerico listaIndices) {
        Arreglo resultado = new Arreglo(listaIndices.cantidad());
        for (int i = 0; i < listaIndices.cantidad(); i++) {
            int indice = ((Number) listaIndices.obtener(i)).intValue();
            Object valor = obtener(indice);
            if (valor != null) {
                resultado.poner(valor);
            }
        }
        return resultado;
    }

    @Override
    public Object verFinal() {
        return ultimo != null ? ultimo.getDato() : null;
    }

    @Override
    public Object[] leerArreglo() {
        return aListaEstatica().leerArreglo();
    }

    @Override
    public void imprimirDesc() {
        mostrarOI();
    }

    @Override
    public Object verInicio() {
        return primero != null ? primero.getDato() : null;
    }

    @Override
    public void iniciaIterador() {
        iterador = primero;
    }

    @Override
    public boolean iteradorValido() {
        return iterador != null;
    }

    @Override
    public Object obtenerIterador() {
        return iteradorValido() ? iterador.getDato() : null;
    }

    @Override
    public void moverIterador() {
        if (iteradorValido()) {
            iterador = iterador.getLigaDer();
        }
    }

    private NodoABuscar buscarNodo(Object valor) {
        Nodo anterior = null;
        Nodo encontrado = primero;

        while (encontrado != null && !iguales(valor, encontrado.getDato())) {
            anterior = encontrado;
            encontrado = encontrado.getLigaDer();
        }

        if (encontrado != null) {
            NodoABuscar retornoBusqueda = new NodoABuscar();
            retornoBusqueda.setAnterior(anterior);
            retornoBusqueda.setEncontrado(encontrado);
            return retornoBusqueda;
        }
        return null;
    }

    // Enumerado para agregarMatriz2D
    public enum TipoTabla {
        RENGLON, COLUMNA
    }

    public int cantidad() {
        int total = 0;
        for (Nodo actual = primero; actual != null; actual = actual.getLigaDer()) total++;
        return total;
    }

    private static boolean iguales(Object a, Object b) {
        return a == null || b == null ? a == b : a.toString().equalsIgnoreCase(b.toString());
    }

}