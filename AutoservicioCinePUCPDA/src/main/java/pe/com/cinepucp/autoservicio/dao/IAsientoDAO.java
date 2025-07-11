/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.dao;
import java.util.List;
import pe.com.cinepucp.autoservicio.model.salas.Asiento;

/**
 *
 * @author Sergio
 */
public interface IAsientoDAO extends ICrud<Asiento> {
    public List<Asiento> listaAsientos(int idsala);
}
