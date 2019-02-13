/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.persistence;

import co.edu.uniandes.csw.turismo.entities.ViajeroEntity;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */
@Stateless
public class ViajeroPersistence {
    
    @PersistenceContext (unitName="turismoPU")
    protected EntityManager em;
    
    private static final Logger LOGGER= Logger.getLogger (PlanTuristicoPersistence.class .getName());
    
    
    public ViajeroEntity create (ViajeroEntity viajeroEntity){
       em.persist(viajeroEntity);
       return viajeroEntity;
   }
    
    public ViajeroEntity find(Long viajerosId) {
        return em.find(ViajeroEntity.class, viajerosId);
    }
    
    public List<ViajeroEntity> findAll() {
        TypedQuery<ViajeroEntity> query = em.createQuery("select u from ViajeroEntity u", ViajeroEntity.class);
        return query.getResultList();
    }
}
