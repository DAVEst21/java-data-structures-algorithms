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
        int posicionB = 0;
        while (posicionB <= indiceSuperior && ManipuladorObjetos.compararObjetos(valor, datos[posicionB]) > 0) {
            posicionB = posicionB + 1;
        }
        if (posicionB > indiceSuperior || ManipuladorObjetos.compararObjetos(valor, datos[posicionB]) < 0) {
            return (posicionB + 1) * (-1);
        } else { // igual, si se encontró
            return posicionB + 1;
        }
    }

    @Override
    public Integer poner(Object valor) {
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
        if (validaPosicion(indice)) {
            quitar(datos[indice]);
            poner(valor);
            return true;
        }
        return false;
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
        if (lista2 instanceof Arreglo) {
            Arreglo arregloLista2 = (Arreglo) lista2;
            for (int i = 0; i < arregloLista2.cantidad(); i++) {
                Object valor = arregloLista2.obtener(i);
                if ((int) buscar(valor) < 0) { // No existe, se puede insertar
                    poner(valor);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void invertir() {
        for (int i = 0; i <= indiceSuperior / 2; i++) {
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
            // Caso 1: Valor es numérico
            double limite = ((Number) valor).doubleValue();
            if (limite > 0) {
                if (orden == TipoOrdenamiento.INC) {
                    // Rellenar de 1 a límite (orden incremental)
                    for (int i = 1; i <= limite && i <= capacidad; i++) {
                        datos[indiceSuperior + 1] = i;
                        indiceSuperior++;
                    }
                } else {
                    // Rellenar de límite a 1 (orden decremental)
                    for (int i = (int) limite; i >= 1 && i <= capacidad; i--) {
                        datos[indiceSuperior + 1] = i;
                        indiceSuperior++;
                    }
                }
            } else if (limite < 0) {
                if (orden == TipoOrdenamiento.INC) {
                    // Rellenar de -6 a -1 (orden incremental)
                    for (int i = (int) limite; i <= -1 && i >= -capacidad; i++) {
                        datos[indiceSuperior + 1] = i;
                        indiceSuperior++;
                    }
                } else {
                    // Rellenar de -1 a -6 (orden decremental)
                    for (int i = -1; i >= limite && i >= -capacidad; i--) {
                        datos[indiceSuperior + 1] = i;
                        indiceSuperior++;
                    }
                }
            }
        } else if (valor instanceof Character) {
            // Caso 2: Valor es una letra
            char letra = Character.toUpperCase((Character) valor);
            if (Character.isLetter(letra)) {
                if (orden == TipoOrdenamiento.INC) {
                    // Rellenar de A a letra (orden incremental)
                    for (char c = 'A'; c <= letra && c <= 'Z'; c++) {
                        datos[indiceSuperior + 1] = c;
                        indiceSuperior++;
                    }
                } else {
                    // Rellenar de letra a A (orden decremental)
                    for (char c = letra; c >= 'A' && c <= 'Z'; c--) {
                        datos[indiceSuperior + 1] = c;
                        indiceSuperior++;
                    }
                }
            }
        } else if (valor instanceof String) {
            // Caso 3: Valor es una cadena
            String cadena = (String) valor;
            if (!cadena.isEmpty()) {
                datos[indiceSuperior + 1] = cadena;
                indiceSuperior++;
            }
        } else {
            // Caso 4: Valor es otro tipo de objeto
            datos[indiceSuperior + 1] = valor;
            indiceSuperior++;
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
        if (lista2 instanceof Arreglo && lista2Nuevos instanceof Arreglo) {
            Arreglo arregloLista2 = (Arreglo) lista2;
            Arreglo arregloLista2Nuevos = (Arreglo) lista2Nuevos;
            for (int i = 0; i < arregloLista2.cantidad(); i++) {
                Object valorViejo = arregloLista2.obtener(i);
                Object valorNuevo = arregloLista2Nuevos.obtener(i);
                int posicion = (int) buscar(valorViejo);
                if (posicion >= 0) {
                    modificar(posicion, valorNuevo);
                }
            }
            return true;
        }
        return false;
    }
}