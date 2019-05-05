/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;


import co.edu.uniandes.csw.turismo.dtos.CiudadDetailDTO;
import co.edu.uniandes.csw.turismo.dtos.PaisDTO;
import co.edu.uniandes.csw.turismo.ejb.CiudadLogic;
import co.edu.uniandes.csw.turismo.ejb.PaisLogic;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.ejb.CiudadPaisLogic;
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
 * @author David Fonseca
 */
@Path("ciudades/{ciudadesId: \\d+}/pais")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CiudadPaisResource 
{
private static final Logger LOGGER = Logger.getLogger(CiudadPaisResource.class.getName());

    @Inject
    private CiudadLogic ciudadLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private CiudadPaisLogic ciudadPaisLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private PaisLogic paisLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Remplaza la instancia de Pais asociada a un Ciudad.
     *
     * @param ciudadsId Identificador del libro que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param pais La pais que se será del libro.
     * @return JSON {@link CiudadDetailDTO} - El arreglo de libros guardado en la
     * pais.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la pais o el
     * libro.
     */
    @PUT
    public CiudadDetailDTO replacePais(@PathParam("ciudadsId") Long ciudadsId, PaisDTO pais) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CiudadPaisResource replacePais: input: ciudadsId{0} , Pais:{1}", new Object[]{ciudadsId, pais});
        if (ciudadLogic.getCiudad(ciudadsId) == null) {
            throw new WebApplicationException("El recurso /ciudads/" + ciudadsId + " no existe.", 404);
        }
        if (paisLogic.getPais(pais.getId()) == null) {
            throw new WebApplicationException("El recurso /paiss/" + pais.getId() + " no existe.", 404);
        }
      //  CiudadDetailDTO ciudadDetailDTO = new CiudadDetailDTO(ciudadPaisLogic.replace(ciudadsId, pais.getID()));
     //   LOGGER.log(Level.INFO, "CiudadPaisResource replacePais: output: {0}", ciudadDetailDTO);
       return null;
    }


  
}
