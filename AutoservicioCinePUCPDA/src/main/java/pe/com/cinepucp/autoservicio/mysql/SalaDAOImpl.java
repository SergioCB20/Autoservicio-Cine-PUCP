/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.mysql;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import pe.com.cinepucp.autoservicio.config.DBManager;
import pe.com.cinepucp.autoservicio.dao.ISalaDAO;
import pe.com.cinepucp.autoservicio.model.salas.Asiento;
import pe.com.cinepucp.autoservicio.model.salas.Sala;
import pe.com.cinepucp.autoservicio.model.salas.TipoSala;

/**
 *
 * @author Sergio
 */

public class SalaDAOImpl extends BaseDAOImpl<Sala> implements ISalaDAO {

    private final int usuarioModificacionId = 4; // TODO: Obtener el ID del usuario de la sesión

    @Override
    protected PreparedStatement comandoInsertar(Connection conn, Sala sala) throws SQLException {
        String sql = "{CALL sp_insertar_sala(?, ?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setString(1, sala.getNombre());
        stmt.setInt(2, sala.getCapacidad());
        stmt.setString(3, sala.getTipoSala().getDescripcion());
        stmt.setBoolean(4, sala.isActiva());
        stmt.setInt(5, usuarioModificacionId); // Usar el ID del usuario de la sesión
        return stmt;
    }

    @Override
    protected PreparedStatement comandoModificar(Connection conn, Sala sala) throws SQLException {
        String sql = "{CALL sp_actualizar_sala(?, ?, ?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, sala.getId());
        stmt.setString(2, sala.getNombre());
        stmt.setInt(3, sala.getCapacidad());
        stmt.setString(4, sala.getTipoSala().getDescripcion());
        stmt.setBoolean(5, sala.isActiva());
        stmt.setInt(6, usuarioModificacionId); // Usar el ID del usuario de la sesión
        return stmt;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL sp_eliminar_sala(?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        stmt.setInt(2, usuarioModificacionId); // Usar el ID del usuario de la sesión
        return stmt;
    }

    @Override
    protected PreparedStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL sp_leer_sala(?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        return stmt;
    }

    @Override
    protected PreparedStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL sp_listar_salas(FALSE)}"; // Listar todas las salas (incluidas las no activas por defecto)
        return conn.prepareCall(sql);
    }

    @Override
    protected Sala mapearModelo(ResultSet rs) throws SQLException {
        Sala sala = new Sala();
        sala.setId(rs.getInt("sala_id"));
        sala.setNombre(rs.getString("nombre"));
        sala.setCapacidad(rs.getInt("capacidad"));
        sala.setTipoSala(TipoSala.fromString(rs.getString("tipo_sala")));
        sala.setActiva(rs.getBoolean("esta_activa"));
        sala.setFechaModificacion(rs.getTimestamp("fecha_modificacion"));
        // No mapeamos usuario_modificacion en el modelo por ahora
        return sala;
    }
    
}