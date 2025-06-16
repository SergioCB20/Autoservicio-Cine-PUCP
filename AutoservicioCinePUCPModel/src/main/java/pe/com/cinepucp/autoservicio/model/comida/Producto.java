

package pe.com.cinepucp.autoservicio.model.comida;

import java.util.Date;


public class Producto {
    private Integer id;
    private String nombre_es;
    private String nombre_en;
    private String descripcion_es;
    private String descripcion_en;
    private double precio;
    private String imagenUrl;
    private TipoProducto tipo;
    private boolean estaActivo;    
    private Date fechaModificacion;
    private Integer usuarioModificacion;

    public Producto() {
    }

    public Producto(String nombre_es,String nombre_en,String descripcion_es, String descripcion_en, double precio, TipoProducto tipo, boolean estaActivo) {
        
        this.nombre_en = nombre_en;
        this.nombre_es = nombre_es;
        this.descripcion_en = descripcion_en;
        this.descripcion_es = descripcion_es;        
        this.precio = precio;
        this.tipo = tipo;
        this.estaActivo = estaActivo;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    public boolean isEstaActivo() {
        return estaActivo;
    }

    public void setEstaActivo(boolean estaActivo) {
        this.estaActivo = estaActivo;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + getId() +
                ", nombreEn='" + getNombre_en() + '\'' +
                ", nombreEs='" + getNombre_es() + '\'' +
                ", descripcionEn='" + getDescripcion_en() + '\'' +
                ", descripcionEs='" + getDescripcion_es() + '\'' +                
                ", precio=" + precio +
                ", tipo=" + tipo +
                ", estaActivo=" + estaActivo +
                '}';
    }

    /**
     * @return the fechaModificacion
     */
    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    /**
     * @param fechaModificacion the fechaModificacion to set
     */
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    /**
     * @return the usuarioModificacion
     */
    public Integer getUsuarioModificacion() {
        return usuarioModificacion;
    }

    /**
     * @param usuarioModificacion the usuarioModificacion to set
     */
    public void setUsuarioModificacion(Integer usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    /**
     * @return the nombre_es
     */
    public String getNombre_es() {
        return nombre_es;
    }

    /**
     * @param nombre_es the nombre_es to set
     */
    public void setNombre_es(String nombre_es) {
        this.nombre_es = nombre_es;
    }

    /**
     * @return the nombre_en
     */
    public String getNombre_en() {
        return nombre_en;
    }

    /**
     * @param nombre_en the nombre_en to set
     */
    public void setNombre_en(String nombre_en) {
        this.nombre_en = nombre_en;
    }

    /**
     * @return the descripcion_es
     */
    public String getDescripcion_es() {
        return descripcion_es;
    }

    /**
     * @param descripcion_es the descripcion_es to set
     */
    public void setDescripcion_es(String descripcion_es) {
        this.descripcion_es = descripcion_es;
    }

    /**
     * @return the descripcion_en
     */
    public String getDescripcion_en() {
        return descripcion_en;
    }

    /**
     * @param descripcion_en the descripcion_en to set
     */
    public void setDescripcion_en(String descripcion_en) {
        this.descripcion_en = descripcion_en;
    }

    /**
     * @return the imagenUrl
     */
    public String getImagenUrl() {
        return imagenUrl;
    }

    /**
     * @param imagenUrl the imagenUrl to set
     */
    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }


}

