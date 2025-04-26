package pe.com.cinepucp.autoservicio.mysql;

import java.sql.*;
import pe.com.cinepucp.autoservicio.dao.IProductoDAO;
import pe.com.cinepucp.autoservicio.model.comida.Producto;
import pe.com.cinepucp.autoservicio.model.comida.TipoProducto;

public class ProductoDAOImpl extends BaseDAOImpl<Producto> implements IProductoDAO {

    @Override
    protected PreparedStatement comandoInsertar(Connection conn, Producto producto) throws SQLException {
        String sql = "{CALL sp_insertar_producto(?, ?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setString(1, producto.getNombre());
        stmt.setString(2, producto.getDescripcion());
        stmt.setDouble(3, producto.getPrecio());
        stmt.setString(4, producto.getTipo().name());
        stmt.setBoolean(5, producto.isEstaActivo());
        return stmt;
    }

    @Override
    protected PreparedStatement comandoModificar(Connection conn, Producto producto) throws SQLException {
        String sql = "{CALL sp_actualizar_producto(?, ?, ?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, producto.getId());
        stmt.setString(2, producto.getNombre());
        stmt.setString(3, producto.getDescripcion());
        stmt.setDouble(4, producto.getPrecio());
        stmt.setString(5, producto.getTipo().name());
        stmt.setBoolean(6, producto.isEstaActivo());
        return stmt;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL sp_eliminar_producto(?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        return stmt;
    }

    @Override
    protected PreparedStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL sp_buscar_producto(?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        return stmt;
    }

    @Override
    protected PreparedStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL sp_listar_productos()}";
        return conn.prepareCall(sql);
    }

    @Override
    protected Producto mapearModelo(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        producto.setId(rs.getInt("producto_id"));
        producto.setNombre(rs.getString("nombre"));
        producto.setDescripcion(rs.getString("descripcion"));
        producto.setPrecio(rs.getDouble("precio"));
        producto.setTipo(TipoProducto.valueOf(rs.getString("tipo").toUpperCase()));
        producto.setEstaActivo(rs.getBoolean("esta_activo"));
        return producto;
    }
}

