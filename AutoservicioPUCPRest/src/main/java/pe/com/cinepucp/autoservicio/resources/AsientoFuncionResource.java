package pe.com.cinepucp.autoservicio.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.Map;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.IAsientoFuncionBO;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl.AsientoFuncionBOImpl;
import pe.com.cinepucp.autoservicio.model.salas.AsientoFuncion;

@Path("asientofunciones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@SuppressWarnings("UnnecessaryReturnStatement")
public class AsientoFuncionResource {
    
    private final IAsientoFuncionBO funcionBO;

    public AsientoFuncionResource() {
        this.funcionBO = new AsientoFuncionBOImpl();
    }

    @GET
    public List<AsientoFuncion> listar() {
        return this.funcionBO.listar();
    }

    @GET
    @Path("{id}")
    public Response obtener(@PathParam("id") int id) {
        try {
            AsientoFuncion funcion = this.funcionBO.buscarPorId(id);
            if (funcion == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(Map.of("error", "AsientoFunción: " + id + " no encontrada"))
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
    @Path("asientofuncion/{funcionId}")
    public Response listarPorFuncion(@PathParam("funcionId") int funcionId) {
        try {
            List<AsientoFuncion> funciones = this.funcionBO.listarPorFuncion(funcionId);
            return Response.ok(funciones).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", e.getMessage()))
                    .build();
        }
    }

    @POST
    public Response crear(AsientoFuncion funcion) {
        try {
            this.funcionBO.registrar(funcion);
            URI location = URI.create("/AutoservicioCine/api/asientofunciones/" + funcion.getIdAsientoFuncion());
            return Response.created(location).entity(funcion).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", e.getMessage()))
                    .build();
        }
    }

    @PUT
    @Path("{id}")
    public Response actualizar(@PathParam("id") int id, AsientoFuncion funcion) {
        try {
            if (funcion == null || funcion.getIdAsientoFuncion()== null || funcion.getIdAsientoFuncion()!= id) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(Map.of("error", "ID de asientofunción inválido o inconsistente"))
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
