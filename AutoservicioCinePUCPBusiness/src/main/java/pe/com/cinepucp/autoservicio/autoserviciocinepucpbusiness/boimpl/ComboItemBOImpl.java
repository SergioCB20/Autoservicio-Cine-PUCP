/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl;

import java.util.List;
import java.util.Objects;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.IBOBase;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.IComboItemBO;
import pe.com.cinepucp.autoservicio.dao.IComboItemDAO;
import pe.com.cinepucp.autoservicio.model.comida.ComboItem;
import pe.com.cinepucp.autoservicio.mysql.ComboItemDAOImpl;
/**
 *
 * @author gonza
 */
public class ComboItemBOImpl implements IComboItemBO{
    private final IComboItemDAO ComboItemDAO;
    
    public ComboItemBOImpl(){
        this.ComboItemDAO=new ComboItemDAOImpl();
    }
    private void validarProducto(ComboItem combo) throws Exception{
        Objects.requireNonNull(combo, "El combo no puede ser nulo.");

        if (combo.getProducto()==null) {
            throw new Exception("El producto que pertenece al combo es requerido.");
        }        
        if (combo.getCantidad()<=0) {
            throw new Exception("la cantidad del producto del combo debe tener sentido.");
        }
        
    }  

    @Override
    public void registrar(ComboItem combo) throws Exception {
        validarProducto(combo);
        ComboItemDAO.insertar(combo);
    }

    @Override
    public void actualizar(ComboItem combo) throws Exception {
        validarProducto(combo);
        ComboItemDAO.modificar(combo);
    }

    @Override
    public List<ComboItem> listar() {
        return ComboItemDAO.listar();
    }

    @Override
    public void eliminar(int id,int id_usua_mod) {
        ComboItemDAO.eliminar(id, id_usua_mod);
    }

    @Override
    public ComboItem buscarPorId(int id) {
        return ComboItemDAO.buscar(id);
    }
    
}
