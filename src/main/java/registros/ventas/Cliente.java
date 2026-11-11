/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registros.ventas;

/**
 *
 * @author Usuario
 */
public class Cliente {
    protected String RFC;
    protected String nombre;
    protected char sexo;

    public Cliente(String RFC, String nombre, char sexo) {
        this.RFC = RFC;
        this.nombre = nombre;
        this.sexo = sexo;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return RFC;
    }
}
