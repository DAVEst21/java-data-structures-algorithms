package utils.validadores;

public class ValidadorBinario {
    // Método recursivo para verificar si un número es binario
    private static boolean esBinarioRecursivo(long numero) {
        // Caso base: si el número es 0, hemos verificado todos los dígitos
        if (numero == 0) {
            return true;
        }
        // Obtener el dígito menos significativo
        long digito = numero % 10;
        // Verificar si el dígito es 0 o 1
        if (digito != 0 && digito != 1) {
            return false;
        }
        // Llamada recursiva con el número dividido por 10
        return esBinarioRecursivo(numero / 10);
    }

    // Método público para iniciar la verificación
    public static boolean esBinario(long numero) {
        if (numero < 0) {
            return false;
        }
        // Manejar el caso de 0
        if (numero == 0) {
            return true;
        }
        return esBinarioRecursivo(numero);
    }
}