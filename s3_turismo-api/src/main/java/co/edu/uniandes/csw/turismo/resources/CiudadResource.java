/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;



import co.edu.uniandes.csw.turismo.dtos.CiudadDTO;
import co.edu.uniandes.csw.turismo.dtos.CiudadDetailDTO;
import co.edu.uniandes.csw.turismo.ejb.CiudadLogic;
import co.edu.uniandes.csw.turismo.entities.CiudadEntity;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author estudiante
 */

@Path("/ciudades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON )
@RequestScoped
public class CiudadResource {
    private static final Logger LOGGER=Logger.getLogger(CiudadResource.class.getName());
    
    @Inject
    private CiudadLogic ciudadLogic;
    

    
    
    @POST
    public CiudadDTO crearCiudad(CiudadDTO ciudad) throws BusinessLogicException
    {
        CiudadEntity ciudadEntity = ciudad.toEntity();
        ciudadEntity = ciudadLogic.createCiudad(ciudadEntity);
        return new CiudadDTO(ciudadEntity);

    }
    @GET
    public List<CiudadDetailDTO> getCiudades() throws BusinessLogicException {
        LOGGER.info("BookResource getBooks: input: void");
        List<CiudadDetailDTO> listaCiudad = listEntity2DetailDTO(ciudadLogic.getCiudades());
        LOGGER.log(Level.INFO, "BookResource getBooks: output: {0}", listaCiudad);
        return listaCiudad;
    }
    
    
    
     @GET
    @Path("{ciudadId: \\d+}")
    public CiudadDetailDTO getCiudad(@PathParam("ciudadId") Long ciudadId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CiudadResource get Ciudad: input: {0}", ciudadId);
        CiudadEntity ciudadEntity = ciudadLogic.getCiudad(ciudadId);
        if (ciudadEntity == null) {
            throw new WebApplicationException("El recurso /books/" + ciudadId + " no existe.", 404);
        }
        CiudadDetailDTO ciudadDetailDTO = new CiudadDetailDTO(ciudadEntity);
        LOGGER.log(Level.INFO, "BookResource getBook: output: {0}", ciudadDetailDTO);
        return ciudadDetailDTO;
    }
    
    
    @PUT
    @Path("{ciudadId: \\d+}")
    public CiudadDetailDTO updateCiudad(@PathParam("ciudadId") Long ciudadId, CiudadDetailDTO ciudad) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "BookResource updateBook: input: id: {0} , book: {1}", new Object[]{ciudadId, ciudad});
        ciudad.setId(ciudadId);
        if (ciudadLogic.getCiudad(ciudadId) == null) {
            throw new WebApplicationException("El recurso /books/" + ciudadId + " no existe.", 404);
        }
        CiudadDetailDTO detailDTO = new CiudadDetailDTO(ciudadLogic.updateCiudad(ciudadId, ciudad.toEntity()));
        LOGGER.log(Level.INFO, "BookResource updateBook: output: {0}", detailDTO);
        return detailDTO;
    }
    
     @Path("{ciudadId: \\d+}/SitiosTuristicos")
    public Class<SitioTuristicoResource> getSitiosTuristicosResource(@PathParam("ciudadId") Long ciudadId) throws BusinessLogicException {
        CiudadEntity ciudad = ciudadLogic.getCiudad(ciudadId);
        if (ciudad == null) {
                throw new WebApplicationException("El recurso /ciudades/" + ciudadId + "/SitiosTuristicos no existe.", 404);
        } 
        return SitioTuristicoResource.class;
    }
    
    
    private List<CiudadDetailDTO> listEntity2DetailDTO(List<CiudadEntity> entityList) throws BusinessLogicException {
        List<CiudadDetailDTO> list = new ArrayList<>();
        for (CiudadEntity entity : entityList) {
            list.add(new CiudadDetailDTO(entity));
        }
        return list;
    }
}
