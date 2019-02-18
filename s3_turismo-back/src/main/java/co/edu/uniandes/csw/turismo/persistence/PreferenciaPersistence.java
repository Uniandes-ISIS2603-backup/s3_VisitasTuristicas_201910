/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.persistence;

import co.edu.uniandes.csw.turismo.entities.PreferenciaEntity;
import java.util.List;
import java.util.logging.Level;
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
public class PreferenciaPersistence {
    @PersistenceContext (unitName="turismoPU")
    protected EntityManager em;
    
    private static final Logger LOGGER= Logger.getLogger (PlanTuristicoPersistence.class .getName());
    
    public PreferenciaEntity create (PreferenciaEntity preferenciaEntity){
        LOGGER.log(Level.INFO, "Creando una preferencia nueva");
       em.persist(preferenciaEntity);
       return preferenciaEntity;
   }
    
    public PreferenciaEntity find(Long preferenciaId) {
        LOGGER.log(Level.INFO, "Consultando la preferencia con id = {0}", preferenciaId );
        return em.find(PreferenciaEntity.class, preferenciaId);
    }
    
    public List<PreferenciaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos");
        TypedQuery<PreferenciaEntity> query = em.createQuery("select u from PreferenciaEntity u", PreferenciaEntity.class);
        return query.getResultList();
    }
    
    public void delete(Long preferenciaId) {
         LOGGER.log(Level.INFO, "Borrando preferencia con id = {0}", preferenciaId);
        PreferenciaEntity viajero = em.find(PreferenciaEntity.class, preferenciaId);
        em.remove(viajero);
    }
    
    public PreferenciaEntity update(PreferenciaEntity preferenciaEntity) {
        LOGGER.log(Level.INFO, "Actualizando preferencia con id = {0}", preferenciaEntity.getId());
        return em.merge(preferenciaEntity);
    }
    
}
