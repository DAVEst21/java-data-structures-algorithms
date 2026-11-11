package colecciones.edlineal.listas.listadinclave;

import colecciones.edlineal.arreglos.Arreglo;
import colecciones.edlineal.listas.ListaDin;
import colecciones.ednolineal.Arreglo2D;
import utils.entradasalida.cadenas.Salida;

public class ListaDinClave {
    private NodoClaveValor primero;
    private NodoClaveValor ultimo;

    public ListaDinClave() {
        this.primero = null;
        this.ultimo = null;
    }

    // a) Insertar al final, permite duplicados en valor pero no en clave
    public boolean poner(Object clave, Object valor) {
        if (clave == null || valor == null) return false;
        NodoClaveValor nuevo = new NodoClaveValor(clave, valor);
        if (nuevo == null) {
            return false;
        }

        // Verificar si la clave ya existe
        NodoClaveValor actual = primero;
        NodoClaveValor anterior = null;
        while (actual != null) {
            if (actual.getClave().toString().equalsIgnoreCase(clave.toString())) {
                // Clave encontrada, actualizar valor
                actual.setValor(valor);
                return true;
            }
            anterior = actual;
            actual = actual.getLigaDer();
        }

        // Clave no existe, insertar al final
        if (vacia()) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.setLigaDer(nuevo);
            ultimo = nuevo;
        }
        return true;
    }

    // b) Quitar elemento por clave
    public Object quitar(Object clave) {
        if (clave == null) return null;
        NodoClaveValor actual = primero;
        NodoClaveValor anterior = null;

        while (actual != null && !actual.getClave().toString().equalsIgnoreCase(clave.toString())) {
            anterior = actual;
            actual = actual.getLigaDer();
        }

        if (actual == null) {
            return null;
        }

        Object valor = actual.getValor();
        if (anterior == null) {
            primero = actual.getLigaDer();
        } else {
            anterior.setLigaDer(actual.getLigaDer());
        }
        if (actual == ultimo) {
            ultimo = anterior;
        }
        return valor;
    }

    // c) Quitar elemento por valor
    public Object quitarContenido(Object valor) {
        if (valor == null) return null;
        NodoClaveValor actual = primero;
        NodoClaveValor anterior = null;

        while (actual != null && !actual.getValor().toString().equalsIgnoreCase(valor.toString())) {
            anterior = actual;
            actual = actual.getLigaDer();
        }

        if (actual == null) {
            return null;
        }

        Object contenido = actual.getValor();
        if (anterior == null) {
            primero = actual.getLigaDer();
        } else {
            anterior.setLigaDer(actual.getLigaDer());
        }
        if (actual == ultimo) {
            ultimo = anterior;
        }
        return contenido;
    }

    // d) Buscar por clave
    public Object buscar(Object clave) {
        if (clave == null) return null;
        NodoClaveValor actual = primero;
        while (actual != null) {
            if (actual.getClave().toString().equalsIgnoreCase(clave.toString())) {
                return actual.getValor();
            }
            actual = actual.getLigaDer();
        }
        return null;
    }

    // e) Buscar por contenido
    public Object buscarContenido(Object valor) {
        if (valor == null) return null;
        NodoClaveValor actual = primero;
        while (actual != null) {
            if (actual.getValor().toString().equalsIgnoreCase(valor.toString())) {
                return actual.getValor();
            }
            actual = actual.getLigaDer();
        }
        return null;
    }

    // f) Cambiar valor por clave
    public boolean cambiar(Object clave, Object valor) {
        if (clave == null || valor == null) return false;
        NodoClaveValor actual = primero;
        while (actual != null) {
            if (actual.getClave().toString().equalsIgnoreCase(clave.toString())) {
                actual.setValor(valor);
                return true;
            }
            actual = actual.getLigaDer();
        }
        return false;
    }

    // f) Cambiar valor viejo por nuevo
    public boolean cambiarValor(Object valorViejo, Object valorNuevo) {
        if (valorViejo == null || valorNuevo == null) return false;
        NodoClaveValor actual = primero;
        boolean cambiado = false;
        while (actual != null) {
            if (actual.getValor().toString().equalsIgnoreCase(valorViejo.toString())) {
                actual.setValor(valorNuevo);
                cambiado = true;
            }
            actual = actual.getLigaDer();
        }
        return cambiado;
    }

    // g) Mostrar lista completa
    public void mostrar() {
        NodoClaveValor actual = primero;
        while (actual != null) {
            Salida.salidaPorDefecto("[" + actual.getClave() + ", " + actual.getValor() + "] -> ");
            actual = actual.getLigaDer();
        }
        Salida.salidaPorDefecto("null\n");
    }

    // h) Mostrar claves
    public void mostrarClaves() {
        NodoClaveValor actual = primero;
        while (actual != null) {
            Salida.salidaPorDefecto(actual.getClave() + " -> ");
            actual = actual.getLigaDer();
        }
        Salida.salidaPorDefecto("null\n");
    }

    // i) Mostrar valores
    public void mostrarValores() {
        NodoClaveValor actual = primero;
        while (actual != null) {
            Salida.salidaPorDefecto(actual.getValor() + " -> ");
            actual = actual.getLigaDer();
        }
        Salida.salidaPorDefecto("null\n");
    }

    // j) Convertir a arreglos estáticos
    public Arreglo aListasEstaticas() {
        Arreglo resultado = new Arreglo(2);
        Arreglo claves = new Arreglo(cantidad());
        Arreglo valores = new Arreglo(cantidad());
        NodoClaveValor actual = primero;

        while (actual != null) {
            claves.poner(actual.getClave());
            valores.poner(actual.getValor());
            actual = actual.getLigaDer();
        }

        resultado.poner(claves);
        resultado.poner(valores);
        return resultado;
    }

    // k) Convertir a listas dinámicas
    public ListaDin aListasDinamicas() {
        ListaDin resultado = new ListaDin();
        ListaDin claves = new ListaDin();
        ListaDin valores = new ListaDin();
        NodoClaveValor actual = primero;

        while (actual != null) {
            claves.poner(actual.getClave());
            valores.poner(actual.getValor());
            actual = actual.getLigaDer();
        }

        resultado.poner(claves);
        resultado.poner(valores);
        return resultado;
    }

    // l) Convertir a matriz 2D
    public Arreglo2D aMatriz2() {
        Arreglo2D matriz = new Arreglo2D(cantidad(), 2);
        NodoClaveValor actual = primero;
        int fila = 0;

        while (actual != null) {
            matriz.cambiar(fila, 0, actual.getClave());
            matriz.cambiar(fila, 1, actual.getValor());
            actual = actual.getLigaDer();
            fila++;
        }
        return matriz;
    }

    // m) Vaciar lista
    public void vaciar() {
        primero = null;
        ultimo = null;
    }

    // n) Obtener valor por clave
    public Object obtener(Object clave) {
        return buscar(clave);
    }

    // o) Verificar si está vacía
    public boolean vacia() {
        return primero == null;
    }

    // p) Agregar otra lista
    public boolean agregarLista(ListaDinClave lista2) {
        if (lista2 == null || lista2.vacia()) {
            return true;
        }

        NodoClaveValor actual = lista2.primero;
        while (actual != null) {
            if (!poner(actual.getClave(), actual.getValor())) {
                return false;
            }
            actual = actual.getLigaDer();
        }
        return true;
    }

    // q) Obtener cantidad de elementos
    public int cantidad() {
        int contador = 0;
        NodoClaveValor actual = primero;
        while (actual != null) {
            contador++;
            actual = actual.getLigaDer();
        }
        return contador;
    }

    // r) Agregar desde arreglos paralelos
    public boolean agregarArreglo(Arreglo arregloClaves, Arreglo arregloValores) {
        if (arregloClaves == null || arregloValores == null ||
                arregloClaves.cantidad() != arregloValores.cantidad()) {
            return false;
        }

        for (int i = 0; i < arregloClaves.cantidad(); i++) {
            if (!poner(arregloClaves.obtener(i), arregloValores.obtener(i))) {
                return false;
            }
        }
        return true;
    }

    // s) Agregar desde listas dinámicas paralelas
    public boolean agregarListasDinamicas(ListaDin listaClaves, ListaDin listaValores) {
        if (listaClaves == null || listaValores == null) {
            return false;
        }

        if (listaClaves.cantidad() != listaValores.cantidad()) return false;
        ListaDin clavesCopia = (ListaDin) listaClaves.clonar();
        ListaDin valoresCopia = (ListaDin) listaValores.clonar();

        clavesCopia.iniciaIterador();
        valoresCopia.iniciaIterador();

        while (clavesCopia.iteradorValido() && valoresCopia.iteradorValido()) {
            if (!poner(clavesCopia.obtenerIterador(), valoresCopia.obtenerIterador())) {
                return false;
            }
            clavesCopia.moverIterador();
            valoresCopia.moverIterador();
        }

        return !clavesCopia.iteradorValido() && !valoresCopia.iteradorValido();
    }

    // t) Agregar desde matriz 2D
    public boolean agregarMatriz2D(Arreglo2D matriz) {
        if (matriz == null || matriz.obtenerColumnas() != 2) {
            return false;
        }

        for (int i = 0; i < matriz.obtenerRenglones(); i++) {
            Object clave = matriz.obtener(i, 0);
            Object valor = matriz.obtener(i, 1);
            if (clave == null || !poner(clave, valor)) {
                return false;
            }
        }
        return true;
    }
}