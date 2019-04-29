/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.SitioTuristicosDTO;
import co.edu.uniandes.csw.turismo.ejb.CiudadSitiosTuristicosLogic;
import co.edu.uniandes.csw.turismo.ejb.SitioTuristicoLogic;
import co.edu.uniandes.csw.turismo.entities.SitioTuristicoEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author David Fonseca
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CiudadSitiosTuristicosResource {
    private static final Logger LOGGER = Logger.getLogger(CiudadSitiosTuristicosResource.class.getName());

    @Inject
    private CiudadSitiosTuristicosLogic ciudadSitioLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private SitioTuristicoLogic sitioLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda un libro dentro de una editorial con la informacion que recibe el
     * la URL. Se devuelve el libro que se guarda en la editorial.
     *
     * @param ciudadId
     * @param sitioId
     * @return 
     */
    @POST
    @Path("{sitiosId: \\d+}")
    public SitioTuristicosDTO addSitio(@PathParam("ciudadId") Long ciudadId, @PathParam("sitiosId") Long sitioId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EditorialBooksResource addBook: input: editorialsID: {0} , booksId: {1}", new Object[]{ciudadId, sitioId});
        if (sitioLogic.getSitio(sitioId) == null) {
            throw new WebApplicationException("El recurso /books/" + sitioId + " no existe.", 404);
        }
        SitioTuristicosDTO sitioDTO = new SitioTuristicosDTO(ciudadSitioLogic.addSitioTuristico(sitioId, ciudadId));
        LOGGER.log(Level.INFO, "EditorialBooksResource addBook: output: {0}", sitioDTO);
        return sitioDTO;
    }

    /**
     * Busca y devuelve todos los libros que existen en la editorial.
     *
     * @param ciudadId
     * @return JSONArray {@link BookDetailDTO} - Los libros encontrados en la
     * editorial. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<SitioTuristicosDTO> getSitios(@PathParam("ciudadId") Long ciudadId) {
        LOGGER.log(Level.INFO, "EditorialBooksResource getBooks: input: {0}", ciudadId);
        List<SitioTuristicosDTO> listaDetailDTOs = sitiosListEntity2DTO(ciudadSitioLogic.getSitioTuristicos(ciudadId));
        LOGGER.log(Level.INFO, "EditorialBooksResource getBooks: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }

    /**
     * Busca el libro con el id asociado dentro de la editorial con id asociado.
     *
     * @param ciudadId
     * @param sitioId
     * @return JSON {@link BookDetailDTO} - El libro buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro en la
     * editorial.
     */
    @GET
    @Path("{sitiosId: \\d+}")
    public SitioTuristicosDTO getSitio(@PathParam("ciudadId") Long ciudadId, @PathParam("sitiosId") Long sitioId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EditorialBooksResource getBook: input: editorialsID: {0} , booksId: {1}", new Object[]{ciudadId, sitioId});
        if (sitioLogic.getSitio(sitioId) == null) {
            throw new WebApplicationException("El recurso /editorials/" + ciudadId + "/books/" + sitioId + " no existe.", 404);
        }
        SitioTuristicosDTO sitioDto = new SitioTuristicosDTO(ciudadSitioLogic.getSitioTuristico(ciudadId, sitioId));
        LOGGER.log(Level.INFO, "EditorialBooksResource getBook: output: {0}", sitioDto);
        return sitioDto;
    }

    /**
     * Remplaza las instancias de Book asociadas a una instancia de Editorial
     *
     * @param editorialsId Identificador de la editorial que se esta
     * remplazando. Este debe ser una cadena de dígitos.
     * @param books JSONArray {@link BookDTO} El arreglo de libros nuevo para la
     * editorial.
     * @return JSON {@link BookDTO} - El arreglo de libros guardado en la
     * editorial.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @PUT
    public List<SitioTuristicosDTO> replaceSitio(@PathParam("ciudadId") Long ciudadId, List<SitioTuristicosDTO> sitios) throws BusinessLogicException {
        for (SitioTuristicosDTO book : sitios) {
            if (sitioLogic.getSitio(book.getId()) == null) {
                throw new WebApplicationException("El recurso /books/" + book.getId() + " no existe.", 404);
            }
        }
        List<SitioTuristicosDTO> listaDetailDTOs = sitiosListEntity2DTO(ciudadSitioLogic.replaceSitioTuristicos(ciudadId, sitiosListDTO2Entity(sitios)));
        LOGGER.log(Level.INFO, "EditorialBooksResource replaceBooks: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }

    /**
     * Convierte una lista de BookEntity a una lista de BookDetailDTO.
     *
     * @param entityList Lista de BookEntity a convertir.
     * @return Lista de BookDTO convertida.
     */
    private List<SitioTuristicosDTO> sitiosListEntity2DTO(List<SitioTuristicoEntity> entityList) {
        List<SitioTuristicosDTO> list = new ArrayList();
        for (SitioTuristicoEntity entity : entityList) {
            list.add(new SitioTuristicosDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de BookDetailDTO a una lista de BookEntity.
     *
     * @param dtos Lista de BookDetailDTO a convertir.
     * @return Lista de BookEntity convertida.
     */
    private List<SitioTuristicoEntity> sitiosListDTO2Entity(List<SitioTuristicosDTO> dtos) {
        List<SitioTuristicoEntity> list = new ArrayList<>();
        for (SitioTuristicosDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}
