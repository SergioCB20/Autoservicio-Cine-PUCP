package pe.com.cinepucp.autoservicio.main.cruds;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import pe.com.cinepucp.autoservicio.model.auth.Usuario;
import pe.com.cinepucp.autoservicio.model.venta.EstadoVenta;
import pe.com.cinepucp.autoservicio.model.venta.MetodoPago;
import pe.com.cinepucp.autoservicio.model.venta.Venta;
import pe.com.cinepucp.autoservicio.mysql.UsuarioDAOImpl;
import pe.com.cinepucp.autoservicio.mysql.VentaDAOImpl;


public class VentaCRUD {
    public static void ejecutarCRUDVenta(){
        System.out.println("\n=== INICIO CRUD VENTA ===");
        VentaDAOImpl ventaDAO = new VentaDAOImpl();

        //listarVentas("Ventas al inicio", ventaDAO);
        
//        int idVentas = crearVentaEjm(ventaDAO);
//        buscarVenta(ventaDAO, idVentas);
//        actualizarVenta(ventaDAO, idVentas);
//        eliminarVenta(ventaDAO, idVentas);
//
//        listarVentas(null, ventaDAO);
        listarVentasreporte("Ventas en rango", ventaDAO);
        System.out.println("\n=== CRUD VENTAS COMPLETADO CON ÉXITO ===");
    }

    private static int crearVentaEjm(VentaDAOImpl ventaDAO){
         Venta nuevaventa = new Venta();
         
         Usuario usuario = new Usuario();
         usuario.setId(2);
         
         nuevaventa.setFechaHora(LocalDateTime.now());
         nuevaventa.setUsuario(usuario);
         nuevaventa.setSubtotal(24.3);
         nuevaventa.setImpuestos(4.3);
         nuevaventa.setTotal(44.3);
         nuevaventa.setEstado(EstadoVenta.PENDIENTE);
         nuevaventa.setMetodoPago(MetodoPago.TARJETA);
        return insertarVenta(ventaDAO,nuevaventa);

    }

    public static void listarVentas(String titulo, VentaDAOImpl ventaDAO){
        System.out.println("\n" + titulo);
        System.out.println("======================================");
        List<Venta> ventas = ventaDAO.listar();
        if(ventas.isEmpty()){
            System.out.println("No hay ventas registradas");
        }else{
            ventas.forEach(System.out::println);
        }
        System.out.println("======================================\n");

    }
    public static void listarVentasreporte(String titulo, VentaDAOImpl ventaDAO){
        System.out.println("\n" + titulo);
        System.out.println("======================================");
        List<Venta> ventas = ventaDAO.listarVentasRep("2025-06-01","2025-06-27");
        if(ventas.isEmpty()){
            System.out.println("No hay ventas registradas");
        }else{
            ventas.forEach(System.out::println);
        }
        System.out.println("======================================\n");

    }

    public static int insertarVenta(VentaDAOImpl ventaDAO, Venta venta){
        int idVenta = ventaDAO.insertar(venta);
        if(idVenta == -1){
            throw new RuntimeException("Error al crear la venta");
        }
        System.out.println("CREATE: Venta creada con ID: " + idVenta);
        return idVenta;
    }

    public static Venta buscarVenta(VentaDAOImpl ventaDAO, int id){
        Venta venta = ventaDAO.buscar(id);
        if (venta == null) {
            throw new RuntimeException("No se encontró la venta con ID: " + id);
        }
        System.out.println("READ: Venta encontrada - " + venta);
        return venta;
    }

    public static void actualizarVenta(VentaDAOImpl ventaDAO,int id){
        Venta venta = ventaDAO.buscar(id);
        venta.setEstado(EstadoVenta.COMPLETADA);
        ventaDAO.modificar(venta);
        if (venta == null) {
            throw new RuntimeException("Error al actualizar la venta con ID: " + id);
        }
        System.out.println("UPDATE: Venta actualizada correctamente");
    }

    public static void eliminarVenta(VentaDAOImpl ventaDAO, int id) {
        if (!ventaDAO.eliminar(id)) {
            throw new RuntimeException("Error al eliminar el venta con ID: " + id);
        }
        System.out.println("DELETE: venta eliminado correctamente");
    }
}
