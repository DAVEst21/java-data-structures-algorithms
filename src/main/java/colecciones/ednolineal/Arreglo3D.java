/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package colecciones.ednolineal;

import utils.entradasalida.cadenas.Salida;

/**
 *
 * @author Usuario
 */
public class Arreglo3D {
    protected int ancho;
    protected int alto;
    protected int profundo;
    protected Object datos [][][]; //Areglo multidimensional (en este caso 3 dimensiones)
                         // 1 Filas (ALTO) | 2 Col (ANCHO) | Profundidad ??????? maybe modifiqué cosas 

    public Arreglo3D(int alto, int ancho, int profundo){
        this.ancho=ancho;
        this.alto=alto;
        this.profundo=profundo;
        datos=new Object[alto][ancho][profundo];
    }

    public Arreglo3D(int alto, int ancho, int profundo, Object valor){
        this(alto, ancho, profundo);
        //invocar al método rellenar usando valor.....
        rellenar(valor);
    }

    public void rellenar(Object valor){
        //Hacer mi ciclo para lo alto, filas
        for(int cadaFila=0;cadaFila<alto;cadaFila++){
            //Hacer el ciclo de lo ancho, columnas
            for(int cadaCol=0; cadaCol<ancho; cadaCol++){
                //hacer la profundidad,
                for(int cadaProf=0;cadaProf<profundo;cadaProf++){
                    //Por cada celda [ ] [ ] [ ]
                    datos[cadaFila][cadaCol][cadaProf]=valor; 
                }
            }
        }
    }

    public void imprimirXColumnas(){
        //Dividir el cubo en rebanadas de columnas
        for(int cadaRebanda=0; cadaRebanda<ancho;cadaRebanda++){
            //Ahora debo imprimir una rebamnada y como está formada
            //Primero agarro los renglones de la matriz que es una
            //rebanada
            for(int cadaFila=0;cadaFila<alto;cadaFila++){
                //Asumo que hay un arreglo unidimensional para las
                //columnas, y recorre cada columna de ese
                //arreglo unidimensional
                for(int cadaCol=0;cadaCol<profundo;cadaCol++){
                    Salida.salidaPorDefecto(datos[cadaFila][cadaRebanda][cadaCol]+ " ");
                }
                //Cuando se hayan impreso todas las columnas de una fila
                //saltamos de linea
                Salida.salidaPorDefecto("\n");
            }
            //cuando se hayan impreso  todos los renglones de una
            //rebanada, hacemos un salto de linea
            Salida.salidaPorDefecto("\n");
        }
    }

    //Agregar dos métodos, uno para leer y otro para cambiar valores
    //en el cubo, en una celda en particular.

    //Validar si una dimensión específica es válida
    private boolean validarIndice(int tamanioDimension,
                                  int indiceValidar){
        if(indiceValidar>=0 && indiceValidar<tamanioDimension){
            return true;
        }else{
            return false;
        }
    }
    //Este extrae un valor de una celda en una posicion específica
    public Object obtenerCelda(int fila, int col, int prof){
        if(validarIndice(alto, fila)==true &&
            validarIndice(ancho, col)==true &&
            validarIndice(profundo, prof)==true){
            //me dieron una fila una columna y una profundiad
            //válida
            //Retorno el valor de esa celda
            return datos[fila][col][prof];
        }else{ //no hay índice válido
            return null;
        }
    }

    //Este coloca un valor a una celda en una posicion específica
    public boolean cambiarCelda(int fila, int col, int prof, Object valor){
        if(validarIndice(alto, fila)==true &&
            validarIndice(ancho, col)==true &&
            validarIndice(profundo, prof)==true){
            //me dieron una fila una columna y una profundiad
            //válida
            //Retorno el valor de esa celda
            datos[fila][col][prof]=valor;
            return true;
        }else{ //no hay índice válido
            return false;
        }
    }
}
