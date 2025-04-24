
package pe.com.cinepucp.autoservicio.main.cruds;
import java.util.List;
import pe.com.cinepucp.autoservicio.model.Peliculas.Genero;
import pe.com.cinepucp.autoservicio.main.mysql.GeneroDAOImpl;

public class GeneroCRUD{
    public static void ejecutarCRUDGenero() {
        System.out.println("\n=== INICIO CRUD GENERO ===");
        GeneroDAOImpl generoDAO = new GeneroDAOImpl();

        listarGeneros("Géneros al inicio", generoDAO);

        int idGenero = crearGeneroEjemplo(generoDAO);
        buscarGenero(generoDAO, idGenero);
        actualizarGenero(generoDAO, idGenero);
        eliminarGenero(generoDAO, idGenero);

        listarGeneros("Géneros al final", generoDAO);
        System.out.println("=== CRUD GENERO COMPLETADO CON ÉXITO ===\n");
    }

    private static int crearGeneroEjemplo(GeneroDAOImpl generoDAO) {
        Genero nuevoGenero = new Genero();
        nuevoGenero.setNombreEs("Acción");
        nuevoGenero.setNombreEn("Action");
        return insertarGenero(generoDAO, nuevoGenero);
    }

    public static void listarGeneros(String titulo, GeneroDAOImpl generoDAO) {
        System.out.println("\n" + titulo);
        System.out.println("======================================");
        List<Genero> generos = generoDAO.listar();
        if (generos.isEmpty()) {
            System.out.println("No hay géneros registrados");
        } else {
            generos.forEach(System.out::println);
        }
        System.out.println("======================================\n");
    }

    public static int insertarGenero(GeneroDAOImpl generoDAO, Genero genero) {
        int idGenero = generoDAO.insertar(genero);
        if (idGenero == -1) {
            throw new RuntimeException("Error al crear el género");
        }
        System.out.println("CREATE: Género creado con ID: " + idGenero);
        return idGenero;
    }

    public static Genero buscarGenero(GeneroDAOImpl generoDAO, int id) {
        Genero genero = generoDAO.buscar(id);
        if (genero == null) {
            throw new RuntimeException("No se encontró el género con ID: " + id);
        }
        System.out.println("READ: Género encontrado - " + genero);
        return genero;
    }

    public static void actualizarGenero(GeneroDAOImpl generoDAO, int id) {
        Genero genero = buscarGenero(generoDAO, id);
        genero.setNombreEs("Aventura");
        genero.setNombreEn("Adventure");

        if (!generoDAO.modificar(genero)) {
            throw new RuntimeException("Error al actualizar el género con ID: " + id);
        }
        System.out.println("UPDATE: Género actualizado correctamente");
    }

    public static void eliminarGenero(GeneroDAOImpl generoDAO, int id) {
        if (!generoDAO.eliminar(id)) {
            throw new RuntimeException("Error al eliminar el género con ID: " + id);
        }
        System.out.println("DELETE: Género eliminado correctamente");
    }
}
