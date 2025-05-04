package pe.com.cinepucp.autoservicio.model.Peliculas;
import java.util.List;
import java.util.ArrayList;

public class Genero {
    private Integer generoId;
    private String nombreEs;
    private String nombreEn;
    private List<Pelicula> peliculas = new ArrayList<>();
    
    public Genero(){        
    }
    
    public Genero(String nombreEs,String nombreEn){
        this.nombreEn=nombreEn;
        this.nombreEs=nombreEs;
    }

    /**
     * @return the id
     */
   
    @Override
    public String toString(){
        return "Genero{"+"id="+getGeneroId()+",nombreEs="+getNombreEs()+'\''+
                ",nombreEn"+getNombreEn()+'}';
    }

    /**
     * @return the generoId
     */
    public Integer getGeneroId() {
        return generoId;
    }

    /**
     * @param generoId the generoId to set
     */
    public void setGeneroId(Integer generoId) {
        this.generoId = generoId;
    }

    /**
     * @return the nombreEs
     */
    public String getNombreEs() {
        return nombreEs;
    }

    /**
     * @param nombreEs the nombreEs to set
     */
    public void setNombreEs(String nombreEs) {
        this.nombreEs = nombreEs;
    }

    /**
     * @return the nombreEn
     */
    public String getNombreEn() {
        return nombreEn;
    }

    /**
     * @param nombreEn the nombreEn to set
     */
    public void setNombreEn(String nombreEn) {
        this.nombreEn = nombreEn;
    }

    /**
     * @return the peliculas
     */
    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    /**
     * @param peliculas the peliculas to set
     */
    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }
    
    
    
}
