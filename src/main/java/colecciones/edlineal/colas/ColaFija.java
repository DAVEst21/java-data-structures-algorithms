package colecciones.edlineal.colas;

import colecciones.interfaces.LoteDatos;
import utils.entradasalida.cadenas.Salida;

public class ColaFija implements LoteDatos {
    protected int primero;
    protected int ultimo;
    protected int capacidad;
    protected Object datos[];

    public ColaFija(int tamanio) {
        capacidad = tamanio;
        datos = new Object[tamanio];
        primero = -1;
        ultimo = -1;
    }

    public boolean vacio() {
        if (primero == -1) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean lleno() {
        if ((primero == 0 && ultimo == (capacidad - 1)) || (primero == (ultimo + 1))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean poner(Object valor) {
        if (lleno() == false) {
            if (vacio() == true) { //a
                datos[0] = valor;
                primero = 0;
                ultimo = 0;
            } else if (ultimo == (capacidad - 1)) { //d
                datos[0] = valor;
                ultimo = 0;
            } else { //c, e)
                ultimo++;
                datos[ultimo] = valor;
            }
            return true;
        } else { //b
            return false;
        }
    }

    @Override
    public Object quitar() {
        if (vacio() == false) {
            Object respaldo = datos[primero];
            if (primero == ultimo) {//f

                primero = -1;
                ultimo = -1;
            } else if (primero == (capacidad - 1)) { //d

                primero = 0;
            } else { //b, c, e)

                primero++;
            }
            return respaldo;

        } else {
            //a)
            return null;
        }
    }

    @Override
    public void vaciar() {
        throw new UnsupportedOperationException("Unimplemented method 'vaciar'");
    }

    @Override
    public void imprimir() {
        if (vacio() == false) {
            if (primero <= ultimo) {
                for (int pos = primero; pos <= ultimo; pos++) {
                    Salida.salidaPorDefecto(datos[pos] + " ");
                }
            } else {
                for (int pos = primero; pos <= capacidad - 1; pos++) {
                    Salida.salidaPorDefecto(datos[pos] + " ");
                }
                for (int pos = 0; pos <= ultimo; pos++) {
                    Salida.salidaPorDefecto(datos[pos] + " ");

                }
            }
        }

    }

    @Override
    public Object verTope() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'verTope'");
    }
}
