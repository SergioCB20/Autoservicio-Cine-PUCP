/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.mysql;

/**
 *
 * @author Sergio
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.com.cinepucp.autoservicio.config.DBManager;
import pe.com.cinepucp.autoservicio.dao.ICrud;

public abstract class BaseDAOImpl<T> implements ICrud<T> {
    protected abstract PreparedStatement comandoInsertar(Connection conn, T modelo) throws SQLException;
    protected abstract PreparedStatement comandoModificar(Connection conn, T modelo) throws SQLException;
    protected abstract PreparedStatement comandoEliminar(Connection conn, int id) throws SQLException;
    protected abstract PreparedStatement comandoBuscar(Connection conn, int id) throws SQLException;
    protected abstract PreparedStatement comandoListar(Connection conn) throws SQLException;
    
    protected abstract T mapearModelo(ResultSet rs) throws SQLException;
    
    @Override
    public int insertar(T modelo) {
        try (
            Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement cmd = this.comandoInsertar(conn, modelo);
        ) {
          
            try (ResultSet rs = cmd.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1); 
                }
                System.err.println("No se pudo obtener el ID generado");
                return -1;
            }
        }
        catch (SQLException e) {
            System.err.println("Error SQL durante la inserción: " + e.getMessage());
            throw new RuntimeException("No se pudo insertar el registro.", e);
        }
        catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al insertar el registro.", e);
        }
    }

    @Override
    public boolean modificar(T modelo) {
        try (
            Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement cmd = this.comandoModificar(conn, modelo);
        ) {
            
            try (ResultSet rs = cmd.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; 
                }
                return false;
            }
        }
        catch (SQLException e) {
            System.err.println("Error SQL durante la modificación: " + e.getMessage());
            throw new RuntimeException("No se pudo modificar el registro.", e);
        }
        catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al modificar el registro.", e);
        }
    }

    @Override
    public boolean eliminar(int id) {
        try (
            Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement cmd = this.comandoEliminar(conn, id);
        ) {
            
            try (ResultSet rs = cmd.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
                return false;
            }
        }
        catch (SQLException e) {
            System.err.println("Error SQL durante la eliminación: " + e.getMessage());
            throw new RuntimeException("No se pudo eliminar el registro.", e);
        }
        catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al eliminar el registro.", e);
        }
    }

    @Override
    public T buscar(int id) {
        try (
            Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement ps = this.comandoBuscar(conn, id);
        ) {
            ResultSet rs = ps.executeQuery();
            
            if (!rs.next()) {
                System.err.println("No se encontró el registro con id: " + id);
                return null;
            }
            
            return this.mapearModelo(rs);
        }
        catch (SQLException e) {
            System.err.println("Error SQL durante la búsqueda: " + e.getMessage());
            throw new RuntimeException("No se pudo buscar el registro.", e);
        }
        catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al buscar el registro.", e);
        }
    }

    @Override
    public List<T> listar() {
        try (
            Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement ps = this.comandoListar(conn);
        ) {
            ResultSet rs = ps.executeQuery();
            
            List<T> modelos = new ArrayList<>();
            while (rs.next()) {
                modelos.add(this.mapearModelo(rs));
            }
            
            return modelos;
        }
        catch (SQLException e) {
            System.err.println("Error SQL durante el listado: " + e.getMessage());
            throw new RuntimeException("No se pudo listar el registro.", e);
        }
        catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al listar los registros.", e);
        }
    }
}