package pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl;

import java.util.List;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.IAsientoFuncionBO;
import pe.com.cinepucp.autoservicio.dao.IAsientoFuncionDAO;
import pe.com.cinepucp.autoservicio.model.salas.AsientoFuncion;
import pe.com.cinepucp.autoservicio.mysql.AsientoFuncionDAOImpl;

public class AsientoFuncionBOImpl implements IAsientoFuncionBO {
    private final IAsientoFuncionDAO asientoFuncionDAO;
    
    public AsientoFuncionBOImpl() {
        asientoFuncionDAO = new AsientoFuncionDAOImpl();
    }
    
    private void validarAsientoFuncion(AsientoFuncion funcion) throws Exception {
        if(funcion==null)
            throw new Exception("El asiento función no puede ser nulo.");
    }
    
    
    @Override
    public void registrar(AsientoFuncion funcion) throws Exception {
        validarAsientoFuncion(funcion);
        
        asientoFuncionDAO.insertar(funcion);
    }
    
    @Override
    public void actualizar(AsientoFuncion funcion) throws Exception {
        if (funcion.getIdAsientoFuncion()== null) {
            throw new Exception("El ID del asiento-función es requerido para actualizar.");
        }
        
        validarAsientoFuncion(funcion);
        
        
        asientoFuncionDAO.modificar(funcion);
    }
    
    @Override
    public List<AsientoFuncion> listar() {
        return asientoFuncionDAO.listar();
    }
    
    /**
     * Lista asientos por función
     * @param funcionID ID de la función
     * @return Lista de asientos de la función
     */
    
    @Override
    public List<AsientoFuncion> listarPorFuncion(int funcionID) {
        if (funcionID <= 0) {
            throw new IllegalArgumentException("El ID de la función debe ser válido.");
        }
        return asientoFuncionDAO.listaAsientosFuncion(funcionID);
    }

    @Override
    public void eliminar(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID de la función debe ser válido.");
        }
        
    }
    
    @Override
    public AsientoFuncion buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID de la función debe ser válido.");
        }
        return null;
    }
    
}
