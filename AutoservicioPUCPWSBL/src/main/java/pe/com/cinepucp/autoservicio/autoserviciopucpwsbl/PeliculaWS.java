/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.autoserviciopucpwsbl;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import java.util.List;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.IPeliculaBO;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl.PeliculaBOImpl;
import pe.com.cinepucp.autoservicio.model.Peliculas.Pelicula;

/**
 *
 * @author Sergio
 */

@WebService(serviceName = "PeliculaWS")
public class PeliculaWS {
    private final IPeliculaBO peliculaBO;
    
    public PeliculaWS() {
        peliculaBO = new PeliculaBOImpl();
    }
    
    @WebMethod(operationName = "registrarPelicula")
    public void registrarPelicula(Pelicula peli) {
        try {
            peliculaBO.registrar(peli);
        } catch (Exception e) {
            throw new WebServiceException("Error al registrar pelicula: " + e.getMessage());
        }
    }
    
    @WebMethod(operationName = "actualizarPelicula")
    public void actualizarPelicula(Pelicula peli) {
        try {
            peliculaBO.actualizar(peli);
        } catch (Exception e) {
            throw new WebServiceException("Error al actualizar pelicula: " + e.getMessage());
        }
    }
    
    @WebMethod(operationName = "eliminarPelicula")
    public void eliminarPelicula(int id) {
        try {
            peliculaBO.eliminar(id);
        } catch (Exception e) {
            throw new WebServiceException("Error al eliminar pelicula: " + e.getMessage());
        }
    }
    
    @WebMethod(operationName = "buscarPeliculaPorId")
    public void buscarPeliculaPorId(int id) {
        try {
            peliculaBO.buscarPorId(id);
        } catch (Exception e) {
            throw new WebServiceException("Error al buscar pelicula por id: " + e.getMessage());
        }
    }
    
    @WebMethod(operationName = "listarPeliculas")
    public List<Pelicula> listarPeliculas() {
        try {
            return peliculaBO.listar();
        } catch (Exception e) {
            throw new WebServiceException("Error al listar peliculas: " + e.getMessage());
        }
    }
    
}
