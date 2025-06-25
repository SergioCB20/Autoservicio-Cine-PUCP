package pe.com.cinepucp.autoservicio.dao;

import java.time.LocalDateTime;
import java.util.List;
import pe.com.cinepucp.autoservicio.model.venta.Venta;

public interface IVentaDAO extends ICrud<Venta>{
    public List<Venta> listarVentasRep(LocalDateTime fechaini,LocalDateTime fechafin);
} 