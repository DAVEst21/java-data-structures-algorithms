/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package colecciones.ednolineal;

import colecciones.edlineal.arreglos.Arreglo;
import utils.entradasalida.cadenas.Salida;

/**
 *
 * @author Usuario
 */
public class Arreglo2D {

    protected int renglones; //Cantidad de filas
    protected int columnas; //Cantidad de columnas
    protected Object[][] datos; //Arreglo bidimensional

    // 1. Constructor
    public Arreglo2D(int renglones, int columnas) {
        this.renglones = renglones;
        this.columnas = columnas;
        this.datos = new Object[renglones][columnas];
    }

    // 2. Constructor con valor inicial
    public Arreglo2D(int renglones, int columnas, Object valor) {
        this(renglones, columnas);
        rellenar(valor);
    }

    // 3. Obtener elemento
    public Object obtener(int fila, int col) {
        if (validarIndice(fila, col)) {
            return datos[fila][col];
        }
        return null;
    }

    // 4. Cambiar elemento
    public boolean cambiar(int fila, int col, Object valor) {
        if (validarIndice(fila, col)) {
            datos[fila][col] = valor;
            return true;
        }
        return false;
    }

    // 5. Obtener dimensiones
    public int obtenerRenglones() {
        return renglones;
    }

    public int obtenerColumnas() {
        return columnas;
    }

    // 6. Imprimir por renglones
    public void imprimirXRenglones() {
        //Recorrer cada fila
        for (int i = 0; i < renglones; i++) {
            //Recorrer cada columna de la fila actual
            for (int j = 0; j < columnas; j++) {
                Salida.salidaPorDefecto(datos[i][j] + " ");
            }
            //Salto de línea
            Salida.salidaPorDefecto("\n");
        }
    }

    // 7. Imprimir por columnas
    public void imprimirXColumnas() {
        //Recorrer cada columna
        for (int j = 0; j < columnas; j++) {
            //recorrer cada fila de la columna actual
            for (int i = 0; i < renglones; i++) {
                Salida.salidaPorDefecto(datos[i][j] + " ");
            }
            //Salto de línea
            Salida.salidaPorDefecto("\n");
        }
    }

    // 8. Transpuesta
    public void transpuesta() {
        //Crear nueva matriz con dimensiones invertidas
        Object[][] nueva = new Object[columnas][renglones];
        //Intercambiar filas por columnas
        for (int i = 0; i < renglones; i++) {
            for (int j = 0; j < columnas; j++) {
                nueva[j][i] = datos[i][j];
            }
        }
        //Actualizar atributos
        datos = nueva;
        int temp = renglones;
        renglones = columnas;
        columnas = temp;
    }

    // 9. Rellenar matriz
    public void rellenar(Object valor) {
        //Recorrer todas las filas
        for (int i = 0; i < renglones; i++) {
            //Recorrer todas las columnas de cada fila
            for (int j = 0; j < columnas; j++) {
                datos[i][j] = valor;
            }
        }
    }

    // 10. Clonar matriz
    public Arreglo2D clonar() {
        Arreglo2D copia = new Arreglo2D(renglones, columnas);
        //Copiar cada elemento
        for (int i = 0; i < renglones; i++) {
            for (int j = 0; j < columnas; j++) {
                copia.datos[i][j] = datos[i][j];
            }
        }
        return copia;
    }

    // 11. Comparar matrices
    public boolean esIgual(Arreglo2D matriz2) {
        //Verificar dimensiones primero
        if (renglones != matriz2.renglones || columnas != matriz2.columnas) {
            return false;
        }
        //Comparar cada elemento
        for (int i = 0; i < renglones; i++) {
            for (int j = 0; j < columnas; j++) {
                if (!datos[i][j].equals(matriz2.datos[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    // 12. Vector columna
    public boolean vectorColumna(int filas, Object info) {
        //La matriz debe ser de una sola columna y tener las filas especificadas
        if (columnas != 1 || renglones != filas) {
            return false;
        }
        //Llenar todas las celdas con el valor específicado
        for (int i = 0; i < filas; i++) {
            datos[i][0] = info;
        }
        return true;
    }

    // 13. Vector renglón
    public boolean vectorRenglon(int columnas, Object info) {
        //La matriz debe ser de un solo renglón y tener las columnas especificadas
        if (renglones != 1 || this.columnas != columnas) {
            return false;
        }
        //Llenar todas las celdas con el valor especificado
        for (int j = 0; j < columnas; j++) {
            datos[0][j] = info;
        }
        return true;
    }

    // 14. Redefinir matriz
    public boolean redefinir(Arreglo2D matriz2) {
        this.renglones = matriz2.renglones;
        this.columnas = matriz2.columnas;
        this.datos = matriz2.datos;
        return true;
    }

    // 15. Agregar renglón
    public boolean agregarRenglon(Arreglo arreglo) {
        //El arreglo debe tener la misma cantidad de elementos que columnas tiene la matriz
        if (arreglo.cantidad() != columnas) {
            return false;
        }
        
        //Crear nueva matriz con un renglón adicional
        Object[][] nueva = new Object[renglones + 1][columnas];
        // Copiar datos existentes
        for (int i = 0; i < renglones; i++) {
            for (int j = 0; j < columnas; j++) {
                nueva[i][j] = datos[i][j];
            }
        }
        //Agregar nuevo renglón
        for (int j = 0; j < columnas; j++) {
            nueva[renglones][j] = arreglo.obtener(j);
        }
        //Actualizar atributos
        datos = nueva;
        renglones++;
        return true;
    }

    // 16. Agregar columna
    public boolean agregarColumna(Arreglo arreglo) {
        //El arreglo debe tener la misma cantidad de elementos que renglones tiene la matriz
        if (arreglo.cantidad() != renglones) {
            return false;
        }
        
        //Crear nueva matriz con una columna adicional
        Object[][] nueva = new Object[renglones][columnas + 1];
        for (int i = 0; i < renglones; i++) {
            // Copiar datos existentes
            for (int j = 0; j < columnas; j++) {
                nueva[i][j] = datos[i][j];
            }
            // Agregar nueva columna
            nueva[i][columnas] = arreglo.obtener(i);
        }
        //Actualizar atributos
        datos = nueva;
        columnas++;
        return true;
    }

    // 17. Agregar matriz por columnas
    public boolean agregarMatrizXColumna(Arreglo2D matriz2) {
        //Las matrices deben tener el mismo número de filas
        if (matriz2.renglones != renglones) {
            return false;
        }

        Object[][] nueva = new Object[renglones][columnas + matriz2.columnas];
        for (int i = 0; i < renglones; i++) {
            //Copiar datos originales
            for (int j = 0; j < columnas; j++) {
                nueva[i][j] = datos[i][j];
            }
            //Copiar datos de la nueva matriz
            for (int j = 0; j < matriz2.columnas; j++) {
                nueva[i][columnas + j] = matriz2.datos[i][j];
            }
        }
        datos = nueva;
        columnas += matriz2.columnas;
        return true;
    }

    // 18. Agregar matriz por renglones
    public boolean agregarMatrizXRenglon(Arreglo2D matriz2) {
        //Las matrices deben tener el mismo número de columnas
        if (matriz2.columnas != columnas) {
            return false;
        }
        
        //Matriz con filas adicionales
        Object[][] nueva = new Object[renglones + matriz2.renglones][columnas];
        //Copiar datos originales
        for (int i = 0; i < renglones; i++) {
            for (int j = 0; j < columnas; j++) {
                nueva[i][j] = datos[i][j];
            }
        }
        //Copiar datos de la nueva matriz
        for (int i = 0; i < matriz2.renglones; i++) {
            for (int j = 0; j < columnas; j++) {
                nueva[renglones + i][j] = matriz2.datos[i][j];
            }
        }
        //Actualizar atributos
        datos = nueva;
        renglones += matriz2.renglones;
        return true;
    }

    // 19. Convertir a matriz 3D
    public Arreglo3D aMatriz3(Arreglo matrices) {
        //Profundad será cantidad de matrices + 1
        int profundo = matrices.cantidad() + 1; // +1 para la matriz actual
        Arreglo3D matriz3D = new Arreglo3D(renglones, columnas, profundo);

        // Copiar matriz actual como primera capa
        for (int i = 0; i < renglones; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz3D.cambiarCelda(i, j, 0, datos[i][j]);
            }
        }

        // Copiar matrices adicionales como nueva capa
        for (int k = 1; k < profundo; k++) {
            Arreglo2D mat = (Arreglo2D) matrices.obtener(k - 1);
            //Verificar compatibilidad de dimensiones
            if (mat.renglones != renglones || mat.columnas != columnas) {
                return null; // Dimensiones no coinciden
            }
            //Copiar los datos de la matriz acual a la capa k
            for (int i = 0; i < renglones; i++) {
                for (int j = 0; j < columnas; j++) {
                    matriz3D.cambiarCelda(i, j, k, mat.datos[i][j]);
                }
            }
        }
        return matriz3D;
    }

    // 20. Convertir a vector columna
    public Arreglo2D aVectorColumna() {
        //Crear matriz con una sola columna y suficientes filas para todos los elementos
        Arreglo2D vector = new Arreglo2D(renglones * columnas, 1);
        int index = 0;
        //Recorrer por columnas primero para mantener orden
        for (int j = 0; j < columnas; j++) {
            for (int i = 0; i < renglones; i++) {
                vector.datos[index++][0] = datos[i][j];
            }
        }
        return vector;
    }

    // 21. Convertir a vector renglón
    public Arreglo2D aVectorRenglon() {
        //Crear matriz con un solo renglón y suficientes columnas para todos los elementos
        Arreglo2D vector = new Arreglo2D(1, renglones * columnas);
        int index = 0;
        //Recorrer por renglones primero para mantener orden
        for (int i = 0; i < renglones; i++) {
            for (int j = 0; j < columnas; j++) {
                vector.datos[0][index++] = datos[i][j];
            }
        }
        return vector;
    }

    // 22. Eliminar columna (izquierda/derecha)
    public boolean quitarColumna(TipoColumna tipoCol) {
        if (columnas <= 1) {
            return false;
        }
        //Crear nueva matriz con una columna menos
        Object[][] nueva = new Object[renglones][columnas - 1];
        //Determinar qué columna se eliminará
        int colEliminar;
        if (tipoCol == TipoColumna.IZQUIERDA) {
            colEliminar = 0; //Eliminar la primera columna
        } else {
            colEliminar = columnas - 1; //Eliminar la última columna
        }

        // Copiar todas las columnas menos la que se elimina
        for (int i = 0; i < renglones; i++) {
            for (int j = 0, k = 0; j < columnas; j++) {
                if (j != colEliminar) {
                    nueva[i][k] = datos[i][j];
                    k++;
                }
            }
        }
        //Actualizar atributos
        datos = nueva;
        columnas--;
        return true;
    }

    // 23. Eliminar fila (superior/inferior)
    public boolean quitarRenglon(TipoRenglon tipoRenglon) {
        if (renglones <= 1) {
            return false;
        }
        //Crear nueva matriz con un renglón menos
        Object[][] nueva = new Object[renglones - 1][columnas];
        //Determinar qué renglón se eliminará
        int filaEliminar;
        if (tipoRenglon == TipoRenglon.SUPERIOR) {
            filaEliminar = 0; //Eliminar la primera fila
        } else {
            filaEliminar = renglones - 1; //Eliminar la última fila
        }

        //Copiar todos los renglones menos el que se eliminará
        for (int i = 0, k = 0; i < renglones; i++) {
            if (i != filaEliminar) {
                for (int j = 0; j < columnas; j++) {
                    nueva[k][j] = datos[i][j];
                }
                k++;
            }
        }
        //Actualizar atributos
        datos = nueva;
        renglones--;
        return true;
    }

    // 24. Eliminar fila específica
    public boolean eliminarRenglon(int renglon) {
        //Verificar que el índice sea válido
        if (!validarIndice(renglon, 0)) {
            return false;
        }
        //Crear nueva matriz con un renglón menos
        Object[][] nueva = new Object[renglones - 1][columnas];
        
        //Copiar todos los renglones menos el que se elimina
        for (int i = 0, k = 0; i < renglones; i++) {
            if (i != renglon) {
                for (int j = 0; j < columnas; j++) {
                    nueva[k][j] = datos[i][j];
                }
                k++;
            }
        }
        datos = nueva;
        renglones--;
        return true;
    }

    // 25. Eliminar columna específica
    public boolean eliminarColumna(int columna) {
        //Verificar que el índice existe
        if (!validarIndice(0, columna)) {
            return false;
        }
        //Crear nueva matriz con una columna menos
        Object[][] nueva = new Object[renglones][columnas - 1];
        //Copiar todas las columnas menos la que se eliminará
        for (int i = 0; i < renglones; i++) {
            for (int j = 0, k = 0; j < columnas; j++) {
                if (j != columna) {
                    nueva[i][k++] = datos[i][j];
                }
            }
        }
        //Actualizar atributos
        datos = nueva;
        columnas--;
        return true;
    }

    // Método auxiliar para validar índices
    private boolean validarIndice(int fila, int col) {
        return fila >= 0 && fila < renglones
                && col >= 0 && col < columnas;
    }

    // Enumerados
    public enum TipoColumna {
        IZQUIERDA, DERECHA
    }

    public enum TipoRenglon {
        SUPERIOR, INFERIOR
    }
}
