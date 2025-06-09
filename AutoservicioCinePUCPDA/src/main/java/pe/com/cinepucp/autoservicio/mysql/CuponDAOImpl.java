package pe.com.cinepucp.autoservicio.mysql;

import java.sql.*;
import java.time.LocalDateTime;
import pe.com.cinepucp.autoservicio.dao.ICuponDAO;
import pe.com.cinepucp.autoservicio.model.auth.Usuario;
import pe.com.cinepucp.autoservicio.model.venta.Cupon;
import pe.com.cinepucp.autoservicio.model.venta.TipoDescuento;

public class CuponDAOImpl extends BaseDAOImpl<Cupon> implements ICuponDAO{
protected CallableStatement comandoInsertar(Connection conn, Cupon modelo) throws SQLException {
        String sql = "{CALL sp_insertar_cupon(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setString(1, modelo.getCodigo());
        stmt.setString(2, modelo.getDescripcionEs());
        stmt.setString(3, modelo.getDescripcionEn());
        stmt.setString(4, modelo.getDescuentoTipo().name()); // Enum en DB
        stmt.setDouble(5, modelo.getDescuentoValor());
        stmt.setDate(6, Date.valueOf(modelo.getFechaInicio()));
        stmt.setDate(7, Date.valueOf(modelo.getFechaFin()));
        stmt.setObject(8, modelo.getMaxUsos(), Types.INTEGER); // Puede ser null
        stmt.setInt(9, modelo.getUsosActuales());
        stmt.setInt(10, modelo.getCreadoPor().getId());
        stmt.setBoolean(11, true); // esta_activo por defecto al insertar
        stmt.setTimestamp(12, Timestamp.valueOf(LocalDateTime.now().now())); // fecha_modificacion
        stmt.setInt(13, modelo.getModificadoPor().getId()); // usuario_modificacion (asumiendo mismo creador)
        return stmt;
    }

    @Override
    protected PreparedStatement comandoModificar(Connection conn, Cupon modelo) throws SQLException {
        String sql = "{CALL sp_actualizar_cupon(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}"; // 14
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, modelo.getCuponId());
        stmt.setString(2, modelo.getCodigo());
        stmt.setString(3, modelo.getDescripcionEs());
        stmt.setString(4, modelo.getDescripcionEn());
        stmt.setString(5, modelo.getDescuentoTipo().name());
        stmt.setDouble(6, modelo.getDescuentoValor());
        stmt.setDate(7, Date.valueOf(modelo.getFechaInicio()));
        stmt.setDate(8, Date.valueOf(modelo.getFechaFin()));
        stmt.setObject(9, modelo.getMaxUsos(), Types.INTEGER);
        stmt.setInt(10, modelo.getUsosActuales());
        stmt.setInt(11, modelo.getCreadoPor().getId());
        stmt.setBoolean(12, true); // esta_activo
        stmt.setTimestamp(13, Timestamp.valueOf(LocalDateTime.now().now())); // fecha_modificacion
        stmt.setInt(14, modelo.getModificadoPor().getId()); // usuario_modificacion
        return stmt;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL sp_eliminar_cupon(?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        return stmt;
    }

    @Override
    protected PreparedStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL sp_buscar_cupon(?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        return stmt;
    }

    @Override
    protected PreparedStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL sp_listar_cupones ()}";
        return conn.prepareCall(sql);
    }

    @Override
    protected Cupon mapearModelo(ResultSet rs) throws SQLException {
        Cupon cupon = new Cupon();
        cupon.setCuponId(rs.getInt("cupon_id"));
        cupon.setCodigo(rs.getString("codigo"));
        cupon.setDescripcionEs(rs.getString("descripcion_es"));
        cupon.setDescripcionEn(rs.getString("descripcion_en"));
        cupon.setDescuentoTipo(TipoDescuento.fromString(rs.getString("descuento_tipo")));
        cupon.setDescuentoValor(rs.getDouble("descuento_valor"));
        cupon.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
        cupon.setFechaFin(rs.getDate("fecha_fin").toLocalDate());
        cupon.setMaxUsos(rs.getObject("max_usos", Integer.class));
        cupon.setUsosActuales(rs.getInt("usos_actuales"));

        Usuario creador = new Usuario();
        creador.setId(rs.getInt("creado_por"));
        cupon.setCreadoPor(creador);
        creador.setId(rs.getInt("usuario_modificacion"));
        cupon.setModificadoPor(creador);
        // Puedes mapear esta_activo si es necesario en el modelo
        return cupon;
    }
}
