/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl;

import java.util.List;
import java.util.Objects;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.ICuponBO;
import pe.com.cinepucp.autoservicio.dao.ICuponDAO;
import pe.com.cinepucp.autoservicio.model.venta.Cupon;
import pe.com.cinepucp.autoservicio.mysql.CuponDAOImpl;

/**
 *
 * @author Amira
 */
public class CuponBOImpl implements ICuponBO{
    private final ICuponDAO cuponDAO;
    
    public CuponBOImpl(){
        cuponDAO = new CuponDAOImpl();
    }
    
    private void validarCupon(Cupon cupon) throws Exception {
         
        Objects.requireNonNull(cupon, "El cupon no puede ser nulo.");

    }
    
    @Override
    public void registrar(Cupon cupon) throws Exception {
        validarCupon(cupon);
        int idCupon = cuponDAO.insertar(cupon);
    }
    
    @Override
    public void actualizar(Cupon cupon) throws Exception{
        validarCupon(cupon);
        cuponDAO.modificar(cupon);
    }
    @Override
    public List<Cupon> listar(){
        return cuponDAO.listar();
    }
    @Override
    public void eliminar(int id,int id_usua_mod){
        cuponDAO.eliminar(id,id_usua_mod);
    }
    @Override
    public Cupon buscarPorId(int id){
        return cuponDAO.buscar(id);
    }
    
}
