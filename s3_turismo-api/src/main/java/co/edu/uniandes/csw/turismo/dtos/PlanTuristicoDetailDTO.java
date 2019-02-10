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
public class PlanTuristicoDetailDTO extends PlanTuristicoDTO implements Serializable{
    private List<PaisDTO> paises;
    //private List<ValoracionDTO> valoraciones;
    //private List<BlogDeViajeroDTO> blogs;
    
    public PlanTuristicoDetailDTO(){
        
    }

    /**
     * @return the paises
     */
    public List<PaisDTO> getPaises() {
        return paises;
    }

    /**
     * @param paises the paises to set
     */
    public void setPaises(List<PaisDTO> paises) {
        this.paises = paises;
    }
    
    
}
