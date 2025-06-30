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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import pe.com.cinepucp.autoservicio.config.DBManager;

public class LogSistemaDAOImpl extends BaseDAOImpl<LogSistema> implements ILogSistemaDAO {
    
    @Override
    protected PreparedStatement comandoInsertar(Connection conn, LogSistema modelo) throws SQLException {
        String sql = "{CALL sp_insertar_log_sistema(?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, modelo.getUsuario());
        stmt.setString(2, modelo.getAccion());
        
        return stmt;
    }

    @Override
    protected PreparedStatement comandoModificar(Connection conn, LogSistema modelo) throws SQLException {
        String sql = "{CALL sp_actualizar_log_sistema(?, ?, ?, ? )}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, modelo.getId());
        stmt.setInt(2, modelo.getUsuario());
        stmt.setString(3, modelo.getAccion());
        stmt.setTimestamp(4, Timestamp.valueOf(modelo.getFecha()));
        
        return stmt;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, int id,int id_mod) throws SQLException {
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
        
        
        log.setFecha(rs.getTimestamp("fecha_hora").toLocalDateTime());
        log.setUsuario(rs.getInt("usuario_id"));
        
        return log;

    }
    protected CallableStatement comandolistaReporte(Connection conn, String fechaini, String fechafin) throws SQLException{
        String sql = "{CALL sp_listarReporteLogs(?, ?)}";
        LocalDate fechai = LocalDate.parse(fechaini);
        LocalDate fechaf = LocalDate.parse(fechafin);

// Convertir a LocalDateTime con hora cero
        LocalDateTime fecha1 = fechai.atStartOfDay();
        LocalDateTime fecha2 = fechaf.atStartOfDay();
        
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setTimestamp(1, Timestamp.valueOf(fecha1));
        cmd.setTimestamp(2, Timestamp.valueOf(fecha2));
        return cmd;
    }
    
    @Override
    public List<LogSistema> listarReporteLogs(String fechaini, String fechafin) {
        try (
            Connection conn = DBManager.getInstance().getConnection();
            CallableStatement cmd = this.comandolistaReporte(conn, fechaini,fechafin);
        ) {
            ResultSet rs = cmd.executeQuery();
            LogSistemaDAOImpl logDAO =new LogSistemaDAOImpl();
            List<LogSistema> logs = new ArrayList<>();
            while (rs.next()) {
                logs.add(logDAO.mapearModelo(rs));
            }
            return logs;
        }
        catch (SQLException e) {
            System.err.println("Error SQL durante el listado: " + e.getMessage());
            throw new RuntimeException("No se pudo listar el registro.", e);
        }
        catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al listar los registros.", e);
        } 
    }

}
