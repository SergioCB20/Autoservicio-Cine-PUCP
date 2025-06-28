package pe.com.cinepucp.autoservicio.model.Peliculas;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import pe.com.cinepucp.autoservicio.model.venta.Boleto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import pe.com.cinepucp.autoservicio.model.adapters.LocalDateTimeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class Funcion {
     private Integer funcionId;
    private Integer peliculaId;
    private Integer salaId;
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss") 
    private LocalDateTime fechaHora;

    private String formatoProyeccion;
    private String idioma;
    private Boolean subtitulos;
    private boolean estaActiva;
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss") 
    private LocalDateTime fechaModificacion;

    private Integer usuarioModificacion;
    private List<Boleto> boletos = new ArrayList<>();

    public Funcion() {
    }

    public Funcion(Integer salaId, LocalDateTime fechaHora, String idioma, boolean subtitulos) {
        this.salaId = salaId;
        this.fechaHora = fechaHora;
        this.idioma = idioma;
        this.subtitulos = subtitulos;
    }

    public Integer getFuncionId() {
        return funcionId;
    }

    public void setFuncionId(Integer funcionId) {
        this.funcionId = funcionId;
    }

    public Integer getPeliculaId() {
        return peliculaId;
    }

    public void setPeliculaId(Integer peliculaId) {
        this.peliculaId = peliculaId;
    }

    public Integer getSalaId() {
        return salaId;
    }

    public void setSalaId(Integer salaId) {
        this.salaId = salaId;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getFormatoProyeccion() {
        return formatoProyeccion;
    }

    public void setFormatoProyeccion(String formatoProyeccion) {
        this.formatoProyeccion = formatoProyeccion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Boolean getSubtitulos() {
        return subtitulos;
    }

    public void setSubtitulos(Boolean subtitulos) {
        this.subtitulos = subtitulos;
    }

    public boolean getEstaActiva() {
        return estaActiva;
    }

    public void setEstaActiva(boolean estaActiva) {
        this.estaActiva = estaActiva;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(Integer usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public List<Boleto> getBoletos() {
        return boletos;
    }

    public void setBoletos(List<Boleto> boletos) {
        this.boletos = boletos;
    }
}
