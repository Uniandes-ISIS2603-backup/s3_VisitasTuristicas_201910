/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.CiudadEntity;
import co.edu.uniandes.csw.turismo.entities.PaisEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.CiudadPersistence;
import co.edu.uniandes.csw.turismo.persistence.PaisPersistence;
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
public class CiudadPaisLogic {
    
        private static final Logger LOGGER = Logger.getLogger(CiudadPaisLogic.class.getName());
        
        @Inject
        private CiudadPersistence ciudadPersistence;
        
        @Inject
        private PaisPersistence paisPersistence;
        
        /**
     * Agregar una ciudad a el pais
     *
     * @param ciudadId El id libro a guardar
     * @param paisId El id de la editorial en la cual se va a guardar el
     * libro.
     * @return La ciudad creado.
     */
    public CiudadEntity addCiudad(Long ciudadId, Long paisId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle una ciudad a el pais con id = {0}", paisId);
        PaisEntity editorialEntity = paisPersistence.find(paisId);
        CiudadEntity bookEntity = ciudadPersistence.find(ciudadId);
        bookEntity.actualizarPais(editorialEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle una ciudad a el pais con id = {0}", paisId);
        return bookEntity;
    }
    
    
    
    
    
     /**
     * Retorna todas la ciudades asociados a un pais
     *
     * @param ciudadId El ID de el pais buscado
     * @return La lista de ciudades de el pais
     */
    public List<CiudadEntity> getCiudades(Long ciudadId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar las ciudades asociadas a el pais con id = {0}", ciudadId);
        return paisPersistence.find(ciudadId).darCiudades();
    }

    /**
     * Retorna una ciudad asociada a un pais
     *
     * @param paisId El id del pais a buscar.
     * @param ciudadId El id de la ciudad a buscar
     * @return La ciudad encontrada dentro de el pais.
     * @throws BusinessLogicException Si la ciudad no se encuentra en el
     * pais
     */
    public CiudadEntity getCiudad(Long paisId, Long ciudadId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la ciudad con id = {0} de el pais con id = " + paisId, ciudadId);
        List<CiudadEntity> books = paisPersistence.find(paisId).darCiudades();
        CiudadEntity bookEntity = ciudadPersistence.find(ciudadId);
        int index = books.indexOf(bookEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar la ciudad con id = {0} de el pais con id = " + paisId, ciudadId);
        if (index >= 0) {
            return books.get(index);
        }
        throw new BusinessLogicException("La ciudad no está asociada a el pais");
    }

    /**
     * Remplazar Ciudades de un pais
     *
     * @param ciudades Lista de libros que serán los de la editorial.
     * @param paisId El id de la editorial que se quiere actualizar.
     * @return La lista de libros actualizada.
     */
    public List<CiudadEntity> replaceCiudades(Long paisId, List<CiudadEntity> ciudades) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el pais con id = {0}", paisId);
        PaisEntity editorialEntity = paisPersistence.find(paisId);
        List<CiudadEntity> bookList = ciudadPersistence.findAll();
        for (CiudadEntity book : bookList) {
            if (ciudades.contains(book)) {
                book.actualizarPais(editorialEntity);
            } else if (book.darPais()!= null && book.darPais().equals(editorialEntity)) {
                book.actualizarPais(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de el pais con id = {0}", paisId);
        return ciudades;
    }
        

    
}
