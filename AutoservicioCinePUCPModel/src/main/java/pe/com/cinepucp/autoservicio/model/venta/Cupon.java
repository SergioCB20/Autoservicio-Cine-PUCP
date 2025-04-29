package pe.com.cinepucp.autoservicio.model.venta;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import pe.com.cinepucp.autoservicio.model.auth.Usuario;

public class Cupon {
    
    private Integer cuponId;
    private String codigo;
    private String descripcionEs;
    private String descripcionEn;
    private TipoDescuento descuentoTipo;
    private Double descuentoValor;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Integer maxUsos;
    private Integer usosActuales;
    private Usuario creadoPor;
    private List<Usuario> usuarios = new ArrayList<>(); // Usuarios que tienen este cupón
    private List<Venta> ventas = new ArrayList<>(); // Ventas donde se aplicó este cupón

    public Cupon(){

    }
    
    public Cupon(String codigo, String descripcionEs, String descripcionEn, TipoDescuento descuentoTipo,
            Double descuentoValor, LocalDate fechaInicio, LocalDate fechaFin, Integer maxUsos, Integer usosActuales,
            Usuario creadoPor) {
        this.codigo = codigo;
        this.descripcionEs = descripcionEs;
        this.descripcionEn = descripcionEn;
        this.descuentoTipo = descuentoTipo;
        this.descuentoValor = descuentoValor;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.maxUsos = maxUsos;
        this.usosActuales = usosActuales;
        this.creadoPor = creadoPor;
    }

    public Integer getCuponId() {
        return cuponId;
    }

    public void setCuponId(Integer cuponId) {
        this.cuponId = cuponId;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcionEs() {
        return descripcionEs;
    }

    public void setDescripcionEs(String descripcionEs) {
        this.descripcionEs = descripcionEs;
    }

    public String getDescripcionEn() {
        return descripcionEn;
    }

    public void setDescripcionEn(String descripcionEn) {
        this.descripcionEn = descripcionEn;
    }

    public TipoDescuento getDescuentoTipo() {
        return descuentoTipo;
    }

    public void setDescuentoTipo(TipoDescuento descuentoTipo) {
        this.descuentoTipo = descuentoTipo;
    }

    public Double getDescuentoValor() {
        return descuentoValor;
    }

    public void setDescuentoValor(Double descuentoValor) {
        this.descuentoValor = descuentoValor;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getMaxUsos() {
        return maxUsos;
    }

    public void setMaxUsos(Integer maxUsos) {
        this.maxUsos = maxUsos;
    }

    public Integer getUsosActuales() {
        return usosActuales;
    }

    public void setUsosActuales(Integer usosActuales) {
        this.usosActuales = usosActuales;
    }

    public Usuario getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Usuario creadoPor) {
        this.creadoPor = creadoPor;
    }
    
}
