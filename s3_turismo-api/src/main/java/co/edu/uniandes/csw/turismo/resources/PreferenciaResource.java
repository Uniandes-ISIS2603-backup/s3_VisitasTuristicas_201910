/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.PreferenciaDTO;
import co.edu.uniandes.csw.turismo.ejb.PreferenciaLogic;
import co.edu.uniandes.csw.turismo.entities.PreferenciaEntity;
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
 * @author Juan Sebastian Gutierrez S.
 */

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PreferenciaResource {
    
    
    
    private static final Logger LOGGER=Logger.getLogger(PreferenciaResource.class.getName());
    @Inject
    private PreferenciaLogic logic;
  
    
    
    
     @POST
    public PreferenciaDTO createPreferencia(@PathParam("viajeroId") Long booksId, PreferenciaDTO sitio) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PreferenciaResource createPreferencia: input: {0}", sitio);
        PreferenciaDTO nuevoPreferenciaDTO = new PreferenciaDTO(logic.createPreferencia(booksId, sitio.toEntity()));
        LOGGER.log(Level.INFO, "PreferenciaResource createPreferencia: output: {0}", nuevoPreferenciaDTO);
        return nuevoPreferenciaDTO;
    }

    /**
     * Busca y devuelve todas las reseñas que existen en un libro.
     *
     * @param viajeroId
     * @return JSONArray {@link PreferenciaDTO} - Las reseñas encontradas en el
     * libro. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<PreferenciaDTO> getPreferencias(@PathParam("viajeroId") Long viajeroId) {
        LOGGER.log(Level.INFO, "PreferenciaResource getPreferencia: input: {0}", viajeroId);
        List<PreferenciaDTO> listaDTOs = listEntity2DTO(logic.getPreferencias(viajeroId));
        LOGGER.log(Level.INFO, "EditorialBooksResource getBooks: output: {0}", listaDTOs);
        return listaDTOs;
    }

    /**
     * Busca y devuelve la reseña con el ID recibido en la URL, relativa a un
     * libro.
     *
     * @param booksId El ID del libro del cual se buscan las reseñas
     * @param preferenciaId El ID de la reseña que se busca
     * @return {@link PreferenciaDTO} - La reseña encontradas en el libro.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la reseña.
     */
    @GET
    @Path("{preferencias: \\d+}")
    public PreferenciaDTO getPreferencia(@PathParam("viajeroId") Long booksId, @PathParam("preferencias") Long preferenciaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PreferenciaResource getPreferencia: input: {0}", preferenciaId);
        PreferenciaEntity entity = logic.getPreferencia(booksId, preferenciaId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + booksId + "/preferencia/" + preferenciaId + " no existe.", 404);
        }
        PreferenciaDTO sitioDTO = new PreferenciaDTO(entity);
        LOGGER.log(Level.INFO, "PreferenciaResource getPreferencia: output: {0}", sitioDTO);
        return sitioDTO;
    }

    /**
     * Actualiza una reseña con la informacion que se recibe en el cuerpo de la
     * petición y se regresa el objeto actualizado.
     *
     * @param booksId El ID del libro del cual se guarda la reseña
     * @param preferenciaId El ID de la reseña que se va a actualizar
     * @param sitio {@link PreferenciaDTO} - La reseña que se desea guardar.
     * @return JSON {@link PreferenciaDTO} - La reseña actualizada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la reseña.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la reseña.
     */
    @PUT
    @Path("{preferencias: \\d+}")
    public PreferenciaDTO updatePreferencia(@PathParam("viajeroId") Long booksId, @PathParam("preferencias") Long preferenciaId, PreferenciaDTO sitio) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PreferenciaResource updatePreferencia: input: booksId: {0} , preferenciaId: {1} , sitio:{2}", new Object[]{booksId, preferenciaId, sitio});
        if (preferenciaId.equals(sitio.getIdPreferencia())) {
            throw new BusinessLogicException("Los ids del Preferencia no coinciden.");
        }
        PreferenciaEntity entity = logic.getPreferencia(booksId, preferenciaId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + booksId + "/preferencia/" + preferenciaId + " no existe.", 404);

        }
        PreferenciaDTO sitioDTO = new PreferenciaDTO(logic.updatePreferencia(booksId, sitio.toEntity()));
        LOGGER.log(Level.INFO, "PreferenciaResource updatePreferencia: output:{0}", sitioDTO);
        return sitioDTO;

    }

    /**
     * Borra la reseña con el id asociado recibido en la URL.
     *
     * @param booksId El ID del libro del cual se va a eliminar la reseña.
     * @param preferenciaId El ID de la reseña que se va a eliminar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la reseña.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la reseña.
     */
    
    
    
    
    
    
    
    
    
    
    @DELETE
    @Path("{preferencias: \\d+}")
    public void deletePreferencia(@PathParam("viajeroId") Long booksId, @PathParam("preferencias") Long preferenciaId) throws BusinessLogicException {
        PreferenciaEntity entity = logic.getPreferencia(booksId, preferenciaId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + booksId + "/preferencia/" + preferenciaId + " no existe.", 404);
        }
                             logic.deletePreferencia(booksId, preferenciaId);
    }

    /**
     * Lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos PrizeEntity a una lista de
     * objetos PreferenciaDTO (json)
     *
     * @param entityList corresponde a la lista de reseñas de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de reseñas en forma DTO (json)
     */
    private List<PreferenciaDTO> listEntity2DTO(List<PreferenciaEntity> entityList) {
        List<PreferenciaDTO> list = new ArrayList<>();
        for (PreferenciaEntity entity : entityList) {
            list.add(new PreferenciaDTO(entity));
        }
        return list;
    }
}
