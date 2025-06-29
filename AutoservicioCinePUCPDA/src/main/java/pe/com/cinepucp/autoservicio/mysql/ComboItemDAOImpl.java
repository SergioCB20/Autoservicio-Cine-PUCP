/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.mysql;

/**
 *
 * @author gonza
 */
import java.sql.*;
import pe.com.cinepucp.autoservicio.dao.IComboItemDAO;
import pe.com.cinepucp.autoservicio.model.comida.Producto;
import pe.com.cinepucp.autoservicio.model.comida.ComboItem;

public class ComboItemDAOImpl extends BaseDAOImpl<ComboItem> implements IComboItemDAO {

    private final int usuarioModificacionId = 1; // TODO: Obtener el ID del usuario de la sesión

    @Override
    protected PreparedStatement comandoInsertar(Connection conn, ComboItem comboItem) throws SQLException {
        String sql = "{CALL sp_insertar_combo_item(?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, comboItem.getCombo().getId());
        stmt.setInt(2, comboItem.getProducto().getId());
        stmt.setInt(3, comboItem.getCantidad());
        stmt.setInt(4, usuarioModificacionId);
        return stmt;
    }

    @Override
    protected PreparedStatement comandoModificar(Connection conn, ComboItem comboItem) throws SQLException {
        String sql = "{CALL sp_actualizar_combo_item(?, ?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, comboItem.getCombo().getId());
        stmt.setInt(2, comboItem.getProducto().getId());
        stmt.setInt(3, comboItem.getCantidad());
        stmt.setInt(4, usuarioModificacionId);
        stmt.setInt(5, comboItem.getProducto().getId()); // Asumiendo que se usa como ID para modificar
        return stmt;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, int id,int id_modificacion) throws SQLException {
        String sql = "{CALL sp_eliminar_combo_item(?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        stmt.setInt(2, usuarioModificacionId);
        return stmt;
    }

    @Override
    protected PreparedStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL sp_leer_combo_item(?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        return stmt;
    }

    @Override
    protected PreparedStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL sp_listar_combo_items()}";
        return conn.prepareCall(sql);
    }

    @Override
    protected ComboItem mapearModelo(ResultSet rs) throws SQLException {
        ComboItem item = new ComboItem();

        Producto combo = new Producto();
        combo.setId(rs.getInt("combo_id"));
        combo.setNombre_es(rs.getString("combo_nombre_es")); // Asegúrate que el SP lo retorne
        combo.setNombre_en(rs.getString("combo_nombre_en"));
        item.setCombo(combo);

        Producto producto = new Producto();
        producto.setId(rs.getInt("producto_id"));
        producto.setNombre_es(rs.getString("producto_nombre_es")); // Asegúrate que el SP lo retorne
        producto.setNombre_en(rs.getString("producto_nombre_en")); // Asegúrate que el SP lo retorne
        item.setProducto(producto);

        item.setCantidad(rs.getInt("cantidad"));

        return item;
    }
}
