/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;


import co.edu.uniandes.csw.turismo.dtos.SitioTuristicosDTO;
import co.edu.uniandes.csw.turismo.ejb.SitioTuristicoLogic;
import co.edu.uniandes.csw.turismo.entities.SitioTuristicoEntity;
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
 * @author David Fonseca
 */

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SitioTuristicoResource {
    private static final Logger LOGGER=Logger.getLogger(SitioTuristicoResource.class.getName());
    @Inject
    private SitioTuristicoLogic logic;
  
    
    
    
     @POST
    public SitioTuristicosDTO createSitioTuristico(@PathParam("booksId") Long booksId, SitioTuristicosDTO sitio) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "SitioTuristicoResource createSitioTuristico: input: {0}", sitio);
        SitioTuristicosDTO nuevoSitioTuristicoDTO = new SitioTuristicosDTO(logic.createSitioTuritico(booksId, sitio.toEntity()));
        LOGGER.log(Level.INFO, "SitioTuristicoResource createSitioTuristico: output: {0}", nuevoSitioTuristicoDTO);
        return nuevoSitioTuristicoDTO;
    }

    /**
     * Busca y devuelve todas las reseñas que existen en un libro.
     *
     * @param ciudadId
     * @return JSONArray {@link SitioTuristicoDTO} - Las reseñas encontradas en el
     * libro. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<SitioTuristicosDTO> getSitioTuristicos(@PathParam("ciudadId") Long ciudadId) {
        LOGGER.log(Level.INFO, "SitioTuristicoResource getSitioTuristicos: input: {0}", ciudadId);
        List<SitioTuristicosDTO> listaDTOs = listEntity2DTO(logic.getSitioTuriticos(ciudadId));
        LOGGER.log(Level.INFO, "EditorialBooksResource getBooks: output: {0}", listaDTOs);
        return listaDTOs;
    }

    /**
     * Busca y devuelve la reseña con el ID recibido en la URL, relativa a un
     * libro.
     *
     * @param booksId El ID del libro del cual se buscan las reseñas
     * @param sitiosId El ID de la reseña que se busca
     * @return {@link SitioTuristicoDTO} - La reseña encontradas en el libro.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la reseña.
     */
    @GET
    @Path("{sitiosId: \\d+}")
    public SitioTuristicosDTO getSitioTuristico(@PathParam("booksId") Long booksId, @PathParam("sitiosId") Long sitiosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "SitioTuristicoResource getSitioTuristico: input: {0}", sitiosId);
        SitioTuristicoEntity entity = logic.getSitioTuritico(booksId, sitiosId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + booksId + "/sitios/" + sitiosId + " no existe.", 404);
        }
        SitioTuristicosDTO sitioDTO = new SitioTuristicosDTO(entity);
        LOGGER.log(Level.INFO, "SitioTuristicoResource getSitioTuristico: output: {0}", sitioDTO);
        return sitioDTO;
    }

    /**
     * Actualiza una reseña con la informacion que se recibe en el cuerpo de la
     * petición y se regresa el objeto actualizado.
     *
     * @param booksId El ID del libro del cual se guarda la reseña
     * @param sitiosId El ID de la reseña que se va a actualizar
     * @param sitio {@link SitioTuristicoDTO} - La reseña que se desea guardar.
     * @return JSON {@link SitioTuristicoDTO} - La reseña actualizada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la reseña.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la reseña.
     */
    @PUT
    @Path("{sitiosId: \\d+}")
    public SitioTuristicosDTO updateSitioTuristico(@PathParam("ciudadId") Long booksId, @PathParam("sitiosId") Long sitiosId, SitioTuristicosDTO sitio) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "SitioTuristicoResource updateSitioTuristico: input: booksId: {0} , sitiosId: {1} , sitio:{2}", new Object[]{booksId, sitiosId, sitio});
        if (sitiosId.equals(sitio.getId())) {
            throw new BusinessLogicException("Los ids del SitioTuristico no coinciden.");
        }
        SitioTuristicoEntity entity = logic.getSitioTuritico(booksId, sitiosId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + booksId + "/sitios/" + sitiosId + " no existe.", 404);

        }
        SitioTuristicosDTO sitioDTO = new SitioTuristicosDTO(logic.updateSitioTuritico(booksId, sitio.toEntity()));
        LOGGER.log(Level.INFO, "SitioTuristicoResource updateSitioTuristico: output:{0}", sitioDTO);
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
    public void deleteSitioTuristico(@PathParam("booksId") Long booksId, @PathParam("sitiosId") Long sitiosId) throws BusinessLogicException {
        SitioTuristicoEntity entity = logic.getSitioTuritico(booksId, sitiosId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + booksId + "/sitios/" + sitiosId + " no existe.", 404);
        }
        logic.deleteSitioTuritico(booksId, sitiosId);
    }

    /**
     * Lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos PrizeEntity a una lista de
     * objetos SitioTuristicoDTO (json)
     *
     * @param entityList corresponde a la lista de reseñas de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de reseñas en forma DTO (json)
     */
    private List<SitioTuristicosDTO> listEntity2DTO(List<SitioTuristicoEntity> entityList) {
        List<SitioTuristicosDTO> list = new ArrayList<>();
        for (SitioTuristicoEntity entity : entityList) {
            list.add(new SitioTuristicosDTO(entity));
        }
        return list;
    }
    
    
}
