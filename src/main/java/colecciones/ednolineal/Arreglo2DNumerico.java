package colecciones.ednolineal;

import colecciones.edlineal.arreglos.ArregloNumerico;

public class Arreglo2DNumerico extends Arreglo2D {
    public enum TipoLogaritmo {
        NATURAL, BASE10, BASE2
    }

    // 2: Constructor que inicializa con ceros
    public Arreglo2DNumerico(int filas, int columnas) {
        super(filas, columnas, 0.0);
    }

    // 3: Constructor que inicializa con un valor específico
    public Arreglo2DNumerico(int filas, int columnas, Object valor) {
        super(filas, columnas);
        if (valor instanceof Number) {
            rellenar(((Number) valor).doubleValue());
        } else {
            throw new IllegalArgumentException("El valor debe ser numérico");
        }
    }

    @Override
    public boolean cambiar(int fila, int col, Object valor) {
        if (valor instanceof Number) {
            return super.cambiar(fila, col, ((Number) valor).doubleValue());
        }
        return false;
    }

    @Override
    public void rellenar(Object valor) {
        if (valor instanceof Number) {
            super.rellenar(((Number) valor).doubleValue());
        } else {
            throw new IllegalArgumentException("El valor debe ser numérico");
        }
    }

    // 4: Multiplica toda la matriz por un escalar
    public boolean porEscalar(Number escalar) {
        if (vacia()) return false;

        double escalarValue = escalar.doubleValue();
        for (int i = 0; i < renglones; i++) {
            for (int j = 0; j < columnas; j++) {
                double valorActual = ((Number) datos[i][j]).doubleValue();
                datos[i][j] = valorActual * escalarValue;
            }
        }
        return true;
    }

    // 5: Multiplica cada elemento por un escalar correspondiente del arreglo
    public boolean porEscalares(ArregloNumerico escalares) {
        if (vacia() || escalares.vacia() || escalares.cantidad() != renglones * columnas) {
            return false;
        }

        int index = 0;
        for (int i = 0; i < renglones; i++) {
            for (int j = 0; j < columnas; j++) {
                double escalar = ((Number) escalares.obtener(index++)).doubleValue();
                double valorActual = ((Number) datos[i][j]).doubleValue();
                datos[i][j] = valorActual * escalar;
            }
        }
        return true;
    }

    // 6: Suma un escalar a toda la matriz
    public boolean sumarEscalar(Number escalar) {
        if (vacia()) return false;

        double escalarValue = escalar.doubleValue();
        for (int i = 0; i < renglones; i++) {
            for (int j = 0; j < columnas; j++) {
                double valorActual = ((Number) datos[i][j]).doubleValue();
                datos[i][j] = valorActual + escalarValue;
            }
        }
        return true;
    }

    // 7: Suma a cada elemento un escalar correspondiente del arreglo
    public boolean sumarEscalares(ArregloNumerico escalares) {
        if (vacia() || escalares.vacia() || escalares.cantidad() != renglones * columnas) {
            return false;
        }

        int index = 0;
        for (int i = 0; i < renglones; i++) {
            for (int j = 0; j < columnas; j++) {
                double escalar = ((Number) escalares.obtener(index++)).doubleValue();
                double valorActual = ((Number) datos[i][j]).doubleValue();
                datos[i][j] = valorActual + escalar;
            }
        }
        return true;
    }

    // 8: Multiplica esta matriz por otra matriz
    public boolean multiplicar(Arreglo2DNumerico matriz2) {
        if (vacia() || matriz2.vacia() || columnas != matriz2.renglones) {
            return false;
        }

        Object[][] resultado = new Object[renglones][matriz2.columnas];

        for (int i = 0; i < renglones; i++) {
            for (int j = 0; j < matriz2.columnas; j++) {
                double suma = 0.0;
                for (int k = 0; k < columnas; k++) {
                    double a = ((Number) datos[i][k]).doubleValue();
                    double b = ((Number) matriz2.datos[k][j]).doubleValue();
                    suma += a * b;
                }
                resultado[i][j] = suma;
            }
        }

        // Actualizar la matriz actual con el resultado
        datos = resultado;
        columnas = matriz2.columnas;
        return true;
    }

    // 9: Suma esta matriz con otra matriz
    public boolean sumar(Arreglo2DNumerico matriz2) {
        if (vacia() || matriz2.vacia() || renglones != matriz2.renglones || columnas != matriz2.columnas) {
            return false;
        }

        for (int i = 0; i < renglones; i++) {
            for (int j = 0; j < columnas; j++) {
                double a = ((Number) datos[i][j]).doubleValue();
                double b = ((Number) matriz2.datos[i][j]).doubleValue();
                datos[i][j] = a + b;
            }
        }
        return true;
    }

    // 10: Aplica una potencia elemento por elemento
    public boolean aplicarPotencia(Number escalar) {
        if (vacia()) return false;

        double exponente = escalar.doubleValue();
        for (int i = 0; i < renglones; i++) {
            for (int j = 0; j < columnas; j++) {
                double base = ((Number) datos[i][j]).doubleValue();
                datos[i][j] = Math.pow(base, exponente);
            }
        }
        return true;
    }

    // 11: Aplica logaritmo elemento por elemento
    public boolean aplicarLogaritmo(TipoLogaritmo tipoLogaritmo) {
        if (vacia()) return false;

        // Verificar si hay valores <= 0
        for (int i = 0; i < renglones; i++) {
            for (int j = 0; j < columnas; j++) {
                double valor = ((Number) datos[i][j]).doubleValue();
                if (valor <= 0) {
                    return false;
                }
            }
        }

        // Aplicar logaritmo según el tipo
        for (int i = 0; i < renglones; i++) {
            for (int j = 0; j < columnas; j++) {
                double valor = ((Number) datos[i][j]).doubleValue();
                switch (tipoLogaritmo) {
                    case NATURAL:
                        datos[i][j] = Math.log(valor);
                        break;
                    case BASE10:
                        datos[i][j] = Math.log10(valor);
                        break;
                    case BASE2:
                        datos[i][j] = Math.log(valor) / Math.log(2);
                        break;
                }
            }
        }
        return true;
    }

    // 12: Genera una matriz diagonal con el valor proporcionado
    public boolean matrizDiagonal(Number contenido) {
        if (vacia() || renglones != columnas) {
            return false;
        }

        double valor = contenido.doubleValue();
        for (int i = 0; i < renglones; i++) {
            for (int j = 0; j < columnas; j++) {
                if (i == j) {
                    datos[i][j] = valor;
                } else {
                    datos[i][j] = 0.0;
                }
            }
        }
        return true;
    }

    // 13: Determina si la matriz es triangular superior
    public boolean esDiagonalSuperior() {
        if (vacia() || renglones != columnas) {
            return false;
        }

        for (int i = 0; i < renglones; i++) {
            for (int j = 0; j < i; j++) {
                double valor = ((Number) datos[i][j]).doubleValue();
                if (valor != 0.0) {
                    return false;
                }
            }
        }
        return true;
    }

    // 14: Determina si la matriz es triangular inferior
    public boolean esDiagonalInferior() {
        if (vacia() || renglones != columnas) {
            return false;
        }

        for (int i = 0; i < renglones; i++) {
            for (int j = i + 1; j < columnas; j++) {
                double valor = ((Number) datos[i][j]).doubleValue();
                if (valor != 0.0) {
                    return false;
                }
            }
        }
        return true;
    }

    // 15: Calcula la potencia de la matriz
    public boolean potencia(int exponente) {
        if (vacia() || renglones != columnas || exponente < 1) {
            return false;
        }

        Arreglo2DNumerico resultado = clonar();
        for (int i = 1; i < exponente; i++) {
            resultado.multiplicar(this);
        }

        this.datos = resultado.datos;
        return true;
    }

    // 16: Redimensiona la matriz sumando columnas adyacentes
    public boolean doblarRenglones() {
        if (vacia() || columnas < 2) {
            return false;
        }

        int nuevasColumnas;
        if (columnas % 2 == 0) {
            nuevasColumnas = columnas / 2;
        } else {
            nuevasColumnas = (columnas / 2) + 1;
        }

        Object[][] nueva = new Object[renglones][nuevasColumnas];

        for (int i = 0; i < renglones; i++) {
            for (int j = 0, k = 0; j < columnas; j += 2, k++) {
                if (j + 1 < columnas) {
                    // Sumar dos columnas adyacentes
                    double a = ((Number) datos[i][j]).doubleValue();
                    double b = ((Number) datos[i][j + 1]).doubleValue();
                    nueva[i][k] = a + b;
                } else {
                    // Columna central (cuando hay número impar de columnas)
                    nueva[i][k] = datos[i][j];
                }
            }
        }

        datos = nueva;
        columnas = nuevasColumnas;
        return true;
    }

    // 17: Redimensiona la matriz sumando filas adyacentes
    public boolean doblarColumnas() {
        if (vacia() || renglones < 2) {
            return false;
        }

        int nuevasFilas;
        if (renglones % 2 == 0) {
            nuevasFilas = renglones / 2;
        } else {
            nuevasFilas = (renglones / 2) + 1;
        }

        Object[][] nueva = new Object[nuevasFilas][columnas];

        for (int j = 0; j < columnas; j++) {
            for (int i = 0, k = 0; i < renglones; i += 2, k++) {
                if (i + 1 < renglones) {
                    // Sumar dos filas adyacentes
                    double a = ((Number) datos[i][j]).doubleValue();
                    double b = ((Number) datos[i + 1][j]).doubleValue();
                    nueva[k][j] = a + b;
                } else {
                    // Fila central (cuando hay número impar de filas)
                    nueva[k][j] = datos[i][j];
                }
            }
        }

        datos = nueva;
        renglones = nuevasFilas;
        return true;
    }

    // auxiliar para verificar si la matriz está vacía
    private boolean vacia() {
        return renglones == 0 || columnas == 0;
    }

    // Clona la matriz actual
    @Override
    public Arreglo2DNumerico clonar() {
        Arreglo2DNumerico copia = new Arreglo2DNumerico(renglones, columnas);
        for (int i = 0; i < renglones; i++) {
            for (int j = 0; j < columnas; j++) {
                copia.datos[i][j] = datos[i][j];
            }
        }
        return copia;
    }
}
