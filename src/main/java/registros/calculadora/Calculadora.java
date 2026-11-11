package registros.calculadora;

import colecciones.edlineal.pilas.PilaFija;
import utils.entradasalida.cadenas.Entrada;
import utils.entradasalida.cadenas.Salida;

public class Calculadora {
    protected PilaFija pilaOperadores;
    protected PilaFija pilaEvaluacion;
    protected String expresionInfija;
    protected String expresionPostfija;

    public Calculadora(int capacidad) {
        pilaOperadores = new PilaFija(capacidad);
        pilaEvaluacion = new PilaFija(capacidad);
        expresionInfija = "";
        expresionPostfija = "";
    }

    public void procesarExpresion() {
        Salida.salidaPorDefecto("Ingrese la expresión matemática: ");
        expresionInfija = Entrada.terminalCadenas();

        solicitarValoresVariables();

        convertirPostfija();
        double resultado = evaluarExpresion();

        Salida.salidaPorDefecto("\nExpresión infija con valores: " + expresionInfija + "\n");
        Salida.salidaPorDefecto("Expresión postfija: " + expresionPostfija + "\n");
        Salida.salidaPorDefecto("Resultado: " + resultado + "\n");
    }

    public String convertirPostfija() {
        expresionPostfija = "";
        pilaOperadores.vaciar();

        for (int posicionActual = 0; posicionActual < expresionInfija.length(); posicionActual++) {
            char caracterActual = expresionInfija.charAt(posicionActual);

            if (caracterActual == ' ') {
                continue; // Ignorar espacios
            }

            if (esOperando(caracterActual)) {
                // Manejar operandos (números/variables)
                StringBuilder operandoActual = new StringBuilder();
                while (posicionActual < expresionInfija.length() &&
                        (Character.isLetterOrDigit(expresionInfija.charAt(posicionActual)) ||
                                expresionInfija.charAt(posicionActual) == '.')) {
                    operandoActual.append(expresionInfija.charAt(posicionActual));
                    posicionActual++;
                }
                posicionActual--; // Retroceder para no saltarse caracteres
                expresionPostfija += operandoActual.toString() + " ";
            } else if (caracterActual == '(') {
                pilaOperadores.poner(caracterActual); // Apilar '('
            } else if (caracterActual == ')') {
                // Desapilar hasta encontrar '('
                while (!pilaOperadores.vacio() && (char) pilaOperadores.verTope() != '(') {
                    expresionPostfija += pilaOperadores.quitar() + " ";
                }
                pilaOperadores.quitar(); // Eliminar '(' sin enviar a EPOS
            } else if (esOperador(caracterActual)) {
                // Desapilar operadores de mayor o igual precedencia
                while (!pilaOperadores.vacio() &&
                        precedencia((char) pilaOperadores.verTope()) >= precedencia(caracterActual)) {
                    expresionPostfija += pilaOperadores.quitar() + " ";
                }
                pilaOperadores.poner(caracterActual); // Apilar operador actual
            }
        }

        // Desapilar operadores restantes
        while (!pilaOperadores.vacio()) {
            expresionPostfija += pilaOperadores.quitar() + " ";
        }

        return expresionPostfija.trim();
    }

    public double evaluarExpresion() {
        pilaEvaluacion.vaciar();
        String[] tokensPostfijos = expresionPostfija.split(" ");

        for (String tokenActual : tokensPostfijos) {
            if (tokenActual.isEmpty()) continue;

            if (esNumero(tokenActual)) {
                pilaEvaluacion.poner(Double.parseDouble(tokenActual)); // Apilar operandos
            } else if (tokenActual.length() == 1 && esOperador(tokenActual.charAt(0))) {
                double operandoDerecho = (double) pilaEvaluacion.quitar(); //op2
                double operandoIzquierdo = (double) pilaEvaluacion.quitar(); //op1
                double resultadoParcial = aplicarOperador(tokenActual.charAt(0), operandoIzquierdo, operandoDerecho);
                pilaEvaluacion.poner(resultadoParcial); // Apilar resultado
            }
        }

        return (double) pilaEvaluacion.quitar(); // Resultado final
    }

    public void solicitarValoresVariables() {
        StringBuilder nuevaExpresion = new StringBuilder();

        for (int posicionCaracter = 0; posicionCaracter < expresionInfija.length(); posicionCaracter++) {
            char caracterActual = expresionInfija.charAt(posicionCaracter);

            if (Character.isLetter(caracterActual)) {
                Salida.salidaPorDefecto("Ingrese el valor para " + caracterActual + ": ");
                String valorVariable = Entrada.terminalCadenas();

                try {
                    Double.parseDouble(valorVariable);
                    nuevaExpresion.append(valorVariable);
                } catch (NumberFormatException e) {
                    nuevaExpresion.append(caracterActual);
                    Salida.salidaPorDefecto("Valor inválido. Se mantendrá la variable " + caracterActual + "\n");
                }
            } else {
                nuevaExpresion.append(caracterActual);
            }
        }

        expresionInfija = nuevaExpresion.toString();
    }

    protected boolean esOperando(char caracter) {
        return Character.isLetterOrDigit(caracter) || caracter == '.';
    }

    protected boolean esOperador(char operador) {
        return operador == '+' || operador == '-' || operador == '*' || operador == '/' || operador == '^';
    }

    protected boolean esNumero(String posibleNumero) {
        try {
            Double.parseDouble(posibleNumero);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    protected int precedencia(char operador) {
        switch (operador) {
            case '^':
                return 4;
            case '*':
            case '/':
                return 3;
            case '+':
            case '-':
                return 2;
            default:
                return 0;
        }
    }

    protected double aplicarOperador(char operador, double operandoIzquierdo, double operandoDerecho) {
        switch (operador) {
            case '+':
                return operandoIzquierdo + operandoDerecho;
            case '-':
                return operandoIzquierdo - operandoDerecho;
            case '*':
                return operandoIzquierdo * operandoDerecho;
            case '/':
                return operandoIzquierdo / operandoDerecho;
            case '^':
                return Math.pow(operandoIzquierdo, operandoDerecho);
            default:
                return 0;
        }
    }
}