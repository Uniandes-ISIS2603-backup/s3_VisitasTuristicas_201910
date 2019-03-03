/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.persistence;

import co.edu.uniandes.csw.turismo.entities.TarjetaDeCreditoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
        private static final Logger LOGGER = Logger.getLogger(TarjetaDeCreditoPersistence.class.getName());
    
    @PersistenceContext(unitName = "turismoPU")
    protected EntityManager em;
    
    public TarjetaDeCreditoEntity create(TarjetaDeCreditoEntity facturaEntity){
        em.persist(facturaEntity);
        return facturaEntity;
    }
    
    public TarjetaDeCreditoEntity find(Long facturaEntityID){
        return em.find(TarjetaDeCreditoEntity.class, facturaEntityID);
    }
    
    public List<TarjetaDeCreditoEntity> findAll(){
        TypedQuery<TarjetaDeCreditoEntity> query = em.createQuery("select u from TarjetaDeCreditoEntity u", TarjetaDeCreditoEntity.class);
        return query.getResultList();
    }
    
    public TarjetaDeCreditoEntity update(TarjetaDeCreditoEntity bookEntity) {
        return em.merge(bookEntity);
    }
    
    public void delete(Long TarjetaDeCreditoId) {
        LOGGER.log(Level.INFO, "Borrando el libro con id={0}", TarjetaDeCreditoId);
        TarjetaDeCreditoEntity bookEntity = em.find(TarjetaDeCreditoEntity.class, TarjetaDeCreditoId);
        em.remove(bookEntity);
    }
    
    public TarjetaDeCreditoEntity getByNumber(String number)
    {
        TypedQuery<TarjetaDeCreditoEntity> query = em.createQuery("select u from TarjetaDeCreditoEntity u where u.numero = :numero", TarjetaDeCreditoEntity.class);
        query = query.setParameter("numero", number);
        List<TarjetaDeCreditoEntity> list = query.getResultList();
        if(list == null)
        {
            return null;
        }else if(list.isEmpty())
        {
            return null;
        }else
        {
            return list.get(0);
        }
          
    }
}
