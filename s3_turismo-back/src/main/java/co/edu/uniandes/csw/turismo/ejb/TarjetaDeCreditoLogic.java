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
    private ViajeroPersistence viajeroPersistence;

    /**
     * Se encarga de crear un TarjetaDeCredito en la base de datos.
     *
     * @param tarjetaDeCreditoEntity Objeto de TarjetaDeCreditoEntity con los datos nuevos
     * @param viajerosId id del Viajero el cual sera padre del nuevo TarjetaDeCredito.
     * @return Objeto de TarjetaDeCreditoEntity con los datos nuevos y su ID.
     * @throws BusinessLogicException si viajerosId no es el mismo que tiene el
     * entity.
     *
     */
    public TarjetaDeCreditoEntity createTarjetaDeCredito(Long viajerosId, TarjetaDeCreditoEntity tarjetaDeCreditoEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de crear tarjetaDeCredito");
        ViajeroEntity viajero = viajeroPersistence.find(viajerosId);
        tarjetaDeCreditoEntity.setViajero(viajero);
        LOGGER.log(Level.INFO, "Termina proceso de creación del tarjetaDeCredito");
        return persistence.create(tarjetaDeCreditoEntity);
    }

    /**
     * Obtiene la lista de los registros de TarjetaDeCredito que pertenecen a un Viajero.
     *
     * @param viajerosId id del Viajero el cual es padre de los TarjetaDeCreditos.
     * @return Colección de objetos de TarjetaDeCreditoEntity.
     */
    public List<TarjetaDeCreditoEntity> getTarjetaDeCreditos(Long viajerosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los tarjetaDeCreditos asociados al viajero con id = {0}", viajerosId);
        ViajeroEntity viajeroEntity = viajeroPersistence.find(viajerosId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar los tarjetaDeCreditos asociados al viajero con id = {0}", viajerosId);
        return viajeroEntity.getTarjetas();
    }

    /**
     * Obtiene los datos de una instancia de TarjetaDeCredito a partir de su ID. La
     * existencia del elemento padre Viajero se debe garantizar.
     *
     * @param viajerosId El id del Libro buscado
     * @param tarjetaDeCreditosId Identificador de la Reseña a consultar
     * @return Instancia de TarjetaDeCreditoEntity con los datos del TarjetaDeCredito consultado.
     *
     */
    public TarjetaDeCreditoEntity getTarjetaDeCredito(Long viajerosId, Long tarjetaDeCreditosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el tarjetaDeCredito con id = {0} del libro con id = " + viajerosId, tarjetaDeCreditosId);
        return persistence.find(viajerosId, tarjetaDeCreditosId);
    }

    /**
     * Actualiza la información de una instancia de TarjetaDeCredito.
     *
     * @param tarjetaDeCreditoEntity Instancia de TarjetaDeCreditoEntity con los nuevos datos.
     * @param viajerosId id del Viajero el cual sera padre del TarjetaDeCredito actualizado.
     * @return Instancia de TarjetaDeCreditoEntity con los datos actualizados.
     *
     */
    public TarjetaDeCreditoEntity updateTarjetaDeCredito(Long viajerosId, TarjetaDeCreditoEntity tarjetaDeCreditoEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el tarjetaDeCredito con id = {0} del libro con id = " + viajerosId, tarjetaDeCreditoEntity.getId());
        ViajeroEntity viajeroEntity = viajeroPersistence.find(viajerosId);
        tarjetaDeCreditoEntity.setViajero(viajeroEntity);
        persistence.update(tarjetaDeCreditoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el tarjetaDeCredito con id = {0} del libro con id = " + viajerosId, tarjetaDeCreditoEntity.getId());
        return tarjetaDeCreditoEntity;
    }

    /**
     * Elimina una instancia de TarjetaDeCredito de la base de datos.
     *
     * @param tarjetaDeCreditosId Identificador de la instancia a eliminar.
     * @param viajerosId id del Viajero el cual es padre del TarjetaDeCredito.
     * @throws BusinessLogicException Si la reseña no esta asociada al libro.
     *
     */
    public void deleteTarjetaDeCredito(Long viajerosId, Long tarjetaDeCreditosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el tarjetaDeCredito con id = {0} del libro con id = " + viajerosId, tarjetaDeCreditosId);
        TarjetaDeCreditoEntity old = getTarjetaDeCredito(viajerosId, tarjetaDeCreditosId);
        if (old == null) {
            throw new BusinessLogicException("El tarjetaDeCredito con id = " + tarjetaDeCreditosId + " no esta asociado a el libro con id = " + viajerosId);
        }
        persistence.delete(old.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar el tarjetaDeCredito con id = {0} del libro con id = " + viajerosId, tarjetaDeCreditosId);
    }
}
