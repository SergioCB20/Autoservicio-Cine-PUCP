package pe.com.cinepucp.autoservicio.main.cruds;
import java.util.List;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import pe.com.cinepucp.autoservicio.model.auth.CodigoVerificacion; 
import pe.com.cinepucp.autoservicio.mysql.CodigoVerificacionDAOImpl;
import pe.com.cinepucp.autoservicio.mysql.UsuarioDAOImpl;
import pe.com.cinepucp.autoservicio.model.auth.Usuario;
/**
 *
 * @author Miguel
 */
public class CodigoVerificacionCRUD {
    public static void ejecutarCRUDCodigoVerificacion() {
        System.out.println("\n=== INICIO CRUD CODIGO VERIFICACION ===");
        CodigoVerificacionDAOImpl codigoVerificacionDAO = new CodigoVerificacionDAOImpl();

        listarCodigoVerificaciones("Codigos de Verificacion al inicio", codigoVerificacionDAO);

        int idCodigo = crearCodigoVerificacionEjemplo(codigoVerificacionDAO);

        buscarCodigoVerificacion(codigoVerificacionDAO, idCodigo);

        actualizarCodigoVerificacion(codigoVerificacionDAO, idCodigo);

        eliminarCodigoVerificacion(codigoVerificacionDAO, idCodigo);

        listarCodigoVerificaciones("Codigos de Verificacion al final", codigoVerificacionDAO);
        System.out.println("=== CRUD CODIGO VERIFICACION COMPLETADO CON ÉXITO ===\n");
    }

    private static int crearCodigoVerificacionEjemplo(CodigoVerificacionDAOImpl codigoVerificacionDAO) {
        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl(); // Assuming you have this DAO
        Usuario usuarioEjemplo = usuarioDAO.buscar(1);
        
        if (usuarioEjemplo == null) {
         throw new RuntimeException("Error: No se encontró un usuario de ejemplo con ID 1. Asegúrate de que exista un usuario en la tabla 'usuarios'.");
        }
        
        CodigoVerificacion nuevoCodigo = new CodigoVerificacion();
        nuevoCodigo.setTelefono("987654321");
        nuevoCodigo.setCodigo("123456");
        nuevoCodigo.setFechaCreacion(LocalDateTime.now());
        nuevoCodigo.setFechaExpiracion(LocalDateTime.now().plus(5, ChronoUnit.MINUTES));
        nuevoCodigo.setUsado(false);

        return insertarCodigoVerificacion(codigoVerificacionDAO, nuevoCodigo);
    }

    public static void listarCodigoVerificaciones(String titulo, CodigoVerificacionDAOImpl codigoVerificacionDAO) {
        System.out.println("\n" + titulo);
        System.out.println("======================================");
        List<CodigoVerificacion> codigos = codigoVerificacionDAO.listar();
        if (codigos.isEmpty()) {
            System.out.println("No hay codigos de verificacion registrados");
        } else {
            codigos.forEach(codigo -> {
                // Imprimir detalles del codigo de verificacion
                System.out.println("ID: " + codigo.getId() +
                                   ", Telefono: " + codigo.getTelefono() +
                                   ", Codigo: " + codigo.getCodigo() +
                                   ", Creacion: " + codigo.getFechaCreacion() +
                                   ", Expiracion: " + codigo.getFechaExpiracion() +
                                   ", Usado: " + codigo.isUsado()); //
            });
        }
        System.out.println("======================================\n");
    }
    
    public static int insertarCodigoVerificacion(CodigoVerificacionDAOImpl codigoVerificacionDAO, CodigoVerificacion codigo) {
        int idCodigo = codigoVerificacionDAO.insertar(codigo); 
        if (idCodigo == -1) { 
            throw new RuntimeException("Error al crear el codigo de verificacion");
        }
        System.out.println("CREATE: Codigo de verificacion creado con ID: " + idCodigo);
        return idCodigo;
    }

    public static CodigoVerificacion buscarCodigoVerificacion(CodigoVerificacionDAOImpl codigoVerificacionDAO, int id) {
        CodigoVerificacion codigo = codigoVerificacionDAO.buscar(id);
        if (codigo == null) {
            throw new RuntimeException("No se encontró el codigo de verificacion con ID: " + id);
        }
        System.out.println("READ: Codigo de verificacion encontrado - " +
                           "ID: " + codigo.getId() +
                           ", Telefono: " + codigo.getTelefono() +
                           ", Codigo: " + codigo.getCodigo() +
                           ", Creacion: " + codigo.getFechaCreacion() +
                           ", Expiracion: " + codigo.getFechaExpiracion() +
                           ", Usado: " + codigo.isUsado()); 
        return codigo;
    }

    public static void actualizarCodigoVerificacion(CodigoVerificacionDAOImpl codigoVerificacionDAO, int id) {
        CodigoVerificacion codigo = buscarCodigoVerificacion(codigoVerificacionDAO, id); // Primero buscar

        codigo.setUsado(true); // Marcar como usado

        if (!codigoVerificacionDAO.modificar(codigo)) {
            throw new RuntimeException("Error al actualizar el codigo de verificacion con ID: " + id);
        }
        System.out.println("UPDATE: Codigo de verificacion actualizado correctamente");
        buscarCodigoVerificacion(codigoVerificacionDAO, id);
    }

    public static void eliminarCodigoVerificacion(CodigoVerificacionDAOImpl codigoVerificacionDAO, int id) {
        if (!codigoVerificacionDAO.eliminar(id)) { 
            throw new RuntimeException("Error al eliminar el codigo de verificacion con ID: " + id);
        }
        System.out.println("DELETE: Codigo de verificacion eliminado correctamente con ID: " + id);
    }
}
