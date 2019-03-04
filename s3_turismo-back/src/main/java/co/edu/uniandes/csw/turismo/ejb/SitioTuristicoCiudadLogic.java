/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.CiudadEntity;
import co.edu.uniandes.csw.turismo.entities.SitioTuristicoEntity;
import co.edu.uniandes.csw.turismo.persistence.CiudadPersistence;
import co.edu.uniandes.csw.turismo.persistence.SitioTuristicoPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author David Fonseca
 */

@Stateless
public class SitioTuristicoCiudadLogic {
    
    private static final Logger LOGGER = Logger.getLogger(SitioTuristicoCiudadLogic.class.getName());

    @Inject
    private SitioTuristicoPersistence sitioPersistence;

    @Inject
    private CiudadPersistence ciudadPersistence;

    /**
     * Remplazar la ciudad de un sitio.
     *
     * @param sitiosId id del sitio que se quiere actualizar.
     * @param ciudadsId El id de la ciudad que se será del sitio.
     * @return el nuevo sitio.
     */
    public SitioTuristicoEntity replaceEditorial(Long sitiosId, Long ciudadsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar sitio con id = {0}", sitiosId);
        CiudadEntity ciudadEntity = ciudadPersistence.find(ciudadsId);
        SitioTuristicoEntity sitioEntity = sitioPersistence.find(sitiosId);
        sitioEntity.actualizarCiudad(ciudadEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar sitio con id = {0}", sitioEntity.getId());
        return sitioEntity;
    }

    /**
     * Borrar un sitio de una ciudad. Este metodo se utiliza para borrar la
     * relacion de un sitio.
     *
     * @param sitiosId El sitio que se desea borrar de la ciudad.
     */
    public void removeEditorial(Long sitiosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la Editorial del sitio con id = {0}", sitiosId);
        SitioTuristicoEntity sitioEntity = sitioPersistence.find(sitiosId);
        CiudadEntity ciudadEntity = ciudadPersistence.find(sitioEntity.darCiudad().getId());
        sitioEntity.actualizarCiudad(null);
        ciudadEntity.darSitios().remove(sitioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la Editorial del sitio con id = {0}", sitioEntity.getId());
    }
    
}
