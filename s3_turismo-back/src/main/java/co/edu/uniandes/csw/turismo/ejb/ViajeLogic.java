/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.ViajeEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.PlanTuristicoPersistence;
import co.edu.uniandes.csw.turismo.persistence.ViajePersistence;
import co.edu.uniandes.csw.turismo.persistence.ViajeroPersistence;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Christer Osorio
 */
@Stateless
public class ViajeLogic {

    @Inject
    private ViajePersistence persistence;

    @Inject
    private ViajeroPersistence viajeroPersistence;

    @Inject
    private PlanTuristicoPersistence planTuristicoPersistence;

    public ViajeEntity createViaje(ViajeEntity viajeEntity) throws BusinessLogicException {
        if (viajeEntity.getPlanTuristico() == null || planTuristicoPersistence.find(viajeEntity.getPlanTuristico().getId()) == null) {
            throw new BusinessLogicException("El plan turistico es invalido");
        }
        if (viajeEntity.getViajero() == null || viajeroPersistence.find(viajeEntity.getViajero().getId()) == null) {
            throw new BusinessLogicException("El Viajero no es valido");
        }

      /**  if (!fechasValidas(viajeEntity.getFechaInicio(), viajeEntity.getFechaFin())) {
            throw new BusinessLogicException("La fecha inicial no puede ser posterior a la final");
        }
*/
        persistence.create(viajeEntity);

        return viajeEntity;
    }

    /**
     * Verifica que el ISBN no sea invalido.
     *
     * @param isbn a verificar
     * @return true si el ISBN es valido.
     */
    private boolean fechasValidas(Date fechaInicio, Date fechaFin) {
        
        return (fechaInicio.before(fechaFin));
        
    }

    /**
     * Devuelve todos los viajes que hay en la base de datos.
     *
     * @return Lista de entidades de tipo viaje.
     */
    public List<ViajeEntity> getViajes() {

        List<ViajeEntity> viajes = persistence.findAll();
        return viajes;
    }

    /**
     * Busca un viaje por ID
     *
     * @param viajesId El id del libro a buscar
     * @return El viaje encontrado, null si no lo encuentra.
     */
    public ViajeEntity getViaje(Long viajesId) throws BusinessLogicException {

        ViajeEntity viajeEntity = persistence.find(viajesId);
        if (viajeEntity == null) {
            throw new BusinessLogicException("El viaje con ese id no existe");
        }

        return viajeEntity;
    }

    /**
     * Actualizar un libro por ID
     *
     * @param viajesId El ID del libro a actualizar
     * @param viajeEntity La entidad del libro con los cambios deseados
     * @return La entidad del libro luego de actualizarla
     * @throws BusinessLogicException Si el IBN de la actualización es inválido
     */
    public ViajeEntity updateViaje(Long viajesId, ViajeEntity viajeEntity) throws BusinessLogicException {

     /**   if (!fechasValidas(viajeEntity.getFechaInicio(), viajeEntity.getFechaFin())) {
            throw new BusinessLogicException("La fecha inicial no puede ser posterior a la final");
       }*/
        ViajeEntity newEntity = persistence.update(viajeEntity);

        return newEntity;
    }

    /**
     * Eliminar un viaje por ID
     *
     * @param viajessId El ID del viaje a eliminar
     */
    public void deleteViaje(Long viajessId) {

        persistence.delete(viajessId);

    }
}
