/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.mysql;
import java.sql.*;
import pe.com.cinepucp.autoservicio.dao.IFuncionDAO;
import pe.com.cinepucp.autoservicio.model.peliculas.Funcion;
import pe.com.cinepucp.autoservicio.model.salas.Sala;
/**
 *
 * @author Piero
 */
public class FuncionDAOImpl extends BaseDAOImpl<Funcion> implements IFuncionDAO{
    
    @Override
    protected PreparedStatement comandoInsertar(Connection conn, Funcion funcion) throws SQLException {
        String sql = "{CALL sp_insertar_funcion(?, ?, ?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, funcion.getSala().getId());
        stmt.setTimestamp(2, Timestamp.valueOf(funcion.getFechaHora()));
        stmt.setString(3, funcion.getFormatoProyeccion());
        stmt.setString(4, funcion.getIdioma());
        stmt.setBoolean(5, funcion.isSubtitulos());
//        stmt.setInt(6, funcion.getPelicula().getId());
        return stmt;
    }
    
    @Override
    protected PreparedStatement comandoModificar(Connection conn, Funcion funcion) throws SQLException {
        String sql = "{CALL sp_actualizar_funcion(?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, funcion.getId());
        stmt.setInt(2, funcion.getSala().getId());
        stmt.setTimestamp(3, Timestamp.valueOf(funcion.getFechaHora()));
        stmt.setString(4, funcion.getFormatoProyeccion());
        stmt.setString(5, funcion.getIdioma());
        stmt.setBoolean(6, funcion.isSubtitulos());
//        stmt.setInt(7, funcion.getPelicula().getId());
        return stmt;
    }
    
    @Override
    protected PreparedStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL sp_eliminar_funcion(?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        return stmt;
    }
    
    @Override
    protected PreparedStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL sp_buscar_funcion(?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        return stmt;
    }
    
    @Override
    protected PreparedStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL sp_listar_funciones()}";
        return conn.prepareCall(sql);
    }
    
    @Override
    protected Funcion mapearModelo(ResultSet rs) throws SQLException {
        Funcion funcion = new Funcion();
        funcion.setId(rs.getInt("funcion_id"));
        
        // Mapear la sala asociada
        Sala sala = new Sala();
        sala.setId(rs.getInt("sala_id"));
        sala.setNombre(rs.getString("sala_nombre"));
        funcion.setSala(sala);
        
        // Mapear la película asociada
//        Pelicula pelicula = new Pelicula();
//        pelicula.setId(rs.getInt("pelicula_id"));
//        funcion.setPelicula(pelicula);
        
        funcion.setFechaHora(rs.getTimestamp("fechaHora").toLocalDateTime());
        funcion.setFormatoProyeccion(rs.getString("formatoProyeccion"));
        funcion.setIdioma(rs.getString("idioma"));
        funcion.setSubtitulos(rs.getBoolean("subtitulos"));
        
        return funcion;
    }
    
}
