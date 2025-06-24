package pe.com.cinepucp.autoservicio.mysql;

import java.sql.*;

import pe.com.cinepucp.autoservicio.dao.IBoletoDetalleDAO;
import pe.com.cinepucp.autoservicio.model.salas.Asiento;
import pe.com.cinepucp.autoservicio.model.venta.Boleto;
import pe.com.cinepucp.autoservicio.model.venta.BoletoDetalle;
import pe.com.cinepucp.autoservicio.model.venta.TipoBoleto;

public class BoletoDetalleDAOImpl extends BaseDAOImpl<BoletoDetalle> implements IBoletoDetalleDAO{
    
    @Override
    protected PreparedStatement comandoInsertar(Connection conn, BoletoDetalle detalle) throws SQLException {
        String sql = "{CALL sp_insertar_boleto_detalle(?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, detalle.getBoleto().getBoletoId());
        stmt.setString(2, detalle.getTipo().getDescripcion());
        stmt.setDouble(3, detalle.getPrecio());
        stmt.setInt(4, detalle.getAsiento().getId());
        return stmt;
    }
    
    @Override
    protected PreparedStatement comandoModificar(Connection conn, BoletoDetalle detalle) throws SQLException{
        return null;
    }
    
    @Override
    protected PreparedStatement comandoEliminar(Connection conn, int id) throws SQLException{
        return null;
    }
    
    @Override
    protected PreparedStatement comandoBuscar(Connection conn, int id) throws SQLException{
        return null;
    }
    
    @Override
    protected PreparedStatement comandoListar(Connection conn) throws SQLException{
        return null;
    }
    
    @Override
    protected BoletoDetalle mapearModelo(ResultSet rs) throws SQLException {
        BoletoDetalle detalle = new BoletoDetalle();
        detalle.setDetalleId(rs.getInt("detalle_id"));
        
        // Mapear el boleto asociado
        Boleto boleto = new Boleto();
        boleto.setBoletoId(rs.getInt("boleto_id"));
        detalle.setBoleto(boleto);
        
        // Mapear el asiento asociado
        Asiento asiento = new Asiento();
        asiento.setId(rs.getInt("asiento_id"));
        detalle.setAsiento(asiento);
        
        detalle.setPrecio(rs.getDouble("precio"));
        detalle.setTipo(TipoBoleto.fromString(rs.getString("tipo")));
        
        return detalle;
    }
    
}

