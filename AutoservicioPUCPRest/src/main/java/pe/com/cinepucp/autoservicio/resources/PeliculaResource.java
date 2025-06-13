/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.Map;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.IPeliculaBO;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl.PeliculaBOImpl;
import pe.com.cinepucp.autoservicio.model.Peliculas.Pelicula;


@Path("peliculas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@SuppressWarnings("UnnecessaryReturnStatement")
public class PeliculaResource {
    private final IPeliculaBO peliculaBO;
    
    public PeliculaResource(){
        peliculaBO=new PeliculaBOImpl();
    }
    @GET
    public List<Pelicula> listar(){
        return this.peliculaBO.listar();
    }
    
    @GET
    @Path("{id}")
    public Response obtener(@PathParam("id") int id) {
        Pelicula pelicula = this.peliculaBO.buscarPorId(id);
        
        if (pelicula == null ) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Pelicula: " + id + ", no encontrada"))
                    .build();
        }
        
        return Response.ok(pelicula).build();
    }
    @POST
    public Response crear(Pelicula pelicula) throws Exception {
        if (pelicula == null || pelicula.getTituloEn()==null ||pelicula.getTituloEn().isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("La pelicula no es valida")
                    .build();
        }
        
        this.peliculaBO.registrar(pelicula);
        URI location = URI.create("/AutoservicioCine/api/Peliculas" + pelicula.getPeliculaId());        
        return Response.created(location)
                .entity(pelicula)
                .build();
    }
    
    @PUT
    @Path("{id}")
    public Response actualizar(@PathParam("id") int id, Pelicula pelicula) throws Exception {
        if (pelicula == null || pelicula.getTituloEn()==null ||pelicula.getTituloEn().isBlank()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Pelicula: " + id + ", no encontrada")
                    .build();
        }
        
        this.peliculaBO.actualizar(pelicula);
        
        return Response.ok(pelicula).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") int id) {
        if (this.peliculaBO.buscarPorId(id) == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("pelicula: " + id + ", no encontrada")
                    .build();
        }
        this.peliculaBO.eliminar(id);
        return Response.noContent().build();
    }        
}
