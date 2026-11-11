/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registros.granja;

import colecciones.edlineal.arreglos.Arreglo;

/**
 *
 * @author Usuario
 */
public class Campesino {
    protected String nombre;
    protected Arreglo toneladasAnio1;
    protected Arreglo toneladasAnio2;
    protected Arreglo toneladasAnio3;
    protected Arreglo toneladasAnio4;
    protected Arreglo toneladasAnio5;

    public Campesino(String nombre) {
        this.nombre = nombre;
        toneladasAnio1 = new Arreglo (12);
        toneladasAnio2 = new Arreglo (12);
        toneladasAnio3 = new Arreglo (12);
        toneladasAnio4 = new Arreglo (12);
        toneladasAnio5 = new Arreglo (12);
        importarDatos();
    }
    
    //Importa datos "desde la empresa" (en realidad son números al azar del 0 al 1.5)
    private void importarDatos () {
        for (int i = 0; i < 12; i++) {
            toneladasAnio1.poner ((double) (Math.random() * 1.5));
            toneladasAnio2.poner ((double) (Math.random() * 1.5));
            toneladasAnio3.poner ((double) (Math.random() * 1.5));
            toneladasAnio4.poner ((double) (Math.random() * 1.5));
            toneladasAnio5.poner ((double) (Math.random() * 1.5));
        }
    }
    
    public double calcularPromedioToneladasAnio (int anio) {
        switch (anio) {
            case 1:
                return (double) toneladasAnio1.promedio();
            case 2:
                return (double) toneladasAnio2.promedio();
            case 3:
                return (double) toneladasAnio3.promedio();
            case 4:
                return (double) toneladasAnio4.promedio();
            case 5:
                return (double) toneladasAnio5.promedio();
            default:
                return -1; //Año no válido
        }
    }
    
    public Object calcularMesProductivo (int anio) {
        Arreglo mesMasProductivoAnio = new Arreglo(5);

        Arreglo toneladasAnio;
        switch (anio) {
            case 1:
                toneladasAnio = toneladasAnio1;
                break;
            case 2:
                toneladasAnio = toneladasAnio2;
                break;
            case 3:
                toneladasAnio = toneladasAnio3;
                break;
            case 4:
                toneladasAnio = toneladasAnio4;
                break;
            case 5:
                toneladasAnio = toneladasAnio5;
                break;
            default:
                return null;
        }
    
        // Obtenemos el valor más alto y su índice
        double mayorValor = (double) toneladasAnio.encontrarMayor();
        int mesMasProductivo = (int) toneladasAnio.buscar(mayorValor);
        return mesMasProductivo + 1;
    }
    
    public Object calcularToneladasUltimoMes () {
        Arreglo toneladasUltimoMes = new Arreglo (5);
        toneladasUltimoMes.poner((double) toneladasAnio1.obtener(11));
        toneladasUltimoMes.poner((double) toneladasAnio2.obtener(11));
        toneladasUltimoMes.poner((double) toneladasAnio3.obtener(11));
        toneladasUltimoMes.poner((double) toneladasAnio4.obtener(11));
        toneladasUltimoMes.poner((double) toneladasAnio5.obtener(11));
        return toneladasUltimoMes;
    }
    
    public Object calcularToneladasSegundoCuatrimestre (int anio) {
        double toneladasSegundoCuatrimestre = 0;

        Arreglo toneladasAnio;
        switch (anio) {
            case 1:
                toneladasAnio = toneladasAnio1;
                break;
            case 2:
                toneladasAnio = toneladasAnio2;
                break;
            case 3:
                toneladasAnio = toneladasAnio3;
                break;
            case 4:
                toneladasAnio = toneladasAnio4;
                break;
            case 5:
                toneladasAnio = toneladasAnio5;
                break;
            default:
                return null;
        }

        //sumar los meses de mayo a agosto (índices 4 a 7)
        for (int mes = 4; mes <= 7; mes++) {
            toneladasSegundoCuatrimestre += (double) toneladasAnio.obtener(mes);
        }

        return toneladasSegundoCuatrimestre;
    }
    
    public Arreglo getToneladasAnio(int anio) {
    switch (anio) {
        case 1:
            return toneladasAnio1;
        case 2:
            return toneladasAnio2;
        case 3:
            return toneladasAnio3;
        case 4:
            return toneladasAnio4;
        case 5:
            return toneladasAnio5;
        default:
            return null;
    }
}

    
}
