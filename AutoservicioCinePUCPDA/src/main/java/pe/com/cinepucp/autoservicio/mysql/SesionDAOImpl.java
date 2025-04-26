package pe.com.cinepucp.autoservicio.mysql;
import java.sql.*;
import pe.com.cinepucp.autoservicio.model.auth.Sesion;
import pe.com.cinepucp.autoservicio.dao.ISesionDAO;
import pe.com.cinepucp.autoservicio.model.auth.TipoSesion;
/**
 *
 * @author Miguel
 */
public class SesionDAOImpl extends BaseDAOImpl<Sesion> implements ISesionDAO{
    
    @Override
    protected PreparedStatement comandoInsertar(Connection conn, Sesion sesion) throws SQLException{
        String sql= "{CALL sp_insertar_sesion(?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setString(1, sesion.getToken());
        stmt.setString(2, sesion.getMetodoLogin().getDescripcion());
        stmt.setDate(3, Date.valueOf(sesion.getFechaInicio()));
        stmt.setDate(4, Date.valueOf(sesion.getFechaExpiracion()));
        return stmt;
    }
    
    @Override
    protected PreparedStatement comandoModificar(Connection conn, Sesion sesion) throws SQLException {
        String sql = "{ CALL sp_modificar_sesion(?, ?, ?, ?, ?) }";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, sesion.getId());
        stmt.setString(2, sesion.getToken());
        stmt.setString(3, sesion.getMetodoLogin().getDescripcion());
        stmt.setDate(4, Date.valueOf(sesion.getFechaInicio()));
        stmt.setDate(5, Date.valueOf(sesion.getFechaExpiracion()));
        return stmt;
    }
    
    @Override
    protected PreparedStatement comandoEliminar(Connection conn, int idSesion) throws SQLException {
        String sql = "{ CALL sp_eliminar_sesion(?) }";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, idSesion);
        return stmt;
    }
    
    @Override
    protected PreparedStatement comandoBuscar(Connection conn, int idSesion) throws SQLException {
        String sql = "{ CALL sp_buscar_sesion(?) }";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, idSesion);
        return stmt;
    }

    @Override
    protected PreparedStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{ CALL sp_listar_sesiones() }";
        CallableStatement stmt = conn.prepareCall(sql);
        return stmt;
    }
    
    @Override
    protected Sesion mapearModelo(ResultSet rs) throws SQLException {
        Sesion sesion = new Sesion();
        // Assuming the columns in the ResultSet match the Sesion properties
        sesion.setId(rs.getInt("sesion_id"));
        sesion.setToken(rs.getString("token"));
        sesion.setMetodoLogin(TipoSesion.fromString(rs.getString("metodo_login")));

        Date fechaInicioSql = rs.getDate("fecha_inicio");
        if (fechaInicioSql != null) {
            sesion.setFechaInicio(fechaInicioSql.toLocalDate());
        }

        Date fechaExpiracionSql = rs.getDate("fecha_expiracion");
        if (fechaExpiracionSql != null) {
            sesion.setFechaExpiracion(fechaExpiracionSql.toLocalDate());
        }
        return sesion;
    }
    
}
