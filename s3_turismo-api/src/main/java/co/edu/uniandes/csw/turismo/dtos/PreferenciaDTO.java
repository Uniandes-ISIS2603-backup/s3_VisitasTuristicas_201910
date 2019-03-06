/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.PreferenciaEntity;
import java.io.Serializable;
/**
 *
 * @author estudiante
 */
public class PreferenciaDTO implements Serializable {
    
    private Long idPreferencia;
    private String nombrePreferencia;

    public PreferenciaDTO() {
        
    }
    
    public Long getIdPreferencia() {
        return idPreferencia;
    }

    public String getNombrePreferencia() {
        return nombrePreferencia;
    }

    public void setNombrePreferencia(String nombrePreferencia) {
        this.nombrePreferencia = nombrePreferencia;
    }
    
    public PreferenciaEntity toEntity() {
        PreferenciaEntity nuevo = new PreferenciaEntity();
        nuevo.setNombrePreferencia(nombrePreferencia);
        return nuevo;
    }
    
    public PreferenciaDTO(PreferenciaEntity ent) {
        this.nombrePreferencia = ent.getNombrePreferencia();
    }
}
