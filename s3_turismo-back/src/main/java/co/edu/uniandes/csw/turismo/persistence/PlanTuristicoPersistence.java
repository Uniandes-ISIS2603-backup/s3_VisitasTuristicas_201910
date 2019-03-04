/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.persistence;

import co.edu.uniandes.csw.turismo.entities.PlanTuristicoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Christer Osorio
 */
@Stateless
public class PlanTuristicoPersistence {

    private static final Logger LOGGER = Logger.getLogger(PlanTuristicoPersistence.class.getName());
    @PersistenceContext(unitName = "turismoPU")
    protected EntityManager em;

    public PlanTuristicoEntity create(PlanTuristicoEntity planTuristicoEntity) {
        em.persist(planTuristicoEntity);
        return planTuristicoEntity;
    }

    public PlanTuristicoEntity find(Long planTuristicoId) {
        return em.find(PlanTuristicoEntity.class, planTuristicoId);
    }
    public PlanTuristicoEntity findByName(String pNombrePlan) {
         TypedQuery<PlanTuristicoEntity> query= em.createQuery("Select e From PlanTuristicoEntity e where e.nombrePlan = :nombrePlan", PlanTuristicoEntity.class);
         query= query.setParameter("nombrePlan", pNombrePlan);
         List <PlanTuristicoEntity> sameName= query.getResultList();
         
         PlanTuristicoEntity result;
        
        if(sameName==null){
            result=null;
        }
        else if(sameName.isEmpty())
        {
            result=null;
        }
        else{
            result=sameName.get(0);
        }
        return result;
    }
    
    public List<PlanTuristicoEntity> findAll(){
        TypedQuery<PlanTuristicoEntity> query= em.createQuery("select u from PlanTuristicoEntity u", PlanTuristicoEntity.class);
        return query.getResultList();
    }
    
    public void delete(Long planTuristicoId) {
         LOGGER.log(Level.INFO, "Borrando planTuristico con id = {0}", planTuristicoId);
        PlanTuristicoEntity planTuristico = em.find(PlanTuristicoEntity.class, planTuristicoId);
        em.remove(planTuristico);
        
    }
    
    public PlanTuristicoEntity update(PlanTuristicoEntity planTuristicoEntity) {
        LOGGER.log(Level.INFO, "Actualizando planTuristico con id = {0}", planTuristicoEntity.getId());
        return em.merge(planTuristicoEntity);
    }

}
