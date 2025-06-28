package pe.com.cinepucp.autoservicio.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.Map;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.IFuncionBO;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl.FuncionBOImpl;
import pe.com.cinepucp.autoservicio.model.Peliculas.Funcion;

@Path("funciones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@SuppressWarnings("UnnecessaryReturnStatement")
public class FuncionResource {

    private final IFuncionBO funcionBO;

    public FuncionResource() {
        this.funcionBO = new FuncionBOImpl();
    }

    @GET
    public List<Funcion> listar() {
        return this.funcionBO.listar();
    }

    @GET
    @Path("{id}")
    public Response obtener(@PathParam("id") int id) {
        try {
            Funcion funcion = this.funcionBO.buscarPorId(id);
            if (funcion == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(Map.of("error", "Función: " + id + " no encontrada"))
                        .build();
            }
            return Response.ok(funcion).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", e.getMessage()))
                    .build();
        }
    }

    @GET
    @Path("pelicula/{peliculaId}")
    public Response listarPorPelicula(@PathParam("peliculaId") int peliculaId) {
        try {
            List<Funcion> funciones = this.funcionBO.listarPorPelicula(peliculaId);
            return Response.ok(funciones).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", e.getMessage()))
                    .build();
        }
    }

    @POST
    public Response crear(Funcion funcion) {
        try {
            this.funcionBO.registrar(funcion);
            URI location = URI.create("/AutoservicioCine/api/funciones/" + funcion.getFuncionId());
            return Response.created(location).entity(funcion).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", e.getMessage()))
                    .build();
        }
    }

    @PUT
    @Path("{id}")
    public Response actualizar(@PathParam("id") int id, Funcion funcion) {
        try {
            if (funcion == null || funcion.getFuncionId() == null || funcion.getFuncionId() != id) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(Map.of("error", "ID de función inválido o inconsistente"))
                        .build();
            }
            this.funcionBO.actualizar(funcion);
            return Response.ok(funcion).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", e.getMessage()))
                    .build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") int id) {
        try {
            this.funcionBO.eliminar(id);
            return Response.noContent().build();
        } catch (IllegalArgumentException | IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", e.getMessage()))
                    .build();
        }
    }
}
