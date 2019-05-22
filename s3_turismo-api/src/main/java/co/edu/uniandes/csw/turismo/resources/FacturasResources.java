package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.FacturaDTO;
import co.edu.uniandes.csw.turismo.ejb.FacturaLogic;
import co.edu.uniandes.csw.turismo.entities.FacturaEntity;
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
 * @author estudiante
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FacturasResources {
    
  private static final Logger LOGGER=Logger.getLogger(FacturasResources.class.getName());
   
  
  
  @Inject
    private FacturaLogic logic;
  
    
    
    
     @POST
    public FacturaDTO createFactura(@PathParam("viajeroId") Long booksId, FacturaDTO sitio) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "FacturaResource createFactura: input: {0}", sitio);
        FacturaDTO nuevoFacturaDTO = new FacturaDTO(logic.createSitioTuritico(booksId, sitio.toEntity()));
        LOGGER.log(Level.INFO, "FacturaResource createFactura: output: {0}", nuevoFacturaDTO);
        return nuevoFacturaDTO;
    }

    /**
     * Busca y devuelve todas las reseñas que existen en un libro.
     *
     * @param viajeroId
     * @return JSONArray {@link FacturaDTO} - Las reseñas encontradas en el
     * libro. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<FacturaDTO> getFactura(@PathParam("viajeroId") Long viajeroId) {
        LOGGER.log(Level.INFO, "FacturaResource getFactura: input: {0}", viajeroId);
        List<FacturaDTO> listaDTOs = listEntity2DTO(logic.getSitioTuriticos(viajeroId));
        LOGGER.log(Level.INFO, "EditorialBooksResource getBooks: output: {0}", listaDTOs);
        return listaDTOs;
    }

    /**
     * Busca y devuelve la reseña con el ID recibido en la URL, relativa a un
     * libro.
     *
     * @param booksId El ID del libro del cual se buscan las reseñas
     * @param sitiosId El ID de la reseña que se busca
     * @return {@link FacturaDTO} - La reseña encontradas en el libro.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la reseña.
     */
    @GET
    @Path("{factura: \\d+}")
    public FacturaDTO getFactura(@PathParam("viajeroId") Long booksId, @PathParam("factura") Long sitiosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "FacturaResource getFactura: input: {0}", sitiosId);
        FacturaEntity entity = logic.getSitioTuritico(booksId, sitiosId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + booksId + "/sitios/" + sitiosId + " no existe.", 404);
        }
        FacturaDTO sitioDTO = new FacturaDTO(entity);
        LOGGER.log(Level.INFO, "FacturaResource getFactura: output: {0}", sitioDTO);
        return sitioDTO;
    }

    /**
     * Actualiza una reseña con la informacion que se recibe en el cuerpo de la
     * petición y se regresa el objeto actualizado.
     *
     * @param booksId El ID del libro del cual se guarda la reseña
     * @param sitiosId El ID de la reseña que se va a actualizar
     * @param sitio {@link FacturaDTO} - La reseña que se desea guardar.
     * @return JSON {@link FacturaDTO} - La reseña actualizada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la reseña.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la reseña.
     */
    @PUT
    @Path("{sitiosId: \\d+}")
    public FacturaDTO updateFactura(@PathParam("viajeroId") Long booksId, @PathParam("sitiosId") Long sitiosId, FacturaDTO sitio) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "FacturaResource updateFactura: input: booksId: {0} , sitiosId: {1} , sitio:{2}", new Object[]{booksId, sitiosId, sitio});
        if (sitiosId.equals(sitio.getId())) {
            throw new BusinessLogicException("Los ids del Factura no coinciden.");
        }
        FacturaEntity entity = logic.getSitioTuritico(booksId, sitiosId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + booksId + "/sitios/" + sitiosId + " no existe.", 404);

        }
        FacturaDTO sitioDTO = new FacturaDTO(logic.updateSitioTuritico(booksId, sitio.toEntity()));
        LOGGER.log(Level.INFO, "FacturaResource updateFactura: output:{0}", sitioDTO);
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
    @Path("{sitiosId: \\d+}")
    public void deleteFactura(@PathParam("viajeroId") Long booksId, @PathParam("sitiosId") Long sitiosId) throws BusinessLogicException {
        FacturaEntity entity = logic.getSitioTuritico(booksId, sitiosId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + booksId + "/sitios/" + sitiosId + " no existe.", 404);
        }
        logic.deleteSitioTuritico(booksId, sitiosId);
    }

    /**
     * Lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos PrizeEntity a una lista de
     * objetos FacturaDTO (json)
     *
     * @param entityList corresponde a la lista de reseñas de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de reseñas en forma DTO (json)
     */
    private List<FacturaDTO> listEntity2DTO(List<FacturaEntity> entityList) {
        List<FacturaDTO> list = new ArrayList<>();
        for (FacturaEntity entity : entityList) {
            list.add(new FacturaDTO(entity));
        }
        return list;
    }
    
}