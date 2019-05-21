/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.TarjetaDeCreditoDTO;
import co.edu.uniandes.csw.turismo.ejb.TarjetaDeCreditoLogic;
import co.edu.uniandes.csw.turismo.ejb.ViajeroTarjetaDeCreditoLogic;
import co.edu.uniandes.csw.turismo.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.turismo.entities.ViajeroEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.TarjetaDeCreditoPersistence;
import co.edu.uniandes.csw.turismo.persistence.ViajeroPersistence;
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
 * @tarjetaDeCredito estudiante
 */

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ViajeroTarjetaDeCreditoResource {

   private static final Logger LOGGER = Logger.getLogger(ViajeroTarjetaDeCreditoResource.class.getName());

    @Inject
    private ViajeroTarjetaDeCreditoLogic viajeroTarjetaDeCreditosLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private TarjetaDeCreditoLogic tarjetaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda un libro dentro de una viajero con la informacion que recibe el
     * la URL. Se devuelve el libro que se guarda en la viajero.
     *
     * @param viajerosId Identificador de la viajero que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param tarjetasId Identificador del libro que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link TarjetaDeCreditoDTO} - El libro guardado en la viajero.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    @Path("{tarjetas: \\d+}")
    public TarjetaDeCreditoDTO addTarjetaDeCredito(@PathParam("viajero") Long viajerosId, @PathParam("tarjetas") Long tarjetasId) {
        LOGGER.log(Level.INFO, "ViajeroTarjetaDeCreditosResource addTarjetaDeCredito: input: viajerosID: {0} , tarjetasId: {1}", new Object[]{viajerosId, tarjetasId});
        if (tarjetaLogic.getTarjetaDeCredito(tarjetasId) == null) {
            throw new WebApplicationException("El recurso /tarjetas/" + tarjetasId + " no existe.", 404);
        }
        TarjetaDeCreditoDTO tarjetaDTO = new TarjetaDeCreditoDTO(viajeroTarjetaDeCreditosLogic.addTarjetaDeCredito(tarjetasId, viajerosId));
        LOGGER.log(Level.INFO, "ViajeroTarjetaDeCreditosResource addTarjetaDeCredito: output: {0}", tarjetaDTO);
        return tarjetaDTO;
    }

    /**
     * Busca y devuelve todos los libros que existen en la viajero.
     *
     * @param viajerosId Identificador de la viajero que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSONArray {@link TarjetaDeCreditoDetailDTO} - Los libros encontrados en la
     * viajero. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<TarjetaDeCreditoDTO> getTarjetasDeCreditos(@PathParam("viajero") Long viajerosId) {
        LOGGER.log(Level.INFO, "ViajeroTarjetaDeCreditosResource getTarjetaDeCreditos: input: {0}", viajerosId);
        List<TarjetaDeCreditoEntity> tarje = viajeroTarjetaDeCreditosLogic.getTarjetaDeCreditos(viajerosId);
        LOGGER.log(Level.INFO, "Numero de tarjetas de la lista de entidades--------------- {0}", tarje.size());
        List<TarjetaDeCreditoDTO> listaDetailDTOs = tarjetasListEntity2DTO(tarje);
        LOGGER.log(Level.INFO, "Numero de tarjetas de la lista de DTOS------------ {0}", listaDetailDTOs.size());

        
        
        LOGGER.log(Level.INFO, "ViajeroTarjetaDeCreditosResource getTarjetaDeCreditos: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }

    /**
     * Busca el libro con el id asociado dentro de la viajero con id asociado.
     *
     * @param viajerosId Identificador de la viajero que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @param tarjetasId Identificador del libro que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link TarjetaDeCreditoDetailDTO} - El libro buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro en la
     * viajero.
     */
    @GET
    @Path("{tarjetas: \\d+}")
    public TarjetaDeCreditoDTO getTarjetaDeCredito(@PathParam("viajero") Long viajerosId, @PathParam("tarjetas") Long tarjetasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ViajeroTarjetaDeCreditosResource getTarjetaDeCredito: input: viajerosID: {0} , tarjetasId: {1}", new Object[]{viajerosId, tarjetasId});
        if (tarjetaLogic.getTarjetaDeCredito(tarjetasId) == null) {
            throw new WebApplicationException("El recurso /viajeros/" + viajerosId + "/tarjetas/" + tarjetasId + " no existe.", 404);
        }
        TarjetaDeCreditoDTO tarjetaDetailDTO = new TarjetaDeCreditoDTO(viajeroTarjetaDeCreditosLogic.getTarjetaDeCredito(viajerosId, tarjetasId));
        LOGGER.log(Level.INFO, "ViajeroTarjetaDeCreditosResource getTarjetaDeCredito: output: {0}", tarjetaDetailDTO);
        return tarjetaDetailDTO;
    }

    /**
     * Remplaza las instancias de TarjetaDeCredito asociadas a una instancia de Viajero
     *
     * @param viajerosId Identificador de la viajero que se esta
     * remplazando. Este debe ser una cadena de dígitos.
     * @param tarjetas JSONArray {@link TarjetaDeCreditoDTO} El arreglo de libros nuevo para la
     * viajero.
     * @return JSON {@link TarjetaDeCreditoDTO} - El arreglo de libros guardado en la
     * viajero.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @PUT
    public List<TarjetaDeCreditoDTO> replaceTarjetaDeCreditos(@PathParam("viajero") Long viajerosId, List<TarjetaDeCreditoDTO> tarjetas) {
        LOGGER.log(Level.INFO, "ViajeroTarjetaDeCreditosResource replaceTarjetaDeCreditos: input: viajerosId: {0} , tarjetas: {1}", new Object[]{viajerosId, tarjetas});
        for (TarjetaDeCreditoDTO tarjeta : tarjetas) {
            if (tarjetaLogic.getTarjetaDeCredito(tarjeta.getIdTarjetaDeCredito()) == null) {
                throw new WebApplicationException("El recurso /tarjetas/" + tarjeta.getIdTarjetaDeCredito()+ " no existe.", 404);
            }
        }
        List<TarjetaDeCreditoDTO> listaDetailDTOs = tarjetasListEntity2DTO(viajeroTarjetaDeCreditosLogic.replaceTarjetaDeCreditos(viajerosId, tarjetasListDTO2Entity(tarjetas)));
        LOGGER.log(Level.INFO, "ViajeroTarjetaDeCreditosResource replaceTarjetaDeCreditos: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }

    /**
     * Convierte una lista de TarjetaDeCreditoEntity a una lista de TarjetaDeCreditoDetailDTO.
     *
     * @param entityList Lista de TarjetaDeCreditoEntity a convertir.
     * @return Lista de TarjetaDeCreditoDTO convertida.
     */
    private List<TarjetaDeCreditoDTO> tarjetasListEntity2DTO(List<TarjetaDeCreditoEntity> entityList) {
        List<TarjetaDeCreditoDTO> list = new ArrayList();
        for (TarjetaDeCreditoEntity entity : entityList) {
            list.add(new TarjetaDeCreditoDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de TarjetaDeCreditoDetailDTO a una lista de TarjetaDeCreditoEntity.
     *
     * @param dtos Lista de TarjetaDeCreditoDetailDTO a convertir.
     * @return Lista de TarjetaDeCreditoEntity convertida.
     */
    private List<TarjetaDeCreditoEntity> tarjetasListDTO2Entity(List<TarjetaDeCreditoDTO> dtos) {
        List<TarjetaDeCreditoEntity> list = new ArrayList<>();
        for (TarjetaDeCreditoDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}
