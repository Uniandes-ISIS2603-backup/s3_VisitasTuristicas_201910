/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;


import co.edu.uniandes.csw.turismo.dtos.SitioTuristicosDTO;
import co.edu.uniandes.csw.turismo.ejb.SitioTuristicoLogic;
import co.edu.uniandes.csw.turismo.entities.SitioTuristicoEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author David Fonseca
 */

@Path("/sitios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class SitioTuristicoResource {
    private static final Logger LOGGER=Logger.getLogger(SitioTuristicoResource.class.getName());
    @Inject
    private SitioTuristicoLogic logic;
    @POST
    public SitioTuristicosDTO crearSitioTuristico(SitioTuristicosDTO sitio) throws BusinessLogicException
    {
        SitioTuristicosDTO nuevoV = new SitioTuristicosDTO(logic.createSitio(sitio.toEntity()));
        return nuevoV;  
    }
    
     @GET
    @Path("{id: \\d+}")
    public SitioTuristicosDTO getSitioTuristico(@PathParam("id") Long idsitio) throws BusinessLogicException {
        SitioTuristicoEntity entity = logic.getSitio(idsitio);
        if (entity == null) {
            throw new WebApplicationException("El recurso /plan/" + idsitio, 404);
        }
        SitioTuristicosDTO blogDTO = new SitioTuristicosDTO(entity);
       // LOGGER.log(Level.INFO, "ValoracionResource getValoracion: output: {0}", ValoracionDTO.toString());
        return blogDTO;
    }
    
}
