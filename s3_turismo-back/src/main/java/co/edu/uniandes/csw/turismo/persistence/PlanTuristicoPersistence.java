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
import javax.persistence.Query;
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

    /**
     * Método para persisitir el planTuristico en la base de datos.
     *
     * @param planTuristicoEntity objeto editorial que se creará en la base de
     * datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public PlanTuristicoEntity create(PlanTuristicoEntity planTuristicoEntity) {
        em.persist(planTuristicoEntity);
        return planTuristicoEntity;
    }

    public PlanTuristicoEntity find(Long planTuristicoId) {
        return em.find(PlanTuristicoEntity.class, planTuristicoId);
    }

    /**
     * Busca si hay alguna editorial con el nombre que se envía de argumento
     *
     * @param pNombrePlan: Nombre del plan turistico que se está buscando
     * @return null si no existe ningun plan turistico con el nombre del
     * argumento. Si existe alguno devuelve la primero.
     */
    public PlanTuristicoEntity findByName(String pNombrePlan) {
        TypedQuery<PlanTuristicoEntity> query = em.createQuery("Select e From PlanTuristicoEntity e where e.nombrePlan = :nombrePlan", PlanTuristicoEntity.class);
        query = query.setParameter("nombrePlan", pNombrePlan);
        List<PlanTuristicoEntity> sameName = query.getResultList();

        PlanTuristicoEntity result;

        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        return result;
    }

    public List<PlanTuristicoEntity> findAll() {
        Query query = em.createQuery("select u from PlanTuristicoEntity u");
        return query.getResultList();
    }

    /**
     *
     * Borra una editorial de la base de datos recibiendo como argumento el id
     * de la editorial
     *
     * @param planTuristicoId: id correspondiente a la editorial a borrar.
     */
    public void delete(Long planTuristicoId) {
        LOGGER.log(Level.INFO, "Borrando planTuristico con id = {0}", planTuristicoId);
        PlanTuristicoEntity planTuristico = em.find(PlanTuristicoEntity.class, planTuristicoId);
        em.remove(planTuristico);

    }

    /**
     * Actualiza un plan turistico.
     *
     * @param planTuristicoEntity: la editorial que viene con los nuevos
     * cambios. Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso
     * del método update.
     * @return un plan turistico con los cambios aplicados.
     */
    public PlanTuristicoEntity update(PlanTuristicoEntity planTuristicoEntity) {
        LOGGER.log(Level.INFO, "Actualizando planTuristico con id = {0}", planTuristicoEntity.getId());
        return em.merge(planTuristicoEntity);
    }

}
