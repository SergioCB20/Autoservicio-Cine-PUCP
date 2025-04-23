package pe.com.cinepucp.autoservicio.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import pe.com.cinepucp.autoservicio.dao.IVentaDAO;
import pe.com.cinepucp.autoservicio.model.venta.Venta;

public class VentaDAOImpl extends BaseDAOImpl<Venta> implements IVentaDAO{

    @Override
    protected PreparedStatement comandoInsertar(Connection conn, Venta modelo) throws SQLException {
        String sql = "{CALL sp_insertar_venta(?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setTimestamp(1,Timestamp.valueOf(modelo.getFechaHora()) );
        stmt.setBigDecimal(2, modelo.getSubtotal());
        stmt.setBigDecimal(3, modelo.getImpuestos());
        stmt.setBigDecimal(4, modelo.getTotal());
        stmt.setString(5, modelo.getEstado());
        stmt.setString(6, modelo.getMetodoPago());
        stmt.setString(7, modelo.getCodigoQr());
        return stmt;
    }

    @Override
    protected PreparedStatement comandoModificar(Connection conn, Venta modelo) throws SQLException {
        String sql = "{CALL sp_insertar_venta(?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, modelo.getId());
        stmt.setTimestamp(2,Timestamp.valueOf(modelo.getFechaHora()) );
        stmt.setBigDecimal(3, modelo.getSubtotal());
        stmt.setBigDecimal(4, modelo.getImpuestos());
        stmt.setBigDecimal(5, modelo.getTotal());
        stmt.setString(6, modelo.getEstado());
        stmt.setString(7, modelo.getMetodoPago());
        stmt.setString(8, modelo.getCodigoQr());
        return stmt;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL sp_eliminar_venta(?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        return stmt;
    }

    @Override
    protected PreparedStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL sp_buscar_venta(?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        return stmt;
    }

    @Override
    protected PreparedStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL sp_listar_venta()}";
        return conn.prepareCall(sql);
    }

    @Override
    protected Venta mapearModelo(ResultSet rs) throws SQLException {
        Venta venta = new Venta();
        venta.setId(rs.getInt("id"));
        venta.setFechaHora(rs.getTimestamp("fecha_hora").toLocalDateTime());
        venta.setSubtotal(rs.getBigDecimal("subtotal"));
        venta.setImpuestos(rs.getBigDecimal("impuestos"));
        venta.setTotal(rs.getBigDecimal("total"));
        venta.setEstado(rs.getString("estado"));
        venta.setMetodoPago(rs.getString("metodo_pago"));
        venta.setCodigoQr(rs.getString("codigo_qr"));
        return venta;
    }

}
