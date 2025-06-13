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
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.IBoletoBO;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl.BoletoBOImpl;
import pe.com.cinepucp.autoservicio.model.venta.Boleto;


@Path("boletos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@SuppressWarnings("UnnecessaryReturnStatement")
public class BoletoResource {
    private final IBoletoBO boletoBO;
    
    public BoletoResource(){
        boletoBO=new BoletoBOImpl();
    }
    
    @GET
    public List<Boleto> listar(){
        return this.boletoBO.listar();
    }
    
    @GET
    @Path("{id}")
    public Response obtener(@PathParam("id") int id) {
        Boleto boleto = this.boletoBO.buscarPorId(id);        
        if (boleto == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Boleto: " + id + ", no encontrada"))
                    .build();
        }
        
        return Response.ok(boleto).build();
    }
    @POST
    public Response crear(Boleto boleto) throws Exception {
        if (boleto == null ) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El Boleto no es valida")
                    .build();
        }
        
        this.boletoBO.registrar(boleto);
        URI location = URI.create("/AutoservicioCine/api/Boletos/" + boleto.getBoletoId());
        
        return Response.created(location)
                .entity(boleto)
                .build();
    }
    
    @PUT
    @Path("{id}")
    public Response actualizar(@PathParam("id") int id, Boleto boleto) throws Exception {
        if (boleto==null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Boleto: " + id + ", no encontrada")
                    .build();
        }
        
        this.boletoBO.actualizar(boleto);
        
        return Response.ok(boleto).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") int id) {
        if (this.boletoBO.buscarPorId(id) == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Boelto: " + id + ", no encontrada")
                    .build();
        }
        this.boletoBO.eliminar(id);
        
        return Response.noContent().build();
    }    
    
    
}
