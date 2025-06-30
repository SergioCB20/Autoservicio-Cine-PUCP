package pe.com.cinepucp.autoservicio.dao;

import java.util.List;
import pe.com.cinepucp.autoservicio.model.salas.AsientoFuncion;


public interface IAsientoFuncionDAO extends ICrud<AsientoFuncion> {
    public List<AsientoFuncion> listaAsientosFuncion(int idfuncion);
}
