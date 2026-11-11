package principales.registros.calculadora;

import utils.encriptadores.Encriptador;

public class PrincipalEncriptador {
    public static void main(String[] args) {
        Encriptador encriptador = new Encriptador(100);

        encriptador.solicitarDatosEncriptacion();

        encriptador.solicitarDesencriptacion();
    }
}
