package utils.encriptadores;

import colecciones.edlineal.arreglos.Arreglo;
import colecciones.edlineal.pilas.PilaFija;
import utils.entradasalida.cadenas.Entrada;
import utils.entradasalida.cadenas.Salida;

public class Encriptador {
    private PilaFija pilaCaracteres;
    private Arreglo posicionesParentesis;
    private Arreglo tamaniosParentesis;

    public Encriptador(int capacidadMaxima) {
        pilaCaracteres = new PilaFija(capacidadMaxima);
        posicionesParentesis = new Arreglo(capacidadMaxima);
        tamaniosParentesis = new Arreglo(capacidadMaxima);
    }

    public void solicitarDatosEncriptacion() {
        Salida.salidaPorDefecto("Ingrese el texto a encriptar: ");
        String textoOriginal = Entrada.terminalCadenas();

        Salida.salidaPorDefecto("Ingrese cantidad de agrupaciones: ");
        int cantidadAgrupaciones = Integer.parseInt(Entrada.terminalCadenas());

        posicionesParentesis.vaciar();
        tamaniosParentesis.vaciar();

        for (int contadorAgrupaciones = 0; contadorAgrupaciones < cantidadAgrupaciones; contadorAgrupaciones++) {
            Salida.salidaPorDefecto("Posición " + (contadorAgrupaciones + 1) + ": ");
            int posicionActual = Integer.parseInt(Entrada.terminalCadenas());
            posicionesParentesis.poner(posicionActual);

            Salida.salidaPorDefecto("Tamaño " + (contadorAgrupaciones + 1) + ": ");
            int tamanioActual = Integer.parseInt(Entrada.terminalCadenas());
            tamaniosParentesis.poner(tamanioActual);
        }

        String textoEncriptado = encriptar(textoOriginal);
        Salida.salidaPorDefecto("\nTexto encriptado: " + textoEncriptado + "\n");
    }

    public void solicitarDesencriptacion() {
        Salida.salidaPorDefecto("Ingrese el texto a desencriptar: ");
        String textoEncriptado = Entrada.terminalCadenas();

        String textoOriginal = desencriptar(textoEncriptado);
        Salida.salidaPorDefecto("Texto desencriptado: " + textoOriginal + "\n");
    }

    public String encriptar(String textoOriginal) {
        if (!validarParametros(textoOriginal)) {
            return textoOriginal;
        }

        StringBuilder textoEncriptado = new StringBuilder();
        int indiceTexto = 0;

        for (int contadorParentesis = 0; contadorParentesis < posicionesParentesis.cantidad(); contadorParentesis++) {
            int posicionInicio = (int) posicionesParentesis.obtener(contadorParentesis);
            int tamanioSeccion = (int) tamaniosParentesis.obtener(contadorParentesis);

            // Agregar parte normal antes del paréntesis
            textoEncriptado.append(textoOriginal.substring(indiceTexto, posicionInicio));

            // Procesar sección a invertir
            String seccionOriginal = textoOriginal.substring(posicionInicio, posicionInicio + tamanioSeccion);
            textoEncriptado.append("(").append(invertirTexto(seccionOriginal)).append(")");

            indiceTexto = posicionInicio + tamanioSeccion;
        }

        // Agregar lo que queda después del último paréntesis
        textoEncriptado.append(textoOriginal.substring(indiceTexto));

        return textoEncriptado.toString();
    }

    public String desencriptar(String textoEncriptado) {
        StringBuilder textoOriginal = new StringBuilder();
        StringBuilder seccionEncriptada = new StringBuilder();
        boolean dentroDeParentesis = false;

        for (int posicionCaracter = 0; posicionCaracter < textoEncriptado.length(); posicionCaracter++) {
            char caracterActual = textoEncriptado.charAt(posicionCaracter);

            if (caracterActual == '(') {
                dentroDeParentesis = true;
                seccionEncriptada.setLength(0);
            } else if (caracterActual == ')') {
                dentroDeParentesis = false;
                textoOriginal.append(invertirTexto(seccionEncriptada.toString()));
            } else if (dentroDeParentesis) {
                seccionEncriptada.append(caracterActual);
            } else {
                textoOriginal.append(caracterActual);
            }
        }

        return textoOriginal.toString();
    }

    private String invertirTexto(String texto) {
        pilaCaracteres.vaciar();

        // Apilar caracteres
        for (int posicionCaracter = 0; posicionCaracter < texto.length(); posicionCaracter++) {
            pilaCaracteres.poner(texto.charAt(posicionCaracter));
        }

        // Desapilar para invertir
        StringBuilder textoInvertido = new StringBuilder();
        while (!pilaCaracteres.vacio()) {
            textoInvertido.append(pilaCaracteres.quitar());
        }

        return textoInvertido.toString();
    }

    private boolean validarParametros(String texto) {
        if (posicionesParentesis.cantidad() != tamaniosParentesis.cantidad()) {
            Salida.salidaPorDefecto("Error: Cantidad de posiciones no coincide con cantidad de tamaños\n");
            return false;
        }

        for (int contadorParentesis = 0; contadorParentesis < posicionesParentesis.cantidad(); contadorParentesis++) {
            int posicionActual = (int) posicionesParentesis.obtener(contadorParentesis);
            int tamanioActual = (int) tamaniosParentesis.obtener(contadorParentesis);

            if (posicionActual < 0 || posicionActual >= texto.length()) {
                Salida.salidaPorDefecto("Error: Posición " + posicionActual + " fuera de rango\n");
                return false;
            }

            if (tamanioActual <= 0 || posicionActual + tamanioActual > texto.length()) {
                Salida.salidaPorDefecto("Error: Tamaño " + tamanioActual + " inválido para posición " + posicionActual + "\n");
                return false;
            }

            // Verificar superposición con otros paréntesis
            for (int contadorComparacion = contadorParentesis + 1; contadorComparacion < posicionesParentesis.cantidad(); contadorComparacion++) {
                int otraPosicion = (int) posicionesParentesis.obtener(contadorComparacion);
                int otroTamanio = (int) tamaniosParentesis.obtener(contadorComparacion);

                if (!(posicionActual + tamanioActual <= otraPosicion ||
                        otraPosicion + otroTamanio <= posicionActual)) {
                    Salida.salidaPorDefecto("Error: Paréntesis en posición " + posicionActual +
                            " se solapa con posición " + otraPosicion + "\n");
                    return false;
                }
            }
        }

        return true;
    }
}