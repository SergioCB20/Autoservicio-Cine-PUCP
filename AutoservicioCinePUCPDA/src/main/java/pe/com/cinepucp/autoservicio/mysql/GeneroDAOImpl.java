package pe.com.cinepucp.autoservicio.main.mysql;

import java.sql.*;
import java.util.List;
import pe.com.cinepucp.autoservicio.dao.IGeneroDAO;
import pe.com.cinepucp.autoservicio.model.Peliculas.Genero;
import pe.com.cinepucp.autoservicio.mysql.BaseDAOImpl;

/**
 *
 * @author Computer
 */
public class GeneroDAOImpl extends BaseDAOImpl<Genero> implements IGeneroDAO{

    @Override
    protected PreparedStatement comandoInsertar(Connection conn, Genero genero) throws SQLException {
        String sql = "{CALL sp_insertar_genero(?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setString(1,genero.getNombreEs());
        stmt.setString(2, genero.getNombreEn());

        return stmt;
    }

    @Override
    protected PreparedStatement comandoModificar(Connection conn, Genero genero) throws SQLException {
        String sql = "{CALL sp_actualizar_genero(?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, genero.getId());
        stmt.setString(2, genero.getNombreEs());
        stmt.setString(3, genero.getNombreEn());

        return stmt;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL sp_eliminar_genero(?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        return stmt;
    }

    @Override
    protected PreparedStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL sp_buscar_genero(?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        return stmt;
    }

    @Override
    protected PreparedStatement comandoListar(Connection conn) throws SQLException {
         String sql = "{CALL sp_listar_generos()}";
        return conn.prepareCall(sql);
    }

    @Override
    protected Genero mapearModelo(ResultSet rs) throws SQLException {
        Genero genero = new Genero();
        genero.setId(rs.getInt("genero_id"));
        
        genero.setNombreEs(rs.getString("nombre_es"));
        genero.setNombreEn(rs.getString("nombre_en"));
        return genero;
    }

    
    
}

