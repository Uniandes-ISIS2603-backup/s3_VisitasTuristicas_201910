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
    private SitioTuristicoPersistence sitioTuristicoPersistence;

    @Inject
    private CiudadPersistence ciudadPersistence;

    /**
     * Remplazar la ciudad de un sitioTuristico.
     *
     * @param sitioTuristicosId id del libro que se quiere actualizar.
     * @param ciudadsId El id de la ciudad que se será del libro.
     * @return el nuevo libro.
     */
    public SitioTuristicoEntity replaceCiudad(Long sitioTuristicosId, Long ciudadsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar libro con id = {0}", sitioTuristicosId);
        CiudadEntity ciudadEntity = ciudadPersistence.find(ciudadsId);
        SitioTuristicoEntity sitioTuristicoEntity = sitioTuristicoPersistence.find(sitioTuristicosId);
        sitioTuristicoEntity.actualizarCiudad(ciudadEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar libro con id = {0}", sitioTuristicoEntity.getId());
        return sitioTuristicoEntity;
    }

    /**
     * Borrar un sitioTuristico de una ciudad. Este metodo se utiliza para borrar la
     * relacion de un libro.
     *
     * @param sitioTuristicosId El libro que se desea borrar de la ciudad.
     */
    public void removeCiudad(Long sitioTuristicosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la Ciudad del libro con id = {0}", sitioTuristicosId);
        SitioTuristicoEntity sitioTuristicoEntity = sitioTuristicoPersistence.find(sitioTuristicosId);
        CiudadEntity ciudadEntity = ciudadPersistence.find(sitioTuristicoEntity.getCiudad().getId());
        sitioTuristicoEntity.actualizarCiudad(null);
        ciudadEntity.darSitios().remove(sitioTuristicoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la Ciudad del libro con id = {0}", sitioTuristicoEntity.getId());
    }
}
