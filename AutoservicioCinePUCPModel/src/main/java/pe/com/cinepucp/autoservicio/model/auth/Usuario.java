package pe.com.cinepucp.autoservicio.model.auth;

import java.time.LocalDate;

public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private int telefono;
    private String password;
    private LocalDate fechaRegistro;
    private boolean estaActivo
    private String idiomaPreferido;

    public Usuario() {
    }

    public Usuario(String nombre, String email, int telefono, String password,
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

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
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
