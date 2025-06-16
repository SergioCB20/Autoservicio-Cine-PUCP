package pe.com.cinepucp.autoservicio.mysql;

import java.sql.*;
import pe.com.cinepucp.autoservicio.dao.IProductoDAO;
import pe.com.cinepucp.autoservicio.model.comida.Producto;
import pe.com.cinepucp.autoservicio.model.comida.TipoProducto;

public class ProductoDAOImpl extends BaseDAOImpl<Producto> implements IProductoDAO {

    private final int usuarioModificacionId = 4;

    @Override
    protected PreparedStatement comandoInsertar(Connection conn, Producto producto) throws SQLException {
        String sql = "{CALL sp_insertar_producto(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setString(1, producto.getNombre_es());
        stmt.setString(2, producto.getNombre_en());        
        stmt.setString(3, producto.getDescripcion_es());
        stmt.setString(4, producto.getDescripcion_en());        
        stmt.setDouble(5, producto.getPrecio());
        stmt.setString(6, producto.getImagenUrl());
        stmt.setString(7, producto.getTipo().name());
        stmt.setBoolean(8, producto.isEstaActivo());
        stmt.setInt(9,producto.getUsuarioModificacion());
        return stmt;
    }

    @Override
    protected PreparedStatement comandoModificar(Connection conn, Producto producto) throws SQLException {
        String sql = "{CALL sp_actualizar_producto(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, producto.getId());
        stmt.setString(2, producto.getNombre_es());
        stmt.setString(3, producto.getNombre_en());        
        stmt.setString(4, producto.getDescripcion_es());
        stmt.setString(5, producto.getDescripcion_en());        
        stmt.setDouble(6, producto.getPrecio());
        stmt.setString(7, producto.getImagenUrl());
        stmt.setString(8, producto.getTipo().name());
        stmt.setBoolean(9, producto.isEstaActivo());
        stmt.setInt(10,producto.getUsuarioModificacion());
        
        return stmt;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL sp_eliminar_producto(?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        stmt.setInt(2, usuarioModificacionId);

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
        String sql = "{CALL sp_listar_productos(False)}";
        return conn.prepareCall(sql);
    }

    @Override
    protected Producto mapearModelo(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        producto.setId(rs.getInt("producto_id"));
        producto.setNombre_es(rs.getString("nombre_es"));
        producto.setNombre_en(rs.getString("nombre_en"));        
        producto.setDescripcion_es(rs.getString("descripcion_es"));
        producto.setDescripcion_en(rs.getString("descripcion_en"));        
        producto.setPrecio(rs.getDouble("precio"));
        producto.setTipo(TipoProducto.valueOf(rs.getString("tipo").toUpperCase()));
        producto.setImagenUrl(rs.getString("imagen_url"));
        producto.setEstaActivo(rs.getBoolean("esta_activo"));
        producto.setFechaModificacion(rs.getTimestamp("fecha_modificacion"));
        return producto;
    }
}

