/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.ValoracionEntity;
import co.edu.uniandes.csw.turismo.persistence.ValoracionPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author lm.rodriguezc2
 */

@Stateless
public class ValoracionLogic {
    
    @Inject
    private ValoracionPersistence persistence;
    
    public ValoracionEntity createValoracion(ValoracionEntity valoracion){
        
        //condición de la valoracion
        return valoracion;
    }
}
