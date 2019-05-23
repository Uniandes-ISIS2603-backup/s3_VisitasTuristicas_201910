/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.persistence;

import co.edu.uniandes.csw.turismo.entities.ValoracionEntity;
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
public class ValoracionPersistence {

    private static final Logger LOGGER = Logger.getLogger(ValoracionPersistence.class.getName());

    @PersistenceContext(unitName = "turismoPU")
    protected EntityManager em;

    /*
    *Crea una nueva valoracion
    *@param valoracionEntity
     */
    public ValoracionEntity create(ValoracionEntity valoracionEntity) {
        LOGGER.log(Level.INFO, "Creando una nueva valoracion");
        em.persist(valoracionEntity);
        LOGGER.log(Level.INFO, "Valoracion creada");
        return valoracionEntity;
        
    }
    
      /**
     * Buscar una valoracion
     *
     * Busca si hay alguna valoracion asociada a un plan y con un ID específico
     *
     * @param planId El ID del lan con respecto al cual se busca
     * @param valoracionId El ID de la valoracion buscada
     * @return La valoracion encontrada o null. Nota: Si existe una o más valoraciones
     * devuelve siempre la primera que encuentra
     */
    public ValoracionEntity find(Long planId, Long valoracionId) {
        TypedQuery<ValoracionEntity> q = em.createQuery("select p from ValoracionEntity p where (p.planTuristico.id = :planId) and (p.id = :valoracionId)", ValoracionEntity.class);
        q.setParameter("planId", planId);
        q.setParameter("valoracionId", valoracionId);
        List<ValoracionEntity> results = q.getResultList();
        ValoracionEntity valoracion = null;
         if (results.size() >= 1) {
            valoracion = results.get(0);
        }
        return valoracion;
    }
     /*
    *Retorna la lista de valoraciones
    *@return lista de valoraciones
    */
    public List<ValoracionEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las valoraciones");
        
        Query q = em.createQuery("SELECT U FROM ValoracionEntity U ", ValoracionEntity.class);
        return q.getResultList();
    }
         /*
    *Actualiza y retorna una valoracion
    *@return valoracionEntity
    */
    public ValoracionEntity update(ValoracionEntity valoracionEntity) {
        LOGGER.log(Level.INFO, "Actualizando la valoracion con id={0}", valoracionEntity.getId());
        return em.merge(valoracionEntity);
        
        
    }
    
    /**
     * Eliminar una valoracion
     *
     * Elimina la valoracion asociada al ID que recibe
     *
     * @param valoracionId El ID de la valoracion que se desea borrar
     */
    public void delete(Long valoracionId) {
        LOGGER.log(Level.INFO, "Borrando valoracion con id = {0}", valoracionId);
        ValoracionEntity valoracionEntity = em.find(ValoracionEntity.class, valoracionId);
        em.remove(valoracionEntity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la valoracion con id = {0}", valoracionId);
    }
}
