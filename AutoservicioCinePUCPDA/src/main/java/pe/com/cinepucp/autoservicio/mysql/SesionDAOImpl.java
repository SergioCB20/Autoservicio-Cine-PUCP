package pe.com.cinepucp.autoservicio.mysql;
import java.sql.*;
import pe.com.cinepucp.autoservicio.model.auth.Sesion;
import pe.com.cinepucp.autoservicio.dao.ISesionDAO;
import pe.com.cinepucp.autoservicio.dao.IUsuarioDAO;
import pe.com.cinepucp.autoservicio.main.cruds.UsuarioCRUD;
import pe.com.cinepucp.autoservicio.model.auth.TipoSesion;
import pe.com.cinepucp.autoservicio.model.auth.Usuario;
/**
 *
 * @author Miguel
 */
public class SesionDAOImpl extends BaseDAOImpl<Sesion> implements ISesionDAO{
    
    @Override
    protected PreparedStatement comandoInsertar(Connection conn, Sesion sesion) throws SQLException{
        String sql= "{CALL sp_insertar_sesion(?, ?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, sesion.getUsuario().getId());
        stmt.setString(2, sesion.getToken());
        stmt.setString(3, sesion.getMetodoLogin().getDescripcion());
        stmt.setDate(4, Date.valueOf(sesion.getFechaInicio()));
        stmt.setDate(5, Date.valueOf(sesion.getFechaExpiracion()));
        return stmt;
    }
    
    @Override
    protected PreparedStatement comandoModificar(Connection conn, Sesion sesion) throws SQLException {
        String sql = "{ CALL sp_actualizar_sesion(?, ?, ?, ?, ?, ?) }";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, sesion.getId());
        stmt.setInt(2, sesion.getUsuario().getId());
        stmt.setString(3, sesion.getToken());
        stmt.setString(4, sesion.getMetodoLogin().getDescripcion());
        stmt.setDate(5, Date.valueOf(sesion.getFechaInicio()));
        stmt.setDate(6, Date.valueOf(sesion.getFechaExpiracion()));
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
        sesion.setId(rs.getInt("sesion_id"));
        //usuario
        IUsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        Usuario usuario = usuarioDAO.buscar(rs.getInt("usuario_id"));
        sesion.setUsuario(usuario);
        //usuario
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
