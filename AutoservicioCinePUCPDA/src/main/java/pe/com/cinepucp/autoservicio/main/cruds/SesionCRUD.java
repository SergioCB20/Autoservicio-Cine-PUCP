package pe.com.cinepucp.autoservicio.main.cruds;

import java.time.LocalDate;
import java.util.List;
import pe.com.cinepucp.autoservicio.dao.IUsuarioDAO;
import pe.com.cinepucp.autoservicio.model.auth.Sesion;
import pe.com.cinepucp.autoservicio.model.auth.TipoSesion;
import pe.com.cinepucp.autoservicio.model.auth.Usuario;
import pe.com.cinepucp.autoservicio.mysql.SesionDAOImpl;
import pe.com.cinepucp.autoservicio.main.cruds.UsuarioCRUD;
import pe.com.cinepucp.autoservicio.mysql.UsuarioDAOImpl;

/**
 *
 * @author Miguel
 */
public class SesionCRUD {
    public static void ejecutarCRUDSesion(){
        System.out.println("\n=== INICIO CRUD SESION ===");
        SesionDAOImpl sesionDAO = new SesionDAOImpl();
        
        
        int idSesion = crearSesionEjemplo(sesionDAO);
        listarSesiones("Sesiones al inicio", sesionDAO);
        
        
        buscarSesion(sesionDAO, idSesion);
        actualizarSesion(sesionDAO, idSesion);
        eliminarSesion(sesionDAO, idSesion);
        
        listarSesiones("Sesiones al final", sesionDAO);
        System.out.println("=== CRUD SESION COMPLETADO CON ÉXITO ===\n");;
        
    }
    
    private static int crearSesionEjemplo(SesionDAOImpl sesionDAO) {
        // Crear un objeto Sesion de ejemplo
        Sesion nuevaSesion = new Sesion();
        //IUsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        //Usuario usuario =UsuarioCRUD.buscarUsuario(usuarioDAO, 15);//ejemplo de susario fijo en la BD
        Usuario usuario= new Usuario();
        
        usuario.setId(2);
        nuevaSesion.setUsuario(usuario);
        nuevaSesion.setToken("ejemplo_token_" + System.currentTimeMillis()); // Usar timestamp para unicidad simple
        nuevaSesion.setMetodoLogin(TipoSesion.SMS);
        nuevaSesion.setFechaInicio(LocalDate.now());
        nuevaSesion.setFechaExpiracion(LocalDate.now().plusDays(7)); // Expira en 7 días

        // Insertar la sesion
        return insertarSesion(sesionDAO, nuevaSesion);
    }
    
    public static void listarSesiones(String titulo, SesionDAOImpl sesionDAO) {
        System.out.println("\n" + titulo);
        System.out.println("======================================");
        List<Sesion> sesiones = sesionDAO.listar();
        if (sesiones.isEmpty()) {
            System.out.println("No hay sesiones registradas");
        } else {
            sesiones.forEach(sesion -> {
                System.out.println("ID: " + sesion.getId() +
                                   ",ID Usuario: " + sesion.getUsuario().getId() +
                                   ", Token: " + sesion.getToken() +
                                   ", Método: " + sesion.getMetodoLogin() +
                                   ", Inicio: " + sesion.getFechaInicio() +
                                   ", Expiración: " + sesion.getFechaExpiracion());
            });
        }
        System.out.println("======================================\n");
    }
    
    public static int insertarSesion(SesionDAOImpl sesionDAO, Sesion sesion) {
        int idSesion = sesionDAO.insertar(sesion);
        if (idSesion == -1) {
            throw new RuntimeException("Error al crear la sesión");
        }
        System.out.println("CREATE: Sesión creada con ID: " + idSesion);
        return idSesion;
    }
    
    public static Sesion buscarSesion(SesionDAOImpl sesionDAO, int id) {
        Sesion sesion = sesionDAO.buscar(id);
        if (sesion == null) {
            throw new RuntimeException("No se encontró la sesión con ID: " + id);
        }
         System.out.println("READ: Sesión encontrada - " +
                           "ID: " + sesion.getId() +
                           ",ID Usuario: " + sesion.getUsuario().getId() +
                           ", Token: " + sesion.getToken() +
                           ", Método: " + sesion.getMetodoLogin() +
                           ", Inicio: " + sesion.getFechaInicio() +
                           ", Expiración: " + sesion.getFechaExpiracion());
        return sesion;
    }
    
    public static void actualizarSesion(SesionDAOImpl sesionDAO, int id) {
        Sesion sesion = buscarSesion(sesionDAO, id); 
        
        sesion.setMetodoLogin(TipoSesion.SMS);
        sesion.setFechaExpiracion(LocalDate.now().plusDays(30));
        
        if (!sesionDAO.modificar(sesion)) { 
            throw new RuntimeException("Error al actualizar la sesión con ID: " + id);
        }
        System.out.println("UPDATE: Sesión actualizada correctamente");
        buscarSesion(sesionDAO, id);
    }

    public static void eliminarSesion(SesionDAOImpl sesionDAO, int id) {
        if (!sesionDAO.eliminar(id,34)) {
            throw new RuntimeException("Error al eliminar la sesión con ID: " + id);
        }
        System.out.println("DELETE: Sesión eliminada correctamente con ID: " + id);
    }
}
