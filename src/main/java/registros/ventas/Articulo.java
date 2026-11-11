/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registros.ventas;

/**
 *
 * @author Usuario
 */
public class Articulo {
    protected String clave;
    protected String descripcion;
    protected double PrecioUni;

    public Articulo(String clave, String descripcion, double PrecioUni) {
        this.clave = clave;
        this.descripcion = descripcion;
        this.PrecioUni = PrecioUni;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioUni() {
        return PrecioUni;
    }

    public void setPrecioUni(double PrecioUni) {
        this.PrecioUni = PrecioUni;
    }

    @Override
    public String toString() {
        return clave;
    }
}
