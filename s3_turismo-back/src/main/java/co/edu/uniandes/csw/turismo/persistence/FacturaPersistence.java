/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.persistence;

import co.edu.uniandes.csw.turismo.entities.FacturaEntity;
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
public class FacturaPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(FacturaPersistence.class.getName());
    
    @PersistenceContext(unitName = "turismoPU")
    protected EntityManager em;
    
    public FacturaEntity create(FacturaEntity facturaEntity){
        em.persist(facturaEntity);
        return facturaEntity;
    }
    
    public FacturaEntity find(Long facturaEntityID, Long usuarioEntityId){
         LOGGER.log(Level.INFO, "Consultando el review con id = {0} del libro con id = " + usuarioEntityId, facturaEntityID);
        TypedQuery<FacturaEntity> q = em.createQuery("select p from FacturaEntity p where (p.viajero.id = :bookid) and (p.id = :reviewsId)", FacturaEntity.class);
        q.setParameter("bookid", usuarioEntityId);
        q.setParameter("reviewsId", facturaEntityID);
        List<FacturaEntity> results = q.getResultList();
        FacturaEntity review = null;
        if (results == null) {
            review = null;
        } else if (results.isEmpty()) {
            review = null;
        } else if (results.size() >= 1) {
            review = results.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar el review con id = {0} del libro con id =" + usuarioEntityId, facturaEntityID);
        return review;    }
    
    public List<FacturaEntity> findAll(){
        TypedQuery<FacturaEntity> query = em.createQuery("select u from FacturaEntity u", FacturaEntity.class);
        return query.getResultList();
    }
    
    public FacturaEntity update(FacturaEntity bookEntity) {
        return em.merge(bookEntity);
    }
    
    public void delete(Long FacturaId) {
        LOGGER.log(Level.INFO, "Borrando el libro con id={0}", FacturaId);
        FacturaEntity bookEntity = em.find(FacturaEntity.class, FacturaId);
        em.remove(bookEntity);
    }
}
