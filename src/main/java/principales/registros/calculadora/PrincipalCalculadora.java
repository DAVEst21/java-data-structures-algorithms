package principales.registros.calculadora;

import registros.calculadora.Calculadora;

public class PrincipalCalculadora {
    public static void main(String[] args) {
        // Crear calculadora con capacidad suficiente
        Calculadora calculadora = new Calculadora(100);

        calculadora.procesarExpresion();
    }
}