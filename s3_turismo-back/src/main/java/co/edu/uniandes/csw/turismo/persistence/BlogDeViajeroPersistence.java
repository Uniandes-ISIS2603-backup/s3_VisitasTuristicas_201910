/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.persistence;

import co.edu.uniandes.csw.turismo.entities.BlogDeViajeroEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author lm.rodriguezc2
 */
@Stateless
public class BlogDeViajeroPersistence {

    private static final Logger LOGGER = Logger.getLogger(BlogDeViajeroPersistence.class.getName());

    @PersistenceContext(unitName = "turismoPU")
    protected EntityManager em;
    
    /*
    *Crea un nuevo blog de viajero
    *@param blogDeViajeroEntity
     */
    public BlogDeViajeroEntity create(BlogDeViajeroEntity blogDeViajeroEntity) {
        LOGGER.log(Level.INFO, "Creando un nuevo blog de viajero");
        em.persist(blogDeViajeroEntity);
        LOGGER.log(Level.INFO, "Blog de viajero creado");
        return blogDeViajeroEntity;
    }
    
     /*
    *Busca un blog de viajero con el id indicado
    *@return BlogDeViajeroEntity
    */
    public BlogDeViajeroEntity find(Long blogDeViajeroId) {
        LOGGER.log(Level.INFO, "Consultando el blog con id={0}", blogDeViajeroId);
        return em.find(BlogDeViajeroEntity.class, blogDeViajeroId);
    }
     /*
    *Retorna la lista de blogs de viajero
    *@return lista de blogs
    */
    public List<BlogDeViajeroEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los blogs de viajero");
        
        Query q = em.createQuery("SELECT U FROM BlogDeViajeroEntity U ", BlogDeViajeroEntity.class);
        return q.getResultList();
    }
     /*
    *Actualiza y retorna un blog de viajero
    *@return blogEntity
    */
    public BlogDeViajeroEntity update(BlogDeViajeroEntity blogEntity) {
        LOGGER.log(Level.INFO, "Actualizando el blog de viajero con id={0}", blogEntity.getId());
        return em.merge(blogEntity);
        
    }
}
