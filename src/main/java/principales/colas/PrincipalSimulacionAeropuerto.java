package principales.colas;

import colecciones.edlineal.colas.colapriori.Avion;
import colecciones.edlineal.colas.colapriori.SimulacionAeropuerto;

public class PrincipalSimulacionAeropuerto {
    public static void main(String[] args) {
        // Hora base para la simulación (ahora)
        long horaBase = System.currentTimeMillis();

        // Crear simulación con 2 pistas
        SimulacionAeropuerto aeropuerto = new SimulacionAeropuerto(2);

        // Agregar algunos aviones con diferentes horas planificadas
        aeropuerto.agregarAvion(new Avion("Vuelo123", "Aeroméxico", "Nueva York",
                horaBase - 45*60*1000, horaBase)); // 45 min de espera
        aeropuerto.agregarAvion(new Avion("Vuelo456", "Volaris", "Los Ángeles",
                horaBase - 30*60*1000, horaBase)); // 30 min
        aeropuerto.agregarAvion(new Avion("Vuelo789", "Delta", "Atlanta",
                horaBase - 15*60*1000, horaBase)); // 15 min
        aeropuerto.agregarAvion(new Avion("Vuelo101", "Interjet", "Cancún",
                horaBase - 60*60*1000, horaBase)); // 60 min (máxima prioridad)

        // Mostrar estado inicial
        aeropuerto.mostrarEstadoCola();

        // Procesar primeros despegues (deberían salir los de mayor tiempo de espera)
        aeropuerto.procesarDespegues();

        // Avanzar el tiempo 20 minutos
        aeropuerto.avanzarTiempo(20);

        // Agregar otro avión
        aeropuerto.agregarAvion(new Avion("Vuelo202", "United", "Chicago",
                horaBase - 10*60*1000, horaBase + 20*60*1000));

        // Mostrar estado actualizado
        aeropuerto.mostrarEstadoCola();

        // Procesar despegues nuevamente
        aeropuerto.procesarDespegues();

        // Mostrar estado final
        aeropuerto.mostrarEstadoCola();
    }
}
