package pe.com.cinepucp.autoservicio.mysql;
import java.sql.*;
import java.time.LocalDateTime;
import pe.com.cinepucp.autoservicio.model.auth.CodigoVerificacion;
import pe.com.cinepucp.autoservicio.dao.ICodigoVerificacionDAO;
import pe.com.cinepucp.autoservicio.model.auth.Usuario;
/**
 *
 * @author Miguel
 */
public class CodigoVerificacionDAOImpl extends BaseDAOImpl<CodigoVerificacion> implements ICodigoVerificacionDAO{
    
    @Override
    protected PreparedStatement comandoInsertar(Connection conn, CodigoVerificacion codigoVerificacion) throws SQLException {
        String sql = "{ CALL sp_insertar_codigoVerificacion(?, ?, ?, ?, ?, ?) }";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setString(1, codigoVerificacion.getTelefono());
        stmt.setString(2, codigoVerificacion.getCodigo());
        stmt.setTimestamp(3, Timestamp.valueOf(codigoVerificacion.getFechaCreacion()));
        stmt.setTimestamp(4, Timestamp.valueOf(codigoVerificacion.getFechaExpiracion()));
        stmt.setBoolean(5, codigoVerificacion.isUsado());
        
         if (codigoVerificacion.getUsuario() != null) {
             stmt.setInt(6, codigoVerificacion.getUsuario().getId());
        } else {
             throw new SQLException("Usuario object no se encuentra in CodigoVerificacion for insertion.");
        }
        
        return stmt;
    }
    
    @Override
    protected PreparedStatement comandoModificar(Connection conn, CodigoVerificacion codigoVerificacion) throws SQLException {
        String sql = "{ CALL sp_actualizar_codigoVerificacion(?, ?, ?, ?, ?, ?) }";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, codigoVerificacion.getId());
        stmt.setString(2, codigoVerificacion.getTelefono());
        stmt.setString(3, codigoVerificacion.getCodigo());
        stmt.setTimestamp(4, Timestamp.valueOf(codigoVerificacion.getFechaCreacion()));
        stmt.setTimestamp(5, Timestamp.valueOf(codigoVerificacion.getFechaExpiracion()));
        stmt.setBoolean(6, codigoVerificacion.isUsado());
        return stmt;
    }
    
    @Override
    protected PreparedStatement comandoEliminar(Connection conn, int idCodigoVerificacion) throws SQLException {
        String sql = "{ CALL sp_eliminar_codigoVerificacion(?) }";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, idCodigoVerificacion); 
        return stmt;
    }
    
    @Override
    protected PreparedStatement comandoBuscar(Connection conn, int idCodigoVerificacion) throws SQLException {
        String sql = "{ CALL sp_buscar_codigoVerificacion(?) }";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, idCodigoVerificacion); 
        return stmt;
    }
    
    @Override
    protected PreparedStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{ CALL sp_listar_codigoVerificacion() }";
        CallableStatement stmt = conn.prepareCall(sql);
        return stmt;
    }
    
    protected CodigoVerificacion mapearModelo(ResultSet rs) throws SQLException {
        CodigoVerificacion codigoVerificacion = new CodigoVerificacion();
        
        codigoVerificacion.setId(rs.getInt("codigo_id"));
        codigoVerificacion.setTelefono(rs.getString("telefono"));
        codigoVerificacion.setCodigo(rs.getString("codigo"));

        Timestamp fechaCreacionSql = rs.getTimestamp("fecha_creacion");
        if (fechaCreacionSql != null) {
            codigoVerificacion.setFechaCreacion(fechaCreacionSql.toLocalDateTime());
        }

        Timestamp fechaExpiracionSql = rs.getTimestamp("fecha_expiracion");
        if (fechaExpiracionSql != null) {
            codigoVerificacion.setFechaExpiracion(fechaExpiracionSql.toLocalDateTime());
        }

        codigoVerificacion.setUsado(rs.getBoolean("usado"));
        int usuarioId = rs.getInt("usuario_id");
         Usuario dummyUser = new Usuario(); // Assuming default constructor exists
         dummyUser.setId(usuarioId);
         codigoVerificacion.setUsuario(dummyUser);
        
        return codigoVerificacion;
    }
}
