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
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.IVentaBO;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl.VentaBOImpl;
import pe.com.cinepucp.autoservicio.model.venta.Venta;
/**
 *
 * @author gonza
 */
@Path("ventas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@SuppressWarnings("UnnecessaryReturnStatement")
public class VentaResource {
    private final IVentaBO ventaBO;
    
    public VentaResource(){
        this.ventaBO=new VentaBOImpl();
    }
   /* @GET
    @Path("{fechaini}-{fechafin}")
    public List<Venta> listar(@PathParam("fechaini")LocalDateTime fechaini,@PathParam("fechafin")LocalDateTime fechafin){
        return this.ventaBO.listarVentaReporte(fechaini,fechafin);
    }*/

    @GET
    @Path("{fechaini}/{fechafin}")
    public List<Venta> listar(@PathParam("fechaini") String fechaini,@PathParam("fechafin") String fechafin) {  
        
        return this.ventaBO.listarVentaReporte(fechaini, fechafin);
    }

    
    @GET
    @Path("{id}")
    public Response obtener(@PathParam("id") int id) {
        Venta venta = this.ventaBO.buscarPorId(id);
        
        if (venta == null ) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Venta: " + id + ", no encontrada"))
                    .build();
        }
        
        return Response.ok(venta).build();
    }
    @POST
    public Response crear(Venta venta) throws Exception {
        if (venta == null || venta.getTotal()<0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("La Venta no es valida")
                    .build();
        }
        
        this.ventaBO.registrar(venta);
        URI location = URI.create("/AutoservicioCine/api/Ventas" + venta.getVentaId());        
        return Response.created(location)
                .entity(venta)
                .build();
    }
    
    @PUT
    @Path("{id}")
    public Response actualizar(@PathParam("id") int id, Venta venta) throws Exception {
        if (venta == null || venta.getTotal()<0) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Venta: " + id + ", no encontrada")
                    .build();
        }
        
        this.ventaBO.actualizar(venta);
        
        return Response.ok(venta).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") int id,@PathParam("id_mod") int id_mod) {
        if (this.ventaBO.buscarPorId(id) == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Venta: " + id + ", no encontrada")
                    .build();
        }
        this.ventaBO.eliminar(id,id_mod);
        return Response.noContent().build();
    }        
    
}
