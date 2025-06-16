/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl;

import java.util.List;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.IUsuarioBO;
import pe.com.cinepucp.autoservicio.dao.IUsuarioDAO;
import pe.com.cinepucp.autoservicio.model.auth.Usuario;
import pe.com.cinepucp.autoservicio.mysql.UsuarioDAOImpl;

/**
 *
 * @author Amira
 */
public class UsuarioBOImpl implements IUsuarioBO{
    
     private final IUsuarioDAO usuarioDAO;
     
     public UsuarioBOImpl(){
         usuarioDAO = new UsuarioDAOImpl();
     }
    @Override
    public void registrar(Usuario modelo) throws Exception {
        usuarioDAO.insertar(modelo);
    }

    @Override
    public void actualizar(Usuario modelo) throws Exception {
        usuarioDAO.modificar(modelo);
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
    
}
