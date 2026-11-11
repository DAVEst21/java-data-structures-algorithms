package utils.encriptadores;

import colecciones.edlineal.arreglos.ArregloNumerico;

public class EncriptadorRecursivo {
    // Valida que las posiciones y tamaños sean correctos y no se solapen
    private static boolean validarPosiciones(ArregloNumerico posiciones, ArregloNumerico tamanios, int longitudCadena) {
        // Verifica que haya la misma cantidad de posiciones y tamaños
        if (posiciones.cantidad() != tamanios.cantidad()) {
            return false;
        }
        // Revisa cada posición y tamaño
        for (int i = 0; i < posiciones.cantidad(); i++) {
            int pos = ((Number) posiciones.obtener(i)).intValue();
            int tam = ((Number) tamanios.obtener(i)).intValue();
            // Comprueba que la posición y tamaño estén dentro de los límites
            if (pos < 0 || pos >= longitudCadena || tam <= 0 || pos + tam > longitudCadena) {
                return false;
            }
            // Verifica que no haya solapamiento con otras posiciones
            for (int j = i + 1; j < posiciones.cantidad(); j++) {
                int pos2 = ((Number) posiciones.obtener(j)).intValue();
                int tam2 = ((Number) tamanios.obtener(j)).intValue();
                if (pos < pos2 + tam2 && pos2 < pos + tam) {
                    return false;
                }
            }
        }
        return true;
    }

    // Encripta una cadena recursivamente insertando paréntesis e invirtiendo subcadenas
    private static String encriptarRecursivo(String cadena, ArregloNumerico posiciones, ArregloNumerico tamanios, int indice, int desplazamiento) {
        // Caso base: si no hay más posiciones que procesar, retorna la cadena
        if (indice >= posiciones.cantidad()) {
            return cadena;
        }
        // Obtiene la posición ajustada por los paréntesis previos y el tamaño
        int posOriginal = ((Number) posiciones.obtener(indice)).intValue();
        int posAjustada = posOriginal + desplazamiento;
        int tam = ((Number) tamanios.obtener(indice)).intValue();
        // Extrae la subcadena y la invierte
        String subcadena = cadena.substring(posAjustada, posAjustada + tam);
        StringBuilder subcadenaInvertida = new StringBuilder(subcadena).reverse();
        // Construye la nueva cadena con paréntesis
        String nuevaCadena = cadena.substring(0, posAjustada) + "(" + subcadenaInvertida + ")" +
                cadena.substring(posAjustada + tam);
        // Actualiza el desplazamiento por los paréntesis añadidos
        int nuevoDesplazamiento = desplazamiento + 2;
        // Llama recursivamente para la siguiente posición
        return encriptarRecursivo(nuevaCadena, posiciones, tamanios, indice + 1, nuevoDesplazamiento);
    }

    // Inicia la encriptación validando y ordenando posiciones
    public static String encriptar(String cadena, ArregloNumerico posiciones, ArregloNumerico tamanios) {
        // Valida las posiciones y tamaños
        if (!validarPosiciones(posiciones, tamanios, cadena.length())) {
            return null;
        }
        // Ordena posiciones y tamaños de menor a mayor
        ArregloNumerico posOrdenadas = new ArregloNumerico(posiciones.cantidad());
        ArregloNumerico tamOrdenados = new ArregloNumerico(tamanios.cantidad());
        for (int i = 0; i < posiciones.cantidad(); i++) {
            int minIndex = -1;
            int minPos = Integer.MAX_VALUE;
            for (int j = 0; j < posiciones.cantidad(); j++) {
                int pos = ((Number) posiciones.obtener(j)).intValue();
                if (pos < minPos && posOrdenadas.buscar(pos).equals(-1)) {
                    minPos = pos;
                    minIndex = j;
                }
            }
            posOrdenadas.poner(posiciones.obtener(minIndex));
            tamOrdenados.poner(tamanios.obtener(minIndex));
        }
        // Inicia la encriptación recursiva
        return encriptarRecursivo(cadena, posOrdenadas, tamOrdenados, 0, 0);
    }

    // Desencripta una cadena recursivamente eliminando paréntesis e invirtiendo subcadenas
    private static String desencriptarRecursivo(String cadena, int inicio, StringBuilder resultado) {
        // Caso base: si se llega al final de la cadena, retorna el resultado
        if (inicio >= cadena.length()) {
            return resultado.toString();
        }
        // Busca el próximo paréntesis de apertura
        int inicioPar = cadena.indexOf('(', inicio);
        if (inicioPar == -1) {
            // Si no hay más paréntesis, agrega el resto de la cadena
            resultado.append(cadena.substring(inicio));
            return resultado.toString();
        }
        // Busca el paréntesis de cierre
        int finPar = cadena.indexOf(')', inicioPar);
        if (finPar == -1) {
            // Si no hay cierre, agrega el resto de la cadena
            resultado.append(cadena.substring(inicio));
            return resultado.toString();
        }
        // Agrega la parte antes del paréntesis
        resultado.append(cadena.substring(inicio, inicioPar));
        // Invierte la subcadena dentro de los paréntesis
        String subcadena = cadena.substring(inicioPar + 1, finPar);
        resultado.append(new StringBuilder(subcadena).reverse());
        // Continúa recursivamente después del paréntesis
        return desencriptarRecursivo(cadena, finPar + 1, resultado);
    }

    // Inicia la desencriptación de una cadena
    public static String desencriptar(String cadena) {
        // Inicia la desencriptación recursiva con un StringBuilder vacío
        return desencriptarRecursivo(cadena, 0, new StringBuilder());
    }
}