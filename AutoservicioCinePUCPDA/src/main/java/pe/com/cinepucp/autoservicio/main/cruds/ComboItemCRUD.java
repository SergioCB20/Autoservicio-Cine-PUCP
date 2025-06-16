/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.main.cruds;

/**
 *
 * @author gonza
 */
import java.util.List;
import pe.com.cinepucp.autoservicio.model.comida.Producto;
import pe.com.cinepucp.autoservicio.model.comida.ComboItem;

import pe.com.cinepucp.autoservicio.mysql.ComboItemDAOImpl;

public class ComboItemCRUD {

    public static void ejecutarCRUDComboItem() {
        System.out.println("\n=== INICIO CRUD COMBO ITEM ===");
        ComboItemDAOImpl comboItemDAO = new ComboItemDAOImpl();

        listarComboItems("ComboItems al inicio", comboItemDAO);

        int idComboItem = crearComboItemEjemplo(comboItemDAO);
        buscarComboItem(comboItemDAO, idComboItem);
        actualizarComboItem(comboItemDAO, idComboItem);
        eliminarComboItem(comboItemDAO, idComboItem);

        listarComboItems("ComboItems al final", comboItemDAO);
        System.out.println("=== CRUD COMBO ITEM COMPLETADO CON ÉXITO ===\n");
    }

    private static int crearComboItemEjemplo(ComboItemDAOImpl comboItemDAO) {
        ComboItem item = new ComboItem();

        Producto combo = new Producto();
        combo.setId(1); // ID de un combo existente
        combo.setNombre_es("Combo Almuerzo");

        Producto producto = new Producto();
        producto.setId(2); // ID de un producto existente
        producto.setNombre_es("Bebida");

        item.setCombo(combo);
        item.setProducto(producto);
        item.setCantidad(1);

        return insertarComboItem(comboItemDAO, item);
    }

    public static void listarComboItems(String titulo, ComboItemDAOImpl comboItemDAO) {
        System.out.println("\n" + titulo);
        System.out.println("======================================");
        List<ComboItem> items = comboItemDAO.listar();
        if (items.isEmpty()) {
            System.out.println("No hay combo items registrados");
        } else {
            items.forEach(System.out::println);
        }
        System.out.println("======================================\n");
    }

    public static int insertarComboItem(ComboItemDAOImpl comboItemDAO, ComboItem item) {
        int id = comboItemDAO.insertar(item);
        if (id == -1) {
            throw new RuntimeException("Error al crear el combo item");
        }
        System.out.println("CREATE: ComboItem creado con ID: " + id);
        return id;
    }

    public static ComboItem buscarComboItem(ComboItemDAOImpl comboItemDAO, int id) {
        ComboItem item = comboItemDAO.buscar(id);
        if (item == null) {
            throw new RuntimeException("No se encontró el combo item con ID: " + id);
        }
        System.out.println("READ: ComboItem encontrado - " + item);
        return item;
    }

    public static void actualizarComboItem(ComboItemDAOImpl comboItemDAO, int id) {
        ComboItem item = buscarComboItem(comboItemDAO, id);
        item.setCantidad(item.getCantidad() + 1); // Por ejemplo, aumentamos la cantidad

        if (!comboItemDAO.modificar(item)) {
            throw new RuntimeException("Error al actualizar el combo item con ID: " + id);
        }
        System.out.println("UPDATE: ComboItem actualizado correctamente");
    }

    public static void eliminarComboItem(ComboItemDAOImpl comboItemDAO, int id) {
        if (!comboItemDAO.eliminar(id)) {
            throw new RuntimeException("Error al eliminar el combo item con ID: " + id);
        }
        System.out.println("DELETE: ComboItem eliminado correctamente");
    }
}
