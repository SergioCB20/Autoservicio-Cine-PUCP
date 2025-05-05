package pe.com.cinepucp.autoservicio.model.auth;

import java.time.LocalDate;
import java.util.List;
import pe.com.cinepucp.autoservicio.model.venta.Cupon;
import pe.com.cinepucp.autoservicio.model.venta.Venta;
import pe.com.cinepucp.autoservicio.model.auth.CodigoVerificacion;

public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String telefono;
    private String password;
    private LocalDate fechaRegistro;
    private boolean estaActivo;
    private String idiomaPreferido;
    private TipoUsuario tipoUsuario;
    private List<Sesion> sesiones;
    private List<CodigoVerificacion> codigosVerificacion;
    private List<Venta> compras;
    private List<Cupon> cupones;
    private List<LogSistema> logsUsuario;

    public Usuario() {
    }
    
    public Usuario(Usuario usuario){
        this.id=usuario.getId();
        this.nombre = usuario.getNombre();
        this.email = usuario.getEmail();
        this.telefono = usuario.getTelefono();
        this.password = usuario.getPassword();
        this.fechaRegistro = usuario.getFechaRegistro();
        this.estaActivo = usuario.isEstaActivo();
        this.idiomaPreferido = usuario.getIdiomaPreferido();
    }
    
    public Usuario(String nombre, String email, String telefono, String password,
            LocalDate fechaRegistro, boolean estaActivo, String idiomaPreferido) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
        this.fechaRegistro = fechaRegistro;
        this.estaActivo = estaActivo;
        this.idiomaPreferido = idiomaPreferido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public boolean isEstaActivo() {
        return estaActivo;
    }

    public void setEstaActivo(boolean estaActivo) {
        this.estaActivo = estaActivo;
    }

    public String getIdiomaPreferido() {
        return idiomaPreferido;
    }

    public void setIdiomaPreferido(String idiomaPreferido) {
        this.idiomaPreferido = idiomaPreferido;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    // Métodos para manipulación de sesiones
    public void agregarSesion(Sesion sesion) {
        this.sesiones.add(sesion);
    }

    public void eliminarSesion(Sesion sesion) {
        this.sesiones.remove(sesion);
    }

    public boolean tieneSesiones() {
        return !this.sesiones.isEmpty();
    }

    public List<Sesion> obtenerSesiones() {
        return List.copyOf(this.sesiones); // Devuelve una copia inmutable
    }

    // Métodos para manipulación de compras
    public void agregarCompra(Venta venta) {
        this.compras.add(venta);
    }

    public void eliminarCompra(Venta venta) {
        this.compras.remove(venta);
    }

    public boolean tieneCompras() {
        return !this.compras.isEmpty();
    }

    public List<Venta> obtenerCompras() {
        return List.copyOf(this.compras);
    }

    // Métodos para manipulación de cupones
    public void agregarCupon(Cupon cupon) {
        this.cupones.add(cupon);
    }

    public void eliminarCupon(Cupon cupon) {
        this.cupones.remove(cupon);
    }

    public boolean tieneCupones() {
        return !this.cupones.isEmpty();
    }

    public List<Cupon> obtenerCupones() {
        return List.copyOf(this.cupones);
    }

    public void agregarLogUsuario(LogSistema log) {
        this.logsUsuario.add(log);
    }

    public void eliminarLogUsuario(LogSistema log) {
        this.logsUsuario.remove(log);
    }

    public boolean tieneLogsUsuario() {
        return !this.logsUsuario.isEmpty();
    }

    public List<LogSistema> obtenerLogsUsuario() {
        return List.copyOf(this.logsUsuario);
    }
    
    // Métodos para manipulación de codigos de verificacion
    public void agregarCodigoVerificacion(CodigoVerificacion codV) {
        this.codigosVerificacion.add(codV);
    }

    public void eliminarCodigoVerificacion(CodigoVerificacion codV) {
        this.codigosVerificacion.remove(codV);
    }

    public boolean tieneCodigosVerificacion() {
        return !this.codigosVerificacion.isEmpty();
    }

    public List<CodigoVerificacion> obtenerCodigosVerificacion() {
        return List.copyOf(this.codigosVerificacion);
    }
    
    
    
    
    
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", telefono=" + telefono +
                ", password='" + password + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", estaActivo=" + estaActivo +
                ", idiomaPreferido='" + idiomaPreferido + '\'' +
                '}';
    }
}