/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.resources;

/**
 *
 * @author Sergio
 */

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
import java.util.List;
import java.util.Map;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.IUsuarioBO;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl.UsuarioBOImpl; // Asumiendo implementación directa para el ejemplo
import pe.com.cinepucp.autoservicio.model.auth.Usuario;

// Define la ruta base para este recurso. Por ejemplo, tu API podría estar en /api/usuarios
@Path("usuarios")
@Consumes(MediaType.APPLICATION_JSON) // Espera JSON en las solicitudes
@Produces(MediaType.APPLICATION_JSON) // Produce JSON en las respuestas
public class UsuarioResource {

    private final IUsuarioBO usuarioBO;

    public UsuarioResource() {
        this.usuarioBO = new UsuarioBOImpl(); // Instancia el BO.
    }
    
    @POST
    public Response registrarUsuario(Usuario usuario) {
        try {
            // Validación básica de los datos del usuario antes de pasarlos al BO.
            // La validación más completa se hace en el BO.
            if (usuario == null || usuario.getEmail() == null || usuario.getEmail().isBlank() ||
                usuario.getPassword() == null || usuario.getPassword().isBlank() ||
                usuario.getNombre() == null || usuario.getNombre().isBlank()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(Map.of("message", "Datos de usuario incompletos. Email, password y nombre son requeridos."))
                        .build();
            }

            usuarioBO.registrar(usuario); // Llama al método registrar del BO
            return Response.status(Response.Status.CREATED) // 201 Created
                    .entity(Map.of("message", "Usuario registrado exitosamente."))
                    .build();
        } catch (Exception e) {
            // Captura excepciones del BO (ej. email duplicado, validación fallida)
            System.err.println("Error al registrar usuario: " + e.getMessage());
            // Si el email ya existe, el BO lanza una excepción que convertimos a 409 CONFLICT
            if (e.getMessage().contains("El email ya está registrado.")) {
                 return Response.status(Response.Status.CONFLICT) // 409 Conflict
                        .entity(Map.of("message", e.getMessage()))
                        .build();
            }
            return Response.status(Response.Status.BAD_REQUEST) // 400 Bad Request para otras validaciones
                    .entity(Map.of("message", e.getMessage()))
                    .build();
        }
    }
    @GET
    @Path("{id}") // Define que el ID es parte de la URL (ej. /usuarios/123)
    public Response buscarUsuarioPorId(@PathParam("id") int id) {
        try {
            Usuario usuario = usuarioBO.buscarPorId(id);
            if (usuario != null) {
                // No devolver la contraseña hasheada en la respuesta GET por seguridad
                Usuario responseUsuario = new Usuario();
                responseUsuario.setId(usuario.getId());
                responseUsuario.setNombre(usuario.getNombre());
                responseUsuario.setEmail(usuario.getEmail());
                responseUsuario.setTelefono(usuario.getTelefono());
                responseUsuario.setTipoUsuario(usuario.getTipoUsuario());
                // No responseUsuario.setPassword(usuario.getPassword());

                return Response.ok(responseUsuario).build(); // 200 OK
            } else {
                return Response.status(Response.Status.NOT_FOUND) // 404 Not Found
                        .entity(Map.of("message", "Usuario no encontrado con ID: " + id))
                        .build();
            }
        } catch (Exception e) {
            System.err.println("Error al buscar usuario por ID: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR) // 500 Internal Server Error
                    .entity(Map.of("message", "Error interno al buscar usuario: " + e.getMessage()))
                    .build();
        }
    }
    @GET
    public Response listarUsuarios() {
        try {
            List<Usuario> usuarios = usuarioBO.listar();
            
            // Opcional: Para cada usuario en la lista, asegurar que la contraseña no se exponga.
            // Si tu método listar() del DAO/BO ya no carga la contraseña, esto es redundante.
            // Si sí la carga, deberías crear una lista de objetos Usuario "seguros" sin password.
            // Ejemplo:
            /*
            List<Usuario> safeUsuarios = new ArrayList<>();
            for (Usuario u : usuarios) {
                Usuario safeU = new Usuario();
                safeU.setId(u.getId());
                safeU.setNombre(u.getNombre());
                safeU.setEmail(u.getEmail());
                safeU.setTelefono(u.getTelefono());
                safeU.setTipoUsuario(u.getTipoUsuario());
                safeUsuarios.add(safeU);
            }
            return Response.ok(safeUsuarios).build();
            */
            // Por simplicidad, asumo que tu modelo Usuario en este punto ya no expone la password
            // en un GET LISTAR a menos que sea explícitamente necesario.
            // Si tu BD carga la password y la clase Usuario la contiene, DEBERÍAS filtrarla aquí.
            return Response.ok(usuarios).build(); // 200 OK
        } catch (Exception e) {
            System.err.println("Error al listar usuarios: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR) // 500 Internal Server Error
                    .entity(Map.of("message", "Error interno al listar usuarios: " + e.getMessage()))
                    .build();
        }
    }
    @PUT
    @Path("{id}") // El ID en la URL debe coincidir con el ID en el cuerpo del JSON si lo incluyes
    public Response actualizarUsuario(@PathParam("id") int id, Usuario usuario) {
        try {
            if (usuario == null || id <= 0 || usuario.getId() != id) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(Map.of("message", "ID de usuario inválido o no coincide con la URL."))
                        .build();
            }

            // Asegúrate de que el ID del objeto Usuario sea el mismo que el de la URL.
            usuario.setId(id);

            // Si se permite actualizar la contraseña a través de este endpoint,
            // y la contraseña viene en texto plano, deberías hashearla aquí antes de llamar al BO.
            // Sin embargo, es mejor tener un endpoint específico para cambiar contraseña (/auth/cambiarContrasena)
            // Si la contraseña no se envía, o si se envía y no se modifica, el BO la mantendría.
            // Si se envía una NUEVA contraseña en TEXTO PLANO y este endpoint la procesa:
            /*
            if (usuario.getPassword() != null && !usuario.getPassword().isBlank()) {
                String hashedPassword = PasswordHasher.hashPassword(usuario.getPassword());
                usuario.setPassword(hashedPassword);
            } else {
                // Si la password es nula/vacía, podrías optar por no actualizarla o requerirla.
                // Podrías cargar el usuario existente para mantener su contraseña actual si no se provee una nueva.
                Usuario existingUser = usuarioBO.buscarPorId(id);
                if (existingUser != null) {
                    usuario.setPassword(existingUser.getPassword());
                }
            }
            */
            
            usuarioBO.actualizar(usuario); // Llama al método actualizar del BO
            return Response.ok(Map.of("message", "Usuario actualizado exitosamente.")).build(); // 200 OK
        } catch (Exception e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST) // 400 Bad Request para validaciones
                    .entity(Map.of("message", e.getMessage()))
                    .build();
        }
    }
    
    @DELETE
    @Path("{id}") // Define que el ID es parte de la URL (ej. /usuarios/123)
    public Response eliminarUsuario(@PathParam("id") int id) {
        try {
            if (id <= 0) {
                 return Response.status(Response.Status.BAD_REQUEST)
                        .entity(Map.of("message", "ID de usuario inválido."))
                        .build();
            }
            // Opcional: Puedes verificar si el usuario existe antes de intentar eliminarlo
            // Usuario usuarioExistente = usuarioBO.buscarPorId(id);
            // if (usuarioExistente == null) {
            //     return Response.status(Response.Status.NOT_FOUND)
            //             .entity(Map.of("message", "Usuario no encontrado con ID: " + id))
            //             .build();
            // }

            usuarioBO.eliminar(id); // Llama al método eliminar del BO
            return Response.status(Response.Status.NO_CONTENT).build(); // 204 No Content (éxito sin cuerpo de respuesta)
        } catch (Exception e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR) // 500 Internal Server Error
                    .entity(Map.of("message", "Error interno al eliminar usuario: " + e.getMessage()))
                    .build();
        }
    }
}
