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
 * @author estudiante
 */
@Stateless
public class ViajeroTarjetaDeCreditoLogic {
    private static final Logger LOGGER = Logger.getLogger(ViajeroTarjetaDeCreditoLogic.class.getName());

    @Inject
    private TarjetaDeCreditoPersistence tarjetaDeCreditoPersistence;

    @Inject
    private ViajeroPersistence viajeroPersistence;

    /**
     * Agregar un tarjetaDeCredito a la viajero
     *
     * @param tarjetaDeCreditosId El id libro a guardar
     * @param viajerosId El id de la viajero en la cual se va a guardar el
     * libro.
     * @return El libro creado.
     */
    public TarjetaDeCreditoEntity addTarjetaDeCredito(Long tarjetaDeCreditosId, Long viajerosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un libro a la viajero con id = {0}", viajerosId);
        ViajeroEntity viajeroEntity = viajeroPersistence.find(viajerosId);
        TarjetaDeCreditoEntity tarjetaDeCreditoEntity = tarjetaDeCreditoPersistence.find(tarjetaDeCreditosId);
        tarjetaDeCreditoEntity.setViajero(viajeroEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un libro a la viajero con id = {0}", viajerosId);
        return tarjetaDeCreditoEntity;
    }

    /**
     * Retorna todos los tarjetaDeCreditos asociados a una viajero
     *
     * @param viajerosId El ID de la viajero buscada
     * @return La lista de libros de la viajero
     */
    public List<TarjetaDeCreditoEntity> getTarjetaDeCreditos(Long viajerosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los libros asociados a la viajero con id = {0}", viajerosId);
        return viajeroPersistence.find(viajerosId).getTarjetas();
    }

    /**
     * Retorna un tarjetaDeCredito asociado a una viajero
     *
     * @param viajerosId El id de la viajero a buscar.
     * @param tarjetaDeCreditosId El id del libro a buscar
     * @return El libro encontrado dentro de la viajero.
     * @throws BusinessLogicException Si el libro no se encuentra en la
     * viajero
     */
    public TarjetaDeCreditoEntity getTarjetaDeCredito(Long viajerosId, Long tarjetaDeCreditosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro con id = {0} de la viajero con id = " + viajerosId, tarjetaDeCreditosId);
        List<TarjetaDeCreditoEntity> tarjetaDeCreditos = viajeroPersistence.find(viajerosId).getTarjetas();
        TarjetaDeCreditoEntity tarjetaDeCreditoEntity = tarjetaDeCreditoPersistence.find(tarjetaDeCreditosId);
        int index = tarjetaDeCreditos.indexOf(tarjetaDeCreditoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el libro con id = {0} de la viajero con id = " + viajerosId, tarjetaDeCreditosId);
        if (index >= 0) {
            return tarjetaDeCreditos.get(index);
        }
        throw new BusinessLogicException("El libro no está asociado a la viajero");
    }

    /**
     * Remplazar tarjetaDeCreditos de una viajero
     *
     * @param tarjetaDeCreditos Lista de libros que serán los de la viajero.
     * @param viajerosId El id de la viajero que se quiere actualizar.
     * @return La lista de libros actualizada.
     */
    public List<TarjetaDeCreditoEntity> replaceTarjetaDeCreditos(Long viajerosId, List<TarjetaDeCreditoEntity> tarjetaDeCreditos) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la viajero con id = {0}", viajerosId);
        ViajeroEntity viajeroEntity = viajeroPersistence.find(viajerosId);
        List<TarjetaDeCreditoEntity> tarjetaDeCreditoList = tarjetaDeCreditoPersistence.findAll();
        for (TarjetaDeCreditoEntity tarjetaDeCredito : tarjetaDeCreditoList) {
            if (tarjetaDeCreditos.contains(tarjetaDeCredito)) {
                tarjetaDeCredito.setViajero(viajeroEntity);
            } else if (tarjetaDeCredito.getViajero() != null && tarjetaDeCredito.getViajero().equals(viajeroEntity)) {
                tarjetaDeCredito.setViajero(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la viajero con id = {0}", viajerosId);
        return tarjetaDeCreditos;
    }
}
