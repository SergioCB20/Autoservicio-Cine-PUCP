package pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl;

import java.util.List;
import java.util.Objects;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.IBoletoBO;
import pe.com.cinepucp.autoservicio.dao.IBoletoDAO;
import pe.com.cinepucp.autoservicio.model.venta.Boleto;
import pe.com.cinepucp.autoservicio.mysql.BoletoDAOImpl;

/**
 *
 * @author Piero
 */
public class BoletoBOImpl implements IBoletoBO{
    private final IBoletoDAO boletoDAO;
    
    public BoletoBOImpl() {
        boletoDAO = new BoletoDAOImpl();
    }
    
     private void validarBoleto(Boleto boleto) throws Exception {
         
        Objects.requireNonNull(boleto, "El boleto no puede ser nulo.");
    }
    
    @Override
    public void registrar(Boleto boleto) throws Exception {
        validarBoleto(boleto);
        boletoDAO.insertar(boleto);
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
