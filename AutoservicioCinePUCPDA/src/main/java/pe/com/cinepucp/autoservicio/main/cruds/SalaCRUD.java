/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.main.cruds;

/**
 *
 * @author Sergio
 */

/**
 *
 * @author Sergio
 */

import java.util.List;
import pe.com.cinepucp.autoservicio.model.salas.Sala;
import pe.com.cinepucp.autoservicio.model.salas.TipoSala;
import pe.com.cinepucp.autoservicio.mysql.SalaDAOImpl;

public class SalaCRUD {
    public static void ejecutarCRUDSala() {
        System.out.println("\n=== INICIO CRUD SALA ===");
        SalaDAOImpl salaDAO = new SalaDAOImpl();

        //listarSalas("Salas al inicio", salaDAO);
        
        int idSala = crearSalaEjemplo(salaDAO);
        Sala sala=buscarSala(salaDAO, idSala);
        //actualizarSala(salaDAO, idSala);
        System.out.println(sala.getId());
        System.out.println(sala.getTipoSala());
        eliminarSala(salaDAO, idSala);
        
        listarSalas("Salas al final", salaDAO);
        System.out.println("=== CRUD SALA COMPLETADO CON ÉXITO ===\n");
    }

    private static int crearSalaEjemplo(SalaDAOImpl salaDAO) {
        Sala nuevaSala = new Sala();
        nuevaSala.setNombre("Sala 3");
        nuevaSala.setTipoSala(TipoSala.ESTANDAR);
        nuevaSala.setCapacidad(50);
        return insertarSala(salaDAO, nuevaSala);
    }

    // Métodos CRUD básicos (podrían ser públicos si se usan desde otras clases)
    public static void listarSalas(String titulo, SalaDAOImpl salaDAO) {
        System.out.println("\n" + titulo);
        System.out.println("======================================");
        List<Sala> salas = salaDAO.listar();
        if (salas.isEmpty()) {
            System.out.println("No hay salas registradas");
        } else {
            salas.forEach(System.out::println);
        }
        System.out.println("======================================\n");
    }

    public static int insertarSala(SalaDAOImpl salaDAO, Sala sala) {
        int idSala = salaDAO.insertar(sala);
        if (idSala == -1) {
            throw new RuntimeException("Error al crear la sala");
        }
        System.out.println("CREATE: Sala creada con ID: " + idSala);
        return idSala;
    }

    public static Sala buscarSala(SalaDAOImpl salaDAO, int id) {
        Sala sala = salaDAO.buscar(id);
        if (sala == null) {
            throw new RuntimeException("No se encontró la sala con ID: " + id);
        }
        System.out.println("READ: Sala encontrada - " + sala);
        return sala;
    }

    public static void actualizarSala(SalaDAOImpl salaDAO, int id) {
        Sala sala = buscarSala(salaDAO, id);
        sala.setNombre("Sala 3 PREMIUM");
        sala.setTipoSala(TipoSala.PREMIUM);
        
        if (!salaDAO.modificar(sala)) {
            throw new RuntimeException("Error al actualizar la sala con ID: " + id);
        }
        System.out.println("UPDATE: Sala actualizada correctamente");
    }

    public static void eliminarSala(SalaDAOImpl salaDAO, int id) {
        if (!salaDAO.eliminar(id)) {
            throw new RuntimeException("Error al eliminar la sala con ID: " + id);
        }
        System.out.println("DELETE: Sala eliminada correctamente");
    }
}