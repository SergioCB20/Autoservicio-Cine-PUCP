package pe.com.cinepucp.autoservicio.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pe.com.cinepucp.autoservicio.config.DBManager;

import pe.com.cinepucp.autoservicio.dao.IVentaDAO;
import pe.com.cinepucp.autoservicio.model.auth.Usuario;
import pe.com.cinepucp.autoservicio.model.salas.Asiento;
import pe.com.cinepucp.autoservicio.model.venta.EstadoVenta;
import pe.com.cinepucp.autoservicio.model.venta.MetodoPago;
import pe.com.cinepucp.autoservicio.model.venta.Venta;
import pe.com.cinepucp.autoservicio.model.venta.*;

public class VentaDAOImpl extends BaseDAOImpl<Venta> implements IVentaDAO{

    @Override
    protected PreparedStatement comandoInsertar(Connection conn, Venta modelo) throws SQLException {
        String sql = "{CALL sp_insertar_venta(?, ?, ?, ?, ?, ?, ?)}"; //7
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, modelo.getUsuario().getId());
        stmt.setTimestamp(2, Timestamp.valueOf(modelo.getFechaHora()));
        stmt.setDouble(3, modelo.getSubtotal());
        stmt.setDouble(4, modelo.getImpuestos());
        stmt.setDouble(5, modelo.getTotal());
        stmt.setString(6, modelo.getEstado().getDescripcion()); // Enum a String
        stmt.setString(7, modelo.getMetodoPago().getDescripcion()); // Enum a String
        return stmt;
    }

    @Override
    protected PreparedStatement comandoModificar(Connection conn, Venta modelo) throws SQLException {
        String sql = "{CALL sp_actualizar_venta(?, ?, ?, ?, ?, ?, ?, ?)}"; //8
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, modelo.getVentaId());
        stmt.setInt(2, modelo.getUsuario().getId());
        stmt.setTimestamp(3,Timestamp.valueOf(modelo.getFechaHora()));
        stmt.setDouble(4, modelo.getSubtotal());
        stmt.setDouble(5, modelo.getImpuestos());
        stmt.setDouble(6, modelo.getTotal());
        stmt.setString(7, modelo.getEstado().getDescripcion());
        stmt.setString(8, modelo.getMetodoPago().getDescripcion());
        return stmt;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL sp_eliminar_venta(?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        return stmt;
    }

    @Override
    protected PreparedStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL sp_buscar_venta(?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, id);
        return stmt;
    }

    @Override
    protected PreparedStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL sp_listar_ventas()}";
        return conn.prepareCall(sql);
    }
    protected CallableStatement comandolistaVentaReporte(Connection conn,String fechaini,String fechafin) throws SQLException {
        String sql = "{CALL sp_listarVentasReporte(?, ?)}";
        LocalDate fechai = LocalDate.parse(fechaini);
        LocalDate fechaf = LocalDate.parse(fechaini);

// Convertir a LocalDateTime con hora cero
        LocalDateTime fecha1 = fechai.atStartOfDay();
        LocalDateTime fecha2 = fechaf.atStartOfDay();
        
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setTimestamp(1, Timestamp.valueOf(fecha1));
        cmd.setTimestamp(2, Timestamp.valueOf(fecha2));
        return cmd;
    }
    @Override
    public List<Venta> listarVentasRep(String fechaini,String fechafin){
        try (
            Connection conn = DBManager.getInstance().getConnection();
            CallableStatement cmd = this.comandolistaVentaReporte(conn, fechaini,fechafin);
        ) {
            ResultSet rs = cmd.executeQuery();
            VentaDAOImpl ventaDAO =new VentaDAOImpl();
            List<Venta> ventas = new ArrayList<>();
            while (rs.next()) {
                ventas.add(ventaDAO.mapearModelo(rs));
            }
            return ventas;
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
    @Override
    protected Venta mapearModelo(ResultSet rs) throws SQLException {
        Venta venta = new Venta();
        venta.setVentaId(rs.getInt("venta_id"));
        
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("usuario_id"));
        venta.setUsuario(usuario);
        
        Timestamp ts = rs.getTimestamp("fecha_hora");
        if(ts != null){
            venta.setFechaHora(ts.toLocalDateTime());
        }
        
        venta.setSubtotal(rs.getDouble("subtotal"));
        venta.setImpuestos(rs.getDouble("impuestos"));
        venta.setTotal(rs.getDouble("total"));
        venta.setEstado(EstadoVenta.fromString(rs.getString("estado")));
        venta.setMetodoPago(MetodoPago.fromString(rs.getString("metodo_pago")));
        return venta;
    }
    @Override
    public List<Venta> listarVentasPorUsuario(int usuario_id){
        List<Venta> ventas = new ArrayList<>();

        try (Connection conn = DBManager.getInstance().getConnection()) {
            String sql = "{CALL sp_listar_ventas_por_usuario(?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, usuario_id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Venta venta = mapearModelo(rs);
                ventas.add(venta);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.err.println("Error SQL al buscar ventas por usuario: " + e.getMessage());
            throw new RuntimeException("No se pudieron recuperar las ventas con ID de usuario: " + usuario_id, e);
        } catch (Exception e) {
            System.err.println("Error inesperado al buscar ventas por usuario: " + e.getMessage());
            throw new RuntimeException("Error inesperado al buscar ventas por usuario.", e);
        }

        return ventas;
    }
}
