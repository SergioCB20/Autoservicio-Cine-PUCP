package pe.com.cinepucp.autoservicio.autoservicio.WS;


import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.util.List;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.IProductoBO;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl.ProductoBOImpl;
import pe.com.cinepucp.autoservicio.model.comida.Producto;

/**
 *
 * @author Miguel
 */
@WebService(serviceName = "ComidaWS")
public class ComidaWS {

    private final IProductoBO productoBO;
    
    public ComidaWS(){
        productoBO = new ProductoBOImpl();
    }
    
    @WebMethod(operationName = "registrarComida")
    public void registrarComida(@WebParam(name = "comida") Producto prod) {
        try{
            productoBO.registrar(prod);
        }catch(Exception e){
            throw new WebServiceException("Error al registrar comida: "+e.getMessage());
        }
    }
    @WebMethod (operationName = "eliminarComida")
    public void eliminarComida(int id){
        try{
            System.err.println("comidaId: "+id);
            productoBO.eliminar(id);
        }catch(Exception e){
            throw new WebServiceException("Eror al eliminar comida: "+e.getMessage());
        }
    }
    
    @WebMethod (operationName = "actualizaComida")
    public void actualizarComida(Producto prod){
        try{
            productoBO.actualizar(prod);
        }catch(Exception e){
            throw new WebServiceException("Error al actualizar comida: "+e.getMessage());
        }
    }
    
    @WebMethod (operationName = "buscarComidaPorId")
    public Producto buscarComidaPorId(int id){
        try{
            return productoBO.buscarPorId(id);
        }catch (Exception e){
            throw  new WebServiceException("Error al buscar comida por id"+ e.getMessage());
        }
    }
    
    @WebMethod (operationName = "listarComidas")
    public List<Producto> listarComidas(){
        try{
            return productoBO.listar();
        }catch(Exception e){
            throw  new WebServiceException("Error al listar Comidas: "+e.getMessage());
        }
    }
}
