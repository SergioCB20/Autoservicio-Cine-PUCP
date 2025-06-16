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
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.ISalaBO;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl.SalaBOImpl;
import pe.com.cinepucp.autoservicio.model.salas.Sala;

/**
 *
 * @author gonza
 */
@Path("salas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@SuppressWarnings("UnnecessaryReturnStatement")
public class SalaResource {
    private final ISalaBO salaBO;
    
    public SalaResource(){
        this.salaBO=new SalaBOImpl();
    }
    
    @GET
    public List<Sala> listar(){
        return this.salaBO.listar();
    }
    
    @GET
    @Path("{id}")
    public Response obtener(@PathParam("id") int id) {
        Sala sala = this.salaBO.buscarPorId(id);
        
        if (sala == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Sala: " + id + ", no encontrada"))
                    .build();
        }
        
        return Response.ok(sala).build();
    }
    @POST
    public Response crear(Sala sala) throws Exception {
        if (sala == null || sala.getNombre()==null || sala.getNombre().isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("La sala no es valida")
                    .build();
        }
        
        this.salaBO.registrar(sala);
        URI location = URI.create("/AutoservicioCine/api/Salas/" + sala.getId());
        
        return Response.created(location)
                .entity(sala)
                .build();
    }
    
    @PUT
    @Path("{id}")
    public Response actualizar(@PathParam("id") int id, Sala sala) throws Exception {
        if (sala == null || sala.getNombre() == null || sala.getNombre().isBlank()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("SAla: " + id + ", no encontrada")
                    .build();
        }
        
        this.salaBO.actualizar(sala);
        
        return Response.ok(sala).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") int id) {
        if (this.salaBO.buscarPorId(id) == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("SAla: " + id + ", no encontrada")
                    .build();
        }
        this.salaBO.eliminar(id);
        
        return Response.noContent().build();
    }    
    
}
