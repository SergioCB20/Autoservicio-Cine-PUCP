package pe.com.cinepucp.autoservicio.model.auth;

/**
 *
 * @author Sergio
 */
import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import pe.com.cinepucp.autoservicio.model.adapters.LocalDateTimeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class LogSistema {
    private Integer id;
    private String accion;
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss") 
    private LocalDateTime  fecha;
    private Integer id_usuario;

    public LogSistema() {
    }

    public LogSistema(String accion, LocalDateTime fecha, Integer id_usuario) {
        this.accion = accion;
        this.fecha = fecha;
        this.id_usuario = id_usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    
    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Integer getUsuario() {
        return id_usuario;
    }

    public void setUsuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    // Método toString
    @Override
    public String toString() {
        return "LogSistema{" +
                "id=" + id +
                ", accion='" + accion + '\'' +
                ", fecha=" + fecha +
                ", usuario id=" + id_usuario +
                '}';
    }
}