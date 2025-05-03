package pe.com.cinepucp.autoservicio.main.cruds;

import java.util.List;
import pe.com.cinepucp.autoservicio.dao.IBoletoDAO;
import pe.com.cinepucp.autoservicio.dao.IVentaDAO;
import pe.com.cinepucp.autoservicio.model.Peliculas.Funcion;
import pe.com.cinepucp.autoservicio.model.venta.Boleto;
import pe.com.cinepucp.autoservicio.model.venta.EstadoBoleto;
import pe.com.cinepucp.autoservicio.model.venta.Venta;
import pe.com.cinepucp.autoservicio.mysql.BoletoDAOImpl;
import pe.com.cinepucp.autoservicio.mysql.VentaDAOImpl;

public class BoletoCRUD {
    public static void ejecutarCRUDBoleto() {
        System.out.println("\n=== INICIO CRUD BOLETO ===");
        IBoletoDAO boletoDAO = new BoletoDAOImpl();

        listarBoletos("Boletos al inicio", boletoDAO);
        
        int idBoleto = crearBoletoEjemplo(boletoDAO);
        buscarBoleto(boletoDAO, idBoleto);
        actualizarBoleto(boletoDAO, idBoleto);
        eliminarBoleto(boletoDAO, idBoleto);
        
        listarBoletos("Boletos al final", boletoDAO);
        System.out.println("=== CRUD BOLETO COMPLETADO CON ÉXITO ===\n");
    }

    private static int crearBoletoEjemplo(IBoletoDAO boletoDAO) {
        Venta venta = new Venta();
        venta.setVentaId(1);
        
        Funcion funcion = new Funcion();
        funcion.setId(1);
        
        Boleto nuevoBoleto = new Boleto();
        
        nuevoBoleto.setEstado(EstadoBoleto.VALIDO);
        nuevoBoleto.setFuncion(funcion);
        nuevoBoleto.setVenta(venta);
        return insertarBoleto(boletoDAO, nuevoBoleto);
    }

    // Métodos CRUD básicos (podrían ser públicos si se usan desde otras clases)
    public static void listarBoletos(String titulo, IBoletoDAO boletoDAO) {
        System.out.println("\n" + titulo);
        System.out.println("======================================");
        List<Boleto> boletos = boletoDAO.listar();
        if (boletos.isEmpty()) {
            System.out.println("No hay boletos registradas");
        } else {
            boletos.forEach(System.out::println);
        }
        System.out.println("======================================\n");
    }

    public static int insertarBoleto(IBoletoDAO boletoDAO, Boleto boleto) {
        int idBoleto = boletoDAO.insertar(boleto);
        if (idBoleto == -1) {
            throw new RuntimeException("Error al crear el boleto");
        }
        System.out.println("CREATE: Boleto creado con ID: " + idBoleto);
        return idBoleto;
    }

    public static Boleto buscarBoleto(IBoletoDAO boletoDAO, int id) {
        Boleto boleto = boletoDAO.buscar(id);
        if (boleto == null) {
            throw new RuntimeException("No se encontró el boleto con ID: " + id);
        }
        System.out.println("READ: Boleto encontrado - " + boleto);
        return boleto;
    }

    public static void actualizarBoleto(IBoletoDAO boletoDAO, int id) {
        Boleto boleto = buscarBoleto(boletoDAO, id);
        boleto.setEstado(EstadoBoleto.USADO);
        
        if (!boletoDAO.modificar(boleto)) {
            throw new RuntimeException("Error al actualizar el boleto con ID: " + id);
        }
        System.out.println("UPDATE: Boleto actualizado correctamente");
    }

    public static void eliminarBoleto(IBoletoDAO boletoDAO, int id) {
        if (!boletoDAO.eliminar(id)) {
            throw new RuntimeException("Error al eliminar el boleto con ID: " + id);
        }
        System.out.println("DELETE: Boleto eliminado correctamente");
    }
}
