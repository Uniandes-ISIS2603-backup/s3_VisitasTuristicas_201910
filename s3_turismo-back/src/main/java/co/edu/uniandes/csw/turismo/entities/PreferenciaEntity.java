/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class PreferenciaEntity extends BaseEntity implements Serializable{
    
    private String nombrePreferencia;
    
    @PodamExclude
    @javax.persistence.ManyToOne
    ViajeroEntity viajero;

    public PreferenciaEntity() {
        
    }
    public ViajeroEntity getViajero() {
        return viajero;
    }

    public void setViajero(ViajeroEntity viajero) {
        this.viajero = viajero;
    }
    
    public void setNombrePreferencia(String pN) {
        nombrePreferencia = pN;
    }
    public String getNombrePreferencia() {
        return nombrePreferencia;
    }
}
