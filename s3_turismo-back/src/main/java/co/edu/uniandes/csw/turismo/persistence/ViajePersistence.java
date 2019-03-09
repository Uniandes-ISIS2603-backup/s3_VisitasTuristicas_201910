/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.persistence;

import co.edu.uniandes.csw.turismo.entities.ViajeEntity;
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
public class ViajePersistence {

    private static final Logger LOGGER = Logger.getLogger(PlanTuristicoPersistence.class.getName());
    @PersistenceContext(unitName = "turismoPU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param viajeEntity objeto viaje que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ViajeEntity create(ViajeEntity viajeEntity) {
        em.persist(viajeEntity);
        return viajeEntity;
    }

    /**
     * Busca si hay alguna editorial con el id que se envía de argumento
     *
     * @param viajeId: id correspondiente al viaje buscado.
     * @return un viaje.
     */
    public ViajeEntity find(Long viajeId) {
        return em.find(ViajeEntity.class, viajeId);
    }

    public List<ViajeEntity> findAll() {
        TypedQuery<ViajeEntity> query = em.createQuery("select u from ViajeEntity u", ViajeEntity.class);
        return query.getResultList();
    }

    /**
     * Actualiza un viaje.
     *
     * @param viajeEntity: el viaje que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un viaje con los cambios aplicados.
     */
    public ViajeEntity update(ViajeEntity viajeEntity) {
        LOGGER.log(Level.INFO, "Actualizando el viaje con id={0}", viajeEntity.getId());
        return em.merge(viajeEntity);
    }

    /**
     *
     * Borra un viaje de la base de datos recibiendo como argumento el id del
     * viaje
     *
     * @param viajesId: id correspondiente al viaje a borrar.
     */
    public void delete(Long viajesId) {
        LOGGER.log(Level.INFO, "Borrando el viaje con id={0}", viajesId);
        ViajeEntity bookEntity = em.find(ViajeEntity.class, viajesId);
        em.remove(bookEntity);
    }
}
