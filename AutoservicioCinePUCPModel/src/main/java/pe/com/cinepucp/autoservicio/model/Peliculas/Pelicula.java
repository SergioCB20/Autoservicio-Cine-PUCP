
package pe.com.cinepucp.autoservicio.model.peliculas;

public class Pelicula {
    private int id;
    private String tituloEs;
    private String tituloEn;
    private int duracionMin;
    private String clasificacion;
    private String sinopsisEs;
    private String sinopsisEn;
    private String imagenUrl;
    private boolean estaActiva;
    
    public Pelicula(){        
    }

    public Pelicula(String tituloEs,String tituloEn,int duracionMin,String clasificacion){
        this.tituloEs = tituloEs;
        this.tituloEn=tituloEn;
        this.duracionMin=duracionMin;
        this.clasificacion=clasificacion;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTituloEs() {
        return tituloEs;
    }


    public void setTituloEs(String tituloEs) {
        this.tituloEs = tituloEs;
    }


    public String getTituloEn() {
        return tituloEn;
    }

    public void setTituloEn(String tituloEn) {
        this.tituloEn = tituloEn;
    }


    public int getDuracionMin() {
        return duracionMin;
    }

 
    public void setDuracionMin(int duracionMin) {
        this.duracionMin = duracionMin;
    }

    public String getClasificacion() {
        return clasificacion;
    }


    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }


    public String getSinopsisEs() {
        return sinopsisEs;
    }

    public void setSinopsisEs(String sinopsisEs) {
        this.sinopsisEs = sinopsisEs;
    }

    public String getSinopsisEn() {
        return sinopsisEn;
    }

    public void setSinopsisEn(String sinopsisEn) {
        this.sinopsisEn = sinopsisEn;
    }


    public String getImagenUrl() {
        return imagenUrl;
    }


    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public boolean isEstaActiva() {
        return estaActiva;
    }


    public void setEstaActiva(boolean estaActiva) {
        this.estaActiva = estaActiva;
    }
    @Override
    public String toString(){
        return "Genero{"+"id="+id+",tituloEs="+tituloEs+'\''+
                ",tituloEn"+tituloEn+'\''+",duracionMin="+duracionMin+'\''+",clasificacion="+clasificacion
                +'\''+",sinopsisEs="+sinopsisEs+'\''+",sinopsisEn="+sinopsisEn+'\''+",imagenUrl="+imagenUrl+'}';
    }
}
