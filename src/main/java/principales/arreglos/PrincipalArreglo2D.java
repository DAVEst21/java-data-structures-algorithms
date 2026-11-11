/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principales.arreglos;

import colecciones.edlineal.arreglos.Arreglo;
import colecciones.ednolineal.Arreglo2D;
import colecciones.ednolineal.Arreglo3D;
import utils.entradasalida.cadenas.Salida;

/**
 *
 * @author Usuario
 */
public class PrincipalArreglo2D {

    public static void main(String[] args) {
        // 1. Constructor basico
        Salida.salidaPorDefecto("\n1. Constructor\n");
        Arreglo2D matriz1 = new Arreglo2D(2, 3);
        matriz1.imprimirXRenglones();

        // 2. Constructor con valor inicial
        Salida.salidaPorDefecto("\n2. Constructor con valor inicial\n");
        Arreglo2D matriz2 = new Arreglo2D(2, 2, "A");
        matriz2.imprimirXRenglones();

        // 3. Obtener elemento
        Salida.salidaPorDefecto("\n3. Obtener elemento\n");
        matriz2.cambiar(0, 1, "B");
        Salida.salidaPorDefecto("Elemento (0,1): " + matriz2.obtener(0, 1) + "\n");

        // 4. Cambiar elemento
        Salida.salidaPorDefecto("\n4. Cambiar elemento\n");
        matriz2.cambiar(1, 0, "C");
        matriz2.imprimirXRenglones();

        // 5. Obtener dimensiones
        Salida.salidaPorDefecto("\n5. Obtener dimensiones\n");
        Salida.salidaPorDefecto("Renglones: " + matriz2.obtenerRenglones() + "\n");
        Salida.salidaPorDefecto("Columnas: " + matriz2.obtenerColumnas() + "\n");

        // 6. Imprimir por renglones
        Salida.salidaPorDefecto("\n6. Imprimir por renglones\n");
        matriz2.imprimirXRenglones();

        // 7. Imprimir por columnas
        Salida.salidaPorDefecto("\n7. Imprimir por columnas\n");
        matriz2.imprimirXColumnas();

        // 8. Transpuesta
        Salida.salidaPorDefecto("\n8. Transpuesta\n");
        matriz2.transpuesta();
        matriz2.imprimirXRenglones();

        // 9. Rellenar matriz
        Salida.salidaPorDefecto("\n9. Rellenar matriz\n");
        matriz2.rellenar("X");
        matriz2.imprimirXRenglones();

        // 10. Clonar matriz
        Salida.salidaPorDefecto("\n10. Clonar matriz\n");
        Arreglo2D copia = matriz2.clonar();
        copia.cambiar(0, 0, "Y");
        Salida.salidaPorDefecto("Original:\n");
        matriz2.imprimirXRenglones();
        Salida.salidaPorDefecto("Copia modificada:\n");
        copia.imprimirXRenglones();

        // 11. Comparar matrices
        Salida.salidaPorDefecto("\n11. Comparar matrices\n");
        Salida.salidaPorDefecto("¿Son iguales?: " + matriz2.esIgual(copia) + "\n");

        // 12. Vector columna
        Salida.salidaPorDefecto("\n12. Vector columna\n");
        Arreglo2D vectorCol = new Arreglo2D(3, 1);
        vectorCol.vectorColumna(3, "COL");
        vectorCol.imprimirXRenglones();

        // 13. Vector renglon
        Salida.salidaPorDefecto("\n13. Vector renglon\n");
        Arreglo2D vectorRen = new Arreglo2D(1, 4);
        vectorRen.vectorRenglon(4, "REN");
        vectorRen.imprimirXRenglones();

        // 14. Redefinir matriz
        Salida.salidaPorDefecto("\n14. Redefinir matriz\n");
        Arreglo2D nuevaMat = new Arreglo2D(2, 2, "N");
        matriz2.redefinir(nuevaMat);
        matriz2.imprimirXRenglones();

        // 15. Agregar renglon
        Salida.salidaPorDefecto("\n15. Agregar renglon\n");
        Arreglo datosRen = new Arreglo(2);
        datosRen.poner("R1");
        datosRen.poner("R2");
        nuevaMat.agregarRenglon(datosRen);
        nuevaMat.imprimirXRenglones();

        // 16. Agregar columna
        Salida.salidaPorDefecto("\n16. Agregar columna\n");
        Arreglo datosCol = new Arreglo(3);
        datosCol.poner("C1");
        datosCol.poner("C2");
        datosCol.poner("C3");
        nuevaMat.agregarColumna(datosCol);
        nuevaMat.imprimirXRenglones();

        // 17. Agregar matriz por columnas
        Salida.salidaPorDefecto("\n17. Agregar matriz por columnas\n");
        Arreglo2D matExt = new Arreglo2D(3, 2);
        matExt.rellenar("EXT");
        nuevaMat.agregarMatrizXColumna(matExt);
        nuevaMat.imprimirXRenglones();

        // 18. Agregar matriz por renglones
        Salida.salidaPorDefecto("\n18. Agregar matriz por renglones\n");
        Arreglo2D matAbajo = new Arreglo2D(2, 5, "ABAJO");
        nuevaMat.agregarMatrizXRenglon(matAbajo);
        nuevaMat.imprimirXRenglones();

        // 19. Convertir a matriz 3D
        Salida.salidaPorDefecto("\n19. Convertir a matriz 3D\n");
        Arreglo matrices = new Arreglo(2);
        matrices.poner(new Arreglo2D(5, 5, "CAPA1"));
        matrices.poner(new Arreglo2D(5, 5, "CAPA2"));
        Arreglo3D mat3D = nuevaMat.aMatriz3(matrices);
        mat3D.imprimirXColumnas();

        // 20. Convertir a vector columna
        Salida.salidaPorDefecto("\n20. Convertir a vector columna\n");
        Arreglo2D vecCol = nuevaMat.aVectorColumna();
        vecCol.imprimirXRenglones();

        // 21. Convertir a vector renglon
        Salida.salidaPorDefecto("\n21. Convertir a vector renglon\n");
        Arreglo2D vecRen = nuevaMat.aVectorRenglon();
        vecRen.imprimirXRenglones();

        // 22. Eliminar columna (izquierda/derecha)
        Salida.salidaPorDefecto("\n22. Eliminar columna\n");
        nuevaMat.quitarColumna(Arreglo2D.TipoColumna.IZQUIERDA);
        nuevaMat.imprimirXRenglones();

        // 23. Eliminar fila (superior/inferior)
        Salida.salidaPorDefecto("\n23. Eliminar fila\n");
        nuevaMat.quitarRenglon(Arreglo2D.TipoRenglon.INFERIOR);
        nuevaMat.imprimirXRenglones();

        // 24. Eliminar fila especifica
        Salida.salidaPorDefecto("\n24. Eliminar fila especifica\n");
        nuevaMat.eliminarRenglon(1);
        nuevaMat.imprimirXRenglones();

        // 25. Eliminar columna especifica
        Salida.salidaPorDefecto("\n25. Eliminar columna especifica\n");
        nuevaMat.eliminarColumna(2);
        nuevaMat.imprimirXRenglones();
    }
}
