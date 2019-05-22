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
@Path("tarjetas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class TarjetaDeCreditoResource {
  private static final Logger LOGGER = Logger.getLogger(TarjetaDeCreditoResource.class.getName());

    @Inject
    private TarjetaDeCreditoLogic tarjetaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.


    /**
     * Crea un nuevo premio con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param tarjeta {@link TarjetaDeCreditoDTO} - EL premio que se desea guardar.
     * @return JSON {@link TarjetaDeCreditoDTO} - El premio guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el premio o el isbn es
     * inválido o si la organization ingresada es invalida.
     */
    @POST
    public TarjetaDeCreditoDTO createTarjetaDeCredito(TarjetaDeCreditoDTO tarjeta) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TarjetaDeCreditoResource createTarjetaDeCredito: input: {0}", tarjeta);
        TarjetaDeCreditoDTO nuevoTarjetaDeCreditoDTO = new TarjetaDeCreditoDTO(tarjetaLogic.createTarjetaDeCredito(tarjeta.toEntity()));
        LOGGER.log(Level.INFO, "TarjetaDeCreditoResource createTarjetaDeCredito: output: {0}", nuevoTarjetaDeCreditoDTO);
        return nuevoTarjetaDeCreditoDTO;
    }

    /**
     * Busca y devuelve todos los premios que existen en la aplicacion.
     *
     * @return JSONArray {@link TarjetaDeCreditoDTO} - Los premios encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<TarjetaDeCreditoDTO> getTarjetaDeCreditos() {
        LOGGER.info("TarjetaDeCreditoResource getTarjetaDeCreditos: input: void");
        List<TarjetaDeCreditoDTO> listaTarjetaDeCreditos = listEntity2DetailDTO(tarjetaLogic.getTarjetaDeCreditos());
        LOGGER.log(Level.INFO, "TarjetaDeCreditoResource getTarjetaDeCreditos: output: {0}", listaTarjetaDeCreditos);
        return listaTarjetaDeCreditos;
    }

    /**
     * Busca el premio con el id asociado recibido en la URL y lo devuelve.
     *
     * @param tarjetasId Identificador del premio que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link TarjetaDeCreditoDTO} - El premio buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el premio.
     */
    @GET
    @Path("{tarjetasId: \\d+}")
    public TarjetaDeCreditoDTO getTarjetaDeCredito(@PathParam("tarjetasId") Long tarjetasId) {
        LOGGER.log(Level.INFO, "TarjetaDeCreditoResource getTarjetaDeCredito: input: {0}", tarjetasId);
        TarjetaDeCreditoEntity tarjetaEntity = tarjetaLogic.getTarjetaDeCredito(tarjetasId);
        if (tarjetaEntity == null) {
            throw new WebApplicationException("El recurso /tarjetas/" + tarjetasId + " no existe.", 404);
        }
        TarjetaDeCreditoDTO tarjetaDetailDTO = new TarjetaDeCreditoDTO(tarjetaEntity);
        LOGGER.log(Level.INFO, "TarjetaDeCreditoResource getTarjetaDeCredito: output: {0}", tarjetaDetailDTO);
        return tarjetaDetailDTO;
    }

    /**
     * Actualiza el premio con el id recibido en la URL con la información que
     * se recibe en el cuerpo de la petición.
     *
     * @param tarjetasId Identificador del premio que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param tarjeta {@link TarjetaDeCreditoDTO} El premio que se desea guardar.
     * @return JSON {@link TarjetaDeCreditoDTO} - El premio guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el premio a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar el premio.
     */
    @PUT
    @Path("{tarjetasId: \\d+}")
    public TarjetaDeCreditoDTO updateTarjetaDeCredito(@PathParam("tarjetasId") Long tarjetasId, TarjetaDeCreditoDTO tarjeta) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TarjetaDeCreditoResource updateTarjetaDeCredito: input: tarjetasId: {0} , tarjeta: {1}", new Object[]{tarjetasId, tarjeta});
        tarjeta.setIdTarjetaDeCredito(tarjetasId);
        if (tarjetaLogic.getTarjetaDeCredito(tarjetasId) == null) {
            throw new WebApplicationException("El recurso /tarjetas/" + tarjetasId + " no existe.", 404);
        }
        TarjetaDeCreditoDTO detailDTO = new TarjetaDeCreditoDTO(tarjetaLogic.updateTarjetaDeCredito(tarjetasId, tarjeta.toEntity()));
        LOGGER.log(Level.INFO, "TarjetaDeCreditoResource updateTarjetaDeCredito: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Borra el premio con el id asociado recibido en la URL.
     *
     * @param tarjetasId Identificador del premio que se desea borrar. Este debe
     * ser una cadena de dígitos.
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * Error de lógica que se genera cuando el premio tiene un autor.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el premio.
     */
    @DELETE
    @Path("{tarjetasId: \\d+}")
    public void deleteTarjetaDeCredito(@PathParam("tarjetasId") Long tarjetasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TarjetaDeCreditoResource deleteTarjetaDeCredito: input: {0}", tarjetasId);
        if (tarjetaLogic.getTarjetaDeCredito(tarjetasId) == null) {
            throw new WebApplicationException("El recurso /tarjetas/" + tarjetasId + " no existe.", 404);
        }
        tarjetaLogic.deleteTarjetaDeCredito(tarjetasId);
        LOGGER.info("TarjetaDeCreditoResource deleteTarjetaDeCredito: output: void");
    }

    


    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos TarjetaDeCreditoEntity a una lista de
     * objetos TarjetaDeCreditoDTO (json)
     *
     * @param entityList corresponde a la lista de premios de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de premios en forma DTO (json)
     */
    private List<TarjetaDeCreditoDTO> listEntity2DetailDTO(List<TarjetaDeCreditoEntity> entityList) {
        List<TarjetaDeCreditoDTO> list = new ArrayList<>();
        for (TarjetaDeCreditoEntity entity : entityList) {
            list.add(new TarjetaDeCreditoDTO(entity));
        }
        return list;
    }
    
    
    
    @Path("{tarjetas: \\d+}/viajeros")
    public Class<TarjetasDeCreditoViajeroResource> getTarjetaDeCreditoViajerosResource(@PathParam("tarjetas") Long booksId) {
        if (tarjetaLogic.getTarjetaDeCredito(booksId) == null) {
            throw new WebApplicationException("El recurso /books/" + booksId + " no existe.", 404);
        }
        return TarjetasDeCreditoViajeroResource.class;
    }
}
