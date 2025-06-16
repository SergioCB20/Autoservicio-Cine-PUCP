/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.main.cruds;
import java.sql.*;
import java.util.List;
import pe.com.cinepucp.autoservicio.mysql.ProductoDAOImpl;
import pe.com.cinepucp.autoservicio.model.comida.Producto;
import pe.com.cinepucp.autoservicio.model.comida.TipoProducto;

/**
 *
 * @author gonza
 */
public class ProductoCRUD {

    public static void ejecutarCRUDProducto() {
        System.out.println("\n=== INICIO CRUD PRODUCTO ===");
        ProductoDAOImpl productoDAO = new ProductoDAOImpl();

        listarProductos("Productos al inicio", productoDAO);

        int idProducto = crearProductoEjemplo(productoDAO);
        buscarProducto(productoDAO, idProducto);
        actualizarProducto(productoDAO, idProducto);
        eliminarProducto(productoDAO, idProducto);

        listarProductos("Productos al final", productoDAO);
        System.out.println("=== CRUD PRODUCTO COMPLETADO CON ÉXITO ===\n");
    }

    private static int crearProductoEjemplo(ProductoDAOImpl productoDAO) {
        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre_es("Gaseosa");
        nuevoProducto.setDescripcion_es("Bebida fría de 500ml");
        nuevoProducto.setPrecio(5.5);
        nuevoProducto.setTipo(TipoProducto.BEBIDA);
        nuevoProducto.setEstaActivo(true);
        return insertarProducto(productoDAO, nuevoProducto);
    }

    public static void listarProductos(String titulo, ProductoDAOImpl productoDAO) {
        System.out.println("\n" + titulo);
        System.out.println("======================================");
        List<Producto> productos = productoDAO.listar();
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados");
        } else {
            productos.forEach(System.out::println);
        }
        System.out.println("======================================\n");
    }

    public static int insertarProducto(ProductoDAOImpl productoDAO, Producto producto) {
        int idProducto = productoDAO.insertar(producto);
        if (idProducto == -1) {
            throw new RuntimeException("Error al crear el producto");
        }
        System.out.println("CREATE: Producto creado con ID: " + idProducto);
        return idProducto;
    }

    public static Producto buscarProducto(ProductoDAOImpl productoDAO, int id) {
        Producto producto = productoDAO.buscar(id);
        if (producto == null) {
            throw new RuntimeException("No se encontró el producto con ID: " + id);
        }
        System.out.println("READ: Producto encontrado - " + producto);
        return producto;
    }

    public static void actualizarProducto(ProductoDAOImpl productoDAO, int id) {
        Producto producto = buscarProducto(productoDAO, id);
        producto.setNombre_es("Gaseosa Light");
        producto.setDescripcion_es("Bebida sin azúcar de 500ml");
        producto.setPrecio(6.0);
        producto.setTipo(TipoProducto.BEBIDA);
        producto.setEstaActivo(true);

        if (!productoDAO.modificar(producto)) {
            throw new RuntimeException("Error al actualizar el producto con ID: " + id);
        }
        System.out.println("UPDATE: Producto actualizado correctamente");
    }

    public static void eliminarProducto(ProductoDAOImpl productoDAO, int id) {
        if (!productoDAO.eliminar(id)) {
            throw new RuntimeException("Error al eliminar el producto con ID: " + id);
        }
        System.out.println("DELETE: Producto eliminado correctamente");
    }
}
