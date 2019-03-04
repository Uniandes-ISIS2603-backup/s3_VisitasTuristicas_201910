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
 * @author dg.fonseca
 */

@Stateless
public class CiudadSitioTuristicoLogic {
     private static final Logger LOGGER = Logger.getLogger(CiudadSitioTuristicoLogic.class.getName());

    @Inject
    private SitioTuristicoPersistence sitioPersistence;

    @Inject
    private CiudadPersistence ciudadPersistence;

    /**
     * Agregar un sitio a la ciudad
     *
     * @param sitioId El id sitio a guardar
     * @param ciudadId El id de la ciudad en la cual se va a guardar el
     * sitio.
     * @return El sitio creado.
     */
    public SitioTuristicoEntity addSitioTuristico(Long sitioId, Long ciudadId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un sitio a la ciudad con id = {0}", ciudadId);
        CiudadEntity ciudadEntity = ciudadPersistence.find(ciudadId);
        SitioTuristicoEntity sitioEntity = sitioPersistence.find(sitioId);
        sitioEntity.actualizarCiudad(ciudadEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un sitio a la ciudad con id = {0}", ciudadId);
        return sitioEntity;
    }

    /**
     * Retorna todos los sitios asociados a una ciudad
     *
     * @param ciudadId El ID de la ciudad buscada
     * @return La lista de sitios de la ciudad
     */
    public List<SitioTuristicoEntity> getSitios(Long ciudadId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los sitios asociados a la ciudad con id = {0}", ciudadId);
        return ciudadPersistence.find(ciudadId).darSitios();
    }

    /**
     * Retorna un sitio asociado a una ciudad
     *
     * @param ciudadId El id de la ciudad a buscar.
     * @param sitioId El id del sitio a buscar
     * @return El sitio encontrado dentro de la ciudad.
     * @throws BusinessLogicException Si el sitio no se encuentra en la
     * ciudad
     */
    public SitioTuristicoEntity getSitioTuristico(Long ciudadId, Long sitioId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el sitio con id = {0} de la ciudad con id = " + ciudadId, sitioId);
        List<SitioTuristicoEntity> sitios = ciudadPersistence.find(ciudadId).darSitios();
        SitioTuristicoEntity sitioEntity = sitioPersistence.find(sitioId);
        int index = sitios.indexOf(sitioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el sitio con id = {0} de la ciudad con id = " + ciudadId, sitioId);
        if (index >= 0) {
            return sitios.get(index);
        }
        throw new BusinessLogicException("El sitio no está asociado a la ciudad");
    }

    /**
     * Remplazar sitios de una ciudad
     *
     * @param sitios Lista de sitios que serán los de la ciudad.
     * @param ciudadId El id de la ciudad que se quiere actualizar.
     * @return La lista de sitios actualizada.
     */
    public List<SitioTuristicoEntity> replaceSitioTuristico(Long ciudadId, List<SitioTuristicoEntity> sitios) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la ciudad con id = {0}", ciudadId);
        CiudadEntity ciudadEntity = ciudadPersistence.find(ciudadId);
        List<SitioTuristicoEntity> sitioList = sitioPersistence.findAll();
        for (SitioTuristicoEntity sitio : sitioList) {
            if (sitios.contains(sitio)) {
                sitio.actualizarCiudad(ciudadEntity);
            } else if (sitio.darCiudad()!= null && sitio.darCiudad().equals(ciudadEntity)) {
                sitio.actualizarCiudad(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la ciudad con id = {0}", ciudadId);
        return sitios;
    }
}
