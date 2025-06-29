package pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.transaccion.comando;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.transaccion.IComandoTransaccional;
import pe.com.cinepucp.autoservicio.dao.IBoletoDAO;
import pe.com.cinepucp.autoservicio.dao.IBoletoDetalleDAO;
import pe.com.cinepucp.autoservicio.dao.IVentaDAO;
import pe.com.cinepucp.autoservicio.dao.IVentaProductoDAO;
import pe.com.cinepucp.autoservicio.model.venta.Boleto;
import pe.com.cinepucp.autoservicio.model.venta.BoletoDetalle;
import pe.com.cinepucp.autoservicio.model.venta.Venta;
import pe.com.cinepucp.autoservicio.model.venta.VentaProducto;

public class ComandoGuardarBoleto extends ComandoBase implements IComandoTransaccional {
    public ComandoGuardarBoleto(IBoletoDAO boletoDAO, IBoletoDetalleDAO detalleDAO, IVentaDAO ventaDAO, IVentaProductoDAO ventaProductoDAO, Boleto modelo) {
        super(boletoDAO, detalleDAO, ventaDAO, ventaProductoDAO, modelo);
    }
    
    @Override
    public void ejecutar(Connection conexion) {
        IBoletoDAO boletoDao = this.getBoletoDAO();
        IBoletoDetalleDAO detalleDao = this.getDetalleDAO();
        IVentaDAO ventaDao = this.getVentaDAO();
        IVentaProductoDAO ventaProductoDao = this.getVentaProductoDAO();
        Boleto boleto = this.getModelo();
        
        //para insertar el boleto necesitamos un id de venta y función válida
        //el id de la venta se obtendrá aquí
        Venta auxVenta = boleto.getVenta();
        auxVenta.setFechaHora(LocalDateTime.now()); //se asigna la fecha actual
        int ventaId = ventaDao.insertar(auxVenta, conexion);
        auxVenta.setVentaId(ventaId);//se asigna el id de la venta creada
        boleto.setVenta(auxVenta);//regresa la venta modificada
        
        int idBoleto = boletoDao.insertar(boleto, conexion); //esto devuelve el ID
        List<BoletoDetalle>listaDetalle = boleto.getDetalles();
        List<VentaProducto>listaProducto = auxVenta.getProductosVendidos();
        if(listaDetalle!=null){
            for (BoletoDetalle detalle : listaDetalle) {
                Boleto auxBoleto = new Boleto();
                auxBoleto.setBoletoId(idBoleto);
                detalle.setBoleto(auxBoleto);  // Establecer relación
                detalleDao.insertar(detalle,conexion);  // Insertar detalle
            }
        }
        if(listaProducto!=null){
            for(VentaProducto productoVenta : listaProducto){
                Venta auxVenta2 = new Venta();
                auxVenta2.setVentaId(ventaId);
                productoVenta.setVenta(auxVenta2);
                ventaProductoDao.insertar(productoVenta,conexion);
            }
        }
    }
}