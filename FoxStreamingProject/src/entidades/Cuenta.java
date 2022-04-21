/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author Alexander
 */
public class Cuenta {
    private int cuentaId;
    private int planId;
    private String correo;
    private String contrasena;
    private boolean estaActiva;
    private int numActualUsuarios;
    
    public Cuenta(){
        
    }

    public void setCuentaId(int cuentaId) {
        this.cuentaId = cuentaId;
    }

    public int getCuentaId() {
        return cuentaId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public int getPlanId() {
        return planId;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setNumActualUsuarios(int numActualUsuarios) {
        this.numActualUsuarios = numActualUsuarios;
    }

    public int getNumActualUsuarios() {
        return numActualUsuarios;
    }

    public void setEstaActiva(boolean estaActiva) {
        this.estaActiva = estaActiva;
    }
    
    public boolean getEstaActiva(){
        return estaActiva;
    }
    
}
