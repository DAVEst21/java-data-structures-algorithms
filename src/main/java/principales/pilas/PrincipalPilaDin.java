package principales.pilas;

import utils.entradasalida.cadenas.Salida;
import utils.entradasalida.cadenas.Entrada;
import colecciones.edlineal.pilas.PilaDin;

public class PrincipalPilaDin {
    public static void main(String[] args) {
        Salida.salidaPorDefecto("** Prueba de PilaDinamica **\n");

        // Crear una nueva pila dinámica
        PilaDin pila = new PilaDin();

        // Verificar si la pila está vacía
        Salida.salidaPorDefecto("¿La pila está vacía? " + pila.vacio() + "\n");

        // Agregar elementos a la pila
        Salida.salidaPorDefecto("Ingresa 3 números para la pila:\n");
        for (int i = 0; i < 3; i++) {
            Salida.salidaPorDefecto("Número " + (i + 1) + ": ");
            Double numero = Entrada.terminalDouble();
            if (pila.poner(numero)) {
                Salida.salidaPorDefecto("Número " + numero + " agregado correctamente.\n");
            } else {
                Salida.salidaPorDefecto("Error al agregar el número " + numero + ".\n");
            }
        }

        // Mostrar el contenido de la pila
        Salida.salidaPorDefecto("\nContenido de la pila (desde el tope):\n");
        pila.imprimir();

        // Ver el tope de la pila
        Object tope = pila.verTope();
        if (tope != null) {
            Salida.salidaPorDefecto("Tope de la pila: " + tope + "\n");
        } else {
            Salida.salidaPorDefecto("La pila está vacía, no hay tope.\n");
        }

        // Quitar un elemento
        Salida.salidaPorDefecto("\nQuitando un elemento...\n");
        Object elementoQuitado = pila.quitar();
        if (elementoQuitado != null) {
            Salida.salidaPorDefecto("Elemento quitado: " + elementoQuitado + "\n");
        } else {
            Salida.salidaPorDefecto("No se pudo quitar, la pila está vacía.\n");
        }

        // Mostrar el contenido después de quitar
        Salida.salidaPorDefecto("Contenido de la pila después de quitar:\n");
        pila.imprimir();

        // Vaciar la pila
        Salida.salidaPorDefecto("\nVaciando la pila...\n");
        pila.vaciar();

        // Verificar si la pila está vacía después de vaciar
        Salida.salidaPorDefecto("¿La pila está vacía? " + pila.vacio() + "\n");

        // Intentar imprimir la pila vacía
        Salida.salidaPorDefecto("Contenido de la pila después de vaciar:\n");
        pila.imprimir();

        Salida.salidaPorDefecto("\n** Fin de la prueba **\n");
    }
}
