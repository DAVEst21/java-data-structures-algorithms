/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template 
 */
package principales.arreglos;

import colecciones.edlineal.arreglos.Arreglo;
import colecciones.edlineal.arreglos.ArregloNumerico;
import utils.entradasalida.cadenas.Salida;

/**
 *
 * @author Usuario
 */
public class PrincipalANumerico {
    public static void main(String[] args) {
        
        // Prueba del método porEscalar
        Salida.salidaPorDefecto("Prueba de porEscalar:\n");
        ArregloNumerico arregloEscalar = new ArregloNumerico(5);
        arregloEscalar.poner(1.0);
        arregloEscalar.poner(2.0);
        arregloEscalar.poner(3.0);
        arregloEscalar.poner(4.0);
        arregloEscalar.poner(5.0);
        arregloEscalar.porEscalar(2.0);
        arregloEscalar.imprimir();
        Salida.salidaPorDefecto("\n");

        // Prueba del método sumarEscalar
        Salida.salidaPorDefecto("Prueba de sumarEscalar:\n");
        ArregloNumerico arregloSumarEscalar = new ArregloNumerico(5);
        arregloSumarEscalar.poner(1.0);
        arregloSumarEscalar.poner(2.0);
        arregloSumarEscalar.poner(3.0);
        arregloSumarEscalar.poner(4.0);
        arregloSumarEscalar.poner(5.0);
        arregloSumarEscalar.sumarEscalar(5.0);
        arregloSumarEscalar.imprimir();
        Salida.salidaPorDefecto("\n");

        // Prueba del método sumar
        Salida.salidaPorDefecto("Prueba de sumar:\n");
        ArregloNumerico arregloSumar1 = new ArregloNumerico(5);
        arregloSumar1.poner(1.0);
        arregloSumar1.poner(2.0);
        arregloSumar1.poner(3.0);
        arregloSumar1.poner(4.0);
        arregloSumar1.poner(5.0);

        ArregloNumerico arregloSumar2 = new ArregloNumerico(5);
        arregloSumar2.poner(10.0);
        arregloSumar2.poner(20.0);
        arregloSumar2.poner(30.0);
        arregloSumar2.poner(40.0);
        arregloSumar2.poner(50.0);

        arregloSumar1.sumar(arregloSumar2);
        arregloSumar1.imprimir();
        Salida.salidaPorDefecto("\n");

        // Prueba del método multiplicar
        Salida.salidaPorDefecto("Prueba de multiplicar:\n");
        ArregloNumerico arregloMultiplicar1 = new ArregloNumerico(5);
        arregloMultiplicar1.poner(1.0);
        arregloMultiplicar1.poner(2.0);
        arregloMultiplicar1.poner(3.0);
        arregloMultiplicar1.poner(4.0);
        arregloMultiplicar1.poner(5.0);

        ArregloNumerico arregloMultiplicar2 = new ArregloNumerico(5);
        arregloMultiplicar2.poner(10.0);
        arregloMultiplicar2.poner(20.0);
        arregloMultiplicar2.poner(30.0);
        arregloMultiplicar2.poner(40.0);
        arregloMultiplicar2.poner(50.0);

        arregloMultiplicar1.multiplicar(arregloMultiplicar2);
        arregloMultiplicar1.imprimir();
        Salida.salidaPorDefecto("\n");

        // Prueba del método aplicarPotencia (con escalar)
        Salida.salidaPorDefecto("Prueba de aplicarPotencia (con escalar):\n");
        ArregloNumerico arregloPotenciaEscalar = new ArregloNumerico(5);
        arregloPotenciaEscalar.poner(1.0);
        arregloPotenciaEscalar.poner(2.0);
        arregloPotenciaEscalar.poner(3.0);
        arregloPotenciaEscalar.poner(4.0);
        arregloPotenciaEscalar.poner(5.0);
        arregloPotenciaEscalar.aplicarPotencia(2.0);
        arregloPotenciaEscalar.imprimir();
        Salida.salidaPorDefecto("\n");

        // Prueba del método aplicarPotencia (con arreglo de exponentes)
        Salida.salidaPorDefecto("Prueba de aplicarPotencia (con arreglo de exponentes):\n");
        ArregloNumerico arregloPotenciaExponentes = new ArregloNumerico(5);
        arregloPotenciaExponentes.poner(1.0);
        arregloPotenciaExponentes.poner(2.0);
        arregloPotenciaExponentes.poner(3.0);
        arregloPotenciaExponentes.poner(4.0);
        arregloPotenciaExponentes.poner(5.0);

        ArregloNumerico exponentes = new ArregloNumerico(5);
        exponentes.poner(1.0);
        exponentes.poner(2.0);
        exponentes.poner(3.0);
        exponentes.poner(4.0);
        exponentes.poner(5.0);

        arregloPotenciaExponentes.aplicarPotencia(exponentes);
        arregloPotenciaExponentes.imprimir();
        Salida.salidaPorDefecto("\n");

        // Prueba del método productoEscalar
        Salida.salidaPorDefecto("Prueba de productoEscalar:\n");
        ArregloNumerico arregloProducto1 = new ArregloNumerico(5);
        arregloProducto1.poner(1.0);
        arregloProducto1.poner(2.0);
        arregloProducto1.poner(3.0);
        arregloProducto1.poner(4.0);
        arregloProducto1.poner(5.0);

        ArregloNumerico arregloProducto2 = new ArregloNumerico(5);
        arregloProducto2.poner(10.0);
        arregloProducto2.poner(20.0);
        arregloProducto2.poner(30.0);
        arregloProducto2.poner(40.0);
        arregloProducto2.poner(50.0);

        double producto = arregloProducto1.productoEscalar(arregloProducto2);
        Salida.salidaPorDefecto("Producto escalar: " + producto);
        Salida.salidaPorDefecto("\n\n");

        // Prueba del método norma
        Salida.salidaPorDefecto("Prueba de norma:\n");
        ArregloNumerico arregloNorma = new ArregloNumerico(5);
        arregloNorma.poner(1.0);
        arregloNorma.poner(2.0);
        arregloNorma.poner(3.0);
        arregloNorma.poner(4.0);
        arregloNorma.poner(5.0);

        double norma = arregloNorma.norma();
        Salida.salidaPorDefecto("Norma: " + norma);
        Salida.salidaPorDefecto("\n\n");

        // Prueba del método normaEuclidiana
        Salida.salidaPorDefecto("Prueba de normaEuclidiana:\n");
        ArregloNumerico arregloNormaEuclidiana1 = new ArregloNumerico(5);
        arregloNormaEuclidiana1.poner(1.0);
        arregloNormaEuclidiana1.poner(2.0);
        arregloNormaEuclidiana1.poner(3.0);
        arregloNormaEuclidiana1.poner(4.0);
        arregloNormaEuclidiana1.poner(5.0);

        ArregloNumerico arregloNormaEuclidiana2 = new ArregloNumerico(5);
        arregloNormaEuclidiana2.poner(10.0);
        arregloNormaEuclidiana2.poner(20.0);
        arregloNormaEuclidiana2.poner(30.0);
        arregloNormaEuclidiana2.poner(40.0);
        arregloNormaEuclidiana2.poner(50.0);

        double normaEuclidiana = arregloNormaEuclidiana1.normaEuclidiana(arregloNormaEuclidiana2);
        Salida.salidaPorDefecto("Norma euclidiana: " + normaEuclidiana);
        Salida.salidaPorDefecto("\n\n");

        // Prueba del método sumarListaEstatica
        Salida.salidaPorDefecto("Prueba de sumarListaEstatica:\n");
        ArregloNumerico arregloSumarLista = new ArregloNumerico(5);
        arregloSumarLista.poner(1.0);
        arregloSumarLista.poner(2.0);
        arregloSumarLista.poner(3.0);
        arregloSumarLista.poner(4.0);
        arregloSumarLista.poner(5.0);

        Arreglo listas = new Arreglo(2);
        
        ArregloNumerico arreglotemp = new ArregloNumerico(5);
        arreglotemp.poner(10.0);
        arreglotemp.poner(20.0);
        arreglotemp.poner(30.0);
        arreglotemp.poner(40.0);
        arreglotemp.poner(50.0);
        listas.poner(arreglotemp);
        
        ArregloNumerico arreglotemp2 = new ArregloNumerico(5);
        arreglotemp2.poner(100.0);
        arreglotemp2.poner(200.0);
        arreglotemp2.poner(300.0);
        arreglotemp2.poner(400.0);
        arreglotemp2.poner(500.0);
        listas.poner(arreglotemp2);

        arregloSumarLista.sumarListaEstatica(listas);
        arregloSumarLista.imprimir();
        Salida.salidaPorDefecto("\n");

        // Prueba del método sumarEscalares
        Salida.salidaPorDefecto("Prueba de sumarEscalares:\n");
        ArregloNumerico arregloSumarEscalares = new ArregloNumerico(5);
        arregloSumarEscalares.poner(1.0);
        arregloSumarEscalares.poner(2.0);
        arregloSumarEscalares.poner(3.0);
        arregloSumarEscalares.poner(4.0);
        arregloSumarEscalares.poner(5.0);

        ArregloNumerico escalares = new ArregloNumerico(5);
        escalares.poner(1.0);
        escalares.poner(2.0);
        escalares.poner(3.0);
        escalares.poner(4.0);
        escalares.poner(5.0);

        arregloSumarEscalares.sumarEscalares(escalares);
        arregloSumarEscalares.imprimir();
        Salida.salidaPorDefecto("\n");

        // Prueba del método sumarIndices
        Salida.salidaPorDefecto("Prueba de sumarIndices:\n");
        ArregloNumerico arregloSumarIndices = new ArregloNumerico(5);
        arregloSumarIndices.poner(1.0);
        arregloSumarIndices.poner(2.0);
        arregloSumarIndices.poner(3.0);
        arregloSumarIndices.poner(4.0);
        arregloSumarIndices.poner(5.0);

        ArregloNumerico listaIndices = new ArregloNumerico(3);
        listaIndices.poner(0);
        listaIndices.poner(2);
        listaIndices.poner(4);

        double sumaIndices = arregloSumarIndices.sumarIndices(listaIndices);
        Salida.salidaPorDefecto("Suma de índices: " + sumaIndices);
        Salida.salidaPorDefecto("\n\n");

        // Prueba del método subLista
        Salida.salidaPorDefecto("Prueba de subLista:\n");
        ArregloNumerico arregloSubLista = new ArregloNumerico(5);
        arregloSubLista.poner(1.0);
        arregloSubLista.poner(2.0);
        arregloSubLista.poner(3.0);
        arregloSubLista.poner(4.0);
        arregloSubLista.poner(5.0);

        Arreglo sublista = arregloSubLista.subLista(listaIndices);
        sublista.imprimir();
        Salida.salidaPorDefecto("\n");
        
        // Prueba del método subLista (Arreglo con String)
        Salida.salidaPorDefecto("Prueba de subLista:\n");
        Arreglo arregloSubListaStr = new Arreglo(5);
        arregloSubListaStr.poner("David");
        arregloSubListaStr.poner("Ignacio");
        arregloSubListaStr.poner("Becerra");
        arregloSubListaStr.poner("Morán");
        arregloSubListaStr.poner("Hola");

        Arreglo sublistaStr = arregloSubListaStr.subLista(listaIndices);
        sublistaStr.imprimir();
        Salida.salidaPorDefecto("\n");

        // Prueba del método sonLinealmenteDependientes
        Salida.salidaPorDefecto("Prueba de sonLinealmenteDependientes:\n");
        ArregloNumerico arregloDependientes = new ArregloNumerico(3);
        arregloDependientes.poner(1.0);
        arregloDependientes.poner(-2.0);
        arregloDependientes.poner(1.0);

        ArregloNumerico arregloDependientes2 = new ArregloNumerico(3);
        arregloDependientes2.poner(1.0);
        arregloDependientes2.poner(2.0);
        arregloDependientes2.poner(3.0);
        
        ArregloNumerico arregloDependientes3 = new ArregloNumerico(3);
        arregloDependientes3.poner(2.0);
        arregloDependientes3.poner(4.0);
        arregloDependientes3.poner(6.0);
        
        ArregloNumerico arregloDependientes4 = new ArregloNumerico(3);
        arregloDependientes4.poner(3.0);
        arregloDependientes4.poner(6.0);
        arregloDependientes4.poner(9.0);

        Arreglo listaVectores = new Arreglo(3);
        listaVectores.poner(arregloDependientes2);
        listaVectores.poner(arregloDependientes3);
        listaVectores.poner(arregloDependientes4);

        boolean dependientes = arregloDependientes.sonLinealmenteDependientes(listaVectores);
        Salida.salidaPorDefecto("Son linealmente dependientes? " + dependientes);
        Salida.salidaPorDefecto("\n\n");

        // Prueba del método sonLinealmenteIndependientes
        Salida.salidaPorDefecto("Prueba de sonLinealmenteIndependientes:\n");
        ArregloNumerico arregloIndependientes = new ArregloNumerico(3);
        arregloIndependientes.poner(1.0);
        arregloIndependientes.poner(0.0);
        arregloIndependientes.poner(0.0);

        ArregloNumerico arregloIndependientes2 = new ArregloNumerico(3);
        arregloIndependientes2.poner(1.0);
        arregloIndependientes2.poner(0.0);
        arregloIndependientes2.poner(0.0);
        
        ArregloNumerico arregloIndependientes3 = new ArregloNumerico(3);
        arregloIndependientes3.poner(0.0);
        arregloIndependientes3.poner(1.0);
        arregloIndependientes3.poner(0.0);
        
        ArregloNumerico arregloIndependientes4 = new ArregloNumerico(3);
        arregloIndependientes4.poner(0.0);
        arregloIndependientes4.poner(0.0);
        arregloIndependientes4.poner(1.0);

        Arreglo listaVectoresInd = new Arreglo(3);
        listaVectoresInd.poner(arregloIndependientes2);
        listaVectoresInd.poner(arregloIndependientes3);
        listaVectoresInd.poner(arregloIndependientes4);
        boolean independientes = arregloIndependientes.sonLinealmenteIndependientes(listaVectoresInd);
        Salida.salidaPorDefecto("Son linealmente independientes? " + independientes);
        Salida.salidaPorDefecto("\n\n");

        // Prueba del método esOrtogonal
        Salida.salidaPorDefecto("Prueba de esOrtogonal:\n");
        ArregloNumerico arregloOrtogonal1 = new ArregloNumerico(3);
        arregloOrtogonal1.poner(1.0);
        arregloOrtogonal1.poner(0.0);
        arregloOrtogonal1.poner(0.0);

        ArregloNumerico arregloOrtogonal2 = new ArregloNumerico(3);
        arregloOrtogonal2.poner(0.0);
        arregloOrtogonal2.poner(1.0);
        arregloOrtogonal2.poner(0.0);

        boolean ortogonal = arregloOrtogonal1.esOrtogonal(arregloOrtogonal2);
        Salida.salidaPorDefecto("Son ortogonales? " + ortogonal);
        Salida.salidaPorDefecto("\n\n");

        // Prueba del método esParalelo
        Salida.salidaPorDefecto("Prueba de esParalelo:\n");
        ArregloNumerico arregloParalelo1 = new ArregloNumerico(3);
        arregloParalelo1.poner(1.0);
        arregloParalelo1.poner(2.0);
        arregloParalelo1.poner(3.0);

        ArregloNumerico arregloParalelo2 = new ArregloNumerico(3);
        arregloParalelo2.poner(2.0);
        arregloParalelo2.poner(4.0);
        arregloParalelo2.poner(6.0);

        boolean paralelo = arregloParalelo1.esParalelo(arregloParalelo2);
        Salida.salidaPorDefecto("Son paralelos? " + paralelo);
        Salida.salidaPorDefecto("\n\n");
    }
}
