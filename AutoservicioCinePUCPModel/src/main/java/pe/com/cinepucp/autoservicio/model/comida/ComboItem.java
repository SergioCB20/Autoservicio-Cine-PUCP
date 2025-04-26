/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.model.comida;

/**
 *
 * @author Sergio
 */
public class ComboItem {
    private Producto combo;
    private Producto producto;
    private int cantidad;

    /**
     * @return the combo
     */
    public Producto getCombo() {
        return combo;
    }

    /**
     * @param combo the combo to set
     */
    public void setCombo(Producto combo) {
        this.combo = combo;
    }

    /**
     * @return the producto
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
