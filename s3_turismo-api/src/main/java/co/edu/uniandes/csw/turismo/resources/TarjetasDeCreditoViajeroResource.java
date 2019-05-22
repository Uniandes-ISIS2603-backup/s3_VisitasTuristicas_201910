/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.TarjetaDeCreditoDTO;
import co.edu.uniandes.csw.turismo.dtos.ViajeroDTO;
import co.edu.uniandes.csw.turismo.ejb.TarjetaDeCreditoLogic;
import co.edu.uniandes.csw.turismo.ejb.TarjetaDeCreditoViajeroLogic;
import co.edu.uniandes.csw.turismo.ejb.ViajeroLogic;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author estudiante
 */

@Path("viajero/{viajeros: \\d+}/tarjetas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TarjetasDeCreditoViajeroResource {
    private static final Logger LOGGER = Logger.getLogger(TarjetasDeCreditoViajeroResource.class.getName());

    @Inject
    private TarjetaDeCreditoLogic bookLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private TarjetaDeCreditoViajeroLogic bookViajeroLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private ViajeroLogic editorialLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Remplaza la instancia de Viajero asociada a un TarjetaDeCredito.
     *
     * @param booksId Identificador del libro que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param editorial La editorial que se será del libro.
     * @return JSON {@link TarjetaDeCreditoDetailDTO} - El arreglo de libros guardado en la
     * editorial.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la editorial o el
     * libro.
     */
    @PUT
    public TarjetaDeCreditoDTO replaceViajero(@PathParam("tarjetas") Long booksId, ViajeroDTO editorial) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TarjetaDeCreditoViajeroResource replaceViajero: input: booksId{0} , Viajero:{1}", new Object[]{booksId, editorial});
        if (bookLogic.getTarjetaDeCredito(booksId) == null) {
            throw new WebApplicationException("El recurso /books/" + booksId + " no existe.", 404);
        }
        if (editorialLogic.getViajero(editorial.getIdUsuario()) == null) {
            throw new WebApplicationException("El recurso /editorials/" + editorial.getIdUsuario()+ " no existe.", 404);
        }
        TarjetaDeCreditoDTO bookDetailDTO = new TarjetaDeCreditoDTO(bookViajeroLogic.replaceViajero(booksId, editorial.getIdUsuario()));
        LOGGER.log(Level.INFO, "TarjetaDeCreditoViajeroResource replaceViajero: output: {0}", bookDetailDTO);
        return bookDetailDTO;
    }
}
