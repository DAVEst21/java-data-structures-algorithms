package principales.colas;

import colecciones.edlineal.colas.colapriori.ColaPrioridad;
import colecciones.edlineal.colas.colapriori.ElementoPrioritario;
import utils.entradasalida.cadenas.Salida;

/**
 * Programa principal para demostrar el funcionamiento de la ColaPrioridad
 */
public class PrincipalColaPrioridad {
    public static void main(String[] args) {
        // Crear una cola de prioridad con capacidad para 5 elementos, mayor valor de prioridad = mayor prioridad
        ColaPrioridad cola = new ColaPrioridad(5, true);

        Salida.salidaPorDefecto("\nPRUEBA DE COLA DE PRIORIDAD\n");

        // Prueba 1: Agregar elementos con diferentes prioridades
        cola.agregar("Tarea crítica", 10);
        cola.agregar("Tarea normal", 5);
        cola.agregar("Tarea urgente", 8);
        cola.agregar("Tarea baja prioridad", 2);
        cola.agregar("Tarea media prioridad", 6);

        // Mostrar estado inicial
        Salida.salidaPorDefecto("\nEstado inicial de la cola:");
        cola.imprimir();

        // Prueba 2: Quitar elementos, salen en orden de prioridad
        Salida.salidaPorDefecto("\n\nProcesando elementos de la cola:");
        while (!cola.vacio()) {
            String tarea = (String) cola.quitar();
            ElementoPrioritario tope = cola.verElementoTope();
            String proximaTarea = tope != null ? tope.getContenido().toString() : "Ninguna";
            Integer proximaPrioridad = tope != null ? tope.getValorPrioridad() : null;

            Salida.salidaPorDefecto("Procesada: " + tarea);
            Salida.salidaPorDefecto("Próxima en cola: " + proximaTarea +
                    " Prioridad: " + proximaPrioridad);
        }

        // Prueba 3: Comportamiento con cola vacía
        Salida.salidaPorDefecto("\nIntentando quitar de cola vacía:");
        Object elemento = cola.quitar();
        Salida.salidaPorDefecto("Resultado: " + (elemento == null ? "null" : elemento.toString()));

        // Prueba 4: Prioridad inversa (menor valor = mayor prioridad)
        Salida.salidaPorDefecto("\nPRUEBA CON PRIORIDAD INVERSA");

        ColaPrioridad colaInversa = new ColaPrioridad(5, false);
        colaInversa.agregar("Tarea A", 5);
        colaInversa.agregar("Tarea B", 1);
        colaInversa.agregar("Tarea C", 3);

        Salida.salidaPorDefecto("Contenido de la cola (prioridad inversa):");
        colaInversa.imprimir();

        Salida.salidaPorDefecto("\nProcesando elementos:");
        while (!colaInversa.vacio()) {
            Salida.salidaPorDefecto("Procesada: " + colaInversa.quitar());
        }

        // Prueba 5: Intentar agregar a cola llena
        Salida.salidaPorDefecto("\nPRUEBA DE COLA LLENA");
        ColaPrioridad colaLlena = new ColaPrioridad(2, true);
        Salida.salidaPorDefecto("Agregando 1er elemento: " + colaLlena.agregar("Primero", 1));
        Salida.salidaPorDefecto("Agregando 2do elemento: " + colaLlena.agregar("Segundo", 2));
        Salida.salidaPorDefecto("Intentando agregar 3er elemento: " + colaLlena.agregar("Tercero", 3));

        Salida.salidaPorDefecto("\nContenido final de la cola (debería tener solo 2 elementos):");
        colaLlena.imprimir();
    }
}