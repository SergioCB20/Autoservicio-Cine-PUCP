package pe.com.cinepucp.autoservicio.model.venta;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import pe.com.cinepucp.autoservicio.model.adapters.LocalDateTimeAdapter;

import pe.com.cinepucp.autoservicio.model.auth.Usuario;

@XmlAccessorType(XmlAccessType.FIELD)
public class Venta {
    private Integer ventaId;
    private Usuario usuario;
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss") 
    private LocalDateTime fechaHora;
    private Double subtotal;
    private Double impuestos;
    private Double total;
    private EstadoVenta estado;
    private MetodoPago metodoPago;
    private List<Boleto> boletos = new ArrayList<>();
    private List<VentaProducto> productosVendidos = new ArrayList<>();
    private List<Cupon> cuponesAplicados = new ArrayList<>();
    
    @Override
    public String toString() {
        return "Venta{" +
               "ventaId=" + ventaId +
               ", usuario=" + (usuario != null ? usuario.getId(): null) + // Mostrar ID del usuario
               ", fechaHora=" + fechaHora +
               ", subtotal=" + subtotal +
               ", impuestos=" + impuestos +
               ", total=" + total +
               ", estado=" + estado +
               ", metodoPago=" + metodoPago +
               ", boletos=" + (boletos != null ? boletos.size() : 0) + " boletos" +
               ", productosVendidos=" + (getProductosVendidos() != null ? getProductosVendidos().size() : 0) + " productos" +
               ", cuponesAplicados=" + (getCuponesAplicados() != null ? getCuponesAplicados().size() : 0) + " cupones" +
               '}';
    }
    
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

    public List<VentaProducto> getProductosVendidos() {
        return productosVendidos;
    }

    public void setProductosVendidos(List<VentaProducto> productosVendidos) {
        this.productosVendidos = productosVendidos;
    }

    public List<Cupon> getCuponesAplicados() {
        return cuponesAplicados;
    }

    public void setCuponesAplicados(List<Cupon> cuponesAplicados) {
        this.cuponesAplicados = cuponesAplicados;
    }

 
}
