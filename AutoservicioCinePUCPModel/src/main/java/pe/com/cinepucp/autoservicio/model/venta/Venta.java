package pe.com.cinepucp.autoservicio.model.venta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import pe.com.cinepucp.autoservicio.model.auth.Usuario;

public class Venta {
    private int id;
    private Usuario usuario;
    

    private LocalDateTime fechaHora;
    private BigDecimal subtotal;
    private BigDecimal impuestos;
    private BigDecimal total;
    private String estado;
    private String metodoPago;
    private String codigoQr;

    private List<Boleto> boletos;

    public List<Boleto> getBoletos() {
        return boletos;
    }

    public void setBoletos(List<Boleto> boletos) {
        this.boletos = boletos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(BigDecimal impuestos) {
        this.impuestos = impuestos;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getCodigoQr() {
        return codigoQr;
    }

    public void setCodigoQr(String codigoQr) {
        this.codigoQr = codigoQr;
    }

    public Venta(){

    }

    public Venta(int id, LocalDateTime fechaHora, BigDecimal subtotal, BigDecimal impuestos, BigDecimal total,
            String estado, String metodoPago, String codigoQr) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.subtotal = subtotal;
        this.impuestos = impuestos;
        this.total = total;
        this.estado = estado;
        this.metodoPago = metodoPago;
        this.codigoQr = codigoQr;
    }

    

}
