/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.persistence;

import co.edu.uniandes.csw.turismo.entities.ViajeEntity;
import java.util.List;
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
public class ViajePersistence{
    
      private static final Logger LOGGER = Logger.getLogger(PlanTuristicoPersistence.class.getName());
    @PersistenceContext(unitName = "turismoPU")
    protected EntityManager em;

    public ViajeEntity create(ViajeEntity viajeEntity) {
        em.persist(viajeEntity);
        return viajeEntity;
    }

    public ViajeEntity find(Long viajeId) {
        return em.find(ViajeEntity.class, viajeId);
    }
    
    public List<ViajeEntity> findAll(){
        TypedQuery<ViajeEntity> query= em.createQuery("select u from ViajeEntity u", ViajeEntity.class);
        return query.getResultList();
    }
}
