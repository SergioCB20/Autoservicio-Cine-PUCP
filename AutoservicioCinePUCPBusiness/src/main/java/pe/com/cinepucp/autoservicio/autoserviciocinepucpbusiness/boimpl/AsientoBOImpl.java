/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl;

import java.util.List;
import java.util.Objects;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.IAsientoBO;
import pe.com.cinepucp.autoservicio.dao.IAsientoDAO;
import pe.com.cinepucp.autoservicio.model.salas.Asiento;
import pe.com.cinepucp.autoservicio.mysql.AsientoDAOImpl;

/**
 *
 * @author gonza
 */
public class AsientoBOImpl implements IAsientoBO{
    private final IAsientoDAO asientoDAO;
    
    public AsientoBOImpl(){
        asientoDAO=new AsientoDAOImpl();
    }
    private void validarAsiento(Asiento asiento) throws Exception {
        Objects.requireNonNull(asiento, "El asiento no puede ser nula.");
        if (asiento.getNumero()<=0) {
            throw new Exception("El asiento tiene numero invalido.");
        }
        if (asiento.getTipo()==null) {
            throw new Exception("El tipo de asiento es requerido.");
        }
        
    }
    @Override
    public void registrar(Asiento modelo) throws Exception {
        validarAsiento(modelo);
        asientoDAO.insertar(modelo);
    }

    @Override
    public void actualizar(Asiento modelo) throws Exception {
        validarAsiento(modelo);
        asientoDAO.modificar(modelo);
    }

    @Override
    public List<Asiento> listar() {
        return asientoDAO.listar();
    }
    
    @Override
    public List<Asiento> listaAsientoSala(int idsala){
        return asientoDAO.listaAsientos(idsala);
    }
    @Override
    public void eliminar(int id,int id_usua_mod) {
        asientoDAO.eliminar(id,id_usua_mod);
    }

    @Override
    public Asiento buscarPorId(int id) {
        return asientoDAO.buscar(id);
    }
    
}
