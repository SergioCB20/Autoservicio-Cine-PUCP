package pe.com.cinepucp.autoservicio.mysql;

import java.sql.*;
import pe.com.cinepucp.autoservicio.dao.ICuponDAO;
import pe.com.cinepucp.autoservicio.model.venta.Cupon;

public class CuponDAOImpl extends BaseDAOImpl<Cupon> implements ICuponDAO{

    @Override
    protected PreparedStatement comandoInsertar(Connection conn, Cupon modelo) throws SQLException {
        // Usamos CALL para ejecutar el procedimiento almacenado
        String sql = "{CALL sp_insertar_cupon(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setString(1, modelo.getCodigo());
        stmt.setString(2, modelo.getDescripcionEs());
        stmt.setString(3, modelo.getDescripcionEn());
        stmt.setString(4, modelo.getDescuentoTipo());
        stmt.setBigDecimal(5, modelo.getDescuentoValor());
        stmt.setDate(6, Date.valueOf(modelo.getFechaInicio()));
        stmt.setDate(7, Date.valueOf(modelo.getFechaFin()));
        stmt.setInt(8, modelo.getUsosActuales());
        return stmt;
    }

    @Override
    protected PreparedStatement comandoModificar(Connection conn, Cupon modelo) throws SQLException {
        String sql = "{CALL sp_insertar_cupon(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, modelo.getId());
        stmt.setString(2, modelo.getCodigo());
        stmt.setString(3, modelo.getDescripcionEs());
        stmt.setString(4, modelo.getDescripcionEn());
        stmt.setString(5, modelo.getDescuentoTipo());
        stmt.setBigDecimal(6, modelo.getDescuentoValor());
        stmt.setDate(7, Date.valueOf(modelo.getFechaInicio()));
        stmt.setDate(8, Date.valueOf(modelo.getFechaFin()));
        stmt.setInt(9, modelo.getUsosActuales());
        return stmt;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL sp_eliminar_cupon(?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        return stmt;
    }

    @Override
    protected PreparedStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL sp_buscar_cupon(?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        return stmt;
    }

    @Override
    protected PreparedStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL sp_listar_cupon()}";
        return conn.prepareCall(sql);    
    }

    @Override
    protected Cupon mapearModelo(ResultSet rs) throws SQLException {
        Cupon cupon = new Cupon();
        cupon.setId(rs.getInt("id"));
        cupon.setCodigo(rs.getString("codigo"));
        cupon.setDescripcionEs(rs.getString("descripcion_es"));
        cupon.setDescripcionEn(rs.getString("descripcion_en"));
        cupon.setDescuentoTipo(rs.getString("descuento_tipo"));
        cupon.setDescuentoValor(rs.getBigDecimal("descuento_valor"));
        cupon.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
        cupon.setFechaFin(rs.getDate("fecha_fin").toLocalDate());
        cupon.setMaxUsos(rs.getObject("max_usos", Integer.class)); // por si es nullable
        cupon.setUsosActuales(rs.getInt("usos_actuales"));
        return cupon;
    }
    
}
