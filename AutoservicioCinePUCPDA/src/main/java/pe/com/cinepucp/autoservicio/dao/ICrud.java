/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.dao;
import java.util.List;

/**
 *
 * @author Sergio
 * @param <T>
 */
public interface ICrud<T> {
    int insertar(T modelo);
    boolean modificar(T modelo);
    boolean eliminar(int id,int id_modificacion);
    T buscar(int id);
    List<T> listar();
}
