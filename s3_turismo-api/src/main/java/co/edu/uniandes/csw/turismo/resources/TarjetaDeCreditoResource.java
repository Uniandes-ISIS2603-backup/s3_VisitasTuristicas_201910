/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.TarjetaDeCreditoDTO;
import co.edu.uniandes.csw.turismo.ejb.TarjetaDeCreditoLogic;
import co.edu.uniandes.csw.turismo.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
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
 * @author estudiante
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TarjetaDeCreditoResource {
    
    
   private static final Logger LOGGER=Logger.getLogger(TarjetaDeCreditoResource.class.getName());
    @Inject
    private TarjetaDeCreditoLogic logic;
  
    
    
    
     @POST
    public TarjetaDeCreditoDTO createTarjetaDeCredito(@PathParam("viajeroId") Long booksId, TarjetaDeCreditoDTO sitio) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TarjetaDeCreditoResource createTarjetaDeCredito: input: {0}", sitio);
        TarjetaDeCreditoDTO nuevoTarjetaDeCreditoDTO = new TarjetaDeCreditoDTO(logic.createTarjetaDeCredito(booksId, sitio.toEntity()));
        LOGGER.log(Level.INFO, "TarjetaDeCreditoResource createTarjetaDeCredito: output: {0}", nuevoTarjetaDeCreditoDTO);
        return nuevoTarjetaDeCreditoDTO;
    }

    /**
     * Busca y devuelve todas las reseñas que existen en un libro.
     *
     * @param viajeroId
     * @return JSONArray {@link TarjetaDeCreditoDTO} - Las reseñas encontradas en el
     * libro. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<TarjetaDeCreditoDTO> getTarjetasDeCredito(@PathParam("viajeroId") Long viajeroId) {

        LOGGER.log(Level.INFO, "TarjetaDeCreditoResource getTarjetaDeCredito: input: {0}", viajeroId);
        List<TarjetaDeCreditoDTO> listaDTOs = listEntity2DTO(logic.getTarjetaDeCreditos(viajeroId));
        LOGGER.log(Level.INFO, "EditorialBooksResource getBooks: output: {0}", listaDTOs);
        return listaDTOs;
    }

    /**
     * Busca y devuelve la reseña con el ID recibido en la URL, relativa a un
     * libro.
     *
     * @param booksId El ID del libro del cual se buscan las reseñas
     * @return {@link TarjetaDeCreditoDTO} - La reseña encontradas en el libro.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la reseña.
     */
    @GET
    @Path("{tarjeta: \\d+}")
    public TarjetaDeCreditoDTO getTarjetaDeCredito(@PathParam("viajeroId") Long booksId, @PathParam("tarjeta") Long sitiosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TarjetaDeCreditoResource getTarjetaDeCredito: input: {0}", sitiosId);
        TarjetaDeCreditoEntity entity = logic.getTarjetaDeCredito(booksId, sitiosId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + booksId + "/sitios/" + sitiosId + " no existe.", 404);

        }
        TarjetaDeCreditoDTO sitioDTO = new TarjetaDeCreditoDTO(entity);
        LOGGER.log(Level.INFO, "TarjetaDeCreditoResource getTarjetaDeCredito: output: {0}", sitioDTO);
        return sitioDTO;
    }

    /**
     * Actualiza una reseña con la informacion que se recibe en el cuerpo de la
     * petición y se regresa el objeto actualizado.
     *
     * @param booksId El ID del libro del cual se guarda la reseña
     * @param sitiosId El ID de la reseña que se va a actualizar
     * @param tarjetaId El ID de la reseña que se va a actualizar
     * @param sitio {@link TarjetaDeCreditoDTO} - La reseña que se desea guardar.
     * @return JSON {@link TarjetaDeCreditoDTO} - La reseña actualizada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la reseña.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la reseña.
     */
    @PUT
    @Path("{tarjeta: \\d+}")
    public TarjetaDeCreditoDTO updateTarjetaDeCredito(@PathParam("viajeroId") Long booksId, @PathParam("tarjeta") Long sitiosId, TarjetaDeCreditoDTO sitio) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TarjetaDeCreditoResource updateTarjetaDeCredito: input: booksId: {0} , sitiosId: {1} , sitio:{2}", new Object[]{booksId, sitiosId, sitio});
        if (sitiosId.equals(sitio.getIdTarjetaDeCredito())) {
            throw new BusinessLogicException("Los ids del TarjetaDeCredito no coinciden.");
        }
        TarjetaDeCreditoEntity entity = logic.getTarjetaDeCredito(booksId, sitiosId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + booksId + "/sitios/" + sitiosId + " no existe.", 404);

        }

        TarjetaDeCreditoDTO sitioDTO = new TarjetaDeCreditoDTO(logic.updateTarjetaDeCredito(booksId, sitio.toEntity()));
        LOGGER.log(Level.INFO, "TarjetaDeCreditoResource updateTarjetaDeCredito: output:{0}", sitioDTO);
        return sitioDTO;

    }

    /**
     * Borra la reseña con el id asociado recibido en la URL.
     *
     * @param booksId El ID del libro del cual se va a eliminar la reseña.
     * @param sitiosId El ID de la reseña que se va a eliminar.

     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la reseña.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la reseña.
     */
    @DELETE
    @Path("{tarjeta: \\d+}")
    public void deleteTarjetaDeCredito(@PathParam("viajeroId") Long booksId, @PathParam("tarjeta") Long sitiosId) throws BusinessLogicException {
        TarjetaDeCreditoEntity entity = logic.getTarjetaDeCredito(booksId, sitiosId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + booksId + "/sitios/" + sitiosId + " no existe.", 404);
        }
        logic.deleteTarjetaDeCredito(booksId, sitiosId);

    }

    /**
     * Lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos PrizeEntity a una lista de
     * objetos TarjetaDeCreditoDTO (json)
     *
     * @param entityList corresponde a la lista de reseñas de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de reseñas en forma DTO (json)
     */
    private List<TarjetaDeCreditoDTO> listEntity2DTO(List<TarjetaDeCreditoEntity> entityList) {
        List<TarjetaDeCreditoDTO> list = new ArrayList<>();
        for (TarjetaDeCreditoEntity entity : entityList) {
            list.add(new TarjetaDeCreditoDTO(entity));
        }
        return list;
    }

}
