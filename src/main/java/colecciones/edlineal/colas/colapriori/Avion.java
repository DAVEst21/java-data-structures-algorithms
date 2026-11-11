package colecciones.edlineal.colas.colapriori;

public class Avion {
    private String identificador;
    private String aerolinea;
    private String destino;
    private long horaPlanificada; // Hora planificada en milisegundos
    private long horaActual;      // Hora actual de simulación en milisegundos

    public Avion(String identificador, String aerolinea, String destino,
                 long horaPlanificada, long horaActual) {
        this.identificador = identificador;
        this.aerolinea = aerolinea;
        this.destino = destino;
        this.horaPlanificada = horaPlanificada;
        this.horaActual = horaActual;
    }

    // Calcula el tiempo de espera en minutos
    public int calcularTiempoEspera() {
        return Math.toIntExact(Math.subtractExact(horaActual, horaPlanificada) / (60 * 1000));
    }

    // Actualiza la hora actual para el cálculo de prioridad
    public void actualizarHora(long nuevaHora) {
        this.horaActual = nuevaHora;
    }

    @Override
    public String toString() {
        return String.format("%s (%s a %s) - Espera: %d min",
                identificador, aerolinea, destino, calcularTiempoEspera());
    }

    // Getters
    public String getIdentificador() {
        return identificador;
    }

    public String getAerolinea() {
        return aerolinea;
    }

    public String getDestino() {
        return destino;
    }

    public long getHoraPlanificada() {
        return horaPlanificada;
    }
}
