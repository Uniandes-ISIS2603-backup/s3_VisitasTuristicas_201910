/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.CiudadEntity;
import co.edu.uniandes.csw.turismo.entities.PaisEntity;
import co.edu.uniandes.csw.turismo.persistence.CiudadPersistence;
import co.edu.uniandes.csw.turismo.persistence.PaisPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author dg.fonseca
 */

@Stateless
public class PaisCiudadLogic {
    
        private static final Logger LOGGER = Logger.getLogger(PaisCiudadLogic.class.getName());
        
        @Inject
        private PaisPersistence paisPersistence;
        
        @Inject
        private CiudadPersistence ciudadPersistence;
        
        
        /**
     * Asocia una ciudad existente a un pais
     *
     * @param paisId Identificador de la instancia de pais
     * @param ciudadId Identificador de la instancia de ciudad
     * @return Instancia de ciudadEntity que fue asociada a pais
     */
    public CiudadEntity replacePais(Long paisId, Long ciudadId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un pais a la ciudad con id = {0}", paisId);
        PaisEntity paisEntity = paisPersistence.find(paisId);
        CiudadEntity ciudadEntity = ciudadPersistence.find(ciudadId);
        ciudadEntity.actualizarPais(paisEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un pais a la ciudad con id = {0}", paisId);
        return ciudadPersistence.find(ciudadId);
    }
    
    
    
     /**
     * Borrar un book de una editorial. Este metodo se utiliza para borrar la
     * relacion de un libro.
     *
     * @param ciudadId El libro que se desea borrar de la editorial.
     */
    public void removeEditorial(Long ciudadId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar  la ciudad del pais con id = {0}", ciudadId);
        CiudadEntity ciudadEntity = ciudadPersistence.find(ciudadId);
        PaisEntity paisEntity = paisPersistence.find(ciudadEntity.darPais().getId());
        ciudadEntity.actualizarPais(null);
        paisEntity.darCiudades().remove(ciudadEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el pais de la ciudad con id = {0}", ciudadEntity.getId());
    }
        

    
}
