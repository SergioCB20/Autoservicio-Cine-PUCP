/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl;

import java.util.List;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.IPeliculaBO;
import pe.com.cinepucp.autoservicio.model.Peliculas.Pelicula;
import java.util.Objects;
import pe.com.cinepucp.autoservicio.dao.IPeliculaDAO;
import pe.com.cinepucp.autoservicio.mysql.PeliculaDAOImpl;

/**
 *
 * @author Sergio
 */
public class PeliculaBOImpl implements IPeliculaBO {
    
    private IPeliculaDAO peliculaDAO;
    
    public PeliculaBOImpl() {
        peliculaDAO = new PeliculaDAOImpl();
    }
    
     private void validarPelicula(Pelicula pelicula) throws Exception {
         
        Objects.requireNonNull(pelicula, "La película no puede ser nula.");

        if (pelicula.getTituloEs() == null || pelicula.getTituloEs().trim().isEmpty()) {
            throw new Exception("El título en español de la película es requerido.");
        }
        if (pelicula.getTituloEn() == null || pelicula.getTituloEn().trim().isEmpty()) {
            throw new Exception("El título en inglés de la película es requerido.");
        }
        if (pelicula.getClasificacion() == null || pelicula.getClasificacion().trim().isEmpty()) {
            throw new Exception("La clasificación de la película es requerida.");
        }
        if (pelicula.getSinopsisEs() == null || pelicula.getSinopsisEs().trim().isEmpty()) {
            throw new Exception("La sinopsis en español de la película es requerida.");
        }
        if (pelicula.getSinopsisEn() == null || pelicula.getSinopsisEn().trim().isEmpty()) {
            throw new Exception("La sinopsis en inglés de la película es requerida.");
        }
        if (pelicula.getImagenUrl() == null || pelicula.getImagenUrl().trim().isEmpty()) {
            throw new Exception("La URL de la imagen de la película es requerida.");
        }

        if (pelicula.getDuracionMin() <= 0) {
            throw new Exception("La duración de la película debe ser un valor positivo.");
        }
    }
    
    @Override
    public void registrar(Pelicula peli) throws Exception {
        validarPelicula(peli);
        peliculaDAO.insertar(peli);
    }
    
    @Override
    public void actualizar(Pelicula peli) throws Exception{
        validarPelicula(peli);
        peliculaDAO.modificar(peli);
    }
    @Override
    public List<Pelicula> listar(){
        return peliculaDAO.listar();
    }
    @Override
    public void eliminar(int id){
        peliculaDAO.eliminar(id);
    }
    @Override
    public void buscarPorId(int id){
        peliculaDAO.buscar(id);
    }
}
