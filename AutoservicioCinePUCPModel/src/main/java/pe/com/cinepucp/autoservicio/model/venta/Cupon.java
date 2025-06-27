package pe.com.cinepucp.autoservicio.model.venta;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import pe.com.cinepucp.autoservicio.model.adapters.LocalDateAdapter;

import pe.com.cinepucp.autoservicio.model.auth.Usuario;

@XmlAccessorType(XmlAccessType.FIELD)
public class Cupon {

    private Integer cuponId;
    private String codigo;
    private String descripcionEs;
    private String descripcionEn;
    private TipoDescuento descuentoTipo;
    private Double descuentoValor;
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaInicio = null;
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaFin = null;
    private Integer maxUsos;
    private Integer usosActuales;
    private Usuario creadoPor = null;
    private LocalDate fechaModificacion = null;
    private List<Usuario> usuarios = new ArrayList<>(); // Usuarios que tienen este cupón
    private List<Venta> ventas = new ArrayList<>(); // Ventas donde se aplicó este cupón
    private boolean activo;
    private Usuario modificadopor = null;

    /**
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Cupon{"
                + "cuponId=" + cuponId
                + ", codigo='" + codigo + '\''
                + ", descripcionEs='" + descripcionEs + '\''
                + ", descripcionEn='" + descripcionEn + '\''
                + ", descuentoTipo=" + (descuentoTipo != null ? descuentoTipo.getDescripcion() : "null")
                + ", descuentoValor=" + descuentoValor
                + ", fechaInicio=" + fechaInicio
                + ", fechaFin=" + fechaFin
                + ", maxUsos=" + maxUsos
                + ", usosActuales=" + usosActuales
                + ", creadoPor=" + (creadoPor != null ? creadoPor.getId() : "null")
                + '}';
    }

    public Cupon() {

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

    /**
     * @return the fechaModificacion
     */
    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    /**
     * @param fechaModificacion the fechaModificacion to set
     */
    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
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

    public Usuario getModificadoPor() {
        return modificadopor;
    }

    public void setModificadoPor(Usuario modificadopor) {
        this.modificadopor = modificadopor;
    }

}