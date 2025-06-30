package pe.com.cinepucp.autoservicio.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pe.com.cinepucp.autoservicio.dao.IVentaCuponDAO;
import pe.com.cinepucp.autoservicio.model.venta.Cupon;
import pe.com.cinepucp.autoservicio.model.venta.Venta;
import pe.com.cinepucp.autoservicio.model.venta.VentaCupon;

public class VentaCuponDAOImpl extends BaseDAOImpl<VentaCupon> implements IVentaCuponDAO{

    @Override
    protected PreparedStatement comandoInsertar(Connection conn, VentaCupon modelo) throws SQLException {
        String sql = "{CALL sp_insertar_venta_cupon(?, ?, ?)}"; //3 campos
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, modelo.getVenta().getVentaId());
        stmt.setInt(2, modelo.getCupon().getCuponId());
        stmt.setDouble(3, modelo.getDescuentoAplicado());
        return stmt;
    }

    @Override
    protected PreparedStatement comandoModificar(Connection conn, VentaCupon modelo) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, int id,int id_mod) throws SQLException {
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
    protected VentaCupon mapearModelo(ResultSet rs) throws SQLException {
        VentaCupon ventaCupon = new VentaCupon();
        
        Venta venta = new Venta();
        venta.setVentaId(rs.getInt("venta_id"));
        ventaCupon.setVenta(venta);
        
        Cupon cupon = new Cupon();
        cupon.setCuponId(rs.getInt("cupon_id"));
        ventaCupon.setCupon(cupon);
        
        ventaCupon.setDescuentoAplicado(rs.getDouble("descuento_aplicado"));
        
        return ventaCupon;
    }
}