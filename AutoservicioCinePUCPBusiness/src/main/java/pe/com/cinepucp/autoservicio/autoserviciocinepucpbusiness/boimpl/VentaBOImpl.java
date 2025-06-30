/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl;

import java.util.List;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.IVentaBO;
import pe.com.cinepucp.autoservicio.dao.IVentaDAO;
import pe.com.cinepucp.autoservicio.model.venta.Venta;
import pe.com.cinepucp.autoservicio.mysql.VentaDAOImpl;

/**
 *
 * @author Amira
 */
public class VentaBOImpl implements IVentaBO{
    private final IVentaDAO ventaDAO;
    
    public VentaBOImpl(){
        ventaDAO = new VentaDAOImpl();
    }
    
    @Override
    public void registrar(Venta modelo){
        //validarCupon(modelo);
        ventaDAO.insertar(modelo);
    }

    @Override
    public void actualizar(Venta modelo){
        //validarCupon(cupon);
        ventaDAO.modificar(modelo);
    }

    @Override
    public List<Venta> listar() {
        return ventaDAO.listar();
    }
    @Override
    public List<Venta> listarVentaReporte(String fechaini,String fechafin){
        return ventaDAO.listarVentasRep(fechaini, fechafin);
    }

    @Override
    public void eliminar(int id) {
        ventaDAO.eliminar(id);
    }

    @Override
    public Venta buscarPorId(int id) {
        return ventaDAO.buscar(id);
    }

    @Override
    public List<Venta> listarVentaPorUsuario(int idUsuario) {
        return ventaDAO.listarVentasPorUsuario(idUsuario);
    }
    
}
