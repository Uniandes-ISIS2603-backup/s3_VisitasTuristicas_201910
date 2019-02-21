/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.BlogDeViajeroEntity;
import co.edu.uniandes.csw.turismo.persistence.BlogDeViajeroPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author lm.rodriguezc2
 */

@Stateless
public class BlogDeViajeroLogic {
      @Inject
    private BlogDeViajeroPersistence persistence;
    
    public BlogDeViajeroEntity createBlogDeViajero(BlogDeViajeroEntity blogDeViajero){
        
        //condición del blog de viajero
        return blogDeViajero;
    }
    
}
