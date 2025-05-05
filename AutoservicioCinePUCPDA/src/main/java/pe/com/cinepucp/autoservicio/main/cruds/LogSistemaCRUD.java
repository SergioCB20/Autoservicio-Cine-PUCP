
package pe.com.cinepucp.autoservicio.main.cruds;

import pe.com.cinepucp.autoservicio.dao.ILogSistemaDAO;
import pe.com.cinepucp.autoservicio.mysql.LogSistemaDAOImpl;
import pe.com.cinepucp.autoservicio.model.auth.LogSistema;
import pe.com.cinepucp.autoservicio.model.auth.Usuario;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
/**
 *
 * @author Miguel
 */
public class LogSistemaCRUD {
    
    public static void ejecutarCRUDLogSistema(){
        System.out.println("\n=== INICIO CRUD LOG SISTEMA ===");
        ILogSistemaDAO logSistemaDAO = new LogSistemaDAOImpl();
        
        Usuario usuario = new Usuario();
        usuario.setId(15); //se define su id como 15 el que está en la bd
        usuario.setNombre("Dummy user");

        listarLogSistemas("LogSistemas al inicio", logSistemaDAO);
        
        int idLogCreado = crearLogEjemplo(logSistemaDAO, usuario);
        
        buscarLogSistema(logSistemaDAO, idLogCreado);
        actualizarLogSistema(logSistemaDAO, idLogCreado, usuario);
        eliminarLogSistema(logSistemaDAO, idLogCreado);
        
        listarLogSistemas("LogSistemas al inicio", logSistemaDAO);
        System.out.println("=== CRUD CODIGO VERIFICACION COMPLETADO CON EXITO ===\n");
    }
    
    private static int crearLogEjemplo(ILogSistemaDAO logSistemaDAO, Usuario usuario) {
        LogSistema nuevoLog = new LogSistema();
        nuevoLog.setAccion("Prueba de Inserción");
        nuevoLog.setFecha(LocalDate.now());
        nuevoLog.setUsuario(usuario);

        return insertarLogSistema(logSistemaDAO, nuevoLog);
    }
    
     public static void listarLogSistemas(String titulo, ILogSistemaDAO logSistemaDAO) {
        System.out.println("\n" + titulo);
        System.out.println("======================================");
        List<LogSistema> logs = logSistemaDAO.listar();
        if (logs.isEmpty()) {
            System.out.println("No hay logs registrados");
        } else {
            logs.forEach(log -> System.out.println(log));
        }
        System.out.println("======================================\n");
    }
    
    public static int insertarLogSistema(ILogSistemaDAO logSistemaDAO, LogSistema log) {
        int idLog = logSistemaDAO.insertar(log);
        if (idLog == -1) {
            throw new RuntimeException("Error al crear el log");
        }
        System.out.println("CREATE: Log creado con ID: " + idLog);
        return idLog;
    }

    public static LogSistema buscarLogSistema(ILogSistemaDAO logSistemaDAO, int id) {
        LogSistema log = logSistemaDAO.buscar(id);
        if (log == null) {
            System.out.println("READ: No se encontró el log con ID: " + id);
            return null;
        }
        System.out.println("READ: Log encontrado - " + log);
        return log;
    }

    public static void actualizarLogSistema(ILogSistemaDAO logSistemaDAO, int id, Usuario usuario) {
        LogSistema log = buscarLogSistema(logSistemaDAO, id);
        if (log == null) {
            return; 
        }

        log.setAccion("Prueba de Actualización");
        log.setUsuario(usuario);

        if (!logSistemaDAO.modificar(log)) {
            throw new RuntimeException("Error al actualizar el log con ID: " + id);
        }
        System.out.println("UPDATE: Log actualizado correctamente (ID: " + id + ")");
    }

    public static void eliminarLogSistema(ILogSistemaDAO logSistemaDAO, int id) {
        if (!logSistemaDAO.eliminar(id)) {
            throw new RuntimeException("Error al eliminar el log con ID: " + id);
        }
        System.out.println("DELETE: Log eliminado correctamente con ID: " + id);
    }

    
}

