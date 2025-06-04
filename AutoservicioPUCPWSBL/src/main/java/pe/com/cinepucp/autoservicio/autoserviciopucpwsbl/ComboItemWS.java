/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.autoserviciopucpwsbl;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import java.util.List;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.IComboItemBO;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl.ComboItemBOImpl;
import pe.com.cinepucp.autoservicio.model.comida.ComboItem;
/**
 *
 * @author gonza
 */
@WebService(serviceName="ComboItemWS",
        targetNamespace = "http://services.AutoCine.pucp.edu.pe/")
public class ComboItemWS {
    private final IComboItemBO ComboItemBo;
    
    public ComboItemWS(){
        this.ComboItemBo=new ComboItemBOImpl();
    }
    @WebMethod(operationName = "registrarComboItem")
    public void registrarComboItem(ComboItem combo) {
        System.out.println("--- Java ComboItem object received from Web Service ---");
        System.out.println("ComboItemId: " + combo.getCombo().getId());
        System.out.println("Nombre: " + combo.getProducto().getNombre());
        System.out.println("Cantidad: " + combo.getCantidad());
        System.out.println("-----------------------------------------------------");
        try {
            ComboItemBo.registrar(combo);
        } catch (Exception e) {
            throw new WebServiceException("Error al registrar Comboitem: " + e.getMessage());
        }
    }
    
    @WebMethod(operationName = "actualizarComboItem")
    public void actualizarComboItem(ComboItem combo) {
        System.out.println("--- Java ComboItem object received from Web Service ---");
        System.out.println("ComboItemId: " + combo.getCombo().getId());
        System.out.println("Nombre: " + combo.getProducto().getNombre());
        System.out.println("Cantidad: " + combo.getCantidad());
        try {
            ComboItemBo.actualizar(combo);
        } catch (Exception e) {
            throw new WebServiceException("Error al actualizar ComboItem: " + e.getMessage());
        }
    }
    
    @WebMethod(operationName = "eliminarComboItem")
    public void eliminarComboItem(int id) {
        try {
            ComboItemBo.eliminar(id);
        } catch (Exception e) {
            throw new WebServiceException("Error al eliminar ComboItem: " + e.getMessage());
        }
    }
    
    @WebMethod(operationName = "buscarComboItemPorId")
    public ComboItem buscarComboItemPorId(int id) {
        try {
            return ComboItemBo.buscarPorId(id);
        } catch (Exception e) {
            throw new WebServiceException("Error al buscar ComboItem por id: " + e.getMessage());
        }
    }
    
    @WebMethod(operationName = "listarComboItems")
    public List<ComboItem> listarComboItems() {
        try {
            return ComboItemBo.listar();
        } catch (Exception e) {
            throw new WebServiceException("Error al listar ComboItem: " + e.getMessage());
        }
    }    
    
}
