/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.CiudadDetailDTO;
import co.edu.uniandes.csw.turismo.ejb.CiudadLogic;

import co.edu.uniandes.csw.turismo.ejb.PaisCiudadLogic;
import co.edu.uniandes.csw.turismo.entities.CiudadEntity;

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
public class PaisCiudadResource {
    private static final Logger LOGGER = Logger.getLogger(PaisCiudadResource.class.getName());

    @Inject
    private PaisCiudadLogic paisCiudadLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private CiudadLogic ciudadLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda un libro dentro de una editorial con la informacion que recibe el
     * la URL. Se devuelve el libro que se guarda en la editorial.
     *
     * @param ciudadId
     * @param ciudadId
     * @return 
     */
    @POST
    @Path("{ciudadsId: \\d+}")
    public CiudadDetailDTO addCiudad(@PathParam("paisId") Long paisId, @PathParam("ciudadsId") Long ciudadId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EditorialBooksResource addBook: input: editorialsID: {0} , booksId: {1}", new Object[]{ciudadId, ciudadId});
        if (ciudadLogic.getCiudad(ciudadId) == null) {
            throw new WebApplicationException("El recurso /books/" + ciudadId + " no existe.", 404);
        }
        CiudadDetailDTO ciudadDTO = new CiudadDetailDTO(paisCiudadLogic.addCiudad(ciudadId, ciudadId));
        LOGGER.log(Level.INFO, "EditorialBooksResource addBook: output: {0}", ciudadDTO);
        return ciudadDTO;
    }

    /**
     * Busca y devuelve todos los libros que existen en la editorial.
     *
     * @param ciudadId
     * @return JSONArray {@link BookDetailDTO} - Los libros encontrados en la
     * editorial. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<CiudadDetailDTO> getCiudads(@PathParam("ciudadId") Long ciudadId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EditorialBooksResource getBooks: input: {0}", ciudadId);
        List<CiudadDetailDTO> listaDetailDTOs = ciudadsListEntity2DTO(paisCiudadLogic.getCiudads(ciudadId));
        LOGGER.log(Level.INFO, "EditorialBooksResource getBooks: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }

    /**
     * Busca el libro con el id asociado dentro de la editorial con id asociado.
     *
     * @param paisId
     * @param ciudadId
     * @return JSON {@link BookDetailDTO} - El libro buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro en la
     * editorial.
     */
    @GET
    @Path("{ciudadsId: \\d+}")
    public CiudadDetailDTO getCiudad(@PathParam("paisId") Long paisId, @PathParam("ciudadsId") Long ciudadId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EditorialBooksResource getBook: input: editorialsID: {0} , booksId: {1}", new Object[]{ciudadId, ciudadId});
        if (ciudadLogic.getCiudad(ciudadId) == null) {
            throw new WebApplicationException("El recurso /editorials/" + ciudadId + "/books/" + ciudadId + " no existe.", 404);
        }
        CiudadDetailDTO ciudadDto = new CiudadDetailDTO(paisCiudadLogic.getCiudad(ciudadId, ciudadId));
        LOGGER.log(Level.INFO, "EditorialBooksResource getBook: output: {0}", ciudadDto);
        return ciudadDto;
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
    public List<CiudadDetailDTO> replaceCiudad(@PathParam("ciudadId") Long ciudadId, List<CiudadDetailDTO> ciudads) throws BusinessLogicException {
        for (CiudadDetailDTO book : ciudads) {
            if (ciudadLogic.getCiudad(book.getID()) == null) {
                throw new WebApplicationException("El recurso /books/" + book.getID() + " no existe.", 404);
            }
        }
        List<CiudadDetailDTO> listaDetailDTOs = ciudadsListEntity2DTO(paisCiudadLogic.replaceCiudads(ciudadId, ciudadsListDTO2Entity(ciudads)));
        LOGGER.log(Level.INFO, "EditorialBooksResource replaceBooks: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }

    /**
     * Convierte una lista de BookEntity a una lista de BookDetailDTO.
     *
     * @param entityList Lista de BookEntity a convertir.
     * @return Lista de BookDTO convertida.
     */
    private List<CiudadDetailDTO> ciudadsListEntity2DTO(List<CiudadEntity> entityList) throws BusinessLogicException {
        List<CiudadDetailDTO> list = new ArrayList();
        for (CiudadEntity entity : entityList) {
            list.add(new CiudadDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de BookDetailDTO a una lista de BookEntity.
     *
     * @param dtos Lista de BookDetailDTO a convertir.
     * @return Lista de BookEntity convertida.
     */
    private List<CiudadEntity> ciudadsListDTO2Entity(List<CiudadDetailDTO> dtos) {
        List<CiudadEntity> list = new ArrayList<>();
        for (CiudadDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}
