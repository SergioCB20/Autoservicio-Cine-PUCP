package pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.IBoletoBO;
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
public class BoletoBOImpl implements IBoletoBO{
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
        Venta auxVenta = boleto.getVenta();
        //para insertar el boleto necesitamos un id de venta y función válida
        //el id de la venta se obtendrá aquí
        auxVenta.setFechaHora(LocalDateTime.now()); //se asigna la fecha actual
        int idVenta = ventaDAO.insertar(auxVenta);
        auxVenta.setVentaId(idVenta); //se asigna el id de la venta creada
        boleto.setVenta(auxVenta); //regresa la venta modificada
        int idBoleto = boletoDAO.insertar(boleto); //esto devuelve el ID
        List<BoletoDetalle>listaDetalle = boleto.getDetalles();
        List<VentaProducto>listaProducto = auxVenta.getProductosVendidos();
        if(listaDetalle!=null){
            for (BoletoDetalle detalle : listaDetalle) {
                Boleto auxBoleto = new Boleto();
                auxBoleto.setBoletoId(idBoleto);
                detalle.setBoleto(auxBoleto);  // Establecer relación
                detalleDAO.insertar(detalle);  // Insertar detalle
            }
        }
        if(listaProducto!=null){
            for(VentaProducto productoVenta : listaProducto){
                Venta auxVenta2 = new Venta();
                auxVenta2.setVentaId(idVenta);
                productoVenta.setVenta(auxVenta2);
                ventaProductoDAO.insertar(productoVenta);
            }
        }
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
    public void eliminar(int id,int id_usua_mod){
        boletoDAO.eliminar(id,id_usua_mod);
    }
    @Override
    public Boleto buscarPorId(int id){
        return boletoDAO.buscar(id);
    }
}
