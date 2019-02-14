/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.persistence;

import co.edu.uniandes.csw.turismo.entities.CiudadEntity;
import co.edu.uniandes.csw.turismo.entities.SitioTuristicoEntity;
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
 * @author David Fonseca
 */

@Stateless
public class CiudadPersistence {
   
        private static final Logger LOGGER = Logger.getLogger(CiudadPersistence.class.getName());

    
    @PersistenceContext(unitName="turismoPU")
    protected EntityManager em;
    
    /*
    *Crea una nueva ciudad
    *@param ciudadEntity
    */
    public CiudadEntity create(CiudadEntity ciudadEntity)
    {
       LOGGER.log(Level.INFO, "Creando una ciudad nueva");
       em.persist(ciudadEntity);
       LOGGER.log(Level.INFO, "Ciudad creada");
       return ciudadEntity;
    }
    
    /*
    *Retorna la lista de ciudades
    *@return lista de ciudades
    */
    public List<CiudadEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos las ciudades");
        
        Query q = em.createQuery("SELECT U FROM CiudadEntity U ", CiudadEntity.class);
        return q.getResultList();
    }
    
    /*
    *Busca una ciudad con el id dado
    *@return CiudadEntity
    */
    public CiudadEntity find(Long ciudadId) {
        LOGGER.log(Level.INFO, "Consultando la ciudad con id={0}", ciudadId);
        return em.find(CiudadEntity.class, ciudadId);
    }
    
    /*
    *Actualiza y retorna una ciudad
    *@return CiudadEntity
    */
    public CiudadEntity update(CiudadEntity ciudadEntity) {
        LOGGER.log(Level.INFO, "Actualizando la ciudad con id={0}", ciudadEntity.getId());
        return em.merge(ciudadEntity);
    }
    
    /*
    *Busca una ciudad por nombre
    *@param name
    *@return result
    */
     public CiudadEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando Ciudad por nombre ", name);
        // Se crea un query para buscar editoriales con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From CiudadEntity e where e.nombre = :nombre", CiudadEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("nombre", name);
        // Se invoca el query se obtiene la lista resultado
        List<CiudadEntity> sameName = query.getResultList();
        CiudadEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar Ciudad por nombre ", name);
        return result;
    }
    
}
