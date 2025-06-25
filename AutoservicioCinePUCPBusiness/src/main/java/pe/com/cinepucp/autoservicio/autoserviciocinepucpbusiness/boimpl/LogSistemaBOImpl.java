/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl;

import java.util.List;
import java.util.Objects;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.ILogSistemaBO;
import pe.com.cinepucp.autoservicio.dao.ILogSistemaDAO;
import pe.com.cinepucp.autoservicio.mysql.LogSistemaDAOImpl;
import pe.com.cinepucp.autoservicio.model.auth.LogSistema;

/**
 *
 * @author Sergio
 */
public class LogSistemaBOImpl implements ILogSistemaBO {
    private final ILogSistemaDAO logDAO;
    
    public LogSistemaBOImpl(){
        logDAO =new LogSistemaDAOImpl();
    }
    
    private void validarSala(LogSistema log) throws Exception {
        Objects.requireNonNull(log, "el log no puede ser nulao.");

        if (log.getUsuario()== 0) {
            throw new Exception("El usuario autor del log es requerido.");
        }
        if (log.getAccion().trim().equals("")) {
            throw new Exception("La accion es requerida.");
        }
        if (log.getFecha() == null) {
            throw new Exception("La fecha es requerida.");
        }
        
    }
    
    @Override
    public void registrar(LogSistema log) throws Exception {
        validarSala(log);
        logDAO.insertar(log);
    }

    @Override
    public void actualizar(LogSistema log) throws Exception {
        validarSala(log);
        logDAO.modificar(log);
    }

    @Override
    public List<LogSistema> listar() {
        return logDAO.listar();
    }

    @Override
    public void eliminar(int id) {
        logDAO.eliminar(id);
    }

    @Override
    public LogSistema buscarPorId(int id) {
        return logDAO.buscar(id);
    }

}
