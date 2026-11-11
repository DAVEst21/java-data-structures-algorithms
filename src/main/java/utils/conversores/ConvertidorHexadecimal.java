package utils.conversores;

import colecciones.edlineal.arreglos.Arreglo;

public class ConvertidorHexadecimal {
    // Tabla de dígitos hexadecimales (0-9, A-F) almacenada en Arreglo
    private static final Arreglo HEX_DIGITOS = new Arreglo(16);
    static {
        // Inicializa los dígitos hexadecimales
        HEX_DIGITOS.poner('0');
        HEX_DIGITOS.poner('1');
        HEX_DIGITOS.poner('2');
        HEX_DIGITOS.poner('3');
        HEX_DIGITOS.poner('4');
        HEX_DIGITOS.poner('5');
        HEX_DIGITOS.poner('6');
        HEX_DIGITOS.poner('7');
        HEX_DIGITOS.poner('8');
        HEX_DIGITOS.poner('9');
        HEX_DIGITOS.poner('A');
        HEX_DIGITOS.poner('B');
        HEX_DIGITOS.poner('C');
        HEX_DIGITOS.poner('D');
        HEX_DIGITOS.poner('E');
        HEX_DIGITOS.poner('F');
    }

    // Convierte un número decimal a hexadecimal recursivamente
    private static String decimalAHexRecursivo(long numero, StringBuilder resultado) {
        // Caso base: si el número es 0 y no hay dígitos, retorna "0"
        if (numero == 0 && resultado.length() == 0) {
            return "0";
        }
        // Caso base: si el número es 0, retorna el resultado invertido
        if (numero == 0) {
            return resultado.reverse().toString();
        }
        // Obtiene el resto de dividir por 16
        int resto = (int) (numero % 16);
        // Agrega el dígito hexadecimal correspondiente al resto
        resultado.append((Character) HEX_DIGITOS.obtener(resto));
        // Llama recursivamente con el cociente
        return decimalAHexRecursivo(numero / 16, resultado);
    }

    // Inicia la conversión de un número decimal a hexadecimal
    public static String decimalAHex(long numero) {
        // Verifica si el número es negativo
        if (numero < 0) {
            return null;
        }
        // Inicia la conversión recursiva
        return decimalAHexRecursivo(numero, new StringBuilder());
    }
}