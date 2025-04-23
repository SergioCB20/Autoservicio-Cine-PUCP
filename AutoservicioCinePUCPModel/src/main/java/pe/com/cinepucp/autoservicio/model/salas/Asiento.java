/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.model.salas;

/**
 *
 * @author Sergio
 */
public class Asiento {
    private int id;
    private Sala sala;
    private char fila;
    private int numero;
    private TipoAsiento tipo;

    public Asiento() {
    }

    public Asiento(char fila, int numero, TipoAsiento tipo) {
        this.fila = fila;
        this.numero = numero;
        this.tipo = tipo;
    }

    public Asiento(Sala sala, char fila, int numero, TipoAsiento tipo) {
        this.sala = sala;
        this.fila = fila;
        this.numero = numero;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public char getFila() {
        return fila;
    }

    public void setFila(char fila) {
        this.fila = fila;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public TipoAsiento getTipo() {
        return tipo;
    }

    public void setTipo(TipoAsiento tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Asiento{" +
                "id=" + id +
                ", sala=" + (sala != null ? sala.getId() : "null") + 
                ", fila=" + fila +
                ", numero=" + numero +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
