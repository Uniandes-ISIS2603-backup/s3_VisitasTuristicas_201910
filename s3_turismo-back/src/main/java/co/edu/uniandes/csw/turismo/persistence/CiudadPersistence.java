/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.persistence;

import co.edu.uniandes.csw.turismo.entities.CiudadEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author dg.fonseca
 */

@Stateless
public class CiudadPersistence {
   
    
    
    @PersistenceContext(unitName="turismoPU")
    protected EntityManager em;
    
    
    public CiudadEntity create(CiudadEntity ciudadEntity)
    {
       em.persist(ciudadEntity);
       return ciudadEntity;
    }
    
}
