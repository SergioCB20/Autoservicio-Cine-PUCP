/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.mysql;
import java.sql.*;
import pe.com.cinepucp.autoservicio.dao.IAsientoDAO;
import pe.com.cinepucp.autoservicio.model.salas.Asiento;
import pe.com.cinepucp.autoservicio.model.salas.Sala;
import pe.com.cinepucp.autoservicio.model.salas.TipoAsiento;

/**
 *
 * @author Sergio
 */

public class AsientoDAOImpl extends BaseDAOImpl<Asiento> implements IAsientoDAO {

    private static final int USUARIO_MODIFICACION_ID = 15; // ID de usuario constante

    @Override
    protected PreparedStatement comandoInsertar(Connection conn, Asiento asiento) throws SQLException {
        String sql = "{CALL sp_insertar_asiento(?, ?, ?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, asiento.getSala().getId());
        stmt.setString(2, String.valueOf(asiento.getFila()));
        stmt.setInt(3, asiento.getNumero());
        stmt.setString(4, asiento.getTipo().getDescripcion());
        stmt.setBoolean(5, asiento.isActivo());
        stmt.setInt(6, USUARIO_MODIFICACION_ID);
        return stmt;
    }

    @Override
    protected PreparedStatement comandoModificar(Connection conn, Asiento asiento) throws SQLException {
        String sql = "{CALL sp_actualizar_asiento(?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, asiento.getId());
        stmt.setInt(2, asiento.getSala().getId());
        stmt.setString(3, String.valueOf(asiento.getFila()));
        stmt.setInt(4, asiento.getNumero());
        stmt.setString(5, asiento.getTipo().getDescripcion());
        stmt.setBoolean(6, asiento.isActivo());
        stmt.setInt(7, USUARIO_MODIFICACION_ID);
        return stmt;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL sp_eliminar_asiento(?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        stmt.setInt(2, USUARIO_MODIFICACION_ID);
        return stmt;
    }

    @Override
    protected PreparedStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL sp_leer_asiento(?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        return stmt;
    }

    @Override
    protected PreparedStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL sp_listar_asientos()}";
        CallableStatement stmt = conn.prepareCall(sql);
        return stmt;
    }

    @Override
    protected Asiento mapearModelo(ResultSet rs) throws SQLException {
        Asiento asiento = new Asiento();
        asiento.setId(rs.getInt("asiento_id"));

        Sala sala = new Sala();
        sala.setId(rs.getInt("sala_id"));
        asiento.setSala(sala);

        asiento.setFila(rs.getString("fila").charAt(0));
        asiento.setNumero(rs.getInt("numero"));
        asiento.setTipo(TipoAsiento.fromString(rs.getString("tipo")));
        asiento.setActivo(rs.getBoolean("esta_activo"));

        return asiento;
    }
}