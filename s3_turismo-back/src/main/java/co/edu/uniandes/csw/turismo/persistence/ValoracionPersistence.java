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
    
     /*
    *Busca una valoracion con el id indicado
    *@return ValoracionEntity
    */
    public ValoracionEntity find(Long valoracionId) {
        LOGGER.log(Level.INFO, "Consultando la valoracion con id={0}", valoracionId);
        return em.find(ValoracionEntity.class, valoracionId);
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
}
