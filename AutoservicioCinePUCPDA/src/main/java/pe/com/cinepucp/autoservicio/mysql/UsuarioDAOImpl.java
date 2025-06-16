package pe.com.cinepucp.autoservicio.mysql;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.com.cinepucp.autoservicio.config.DBManager;
import pe.com.cinepucp.autoservicio.dao.IUsuarioDAO;
import pe.com.cinepucp.autoservicio.model.auth.Usuario;

public class UsuarioDAOImpl extends BaseDAOImpl<Usuario> implements IUsuarioDAO {

    @Override
    protected PreparedStatement comandoInsertar(Connection conn, Usuario usuario) throws SQLException {
        String sql = "{ CALL sp_insertar_usuario(?, ?, ?, ?, ?, ?, ?) }";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setString(1, usuario.getNombre());
        stmt.setString(2, usuario.getEmail());
        stmt.setString(3, usuario.getTelefono());
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
        stmt.setString(4, usuario.getTelefono());
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
        usuario.setTelefono(rs.getString("telefono"));
        usuario.setPassword(rs.getString("password_hash"));

        Date fechaRegistro = rs.getDate("fecha_registro");
        if (fechaRegistro != null) {
            usuario.setFechaRegistro(fechaRegistro.toLocalDate());
        }

        usuario.setEstaActivo(rs.getBoolean("esta_activo"));
        usuario.setIdiomaPreferido(rs.getString("idioma_preferido"));
        return usuario;
    }
    
    @Override
    public Usuario buscarPorEmail(String email){
        try(Connection conn = DBManager.getInstance().getConnection();) {
            String sql = "{ CALL sp_buscar_usuario_por_email(?) }";
            PreparedStatement cmd = conn.prepareCall(sql);
            cmd.setString(1, email);
            ResultSet rs = cmd.executeQuery();
             if (!rs.next()) {
                System.err.println("No se encontró el registro con email: " + email);
                return null;
            }
            return this.mapearModelo(rs);
        }catch (SQLException e) {
            System.err.println("Error SQL durante la inserción: " + e.getMessage());
            throw new RuntimeException("No se pudo insertar el registro.", e);
        }
        catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al insertar el registro.", e);
        }
    }
}
