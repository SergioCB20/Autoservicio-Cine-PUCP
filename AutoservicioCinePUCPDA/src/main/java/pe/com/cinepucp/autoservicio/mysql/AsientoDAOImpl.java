/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.mysql;
import java.sql.*;
import pe.com.cinepucp.autoservicio.dao.IAsientoDAO;
import pe.com.cinepucp.autoservicio.model.salas.Asiento;
import pe.com.cinepucp.autoservicio.model.salas.Sala;

/**
 *
 * @author Sergio
 */

public class AsientoDAOImpl extends BaseDAOImpl<Asiento> implements IAsientoDAO {

    @Override
    protected PreparedStatement comandoInsertar(Connection conn, Asiento asiento) throws SQLException {
        String sql = "{CALL sp_insertar_asiento(?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, asiento.getSala().getId());
        stmt.setString(2, String.valueOf(asiento.getFila()));
        stmt.setInt(3, asiento.getNumero());
        stmt.setString(4, asiento.getTipo());
        return stmt;
    }

    @Override
    protected PreparedStatement comandoModificar(Connection conn, Asiento asiento) throws SQLException {
        String sql = "{CALL sp_actualizar_asiento(?, ?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, asiento.getId());
        stmt.setInt(2, asiento.getSala().getId());
        stmt.setString(3, String.valueOf(asiento.getFila()));
        stmt.setInt(4, asiento.getNumero());
        stmt.setString(5, asiento.getTipo());
        return stmt;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL sp_eliminar_asiento(?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        return stmt;
    }

    @Override
    protected PreparedStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL sp_buscar_asiento(?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        return stmt;
    }

    @Override
    protected PreparedStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL sp_listar_asientos()}";
        return conn.prepareCall(sql);
    }

   @Override
    protected Asiento mapearModelo(ResultSet rs) throws SQLException {
        Asiento asiento = new Asiento();
        asiento.setId(rs.getInt("asiento_id"));
        
        Sala sala = new Sala();
        sala.setId(rs.getInt("sala_id"));
        sala.setNombre(rs.getString("sala_nombre")); 
        asiento.setSala(sala);
        
        asiento.setFila(rs.getString("fila").charAt(0));
        asiento.setNumero(rs.getInt("numero"));
        asiento.setTipo(rs.getString("tipo"));
        
        return asiento;
    }
}