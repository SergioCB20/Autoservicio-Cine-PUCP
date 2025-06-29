package pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.transaccion.comando;

import pe.com.cinepucp.autoservicio.dao.IBoletoDAO;
import pe.com.cinepucp.autoservicio.dao.IBoletoDetalleDAO;
import pe.com.cinepucp.autoservicio.dao.IVentaDAO;
import pe.com.cinepucp.autoservicio.dao.IVentaProductoDAO;
import pe.com.cinepucp.autoservicio.model.venta.Boleto;

public abstract class ComandoBase {
    private final IBoletoDAO boletoDAO;
    private final IBoletoDetalleDAO detalleDAO;
    private final IVentaDAO ventaDAO;
    private final IVentaProductoDAO ventaProductoDAO;
    private final Boleto modelo;

    public ComandoBase(
        IBoletoDAO boletoDAO,
        IBoletoDetalleDAO detalleDAO,
        IVentaDAO ventaDAO,
        IVentaProductoDAO ventaProductoDAO,
        Boleto modelo
    ) {
        this.boletoDAO = boletoDAO;
        this.detalleDAO = detalleDAO;
        this.ventaDAO = ventaDAO;
        this.ventaProductoDAO = ventaProductoDAO;
        this.modelo = modelo;
    }
    
    public IBoletoDAO getBoletoDAO() {
        return boletoDAO;
    }
    
    public IBoletoDetalleDAO getDetalleDAO() {
        return detalleDAO;
    }
    
    public IVentaDAO getVentaDAO() {
        return ventaDAO;
    }
    
    public IVentaProductoDAO getVentaProductoDAO() {
        return ventaProductoDAO;
    }

    public Boleto getModelo() {
        return modelo;
    }
}
