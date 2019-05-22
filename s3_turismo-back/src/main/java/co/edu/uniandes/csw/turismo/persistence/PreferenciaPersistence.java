/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.persistence;

import co.edu.uniandes.csw.turismo.entities.PreferenciaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Juan Sebastian Gutierrez S.
 */
@Stateless
public class PreferenciaPersistence {
 @PersistenceContext(unitName="turismoPU")
    protected EntityManager em;
    
    /*
    *Crea un nuevo sitio turistico
    *@param sitioEntity
    */
    public PreferenciaEntity create(PreferenciaEntity sitioEntity)
    {
       em.persist(sitioEntity);
       return sitioEntity;
    }
    
    
    /*
    *Busca un sitio turistico con el id dado
    *@return PreferenciaEntity
    */
 public PreferenciaEntity find(Long ciudadId, Long sitioId) {
        TypedQuery<PreferenciaEntity> q = em.createQuery("select p from PreferenciaEntity p where (p.viajero.id = :ciudadId) and (p.id = :sitioId)", PreferenciaEntity.class);
        q.setParameter("ciudadId", ciudadId);
        q.setParameter("sitioId", sitioId);
        List<PreferenciaEntity> results = q.getResultList();
        PreferenciaEntity sitioTuristico = null;
        if (results == null) {
            sitioTuristico = null;
        } else if (results.isEmpty()) {
            sitioTuristico = null;
        } else if (results.size() >= 1) {
            sitioTuristico = results.get(0);
        }
        return sitioTuristico;
    }
    
    /*
    *Actualiza y retorna un sitio turistico
    *@return sitioEntity
    */
    public PreferenciaEntity update(PreferenciaEntity sitioEntity) {
        return em.merge(sitioEntity);
        
    }
    
    /*
    *Busca un sitio turistico por nombre
    *@param name
    *@return result
    */
    public PreferenciaEntity findByName(String nombre) {
        // Se crea un query para buscar editoriales con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From PreferenciaEntity e where e.nombre = :nombre", PreferenciaEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("nombre", nombre);
        // Se invoca el query se obtiene la lista resultado
        List<PreferenciaEntity> sameName = query.getResultList();
        PreferenciaEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
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
        PreferenciaEntity sitioTuristicoEntity = em.find(PreferenciaEntity.class, sitioTuristicosId);
        em.remove(sitioTuristicoEntity);
    }
    
}
