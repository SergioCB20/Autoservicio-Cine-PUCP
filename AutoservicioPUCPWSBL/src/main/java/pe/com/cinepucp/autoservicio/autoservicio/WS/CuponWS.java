/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.com.cinepucp.autoservicio.autoservicio.WS;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.util.List;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.ICuponBO;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl.CuponBOImpl;
import pe.com.cinepucp.autoservicio.dao.ICuponDAO;
import pe.com.cinepucp.autoservicio.model.venta.Cupon;

/**
 *
 * @author Amira
 */
@WebService(serviceName = "CuponWS",
        targetNamespace = "http://services.AutoCine.pucp.edu.pe/")
public class CuponWS {
    private final ICuponBO cuponBO;
    
    public CuponWS() {
        cuponBO = new CuponBOImpl();
    }
    
    @WebMethod(operationName = "registrarCupon")
    public void registrarCupon(Cupon cupon) {
        try {
            cuponBO.registrar(cupon);
        } catch (Exception e) {
            throw new WebServiceException("Error al registrar cupon: " + e.getMessage());
        }
    }
    
    @WebMethod(operationName = "actualizarCupon")
    public void actualizarCupon(Cupon cupon) {
        try {
            cuponBO.actualizar(cupon);
        } catch (Exception e) {
            throw new WebServiceException("Error al actualizar cupon: " + e.getMessage());
        }
    }
    
    @WebMethod(operationName = "eliminarCupon")
    public void eliminarCupon(int id) {
        try {
            cuponBO.eliminar(id);
        } catch (Exception e) {
            throw new WebServiceException("Error al eliminar cupon: " + e.getMessage());
        }
    }
    
    @WebMethod(operationName = "buscarCuponPorId")
    public Cupon buscarCuponPorId(int id) {
        try {
            return cuponBO.buscarPorId(id);
        } catch (Exception e) {
            throw new WebServiceException("Error al buscar cupon por id: " + e.getMessage());
        }
    }
    
    @WebMethod(operationName = "listarCupones")
    public List<Cupon> listarCupones() {
        try {
            return cuponBO.listar();
        } catch (Exception e) {
            throw new WebServiceException("Error al listar peliculas: " + e.getMessage());
        }
    }
}
