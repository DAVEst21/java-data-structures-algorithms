package principales.arreglos;

import colecciones.edlineal.arreglos.ArregloNumerico;
import colecciones.ednolineal.Arreglo2DNumerico;

public class PrincipalArreglo2DNumerico {
    public static void main(String[] args) {

        System.out.println("PRUEBA DE CONSTRUCTORES");
        // 2: Constructor con ceros
        Arreglo2DNumerico matriz1 = new Arreglo2DNumerico(3, 3);
        System.out.println("Matriz 3x3 inicializada con ceros:");
        matriz1.imprimirXRenglones();

        // 3: Constructor con valor específico
        Arreglo2DNumerico matriz2 = new Arreglo2DNumerico(2, 4, 5);
        System.out.println("\nMatriz 2x4 inicializada con 5:");
        matriz2.imprimirXRenglones();

        System.out.println("\nPRUEBA DE OPERACIONES BÁSICAS");
        // 4: Multiplicar por escalar
        matriz1.cambiar(0, 0, 1);
        matriz1.cambiar(0, 1, 2);
        matriz1.cambiar(0, 2, 3);
        matriz1.cambiar(1, 0, 4);
        matriz1.cambiar(1, 1, 5);
        matriz1.cambiar(1, 2, 6);
        matriz1.cambiar(2, 0, 7);
        matriz1.cambiar(2, 1, 8);
        matriz1.cambiar(2, 2, 9);

        System.out.println("\nMatriz 1 antes de multiplicar por 2:");
        matriz1.imprimirXRenglones();
        matriz1.porEscalar(2);
        System.out.println("\nMatriz 1 después de multiplicar por 2:");
        matriz1.imprimirXRenglones();

        // 6: Sumar escalar
        matriz1.sumarEscalar(1);
        System.out.println("\nMatriz 1 después de sumar 1:");
        matriz1.imprimirXRenglones();

        System.out.println("\nPRUEBA DE OPERACIONES CON ARREGLOS");
        // 5: Multiplicar por escalares (arreglo)
        ArregloNumerico escalares = new ArregloNumerico(9);
        for (int i = 0; i < 9; i++) {
            escalares.poner(i + 1);
        }

        System.out.println("\nArreglo de escalares:");
        escalares.imprimir();

        matriz1.porEscalares(escalares);
        System.out.println("\nMatriz 1 después de multiplicar por escalares:");
        matriz1.imprimirXRenglones();

        // 7: Sumar escalares (arreglo)
        ArregloNumerico sumandos = new ArregloNumerico(9);
        for (int i = 0; i < 9; i++) {
            sumandos.poner(10);
        }

        matriz1.sumarEscalares(sumandos);
        System.out.println("\nMatriz 1 después de sumar escalares (10):");
        matriz1.imprimirXRenglones();

        System.out.println("\nPRUEBA DE OPERACIONES ENTRE MATRICES");
        // 8: Multiplicación de matrices
        Arreglo2DNumerico matrizA = new Arreglo2DNumerico(2, 3);
        matrizA.cambiar(0, 0, 1);
        matrizA.cambiar(0, 1, 2);
        matrizA.cambiar(0, 2, 3);
        matrizA.cambiar(1, 0, 4);
        matrizA.cambiar(1, 1, 5);
        matrizA.cambiar(1, 2, 6);

        Arreglo2DNumerico matrizB = new Arreglo2DNumerico(3, 2);
        matrizB.cambiar(0, 0, 7);
        matrizB.cambiar(0, 1, 8);
        matrizB.cambiar(1, 0, 9);
        matrizB.cambiar(1, 1, 10);
        matrizB.cambiar(2, 0, 11);
        matrizB.cambiar(2, 1, 12);

        System.out.println("\nMatriz A (2x3):");
        matrizA.imprimirXRenglones();

        System.out.println("\nMatriz B (3x2):");
        matrizB.imprimirXRenglones();

        matrizA.multiplicar(matrizB);
        System.out.println("\nResultado de A x B (2x2):");
        matrizA.imprimirXRenglones();

        // 9: Suma de matrices
        Arreglo2DNumerico matrizC = new Arreglo2DNumerico(2, 2, 1);
        Arreglo2DNumerico matrizD = new Arreglo2DNumerico(2, 2, 2);

        System.out.println("\nMatriz C:");
        matrizC.imprimirXRenglones();

        System.out.println("\nMatriz D:");
        matrizD.imprimirXRenglones();

        matrizC.sumar(matrizD);
        System.out.println("\nResultado de C + D:");
        matrizC.imprimirXRenglones();

        System.out.println("\nPRUEBA DE OPERACIONES AVANZADAS");
        // 10: Potencia elemento por elemento
        Arreglo2DNumerico matrizPot = new Arreglo2DNumerico(2, 2);
        matrizPot.cambiar(0, 0, 2);
        matrizPot.cambiar(0, 1, 3);
        matrizPot.cambiar(1, 0, 4);
        matrizPot.cambiar(1, 1, 5);

        System.out.println("\nMatriz antes de aplicar potencia (^3):");
        matrizPot.imprimirXRenglones();

        matrizPot.aplicarPotencia(3);
        System.out.println("\nMatriz después de aplicar potencia (^3):");
        matrizPot.imprimirXRenglones();

        // 11: Logaritmo
        Arreglo2DNumerico matrizLog = new Arreglo2DNumerico(2, 2);
        matrizLog.cambiar(0, 0, 10);
        matrizLog.cambiar(0, 1, 100);
        matrizLog.cambiar(1, 0, Math.E);
        matrizLog.cambiar(1, 1, 8);

        System.out.println("\nMatriz antes de aplicar logaritmo base 10:");
        matrizLog.imprimirXRenglones();

        matrizLog.aplicarLogaritmo(Arreglo2DNumerico.TipoLogaritmo.BASE10);
        System.out.println("\nMatriz después de aplicar logaritmo base 10:");
        matrizLog.imprimirXRenglones();

        // 12: Matriz diagonal
        Arreglo2DNumerico matrizDiag = new Arreglo2DNumerico(4, 4);
        matrizDiag.matrizDiagonal(5);
        System.out.println("\nMatriz diagonal con valor 5:");
        matrizDiag.imprimirXRenglones();

        System.out.println("\nPRUEBA DE PROPIEDADES MATRICIALES");
        // 13 y 14: Matrices triangulares
        Arreglo2DNumerico triangularSup = new Arreglo2DNumerico(3, 3);
        triangularSup.cambiar(0, 0, 1);
        triangularSup.cambiar(0, 1, 2);
        triangularSup.cambiar(0, 2, 3);
        triangularSup.cambiar(1, 1, 4);
        triangularSup.cambiar(1, 2, 5);
        triangularSup.cambiar(2, 2, 6);

        System.out.println("\nMatriz triangular superior:");
        triangularSup.imprimirXRenglones();
        System.out.println("¿Es triangular superior? " + triangularSup.esDiagonalSuperior());
        System.out.println("¿Es triangular inferior? " + triangularSup.esDiagonalInferior());

        // 15: Potencia de matriz
        Arreglo2DNumerico matrizPoten = new Arreglo2DNumerico(2, 2);
        matrizPoten.cambiar(0, 0, 1);
        matrizPoten.cambiar(0, 1, 1);
        matrizPoten.cambiar(1, 0, 1);
        matrizPoten.cambiar(1, 1, 0);

        System.out.println("\nMatriz antes de elevar a la potencia 3:");
        matrizPoten.imprimirXRenglones();

        matrizPoten.potencia(3);
        System.out.println("\nMatriz después de elevar a la potencia 3:");
        matrizPoten.imprimirXRenglones();

        System.out.println("\nPRUEBA DE REDIMENSIONAMIENTO");
        // 16: Doblar renglones
        Arreglo2DNumerico matrizRedim1 = new Arreglo2DNumerico(4, 4);
        int valor = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrizRedim1.cambiar(i, j, valor++);
            }
        }

        System.out.println("\nMatriz antes de doblar renglones:");
        matrizRedim1.imprimirXRenglones();

        matrizRedim1.doblarRenglones();
        System.out.println("\nMatriz después de doblar renglones:");
        matrizRedim1.imprimirXRenglones();

        // 17: Doblar columnas
        Arreglo2DNumerico matrizRedim2 = new Arreglo2DNumerico(4, 4);
        valor = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrizRedim2.cambiar(i, j, valor++);
            }
        }

        System.out.println("\nMatriz antes de doblar columnas:");
        matrizRedim2.imprimirXRenglones();

        matrizRedim2.doblarColumnas();
        System.out.println("\nMatriz después de doblar columnas:");
        matrizRedim2.imprimirXRenglones();
    }
}
