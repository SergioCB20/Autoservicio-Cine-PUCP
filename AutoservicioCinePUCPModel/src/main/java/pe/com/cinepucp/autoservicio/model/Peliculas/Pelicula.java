
package pe.com.cinepucp.autoservicio.model.Peliculas;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Date;

public class Pelicula {
    private Integer peliculaId;
    private String tituloEs;
    private String tituloEn;
    private int duracionMin;
    private String clasificacion;
    private String sinopsisEs;
    private String sinopsisEn;
    private String imagenUrl;
    private boolean estaActiva;
    private Date fechaModificacion;
    private Integer usuarioModificacion;
    private List<Genero> generos = new ArrayList<>();
    private List<Funcion> funciones = new ArrayList<>();
    public Pelicula(){        
    }

    public Pelicula(Integer peliculaId,String tituloEs,String tituloEn,int duracionMin,String clasificacion,
            String sinopsisEs,String sinopsisEn,String imagenUrl){
        this.peliculaId=peliculaId;
        this.tituloEs = tituloEs;
        this.tituloEn=tituloEn;
        this.duracionMin=duracionMin;
        this.clasificacion=clasificacion;
        this.sinopsisEn=sinopsisEn;
        this.sinopsisEs=sinopsisEs;
        this.imagenUrl=imagenUrl;
        
    }
    
   
    @Override
    public String toString(){
        return "Genero{"+"id="+getPeliculaId()+",tituloEs="+getTituloEs()+'\''+
                ",tituloEn"+getTituloEn()+'\''+",duracionMin="+getDuracionMin()+'\''+",clasificacion="+getClasificacion()
                +'\''+",sinopsisEs="+getSinopsisEs()+'\''+",sinopsisEn="+getSinopsisEn()+'\''+",imagenUrl="+getImagenUrl()+'}';
    }

    /**
     * @return the peliculaId
     */
    public Integer getPeliculaId() {
        return peliculaId;
    }

    /**
     * @param peliculaId the peliculaId to set
     */
    public void setPeliculaId(Integer peliculaId) {
        this.peliculaId = peliculaId;
    }

    /**
     * @return the tituloEs
     */
    public String getTituloEs() {
        return tituloEs;
    }

    /**
     * @param tituloEs the tituloEs to set
     */
    public void setTituloEs(String tituloEs) {
        this.tituloEs = tituloEs;
    }

    /**
     * @return the tituloEn
     */
    public String getTituloEn() {
        return tituloEn;
    }

    /**
     * @param tituloEn the tituloEn to set
     */
    public void setTituloEn(String tituloEn) {
        this.tituloEn = tituloEn;
    }

    /**
     * @return the duracionMin
     */
    public int getDuracionMin() {
        return duracionMin;
    }

    /**
     * @param duracionMin the duracionMin to set
     */
    public void setDuracionMin(int duracionMin) {
        this.duracionMin = duracionMin;
    }

    /**
     * @return the clasificacion
     */
    public String getClasificacion() {
        return clasificacion;
    }

    /**
     * @param clasificacion the clasificacion to set
     */
    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    /**
     * @return the sinopsisEs
     */
    public String getSinopsisEs() {
        return sinopsisEs;
    }

    /**
     * @param sinopsisEs the sinopsisEs to set
     */
    public void setSinopsisEs(String sinopsisEs) {
        this.sinopsisEs = sinopsisEs;
    }

    /**
     * @return the sinopsisEn
     */
    public String getSinopsisEn() {
        return sinopsisEn;
    }

    /**
     * @param sinopsisEn the sinopsisEn to set
     */
    public void setSinopsisEn(String sinopsisEn) {
        this.sinopsisEn = sinopsisEn;
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

    /**
     * @return the estaActiva
     */
    public boolean isEstaActiva() {
        return estaActiva;
    }

    /**
     * @param estaActiva the estaActiva to set
     */
    public void setEstaActiva(boolean estaActiva) {
        this.estaActiva = estaActiva;
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
     * @return the generos
     */
    public List<Genero> getGeneros() {
        return generos;
    }

    /**
     * @param generos the generos to set
     */
    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    /**
     * @return the funciones
     */
    public List<Funcion> getFunciones() {
        return funciones;
    }

    /**
     * @param funciones the funciones to set
     */
    public void setFunciones(List<Funcion> funciones) {
        this.funciones = funciones;
    }
}
