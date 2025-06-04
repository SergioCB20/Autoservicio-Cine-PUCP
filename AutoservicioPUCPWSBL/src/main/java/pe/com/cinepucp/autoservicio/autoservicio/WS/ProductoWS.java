/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.autoservicio.WS;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import java.util.List;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.IProductoBO;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl.ProductoBOImpl;
import pe.com.cinepucp.autoservicio.model.comida.Producto;
import pe.com.cinepucp.autoservicio.model.comida.TipoProducto;
/**
 *
 * @author gonza
 */
@WebService(serviceName="ProductoWS",
        targetNamespace = "http://services.AutoCine.pucp.edu.pe/")
public class ProductoWS {
    private final IProductoBO productoBO;
    
    public ProductoWS() {
       
        this.productoBO = new ProductoBOImpl();
    }
    
    @WebMethod(operationName = "registrarProducto")
    public void registrarProducto(Producto prod) {
        System.out.println("--- Java Producto object received from Web Service ---");
        System.out.println("productoId: " + prod.getId());
        System.out.println("Nombre: " + prod.getNombre());
        System.out.println("Descripcion: " + prod.getDescripcion());
        System.out.println("Precio: " + prod.getPrecio());
        System.out.println("Tipo: " + prod.getTipo().name());        
        System.out.println("estaActiva: " + prod.isEstaActivo()); 
        System.out.println("-----------------------------------------------------");
        
        // *** Punto de verificación crítico ***
        if (prod.getTipo() == null) {
            System.err.println("ERROR CRÍTICO: TipoProducto es NULL al recibir el objeto Producto en registrarProducto.");
            // Lanza una excepción más específica para el cliente C#
            throw new WebServiceException("Error del servicio: El tipo de producto enviado no pudo ser procesado. Es posible que el valor no sea reconocido o esté ausente.");
        }

        // Si llegamos aquí, el tipo NO es null, y podemos llamarle a .name()
        System.out.println("Tipo: " + prod.getTipo().name());
        System.out.println("estaActiva: " + prod.isEstaActivo());
        System.out.println("-----------------------------------------------------");

        
        try {
            productoBO.registrar(prod);                    
        } catch (Exception e) {
            // *** MUY IMPORTANTE: Imprimir la pila de la excepción completa ***
            e.printStackTrace(); // Esto imprimirá el stack trace en la consola del servidor de aplicaciones
            throw new WebServiceException("Error al registrar producto: " + e.getMessage());
        }
    }
    
    @WebMethod(operationName = "actualizarProducto")
    public void actualizarProducto(Producto prod) {
        System.out.println("--- Java Producto object received from Web Service ---");
        System.out.println("productoId: " + prod.getId());
        System.out.println("Nombre: " + prod.getNombre());
        System.out.println("Descripcion: " + prod.getDescripcion());
        System.out.println("Precio: " + prod.getPrecio());
        System.out.println("Tipo: " + prod.getTipo().name());        
        System.out.println("estaActiva: " + prod.isEstaActivo()); 
        System.out.println("-----------------------------------------------------");
        try {
            productoBO.actualizar(prod);
        } catch (Exception e) {
            throw new WebServiceException("Error al actualizar producto: " + e.getMessage());
        }
    }
    
    @WebMethod(operationName = "eliminarProducto")
    public void eliminarProducto(int id) {
        try {
            productoBO.eliminar(id);
        } catch (Exception e) {
            throw new WebServiceException("Error al eliminar producto: " + e.getMessage());
        }
    }
    
    @WebMethod(operationName = "buscarProductoPorId")
    public Producto buscarProductoPorId(int id) {
        try {
            return productoBO.buscarPorId(id);
        } catch (Exception e) {
            throw new WebServiceException("Error al buscar producto por id: " + e.getMessage());
        }
    }
    
    @WebMethod(operationName = "listarProductos")
    public List<Producto> listarProductos() {
        try {
            return productoBO.listar();
        } catch (Exception e) {
            throw new WebServiceException("Error al listar productos: " + e.getMessage());
        }
    }
    
}
