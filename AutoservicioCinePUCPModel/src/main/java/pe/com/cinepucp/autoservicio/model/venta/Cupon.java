package pe.com.cinepucp.autoservicio.model.venta;

import java.math.BigDecimal;
import java.time.LocalDate;

import pe.com.cinepucp.autoservicio.model.auth.Usuario;

public class Cupon {
    private int id;
    private String codigo;
    private String descripcionEs;
    private String descripcionEn;
    private String descuentoTipo;
    private BigDecimal descuentoValor;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Integer maxUsos;
    private int usosActuales;
    private Usuario creadoPor;

    public Cupon(int id, String codigo, String descripcionEs, String descripcionEn, String descuentoTipo,
            BigDecimal descuentoValor, LocalDate fechaInicio, LocalDate fechaFin, Integer maxUsos, int usosActuales,
            Usuario creadoPor) {
        this.id = id;
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

    public Usuario getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Usuario creadoPor) {
        this.creadoPor = creadoPor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDescuentoTipo() {
        return descuentoTipo;
    }

    public void setDescuentoTipo(String descuentoTipo) {
        this.descuentoTipo = descuentoTipo;
    }

    public BigDecimal getDescuentoValor() {
        return descuentoValor;
    }

    public void setDescuentoValor(BigDecimal descuentoValor) {
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

    public int getUsosActuales() {
        return usosActuales;
    }

    public void setUsosActuales(int usosActuales) {
        this.usosActuales = usosActuales;
    }

    public Cupon(){

    }
    


}
