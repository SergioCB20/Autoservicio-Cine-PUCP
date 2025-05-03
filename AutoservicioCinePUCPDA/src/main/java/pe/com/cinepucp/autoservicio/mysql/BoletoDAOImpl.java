package pe.com.cinepucp.autoservicio.mysql;

import java.sql.*;

import pe.com.cinepucp.autoservicio.model.venta.Boleto;
import pe.com.cinepucp.autoservicio.model.venta.Venta;
import pe.com.cinepucp.autoservicio.model.venta.EstadoBoleto;
import pe.com.cinepucp.autoservicio.model.Peliculas.Funcion;
import pe.com.cinepucp.autoservicio.dao.IBoletoDAO;

public class BoletoDAOImpl extends BaseDAOImpl<Boleto> implements IBoletoDAO{
    
    @Override
    protected PreparedStatement comandoInsertar(Connection conn, Boleto boleto) throws SQLException {
        String sql = "{CALL sp_insertar_boleto(?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, boleto.getVenta().getVentaId());
        stmt.setInt(2, boleto.getFuncion().getId());
        stmt.setString(3, boleto.getEstado().getDescripcion());
        return stmt;
    }
    
    @Override
    protected PreparedStatement comandoModificar(Connection conn, Boleto boleto) throws SQLException {
        String sql = "{CALL sp_actualizar_boleto(?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, boleto.getBoletoId());
        stmt.setInt(2, boleto.getVenta().getVentaId());
        stmt.setInt(3, boleto.getFuncion().getId());
        stmt.setString(4, boleto.getEstado().getDescripcion());
        return stmt;
    }
    
    @Override
    protected PreparedStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL sp_eliminar_boleto(?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        return stmt;
    }
    
    @Override
    protected PreparedStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL sp_buscar_boleto(?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        return stmt;
    }
    
    @Override
    protected PreparedStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL sp_listar_boletos()}";
        return conn.prepareCall(sql);
    }
    
    @Override
    protected Boleto mapearModelo(ResultSet rs) throws SQLException {
        Boleto boleto = new Boleto();
        boleto.setBoletoId(rs.getInt("boleto_id"));
        
        // Mapear la función asociada
        Funcion funcion = new Funcion();
        funcion.setId(rs.getInt("funcion_id"));;
        boleto.setFuncion(funcion);
        
        // Mapear la venta asociada
        Venta venta = new Venta();
        venta.setVentaId(rs.getInt("venta_id"));
        boleto.setVenta(venta);
        
        boleto.setEstado(EstadoBoleto.fromString(rs.getString("estado")));
        
        return boleto;
    }
    
}
