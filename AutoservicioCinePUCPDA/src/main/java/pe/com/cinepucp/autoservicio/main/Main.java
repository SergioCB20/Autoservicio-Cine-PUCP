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
import pe.com.cinepucp.autoservicio.main.cruds.SalaCRUD;

public class Main {

    public static void main(String[] args) {
        SalaCRUD.ejecutarCRUDSala();
        AsientoCRUD.ejecutarCRUDAsiento();
    }
    

}
