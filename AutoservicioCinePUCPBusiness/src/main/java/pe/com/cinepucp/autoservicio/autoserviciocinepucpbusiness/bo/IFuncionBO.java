/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo;

import java.util.List;
import pe.com.cinepucp.autoservicio.model.Peliculas.Funcion;

/**
 *
 * @author Sergio
 */
public interface IFuncionBO extends IBOBase<Funcion> {
    List<Funcion> listarPorPelicula(int peliculaId);
}
