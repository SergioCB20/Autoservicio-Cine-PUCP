package pe.com.cinepucp.autoservicio.mysql;

import java.sql.*;
import pe.com.cinepucp.autoservicio.dao.IPeliculaDAO;
import pe.com.cinepucp.autoservicio.model.Peliculas.Pelicula;

/**
 * Data Access Object (DAO) implementation for Pelicula entity,
 * extending BaseDAOImpl for common CRUD operations.
 */
public class PeliculaDAOImpl extends BaseDAOImpl<Pelicula> implements IPeliculaDAO {
    private final int usuarioModificacionId = 4;

    @Override
    protected PreparedStatement comandoInsertar(Connection conn, Pelicula peli) throws SQLException {
        // Usamos CALL para ejecutar el procedimiento almacenado
        String sql = "{CALL sp_insertar_pelicula(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setString(1, peli.getTituloEs());
        stmt.setString(2, peli.getTituloEn());
        stmt.setInt(3, peli.getDuracionMin());
        stmt.setString(4, peli.getClasificacion());
        stmt.setString(5, peli.getSinopsisEs());
        stmt.setString(6, peli.getSinopsisEn());
        stmt.setString(7, peli.getImagenUrl());
        stmt.setBoolean(8, peli.isEstaActiva());
        stmt.setInt(9, peli.getUsuarioModificacion());

        return stmt;
    }

    @Override
    protected PreparedStatement comandoModificar(Connection conn, Pelicula peli) throws SQLException {
        String sql = "{ CALL sp_actualizar_pelicula(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, peli.getPeliculaId());
        stmt.setString(2, peli.getTituloEs());
        stmt.setString(3, peli.getTituloEn());
        stmt.setInt(4, peli.getDuracionMin());
        stmt.setString(5, peli.getClasificacion());
        stmt.setString(6, peli.getSinopsisEs());
        stmt.setString(7, peli.getSinopsisEn());
        stmt.setString(8, peli.getImagenUrl());
        stmt.setBoolean(9, peli.isEstaActiva());
        stmt.setInt(10, peli.getUsuarioModificacion());
        return stmt;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL sp_eliminar_pelicula(?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        stmt.setInt(2, usuarioModificacionId);
        return stmt;
    }

    @Override
    protected PreparedStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL sp_buscar_pelicula(?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        return stmt;
    }

    @Override
    protected PreparedStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL sp_listar_peliculas(FALSE)}"; // Assuming FALSE means active or something
        return conn.prepareCall(sql);
    }

    @Override
    protected Pelicula mapearModelo(ResultSet rs) throws SQLException {
        Pelicula peli = new Pelicula();
        peli.setPeliculaId(rs.getInt("pelicula_id"));
        peli.setTituloEs(rs.getString("titulo_es"));
        peli.setTituloEn(rs.getString("titulo_en"));
        peli.setDuracionMin(rs.getInt("duracion_min"));
        peli.setClasificacion(rs.getString("clasificacion"));
        peli.setSinopsisEs(rs.getString("sinopsis_es"));
        peli.setSinopsisEn(rs.getString("sinopsis_en"));
        peli.setImagenUrl(rs.getString("imagen_url"));
        peli.setEstaActiva(rs.getBoolean("esta_activa"));
        peli.setFechaModificacion(rs.getTimestamp("fecha_modificacion")); 
        return peli;
    }
}