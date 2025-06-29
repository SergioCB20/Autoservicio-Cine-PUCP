/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl;

import java.util.List;
import java.util.Objects;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.ISalaBO;
import pe.com.cinepucp.autoservicio.dao.ISalaDAO;
import pe.com.cinepucp.autoservicio.model.salas.Sala;
import pe.com.cinepucp.autoservicio.mysql.SalaDAOImpl;

/**
 *
 * @author gonza
 */
public class SalaBOImpl implements ISalaBO{
    private final ISalaDAO salaDAO;
    
    public SalaBOImpl(){
        salaDAO=new SalaDAOImpl();
    }
    
    private void validarSala(Sala sala) throws Exception {
        Objects.requireNonNull(sala, "La sala no puede ser nula.");

        if (sala.getNombre()== null || sala.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre de la sala es requerido.");
        }
        if (sala.getCapacidad()<=0) {
            throw new Exception("La sala no tiene capacidad.");
        }
        if (sala.getTipoSala()==null) {
            throw new Exception("El tipo de sala es requerido.");
        }
        
    }
    
    @Override
    public void registrar(Sala sala) throws Exception {
        validarSala(sala);
        salaDAO.insertar(sala);
    }

    @Override
    public void actualizar(Sala sala) throws Exception {
        validarSala(sala);
        salaDAO.modificar(sala);
    }

    @Override
    public List<Sala> listar() {
        return salaDAO.listar();
    }

    @Override
    public void eliminar(int id,int id_usua_mod) {
        salaDAO.eliminar(id,id_usua_mod);
    }

    @Override
    public Sala buscarPorId(int id) {
        return salaDAO.buscar(id);
    }

    
}
