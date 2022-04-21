/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author Alexander
 */
public class TipoServicioStreaming {
    private int tipoServicioId;
    private String nombre;
    private String url_logo;
    private int maxUsuarios;
    
    public TipoServicioStreaming(){
        
    }

    public void setTipoServicioId(int tipoServicioId) {
        this.tipoServicioId = tipoServicioId;
    }

    public int getTipoServicioId() {
        return tipoServicioId;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setUrl_logo(String url_logo) {
        this.url_logo = url_logo;
    }

    public String getUrl_logo() {
        return url_logo;
    }

    public void setMaxUsuarios(int maxUsuarios) {
        this.maxUsuarios = maxUsuarios;
    }

    public int getMaxUsuarios() {
        return maxUsuarios;
    }
    
}
