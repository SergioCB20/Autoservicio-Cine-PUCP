package pe.com.cinepucp.autoservicio.mysql;

import java.sql.*;
import pe.com.cinepucp.autoservicio.dao.IUsuarioDAO;
import pe.com.cinepucp.autoservicio.model.auth.Usuario;

public class UsuarioDAOImpl extends BaseDAOImpl<Usuario> implements IUsuarioDAO {

    @Override
    protected PreparedStatement comandoInsertar(Connection conn, Usuario usuario) throws SQLException {
        String sql = "{ CALL sp_insertar_usuario(?, ?, ?, ?, ?, ?, ?) }";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setString(1, usuario.getNombre());
        stmt.setString(2, usuario.getEmail());
        stmt.setInt(3, usuario.getTelefono());
        stmt.setString(4, usuario.getPassword());
        stmt.setDate(5, Date.valueOf(usuario.getFechaRegistro()));
        stmt.setBoolean(6, usuario.isEstaActivo());
        stmt.setString(7, usuario.getIdiomaPreferido());
        return stmt;
    }

    @Override
    protected PreparedStatement comandoModificar(Connection conn, Usuario usuario) throws SQLException {
        String sql = "{ CALL sp_actualizar_usuario(?, ?, ?, ?, ?, ?, ?) }";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, usuario.getId());
        stmt.setString(2, usuario.getNombre());
        stmt.setString(3, usuario.getEmail());
        stmt.setInt(4, usuario.getTelefono());
        stmt.setString(5, usuario.getPassword());
        stmt.setBoolean(6, usuario.isEstaActivo());
        stmt.setString(7, usuario.getIdiomaPreferido());
        return stmt;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{ CALL sp_eliminar_usuario(?) }";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        return stmt;
    }

    @Override
    protected PreparedStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{ CALL sp_buscar_usuario(?) }";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        return stmt;
    }

    @Override
    protected PreparedStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{ CALL sp_listar_usuarios() }";
        return conn.prepareCall(sql);
    }

    @Override
    protected Usuario mapearModelo(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("usuario_id"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setEmail(rs.getString("email"));
        usuario.setTelefono(rs.getInt("telefono"));
        usuario.setPassword(rs.getString("password"));

        Date fechaRegistro = rs.getDate("fecha_registro");
        if (fechaRegistro != null) {
            usuario.setFechaRegistro(fechaRegistro.toLocalDate());
        }

        usuario.setEstaActivo(rs.getBoolean("esta_activo"));
        usuario.setIdiomaPreferido(rs.getString("idioma_preferido"));
        return usuario;
    }
}
