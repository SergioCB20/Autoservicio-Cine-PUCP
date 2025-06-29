package pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.IBoletoBO;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.transaccion.TransaccionalBO;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.transaccion.comando.ComandoGuardarBoleto;
import pe.com.cinepucp.autoservicio.config.DBManager;
import pe.com.cinepucp.autoservicio.dao.IBoletoDAO;
import pe.com.cinepucp.autoservicio.dao.IBoletoDetalleDAO;
import pe.com.cinepucp.autoservicio.dao.IVentaDAO;
import pe.com.cinepucp.autoservicio.dao.IVentaProductoDAO;
import pe.com.cinepucp.autoservicio.model.venta.Boleto;
import pe.com.cinepucp.autoservicio.model.venta.BoletoDetalle;
import pe.com.cinepucp.autoservicio.model.venta.Venta;
import pe.com.cinepucp.autoservicio.model.venta.VentaProducto;
import pe.com.cinepucp.autoservicio.mysql.BoletoDAOImpl;
import pe.com.cinepucp.autoservicio.mysql.BoletoDetalleDAOImpl;
import pe.com.cinepucp.autoservicio.mysql.VentaDAOImpl;
import pe.com.cinepucp.autoservicio.mysql.VentaProductoDAOImpl;

/**
 *
 * @author Piero
 */
public class BoletoBOImpl extends TransaccionalBO implements IBoletoBO{
    private final IBoletoDAO boletoDAO;
    private final IBoletoDetalleDAO detalleDAO;
    private final IVentaDAO ventaDAO;
    private final IVentaProductoDAO ventaProductoDAO;
    
    public BoletoBOImpl() {
        boletoDAO = new BoletoDAOImpl();
        detalleDAO = new BoletoDetalleDAOImpl();
        ventaDAO = new VentaDAOImpl();
        ventaProductoDAO = new VentaProductoDAOImpl();
    }
    
     private void validarBoleto(Boleto boleto) throws Exception {
         
        Objects.requireNonNull(boleto, "El boleto no puede ser nulo.");
    }
    
    @Override
    public void registrar(Boleto boleto) throws Exception {
        validarBoleto(boleto);
        
        ComandoGuardarBoleto comando = 
                new ComandoGuardarBoleto(
            this.boletoDAO, 
            this.detalleDAO, 
            this.ventaDAO, 
            this.ventaProductoDAO, 
            boleto
        );
        this.ejecutarTransaccion(comando);
    }
    
    @Override
    public void actualizar(Boleto boleto) throws Exception{
        validarBoleto(boleto);
        boletoDAO.modificar(boleto);
    }
    @Override
    public List<Boleto> listar(){
        return boletoDAO.listar();
    }
    @Override
    public void eliminar(int id){
        boletoDAO.eliminar(id);
    }
    @Override
    public Boleto buscarPorId(int id){
        return boletoDAO.buscar(id);
    }
}
