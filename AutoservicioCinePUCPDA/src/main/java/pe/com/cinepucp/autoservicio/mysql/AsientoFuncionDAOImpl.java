package pe.com.cinepucp.autoservicio.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.com.cinepucp.autoservicio.config.DBManager;
import pe.com.cinepucp.autoservicio.dao.IAsientoFuncionDAO;
import pe.com.cinepucp.autoservicio.model.Peliculas.Funcion;
import pe.com.cinepucp.autoservicio.model.salas.Asiento;
import pe.com.cinepucp.autoservicio.model.salas.AsientoFuncion;
import pe.com.cinepucp.autoservicio.model.salas.EstadoAsiento;

public class AsientoFuncionDAOImpl extends BaseDAOImpl<AsientoFuncion> implements IAsientoFuncionDAO {
    
    
    
    @Override
    protected PreparedStatement comandoInsertar(Connection conn, AsientoFuncion asiento) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement comandoModificar(Connection conn, AsientoFuncion asiento) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, int id) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement comandoBuscar(Connection conn, int id) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement comandoListar(Connection conn) throws SQLException {
        return null;
    }
    
    @Override
    public List<AsientoFuncion> listaAsientosFuncion(int funcionId) {
        List<AsientoFuncion> asientoFunciones = new ArrayList<>();

        try (Connection conn = DBManager.getInstance().getConnection()) {
            String sql = "{CALL sp_listar_asientos_por_funcion(?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, funcionId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                AsientoFuncion asientoFuncion = mapearModelo(rs);
                asientoFunciones.add(asientoFuncion);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.err.println("Error SQL al buscar asientos por función: " + e.getMessage());
            throw new RuntimeException("No se pudieron recuperar los asientos por función con ID: " + funcionId, e);
        } catch (Exception e) {
            System.err.println("Error inesperado al buscar asientos por función: " + e.getMessage());
            throw new RuntimeException("Error inesperado al buscar asientos por función.", e);
        }

        return asientoFunciones;
    }
    
    @Override
    protected AsientoFuncion mapearModelo(ResultSet rs) throws SQLException {
        AsientoFuncion asientoFuncion = new AsientoFuncion();
        asientoFuncion.setIdAsientoFuncion(rs.getInt("funcion_asiento_id"));

        Funcion funcion = new Funcion();
        funcion.setFuncionId(rs.getInt("funcion_id"));
        asientoFuncion.setFuncion(funcion);
        Asiento asiento = new Asiento();
        asiento.setId(rs.getInt("asiento_id"));
        asientoFuncion.setAsiento(asiento);

        asientoFuncion.setEstado(EstadoAsiento.fromString(rs.getString("estado")));

        return asientoFuncion;
    }
    
}
