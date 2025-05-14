
package pe.com.cinepucp.autoservicio.main.cruds;
import pe.com.cinepucp.autoservicio.model.auth.Usuario;
import pe.com.cinepucp.autoservicio.dao.IUsuarioDAO;
import pe.com.cinepucp.autoservicio.mysql.UsuarioDAOImpl;
import java.time.LocalDate;
import java.util.List;
/**
 *
 * @author Miguel
 */
public class UsuarioCRUD {

    public static void ejecutarCRUDUsuario() {
        System.out.println("\n=== INICIO CRUD USUARIO ===");
        IUsuarioDAO usuarioDAO = new UsuarioDAOImpl();

        listarUsuarios("Usuarios al inicio", usuarioDAO);
        
        int idUsuarioCreado = crearUsuarioEjemplo(usuarioDAO);

        Usuario usuarioEncontrado = buscarUsuario(usuarioDAO, idUsuarioCreado);
        actualizarUsuario(usuarioDAO, idUsuarioCreado);
        buscarUsuario(usuarioDAO, idUsuarioCreado);
        eliminarUsuario(usuarioDAO, idUsuarioCreado);
        listarUsuarios("Usuarios al final", usuarioDAO);

        System.out.println("=== CRUD USUARIO COMPLETADO CON ÉXITO ===\n");
    }

    
    private static int crearUsuarioEjemplo(IUsuarioDAO usuarioDAO) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre("EjemploUsuario1");
        nuevoUsuario.setEmail("ejemp17.usuario@correo.com");
        nuevoUsuario.setTelefono("1524507564");
        nuevoUsuario.setPassword("clave123");
        nuevoUsuario.setFechaRegistro(LocalDate.now());
        nuevoUsuario.setEstaActivo(true);
        nuevoUsuario.setIdiomaPreferido("es");

        return insertarUsuario(usuarioDAO, nuevoUsuario);
    }

    public static void listarUsuarios(String titulo, IUsuarioDAO usuarioDAO) {
        System.out.println("\n" + titulo);
        System.out.println("======================================");
        List<Usuario> usuarios = usuarioDAO.listar();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados");
        } else {
            usuarios.forEach(usuario -> {
               
                System.out.println("ID: " + usuario.getId() +
                                   ", Nombre: " + usuario.getNombre() +
                                   ", Email: " + usuario.getEmail() +
                                   ", Telefono: " + usuario.getTelefono() + 
                                   ", Fecha Registro: " + usuario.getFechaRegistro() + 
                                   ", Activo: " + usuario.isEstaActivo() + 
                                   ", Idioma: " + usuario.getIdiomaPreferido()); 
            });
        }
        System.out.println("======================================\n");
    }

    public static int insertarUsuario(IUsuarioDAO usuarioDAO, Usuario usuario) {
        int idUsuario = usuarioDAO.insertar(usuario);
        if (idUsuario == -1) {
            throw new RuntimeException("Error al crear el usuario");
        }
        System.out.println("CREATE: Usuario creado con ID: " + idUsuario);
        return idUsuario;
    }

    public static Usuario buscarUsuario(IUsuarioDAO usuarioDAO, int id) {
        Usuario usuario = usuarioDAO.buscar(id);
        if (usuario == null) {
            throw new RuntimeException("No se encontró el usuario con ID: " + id);
        }
        System.out.println("READ: Usuario encontrado - " +
                           "ID: " + usuario.getId() +
                           ", Nombre: " + usuario.getNombre() +
                           ", Email: " + usuario.getEmail() +
                           ", Telefono: " + usuario.getTelefono() +
                           ", Fecha Registro: " + usuario.getFechaRegistro() +
                           ", Activo: " + usuario.isEstaActivo() +
                           ", Idioma: " + usuario.getIdiomaPreferido());
        return usuario;
    }

    public static void actualizarUsuario(IUsuarioDAO usuarioDAO, int id) {
        Usuario usuario = buscarUsuario(usuarioDAO, id);

     
        usuario.setEmail("ejemplo.usuario12.actualizado@correo.com");
        usuario.setEstaActivo(false); // Example: deactivate the user

        if (!usuarioDAO.modificar(usuario)) {
            throw new RuntimeException("Error al actualizar el usuario con ID: " + id);
        }
        System.out.println("UPDATE: Usuario actualizado correctamente (ID: " + id + ")");
    }

    public static void eliminarUsuario(IUsuarioDAO usuarioDAO, int id) {
        if (!usuarioDAO.eliminar(id)) {
            throw new RuntimeException("Error al eliminar el usuario con ID: " + id);
        }
        System.out.println("DELETE: Usuario eliminado correctamente con ID: " + id);
    }
}