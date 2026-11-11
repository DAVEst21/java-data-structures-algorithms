/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principales.arreglos;

import colecciones.edlineal.arreglos.ArregloOrdenado;
import utils.entradasalida.cadenas.Entrada;
import utils.entradasalida.cadenas.Salida;

/**
 *
 * @author Usuario
 */
public class PrincipalAOrdenado {
    public static void main(String[] args) {
        ArregloOrdenado indice = new ArregloOrdenado(100, ArregloOrdenado.TipoOrdenamiento.INC);
        double opcion;

        do {
            Salida.salidaPorDefecto("\n--- Menú de Índice de Términos ---\n");
            Salida.salidaPorDefecto("1. Agregar término/subtérmino y páginas\n");
            Salida.salidaPorDefecto("2. Consultar término\n");
            Salida.salidaPorDefecto("3. Consultar subtérmino\n");
            Salida.salidaPorDefecto("4. Listar todos los términos/subtérminos\n");
            Salida.salidaPorDefecto("5. Listar términos por rango de letras\n");
            Salida.salidaPorDefecto("0. Salir\n");
            Salida.salidaPorDefecto("Seleccione una opción: ");
            opcion = Entrada.terminalDouble();
            int opcionSwitch = (int) opcion;
            switch (opcionSwitch) {
                case 1 -> agregarTermino(indice);
                case 2 -> consultarTermino(indice);
                case 3 -> consultarSubtermino(indice);
                case 4 -> listarTerminos(indice);
                case 5 -> listarTerminosPorRango(indice);
                case 0 -> Salida.salidaPorDefecto("Saliendo...\n");
                default -> Salida.salidaPorDefecto("Opción no válida.\n");
            }
        } while (opcion != 0);
    }

    private static void agregarTermino(ArregloOrdenado indice) {
        Salida.salidaPorDefecto("Ingrese el término principal: ");
        String terminoPrincipal = Entrada.terminalCadenas().trim();

        Salida.salidaPorDefecto("¿Desea agregar un subtérmino? (S/N): ");
        String respuesta = Entrada.terminalCadenas().trim();

        if (respuesta.equalsIgnoreCase("S")) {
            Salida.salidaPorDefecto("Ingrese el subtérmino: ");
            String subtermino = Entrada.terminalCadenas().trim();
            Salida.salidaPorDefecto("Ingrese las páginas para el subtérmino (separadas por comas o rangos con guiones): ");
            String paginasSubtermino = Entrada.terminalCadenas().trim();

            if (validarPaginas(paginasSubtermino)) {
                String entradaSubtermino = terminoPrincipal + " > " + subtermino + " - " + paginasSubtermino;
                if (indice.poner(entradaSubtermino) != -1) {
                    Salida.salidaPorDefecto("Subtérmino agregado correctamente.\n");
                } else {
                    Salida.salidaPorDefecto("Error: No se pudo agregar el subtérmino.\n");
                }
            } else {
                Salida.salidaPorDefecto("Formato de páginas no válido para el subtérmino.\n");
            }
        } else {
            Salida.salidaPorDefecto("Ingrese las páginas para el término principal (separadas por comas o rangos con guiones): ");
            String paginasTermino = Entrada.terminalCadenas().trim();

            if (validarPaginas(paginasTermino)) {
                String entradaTermino = terminoPrincipal + " - " + paginasTermino;
                if (indice.poner(entradaTermino) != -1) {
                    Salida.salidaPorDefecto("Término principal agregado correctamente.\n");
                } else {
                    Salida.salidaPorDefecto("Error: No se pudo agregar el término principal.\n");
                }
            } else {
                Salida.salidaPorDefecto("Formato de páginas no válido para el término principal.\n");
            }
        }
    }

    private static boolean validarPaginas(String paginas) {
        return paginas.matches("^[0-9,-]+$");
    }

    private static void consultarTermino(ArregloOrdenado indice) {
        Salida.salidaPorDefecto("Ingrese el término a buscar: ");
        String termino = Entrada.terminalCadenas().trim();

        boolean encontrado = false;
        Salida.salidaPorDefecto("Resultados de la búsqueda:\n");

        for (int i = 0; i < indice.cantidad(); i++) {
            String entrada = (String) indice.obtener(i);

            if (entrada.toLowerCase().contains(termino.toLowerCase())) {
                encontrado = true;
                Salida.salidaPorDefecto("- " + entrada + "\n");
            }
        }

        if (!encontrado) {
            Salida.salidaPorDefecto("No se encontraron resultados para el término: " + termino + "\n");
        }
    }

    private static void consultarSubtermino(ArregloOrdenado indice) {
        Salida.salidaPorDefecto("Ingrese el subtérmino a buscar: ");
        String subtermino = Entrada.terminalCadenas().trim();

        boolean encontrado = false;
        Salida.salidaPorDefecto("Resultados de la búsqueda:\n");

        for (int i = 0; i < indice.cantidad(); i++) {
            String entrada = (String) indice.obtener(i);

            if (entrada.toLowerCase().contains(" > " + subtermino.toLowerCase())) {
                encontrado = true;
                Salida.salidaPorDefecto("- " + entrada + "\n");
            }
        }

        if (!encontrado) {
            Salida.salidaPorDefecto("No se encontraron resultados para el subtérmino: " + subtermino + "\n");
        }
    }

    private static void listarTerminos(ArregloOrdenado indice) {
        Salida.salidaPorDefecto("Lista de términos/subtérminos:\n");
        indice.imprimir();
    }

    private static void listarTerminosPorRango(ArregloOrdenado indice) {
        Salida.salidaPorDefecto("Ingrese la letra inicial: ");
        char inicio = Entrada.terminalCadenas().trim().charAt(0);
        Salida.salidaPorDefecto("Ingrese la letra final: ");
        char fin = Entrada.terminalCadenas().trim().charAt(0);

        Salida.salidaPorDefecto("Términos entre " + inicio + " y " + fin + ":\n");
        for (int i = 0; i < indice.cantidad(); i++) {
            String termino = (String) indice.obtener(i);
            if (termino.charAt(0) >= inicio && termino.charAt(0) <= fin) {
                Salida.salidaPorDefecto(termino + "\n");
            }
        }
    }
}
