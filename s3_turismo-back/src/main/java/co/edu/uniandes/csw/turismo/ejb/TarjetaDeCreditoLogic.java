/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.turismo.entities.ViajeroEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.TarjetaDeCreditoPersistence;
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
public class TarjetaDeCreditoLogic {
    
    
  private static final Logger LOGGER = Logger.getLogger(TarjetaDeCreditoLogic.class.getName());
       

       
       
       @Inject
    private TarjetaDeCreditoPersistence persistence;

    @Inject
    private ViajeroPersistence ciudadPersistence;

    /**
     * Se encarga de crear un TarjetaDeCredito en la base de datos.
     *
     * @param reviewEntity Objeto de TarjetaDeCreditoEntity con los datos nuevos
     * @param ciudadsId id del Viajero el cual sera padre del nuevo TarjetaDeCredito.
     * @return Objeto de TarjetaDeCreditoEntity con los datos nuevos y su ID.
     * @throws BusinessLogicException si ciudadsId no es el mismo que tiene el
     * entity.
     *
     */
    public TarjetaDeCreditoEntity createTarjetaDeCredito(Long ciudadsId, TarjetaDeCreditoEntity reviewEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de crear review");
        ViajeroEntity ciudad = ciudadPersistence.find(ciudadsId);
        reviewEntity.setViajero(ciudad);
        LOGGER.log(Level.INFO, "Termina proceso de creación del review");
        return persistence.create(reviewEntity);
    }

    /**
     * Obtiene la lista de los registros de TarjetaDeCredito que pertenecen a un Viajero.
     *
     * @param ciudadsId id del Viajero el cual es padre de los TarjetaDeCreditos.
     * @return Colección de objetos de TarjetaDeCreditoEntity.
     */
    public List<TarjetaDeCreditoEntity> getTarjetaDeCreditos(Long ciudadsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los reviews asociados al ciudad con id = {0}", ciudadsId);
        ViajeroEntity ciudadEntity = ciudadPersistence.find(ciudadsId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar los reviews asociados al ciudad con id = {0}", ciudadsId);
        return ciudadEntity.getTarjetas();
    }

    /**
     * Obtiene los datos de una instancia de TarjetaDeCredito a partir de su ID. La
     * existencia del elemento padre Viajero se debe garantizar.
     *
     * @param ciudadsId El id del Libro buscado
     * @param reviewsId Identificador de la Reseña a consultar
     * @return Instancia de TarjetaDeCreditoEntity con los datos del TarjetaDeCredito consultado.
     *
     */
    public TarjetaDeCreditoEntity getTarjetaDeCredito(Long ciudadsId, Long reviewsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el review con id = {0} del libro con id = " + ciudadsId, reviewsId);
        return persistence.find(ciudadsId, reviewsId);
    }

    /**
     * Actualiza la información de una instancia de TarjetaDeCredito.
     *
     * @param reviewEntity Instancia de TarjetaDeCreditoEntity con los nuevos datos.
     * @param ciudadsId id del Viajero el cual sera padre del TarjetaDeCredito actualizado.
     * @return Instancia de TarjetaDeCreditoEntity con los datos actualizados.
     *
     */
    public TarjetaDeCreditoEntity updateTarjetaDeCredito(Long ciudadsId, TarjetaDeCreditoEntity reviewEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el review con id = {0} del libro con id = " + ciudadsId, reviewEntity.getId());
        ViajeroEntity ciudadEntity = ciudadPersistence.find(ciudadsId);
        reviewEntity.setViajero(ciudadEntity);
        persistence.update(reviewEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el review con id = {0} del libro con id = " + ciudadsId, reviewEntity.getId());
        return reviewEntity;
    }

    /**
     * Elimina una instancia de TarjetaDeCredito de la base de datos.
     *
     * @param reviewsId Identificador de la instancia a eliminar.
     * @param ciudadsId id del Viajero el cual es padre del TarjetaDeCredito.
     * @throws BusinessLogicException Si la reseña no esta asociada al libro.
     *
     */
    public void deleteTarjetaDeCredito(Long ciudadsId, Long reviewsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el review con id = {0} del libro con id = " + ciudadsId, reviewsId);
        TarjetaDeCreditoEntity old = getTarjetaDeCredito(ciudadsId, reviewsId);
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
