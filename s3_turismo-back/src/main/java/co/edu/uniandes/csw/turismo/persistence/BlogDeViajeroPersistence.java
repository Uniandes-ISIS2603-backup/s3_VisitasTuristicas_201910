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
import javax.persistence.TypedQuery;

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
    
      /**
     * Buscar un blog
     *
     * Busca si hay algun blog asociada a un plan y con un ID específico
     *
     * @param planId El ID del lan con respecto al cual se busca
     * @param blogId El ID del blog buscada
     * @return El blog encontrado o null. Nota: Si existe uno o más blogs
     * devuelve siempre la primera que encuentra
     */
    public BlogDeViajeroEntity find(Long planId, Long blogId) {
        LOGGER.log(Level.INFO, "Consultando el blog con id = {0} del plan con id = " + planId, blogId);
        TypedQuery<BlogDeViajeroEntity> q = em.createQuery("select p from BlogDeViajeroEntity p where (p.planTuristico.id = :planId) and (p.id = :blogId)", BlogDeViajeroEntity.class);
        q = q.setParameter("planId", planId);
        q = q.setParameter("blogId", blogId);
        List<BlogDeViajeroEntity> results = q.getResultList();
        BlogDeViajeroEntity blog = null;
        if (results == null) {
            blog = null;
        } else if (results.isEmpty()) {
            blog = null;
        } else if (results.size() >= 1) {
            blog = results.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar el blog con id = {0} del plan con id =" + planId, blogId);
        return blog;
    }
     /*
    *Retorna la lista de blogs de viajero
    *@return lista de blogs
    */
    public List<BlogDeViajeroEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los blogs de viajero");
        
        Query q = em.createQuery("select u from BlogDeViajeroEntity u ", BlogDeViajeroEntity.class);
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
    
    /**
     * Eliminar un blog
     *
     * Elimina un blog asociado al ID que recibe
     *
     * @param blogId El ID del blog que se desea borrar
     */
    public void delete(Long blogId) {
        LOGGER.log(Level.INFO, "Borrando blog con id = {0}", blogId);
        BlogDeViajeroEntity blogEntity = em.find(BlogDeViajeroEntity.class, blogId);
        em.remove(blogEntity);
        LOGGER.log(Level.INFO, "Saliendo de borrar el blog con id = {0}", blogId);
    }
}
