
package pe.com.cinepucp.autoservicio.model.peliculas;

public class Genero {
    private int id;
    private String nombreEs;
    private String nombreEn;
    
    public Genero(){        
    }
    
    public Genero(String nombreEs,String nombreEn){
        this.nombreEn=nombreEn;
        this.nombreEs=nombreEs;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
    @Override
    public String toString(){
        return "Genero{"+"id="+id+",nombreEs="+nombreEs+'\''+
                ",nombreEn"+nombreEn+'}';
    }
    
    
    
}
