/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.turismo.entities.ViajeroEntity;
import co.edu.uniandes.csw.turismo.persistence.TarjetaDeCreditoPersistence;
import co.edu.uniandes.csw.turismo.persistence.ViajeroPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class TarjetaDeCreditoViajeroLogic {
        private static final Logger LOGGER = Logger.getLogger(TarjetaDeCreditoViajeroLogic.class.getName());

    @Inject
    private TarjetaDeCreditoPersistence bookPersistence;

    @Inject
    private ViajeroPersistence editorialPersistence;

    /**
     * Remplazar la editorial de un book.
     *
     * @param booksId id del libro que se quiere actualizar.
     * @param editorialsId El id de la editorial que se será del libro.
     * @return el nuevo libro.
     */
    public TarjetaDeCreditoEntity replaceViajero(Long booksId, Long editorialsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar libro con id = {0}", booksId);

       ViajeroEntity editorialEntity = editorialPersistence.find(editorialsId);
        TarjetaDeCreditoEntity bookEntity = bookPersistence.find(booksId);
        bookEntity.setViajero(editorialEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar libro con id = {0}", bookEntity.getId());
        return bookEntity;
    }

    /**
     * Borrar un book de una editorial. Este metodo se utiliza para borrar la
     * relacion de un libro.
     *
     * @param booksId El libro que se desea borrar de la editorial.
     */
    public void removeViajero(Long booksId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la Viajero del libro con id = {0}", booksId);
        TarjetaDeCreditoEntity bookEntity = bookPersistence.find(booksId);
        ViajeroEntity editorialEntity = editorialPersistence.find(bookEntity.getViajero().getId());
        bookEntity.setViajero(null);
        editorialEntity.getTarjetas().remove(bookEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la Viajero del libro con id = {0}", bookEntity.getId());
    }
}
