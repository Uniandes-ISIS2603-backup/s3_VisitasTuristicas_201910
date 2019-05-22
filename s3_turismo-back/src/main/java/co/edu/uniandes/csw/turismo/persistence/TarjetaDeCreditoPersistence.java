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
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author jd.castrellon
 */

@Stateless
public class TarjetaDeCreditoPersistence {
    
private static final Logger LOGGER = Logger.getLogger(TarjetaDeCreditoPersistence.class.getName());



    
    @PersistenceContext(unitName="turismoPU")
    protected EntityManager em;
    
    /*
    *Crea un nuevo sitio turistico
    *@param sitioEntity
    */
    public TarjetaDeCreditoEntity create(TarjetaDeCreditoEntity sitioEntity)
    {
       LOGGER.log(Level.INFO, "Creando un sitio turistico nuevo");
       em.persist(sitioEntity);
       LOGGER.log(Level.INFO, "SitioTuristio creado");
       return sitioEntity;
    }
    
    
    /*
    *Busca un sitio turistico con el id dado
    *@return TarjetaDeCreditoEntity
    */
 public TarjetaDeCreditoEntity find(Long ciudadId, Long sitioId) {
        LOGGER.log(Level.INFO, "Consultando el sitioTuristico con id = {0} del libro con id = " + ciudadId, sitioId);
        TypedQuery<TarjetaDeCreditoEntity> q = em.createQuery("select p from TarjetaDeCreditoEntity p where (p.viajero.id = :ciudadId) and (p.id = :sitioId)", TarjetaDeCreditoEntity.class);
        q.setParameter("ciudadId", ciudadId);
        q.setParameter("sitioId", sitioId);
        List<TarjetaDeCreditoEntity> results = q.getResultList();
        TarjetaDeCreditoEntity sitioTuristico = null;
        if (results == null) {
            sitioTuristico = null;
        } else if (results.isEmpty()) {
            sitioTuristico = null;
        } else if (results.size() >= 1) {
            sitioTuristico = results.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar el sitioTuristico con id = {0} del libro con id =" + ciudadId, sitioId);
        return sitioTuristico;
    }
    
    /*
    *Actualiza y retorna un sitio turistico
    *@return sitioEntity
    */
    public TarjetaDeCreditoEntity update(TarjetaDeCreditoEntity sitioEntity) {
        LOGGER.log(Level.INFO, "Actualizando el sitioTuristico con id={0}", sitioEntity.getId());
        return em.merge(sitioEntity);
        
    }
    
    /*
    *Busca un sitio turistico por nombre
    *@param name
    *@return result
    */
    public TarjetaDeCreditoEntity findByName(String nombre) {
        LOGGER.log(Level.INFO, "Consultando sitio turistico por nombre ", nombre);
        // Se crea un query para buscar editoriales con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From TarjetaDeCreditoEntity e where e.nombre = :nombre", TarjetaDeCreditoEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("nombre", nombre);
        // Se invoca el query se obtiene la lista resultado
        List<TarjetaDeCreditoEntity> sameName = query.getResultList();
        TarjetaDeCreditoEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar TarjetaDeCredito por nombre ", nombre);
        return result;
    }
    
    
        /**
     * Eliminar una reseña
     *
     * Elimina la reseña asociada al ID que recibe
     *
     * @param sitioTuristicosId El ID de la reseña que se desea borrar
     */
    public void delete(Long sitioTuristicosId) {
        LOGGER.log(Level.INFO, "Borrando sitioTuristico con id = {0}", sitioTuristicosId);
        TarjetaDeCreditoEntity sitioTuristicoEntity = em.find(TarjetaDeCreditoEntity.class, sitioTuristicosId);
        em.remove(sitioTuristicoEntity);
        LOGGER.log(Level.INFO, "Saliendo de borrar El sitioTuristico con id = {0}", sitioTuristicosId);
    }
}
