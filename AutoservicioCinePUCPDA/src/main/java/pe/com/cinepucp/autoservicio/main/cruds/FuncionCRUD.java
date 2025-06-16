
package pe.com.cinepucp.autoservicio.main.cruds;

import java.util.List;
import pe.com.cinepucp.autoservicio.model.salas.Sala;
import pe.com.cinepucp.autoservicio.model.Peliculas.Pelicula;
import pe.com.cinepucp.autoservicio.model.Peliculas.Funcion;
import pe.com.cinepucp.autoservicio.mysql.PeliculaDAOImpl;
import pe.com.cinepucp.autoservicio.mysql.FuncionDAOImpl;
import pe.com.cinepucp.autoservicio.mysql.SalaDAOImpl;

import java.time.LocalDateTime;

public class FuncionCRUD {
    public static void ejecutarCRUDFuncion() {
        System.out.println("\n=== INICIO CRUD FUNCIÓN ===");
        FuncionDAOImpl funcionDAO = new FuncionDAOImpl();

        listarFunciones("Funciones al inicio", funcionDAO);

        int idFuncion = crearFuncionEjemplo(funcionDAO);
        buscarFuncion(funcionDAO, idFuncion);
        actualizarFuncion(funcionDAO, idFuncion);
        eliminarFuncion(funcionDAO, idFuncion);

        listarFunciones("Funciones al final", funcionDAO);
        System.out.println("=== CRUD FUNCIÓN COMPLETADO CON ÉXITO ===\n");
    }

    private static int crearFuncionEjemplo(FuncionDAOImpl funcionDAO) {
        Funcion funcion=new Funcion();
        PeliculaDAOImpl peliDAO=new PeliculaDAOImpl();
        Pelicula pelicula=peliDAO.buscar(7);
        
        SalaDAOImpl salaDAO=new SalaDAOImpl();
        Sala sala=salaDAO.buscar(8);
        funcion.setPelicula(pelicula); // Asumiendo que ya existe una película con ID 
        funcion.setSala(sala);         
        funcion.setFechaHora(LocalDateTime.of(2025, 5, 3, 18, 30));
        funcion.setFormatoProyeccion("2D");
        funcion.setIdioma("Español");
        funcion.setSubtitulos(false);
        funcion.setEstaActiva(true);
        funcion.setFechaModificacion(LocalDateTime.of(2025, 5, 3, 20, 30));
        funcion.setUsuarioModificacion(2);
        return insertarFuncion(funcionDAO, funcion);
    }

    public static void listarFunciones(String titulo, FuncionDAOImpl funcionDAO) {
        System.out.println("\n" + titulo);
        System.out.println("======================================");
        List<Funcion> funciones = funcionDAO.listar();
        if (funciones.isEmpty()) {
            System.out.println("No hay funciones registradas");
        } else {
            funciones.forEach(System.out::println);
        }
        System.out.println("======================================\n");
    }

    public static int insertarFuncion(FuncionDAOImpl funcionDAO, Funcion funcion) {
        int id = funcionDAO.insertar(funcion);
        if (id == -1) {
            throw new RuntimeException("Error al insertar la función");
        }
        System.out.println("CREATE: Función creada con ID: " + id);
        return id;
    }

    public static Funcion buscarFuncion(FuncionDAOImpl funcionDAO, int id) {
        Funcion funcion = funcionDAO.buscar(id);
        if (funcion == null) {
            throw new RuntimeException("No se encontró la función con ID: " + id);
        }
        System.out.println("READ: Función encontrada - " + funcion);
        return funcion;
    }

    public static void actualizarFuncion(FuncionDAOImpl funcionDAO, int id) {
        Funcion funcion = buscarFuncion(funcionDAO, id);
        funcion.setFechaHora(LocalDateTime.of(2025, 5, 3, 21, 0));
        funcion.setFormatoProyeccion("3D");
        funcion.setIdioma("Inglés");
        funcion.setSubtitulos(true);
        funcion.setEstaActiva(true);
        funcion.setFechaModificacion(LocalDateTime.of(2025, 5, 3, 20, 30));
        funcion.setUsuarioModificacion(2); //SI NO EXISTE EL USUARIO LANZA ERROR -------------
        if (!funcionDAO.modificar(funcion)) {
            throw new RuntimeException("Error al actualizar la función con ID: " + id);
        }
        System.out.println("UPDATE: Función actualizada correctamente");
    }

    public static void eliminarFuncion(FuncionDAOImpl funcionDAO, int id) {
        if (!funcionDAO.eliminar(id)) {
            throw new RuntimeException("Error al eliminar la función con ID: " + id);
        }
        System.out.println("DELETE: Función eliminada correctamente");
    }
}