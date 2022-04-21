/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author Alexander
 */
public class Plan {
    private int planId;
    private int tipoServicioId;
    private int tiempoMeses;
    private double costo;
    
    //Atributos secundarios
    private String nombrePlataformaAsociada;
    private int numCuentasAsociadas;
    
    public Plan(){
        numCuentasAsociadas = 0;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public int getPlanId() {
        return planId;
    }

    public void setTipoServicioId(int tipoServicioId) {
        this.tipoServicioId = tipoServicioId;
    }
    
    public int getTipoServicioId() {
        return tipoServicioId;
    }

    public void setTiempoMeses(int tiempoMeses) {
        this.tiempoMeses = tiempoMeses;
    }

    public int getTiempoMeses() {
        return tiempoMeses;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getCosto() {
        return costo;
    }

    public void setNumCuentasAsociadas(int numCuentasAsociadas) {
        this.numCuentasAsociadas = numCuentasAsociadas;
    }

    public int getNumCuentasAsociadas() {
        return numCuentasAsociadas;
    }

    public void setNombrePlataformaAsociada(String nombrePlataformaAsociada) {
        this.nombrePlataformaAsociada = nombrePlataformaAsociada;
    }

    public String getNombrePlataformaAsociada() {
        return nombrePlataformaAsociada;
    }
}
