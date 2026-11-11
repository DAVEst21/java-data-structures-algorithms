package registros.casas;

import colecciones.edlineal.listas.ListaDin;
import colecciones.edlineal.arreglos.Arreglo;

public class DatosCasas {
    protected ListaDin habitaciones;
    protected ListaDin precios;

    public DatosCasas(Arreglo arregloHabitaciones, Arreglo arregloPrecios) {
        if (arregloHabitaciones == null || arregloPrecios == null || arregloHabitaciones.cantidad() != arregloPrecios.cantidad())
            throw new IllegalArgumentException("Los datos deben tener la misma cantidad de valores");
        habitaciones = new ListaDin();
        precios = new ListaDin();
        for (int i = 0; i < arregloHabitaciones.cantidad(); i++) {
            double habitacion = Double.parseDouble(String.valueOf(arregloHabitaciones.obtener(i)));
            double precio = Double.parseDouble(String.valueOf(arregloPrecios.obtener(i)));
            if (!Double.isFinite(habitacion) || !Double.isFinite(precio)) throw new IllegalArgumentException("Los datos deben ser finitos");
            habitaciones.poner(habitacion);
            precios.poner(precio);
        }
    }

    public ListaDin getHabitaciones() {
        return habitaciones;
    }

    public ListaDin getPrecios() {
        return precios;
    }
}
