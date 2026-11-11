package registros.casas;

import colecciones.edlineal.listas.ListaDin;
import colecciones.edlineal.arreglos.Arreglo;

public class DatosCasas {
    protected ListaDin habitaciones;
    protected ListaDin precios;

    public DatosCasas(Arreglo arregloHabitaciones, Arreglo arregloPrecios) {
        habitaciones = new ListaDin();
        precios = new ListaDin();
        for (int i = 0; i < arregloHabitaciones.cantidad(); i++) {
            habitaciones.poner(Double.parseDouble((String) arregloHabitaciones.obtener(i)));
            precios.poner(Double.parseDouble((String) arregloPrecios.obtener(i)));
        }
    }

    public ListaDin getHabitaciones() {
        return habitaciones;
    }

    public ListaDin getPrecios() {
        return precios;
    }
}
