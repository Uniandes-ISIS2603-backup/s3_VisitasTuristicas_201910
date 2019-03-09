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
public class SitioTuristicoPersistence {

    private static final Logger LOGGER = Logger.getLogger(SitioTuristicoPersistence.class.getName());

    @PersistenceContext(unitName = "turismoPU")
    protected EntityManager em;

    /*
    *Crea un nuevo sitio turistico
    *@param sitioEntity
     */
    public SitioTuristicoEntity create(SitioTuristicoEntity sitioEntity) {
        LOGGER.log(Level.INFO, "Creando un sitio turistico nuevo");
        em.persist(sitioEntity);
        LOGGER.log(Level.INFO, "SitioTuristio creado");
        return sitioEntity;
    }

    /*
    *Retorna la lista de sitios turisticos
    *@return lista de sitios turisticos
     */
    public List<SitioTuristicoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los Sitios Turisticos");

        //TODO consulta sql
        Query q = em.createQuery("SELECT U FROM SitioTuristicoEntity U ", CiudadEntity.class);
        return q.getResultList();
    }

    /*
    *Busca un sitio turistico con el id dado
    *@return SitioTuristicoEntity
     */
    public SitioTuristicoEntity find(Long ciudadId) {
        LOGGER.log(Level.INFO, "Consultando el SitioTuristico con id={0}", ciudadId);
        return em.find(SitioTuristicoEntity.class, ciudadId);
    }

    /*
    *Actualiza y retorna un sitio turistico
    *@return sitioEntity
     */
    public SitioTuristicoEntity update(SitioTuristicoEntity sitioEntity) {
        LOGGER.log(Level.INFO, "Actualizando el sitioTuristico con id={0}", sitioEntity.getId());
        return em.merge(sitioEntity);

    }

    /*
    *Busca un sitio turistico por nombre
    *@param name
    *@return result
     */
    public SitioTuristicoEntity findByName(String nombre) {
        LOGGER.log(Level.INFO, "Consultando sitio turistico por nombre ", nombre);
        // Se crea un query para buscar editoriales con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From SitioTuristicoEntity e where e.nombre = :nombre", SitioTuristicoEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("nombre", nombre);
        // Se invoca el query se obtiene la lista resultado
        List<SitioTuristicoEntity> sameName = query.getResultList();
        SitioTuristicoEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar SitioTuristico por nombre ", nombre);
        return result;
    }

}
