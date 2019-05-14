/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.PlanTuristicoDetailDTO;
import co.edu.uniandes.csw.turismo.ejb.CiudadPlanTuristicosLogic;
import co.edu.uniandes.csw.turismo.ejb.PlanTuristicoLogic;
import co.edu.uniandes.csw.turismo.entities.PlanTuristicoEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;


/**
 *
 * @ciudad david fonseca
 */

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CiudadPlanTuristicoResource {
     
    @Inject
    private CiudadPlanTuristicosLogic ciudadPlanTuristicoLogic;

    @Inject
    private PlanTuristicoLogic planTuristicoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Asocia un libro existente con un autor existente
     *
     * @param ciudadsId El ID del autor al cual se le va a asociar el libro
     * @param planTuristicosId El ID del libro que se asocia
     * @return JSON {@link PlanTuristicoDetailDTO} - El libro asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    @Path("{planTuristicosId: \\d+}")
    public PlanTuristicoDetailDTO addPlanTuristico(@PathParam("ciudadsId") Long ciudadsId, @PathParam("planTuristicosId") Long planTuristicosId) throws BusinessLogicException {
        if (planTuristicoLogic.getPlanTuristico(planTuristicosId) == null) {
            throw new WebApplicationException("El recurso /planTuristicos/" + planTuristicosId + " no existe.", 404);
        }
        PlanTuristicoDetailDTO detailDTO = new PlanTuristicoDetailDTO(ciudadPlanTuristicoLogic.addPlanTuristico(ciudadsId, planTuristicosId));
        return detailDTO;
    }

    /**
     * Busca y devuelve todos los libros que existen en un autor.
     *
     * @param ciudadsId El ID del autor del cual se buscan los libros
     * @return JSONArray {@link PlanTuristicoDetailDTO} - Los libros encontrados en el
     * autor. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<PlanTuristicoDetailDTO> getPlanTuristicos(@PathParam("ciudadsId") Long ciudadsId) throws BusinessLogicException {
        List<PlanTuristicoDetailDTO> lista = planTuristicosListEntity2DTO(ciudadPlanTuristicoLogic.getPlanTuristicos(ciudadsId));
        return lista;
    }

    /**
     * Busca y devuelve el libro con el ID recibido en la URL, relativo a un
     * autor.
     *
     * @param ciudadsId El ID del autor del cual se busca el libro
     * @param planTuristicosId El ID del libro que se busca
     * @return {@link PlanTuristicoDetailDTO} - El libro encontrado en el autor.
     * @throws co.edu.uniandes.csw.planTuristicostore.exceptions.BusinessLogicException
     * si el libro no está asociado al autor
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @GET
    @Path("{planTuristicosId: \\d+}")
    public PlanTuristicoDetailDTO getPlanTuristico(@PathParam("ciudadsId") Long ciudadsId, @PathParam("planTuristicosId") Long planTuristicosId) throws BusinessLogicException {
        if (planTuristicoLogic.getPlanTuristico(planTuristicosId) == null) {
            throw new WebApplicationException("El recurso /planTuristicos/" + planTuristicosId + " no existe.", 404);
        }
        PlanTuristicoDetailDTO detailDTO = new PlanTuristicoDetailDTO(ciudadPlanTuristicoLogic.getPlanTuristico(ciudadsId, planTuristicosId));
        return detailDTO;
    }

    /**
     * Actualiza la lista de libros de un autor con la lista que se recibe en el
     * cuerpo
     *
     * @param ciudadsId El ID del autor al cual se le va a asociar el libro
     * @param planTuristicos JSONArray {@link PlanTuristicoDetailDTO} - La lista de libros que se
     * desea guardar.
     * @return JSONArray {@link PlanTuristicoDetailDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @PUT
    public List<PlanTuristicoDetailDTO> replacePlanTuristicos(@PathParam("ciudadsId") Long ciudadsId, List<PlanTuristicoDetailDTO> planTuristicos) throws BusinessLogicException {
        for (PlanTuristicoDetailDTO planTuristico : planTuristicos) {
            if (planTuristicoLogic.getPlanTuristico(planTuristico.getPlanTuristicoId()) == null) {
                throw new WebApplicationException("El recurso /planTuristicos/" + planTuristico.getPlanTuristicoId() + " no existe.", 404);
            }
        }
        List<PlanTuristicoDetailDTO> lista = planTuristicosListEntity2DTO(ciudadPlanTuristicoLogic.replacePlanTuristicos(ciudadsId, planTuristicosListDTO2Entity(planTuristicos)));
        return lista;
    }

    /**
     * Elimina la conexión entre el libro y e autor recibidos en la URL.
     *
     * @param ciudadsId El ID del autor al cual se le va a desasociar el libro
     * @param planTuristicosId El ID del libro que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @DELETE
    @Path("{planTuristicosId: \\d+}")
    public void removePlanTuristico(@PathParam("ciudadsId") Long ciudadsId, @PathParam("planTuristicosId") Long planTuristicosId) {
        if (planTuristicoLogic.getPlanTuristico(planTuristicosId) == null) {
            throw new WebApplicationException("El recurso /planTuristicos/" + planTuristicosId + " no existe.", 404);
        }
        ciudadPlanTuristicoLogic.removePlanTuristico(ciudadsId, planTuristicosId);
    }

    /**
     * Convierte una lista de PlanTuristicoEntity a una lista de PlanTuristicoDetailDTO.
     *
     * @param entityList Lista de PlanTuristicoEntity a convertir.
     * @return Lista de PlanTuristicoDetailDTO convertida.
     */
    private List<PlanTuristicoDetailDTO> planTuristicosListEntity2DTO(List<PlanTuristicoEntity> entityList) throws BusinessLogicException {
        List<PlanTuristicoDetailDTO> list = new ArrayList<>();
        for (PlanTuristicoEntity entity : entityList) {
            list.add(new PlanTuristicoDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de PlanTuristicoDetailDTO a una lista de PlanTuristicoEntity.
     *
     * @param dtos Lista de PlanTuristicoDetailDTO a convertir.
     * @return Lista de PlanTuristicoEntity convertida.
     */
    private List<PlanTuristicoEntity> planTuristicosListDTO2Entity(List<PlanTuristicoDetailDTO> dtos) {
        List<PlanTuristicoEntity> list = new ArrayList<>();
        for (PlanTuristicoDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}
