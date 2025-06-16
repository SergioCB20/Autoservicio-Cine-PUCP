package pe.com.cinepucp.autoservicio.dao;

import pe.com.cinepucp.autoservicio.model.auth.Usuario;

/**
 *
 * @author User
 */
public interface IUsuarioDAO extends ICrud<Usuario>{
    Usuario buscarPorEmail(String email);
}
