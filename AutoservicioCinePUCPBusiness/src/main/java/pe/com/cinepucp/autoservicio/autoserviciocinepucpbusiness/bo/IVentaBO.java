/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo;

import java.util.List;
import pe.com.cinepucp.autoservicio.model.venta.Venta;

/**
 *
 * @author Amira
 */
public interface IVentaBO extends IBOBase<Venta>{
    public List<Venta> listarVentaReporte(String fechaini,String fechafin);
}
