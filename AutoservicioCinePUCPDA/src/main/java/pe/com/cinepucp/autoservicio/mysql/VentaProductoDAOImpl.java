package pe.com.cinepucp.autoservicio.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pe.com.cinepucp.autoservicio.dao.IVentaProductoDAO;
import pe.com.cinepucp.autoservicio.model.comida.Producto;
import pe.com.cinepucp.autoservicio.model.venta.Venta;
import pe.com.cinepucp.autoservicio.model.venta.VentaProducto;

public class VentaProductoDAOImpl extends BaseDAOImpl<VentaProducto> implements IVentaProductoDAO{

    @Override
    protected PreparedStatement comandoInsertar(Connection conn, VentaProducto modelo) throws SQLException {
        String sql = "{CALL sp_insertar_venta_producto(?, ?, ?, ?)}"; //4 campos
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, modelo.getVenta().getVentaId());
        stmt.setInt(2, modelo.getProducto().getId());
        stmt.setInt(3,modelo.getCantidad());
        stmt.setDouble(4, modelo.getPrecioUnitario());
        return stmt;
    }

    @Override
    protected PreparedStatement comandoModificar(Connection conn, VentaProducto modelo) throws SQLException {
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
    protected VentaProducto mapearModelo(ResultSet rs) throws SQLException {
        VentaProducto ventaProducto = new VentaProducto();
        
        Venta venta = new Venta();
        venta.setVentaId(rs.getInt("venta_id"));
        ventaProducto.setVenta(venta);
        
        Producto producto = new Producto();
        producto.setId(rs.getInt("producto_id"));
        ventaProducto.setProducto(producto);
        
        ventaProducto.setCantidad(rs.getInt("cantidad"));
        ventaProducto.setPrecioUnitario(rs.getDouble("precio_unitario"));
        
        return ventaProducto;
    }
}
