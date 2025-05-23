/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.mysql;

import pe.com.cinepucp.autoservicio.dao.ILogSistemaDAO;
import pe.com.cinepucp.autoservicio.model.auth.LogSistema;

/**
 *
 * @author Sergio
 */
import java.sql.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import pe.com.cinepucp.autoservicio.model.auth.Usuario;

public class LogSistemaDAOImpl extends BaseDAOImpl<LogSistema> implements ILogSistemaDAO {

    private static final String DELIMITADOR_TABLAS = " - ";
    
    @Override
    protected PreparedStatement comandoInsertar(Connection conn, LogSistema modelo) throws SQLException {
        String sql = "{CALL sp_insertar_log_sistema(?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, modelo.getUsuario().getId());
        stmt.setString(2, modelo.getAccion());
        stmt.setDate(3, Date.valueOf(modelo.getFecha()));
        
        return stmt;
    }

    @Override
    protected PreparedStatement comandoModificar(Connection conn, LogSistema modelo) throws SQLException {
        String sql = "{CALL sp_actualizar_log_sistema(?, ?, ?, ? )}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, modelo.getId());
        stmt.setInt(2, modelo.getUsuario().getId());
        stmt.setString(3, modelo.getAccion());
        stmt.setDate(4, Date.valueOf(modelo.getFecha()));
        
        return stmt;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL sp_eliminar_log_sistema(?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        return stmt;
    }

    @Override
    protected PreparedStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL sp_buscar_log_sistema(?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        return stmt;
    }

    @Override
    protected PreparedStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL sp_listar_log_sistema()}";
        return conn.prepareCall(sql);
    }

    @Override
    protected LogSistema mapearModelo(ResultSet rs) throws SQLException {
        LogSistema log = new LogSistema();
        log.setId(rs.getInt("log_id"));
        log.setAccion(rs.getString("accion"));
        
        
        log.setFecha(rs.getDate("fecha_hora").toLocalDate());
        
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("usuario_id"));
        log.setUsuario(usuario);
        
        return log;
    }

    // Métodos auxiliares para conversión entre List<String> y VARCHAR
    private String convertirListaAString(List<String> lista) {
        if (lista == null || lista.isEmpty()) {
            return "";
        }
        return String.join(DELIMITADOR_TABLAS, lista);
    }

    private List<String> convertirStringALista(String str) {
        if (str == null || str.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(str.split(DELIMITADOR_TABLAS)));
    }
}
