package pe.com.cinepucp.autoservicio.autoservicio.WS;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.util.List;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.IBoletoBO;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl.BoletoBOImpl;
import pe.com.cinepucp.autoservicio.model.venta.Boleto;
@WebService(serviceName = "BoletoWS",
        targetNamespace = "http://services.AutoCine.pucp.edu.pe/")
public class BoletoWS {

    private final IBoletoBO boletoBO;
    
    public BoletoWS() {
        boletoBO = new BoletoBOImpl();
    }
    
    @WebMethod(operationName = "listarBoleto")
    public List<Boleto> listar() {
        return this.boletoBO.listar();
    }
    
    @WebMethod(operationName = "actualizarBoleto")
    public void guardarBoleto(@WebParam(name = "boleto")Boleto boleto) {
        try {
            this.boletoBO.actualizar(boleto);
        } catch (Exception e) {
            throw new WebServiceException("Error al guardar boleto: " + e.getMessage());
        }
    }
    
    @WebMethod(operationName = "obtenerBoleto")
    public Boleto obtener(
        @WebParam(name = "id") int id
    ) {
        return this.boletoBO.buscarPorId(id);
    }
    
    @WebMethod(operationName = "eliminar")
    public void eliminar(
        @WebParam(name = "id") int id
    ) {
        this.boletoBO.eliminar(id);
    }
    
}   