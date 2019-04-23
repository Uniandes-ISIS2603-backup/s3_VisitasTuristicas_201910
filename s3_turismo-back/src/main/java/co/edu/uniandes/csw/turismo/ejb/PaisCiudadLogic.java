/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.PaisEntity;
import co.edu.uniandes.csw.turismo.entities.CiudadEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.PaisPersistence;
import co.edu.uniandes.csw.turismo.persistence.CiudadPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author David Fonseca
 */
@Stateless
public class PaisCiudadLogic {
    
    
    private static final Logger LOGGER = Logger.getLogger(PaisCiudadLogic.class.getName());

    @Inject
    private CiudadPersistence ciudadPersistence;

    @Inject
    private PaisPersistence paisPersistence;

    /**
     * Agregar un ciudad a la pais
     *
     * @param ciudadsId El id libro a guardar
     * @param paissId El id de la pais en la cual se va a guardar el
     * libro.
     * @return El libro creado.
     */
    public CiudadEntity addCiudad(Long ciudadsId, Long paissId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un libro a la pais con id = {0}", paissId);
        PaisEntity paisEntity = paisPersistence.find(paissId);
        CiudadEntity ciudadEntity = ciudadPersistence.find(ciudadsId);
        ciudadEntity.actualizarPais(paisEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un libro a la pais con id = {0}", paissId);
        return ciudadEntity;
    }

    /**
     * Retorna todos los ciudads asociados a una pais
     *
     * @param paissId El ID de la pais buscada
     * @return La lista de libros de la pais
     */
    public List<CiudadEntity> getCiudads(Long paissId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los libros asociados a la pais con id = {0}", paissId);
        return paisPersistence.find(paissId).getCiudades();
    }

    /**
     * Retorna un ciudad asociado a una pais
     *
     * @param paissId El id de la pais a buscar.
     * @param ciudadsId El id del libro a buscar
     * @return El libro encontrado dentro de la pais.
     * @throws BusinessLogicException Si el libro no se encuentra en la
     * pais
     */
    public CiudadEntity getCiudad(Long paissId, Long ciudadsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro con id = {0} de la pais con id = " + paissId, ciudadsId);
        List<CiudadEntity> ciudads = paisPersistence.find(paissId).getCiudades();
        CiudadEntity ciudadEntity = ciudadPersistence.find(ciudadsId);
        int index = ciudads.indexOf(ciudadEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el libro con id = {0} de la pais con id = " + paissId, ciudadsId);
        if (index >= 0) {
            return ciudads.get(index);
        }
        throw new BusinessLogicException("El libro no está asociado a la pais");
    }

    /**
     * Remplazar ciudads de una pais
     *
     * @param ciudads Lista de libros que serán los de la pais.
     * @param paissId El id de la pais que se quiere actualizar.
     * @return La lista de libros actualizada.
     */
    public List<CiudadEntity> replaceCiudads(Long paissId, List<CiudadEntity> ciudads) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la pais con id = {0}", paissId);
        PaisEntity paisEntity = paisPersistence.find(paissId);
        List<CiudadEntity> ciudadList = ciudadPersistence.findAll();
        for (CiudadEntity ciudad : ciudadList) {
            if (ciudads.contains(ciudad)) {
                ciudad.actualizarPais(paisEntity);
            } else if (ciudad.darPais() != null && ciudad.darPais().equals(paisEntity)) {
                ciudad.actualizarPais(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la pais con id = {0}", paissId);
        return ciudads;
    }
}
