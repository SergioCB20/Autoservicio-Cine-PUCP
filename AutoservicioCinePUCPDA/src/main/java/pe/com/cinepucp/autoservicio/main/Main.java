package pe.com.cinepucp.autoservicio.main;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Sergio
 */
import pe.com.cinepucp.autoservicio.mysql.*;
import pe.com.cinepucp.autoservicio.model.salas.*;
public class Main {
    public static void main(String[] args){
        SalaDAOImpl salaDAO = new SalaDAOImpl();
        AsientoDAOImpl asientoDAO = new AsientoDAOImpl();
        Asiento asiento = new Asiento();
        Sala sala = new Sala();
        sala.setNombre("Sala 2");
        sala.setTipoSala(TipoSala.ESTANDAR);
        sala.setCapacidad(50);
        int idSala = salaDAO.insertar(sala);
        System.out.println("Sala con id " + idSala + " creada");
        sala.setId(idSala);
        asiento.setNumero(1);
        asiento.setSala(sala);
        asiento.setFila('H');
        asiento.setTipo(TipoAsiento.VIP);
        int idAsiento = asientoDAO.insertar(asiento);
        System.out.println("Asiento con id " + idAsiento + " creada");
    }
}
