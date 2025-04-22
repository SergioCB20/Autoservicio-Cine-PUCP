/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.mysql;
import java.sql.*;
import pe.com.cinepucp.autoservicio.dao.ISalaDAO;
import pe.com.cinepucp.autoservicio.model.salas.Sala;

/**
 *
 * @author Sergio
 */

public class SalaDAOImpl extends BaseDAOImpl<Sala> implements ISalaDAO {

    @Override
    protected PreparedStatement comandoInsertar(Connection conn, Sala sala) throws SQLException {
        // Usamos CALL para ejecutar el procedimiento almacenado
        String sql = "{CALL sp_insertar_sala(?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setString(1, sala.getNombre());
        stmt.setInt(2, sala.getCapacidad());
        stmt.setString(3, sala.getTipoSala());
        return stmt;
    }

    @Override
    protected PreparedStatement comandoModificar(Connection conn, Sala sala) throws SQLException {
        String sql = "{CALL sp_actualizar_sala(?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, sala.getId());
        stmt.setString(2, sala.getNombre());
        stmt.setInt(3, sala.getCapacidad());
        stmt.setString(4, sala.getTipoSala());
        return stmt;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL sp_eliminar_sala(?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        return stmt;
    }

    @Override
    protected PreparedStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL sp_buscar_sala(?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        return stmt;
    }

    @Override
    protected PreparedStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL sp_listar_salas()}";
        return conn.prepareCall(sql);
    }

    @Override
    protected Sala mapearModelo(ResultSet rs) throws SQLException {
        Sala sala = new Sala();
        sala.setId(rs.getInt("sala_id"));
        sala.setNombre(rs.getString("nombre"));
        sala.setCapacidad(rs.getInt("capacidad"));
        sala.setTipoSala(rs.getString("tipo_sala"));
        return sala;
    }
}