/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author Alexander
 */
public class Cliente {
    private int clienteId;
    private String nombreCompleto;
    private String nombreUsuario;
    private String celular;
    
    //Atributos secundarios
    private int numTotalSuscripciones;
    
    public Cliente(){
        numTotalSuscripciones = 0;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCelular() {
        return celular;
    }

    //Métodos de los atributos secundarios
    public void setNumTotalSuscripciones(int numTotalSuscripciones) {
        this.numTotalSuscripciones = numTotalSuscripciones;
    }

    public int getNumTotalSuscripciones() {
        return numTotalSuscripciones;
    }
    
}
