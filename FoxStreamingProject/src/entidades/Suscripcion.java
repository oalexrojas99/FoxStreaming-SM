/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.GregorianCalendar;

/**
 *
 * @author Alexander
 */
public class Suscripcion {
    private int sucripcionID;
    private int clienteID;
    private int cuentaID;
    private GregorianCalendar fechaInicio;
    private boolean estaActiva;
    private double pagoEfectuado;

    //Atributos secundarios
    private String plataformaStreamingAsociada;
    private String correoCuentaAsociada;
    private String contrasenaCuentaAsociada;
    private GregorianCalendar fechaFin;
    private String nombreCliente;
    
    public Suscripcion(){
        
    }

    public void setSucripcionID(int sucripcionID) {
        this.sucripcionID = sucripcionID;
    }

    public int getSucripcionID() {
        return sucripcionID;
    }

    public void setCuentaID(int cuentaID) {
        this.cuentaID = cuentaID;
    }

    public int getCuentaID() {
        return cuentaID;
    }

    public void setFechaInicio(GregorianCalendar fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    public String getFechaInicio() {
        
        String anio = fechaInicio.get(fechaInicio.YEAR) + "";
        
        String mes;
        
        if(fechaInicio.get(fechaInicio.MONTH) < 10){
            mes = "0" + fechaInicio.get(fechaInicio.MONTH);
        } else{
            mes = fechaInicio.get(fechaInicio.MONTH) + "";
        }
        
        String dia = fechaInicio.get(fechaInicio.DAY_OF_MONTH) + "";
        
        return dia + "-" + mes + "-" + anio;
    }

    public void setPagoEfectuado(double pagoEfectuado) {
        this.pagoEfectuado = pagoEfectuado;
    }

    public double getPagoEfectuado() {
        return pagoEfectuado;
    }

    public void setEstaActiva(boolean estaActiva) {
        this.estaActiva = estaActiva;
    }
    
    public boolean getEstaActiva(){
        return estaActiva;
    }
    
    public void setClienteID(int clienteID) {
        this.clienteID = clienteID;
    }

    public int getClienteID() {
        return clienteID;
    }

    public void setContrasenaCuentaAsociada(String contrasenaCuentaAsociada) {
        this.contrasenaCuentaAsociada = contrasenaCuentaAsociada;
    }

    public String getContrasenaCuentaAsociada() {
        return contrasenaCuentaAsociada;
    }

    public void setCorreoCuentaAsociada(String correoCuentaAsociada) {
        this.correoCuentaAsociada = correoCuentaAsociada;
    }

    public String getCorreoCuentaAsociada() {
        return correoCuentaAsociada;
    }

    public void setPlataformaStreamingAsociada(String plataformaStreamingAsociada) {
        this.plataformaStreamingAsociada = plataformaStreamingAsociada;
    }

    public String getPlataformaStreamingAsociada() {
        return plataformaStreamingAsociada;
    }

    public void setFechaFin(GregorianCalendar fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFechaFin() {
        
        String anio = fechaFin.get(fechaFin.YEAR) + "";
        
        String mes;
        
        if(fechaFin.get(fechaFin.MONTH) < 10){
            mes = "0" + fechaFin.get(fechaFin.MONTH);
        } else{
            mes = fechaFin.get(fechaFin.MONTH) + "";
        }
        
        String dia = fechaFin.get(fechaFin.DAY_OF_MONTH) + "";
        
        return dia + "-" + mes + "-" + anio;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }
    
}
