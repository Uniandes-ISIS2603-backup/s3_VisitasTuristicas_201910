/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.CiudadDetailDTO;
import co.edu.uniandes.csw.turismo.ejb.CiudadLogic;
import co.edu.uniandes.csw.turismo.ejb.PlanTuristicoCiudadLogic;
import co.edu.uniandes.csw.turismo.entities.CiudadEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
 * @ciudad david fonseca
 */

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PlanTuristicoCiudadResource {
    
    

    private static final Logger LOGGER = Logger.getLogger(PlanTuristicoCiudadResource.class.getName());

    @Inject
    private PlanTuristicoCiudadLogic planTuristicoCiudadLogic;

    @Inject
    private CiudadLogic ciudadLogic;

    /**
     * Asocia un autor existente con un libro existente
     *
     * @param ciudadsId El ID del autor que se va a asociar
     * @param planTuristicosId El ID del libro al cual se le va a asociar el autor
     * @return JSON {@link CiudadDetailDTO} - El autor asociado.
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @POST
    @Path("{ciudadsId: \\d+}")
    public CiudadDetailDTO addCiudad(@PathParam("planTuristicosId") Long planTuristicosId, @PathParam("ciudadsId") Long ciudadsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PlanTuristicoCiudadsResource addCiudad: input: planTuristicosId {0} , ciudadsId {1}", new Object[]{planTuristicosId, ciudadsId});
        if (ciudadLogic.getCiudad(ciudadsId) == null) {
            throw new WebApplicationException("El recurso /ciudads/" + ciudadsId + " no existe.", 404);
        }
        CiudadDetailDTO detailDTO = new CiudadDetailDTO(planTuristicoCiudadLogic.addCiudad(planTuristicosId, ciudadsId));
        LOGGER.log(Level.INFO, "PlanTuristicoCiudadsResource addCiudad: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Busca y devuelve todos los autores que existen en un libro.
     *
     * @param planTuristicosId El ID del libro del cual se buscan los autores
     * @return JSONArray {@link CiudadDetailDTO} - Los autores encontrados en el
     * libro. Si no hay ninguno retorna una lista vacía.
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @GET
    public List<CiudadDetailDTO> getCiudads(@PathParam("planTuristicosId") Long planTuristicosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PlanTuristicoCiudadsResource getCiudads: input: {0}", planTuristicosId);
        List<CiudadDetailDTO> lista = ciudadsListEntity2DTO(planTuristicoCiudadLogic.getCiudads(planTuristicosId));
        LOGGER.log(Level.INFO, "PlanTuristicoCiudadsResource getCiudads: output: {0}", lista);
        return lista;
    }

    /**
     * Busca y devuelve el autor con el ID recibido en la URL, relativo a un
     * libro.
     *
     * @param ciudadsId El ID del autor que se busca
     * @param planTuristicosId El ID del libro del cual se busca el autor
     * @return {@link CiudadDetailDTO} - El autor encontrado en el libro.
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @GET
    @Path("{ciudadsId: \\d+}")
    public CiudadDetailDTO getCiudad(@PathParam("planTuristicosId") Long planTuristicosId, @PathParam("ciudadsId") Long ciudadsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PlanTuristicoCiudadsResource getCiudad: input: planTuristicosId {0} , ciudadsId {1}", new Object[]{planTuristicosId, ciudadsId});
        if (ciudadLogic.getCiudad(ciudadsId) == null) {
            throw new WebApplicationException("El recurso /ciudads/" + ciudadsId + " no existe.", 404);
        }
        CiudadDetailDTO detailDTO = new CiudadDetailDTO(planTuristicoCiudadLogic.getCiudad(planTuristicosId, ciudadsId));
        LOGGER.log(Level.INFO, "PlanTuristicoCiudadsResource getCiudad: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza la lista de autores de un libro con la lista que se recibe en
     * el cuerpo.
     *
     * @param planTuristicosId El ID del libro al cual se le va a asociar la lista de
     * autores
     * @param ciudads JSONArray {@link CiudadDetailDTO} - La lista de autores
     * que se desea guardar.
     * @return JSONArray {@link CiudadDetailDTO} - La lista actualizada.
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @PUT
    public List<CiudadDetailDTO> replaceCiudads(@PathParam("planTuristicosId") Long planTuristicosId, List<CiudadDetailDTO> ciudads) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PlanTuristicoCiudadsResource replaceCiudads: input: planTuristicosId {0} , ciudads {1}", new Object[]{planTuristicosId, ciudads});
        for (CiudadDetailDTO ciudad : ciudads) {
            if (ciudadLogic.getCiudad(ciudad.getId()) == null) {
                throw new WebApplicationException("El recurso /ciudads/" + ciudad.getId() + " no existe.", 404);
            }
        }
        List<CiudadDetailDTO> lista = ciudadsListEntity2DTO(planTuristicoCiudadLogic.replaceCiudads(planTuristicosId, ciudadsListDTO2Entity(ciudads)));
        LOGGER.log(Level.INFO, "PlanTuristicoCiudadsResource replaceCiudads: output:{0}", lista);
        return lista;
    }

    /**
     * Elimina la conexión entre el autor y el libro recibidos en la URL.
     *
     * @param planTuristicosId El ID del libro al cual se le va a desasociar el autor
     * @param ciudadsId El ID del autor que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @DELETE
    @Path("{ciudadsId: \\d+}")
    public void removeCiudad(@PathParam("planTuristicosId") Long planTuristicosId, @PathParam("ciudadsId") Long ciudadsId) {
        LOGGER.log(Level.INFO, "PlanTuristicoCiudadsResource removeCiudad: input: planTuristicosId {0} , ciudadsId {1}", new Object[]{planTuristicosId, ciudadsId});
        if (ciudadLogic.getCiudad(ciudadsId) == null) {
            throw new WebApplicationException("El recurso /ciudads/" + ciudadsId + " no existe.", 404);
        }
        planTuristicoCiudadLogic.removeCiudad(planTuristicosId, ciudadsId);
        LOGGER.info("PlanTuristicoCiudadsResource removeCiudad: output: void");
    }

    /**
     * Convierte una lista de CiudadEntity a una lista de CiudadDetailDTO.
     *
     * @param entityList Lista de CiudadEntity a convertir.
     * @return Lista de CiudadDetailDTO convertida.
     */
    private List<CiudadDetailDTO> ciudadsListEntity2DTO(List<CiudadEntity> entityList) throws BusinessLogicException {
        List<CiudadDetailDTO> list = new ArrayList<>();
        for (CiudadEntity entity : entityList) {
            list.add(new CiudadDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de CiudadDetailDTO a una lista de CiudadEntity.
     *
     * @param dtos Lista de CiudadDetailDTO a convertir.
     * @return Lista de CiudadEntity convertida.
     */
    private List<CiudadEntity> ciudadsListDTO2Entity(List<CiudadDetailDTO> dtos) {
        List<CiudadEntity> list = new ArrayList<>();
        for (CiudadDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
}
