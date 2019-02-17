/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.persistence;

import co.edu.uniandes.csw.turismo.entities.TarjetaDeCreditoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jd.castrellon
 */

@Stateless
public class TarjetaDeCreditoPersistence {
    
    @PersistenceContext(unitName = "turismoPU")
    protected EntityManager em;
    
    public TarjetaDeCreditoEntity create(TarjetaDeCreditoEntity tarjetaDeCreditoEntity){
        em.persist(tarjetaDeCreditoEntity);
        return tarjetaDeCreditoEntity;
    }
    
    public TarjetaDeCreditoEntity find(Long tarjetaDeCréditoID){
        return em.find(TarjetaDeCreditoEntity.class, tarjetaDeCréditoID);
    }
    
    public List<TarjetaDeCreditoEntity> findAll(){
        TypedQuery<TarjetaDeCreditoEntity> query = em.createQuery("select u from TarjetaDeCreditoEntity u", TarjetaDeCreditoEntity.class);
        return query.getResultList();
    }
}
