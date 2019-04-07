/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.turismo.dtos.ValoracionDTO;
import co.edu.uniandes.csw.turismo.ejb.ValoracionLogic;
import co.edu.uniandes.csw.turismo.entities.ValoracionEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

@Path("valoracion")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class ValoracionResource {

    @Inject
    private ValoracionLogic valoracionLogic;
    
    private static final Logger LOGGER = Logger.getLogger(ValoracionResource.class.getName());

    
     /**
     * Crea una nueva valoracion con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param planTuristicoId El ID del plan del cual se le agrega la valoracion
     * @param valoracion {@link ValoracionDTO} - La valoracion que se desea guardar.
     * @return JSON {@link ValoracionDTO} - La reseña guardada con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la valoracion.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ValoracionDTO crearValoracion(@PathParam("planTuristicoId")Long planTuristicoId, ValoracionDTO valoracion)throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ValoracionResource crearValoracion: input: {0}", valoracion.toString());
        ValoracionDTO nuevoValoracionDTO = new ValoracionDTO(valoracionLogic.createValoracion(planTuristicoId, valoracion.toEntity()));
          LOGGER.log(Level.INFO, "ValoracionResource crearValoracion: output: {0}", nuevoValoracionDTO.toString());
        return nuevoValoracionDTO;
    }

     /**
     * Busca y devuelve todas las valoraciones que existen en un plan.
     *
     * @param planTuristicoId El ID del plan del cual se buscan las valoraciones
     * @return JSONArray {@link ValoracionDTO} - Las valoraciones encontradas en el
     * plam. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<ValoracionDTO> getValoraciones(@PathParam("planTuristicoId") Long planTuristicoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ValoracionResource getValoraciones: input: {0}", planTuristicoId);
        List<ValoracionDTO> listaDTOs = listEntity2DTO(valoracionLogic.getValoraciones(planTuristicoId));
        LOGGER.log(Level.INFO, "PlanTuristicoResource getValoraciones: output: {0}", listaDTOs.toString());
        return listaDTOs;
    }

    /**
     * Busca y devuelve la valoracion con el ID recibido en la URL, relativa a un
     * plan.
     *
     * @param planTuristicoId El ID del libro del cual se buscan las reseñas
     * @param valoracionId El ID de la valoracion que se busca
     * @return {@link ValoracionDTO} - La valoracion encontradas en el plan.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el plan.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la valoracion.
     */
    @GET
    @Path("{valoracionId: \\d+}")
    public ValoracionDTO getValoracion(@PathParam("planTuristicoId") Long planTuristicoId, @PathParam("valoracionId") Long valoracionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ValoracionResource getValoracion: input: {0}", valoracionId);
        ValoracionEntity entity = valoracionLogic.getValoracion(planTuristicoId, valoracionId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /plan/" + planTuristicoId + "/valoracion/" + valoracionId + " no existe.", 404);
        }
        ValoracionDTO valoracionDTO = new ValoracionDTO(entity);
       // LOGGER.log(Level.INFO, "ValoracionResource getValoracion: output: {0}", ValoracionDTO.toString());
        return valoracionDTO;
    }

    /**
     * Actualiza una valoracion con la informacion que se recibe en el cuerpo de la
     * petición y se regresa el objeto actualizado.
     *
     * @param planId El ID del plan del cual se guarda la valoracion
     * @param valoracionId El ID de la reseña que se va a actualizar
     * @param valoracion {@link ValoracionDTO} - La valoracion que se desea guardar.
     * @return JSON {@link ValoracionDTO} - La valoracion actualizada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la valoracion.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la valoracion.
     */
    @PUT
    @Path("{valoracionId: \\d+}")
    public ValoracionDTO updateValoracion(@PathParam("planId") Long planId, @PathParam("valoracionId") Long valoracionId, ValoracionDTO valoracion) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ValoracionResource updateValoracion: input: planId: {0} , valoracionId: {1} , valoracion:{2}", new Object[]{planId, valoracionId, valoracion.toString()});
        if (valoracionId.equals(valoracion.toEntity().getId())) {
            throw new BusinessLogicException("Los ids de la valoracion no coinciden.");
        }
        ValoracionEntity entity = valoracionLogic.getValoracion(planId, valoracionId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /plan/" + planId + "/valoracion/" + valoracionId + " no existe.", 404);

        }
        ValoracionDTO valoracionDTO = new ValoracionDTO(valoracionLogic.updateValoracion(planId,valoracion.toEntity()));
        LOGGER.log(Level.INFO, "ValoracionResource updateValoracion: output:{0}", valoracionDTO.toString());
        return valoracionDTO;

    }

    /**
     * Borra la valoracion con el id asociado recibido en la URL.
     *
     * @param planId El ID del plan del cual se va a eliminar la valoracion.
     * @param valoracionId El ID de la valoracion que se va a eliminar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la valoracion.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la valoracion.
     */
    @DELETE
    @Path("{valoracionId: \\d+}")
    public void deleteValoracion(@PathParam("planId") Long planId, @PathParam("valoracionId") Long valoracionId) throws BusinessLogicException {
        ValoracionEntity entity = valoracionLogic.getValoracion(planId,valoracionId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /plan/" + planId + "/valoracion/" + valoracionId + " no existe.", 404);
        }
        valoracionLogic.deleteValoracion(planId, valoracionId);
    }

    /**
     * Lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos ValoracionEntity a una lista de
     * objetos ValoracionDTO (json)
     *
     * @param entityList corresponde a la lista de valoraciones de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de valoraciones en forma DTO (json)
     */
    private List<ValoracionDTO> listEntity2DTO(List<ValoracionEntity> entityList)throws BusinessLogicException {
        List<ValoracionDTO> list = new ArrayList<ValoracionDTO>();
        for (ValoracionEntity entity : entityList) {
            list.add(new ValoracionDTO(entity));
        }
        return list;
    }
}
