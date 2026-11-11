package colecciones.edlineal.colas.colapriori;
import utils.entradasalida.cadenas.Salida;

public class SimulacionAeropuerto {
    private ColaPrioridad colaDespegue;
    private int numeroPistas;
    private long horaSimulacion;

    public SimulacionAeropuerto(int numeroPistas) {
        // Configuramos la cola para que mayor tiempo de espera = mayor prioridad
        this.colaDespegue = new ColaPrioridad(50, true);
        this.numeroPistas = numeroPistas;
        this.horaSimulacion = System.currentTimeMillis();
    }

    public void agregarAvion(Avion avion) {
        // Actualizamos la hora del avión con la hora actual de simulación
        avion.actualizarHora((int) horaSimulacion);
        // La prioridad es el tiempo de espera en minutos
        colaDespegue.agregar(avion, avion.calcularTiempoEspera());
    }

    public void avanzarTiempo(int minutos) {
        horaSimulacion += minutos * 60 * 1000;
        Salida.salidaPorDefecto("\n--- Tiempo avanzado " + minutos + " minutos ---\n");
    }

    public void procesarDespegues() {
        Salida.salidaPorDefecto("\nPROCESANDO DESPEGUES\n");
        Salida.salidaPorDefecto("Pistas disponibles: " + numeroPistas + "\n");

        int despeguesRealizados = 0;

        while (despeguesRealizados < numeroPistas && !colaDespegue.vacio()) {
            Avion avion = (Avion) colaDespegue.quitar();
            despeguesRealizados++;

            Salida.salidaPorDefecto("Despegando: " + avion + "\n");
        }

        if (despeguesRealizados == 0) {
            Salida.salidaPorDefecto("No hay aviones esperando para despegar\n");
        } else {
            Salida.salidaPorDefecto("Total despegues: " + despeguesRealizados + "\n");
        }
    }

    public void mostrarEstadoCola() {
        Salida.salidaPorDefecto("\nESTADO DE LA COLA DE DESPEGUE\n");
        Salida.salidaPorDefecto("Hora actual de simulación: " + horaSimulacion + "\n");
        Salida.salidaPorDefecto("Aviones en cola: " + (colaDespegue.vacio() ? "Ninguno" : "\n"));

        if (!colaDespegue.vacio()) {
            colaDespegue.imprimir();
        }
    }
}
