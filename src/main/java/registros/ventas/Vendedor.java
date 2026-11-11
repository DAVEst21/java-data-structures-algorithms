/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registros.ventas;

/**
 *
 * @author Usuario
 */
public class Vendedor {
    protected String nombre;
    protected String RFC;
    protected char sexo;
    protected int numeroVendedor;
    protected String fechaNacimiento;

    public Vendedor(String nombre, String RFC, int numeroVendedor, String fechaNacimiento) {
        this.nombre = nombre;
        this.RFC = RFC;
        this.sexo = sexo;
        this.numeroVendedor = numeroVendedor;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public int getNumeroVendedor() {
        return numeroVendedor;
    }

    public void setNumeroVendedor(int numeroVendedor) {
        this.numeroVendedor = numeroVendedor;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return numeroVendedor + "";
    }
}
