/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl;

/**
 *
 * @author Sergio
 */

import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.IUsuarioBO;
import pe.com.cinepucp.autoservicio.model.auth.Usuario;
import pe.com.cinepucp.autoservicio.utils.PasswordHasher;

import java.util.List;
import java.util.Objects;
import pe.com.cinepucp.autoservicio.dao.IUsuarioDAO;
import pe.com.cinepucp.autoservicio.mysql.UsuarioDAOImpl;

public class UsuarioBOImpl implements IUsuarioBO {

    private final IUsuarioDAO usuarioDAO;

    public UsuarioBOImpl() {
        usuarioDAO = new UsuarioDAOImpl();
    }

    private void validarUsuario(Usuario usuario, boolean isNew) throws Exception {
        Objects.requireNonNull(usuario, "El usuario no puede ser nulo.");

        if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre del usuario es requerido.");
        }
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new Exception("El email del usuario es requerido.");
        }
        if (!usuario.getEmail().matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
             throw new Exception("El formato del email no es válido.");
        }

        if (isNew && (usuario.getPassword() == null || usuario.getPassword().trim().isEmpty())) {
            throw new Exception("La contraseña es requerida para un nuevo usuario.");
        }
        // Si es un nuevo usuario, verifica si el email ya existe
        if (isNew && usuarioDAO.buscarPorEmail(usuario.getEmail()) != null) {
            throw new Exception("El email ya está registrado.");
        }

        if (usuario.getTelefono() == null || usuario.getTelefono().trim().isEmpty()) {
            throw new Exception("El teléfono del usuario es requerido.");
        }
    }

    @Override
    public void registrar(Usuario usuario) throws Exception {
        validarUsuario(usuario, true);
        usuarioDAO.insertar(usuario);
    }

    @Override
    public void actualizar(Usuario usuario) throws Exception {
        validarUsuario(usuario, false);
        usuarioDAO.modificar(usuario);
    }

    @Override
    public List<Usuario> listar() {
        return usuarioDAO.listar();
    }

    @Override
    public void eliminar(int id) {
        usuarioDAO.eliminar(id);
    }

    @Override
    public Usuario buscarPorId(int id) {
        return usuarioDAO.buscar(id);
    }

    @Override
    public Usuario autenticarUsuario(String email, String password) throws Exception {
        Usuario usuario = usuarioDAO.buscarPorEmail(email);

        if (usuario == null) {
            // Para seguridad, no especifiques si el usuario no existe o la contraseña es incorrecta
            throw new Exception("Credenciales inválidas.");
        }
        
        if (!PasswordHasher.checkPassword(password, usuario.getPassword())) {
            throw new Exception("Credenciales inválidas.");
        }

        // Si llegó hasta aquí, las credenciales son válidas
        return usuario;
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return null;
        }
        return usuarioDAO.buscarPorEmail(email);
    }
}
