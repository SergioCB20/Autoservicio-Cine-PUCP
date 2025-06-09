package pe.com.cinepucp.autoservicio.main;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Sergio
 */
import pe.com.cinepucp.autoservicio.main.cruds.AsientoCRUD;
import pe.com.cinepucp.autoservicio.main.cruds.BoletoCRUD;
import pe.com.cinepucp.autoservicio.main.cruds.SalaCRUD;
import pe.com.cinepucp.autoservicio.main.cruds.SesionCRUD;
import pe.com.cinepucp.autoservicio.main.cruds.CodigoVerificacionCRUD;
import pe.com.cinepucp.autoservicio.main.cruds.CuponCRUD;
import pe.com.cinepucp.autoservicio.main.cruds.GeneroCRUD;
import pe.com.cinepucp.autoservicio.main.cruds.PeliculaCRUD;
import pe.com.cinepucp.autoservicio.main.cruds.UsuarioCRUD;
import pe.com.cinepucp.autoservicio.main.cruds.LogSistemaCRUD;
import pe.com.cinepucp.autoservicio.main.cruds.VentaCRUD;

public class Main {

    public static void main(String[] args) {
        //SalaCRUD.ejecutarCRUDSala();
        //AsientoCRUD.ejecutarCRUDAsiento();
        //SesionCRUD.ejecutarCRUDSesion();
        //GeneroCRUD.ejecutarCRUDGenero();
        //CodigoVerificacionCRUD.ejecutarCRUDCodigoVerificacion(); 
        //UsuarioCRUD.ejecutarCRUDUsuario();
        //LogSistemaCRUD.ejecutarCRUDLogSistema();
        //PeliculaCRUD.ejecutarCRUDPelicula();
        //BoletoCRUD.ejecutarCRUDBoleto();
        VentaCRUD.ejecutarCRUDVenta();
        //CuponCRUD.ejecutarCRUDCupon();
    }
    

}
