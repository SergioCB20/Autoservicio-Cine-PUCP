package pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo;

import java.util.List;
import pe.com.cinepucp.autoservicio.model.salas.AsientoFuncion;

public interface IAsientoFuncionBO extends IBOBase<AsientoFuncion> {
    List<AsientoFuncion> listarPorFuncion(int funcionId);
}
