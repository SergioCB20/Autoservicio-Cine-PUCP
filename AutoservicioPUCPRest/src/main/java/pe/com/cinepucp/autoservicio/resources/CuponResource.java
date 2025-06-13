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
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.ICuponBO;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl.CuponBOImpl;
import pe.com.cinepucp.autoservicio.model.venta.Cupon;


@Path("cupones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@SuppressWarnings("UnnecessaryReturnStatement")
public class CuponResource {
    private final ICuponBO cuponBO;
    
    public CuponResource(){
        cuponBO=new CuponBOImpl();
    }
            
    @GET
    public List<Cupon> listar(){
        return this.cuponBO.listar();
    }
    
    @GET
    @Path("{id}")
    public Response obtener(@PathParam("id") int id) {
        Cupon cupon = this.cuponBO.buscarPorId(id);
        
        if (cupon == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Cupon: " + id + ", no encontrado"))
                    .build();
        }
        
        return Response.ok(cupon).build();
    }
    @POST
    public Response crear(Cupon cupon) throws Exception {
        if (cupon == null || cupon.getCodigo()==null ||cupon.getCodigo().isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El cupon no es valida")
                    .build();
        }
        
        this.cuponBO.registrar(cupon);
        URI location = URI.create("/AutoservicioCine/api/Cupones/" + cupon.getCuponId());
        
        return Response.created(location)
                .entity(cupon)
                .build();
    }
    
    @PUT
    @Path("{id}")
    public Response actualizar(@PathParam("id") int id, Cupon cupon) throws Exception {
        if (cupon == null || cupon.getCodigo()==null ||cupon.getCodigo().isBlank()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Cupon: " + id + ", no encontrada")
                    .build();
        }
        
        this.cuponBO.actualizar(cupon);
        
        return Response.ok(cupon).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") int id) {
        if (this.cuponBO.buscarPorId(id) == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Boleto: " + id + ", no encontrada")
                    .build();
        }
        this.cuponBO.eliminar(id);
        return Response.noContent().build();
    }    
    
    
}
