/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl;

import java.util.List;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.IProductoBO;
import pe.com.cinepucp.autoservicio.dao.IProductoDAO;
import pe.com.cinepucp.autoservicio.mysql.ProductoDAOImpl;
import pe.com.cinepucp.autoservicio.model.comida.Producto;
import java.util.Objects;
/**
 *
 * @author gonza
 */
public class ProductoBOImpl implements IProductoBO{
    private final IProductoDAO productoDAO;
    
    public ProductoBOImpl() {
        this.productoDAO = new ProductoDAOImpl();
    }
    
    private void validarProducto(Producto producto) throws Exception {
         
        Objects.requireNonNull(producto, "El producto no puede ser nulo.");

        if (producto.getNombre_es()== null || producto.getNombre_es().trim().isEmpty()) {
            throw new Exception("El nombre del producto es requerido.");
        }
        if (producto.getDescripcion_es() == null || producto.getDescripcion_es().trim().isEmpty()) {
            throw new Exception("La descripcion de producto es requerido.");
        }
        if (producto.getPrecio()<=0) {
            throw new Exception("El precio del producto debe tener sentido.");
        }
        if (producto.getTipo() == null) {
            throw new Exception("La sinopsis en español de la película es requerida.");
        }
        
    }
    
    @Override
    public void registrar(Producto prod) throws Exception {
        validarProducto(prod);
        productoDAO.insertar(prod);
    }
    
    @Override
    public void actualizar(Producto prod) throws Exception{
        validarProducto(prod);
        productoDAO.modificar(prod);
    }
    @Override
    public List<Producto> listar(){
        return productoDAO.listar();
    }
    @Override
    public void eliminar(int id,int id_usua_mod){
        productoDAO.eliminar(id, id_usua_mod);
    }
    @Override
    public Producto buscarPorId(int id){
        return productoDAO.buscar(id);
    }
}

    
   
