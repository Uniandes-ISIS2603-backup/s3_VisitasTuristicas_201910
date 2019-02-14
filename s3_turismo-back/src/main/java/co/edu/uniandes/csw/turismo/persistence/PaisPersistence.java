/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.persistence;

import co.edu.uniandes.csw.turismo.entities.CiudadEntity;
import co.edu.uniandes.csw.turismo.entities.PaisEntity;
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
public class PaisPersistence {
     private static final Logger LOGGER = Logger.getLogger(PaisPersistence.class.getName());

    
    @PersistenceContext(unitName="turismoPU")
    protected EntityManager em;
    
    /*
    *Crea un nuevo pais
    *@param paisEntity
    */
    public PaisEntity create(PaisEntity paisEntity)
    {
       LOGGER.log(Level.INFO, "Creando un pais nuevo");
       em.persist(paisEntity);
       LOGGER.log(Level.INFO, "Ciudad creada");
       return paisEntity;
    }
    
    /*
    *Retorna la lista de paises
    *@return lista de paises
    */
    public List<PaisEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los paises");
        
        //TODO consulta sql
        Query q = em.createQuery("");
        return q.getResultList();
    }
    
    /*
    *Busca una ciudad con el id dado
    *@return CiudadEntity
    */
    public PaisEntity find(Long paisId) {
        LOGGER.log(Level.INFO, "Consultando el pais con id={0}", paisId);
        return em.find(PaisEntity.class, paisId);
    }
    
    /*
    *Actualiza y retorna un pais
    *@return PaisEntity
    */
    public PaisEntity update(PaisEntity paisEntity) {
        LOGGER.log(Level.INFO, "Actualizando el pais con id={0}", paisEntity.getId());
        return em.merge(paisEntity);
    }
    
    /*
    *Busca un pais por nombre
    *@param name
    *@return result
    */
     public PaisEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando pais por nombre ", name);
        // Se crea un query para buscar editoriales con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From PaisEntity e where e.nombre = :nombre", PaisEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<PaisEntity> sameName = query.getResultList();
        PaisEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar Pais por nombre ", name);
        return result;
    }
}
