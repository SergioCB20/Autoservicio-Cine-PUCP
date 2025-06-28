package pe.com.cinepucp.autoservicio.mysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import pe.com.cinepucp.autoservicio.config.DBManager;
import pe.com.cinepucp.autoservicio.dao.IFuncionDAO;
import pe.com.cinepucp.autoservicio.model.Peliculas.Funcion;
import pe.com.cinepucp.autoservicio.model.Peliculas.Pelicula;
import pe.com.cinepucp.autoservicio.model.salas.Sala;

/**
 *
 * @author Piero
 */
public class FuncionDAOImpl extends BaseDAOImpl<Funcion> implements IFuncionDAO {
//    private final int usuarioModificacionId = 1;

    @Override
    protected PreparedStatement comandoInsertar(Connection conn, Funcion funcion) throws SQLException {
        String sql = "{CALL sp_insertar_funcion(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);

        stmt.setInt(1, funcion.getPeliculaId());
        stmt.setInt(2, funcion.getSalaId());
        stmt.setTimestamp(3, Timestamp.valueOf(funcion.getFechaHora()));
        stmt.setString(4, funcion.getFormatoProyeccion());
        stmt.setString(5, funcion.getIdioma());
        stmt.setBoolean(6, funcion.getSubtitulos());
        stmt.setBoolean(7, funcion.getEstaActiva());
        stmt.setTimestamp(8, Timestamp.valueOf(funcion.getFechaModificacion()));
        stmt.setInt(9, funcion.getUsuarioModificacion());
        return stmt;
    }

    @Override
    protected PreparedStatement comandoModificar(Connection conn, Funcion funcion) throws SQLException {
        String sql = "{CALL sp_actualizar_funcion(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, funcion.getFuncionId());
        stmt.setInt(2, funcion.getPeliculaId());
        stmt.setInt(3, funcion.getSalaId());
        stmt.setTimestamp(4, Timestamp.valueOf(funcion.getFechaHora()));
        stmt.setString(5, funcion.getFormatoProyeccion());
        stmt.setString(6, funcion.getIdioma());
        stmt.setBoolean(7, funcion.getSubtitulos());
        stmt.setBoolean(8, funcion.getEstaActiva());
        stmt.setInt(9, funcion.getUsuarioModificacion());
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
        String sql = "{CALL sp_listar_funciones(FALSE)}";
        return conn.prepareCall(sql);
    }

    @Override
    protected Funcion mapearModelo(ResultSet rs) throws SQLException {
        Funcion funcion = new Funcion();
        funcion.setFuncionId(rs.getInt("funcion_id"));

        funcion.setSalaId(rs.getInt("sala_id"));
        funcion.setPeliculaId(rs.getInt("pelicula_id"));

        funcion.setFechaHora(rs.getTimestamp("fecha_Hora").toLocalDateTime());
        funcion.setFormatoProyeccion(rs.getString("formato_proyeccion"));
        funcion.setIdioma(rs.getString("idioma"));
        funcion.setSubtitulos(rs.getBoolean("subtitulos"));

        return funcion;
    }
    
    @Override
    public List<Funcion> buscarPorPelicula(int peliculaId) {
        List<Funcion> funciones = new ArrayList<>();

        try (Connection conn = DBManager.getInstance().getConnection()) {
            String sql = "{CALL sp_listar_funciones_por_peliculas(?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, peliculaId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcion funcion = mapearModelo(rs);
                funciones.add(funcion);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.err.println("Error SQL al buscar funciones por película: " + e.getMessage());
            throw new RuntimeException("No se pudieron recuperar las funciones de la película con ID: " + peliculaId, e);
        } catch (Exception e) {
            System.err.println("Error inesperado al buscar funciones por película: " + e.getMessage());
            throw new RuntimeException("Error inesperado al buscar funciones por película.", e);
        }

        return funciones;
    }

}
