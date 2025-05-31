/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo;

import java.util.List;

/**
 *
 * @author Sergio
 */
public interface IBOBase<T> {
    void registrar(T modelo) throws Exception;
    void actualizar(T modelo) throws Exception;
    List<T> listar();
    void eliminar(int id);
    T buscarPorId(int id);
}
