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
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.IProductoBO;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl.ProductoBOImpl;
import pe.com.cinepucp.autoservicio.model.comida.Producto;

@Path("productos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@SuppressWarnings("UnnecessaryReturnStatement")
public class ProductoResource {
    private final IProductoBO productoBO;
    
    public ProductoResource(){
        productoBO=new ProductoBOImpl();
    }
    @GET
    public List<Producto> listar(){
        return this.productoBO.listar();
    }
    
    @GET
    @Path("{id}")
    public Response obtener(@PathParam("id") int id) {
        Producto producto = this.productoBO.buscarPorId(id);
        
        if (producto == null ) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Producto: " + id + ", no encontrado"))
                    .build();
        }
        
        return Response.ok(producto).build();
    }
    @POST
    public Response crear(Producto producto) throws Exception {
        if (producto == null || producto.getNombre_es()==null ||producto.getNombre_es().isBlank()){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El producto no es valida")
                    .build();
        }
        
        this.productoBO.registrar(producto);
        URI location = URI.create("/AutoservicioCine/api/Productos/" + producto.getId());
        
        return Response.created(location)
                .entity(producto)
                .build();
    }
    
    @PUT
    @Path("{id}")
    public Response actualizar(@PathParam("id") int id, Producto producto) throws Exception {
        if (producto == null || producto.getNombre_es()==null ||producto.getNombre_es().isBlank()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Producto: " + id + ", no encontrado")
                    .build();
        }
        
        this.productoBO.actualizar(producto);
        
        return Response.ok(producto).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") int id) {
        if (this.productoBO.buscarPorId(id) == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Producto: " + id + ", no encontrado")
                    .build();
        }
        this.productoBO.eliminar(id);
        return Response.noContent().build();
    }        
    
}
