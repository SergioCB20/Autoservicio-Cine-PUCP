
package pe.com.cinepucp.autoservicio.model.Peliculas;
import pe.com.cinepucp.autoservicio.model.salas.Sala;

import pe.com.cinepucp.autoservicio.model.venta.Boleto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;


/**
 *
 * @author Piero
 */
public class Funcion {
    private Integer funcionId;
    private Pelicula pelicula;
    private Sala sala;
    private LocalDateTime fechaHora;
    private String formatoProyeccion;
    private String idioma;
    private Boolean subtitulos;
    private boolean estaActiva;
    private LocalDateTime fechaModificacion;
    private Integer usuarioModificacion;
    private List<Boleto> boletos = new ArrayList<>();
    
    public Funcion() {
    }

    public Funcion(Sala sala, LocalDateTime fechaHora, String idioma, boolean subtitulos) {
        this.sala = sala;
        this.fechaHora = fechaHora;
        this.idioma = idioma;
        this.subtitulos = subtitulos;
    }

    /**
     * @return the funcionId
     */
    public Integer getFuncionId() {
        return funcionId;
    }

    /**
     * @param funcionId the funcionId to set
     */
    public void setFuncionId(Integer funcionId) {
        this.funcionId = funcionId;
    }

    /**
     * @return the pelicula
     */
    public Pelicula getPelicula() {
        return pelicula;
    }

    /**
     * @param pelicula the pelicula to set
     */
    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    /**
     * @return the sala
     */
    public Sala getSala() {
        return sala;
    }

    /**
     * @param sala the sala to set
     */
    public void setSala(Sala sala) {
        this.sala = sala;
    }

    /**
     * @return the fechaHora
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * @param fechaHora the fechaHora to set
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * @return the formatoProyeccion
     */
    public String getFormatoProyeccion() {
        return formatoProyeccion;
    }

    /**
     * @param formatoProyeccion the formatoProyeccion to set
     */
    public void setFormatoProyeccion(String formatoProyeccion) {
        this.formatoProyeccion = formatoProyeccion;
    }

    /**
     * @return the idioma
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * @param idioma the idioma to set
     */
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    /**
     * @return the subtitulos
     */
    public Boolean getSubtitulos() {
        return subtitulos;
    }

    /**
     * @param subtitulos the subtitulos to set
     */
    public void setSubtitulos(Boolean subtitulos) {
        this.subtitulos = subtitulos;
    }

    /**
     * @return the boletos
     */
    public List<Boleto> getBoletos() {
        return boletos;
    }

    /**
     * @param boletos the boletos to set
     */
    public void setBoletos(List<Boleto> boletos) {
        this.boletos = boletos;
    }

    /**
     * @return the estaActiva
     */
    public boolean getEstaActiva() {
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
    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    /**
     * @param fechaModificacion the fechaModificacion to set
     */
    public void setFechaModificacion(LocalDateTime fechaModificacion) {
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
    


}
