/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package colecciones.edlineal.arreglos;

import colecciones.interfaces.ListaDatos;
import colecciones.edlineal.listas.ListaDin;
import colecciones.interfaces.VectorFijo;
import colecciones.ednolineal.Arreglo2D;
import utils.entradasalida.cadenas.Salida;

/**
 *
 * @author Usuario
 */

//Clase Arreglo que implementa la interfaz VectorFijo
//Permite manejar un arreglo de objetos
public class Arreglo implements VectorFijo {
    protected int capacidad;
    protected Object datos[];
    protected int indiceSuperior;
    protected int indiceIterador = -1;
    
    //Constructor que inicializa el arreglo con un tamaño dado
    public Arreglo (int tamanio) {
        capacidad = tamanio;
        datos = new Object[capacidad];
        indiceSuperior = -1; // Índice superior en -1 (arreglo vacío)
    }
    
    //Verifica si el arreglo está vacío
    @Override
    public boolean vacia () {
        if (indiceSuperior == -1) {
            return true;
        }
        else {
            return false;
        }
    }
    
    //Verifica si el arreglo está lleno
    @Override
    public boolean lleno () {
        if (indiceSuperior == (capacidad -1 )) {
            return true;
        }
        else {
            return false;
        }
    }
    
    //Inserta un valor en el arreglo
    @Override
    public Integer poner(Object valor) {
        if (lleno() == false) {
            indiceSuperior++;
            datos[indiceSuperior] = valor;
            return indiceSuperior; // Devuelve la posición donde se insertó
        }
        else {
            return -1; // Devuelve -1 si el arreglo está lleno
        }
    }
    
    @Override
    public boolean poner(int indice, Object info) {
        if (validaPosicion(indice)) {
            datos[indice] = info;
            return true;
        }
        return false;
    }
    
    //Busca un valor en el arreglo y devuelve su posición
    @Override
    public Object buscar(Object valor) {
        int recorredor = 0;
        while (recorredor <= indiceSuperior && !iguales(valor, datos[recorredor])) {
            recorredor ++;
        }
        if (recorredor > indiceSuperior) {
            return -1; //Devuelve -1 si no se encuentra el valor
        }
        else {
            return recorredor; //Devuelve la posición del valor encontrado
        }
    }

    //Imprime los elementos del arreglo en orden ascendente
    @Override
    public void imprimir() {
        int posicion;
        for (posicion=0; posicion <= indiceSuperior; posicion ++) {
            Salida.salidaPorDefecto(datos[posicion]+"\n");
        }
    }
    
    //Imprime los elementos del arreglo en orden descendente
    @Override
    public void imprimirDes() {
        int posicion;
        for (posicion = indiceSuperior; posicion >= 0; posicion --) {
            Salida.salidaPorDefecto(datos[posicion]+"\n");
        }
    }
    
    //Elimina y devuelve el último elemento del arreglo
    @Override
    public Object quitar () {
        if (vacia () == false) {
            Object datoExtraido = datos [indiceSuperior];
            indiceSuperior --;
            return datoExtraido;
        }
        else {
            return null;
        }
    }

    // Elimina un valor específico del arreglo
    @Override
    public Object quitar(Object valor) {
        int posicion = (int) buscar (valor);
        if (posicion >= 0) {
            Object respaldo = datos [posicion];
            for (int modificacion = posicion; modificacion <= indiceSuperior-1; modificacion++) {
                datos [modificacion]=datos[modificacion+1];
            }
            indiceSuperior = indiceSuperior - 1;
            return respaldo;
        }
        else {
            return null;
        }
    }

    @Override
    public boolean cambiar(Object valorViejo, Object valorNuevo, int numVeces) {
        return modificar(valorViejo, valorNuevo, numVeces);
    }

    @Override
    public boolean cambiar(int indice, Object valor) {
        return modificar(indice, valor);
    }

    //devuelve la capacidad del arreglo
    @Override
    public int capacidad () {
        return capacidad;
    }
    
    //Devuelve cuántos datos hay en el arreglo
    @Override
    public int cantidad () {
        return indiceSuperior + 1;
    }
    
    //Valida si un índice está dentro del rango válido
    @Override
    public boolean validaPosicion (int indice) {
        if (indice >= 0 && indice <= indiceSuperior) {
            //Sí es válido el rango
            return true;
        }
        else {
            //No es válido el rango
            return false;
        }
    }
    
    //este método obtiene un objeto del arreglo
    //indicado por la posición del índice
    @Override
    public Object obtener (int indice) {
        if (validaPosicion (indice) == true) {
            //Sí existe esa posición
            return datos [indice];
        }
        else {
            //No existe la posición
            return null;
        }
    }
    
    // Devuelve el arreglo de datos
    public Object[] getDatos() {
        return datos;
    }
    
    //Este método indica si la lista actual
    //es igual a la lista2
    @Override
    public boolean esIgual (ListaDatos lista2) {
        if (lista2 instanceof Arreglo) {
            Arreglo arregloLista2 = (Arreglo) lista2;
            if (capacidad != arregloLista2.capacidad || indiceSuperior != arregloLista2.indiceSuperior) {
                return false;
            }
            for (int i = 0; i < cantidad(); i++) {
                if (!iguales(datos[i], arregloLista2.getDatos()[i])) {
                    return false;
                }
            }
        return true;
        }
        else {
            return false;
        }
    }
    
    //Este método modifica un valor viejo por uno nuevo
    //un número específico de veces
    @Override
    public boolean modificar(Object valorViejo, Object valorNuevo, int numVeces) {
        boolean modificado = false;
        int contador = 0;
        for (int i = 0; i <= indiceSuperior && contador < numVeces; i++) {
            if (iguales(datos[i], valorViejo)) {
                if (modificar(i, valorNuevo)) {
                    modificado = true;
                    contador++;
                }
            }
        }
        return modificado;
    }

    // Este método busca todas las ocurrencias de un valor
    //y devuelve un arreglo con sus posiciones
    @Override
    public Arreglo buscarValores(Object valor) {
        Arreglo posiciones = new Arreglo(capacidad);
        for (int i = 0; i <= indiceSuperior; i++) {
            if (iguales(datos[i], valor)) {
                posiciones.poner(i);
            }
        }
        return posiciones;
    }

    @Override
    public boolean agregarMatriz2D(Arreglo2D tabla, ListaDin.TipoTabla enumTipoTabla) {
        if (tabla == null || enumTipoTabla == null) return false;
        if ((long) tabla.obtenerRenglones() * tabla.obtenerColumnas() > capacidad - cantidad()) return false;
        if (enumTipoTabla == ListaDin.TipoTabla.RENGLON) {
            for (int i = 0; i < tabla.obtenerRenglones(); i++) {
                for (int j = 0; j < tabla.obtenerColumnas(); j++) if (poner(tabla.obtener(i, j)) < 0) return false;
            }
        } else {
            for (int j = 0; j < tabla.obtenerColumnas(); j++) {
                for (int i = 0; i < tabla.obtenerRenglones(); i++) if (poner(tabla.obtener(i, j)) < 0) return false;
            }
        }
        return true;
    }

    //Este método vacía el arreglo
    @Override
    public void vaciar() {
        indiceSuperior = -1;
        indiceIterador = -1;
    }

    @Override
    public void mostrarOI() {
        imprimirDes();
    }

    @Override
    public ListaDin encontrarLista(Object valor) {
        ListaDin resultado = new ListaDin();
        for (int i = 0; i < cantidad(); i++) if (iguales(valor, datos[i])) resultado.poner(datos[i]);
        return resultado;
    }

    @Override
    public Arreglo aListaEstatica() {
        Arreglo copia = new Arreglo(cantidad());
        for (int i = 0; i < cantidad(); i++) copia.poner(datos[i]);
        return copia;
    }

    @Override
    public Arreglo aListaEstatica(Arreglo elementosADescartar) {
        if (elementosADescartar == null) return null;
        Arreglo copia = new Arreglo(cantidad());
        for (int i = 0; i < cantidad(); i++) {
            if ((int) elementosADescartar.buscar(datos[i]) < 0) copia.poner(datos[i]);
        }
        return copia;
    }

    @Override
    public Arreglo2D aMatriz2D(int filas, int columnas) {
        if (filas < 0 || columnas < 0) return null;
        Arreglo2D matriz = new Arreglo2D(filas, columnas);
        int indice = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (indice < cantidad()) matriz.cambiar(i, j, datos[indice++]);
            }
        }
        return matriz;
    }

    //Este método agrega los elementos de otra lista al final del arreglo actual
    @Override
    public boolean agregarLista(ListaDatos lista2) {
        if (lista2 instanceof Arreglo) {
            Arreglo arregloLista2 = ((Arreglo) lista2).aListaEstatica();
            for (int i = 0; i < arregloLista2.cantidad(); i++) {
                if (lleno()) {
                    return false;
                }
                poner(arregloLista2.obtener(i));
            }
            return true;
        }
        return false;
    }

    //invierte el orden de los elementos del arreglo
    @Override
    public void invertir() {
        for (int i = 0; i < cantidad() / 2; i++) {
            Object temporal = datos[i];
            datos[i] = datos[indiceSuperior - i];
            datos[indiceSuperior - i] = temporal;
        }
    }

    //Este método cuenta las veces que aparece un valor en el arreglo
    @Override
    public int contar(Object valor) {
        int contador = 0;
        for (int i = 0; i <= indiceSuperior; i++) {
            if (iguales(datos[i], valor)) {
                contador++;
            }
        }
        return contador;
    }

    //Elimina todos los elementos de la lista2 que estén en el arreglo actual
    @Override
    public boolean borrarLista(ListaDatos lista2) {
        if (lista2 instanceof Arreglo) {
            Arreglo arregloLista2 = ((Arreglo) lista2).aListaEstatica();
            for (int i = 0; i < arregloLista2.cantidad(); i++) {
                quitar(arregloLista2.obtener(i));
            }
            return true;
        }
        return false;
    }

    // Rellena el arreglo con un valor dado hasta la cantidad indicada
    @Override
    public void rellenar(Object valor, int cantidad) {
        int limite = Math.min(Math.max(cantidad, 0), capacidad);
        for (int i = 0; i < limite; i++) {
            if (i <= indiceSuperior) modificar(i, valor);
            else if (poner(valor) < 0) break;
        }
    }

    //Este método devuelve un clon del arreglo actual
    @Override
    public ListaDatos clonar() {
        Arreglo clon = new Arreglo(capacidad);
        for (int i = 0; i <= indiceSuperior; i++) {
            clon.poner(datos[i]);
        }
        return clon;
    }

    //Devuelve una sublista con los elementos entre los índices inicial y final
    @Override
    public ListaDatos subLista(int indiceInicial, int indiceFinal) {
        if (validaPosicion(indiceInicial) && validaPosicion(indiceFinal) && indiceInicial <= indiceFinal) {
            Arreglo sublista = new Arreglo(indiceFinal - indiceInicial+1);
            for (int i = indiceInicial; i <= indiceFinal; i++) {
                sublista.poner(datos[i]);
            }
            return sublista;
        }
        return null;
    }

    //Substituye el contenido del arreglo actual con el de otra lista
    @Override
    public boolean substituir(ListaDatos lista2) {
        if (lista2 instanceof Arreglo) {
            Arreglo arregloLista2 = (Arreglo) lista2;
            datos = arregloLista2.getDatos();
            capacidad = arregloLista2.capacidad();
            indiceSuperior = arregloLista2.indiceSuperior;
            return true;
        }
        return false;
    }

    //Modifica el elemento en una posición dada
    @Override
    public boolean modificar(int indice, Object valor) {
        if (validaPosicion(indice)) {
            datos[indice] = valor;
            return true;
        }
        return false;
    }

    //Modifica los elementos del arreglo en las posiciones indicadas por indicesBusqueda
    @Override
    public boolean modificarLista(Arreglo indicesBusqueda, Arreglo valoresNuevos) {
        if (indicesBusqueda.cantidad() == valoresNuevos.cantidad()) {
            for (int i = 0; i < indicesBusqueda.cantidad(); i++) {
                int indice = (int) indicesBusqueda.obtener(i);
                if (!validaPosicion(indice)) {
                    return false;
                }
                if (!modificar(indice, valoresNuevos.obtener(i))) return false;
            }
            return true;
        }
        return false;
    }

    //Elimina y devuelve el elemento en una posición dada
    @Override
    public Object quitar(int indice) {
        if (validaPosicion(indice)) {
            Object respaldo = datos[indice];
            for (int i = indice; i < indiceSuperior; i++) {
                datos[i] = datos[i + 1];
            }
            indiceSuperior--;
            return respaldo;
        }
        return null;
    }

    //Redimensiona el arreglo al nuevo tamaño dado
    @Override
    public boolean redimensionar(int maximo) {
        if (maximo < 0) {
            return false;
        }
        Object[] nuevoArreglo = new Object[maximo];
        int elementosACopiar;
        if (maximo < indiceSuperior+1) { //Si el nuevo tamaño es menor, copia los elementos en el rango
            elementosACopiar = maximo;
        }
        else { //Si el nuevo tamaño es mayor o igual copia todos los elementos existentes
            elementosACopiar = indiceSuperior + 1;
        }
        for (int i = 0; i < elementosACopiar; i++) {
            nuevoArreglo[i] = datos[i];
        }
        datos = nuevoArreglo;
        capacidad = maximo;
        indiceSuperior = elementosACopiar - 1;
        return true;
    }
    
    //este método devuelve el promedio de los valores
    //del arreglo en caso de ser números
    @Override
    public Object promedio () {
        Object total = suma();
        return total == null ? null : ((Number) total).doubleValue() / cantidad();
    }
    
    //este método devuelve el mayor valor
    //del arreglo en caso de ser números
    @Override
    public Object encontrarMayor () {
        if (vacia() || !(datos[0] instanceof Number)) return null;
        double mochila = ((Number) datos[0]).doubleValue();
        for (int i = 1; i < cantidad(); i++) {
            if (!(datos[i] instanceof Number)) return null;
            if (mochila < ((Number) datos[i]).doubleValue()) mochila = ((Number) datos[i]).doubleValue();
        }
        return mochila;
    }
    
    //este método devuelve la suma
    //del arreglo en caso de ser números
    @Override
    public Object suma () {
        if (vacia()) return null;
        double acum = 0;
        for (int i = 0; i < cantidad(); i++) {
            if (!(datos[i] instanceof Number)) return null;
            acum += ((Number) datos[i]).doubleValue();
        }
        return acum; //No es número: se devuelve null antes de sumar.
    }

    @Override
    public void rellenar(Object valor) {
        rellenar(valor, capacidad);
    }

    @Override
    public ListaDatos arregloDesordenado() {
        Arreglo copia = aListaEstatica();
        for (int i = copia.cantidad() - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            Object temporal = copia.obtener(i);
            copia.modificar(i, copia.obtener(j));
            copia.modificar(j, temporal);
        }
        return copia;
    }

    @Override
    public boolean esSublista(ListaDatos lista2) {
        if (!(lista2 instanceof Arreglo)) return false;
        Arreglo lista = (Arreglo) lista2;
        for (int i = 0; i < lista.cantidad(); i++) if ((int) buscar(lista.obtener(i)) < 0) return false;
        return true;
    }

    @Override
    public boolean modificarLista(ListaDatos lista2, ListaDatos lista2Nuevos) {
        if (!(lista2 instanceof Arreglo) || !(lista2Nuevos instanceof Arreglo)) return false;
        Arreglo viejos = ((Arreglo) lista2).aListaEstatica();
        Arreglo nuevos = ((Arreglo) lista2Nuevos).aListaEstatica();
        if (viejos.cantidad() != nuevos.cantidad()) return false;
        boolean resultado = true;
        for (int i = 0; i < viejos.cantidad(); i++) {
            int indice = (int) buscar(viejos.obtener(i));
            if (indice < 0 || !modificar(indice, nuevos.obtener(i))) resultado = false;
        }
        return resultado;
    }

    @Override
    public boolean retenerLista(ListaDatos lista2) {
        if (!(lista2 instanceof Arreglo)) return false;
        Arreglo retenidos = ((Arreglo) lista2).aListaEstatica();
        for (int i = cantidad() - 1; i >= 0; i--) {
            if ((int) retenidos.buscar(datos[i]) < 0) quitar(i);
        }
        return true;
    }
    
    // Devuelve una sublista con los elementos indicados por listaIndices
    @Override
    public Arreglo subLista(ArregloNumerico listaIndices) {
        if (vacia() || listaIndices.vacia()) {
            return null;
        }
        Arreglo sublista = new Arreglo(listaIndices.cantidad());
        for (int i = 0; i < listaIndices.cantidad(); i++) {
            int indice = ((Number) listaIndices.obtener(i)).intValue();
            if (validaPosicion(indice)) {
                sublista.poner(datos[indice]);
            }
        }
        return sublista;
    }
    
    @Override
    public Object verFinal() {
        if(vacia()==false) {
            return datos[indiceSuperior];
        }
        else {
            return null;
        }
    }
    
    public Object[] leerArreglo() {
        Object[] copia = new Object[cantidad()]; // Usamos cantidad() en lugar de capacidad
        for (int i = 0; i < cantidad(); i++) {
            copia[i] = datos[i];
        }
        return copia;
    }

    @Override
    public void imprimirDesc() {
        imprimirDes();
    }

    @Override
    public Integer ponerInicio(Object valor) {
        if (lleno()) return -1;
        for (int i = cantidad(); i > 0; i--) datos[i] = datos[i - 1];
        datos[0] = valor;
        indiceSuperior++;
        return 0;
    }

    @Override
    public void iniciaIterador() {
        indiceIterador = 0;
    }

    @Override
    public boolean iteradorValido() {
        return indiceIterador >= 0 && indiceIterador < cantidad();
    }

    @Override
    public Object obtenerIterador() {
        return iteradorValido() ? datos[indiceIterador] : null;
    }

    @Override
    public void moverIterador() {
        if (iteradorValido()) indiceIterador++;
    }

    @Override
    public Object verInicio() {
        return vacia() ? null : datos[0];
    }
    protected static boolean iguales(Object a, Object b) {
        return a == null || b == null ? a == b : a.toString().equalsIgnoreCase(b.toString());
    }

}