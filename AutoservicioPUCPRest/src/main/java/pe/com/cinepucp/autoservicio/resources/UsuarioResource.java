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
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.IUsuarioBO;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl.UsuarioBOImpl;
import pe.com.cinepucp.autoservicio.model.auth.Usuario;
/**
 *
 * @author Amira
 */

@Path("usuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@SuppressWarnings("UnnecessaryReturnStatement")
public class UsuarioResource {
    private final IUsuarioBO usuarioBO;

    public UsuarioResource() {
        this.usuarioBO = new UsuarioBOImpl();
    }
    
    @GET
    public List<Usuario> listar(){
        return this.usuarioBO.listar();
    }
    
    @GET
    @Path("{id}")
    public Response obtener(@PathParam("id") int id) {
        Usuario pelicula = this.usuarioBO.buscarPorId(id);
        
        if (pelicula == null ) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Usuario: " + id + ", no encontrada"))
                    .build();
        }
        
        return Response.ok(pelicula).build();
    }
    
    @POST
    public Response crear(Usuario usuario) throws Exception {
        if (usuario == null || usuario.getNombre()==null ||usuario.getEmail().isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("La pelicula no es valida")
                    .build();
        }
        
        this.usuarioBO.registrar(usuario);
        URI location = URI.create("/AutoservicioCine/api/Peliculas" + usuario.getId());        
        return Response.created(location)
                .entity(usuario)
                .build();
    }
    
    @PUT
    @Path("{id}")
    public Response actualizar(@PathParam("id") int id, Usuario usuario) throws Exception {
        if (usuario == null || usuario.getNombre()==null ||usuario.getEmail().isBlank()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuario: " + id + ", no encontrado")
                    .build();
        }
        
        this.usuarioBO.actualizar(usuario);
        
        return Response.ok(usuario).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") int id) {
        if (this.usuarioBO.buscarPorId(id) == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("usuario: " + id + ", no encontrado")
                    .build();
        }
        this.usuarioBO.eliminar(id);
        return Response.noContent().build();
    }        
}
