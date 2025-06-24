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
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.IAsientoBO;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl.AsientoBOImpl;
import pe.com.cinepucp.autoservicio.model.salas.Asiento;

/**
 *
 * @author gonza
 */
@Path("asientos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@SuppressWarnings("UnnecessaryReturnStatement")
public class AsientoResource {
    private final IAsientoBO asientoBO;
    
    public AsientoResource(){
       this.asientoBO=new AsientoBOImpl();
    }
    
    @GET
    public List<Asiento> listar(){
        return this.asientoBO.listar();
    }
    @GET
    @Path("por-sala/{salaid}")
    public List<Asiento> listarporSala(@PathParam("salaid")int salaid){
        return this.asientoBO.listaAsientoSala(salaid);
    }
    
    @GET
    @Path("{id}")
    public Response obtener(@PathParam("id") int id) {
        Asiento asiento = this.asientoBO.buscarPorId(id);
        
        if (asiento == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Asiento: " + id + ", no encontrada"))
                    .build();
        }
        
        return Response.ok(asiento).build();
    }
    @POST
    public Response crear(Asiento asiento) throws Exception {
        if (asiento == null || asiento.getNumero()<=0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Asiento no es valida")
                    .build();
        }
        
        this.asientoBO.registrar(asiento);
        URI location = URI.create("/AutoservicioCine/api/Asientos/" + asiento.getId());
        
        return Response.created(location)
                .entity(asiento)
                .build();
    }
    
    @PUT
    @Path("{id}")
    public Response actualizar(@PathParam("id") int id, Asiento asiento) throws Exception {
        if (asiento == null || asiento.getNumero()<=0) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Asiento: " + id + ", no encontrado")
                    .build();
        }
        
        this.asientoBO.actualizar(asiento);
        
        return Response.ok(asiento).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") int id) {
        if (this.asientoBO.buscarPorId(id) == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Asiento: " + id + ", no encontrada")
                    .build();
        }
        this.asientoBO.eliminar(id);
        
        return Response.noContent().build();
    }    
}
