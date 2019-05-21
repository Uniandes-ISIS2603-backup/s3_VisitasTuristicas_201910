/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    public FacturaDTO createFactura(@PathParam("viajeroId") Long booksId, FacturaDTO factura) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "FacturaResource createFactura: input: {0}", factura);
        FacturaDTO nuevoFacturaDTO = new FacturaDTO(logic.createFactura(booksId, factura.toEntity()));
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
        List<FacturaDTO> listaDTOs = listEntity2DTO(logic.getFacturas(viajeroId));
        LOGGER.log(Level.INFO, "EditorialBooksResource getBooks: output: {0}", listaDTOs);
        return listaDTOs;
    }

    /**
     * Busca y devuelve la reseña con el ID recibido en la URL, relativa a un
     * libro.
     *
     * @param booksId El ID del libro del cual se buscan las reseñas
     * @param facturasId El ID de la reseña que se busca
     * @return {@link FacturaDTO} - La reseña encontradas en el libro.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la reseña.
     */
    @GET
    @Path("{facturas: \\d+}")
    public FacturaDTO getFactura(@PathParam("viajeroId") Long booksId, @PathParam("facturas") Long facturasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "FacturaResource getFactura: input: {0}", facturasId);
        FacturaEntity entity = logic.getFactura(booksId, facturasId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + booksId + "/facturas/" + facturasId + " no existe.", 404);
        }
        FacturaDTO facturaDTO = new FacturaDTO(entity);
        LOGGER.log(Level.INFO, "FacturaResource getFactura: output: {0}", facturaDTO);
        return facturaDTO;
    }

    /**
     * Actualiza una reseña con la informacion que se recibe en el cuerpo de la
     * petición y se regresa el objeto actualizado.
     *
     * @param booksId El ID del libro del cual se guarda la reseña
     * @param facturasId El ID de la reseña que se va a actualizar
     * @param factura {@link FacturaDTO} - La reseña que se desea guardar.
     * @return JSON {@link FacturaDTO} - La reseña actualizada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la reseña.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la reseña.
     */
    @PUT
    @Path("{facturasId: \\d+}")
    public FacturaDTO updateFactura(@PathParam("viajeroId") Long booksId, @PathParam("facturasId") Long facturasId, FacturaDTO factura) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "FacturaResource updateFactura: input: booksId: {0} , facturasId: {1} , factura:{2}", new Object[]{booksId, facturasId, factura});
        if (facturasId.equals(factura.getId())) {
            throw new BusinessLogicException("Los ids del Factura no coinciden.");
        }
        FacturaEntity entity = logic.getFactura(booksId, facturasId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + booksId + "/facturas/" + facturasId + " no existe.", 404);

        }
        FacturaDTO facturaDTO = new FacturaDTO(logic.updateFactura(booksId, factura.toEntity()));
        LOGGER.log(Level.INFO, "FacturaResource updateFactura: output:{0}", facturaDTO);
        return facturaDTO;

    }

    /**
     * Borra la reseña con el id asociado recibido en la URL.
     *
     * @param booksId El ID del libro del cual se va a eliminar la reseña.
     * @param facturasId El ID de la reseña que se va a eliminar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la reseña.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la reseña.
     */
    @DELETE
    @Path("{facturasId: \\d+}")
    public void deleteFactura(@PathParam("viajeroId") Long booksId, @PathParam("facturasId") Long facturasId) throws BusinessLogicException {
        FacturaEntity entity = logic.getFactura(booksId, facturasId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + booksId + "/facturas/" + facturasId + " no existe.", 404);
        }
        logic.deleteFactura(booksId, facturasId);
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
