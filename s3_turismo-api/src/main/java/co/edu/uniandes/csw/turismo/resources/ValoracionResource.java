/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ValoracionResource {

    private static final Logger LOGGER=Logger.getLogger(ValoracionResource.class.getName());
    @Inject
    private ValoracionLogic logic;
  
    
    
    
     @POST
    public ValoracionDTO createValoracion(@PathParam("planId") Long booksId, ValoracionDTO valoracion) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ValoracionResource createValoracion: input: {0}", valoracion);
        ValoracionDTO nuevoValoracionDTO = new ValoracionDTO(logic.createValoracion(booksId, valoracion.toEntity()));
        LOGGER.log(Level.INFO, "ValoracionResource createValoracion: output: {0}", nuevoValoracionDTO);
        return nuevoValoracionDTO;
    }

    /**
     * Busca y devuelve todas las reseñas que existen en un libro.
     *
     * @param planId
     * @return JSONArray {@link ValoracionDTO} - Las reseñas encontradas en el
     * libro. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<ValoracionDTO> getValoracion(@PathParam("planId") Long planId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ValoracionResource getValoracion: input: {0}", planId);
        List<ValoracionDTO> listaDTOs = listEntity2DTO(logic.getValoraciones(planId));
        LOGGER.log(Level.INFO, "EditorialBooksResource getBooks: output: {0}", listaDTOs);
        return listaDTOs;
    }

    /**
     * Busca y devuelve la reseña con el ID recibido en la URL, relativa a un
     * libro.
     *
     * @param booksId El ID del libro del cual se buscan las reseñas
     * @param valoracionsId El ID de la reseña que se busca
     * @return {@link ValoracionDTO} - La reseña encontradas en el libro.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la reseña.
     */
    @GET
    @Path("{valoracions: \\d+}")
    public ValoracionDTO getValoracion(@PathParam("planId") Long booksId, @PathParam("valoracions") Long valoracionsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ValoracionResource getValoracion: input: {0}", valoracionsId);
        ValoracionEntity entity = logic.getValoracion(booksId, valoracionsId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + booksId + "/valoracions/" + valoracionsId + " no existe.", 404);
        }
        ValoracionDTO valoracionDTO = new ValoracionDTO(entity);
        LOGGER.log(Level.INFO, "ValoracionResource getValoracion: output: {0}", valoracionDTO);
        return valoracionDTO;
    }

    /**
     * Actualiza una reseña con la informacion que se recibe en el cuerpo de la
     * petición y se regresa el objeto actualizado.
     *
     * @param booksId El ID del libro del cual se guarda la reseña
     * @param valoracionsId El ID de la reseña que se va a actualizar
     * @param valoracion {@link ValoracionDTO} - La reseña que se desea guardar.
     * @return JSON {@link ValoracionDTO} - La reseña actualizada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la reseña.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la reseña.
     */
    @PUT
    @Path("{valoracionsId: \\d+}")
    public ValoracionDTO updateValoracion(@PathParam("planId") Long booksId, @PathParam("valoracionsId") Long valoracionsId, ValoracionDTO valoracion) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ValoracionResource updateValoracion: input: booksId: {0} , valoracionsId: {1} , valoracion:{2}", new Object[]{booksId, valoracionsId, valoracion});
        if (valoracionsId.equals(valoracion.getIdUsuario())) {
            throw new BusinessLogicException("Los ids del Valoracion no coinciden.");
        }
        ValoracionEntity entity = logic.getValoracion(booksId, valoracionsId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + booksId + "/valoracions/" + valoracionsId + " no existe.", 404);

        }
        ValoracionDTO valoracionDTO = new ValoracionDTO(logic.updateValoracion(booksId, valoracion.toEntity()));
        LOGGER.log(Level.INFO, "ValoracionResource updateValoracion: output:{0}", valoracionDTO);
        return valoracionDTO;

    }

    /**
     * Borra la reseña con el id asociado recibido en la URL.
     *
     * @param booksId El ID del libro del cual se va a eliminar la reseña.
     * @param valoracionsId El ID de la reseña que se va a eliminar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la reseña.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la reseña.
     */
    @DELETE
    @Path("{valoracionsId: \\d+}")
    public void deleteValoracion(@PathParam("planId") Long booksId, @PathParam("valoracionsId") Long valoracionsId) throws BusinessLogicException {
        ValoracionEntity entity = logic.getValoracion(booksId, valoracionsId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + booksId + "/valoracions/" + valoracionsId + " no existe.", 404);
        }
        logic.deleteValoracion(booksId, valoracionsId);
    }

    /**
     * Lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos PrizeEntity a una lista de
     * objetos ValoracionDTO (json)
     *
     * @param entityList corresponde a la lista de reseñas de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de reseñas en forma DTO (json)
     */
    private List<ValoracionDTO> listEntity2DTO(List<ValoracionEntity> entityList) throws BusinessLogicException {
        List<ValoracionDTO> list = new ArrayList<>();
        for (ValoracionEntity entity : entityList) {
            list.add(new ValoracionDTO(entity));
        }
        return list;
    }


}
