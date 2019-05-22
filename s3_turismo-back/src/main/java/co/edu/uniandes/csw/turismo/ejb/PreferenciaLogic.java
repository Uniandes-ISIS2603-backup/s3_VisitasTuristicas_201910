/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.PreferenciaEntity;
import co.edu.uniandes.csw.turismo.entities.ViajeroEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.PreferenciaPersistence;
import co.edu.uniandes.csw.turismo.persistence.ViajeroPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class PreferenciaLogic {
  private static final Logger LOGGER = Logger.getLogger(PreferenciaLogic.class.getName());

       

       
       
    @Inject
    private PreferenciaPersistence persistence;

    @Inject
    private ViajeroPersistence viajeroPersistence;

    /**
     * Se encarga de crear un Preferencia en la base de datos.
     *
     * @param reviewEntity Objeto de PreferenciaEntity con los datos nuevos
     * @param viajerosId id del Viajero el cual sera padre del nuevo Preferencia.
     * @return Objeto de PreferenciaEntity con los datos nuevos y su ID.
     * @throws BusinessLogicException si viajerosId no es el mismo que tiene el
     * entity.
     *
     */
    public PreferenciaEntity createPreferencia(Long viajerosId, PreferenciaEntity reviewEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de crear review");
        ViajeroEntity viajero = viajeroPersistence.find(viajerosId);
        reviewEntity.setViajero(viajero);
        LOGGER.log(Level.INFO, "Termina proceso de creación del review");
        return persistence.create(reviewEntity);
    }

    /**
     * Obtiene la lista de los registros de Preferencia que pertenecen a un Viajero.
     *
     * @param viajerosId id del Viajero el cual es padre de los Preferencias.
     * @return Colección de objetos de PreferenciaEntity.
     */
    public List<PreferenciaEntity> getPreferencias(Long viajerosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los reviews asociados al viajero con id = {0}", viajerosId);
        ViajeroEntity viajeroEntity = viajeroPersistence.find(viajerosId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar los reviews asociados al viajero con id = {0}", viajerosId);
        return viajeroEntity.getPreferencias();
    }

    /**
     * Obtiene los datos de una instancia de Preferencia a partir de su ID. La
     * existencia del elemento padre Viajero se debe garantizar.
     *
     * @param viajerosId El id del Libro buscado
     * @param reviewsId Identificador de la Reseña a consultar
     * @return Instancia de PreferenciaEntity con los datos del Preferencia consultado.
     *
     */
    public PreferenciaEntity getPreferencia(Long viajerosId, Long reviewsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el review con id = {0} del libro con id = " + viajerosId, reviewsId);
        return persistence.find(viajerosId, reviewsId);
    }

    /**
     * Actualiza la información de una instancia de Preferencia.
     *
     * @param reviewEntity Instancia de PreferenciaEntity con los nuevos datos.
     * @param viajerosId id del Viajero el cual sera padre del Preferencia actualizado.
     * @return Instancia de PreferenciaEntity con los datos actualizados.
     *
     */
    public PreferenciaEntity updatePreferencia(Long viajerosId, PreferenciaEntity reviewEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el review con id = {0} del libro con id = " + viajerosId, reviewEntity.getId());
        ViajeroEntity viajeroEntity = viajeroPersistence.find(viajerosId);
        reviewEntity.setViajero(viajeroEntity);
        persistence.update(reviewEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el review con id = {0} del libro con id = " + viajerosId, reviewEntity.getId());
        return reviewEntity;
    }

    /**
     * Elimina una instancia de Preferencia de la base de datos.
     *
     * @param reviewsId Identificador de la instancia a eliminar.
     * @param viajerosId id del Viajero el cual es padre del Preferencia.
     * @throws BusinessLogicException Si la reseña no esta asociada al libro.
     *
     */
    public void deletePreferencia(Long viajerosId, Long reviewsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el review con id = {0} del libro con id = " + viajerosId, reviewsId);
        PreferenciaEntity old = getPreferencia(viajerosId, reviewsId);
        if (old == null) {
            throw new BusinessLogicException("El review con id = " + reviewsId + " no esta asociado a el libro con id = " + viajerosId);
        }
        persistence.delete(old.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar el review con id = {0} del libro con id = " + viajerosId, reviewsId);
    }
         
         
         /*
         *Valida si el nombre no es nullo o vacio
         *@return nombre
         */
    private boolean validateNombre(String nombre) {
        return !(nombre == null || nombre.isEmpty());
    }       
}
