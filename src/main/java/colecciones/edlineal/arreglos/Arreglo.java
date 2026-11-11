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
        while (recorredor <= indiceSuperior && valor.toString().equalsIgnoreCase(datos[recorredor].toString()) == false) {
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
        return false;
    }

    @Override
    public boolean cambiar(int indice, Object valor) {
        return false;
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
            for (int i = 0; i < datos.length; i++) {
                if (datos[i] != arregloLista2.getDatos()[i]) {
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
            if (datos[i].toString().equalsIgnoreCase(valorViejo.toString())) {
                datos[i] = valorNuevo;
                modificado = true;
                contador++;
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
            if (datos[i].toString().equalsIgnoreCase(valor.toString())) {
                posiciones.poner(i);
            }
        }
        return posiciones;
    }

    @Override
    public boolean agregarMatriz2D(Arreglo2D tabla, ListaDin.TipoTabla enumTipoTabla) {
        return false;
    }

    //Este método vacía el arreglo
    @Override
    public void vaciar() {
        indiceSuperior = -1;
    }

    @Override
    public void mostrarOI() {

    }

    @Override
    public ListaDin encontrarLista(Object valor) {
        return null;
    }

    @Override
    public Arreglo aListaEstatica() {
        return null;
    }

    @Override
    public Arreglo aListaEstatica(Arreglo elementosADescartar) {
        return null;
    }

    @Override
    public Arreglo2D aMatriz2D(int filas, int columnas) {
        return null;
    }

    //Este método agrega los elementos de otra lista al final del arreglo actual
    @Override
    public boolean agregarLista(ListaDatos lista2) {
        if (lista2 instanceof Arreglo) {
            Arreglo arregloLista2 = (Arreglo) lista2;
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
        for (int i = 0; i <= indiceSuperior / 2; i++) {
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
            if (datos[i].toString().equalsIgnoreCase(valor.toString())) {
                contador++;
            }
        }
        return contador;
    }

    //Elimina todos los elementos de la lista2 que estén en el arreglo actual
    @Override
    public boolean borrarLista(ListaDatos lista2) {
        if (lista2 instanceof Arreglo) {
            Arreglo arregloLista2 = (Arreglo) lista2;
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
        for (int i = 0; i < cantidad && i < capacidad; i++) {
            datos[i] = valor;
        }
        if (cantidad > indiceSuperior + 1) {
            indiceSuperior = cantidad - 1;
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
                datos[indice] = valoresNuevos.obtener(i);
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
        int i = 0;
        if (datos[i] instanceof Number) {
            double acum = 0;
            for (i = 0; i <= indiceSuperior; i++) {
                acum = acum + (double) datos [i];
            }
            double promedio = acum / cantidad();
            return promedio;
        }
        return null; //No es número
    }
    
    //este método devuelve el mayor valor
    //del arreglo en caso de ser números
    @Override
    public Object encontrarMayor () {
        int i = 0;
        if (datos[i] instanceof Number) {
            double mochila = 0;
            for (i = 0; i <= indiceSuperior; i++) {
                if (mochila < (double)datos[i]) {
                    mochila = (double) datos[i];
                }
            }
            return mochila;
        }
        return null; //No es número
    }
    
    //este método devuelve la suma
    //del arreglo en caso de ser números
    @Override
    public Object suma () {
        int i = 0;
        if (datos[i] instanceof Number) {
            double acum = 0;
            for (i = 0; i <= indiceSuperior; i++) {
                acum = acum + (double) datos[i];
            }
            return acum;
        }
        return null; //No es número
    }

    @Override
    public void rellenar(Object valor) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ListaDatos arregloDesordenado() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean esSublista(ListaDatos lista2) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean modificarLista(ListaDatos lista2, ListaDatos lista2Nuevos) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean retenerLista(ListaDatos lista2) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

    }

    @Override
    public Integer ponerInicio(Object valor) {
        return 0;
    }

    @Override
    public void iniciaIterador() {

    }

    @Override
    public boolean iteradorValido() {
        return false;
    }

    @Override
    public Object obtenerIterador() {
        return null;
    }

    @Override
    public void moverIterador() {

    }

    @Override
    public Object verInicio() {
        return null;
    }
}