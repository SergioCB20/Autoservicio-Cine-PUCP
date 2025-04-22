package pe.com.cinepucp.autoservicio.mysql;
import java.sql.*;
import java.time.LocalDate;
import pe.com.cinepucp.autoservicio.model.auth.CodigoVerificacion;
import pe.com.cinepucp.autoservicio.dao.ICodigoVerificacionDAO;
/**
 *
 * @author Miguel
 */
public class CodigoVerificacionDAOImpl extends BaseDAOImpl<CodigoVerificacion> implements ICodigoVerificacionDAO{
    
    @Override
    protected PreparedStatement comandoInsertar(Connection conn, CodigoVerificacion codigoVerificacion) throws SQLException {
        String sql = "{ CALL sp_insertar_codigoVerificacion(?, ?, ?, ?, ?) }";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setString(1, codigoVerificacion.getTelefono());
        stmt.setString(2, codigoVerificacion.getCodigo());
        stmt.setDate(3, Date.valueOf(codigoVerificacion.getFechaCreacion()));
        stmt.setDate(4, Date.valueOf(codigoVerificacion.getFechaExpiracion()));
        stmt.setBoolean(5, codigoVerificacion.isUsado());
        return stmt;
    }
    
    @Override
    protected PreparedStatement comandoModificar(Connection conn, CodigoVerificacion codigoVerificacion) throws SQLException {
        String sql = "{ CALL sp_modificar_codigoVerificacion(?, ?, ?, ?, ?, ?) }";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, codigoVerificacion.getId());
        stmt.setString(2, codigoVerificacion.getTelefono());
        stmt.setString(3, codigoVerificacion.getCodigo());
        stmt.setDate(4, Date.valueOf(codigoVerificacion.getFechaCreacion()));
        stmt.setDate(5, Date.valueOf(codigoVerificacion.getFechaExpiracion()));
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
        stmt.setInt(1, idCodigoVerificacion); // Parameter for the ID to search
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
        // Assuming the columns in the ResultSet match the CodigoVerificacion properties
        codigoVerificacion.setId(rs.getInt("id"));
        codigoVerificacion.setTelefono(rs.getString("telefono"));
        codigoVerificacion.setCodigo(rs.getString("codigo"));

        // Retrieve Date from ResultSet and convert to LocalDate
        Date fechaCreacionSql = rs.getDate("fechaCreacion");
        if (fechaCreacionSql != null) {
            codigoVerificacion.setFechaCreacion(fechaCreacionSql.toLocalDate());
        }

        Date fechaExpiracionSql = rs.getDate("fechaExpiracion");
        if (fechaExpiracionSql != null) {
            codigoVerificacion.setFechaExpiracion(fechaExpiracionSql.toLocalDate());
        }

        codigoVerificacion.setUsado(rs.getBoolean("usado"));

        // If you had a Usuario object, you would map it here too based on its columns
        // codigoVerificacion.setUsuario(...);

        return codigoVerificacion;
    }
}
