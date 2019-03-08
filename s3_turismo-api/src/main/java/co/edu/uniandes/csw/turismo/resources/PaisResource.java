/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.CiudadDetailDTO;
import co.edu.uniandes.csw.turismo.dtos.PaisDTO;
import co.edu.uniandes.csw.turismo.dtos.PaisDetailDTO;
import co.edu.uniandes.csw.turismo.dtos.PlanTuristicoDTO;
import co.edu.uniandes.csw.turismo.ejb.PaisLogic;
import co.edu.uniandes.csw.turismo.entities.PaisEntity;
import co.edu.uniandes.csw.turismo.entities.PlanTuristicoEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author estudiante
 */

@Path("/paises")
@Produces("application/json")
@Consumes("application/json" )
@RequestScoped
public class PaisResource {
    private static final Logger LOGGER=Logger.getLogger(PaisResource.class.getName());
    
    @Inject
    private PaisLogic paisLogic;
    
    
    @POST
    public PaisDTO crearPais(PaisDTO pais) throws BusinessLogicException
    {
        PaisEntity paisEntity = pais.toEntity();
        paisEntity = paisLogic.createPais(paisEntity);
        return new PaisDTO(paisEntity);

    }
    @GET
    public List<PaisDetailDTO> getPaises() {
        LOGGER.info("BookResource getBooks: input: void");
        List<PaisDetailDTO> listaPais = listEntity2DetailDTO(paisLogic.getPaises());
        LOGGER.log(Level.INFO, "BookResource getBooks: output: {0}", listaPais);
        return listaPais;
    }
    
    
    
     @GET
    @Path("{paisId: \\d+}")
    public PaisDetailDTO getPais(@PathParam("paisId") Long paisId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PaisResource get Pais: input: {0}", paisId);
        PaisEntity paisEntity = paisLogic.getPais(paisId);
        if (paisEntity == null) {
            throw new WebApplicationException("El recurso /books/" + paisId + " no existe.", 404);
        }
        PaisDetailDTO paisDetailDTO = new PaisDetailDTO(paisEntity);
        LOGGER.log(Level.INFO, "BookResource getBook: output: {0}", paisDetailDTO);
        return paisDetailDTO;
    }
    
    @PUT
    @Path("{paisId: \\d+}")
    public PaisDetailDTO updatePais(@PathParam("paisId") Long paisId, PaisDetailDTO pais) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "BookResource updateBook: input: id: {0} , book: {1}", new Object[]{paisId, pais});
        pais.cambiarID(paisId);
        if (paisLogic.getPais(paisId) == null) {
            throw new WebApplicationException("El recurso /books/" + paisId + " no existe.", 404);
        }
        PaisDetailDTO detailDTO = new PaisDetailDTO(paisLogic.updatePais(paisId, pais.toEntity()));
        LOGGER.log(Level.INFO, "BookResource updateBook: output: {0}", detailDTO);
        return detailDTO;
    }
    
    
    
    private List<PaisDetailDTO> listEntity2DetailDTO(List<PaisEntity> entityList) {
        List<PaisDetailDTO> list = new ArrayList<>();
        for (PaisEntity entity : entityList) {
            list.add(new PaisDetailDTO(entity));
        }
        return list;
    }
}
