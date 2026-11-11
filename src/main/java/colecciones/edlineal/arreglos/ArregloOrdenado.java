/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package colecciones.edlineal.arreglos;

import colecciones.interfaces.ListaDatos;
import utils.comunesbases.ManipuladorObjetos;

/**
 *
 * @author Usuario
 */
public class ArregloOrdenado extends Arreglo{
    
    public enum TipoOrdenamiento {
        INC, DEC
    }

    private TipoOrdenamiento orden;

    public ArregloOrdenado(int maximo, TipoOrdenamiento orden) {
        super(maximo);
        this.orden = orden;
    }

    @Override
    public Object buscar(Object valor) {
        if (valor == null) return -1;
        int posicionB = 0;
        while (posicionB <= indiceSuperior && compararEnOrden(valor, datos[posicionB]) > 0) {
            posicionB = posicionB + 1;
        }
        if (posicionB > indiceSuperior || compararEnOrden(valor, datos[posicionB]) != 0) {
            return (posicionB + 1) * (-1);
        } else { // igual, si se encontró
            return posicionB + 1;
        }
    }

    @Override
    public Integer poner(Object valor) {
        if (valor == null) return -1;
        if (lleno() == false) { // hay espacio
            int posicionEncontrado = (int) buscar(valor);
            if (posicionEncontrado < 0) {
                // no existe
                posicionEncontrado = posicionEncontrado * (-1);
                posicionEncontrado = posicionEncontrado - 1;
                for (int posMod = (indiceSuperior + 1); posMod >= (posicionEncontrado + 1); posMod--) {
                    datos[posMod] = datos[posMod - 1];
                }
                datos[posicionEncontrado] = valor;
                indiceSuperior = indiceSuperior + 1;
                return posicionEncontrado;
            } else { // si existe
                return -1;
            }
        } else {
            return -1;
        }
    }

    @Override
    public boolean modificar(int indice, Object valor) {
        if (!validaPosicion(indice) || valor == null) return false;
        int existente = (int) buscar(valor);
        if (existente > 0) return existente - 1 == indice;
        super.quitar(indice);
        return poner(valor) >= 0;
    }

    @Override
    public Object quitar(Object valor) {
        if (vacia()) {
        return null;
        }
        
        int resultadoBusqueda = (int) buscar(valor);
        if (resultadoBusqueda > 0) {
            // El valor se encontró
            int posicion = resultadoBusqueda - 1;
            Object respaldo = datos[posicion];

            // Desplazar los elementos hacia la izquierda para eliminar el valor
            for (int i = posicion; i < indiceSuperior; i++) {
                datos[i] = datos[i + 1];
            }

            indiceSuperior--;
            return respaldo;
        }

        // El valor no se encontró
        return null;
    }

    @Override
    public boolean agregarLista(ListaDatos lista2) {
        if (!(lista2 instanceof Arreglo)) return false;
        Arreglo arregloLista2 = ((Arreglo) lista2).aListaEstatica();
        boolean completo = true;
        for (int i = 0; i < arregloLista2.cantidad(); i++) {
            Object valor = arregloLista2.obtener(i);
            if ((int) buscar(valor) < 0 && poner(valor) < 0) completo = false;
        }
        return completo;
    }

    @Override
    public void invertir() {
        for (int i = 0; i < cantidad() / 2; i++) {
            Object temp = datos[i];
            datos[i] = datos[indiceSuperior - i];
            datos[indiceSuperior - i] = temp;
        }
        if (orden == TipoOrdenamiento.INC) {
            orden = TipoOrdenamiento.DEC;
        } else {
            orden = TipoOrdenamiento.INC;
        }
    }

    @Override
    public void rellenar(Object valor) {
        if (valor instanceof Number) {
            // Caso 1: Valor es numérico; generar la secuencia respetando el orden y la capacidad.
            double limite = ((Number) valor).doubleValue();
            if (!Double.isFinite(limite) || limite != Math.rint(limite) || limite < Integer.MIN_VALUE || limite > Integer.MAX_VALUE || limite == 0) return;
            long inicio = limite > 0 ? 1 : (long) limite;
            long fin = limite > 0 ? (long) limite : -1;
            long paso = 1;
            if (orden == TipoOrdenamiento.DEC) { long temporal = inicio; inicio = fin; fin = temporal; paso = -1; }
            for (long n = inicio; !lleno() && (paso > 0 ? n <= fin : n >= fin); n += paso) poner((int) n);
        } else if (valor instanceof Character) {
            // Caso 2: Valor es una letra.
            char letra = Character.toUpperCase((Character) valor);
            if (letra < 'A' || letra > 'Z') return;
            if (orden == TipoOrdenamiento.INC) {
                for (char c = 'A'; c <= letra && !lleno(); c++) poner(c);
            } else {
                for (char c = letra; c >= 'A' && !lleno(); c--) poner(c);
            }
        } else if (valor instanceof String) {
            // Caso 3: Valor es una cadena.
            if (!((String) valor).isEmpty()) poner(valor);
        } else {
            // Caso 4: Valor es otro tipo de objeto.
            poner(valor);
        }
    }

    @Override
    public ListaDatos arregloDesordenado() {
        Arreglo desordenado = new Arreglo(capacidad);
        for (int i = 0; i <= indiceSuperior; i++) {
            desordenado.poner(datos[i]);
        }
        // Mezclar los elementos
        for (int i = 0; i <= indiceSuperior; i++) {
            int j = (int) (Math.random() * (indiceSuperior + 1));
            Object temp = desordenado.obtener(i);
            desordenado.modificar(i, desordenado.obtener(j));
            desordenado.modificar(j, temp);
        }
        return desordenado;
    }

    @Override
    public boolean esSublista(ListaDatos lista2) {
        if (lista2 instanceof Arreglo) {
            Arreglo arregloLista2 = (Arreglo) lista2;
            for (int i = 0; i < arregloLista2.cantidad(); i++) {
                if ((int)buscar(arregloLista2.obtener(i)) < 0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean modificarLista(ListaDatos lista2, ListaDatos lista2Nuevos) {
        if (!(lista2 instanceof Arreglo) || !(lista2Nuevos instanceof Arreglo)) return false;
        Arreglo viejos = ((Arreglo) lista2).aListaEstatica();
        Arreglo nuevos = ((Arreglo) lista2Nuevos).aListaEstatica();
        if (viejos.cantidad() != nuevos.cantidad()) return false;
        Arreglo candidato = aListaEstatica();
        for (int i = 0; i < viejos.cantidad(); i++) {
            int posicion = (int) buscar(viejos.obtener(i));
            if (posicion < 0) return false;
            candidato.modificar(posicion - 1, nuevos.obtener(i));
        }
        return reemplazarOrdenado(candidato);
    }

    private int compararEnOrden(Object a, Object b) {
        return orden == TipoOrdenamiento.INC
                ? ManipuladorObjetos.compararObjetos(a, b)
                : ManipuladorObjetos.compararObjetos(b, a);
    }

    @Override
    public Integer ponerInicio(Object valor) { return poner(valor); }

    @Override
    public boolean poner(int indice, Object valor) { return modificar(indice, valor); }

    @Override
    public void rellenar(Object valor, int cantidad) {
        if (cantidad > 0 && (int) buscar(valor) < 0) poner(valor);
    }

    @Override
    public boolean modificar(Object valorViejo, Object valorNuevo, int numVeces) {
        int posicion = (int) buscar(valorViejo);
        return numVeces > 0 && posicion > 0 && modificar(posicion - 1, valorNuevo);
    }

    @Override
    public boolean modificarLista(Arreglo indicesBusqueda, Arreglo valoresNuevos) {
        if (indicesBusqueda == null || valoresNuevos == null || indicesBusqueda.cantidad() != valoresNuevos.cantidad()) return false;
        Arreglo candidato = aListaEstatica();
        for (int i = 0; i < indicesBusqueda.cantidad(); i++) {
            Object indice = indicesBusqueda.obtener(i);
            if (!(indice instanceof Integer) || !validaPosicion((Integer) indice)) return false;
            candidato.modificar((Integer) indice, valoresNuevos.obtener(i));
        }
        return reemplazarOrdenado(candidato);
    }

    @Override
    public boolean substituir(ListaDatos lista2) {
        if (!(lista2 instanceof Arreglo)) return false;
        return reemplazarOrdenado(((Arreglo) lista2).aListaEstatica());
    }

    private boolean reemplazarOrdenado(Arreglo candidato) {
        if (candidato.cantidad() > capacidad) return false;
        ArregloOrdenado temporal = new ArregloOrdenado(capacidad, orden);
        for (int i = 0; i < candidato.cantidad(); i++) {
            if (temporal.poner(candidato.obtener(i)) < 0) return false;
        }
        // Publicar solo si todos los valores respetan la unicidad y la capacidad.
        datos = temporal.datos;
        indiceSuperior = temporal.indiceSuperior;
        indiceIterador = -1;
        return true;
    }
}