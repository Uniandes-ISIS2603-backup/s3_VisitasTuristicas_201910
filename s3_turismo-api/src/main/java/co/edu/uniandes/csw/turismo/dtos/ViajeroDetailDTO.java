/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import java.io.Serializable;
import java.util.List;
/**
 *
 * @author estudiante
 */
public class ViajeroDetailDTO implements Serializable {
    private List<PreferenciaDTO> preferencias;
    
    public ViajeroDetailDTO() {
        
    }
    
    public List<PreferenciaDTO> getPreferencias() {
        return preferencias;
    }
    
    public void setPreferencias(List<PreferenciaDTO> nuevo) {
        preferencias = nuevo;
    }
}
