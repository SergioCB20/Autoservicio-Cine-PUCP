
package pe.com.cinepucp.autoservicio.model.peliculas;
import pe.com.cinepucp.autoservicio.model.salas.Sala;
import java.time.LocalDateTime;

/**
 *
 * @author Piero
 */
public class Funcion {
    private int id;
    private Sala sala;
    private LocalDateTime fechaHora;
    private String formatoProyeccion;
    private String idioma;
    private boolean subtitulos;
//    private Pelicula pelicula;
    
    public Funcion() {
    }

    public Funcion(Sala sala, LocalDateTime fechaHora, String idioma, boolean subtitulos) {
        this.sala = sala;
        this.fechaHora = fechaHora;
        this.idioma = idioma;
        this.subtitulos = subtitulos;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
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

    public boolean isSubtitulos() {
        return subtitulos;
    }

    public void setSubtitulos(boolean subtitulos) {
        this.subtitulos = subtitulos;
    }
    
//    public Pelicula getPelicula() {
//        return pelicula;
//    }
//
//    public void setPelicula(Pelicula pelicula) {
//        this.pelicula = pelicula;
//    }

}
