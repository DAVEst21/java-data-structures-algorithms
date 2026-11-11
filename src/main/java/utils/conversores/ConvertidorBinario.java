package utils.conversores;

public class ConvertidorBinario {
    // Convierte un número decimal a binario recursivamente
    private static String decimalABinarioRecursivo(long numero, StringBuilder resultado) {
        // Caso base: si el número es 0 y no hay dígitos, retorna "0"
        if (numero == 0 && resultado.length() == 0) {
            return "0";
        }
        // Caso base: si el número es 0, retorna el resultado invertido
        if (numero == 0) {
            return resultado.reverse().toString();
        }
        // Obtiene el resto de dividir por 2
        long resto = numero % 2;
        // Agrega el dígito binario (0 o 1)
        resultado.append(resto);
        // Caso especial: si el número es 1, retorna el resultado invertido
        if (numero == 1) {
            return resultado.reverse().toString();
        }
        // Llama recursivamente con el cociente
        return decimalABinarioRecursivo(numero / 2, resultado);
    }

    // Inicia la conversión de un número decimal a binario
    public static String decimalABinario(long numero) {
        // Verifica si el número es negativo
        if (numero < 0) {
            return null;
        }
        // Inicia la conversión recursiva
        return decimalABinarioRecursivo(numero, new StringBuilder());
    }
}