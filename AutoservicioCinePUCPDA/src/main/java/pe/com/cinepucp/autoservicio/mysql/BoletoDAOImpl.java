/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.mysql;
import pe.com.cinepucp.autoservicio.model.venta.Boleto;
import pe.com.cinepucp.autoservicio.model.venta.Venta;
import pe.com.cinepucp.autoservicio.model.venta.TipoBoleto;
import pe.com.cinepucp.autoservicio.model.Peliculas.Funcion;
import pe.com.cinepucp.autoservicio.model.salas.Asiento;
import pe.com.cinepucp.autoservicio.dao.IBoletoDAO;
import java.sql.*;
/**
 *
 * @author Piero
 */
public class BoletoDAOImpl extends BaseDAOImpl<Boleto> implements IBoletoDAO{
    
    @Override
    protected PreparedStatement comandoInsertar(Connection conn, Boleto boleto) throws SQLException {
        String sql = "{CALL sp_insertar_boleto(?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, boleto.getFuncion().getId());
        stmt.setString(2, boleto.getTipo().getDescripcion());
        stmt.setBigDecimal(3, boleto.getPrecio());
        stmt.setString(4, boleto.getCodigoQr());
        stmt.setBoolean(5, boleto.isUsado());
        stmt.setInt(6, boleto.getAsiento().getId());
        stmt.setInt(7, boleto.getVenta().getId());
        return stmt;
    }
    
    @Override
    protected PreparedStatement comandoModificar(Connection conn, Boleto boleto) throws SQLException {
        String sql = "{CALL sp_actualizar_boleto(?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, boleto.getId());
        stmt.setInt(2, boleto.getFuncion().getId());
        stmt.setString(3, boleto.getTipo().getDescripcion());
        stmt.setBigDecimal(4, boleto.getPrecio());
        stmt.setString(5, boleto.getCodigoQr());
        stmt.setBoolean(5, boleto.isUsado());
        stmt.setInt(6, boleto.getAsiento().getId());
        stmt.setInt(7, boleto.getVenta().getId());
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
        boleto.setId(rs.getInt("boleto_id"));
        
        // Mapear la función asociada
        Funcion funcion = new Funcion();
        funcion.setId(rs.getInt("funcion_id"));;
        boleto.setFuncion(funcion);
        
        // Mapear el asiento asociado
        Asiento asiento = new Asiento();
        asiento.setId(rs.getInt("asiento_id"));
        boleto.setAsiento(asiento);
        
        // Mapear la venta asociada
        Venta venta = new Venta();
        venta.setId(rs.getInt("venta_id"));
        boleto.setVenta(venta);
        
        boleto.setTipo(TipoBoleto.fromString(rs.getString("tipo")));
        boleto.setPrecio(rs.getBigDecimal("precio"));
        boleto.setCodigoQr(rs.getString("codigoQr"));
        boleto.setUsado(rs.getBoolean("usado"));
        
        return boleto;
    }
    
}
