/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.CiudadDTO;
import co.edu.uniandes.csw.turismo.dtos.SitioTuristicosDTO;
import co.edu.uniandes.csw.turismo.ejb.CiudadLogic;
import co.edu.uniandes.csw.turismo.ejb.SitioTuristicoCiudadLogic;
import co.edu.uniandes.csw.turismo.ejb.SitioTuristicoLogic;
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
 * @author david Fonseca
 */
@Path("sitios/{sitioId: \\d+}/ciudad")

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SitioTuristicoCiudadResource {
     private static final Logger LOGGER = Logger.getLogger(SitioTuristicoCiudadResource.class.getName());

    @Inject
    private SitioTuristicoLogic sitioTuristicoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private SitioTuristicoCiudadLogic sitioTuristicoCiudadLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private CiudadLogic ciudadLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Remplaza la instancia de Ciudad asociada a un SitioTuristico.
     *
     * @param sitioTuristicosId Identificador del libro que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param ciudad La ciudad que se será del libro.
     * @return JSON {@link SitioTuristicoDetailDTO} - El arreglo de libros guardado en la
     * ciudad.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la ciudad o el
     * libro.
     */
    @PUT
    public SitioTuristicosDTO replaceCiudad(@PathParam("sitioTuristicosId") Long sitioTuristicosId, CiudadDTO ciudad) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "SitioTuristicoCiudadResource replaceCiudad: input: sitioTuristicosId{0} , Ciudad:{1}", new Object[]{sitioTuristicosId, ciudad});
        if (sitioTuristicoLogic.getSitio(sitioTuristicosId) == null) {
            throw new WebApplicationException("El recurso /sitioTuristicos/" + sitioTuristicosId + " no existe.", 404);
        }
        if (ciudadLogic.getCiudad(ciudad.getId()) == null) {
            throw new WebApplicationException("El recurso /ciudads/" + ciudad.getId() + " no existe.", 404);
        }
        SitioTuristicosDTO sitioTuristicoDetailDTO = new SitioTuristicosDTO(sitioTuristicoCiudadLogic.replaceCiudad(sitioTuristicosId, ciudad.getId()));
        LOGGER.log(Level.INFO, "SitioTuristicoCiudadResource replaceCiudad: output: {0}", sitioTuristicoDetailDTO);
        return sitioTuristicoDetailDTO;
    }
    
}
