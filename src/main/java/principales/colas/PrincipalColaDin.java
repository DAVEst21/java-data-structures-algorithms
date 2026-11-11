package principales.colas;

import colecciones.edlineal.colas.ColaDin;
import utils.entradasalida.cadenas.Entrada;
import utils.entradasalida.cadenas.Salida;

public class PrincipalColaDin {
    public static void main(String[] args) {
        Salida.salidaPorDefecto("** Prueba de ColaDinamica **\n");

        // Crear una nueva cola dinámica
        ColaDin cola = new ColaDin();

        // Verificar si la cola está vacía
        Salida.salidaPorDefecto("¿La cola está vacía? " + cola.vacio() + "\n");

        // Agregar elementos a la cola
        Salida.salidaPorDefecto("Ingresa 3 cadenas para la cola:\n");
        for (int i = 0; i < 3; i++) {
            Salida.salidaPorDefecto("Cadena " + (i + 1) + ": ");
            String cadena = Entrada.terminalCadenas();
            if (cola.poner(cadena)) {
                Salida.salidaPorDefecto("Cadena " + cadena + " agregada correctamente.\n");
            } else {
                Salida.salidaPorDefecto("Error al agregar la cadena " + cadena + ".\n");
            }
        }

        // Mostrar el contenido de la cola
        Salida.salidaPorDefecto("\nContenido de la cola (desde el frente):\n");
        cola.imprimir();

        // Ver el frente de la cola
        Object frente = cola.verTope();
        if (frente != null) {
            Salida.salidaPorDefecto("Frente de la cola: " + frente + "\n");
        } else {
            Salida.salidaPorDefecto("La cola está vacía, no hay frente.\n");
        }

        // Quitar un elemento
        Salida.salidaPorDefecto("\nQuitando un elemento...\n");
        Object elementoQuitado = cola.quitar();
        if (elementoQuitado != null) {
            Salida.salidaPorDefecto("Elemento quitado: " + elementoQuitado + "\n");
        } else {
            Salida.salidaPorDefecto("No se pudo quitar, la cola está vacía.\n");
        }

        // Mostrar el contenido después de quitar
        Salida.salidaPorDefecto("Contenido de la cola después de quitar:\n");
        cola.imprimir();

        // Vaciar la cola
        Salida.salidaPorDefecto("\nVaciando la cola...\n");
        cola.vaciar();

        // Verificar si la cola está vacía después de vaciar
        Salida.salidaPorDefecto("¿La cola está vacía? " + cola.vacio() + "\n");

        // Intentar imprimir la cola vacía
        Salida.salidaPorDefecto("Contenido de la cola después de vaciar:\n");
        cola.imprimir();

        Salida.salidaPorDefecto("\n** Fin de la prueba **\n");
    }
}
