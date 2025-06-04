/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.autoserviciopucpwsbl;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import java.util.List;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.ISalaBO;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl.SalaBOImpl;
import pe.com.cinepucp.autoservicio.model.salas.Sala;

/**
 *
 * @author gonza
 */

@WebService(serviceName="SalaWS",
        targetNamespace = "http://services.AutoCine.pucp.edu.pe/")
public class SalaWS {
    private final ISalaBO salaBO;
    
    public SalaWS(){
        this.salaBO=new SalaBOImpl();
    }
    @WebMethod(operationName = "registrarSala")
    public void registrarSala(Sala sala) {
        System.out.println("--- Java Sala object received from Web Service ---");
        System.out.println("SalaId: " + sala.getId());
        System.out.println("Nombre: " + sala.getNombre());
        System.out.println("Tipo de sala: " + sala.getTipoSala().getDescripcion());
        System.out.println("Capacidad: " + sala.getCapacidad());
        System.out.println("estaActiva: " + sala.isActiva()); 
        System.out.println("-----------------------------------------------------");
        try {
            salaBO.registrar(sala);
        } catch (Exception e) {
            throw new WebServiceException("Error al registrar Comboitem: " + e.getMessage());
        }
    }
    
    @WebMethod(operationName = "actualizarSala")
    public void actualizarSala(Sala sala) {
        System.out.println("--- Java Sala object received from Web Service ---");
        System.out.println("SalaId: " + sala.getId());
        System.out.println("Nombre: " + sala.getNombre());
        System.out.println("Tipo de sala: " + sala.getTipoSala().name());
        System.out.println("Capacidad: " + sala.getCapacidad());
        System.out.println("estaActiva: " + sala.isActiva()); 
        System.out.println("-----------------------------------------------------");
        try {
            salaBO.actualizar(sala);
        } catch (Exception e) {
            throw new WebServiceException("Error al actualizar sala: " + e.getMessage());
        }
    }
    
    @WebMethod(operationName = "eliminarSala")
    public void eliminarSala(int id) {
        try {
            salaBO.eliminar(id);
        } catch (Exception e) {
            throw new WebServiceException("Error al eliminar sala: " + e.getMessage());
        }
    }
    
    @WebMethod(operationName = "buscarSalaPorId")
    public Sala buscarSalaPorId(int id) {
        try {
            return salaBO.buscarPorId(id);
        } catch (Exception e) {
            throw new WebServiceException("Error al buscar sala por id: " + e.getMessage());
        }
    }
    
    @WebMethod(operationName = "listarSalas")
    public List<Sala> listarSalas() {
        try {
            return salaBO.listar();
        } catch (Exception e) {
            throw new WebServiceException("Error al listar salas: " + e.getMessage());
        }
    }    
    
}

