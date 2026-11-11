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
        if (expresionInfija == null) throw new IllegalArgumentException("La expresión es obligatoria");
        boolean esperaOperando = true;

        for (int posicionActual = 0; posicionActual < expresionInfija.length(); posicionActual++) {
            char caracterActual = expresionInfija.charAt(posicionActual);

            if (Character.isWhitespace(caracterActual)) {
                continue; // Ignorar espacios
            }

            if (esOperando(caracterActual)) {
                if (!esperaOperando) throw new IllegalArgumentException("Falta un operador");
                // Manejar operandos (números/variables)
                StringBuilder operandoActual = new StringBuilder();
                while (posicionActual < expresionInfija.length() &&
                        (Character.isLetterOrDigit(expresionInfija.charAt(posicionActual)) ||
                                expresionInfija.charAt(posicionActual) == '.')) {
                    operandoActual.append(expresionInfija.charAt(posicionActual));
                    posicionActual++;
                }
                posicionActual--; // Retroceder para no saltarse caracteres
                if (!esNumero(operandoActual.toString())) throw new IllegalArgumentException("Operando inválido");
                expresionPostfija += operandoActual.toString() + " ";
                esperaOperando = false;
            } else if (caracterActual == '(') {
                if (!esperaOperando) throw new IllegalArgumentException("Falta un operador antes del paréntesis");
                if (!pilaOperadores.poner(caracterActual)) throw new IllegalArgumentException("Capacidad de operadores excedida"); // Apilar '('
            } else if (caracterActual == ')') {
                if (esperaOperando) throw new IllegalArgumentException("Falta un operando");
                // Desapilar hasta encontrar '('
                while (!pilaOperadores.vacio() && (char) pilaOperadores.verTope() != '(') {
                    expresionPostfija += pilaOperadores.quitar() + " ";
                }
                if (pilaOperadores.vacio()) throw new IllegalArgumentException("Paréntesis sin apertura");
                pilaOperadores.quitar(); // Eliminar '(' sin enviar a EPOS
            } else if (esOperador(caracterActual)) {
                if (esperaOperando) throw new IllegalArgumentException("Falta un operando; use operadores binarios");
                // Desapilar operadores de mayor o igual precedencia
                while (!pilaOperadores.vacio() &&
                        (precedencia((char) pilaOperadores.verTope()) > precedencia(caracterActual) ||
                                (caracterActual != '^' && precedencia((char) pilaOperadores.verTope()) == precedencia(caracterActual)))) {
                    expresionPostfija += pilaOperadores.quitar() + " ";
                }
                if (!pilaOperadores.poner(caracterActual)) throw new IllegalArgumentException("Capacidad de operadores excedida"); // Apilar operador actual
                esperaOperando = true;
            } else {
                throw new IllegalArgumentException("Carácter no válido: " + caracterActual);
            }
        }

        if (esperaOperando) throw new IllegalArgumentException("Expresión vacía o incompleta");
        // Desapilar operadores restantes
        while (!pilaOperadores.vacio()) {
            if ((char) pilaOperadores.verTope() == '(') throw new IllegalArgumentException("Paréntesis sin cierre");
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
                if (!pilaEvaluacion.poner(Double.parseDouble(tokenActual))) throw new IllegalArgumentException("Capacidad de operandos excedida"); // Apilar operandos
            } else if (tokenActual.length() == 1 && esOperador(tokenActual.charAt(0))) {
                Object derecho = pilaEvaluacion.quitar(); //op2
                Object izquierdo = pilaEvaluacion.quitar(); //op1
                if (!(derecho instanceof Number) || !(izquierdo instanceof Number)) throw new IllegalArgumentException("Faltan operandos");
                double operandoDerecho = ((Number) derecho).doubleValue();
                double operandoIzquierdo = ((Number) izquierdo).doubleValue();
                double resultadoParcial = aplicarOperador(tokenActual.charAt(0), operandoIzquierdo, operandoDerecho);
                if (!Double.isFinite(resultadoParcial) || !pilaEvaluacion.poner(resultadoParcial)) throw new IllegalArgumentException("Resultado fuera del rango numérico"); // Apilar resultado
            } else {
                throw new IllegalArgumentException("Token inválido: " + tokenActual);
            }
        }

        Object resultado = pilaEvaluacion.quitar();
        if (!(resultado instanceof Number) || !pilaEvaluacion.vacio()) throw new IllegalArgumentException("Expresión postfija inválida");
        return ((Number) resultado).doubleValue(); // Resultado final
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
            return Double.isFinite(Double.parseDouble(posibleNumero));
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
                if (operandoDerecho == 0) throw new IllegalArgumentException("División entre cero");
                return operandoIzquierdo / operandoDerecho;
            case '^':
                return Math.pow(operandoIzquierdo, operandoDerecho);
            default:
                throw new IllegalArgumentException("Operador inválido");
        }
    }
    public String convertirPostfija(String infija) {
        expresionInfija = infija;
        return convertirPostfija();
    }
}