/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.model.venta;
import java.math.BigDecimal;
import pe.com.cinepucp.autoservicio.model.Peliculas.Funcion;
import pe.com.cinepucp.autoservicio.model.salas.Asiento;
/**
 *
 * @author Piero
 */
public class Boleto {

    private int id;
    private Funcion funcion;
    private String tipo;
    private BigDecimal precio;
    private String codigoQr;
    private boolean usado;
    private Asiento asiento;
//    private Venta venta;
    
    public Boleto() {
    }

    public Boleto(BigDecimal precio, String codigoQr, String tipo, boolean usado) {
        this.precio = precio;
        this.codigoQr = codigoQr;
        this.tipo = tipo;
        this.usado = usado;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }
    
    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getCodigoQr() {
        return codigoQr;
    }

    public void setCodigoQr(String codigoQr) {
        this.codigoQr = codigoQr;
    }

    public boolean isUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public Asiento getAsiento() {
        return asiento;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }
    
//    public Venta getVenta() {
//        return venta;
//    }
//
//    public void setVenta(Venta venta) {
//        this.venta = venta;
//    }
}
