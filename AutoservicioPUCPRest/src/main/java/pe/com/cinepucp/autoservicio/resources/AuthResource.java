package pe.com.cinepucp.autoservicio.resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Map;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.IUsuarioBO;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl.UsuarioBOImpl;
import pe.com.cinepucp.autoservicio.utils.PasswordHasher;
import pe.com.cinepucp.autoservicio.model.auth.Usuario;

@Path("auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    private final IUsuarioBO usuarioBO;

    public AuthResource() {
        this.usuarioBO = new UsuarioBOImpl();
    }

    @POST
    @Path("login")
    public Response login(Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password"); // Contraseña en texto plano del cliente

        if (email == null || password == null || email.isBlank() || password.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("message", "Email y contraseña son requeridos."))
                    .build();
        }

        try {
            // Llama al BO con la contraseña en texto plano para que el BO la verifique.
            Usuario usuarioAutenticado = usuarioBO.autenticarUsuario(email, password);

            if (usuarioAutenticado != null) {
                Map<String, Object> responseData = Map.of(
                    "id", usuarioAutenticado.getId(),
                    "nombre", usuarioAutenticado.getNombre(),
                    "email", usuarioAutenticado.getEmail(),
                    "tipoUsuario", usuarioAutenticado.isAdmin() ? "ADMIN" : "CLIENTE"
                );
                return Response.ok(responseData).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity(Map.of("message", "Credenciales inválidas.")) 
                        .build();
            }
        } catch (Exception e) {
            System.err.println("Error en login: " + e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(Map.of("message", e.getMessage()))
                    .build();
        }
    }

    @POST
    @Path("signup")
    public Response signup(Usuario nuevoUsuario) { // nuevoUsuario contiene la contraseña en texto plano
        if (nuevoUsuario == null || nuevoUsuario.getEmail() == null || nuevoUsuario.getPassword() == null ||
            nuevoUsuario.getEmail().isBlank() || nuevoUsuario.getPassword().isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("message", "Email, contraseña y nombre son requeridos."))
                    .build();
        }

        try {
            String hashedPassword = PasswordHasher.hashPassword(nuevoUsuario.getPassword());
            nuevoUsuario.setPassword(hashedPassword); 

            usuarioBO.registrar(nuevoUsuario); 
            return Response.status(Response.Status.CREATED)
                    .entity(Map.of("message", "Usuario registrado exitosamente."))
                    .build();
        } catch (Exception e) {
            System.err.println("Error en signup: " + e.getMessage());
            return Response.status(Response.Status.CONFLICT)
                    .entity(Map.of("message", e.getMessage()))
                    .build();
        }
    }
}