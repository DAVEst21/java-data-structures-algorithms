/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils.comunesbases;

/**
 *
 * @author Usuario
 */
public class ManipuladorObjetos {
    // Compara 2 objetos y me dice
    // si es objeto1 mayor que objeto2
    // si objeto1 es igual que objeto 2
    // si es objeto1 es menor que objeto 2
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static int compararObjetos(Object objeto1, Object objeto2){
        if(objeto1 instanceof Number && objeto2 instanceof Number){
            //los dos son numeros
            Double numero1 = Double.parseDouble(objeto1.toString());
            Double numero2 = Double.parseDouble(objeto2.toString());

            if(numero1 > numero2){
                return 1;
            } else if(numero1 < numero2){
                return -1;
            } else{
                //iguales
                return 0;
            }

        } else if (objeto1 != null && objeto2 != null && objeto1.getClass() == objeto2.getClass() && objeto1 instanceof Comparable && !(objeto1 instanceof String) && !(objeto1 instanceof Character)) {
            return ((Comparable) objeto1).compareTo(objeto2);
        } else{//uno o los dos no son num
            //el metodo compareTo regresa un positivo si 01 > 02
            // si 01 < 02 regresa negativo
            //si 01 = 02 regresa 0
            return objeto1.toString().compareToIgnoreCase(objeto2.toString());
        }
    }
    
}
