/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils.matematicas;

import colecciones.edlineal.pilas.PilaFija;

/**
 *
 * @author Usuario
 */
public class TratamientoExpresiones {

    //Convertir expresión infija -> postfija
    public static String convertirInfijaPostfija(String infija) {
        return "";
    }
    //Convertir expresión infija -> prefija

    //Evaluar expresión infija
    public static Double evaluarInfija(String infija) {
        //Convertir a prefija o postfija
        String postfija = convertirInfijaPostfija(infija);
        //Llama evaluar prefija o postfija
        return evaluarPostfija(postfija);
    }

    //Evaluar expresión postfija
    public static Double evaluarPostfija(String postfija) {
        PilaFija pila = new PilaFija(postfija.length());
        //1. Tokenizar la EPOS de izq -> der
        int posToken;
        for (posToken = 0; posToken < postfija.length(); posToken++) {
            char token = postfija.charAt(posToken);
            if (esOperando(token + "")) {
                // a) Si es operando, apilar
                if (pila.poner(token + "") == false) {
                    return null; //hubo error
                }
            } else {
                //si no fue operando, es operador
                //  b) Si es operador, se extraen dos operandos de la pila

                String op2 = (String) pila.quitar();
                String op1 = (String) pila.quitar();

                Double numero1 = Double.parseDouble(op1);
                Double numero2 = Double.parseDouble(op2);

                //  b) Si es operador, se extraen dos operandos de la pila
                //  (el primer operando es OP2), se calcula la operación con
                //  ese token y se mete el resultado a la pila.
                Double resultado = operacionAritmetica(token, numero1, numero2);
                if (resultado == null) {
                    // hay error
                    return null;
                }
                pila.poner(resultado + "");
            }
        }

        //2. El resultado es el único elemento en la pila
        String cadenaResultado = (String) pila.quitar();
        if (cadenaResultado == null) {
            return null;
        }
        Double evaluacion = Double.parseDouble(cadenaResultado);
        return evaluacion;
    }

    public static Double operacionAritmetica(char operador, Double op1, Double op2) {
        if (operador == '+') {
            return op1 + op2;
        } else if (operador == '-') {
            return op1 - op2;
        } else if (operador == '/') {
            if (op2 != 0) {
                return op1 / op2;
            } else {
                // división por 0
                return null;
            }
        } else if (operador == '*') {
            return op1 * op2;
        } else if (operador == '^') {
            return Math.pow(op1, op2);
        } else if (operador == '%') {
            return op1 % op2;
        } else {
            // otra cosa inválida
            return null;
        }
    }

    public static boolean esOperando(String token) {
        if (token.equals("+") == true) {
            return false;
        } else if (token.equals("-") == true) {
            return false;
        } else if (token.equals("/") == true) {
            return false;
        } else if (token.equals("*") == true) {
            return false;
        } else if (token.equals("^") == true) {
            return false;
        } else if (token.equals("%") == true) {
            return false;
        } else if (token.equals("(") == true) {
            return false;
        } else if (token.equals(")") == true) {
            return false;
        } else {
            // es operando
            return true;
        }
    }

    //Evaluar expresión prefija
}
