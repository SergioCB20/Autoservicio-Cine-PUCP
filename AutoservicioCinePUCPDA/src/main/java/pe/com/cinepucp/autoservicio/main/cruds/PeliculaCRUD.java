package pe.com.cinepucp.autoservicio.main.cruds;
import java.util.List;
import pe.com.cinepucp.autoservicio.model.peliculas.Pelicula;
import pe.com.cinepucp.autoservicio.main.mysql.PeliculaDAOImpl;
public class PeliculaCRUD {
    public static void ejecutarCRUDPelicula() {
        System.out.println("\n=== INICIO CRUD PELÍCULA ===");
        PeliculaDAOImpl peliculaDAO = new PeliculaDAOImpl();

        listarPeliculas("Películas al inicio", peliculaDAO);

        int idPelicula = crearPeliculaEjemplo(peliculaDAO);
        buscarPelicula(peliculaDAO, idPelicula);
        actualizarPelicula(peliculaDAO, idPelicula);
        eliminarPelicula(peliculaDAO, idPelicula);

        listarPeliculas("Películas al final", peliculaDAO);
        System.out.println("=== CRUD PELÍCULA COMPLETADO CON ÉXITO ===\n");
    }

    private static int crearPeliculaEjemplo(PeliculaDAOImpl peliculaDAO) {
        Pelicula pelicula = new Pelicula();
        pelicula.setTituloEs("Matrix");
        pelicula.setTituloEn("The Matrix");
        pelicula.setDuracionMin(136);
        pelicula.setClasificacion("PG-13");
        pelicula.setSinopsisEs("Un hacker descubre la verdad detrás de su realidad.");
        pelicula.setSinopsisEn("A hacker discovers the truth behind his reality.");
        pelicula.setImagenUrl("https://example.com/matrix.jpg");
        pelicula.setEstaActiva(true);
        return insertarPelicula(peliculaDAO, pelicula);
    }

    public static void listarPeliculas(String titulo, PeliculaDAOImpl peliculaDAO) {
        System.out.println("\n" + titulo);
        System.out.println("======================================");
        List<Pelicula> peliculas = peliculaDAO.listar();
        if (peliculas.isEmpty()) {
            System.out.println("No hay películas registradas");
        } else {
            peliculas.forEach(System.out::println);
        }
        System.out.println("======================================\n");
    }

    public static int insertarPelicula(PeliculaDAOImpl peliculaDAO, Pelicula pelicula) {
        int id = peliculaDAO.insertar(pelicula);
        if (id == -1) {
            throw new RuntimeException("Error al insertar la película");
        }
        System.out.println("CREATE: Película creada con ID: " + id);
        return id;
    }

    public static Pelicula buscarPelicula(PeliculaDAOImpl peliculaDAO, int id) {
        Pelicula pelicula = peliculaDAO.buscar(id);
        if (pelicula == null) {
            throw new RuntimeException("No se encontró la película con ID: " + id);
        }
        System.out.println("READ: Película encontrada - " + pelicula);
        return pelicula;
    }

    public static void actualizarPelicula(PeliculaDAOImpl peliculaDAO, int id) {
        Pelicula pelicula = buscarPelicula(peliculaDAO, id);
        pelicula.setTituloEs("Matrix Reloaded");
        pelicula.setTituloEn("The Matrix Reloaded");
        pelicula.setDuracionMin(138);
        pelicula.setClasificacion("R");

        if (!peliculaDAO.modificar(pelicula)) {
            throw new RuntimeException("Error al actualizar la película con ID: " + id);
        }
        System.out.println("UPDATE: Película actualizada correctamente");
    }

    public static void eliminarPelicula(PeliculaDAOImpl peliculaDAO, int id) {
        if (!peliculaDAO.eliminar(id)) {
            throw new RuntimeException("Error al eliminar la película con ID: " + id);
        }
        System.out.println("DELETE: Película eliminada correctamente");
    }
}
