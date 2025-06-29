package pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.transaccion;

import java.sql.Connection;

public interface IComandoTransaccional {
    void ejecutar(Connection conexion);
}
