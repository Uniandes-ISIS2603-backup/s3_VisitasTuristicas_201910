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

    /**
     * Crear una reseña
     *
     * Crea una nueva reseña con la información recibida en la entidad.
     *
     * @param rarjetaDeCreditoEntity La entidad que representa la nueva reseña
     * @return La entidad creada
     */
    public TarjetaDeCreditoEntity create(TarjetaDeCreditoEntity rarjetaDeCreditoEntity) {
        LOGGER.log(Level.INFO, "Creando un rarjetaDeCredito nuevo");
        em.persist(rarjetaDeCreditoEntity);
        LOGGER.log(Level.INFO, "TarjetaDeCredito creado");
        return rarjetaDeCreditoEntity;
    }

    /**
     * Actualizar una reseña
     *
     * Actualiza la entidad que recibe en la base de datos
     *
     * @param rarjetaDeCreditoEntity La entidad actualizada que se desea guardar
     * @return La entidad resultante luego de la acutalización
     */
    public TarjetaDeCreditoEntity update(TarjetaDeCreditoEntity rarjetaDeCreditoEntity) {
        LOGGER.log(Level.INFO, "Actualizando rarjetaDeCredito con id = {0}", rarjetaDeCreditoEntity.getId());
        return em.merge(rarjetaDeCreditoEntity);
    }

    /**
     * Eliminar una reseña
     *
     * Elimina la reseña asociada al ID que recibe
     *
     * @param rarjetaDeCreditosId El ID de la reseña que se desea borrar
     */
    public void delete(Long rarjetaDeCreditosId) {
        LOGGER.log(Level.INFO, "Borrando rarjetaDeCredito con id = {0}", rarjetaDeCreditosId);
        TarjetaDeCreditoEntity rarjetaDeCreditoEntity = em.find(TarjetaDeCreditoEntity.class, rarjetaDeCreditosId);
        em.remove(rarjetaDeCreditoEntity);
        LOGGER.log(Level.INFO, "Saliendo de borrar El rarjetaDeCredito con id = {0}", rarjetaDeCreditosId);
    }

    /**
     * Buscar una reseña
     *
     * Busca si hay alguna reseña asociada a un libro y con un ID específico
     *
     * @param viajerosId El ID del libro con respecto al cual se busca
     * @param rarjetaDeCreditosId El ID de la reseña buscada
     * @return La reseña encontrada o null. Nota: Si existe una o más reseñas
     * devuelve siempre la primera que encuentra
     */
    public TarjetaDeCreditoEntity find(Long viajerosId, Long rarjetaDeCreditosId) {
        LOGGER.log(Level.INFO, "Consultando el rarjetaDeCredito con id = {0} del libro con id = " + viajerosId, rarjetaDeCreditosId);
        TypedQuery<TarjetaDeCreditoEntity> q = em.createQuery("select p from TarjetaDeCreditoEntity p where (p.viajero.id = :viajeroid) and (p.id = :rarjetaDeCreditosId)", TarjetaDeCreditoEntity.class);
        q.setParameter("viajeroid", viajerosId);
        q.setParameter("rarjetaDeCreditosId", rarjetaDeCreditosId);
        List<TarjetaDeCreditoEntity> results = q.getResultList();
        TarjetaDeCreditoEntity rarjetaDeCredito = null;
        if (results == null) {
            rarjetaDeCredito = null;
        } else if (results.isEmpty()) {
            rarjetaDeCredito = null;
        } else if (results.size() >= 1) {
            rarjetaDeCredito = results.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar el rarjetaDeCredito con id = {0} del libro con id =" + viajerosId, rarjetaDeCreditosId);
        return rarjetaDeCredito;
    }
}
