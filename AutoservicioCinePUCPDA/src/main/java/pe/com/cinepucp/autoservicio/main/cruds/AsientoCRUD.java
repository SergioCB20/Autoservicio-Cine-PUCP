/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.main.cruds;

import java.util.List;
import pe.com.cinepucp.autoservicio.model.salas.Asiento;
import pe.com.cinepucp.autoservicio.model.salas.Sala;
import pe.com.cinepucp.autoservicio.model.salas.TipoAsiento;
import pe.com.cinepucp.autoservicio.model.salas.TipoSala;
import pe.com.cinepucp.autoservicio.mysql.AsientoDAOImpl;
import pe.com.cinepucp.autoservicio.mysql.SalaDAOImpl;

/**
 *
 * @author Sergio
 */
public class AsientoCRUD {
    public static void ejecutarCRUDAsiento() {
        System.out.println("\n=== INICIO CRUD ASIENTO ===");
        AsientoDAOImpl asientoDAO = new AsientoDAOImpl();
        
        listarAsientos("Asientos al inicio", asientoDAO);
        
        int idAsiento = crearAsientoEjemplo(asientoDAO);
        buscarAsiento(asientoDAO, idAsiento);
        actualizarAsiento(asientoDAO, idAsiento);
        eliminarAsiento(asientoDAO, idAsiento);
        
        listarAsientos("Asientos al final", asientoDAO);
        System.out.println("=== CRUD ASIENTO COMPLETADO CON ÉXITO ===\n");
    }

    private static int crearAsientoEjemplo(AsientoDAOImpl asientoDAO) {
        SalaDAOImpl salaDAO = new SalaDAOImpl();
        Sala sala = salaDAO.buscar(3);
        
        Asiento nuevoAsiento = new Asiento();
        nuevoAsiento.setSala(sala);
        nuevoAsiento.setFila('A');
        nuevoAsiento.setNumero(2);
        nuevoAsiento.setTipo(TipoAsiento.NORMAL);
        
        return insertarAsiento(asientoDAO, nuevoAsiento);
    }

    // Métodos CRUD básicos
    public static void listarAsientos(String titulo, AsientoDAOImpl asientoDAO) {
        System.out.println("\n" + titulo);
        System.out.println("======================================");
        List<Asiento> asientos = asientoDAO.listar();
        if (asientos.isEmpty()) {
            System.out.println("No hay asientos registrados");
        } else {
            asientos.forEach(System.out::println);
        }
        System.out.println("======================================\n");
    }

    public static int insertarAsiento(AsientoDAOImpl asientoDAO, Asiento asiento) {
        int idAsiento = asientoDAO.insertar(asiento);
        if (idAsiento == -1) {
            throw new RuntimeException("Error al crear el asiento");
        }
        System.out.println("CREATE: Asiento creado con ID: " + idAsiento);
        return idAsiento;
    }

    public static Asiento buscarAsiento(AsientoDAOImpl asientoDAO, int id) {
        Asiento asiento = asientoDAO.buscar(id);
        if (asiento == null) {
            throw new RuntimeException("No se encontró el asiento con ID: " + id);
        }
        System.out.println("READ: Asiento encontrado - " + asiento);
        return asiento;
    }

    public static void actualizarAsiento(AsientoDAOImpl asientoDAO, int id) {
        Asiento asiento = buscarAsiento(asientoDAO, id);
        asiento.setTipo(TipoAsiento.VIP);
        
        if (!asientoDAO.modificar(asiento)) {
            throw new RuntimeException("Error al actualizar el asiento con ID: " + id);
        }
        System.out.println("UPDATE: Asiento actualizado correctamente");
    }

    public static void eliminarAsiento(AsientoDAOImpl asientoDAO, int id) {
        if (!asientoDAO.eliminar(id)) {
            throw new RuntimeException("Error al eliminar el asiento con ID: " + id);
        }
        System.out.println("DELETE: Asiento eliminado correctamente");
    }

}