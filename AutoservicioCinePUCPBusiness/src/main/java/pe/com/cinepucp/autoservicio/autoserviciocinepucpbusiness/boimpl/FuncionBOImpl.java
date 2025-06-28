package pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.IFuncionBO;
import pe.com.cinepucp.autoservicio.dao.IFuncionDAO;
import pe.com.cinepucp.autoservicio.model.Peliculas.Funcion;
import pe.com.cinepucp.autoservicio.mysql.FuncionDAOImpl;

/**
 * Implementación de la lógica de negocio para funciones de cine
 * @author Sergio
 */
public class FuncionBOImpl implements IFuncionBO {
    private final IFuncionDAO funcionDAO;
    
    public FuncionBOImpl() {
        funcionDAO = new FuncionDAOImpl();
    }
    
    /**
     * Valida los datos de una función antes de procesarla
     * @param funcion La función a validar
     * @throws Exception Si algún campo no cumple con las validaciones
     */
    private void validarFuncion(Funcion funcion) throws Exception {
        Objects.requireNonNull(funcion, "La función no puede ser nula.");
        
        // Validar película
        if (funcion.getPeliculaId() == 0) {
            throw new Exception("La función debe tener una película asignada.");
        }
        
        // Validar sala
        if (funcion.getSalaId() == 0) {
            throw new Exception("La función debe tener una sala asignada.");
        }
        
        // Validar fecha y hora
        if (funcion.getFechaHora() == null) {
            throw new Exception("La fecha y hora de la función son requeridas.");
        }
        
        // Validar que la función sea en el futuro (para nuevas funciones)
        if (funcion.getFuncionId() == null && funcion.getFechaHora().isBefore(LocalDateTime.now())) {
            throw new Exception("La fecha y hora de la función debe ser futura.");
        }
        
        // Validar formato de proyección
        if (funcion.getFormatoProyeccion() == null || funcion.getFormatoProyeccion().trim().isEmpty()) {
            throw new Exception("El formato de proyección es requerido.");
        }
        
        // Validar formato de proyección permitido
        String formato = funcion.getFormatoProyeccion().toUpperCase();
        if (!formato.equals("2D") && !formato.equals("3D") && !formato.equals("IMAX") && !formato.equals("4DX")) {
            throw new Exception("El formato de proyección debe ser: 2D, 3D, IMAX o 4DX.");
        }
        
        // Validar idioma
        if (funcion.getIdioma() == null || funcion.getIdioma().trim().isEmpty()) {
            throw new Exception("El idioma de la función es requerido.");
        }
        
        // Validar idioma permitido
        String idioma = funcion.getIdioma().toLowerCase();
        if (!idioma.equals("español") && !idioma.equals("inglés") && !idioma.equals("frances")) {
            throw new Exception("El idioma debe ser: Español, Inglés o Francés.");
        }
        
        // Validar subtítulos (no puede ser null)
        if (funcion.getSubtitulos() == null) {
            throw new Exception("Se debe especificar si la función tiene subtítulos.");
        }
        
        // Validar usuario modificación
        if (funcion.getUsuarioModificacion() == null || funcion.getUsuarioModificacion() <= 0) {
            throw new Exception("El usuario que modifica la función es requerido.");
        }
    }
    
    
    @Override
    public void registrar(Funcion funcion) throws Exception {
        validarFuncion(funcion);
        
        // Establecer valores por defecto para nueva función
        funcion.setEstaActiva(true);
        funcion.setFechaModificacion(LocalDateTime.now());
        
        funcionDAO.insertar(funcion);
    }
    
    @Override
    public void actualizar(Funcion funcion) throws Exception {
        if (funcion.getFuncionId() == null) {
            throw new Exception("El ID de la función es requerido para actualizar.");
        }
        
        validarFuncion(funcion);
        
        // Actualizar fecha de modificación
        funcion.setFechaModificacion(LocalDateTime.now());
        
        funcionDAO.modificar(funcion);
    }
    
    @Override
    public List<Funcion> listar() {
        return funcionDAO.listar();
    }
    
    /**
     * Lista funciones por película
     * @param peliculaId ID de la película
     * @return Lista de funciones de la película
     */
    
    @Override
    public List<Funcion> listarPorPelicula(int peliculaId) {
        if (peliculaId <= 0) {
            throw new IllegalArgumentException("El ID de la película debe ser válido.");
        }
        return funcionDAO.buscarPorPelicula(peliculaId);
    }

    @Override
    public void eliminar(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID de la función debe ser válido.");
        }
        
        Funcion funcion = funcionDAO.buscar(id);
        if (funcion == null) {
            throw new IllegalArgumentException("No se encontró la función con ID: " + id);
        }
        
        // Verificar si tiene boletos vendidos
        if (funcion.getBoletos() != null && !funcion.getBoletos().isEmpty()) {
            throw new IllegalStateException("No se puede eliminar una función que tiene boletos vendidos.");
        }
        
        funcionDAO.eliminar(id);
    }
    
    /**
     * Desactiva una función en lugar de eliminarla
     * @param id ID de la función
     * @param usuarioModificacion ID del usuario que desactiva
     */
    public void desactivar(int id, Integer usuarioModificacion) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID de la función debe ser válido.");
        }
        if (usuarioModificacion == null || usuarioModificacion <= 0) {
            throw new IllegalArgumentException("El usuario de modificación es requerido.");
        }
        
        Funcion funcion = funcionDAO.buscar(id);
        if (funcion == null) {
            throw new IllegalArgumentException("No se encontró la función con ID: " + id);
        }
        
        funcion.setEstaActiva(false);
        funcion.setFechaModificacion(LocalDateTime.now());
        funcion.setUsuarioModificacion(usuarioModificacion);
        
        try {
            funcionDAO.modificar(funcion);
        } catch (Exception e) {
            throw new RuntimeException("Error al desactivar la función: " + e.getMessage());
        }
    }
    
    @Override
    public Funcion buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID de la función debe ser válido.");
        }
        return funcionDAO.buscar(id);
    }
    
    /**
     * Verifica si una función está disponible para venta de boletos
     * @param id ID de la función
     * @return true si está disponible, false en caso contrario
     */
    public boolean estaDisponibleParaVenta(int id) {
        Funcion funcion = buscarPorId(id);
        if (funcion == null) {
            return false;
        }
        
        // Debe estar activa y la función debe ser futura (al menos 30 min antes)
        return funcion.getEstaActiva() && 
               funcion.getFechaHora().isAfter(LocalDateTime.now().plusMinutes(30));
    }
}