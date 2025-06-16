package pe.com.cinepucp.autoservicio.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import pe.com.cinepucp.autoservicio.dao.IVentaDAO;
import pe.com.cinepucp.autoservicio.model.auth.Usuario;
import pe.com.cinepucp.autoservicio.model.venta.EstadoVenta;
import pe.com.cinepucp.autoservicio.model.venta.MetodoPago;
import pe.com.cinepucp.autoservicio.model.venta.Venta;
import pe.com.cinepucp.autoservicio.model.venta.*;

public class VentaDAOImpl extends BaseDAOImpl<Venta> implements IVentaDAO{

    @Override
    protected PreparedStatement comandoInsertar(Connection conn, Venta modelo) throws SQLException {
        String sql = "{CALL sp_insertar_venta(?, ?, ?, ?, ?, ?, ?)}"; //7
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, modelo.getUsuario().getId());
        stmt.setTimestamp(2, Timestamp.valueOf(modelo.getFechaHora()));
        stmt.setDouble(3, modelo.getSubtotal());
        stmt.setDouble(4, modelo.getImpuestos());
        stmt.setDouble(5, modelo.getTotal());
        stmt.setString(6, modelo.getEstado().getDescripcion()); // Enum a String
        stmt.setString(7, modelo.getMetodoPago().getDescripcion()); // Enum a String
        return stmt;
    }

    @Override
    protected PreparedStatement comandoModificar(Connection conn, Venta modelo) throws SQLException {
        String sql = "{CALL sp_actualizar_venta(?, ?, ?, ?, ?, ?, ?, ?)}"; //8
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, modelo.getVentaId());
        stmt.setInt(2, modelo.getUsuario().getId());
        stmt.setTimestamp(3,Timestamp.valueOf(modelo.getFechaHora()));
        stmt.setDouble(4, modelo.getSubtotal());
        stmt.setDouble(5, modelo.getImpuestos());
        stmt.setDouble(6, modelo.getTotal());
        stmt.setString(7, modelo.getEstado().getDescripcion());
        stmt.setString(8, modelo.getMetodoPago().getDescripcion());
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
        String sql = "{CALL sp_listar_ventas()}";
        return conn.prepareCall(sql);
    }

    @Override
    protected Venta mapearModelo(ResultSet rs) throws SQLException {
        Venta venta = new Venta();
        venta.setVentaId(rs.getInt("venta_id"));
        
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("usuario_id"));
        venta.setUsuario(usuario);
        
        Timestamp ts = rs.getTimestamp("fecha_hora");
        if(ts != null){
            venta.setFechaHora(ts.toLocalDateTime());
        }
        
        venta.setSubtotal(rs.getDouble("subtotal"));
        venta.setImpuestos(rs.getDouble("impuestos"));
        venta.setTotal(rs.getDouble("total"));
        venta.setEstado(EstadoVenta.fromString(rs.getString("estado")));
        venta.setMetodoPago(MetodoPago.fromString(rs.getString("metodo_pago")));
        return venta;
    }
}
