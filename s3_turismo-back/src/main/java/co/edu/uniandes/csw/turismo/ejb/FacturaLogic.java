/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.FacturaEntity;
import co.edu.uniandes.csw.turismo.entities.PlanTuristicoEntity;
import co.edu.uniandes.csw.turismo.entities.ViajeroEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.FacturaPersistence;
import co.edu.uniandes.csw.turismo.persistence.ViajeroPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jd.castrellon
 */
@Stateless
public class FacturaLogic {
    
 private static final Logger LOGGER = Logger.getLogger(FacturaLogic.class.getName());
       

       
       
       @Inject
    private FacturaPersistence persistence;

    @Inject
    private ViajeroPersistence ciudadPersistence;

    /**
     * Se encarga de crear un SitioTuritico en la base de datos.
     *
     * @param reviewEntity Objeto de SitioTuriticoEntity con los datos nuevos
     * @param ciudadsId id del Viajero el cual sera padre del nuevo SitioTuritico.
     * @return Objeto de SitioTuriticoEntity con los datos nuevos y su ID.
     * @throws BusinessLogicException si ciudadsId no es el mismo que tiene el
     * entity.
     *
     */
    public FacturaEntity createSitioTuritico(Long ciudadsId, FacturaEntity reviewEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de crear review");
        ViajeroEntity ciudad = ciudadPersistence.find(ciudadsId);
        reviewEntity.setViajero(ciudad);
        LOGGER.log(Level.INFO, "Termina proceso de creación del review");
        return persistence.create(reviewEntity);
    }

    /**
     * Obtiene la lista de los registros de SitioTuritico que pertenecen a un Viajero.
     *
     * @param ciudadsId id del Viajero el cual es padre de los SitioTuriticos.
     * @return Colección de objetos de SitioTuriticoEntity.
     */
    public List<FacturaEntity> getSitioTuriticos(Long ciudadsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los reviews asociados al ciudad con id = {0}", ciudadsId);
        ViajeroEntity ciudadEntity = ciudadPersistence.find(ciudadsId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar los reviews asociados al ciudad con id = {0}", ciudadsId);
        return ciudadEntity.getFacturas();
    }

    /**
     * Obtiene los datos de una instancia de SitioTuritico a partir de su ID. La
     * existencia del elemento padre Viajero se debe garantizar.
     *
     * @param ciudadsId El id del Libro buscado
     * @param reviewsId Identificador de la Reseña a consultar
     * @return Instancia de SitioTuriticoEntity con los datos del SitioTuritico consultado.
     *
     */
    public FacturaEntity getSitioTuritico(Long ciudadsId, Long reviewsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el review con id = {0} del libro con id = " + ciudadsId, reviewsId);
        return persistence.find(ciudadsId, reviewsId);
    }

    /**
     * Actualiza la información de una instancia de SitioTuritico.
     *
     * @param reviewEntity Instancia de SitioTuriticoEntity con los nuevos datos.
     * @param ciudadsId id del Viajero el cual sera padre del SitioTuritico actualizado.
     * @return Instancia de SitioTuriticoEntity con los datos actualizados.
     *
     */
    public FacturaEntity updateSitioTuritico(Long ciudadsId, FacturaEntity reviewEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el review con id = {0} del libro con id = " + ciudadsId, reviewEntity.getId());
        ViajeroEntity ciudadEntity = ciudadPersistence.find(ciudadsId);
        reviewEntity.setViajero(ciudadEntity);
        persistence.update(reviewEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el review con id = {0} del libro con id = " + ciudadsId, reviewEntity.getId());
        return reviewEntity;
    }

    /**
     * Elimina una instancia de SitioTuritico de la base de datos.
     *
     * @param reviewsId Identificador de la instancia a eliminar.
     * @param ciudadsId id del Viajero el cual es padre del SitioTuritico.
     * @throws BusinessLogicException Si la reseña no esta asociada al libro.
     *
     */
    public void deleteSitioTuritico(Long ciudadsId, Long reviewsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el review con id = {0} del libro con id = " + ciudadsId, reviewsId);
        FacturaEntity old = getSitioTuritico(ciudadsId, reviewsId);
        if (old == null) {
            throw new BusinessLogicException("El review con id = " + reviewsId + " no esta asociado a el libro con id = " + ciudadsId);
        }
        persistence.delete(old.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar el review con id = {0} del libro con id = " + ciudadsId, reviewsId);
    }
         
         
         /*
         *Valida si el nombre no es nullo o vacio
         *@return nombre
         */
    private boolean validateNombre(String nombre) {
        return !(nombre == null || nombre.isEmpty());
         }   
 
    

}
