/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.ViajeroEntity;
import java.io.Serializable;
import java.util.List;
/**
 *
 * @author estudiante
 */
public class ViajeroDetailDTO extends ViajeroDTO implements Serializable {
    private List<PreferenciaDTO> preferencias;
    
    public ViajeroDetailDTO() {
        super();
    }
    
    public List<PreferenciaDTO> getPreferencias() {
        return preferencias;
    }
    
    public void setPreferencias(List<PreferenciaDTO> nuevo) {
        preferencias = nuevo;
    }
    
    public ViajeroDetailDTO(ViajeroEntity ent) {
        super(ent);
        if(ent != null) {
            return;
        }
    }
    
    public ViajeroEntity toEntity() {
        return new ViajeroEntity();
    }
}
