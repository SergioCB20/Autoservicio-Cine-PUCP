package pe.com.cinepucp.autoservicio.main.cruds;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import pe.com.cinepucp.autoservicio.model.auth.Usuario;
import pe.com.cinepucp.autoservicio.model.venta.Cupon;
import pe.com.cinepucp.autoservicio.mysql.CuponDAOImpl;
import pe.com.cinepucp.autoservicio.model.venta.TipoDescuento;
import pe.com.cinepucp.autoservicio.mysql.UsuarioDAOImpl;

public class CuponCRUD {

    public static void ejecutarCRUDCupon(){
        System.out.println("\n=== INICIO CRUD CUPON ===");
        CuponDAOImpl cuponDAO = new CuponDAOImpl();

        listarCupon("Cupones al inicio", cuponDAO);
        
        int idCupon = crearCuponEjm(cuponDAO);
        buscarCupon(cuponDAO, idCupon);
        actualizarCupon(cuponDAO, idCupon);
        eliminarCupon(cuponDAO, idCupon);

        listarCupon(null, cuponDAO);
        System.out.println("\n=== CRUD CUPONES COMPLETADO CON ÉXITO ===");
    }

    private static int crearCuponEjm(CuponDAOImpl cuponDAO){
        Cupon nuevoCupon = new Cupon();
        nuevoCupon.setCodigo("DESCUENTO25");
        nuevoCupon.setDescripcionEs("Descuento del 20% en tu compra");
        nuevoCupon.setDescripcionEn("20% discount on your purchase");
        nuevoCupon.setDescuentoTipo(TipoDescuento.PORCENTAJE);
        nuevoCupon.setFechaInicio(LocalDate.now());
        nuevoCupon.setFechaFin(LocalDate.now());
        nuevoCupon.setMaxUsos(100);
        Usuario usuario = new Usuario();
        usuario.setId(2); 
        nuevoCupon.setCreadoPor(usuario);
        nuevoCupon.setDescuentoValor(20.00);
        nuevoCupon.setUsosActuales(0);
        nuevoCupon.setFechaModificacion(LocalDate.now());
        nuevoCupon.setActivo(true);
        nuevoCupon.setModificadoPor(usuario);
        return insertarCupon(cuponDAO, nuevoCupon);
    }

    public static void listarCupon(String titulo, CuponDAOImpl cuponDAO){
        System.out.println("\n" + titulo);
        System.out.println("======================================");
        List<Cupon> cupon = cuponDAO.listar();
        if(cupon.isEmpty()){
            System.out.println("No hay ventas registradas");
        }else{
            cupon.forEach(System.out::println);
        }
        System.out.println("======================================\n");

    }

    public static int insertarCupon(CuponDAOImpl cuponDAO, Cupon cupon){
        int idCupon = cuponDAO.insertar(cupon);
        if(idCupon == -1){
            throw new RuntimeException("Error al crear el cupón");
        }
        System.out.println("CREATE: Cupón creado con ID: " + idCupon);
        return idCupon;
    }

    public static Cupon buscarCupon(CuponDAOImpl cuponDAO, int id){
        Cupon cupon = cuponDAO.buscar(id);
        if (cupon == null) {
            throw new RuntimeException("No se encontró el cupón con ID: " + id);
        }
        System.out.println("READ: Cupón encontrado - " + cupon);
        return cupon;
    }

    public static void actualizarCupon(CuponDAOImpl cuponDAO,int id){
        Cupon cupon = cuponDAO.buscar(id);
        cupon.setUsosActuales(50);
        cuponDAO.modificar(cupon);
        if (cupon == null) {
            throw new RuntimeException("Error al actualizar el cupón con ID: " + id);
        }
        System.out.println("UPDATE: Cupón actualizado correctamente");
    }

    public static void eliminarCupon(CuponDAOImpl cuponDAO, int id) {
        if (!cuponDAO.eliminar(id)) {
            throw new RuntimeException("Error al eliminar el cupón con ID: " + id);
        }
        System.out.println("DELETE: Cupón eliminado correctamente");
    }
}
