package pe.com.cinepucp.autoservicio.dao;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import pe.com.cinepucp.autoservicio.model.venta.Venta;

public interface IVentaDAO extends ICrud<Venta>{
    public List<Venta> listarVentasRep(String fechaini,String fechafin);
    public List<Venta> listarVentasPorUsuario(int idUsuario);
} 