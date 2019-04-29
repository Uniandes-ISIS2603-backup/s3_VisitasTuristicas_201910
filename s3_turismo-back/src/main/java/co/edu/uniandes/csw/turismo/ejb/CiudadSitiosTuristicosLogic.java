/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.CiudadEntity;
import co.edu.uniandes.csw.turismo.entities.SitioTuristicoEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.CiudadPersistence;
import co.edu.uniandes.csw.turismo.persistence.SitioTuristicoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author david fonseca
 */
@Stateless
public class CiudadSitiosTuristicosLogic {
    private static final Logger LOGGER = Logger.getLogger(CiudadSitiosTuristicosLogic.class.getName());

    @Inject
    private SitioTuristicoPersistence sitioTuristicoPersistence;

    @Inject
    private CiudadPersistence ciudadPersistence;

    /**
     * Agregar un sitioTuristico a la ciudad
     *
     * @param sitioTuristicosId El id libro a guardar
     * @param ciudadsId El id de la ciudad en la cual se va a guardar el
     * libro.
     * @return El libro creado.
     */
    public SitioTuristicoEntity addSitioTuristico(Long sitioTuristicosId, Long ciudadsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un libro a la ciudad con id = {0}", ciudadsId);
        CiudadEntity ciudadEntity = ciudadPersistence.find(ciudadsId);
        SitioTuristicoEntity sitioTuristicoEntity = sitioTuristicoPersistence.find(sitioTuristicosId);
        sitioTuristicoEntity.actualizarCiudad(ciudadEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un libro a la ciudad con id = {0}", ciudadsId);
        return sitioTuristicoEntity;
    }

    /**
     * Retorna todos los sitioTuristicos asociados a una ciudad
     *
     * @param ciudadsId El ID de la ciudad buscada
     * @return La lista de libros de la ciudad
     */
    public List<SitioTuristicoEntity> getSitioTuristicos(Long ciudadsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los libros asociados a la ciudad con id = {0}", ciudadsId);
        return ciudadPersistence.find(ciudadsId).darSitios();
    }

    /**
     * Retorna un sitioTuristico asociado a una ciudad
     *
     * @param ciudadsId El id de la ciudad a buscar.
     * @param sitioTuristicosId El id del libro a buscar
     * @return El libro encontrado dentro de la ciudad.
     * @throws BusinessLogicException Si el libro no se encuentra en la
     * ciudad
     */
    public SitioTuristicoEntity getSitioTuristico(Long ciudadsId, Long sitioTuristicosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro con id = {0} de la ciudad con id = " + ciudadsId, sitioTuristicosId);
        List<SitioTuristicoEntity> sitioTuristicos = ciudadPersistence.find(ciudadsId).darSitios();
        SitioTuristicoEntity sitioTuristicoEntity = sitioTuristicoPersistence.find(sitioTuristicosId);
        int index = sitioTuristicos.indexOf(sitioTuristicoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el libro con id = {0} de la ciudad con id = " + ciudadsId, sitioTuristicosId);
        if (index >= 0) {
            return sitioTuristicos.get(index);
        }
        throw new BusinessLogicException("El libro no está asociado a la ciudad");
    }

    /**
     * Remplazar sitioTuristicos de una ciudad
     *
     * @param sitioTuristicos Lista de libros que serán los de la ciudad.
     * @param ciudadsId El id de la ciudad que se quiere actualizar.
     * @return La lista de libros actualizada.
     */
    public List<SitioTuristicoEntity> replaceSitioTuristicos(Long ciudadsId, List<SitioTuristicoEntity> sitioTuristicos) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la ciudad con id = {0}", ciudadsId);
        CiudadEntity ciudadEntity = ciudadPersistence.find(ciudadsId);
        List<SitioTuristicoEntity> sitioTuristicoList = sitioTuristicoPersistence.findAll();
        for (SitioTuristicoEntity sitioTuristico : sitioTuristicoList) {
            if (sitioTuristicos.contains(sitioTuristico)) {
                sitioTuristico.actualizarCiudad(ciudadEntity);
            } else if (sitioTuristico.getCiudad() != null && sitioTuristico.getCiudad().equals(ciudadEntity)) {
                sitioTuristico.actualizarCiudad(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la ciudad con id = {0}", ciudadsId);
        return sitioTuristicos;
    }
    
}
