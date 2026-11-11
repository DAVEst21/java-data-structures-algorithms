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
        if (infija == null) return null;
        registros.calculadora.Calculadora calculadora = new registros.calculadora.Calculadora(infija.length());
        try { return calculadora.convertirPostfija(infija); }
        catch (IllegalArgumentException e) { return null; }
    }
    //Convertir expresión infija -> prefija

    //Evaluar expresión infija
    public static Double evaluarInfija(String infija) {
        if (infija == null) return null;
        // Convertir a postfija usando el mismo algoritmo con pilas de la calculadora.
        registros.calculadora.Calculadora calculadora = new registros.calculadora.Calculadora(infija.length());
        try {
            calculadora.convertirPostfija(infija);
            return calculadora.evaluarExpresion();
        } catch (IllegalArgumentException e) { return null; }
    }

    //Evaluar expresión postfija
    public static Double evaluarPostfija(String postfija) {
        if (postfija == null || postfija.isBlank()) return null;
        PilaFija pila = new PilaFija(postfija.length());
        // Conservar las expresiones compactas de un dígito; aceptar tokens separados por espacios.
        String[] tokens;
        if (postfija.chars().anyMatch(Character::isWhitespace)) tokens = postfija.trim().split("\\s+");
        else {
            tokens = new String[postfija.length()];
            for (int i = 0; i < postfija.length(); i++) tokens[i] = postfija.substring(i, i + 1);
        }
        for (String token : tokens) {
            try {
                if (esOperando(token)) {
                    double numero = Double.parseDouble(token);
                    if (!Double.isFinite(numero) || !pila.poner(numero)) return null;
                } else {
                    if (token.length() != 1) return null;
                    Object op2 = pila.quitar(), op1 = pila.quitar();
                    if (!(op1 instanceof Number) || !(op2 instanceof Number)) return null;
                    Double resultado = operacionAritmetica(token.charAt(0), ((Number) op1).doubleValue(), ((Number) op2).doubleValue());
                    if (resultado == null || !Double.isFinite(resultado) || !pila.poner(resultado)) return null;
                }
            } catch (NumberFormatException e) { return null; }
        }
        Object resultado = pila.quitar();
        return resultado instanceof Double && pila.vacio() ? (Double) resultado : null;
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
