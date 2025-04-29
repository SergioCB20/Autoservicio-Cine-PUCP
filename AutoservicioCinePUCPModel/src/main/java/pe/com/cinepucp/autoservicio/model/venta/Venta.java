package pe.com.cinepucp.autoservicio.model.venta;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import pe.com.cinepucp.autoservicio.model.auth.Usuario;

public class Venta {
    private Integer ventaId;
    private Usuario usuario;
    private LocalDateTime fechaHora;
    private Double subtotal;
    private Double impuestos;
    private Double total;
    private EstadoVenta estado;
    private MetodoPago metodoPago;
    private String codigoQr;
    private List<Boleto> boletos = new ArrayList<>();
    private List<VentaProducto> productosVendidos = new ArrayList<>();
    private List<Cupon> cuponesAplicados = new ArrayList<>();

    public Venta(){

    }

    public Venta(Usuario usuario, LocalDateTime fechaHora, Double subtotal, Double impuestos, Double total,
            EstadoVenta estado, MetodoPago metodoPago, String codigoQr,
            List<Boleto> boletos,List<VentaProducto> productosVendidos,
            List<Cupon> cuponesAplicados) {
        this.usuario= usuario;
        this.fechaHora = fechaHora;
        this.subtotal = subtotal;
        this.impuestos = impuestos;
        this.total = total;
        this.estado = estado;
        this.metodoPago = metodoPago;
        this.codigoQr = codigoQr;
        this.boletos = boletos;
        this.productosVendidos = productosVendidos;
        this.cuponesAplicados = cuponesAplicados;
    }

    public Integer getVentaId() {
        return ventaId;
    }

    public void setVentaId(Integer ventaId) {
        this.ventaId = ventaId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(Double impuestos) {
        this.impuestos = impuestos;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public EstadoVenta getEstado() {
        return estado;
    }

    public void setEstado(EstadoVenta estado) {
        this.estado = estado;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getCodigoQr() {
        return codigoQr;
    }

    public void setCodigoQr(String codigoQr) {
        this.codigoQr = codigoQr;
    }
}
