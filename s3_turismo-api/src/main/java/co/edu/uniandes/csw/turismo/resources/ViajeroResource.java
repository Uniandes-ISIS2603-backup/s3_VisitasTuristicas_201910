/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.TarjetaDeCreditoDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import co.edu.uniandes.csw.turismo.dtos.ViajeroDTO;
import co.edu.uniandes.csw.turismo.dtos.ViajeroDetailDTO;
import co.edu.uniandes.csw.turismo.ejb.ViajeroLogic;
import co.edu.uniandes.csw.turismo.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.turismo.entities.ViajeroEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Juan Sebastian Gutierrez S.
 */
@Path("viajero")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ViajeroResource {

    private static final Logger LOGGER = Logger.getLogger(ViajeroResource.class.getName());

    @Inject
    private ViajeroLogic logic;

    /**
     * crea un viajero
     *
     * @param viajero
     * @return nuevoViajero
     * @throws BusinessLogicException
     */
    @POST
    public ViajeroDTO createViajero(ViajeroDTO viajero) throws BusinessLogicException {
        //ViajeroDTO nuevoV = new ViajeroDTO(logic.createViajero(viajero.toEntity()));
        //logic.createViajero(viajero.toEntity());
        //return nuevoV;
        LOGGER.log(Level.INFO, "CrearViajeroResource crearViajero", "a");
        ViajeroDTO nuevoViajeroDTO = new ViajeroDTO(logic.createViajero(viajero.toEntity()));
        LOGGER.log(Level.INFO, "PreferenciaResource crearBlog: output: {0}", nuevoViajeroDTO.toString());
        return nuevoViajeroDTO;
    }

    /**
     * retorna un viajero con un id
     *
     * @return Viajero
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @GET
    public List<ViajeroDetailDTO> getViajeros() throws BusinessLogicException {
        LOGGER.info("ENTRO ACA");
        List<ViajeroDetailDTO> listaFacturas = listEntity2DTO(logic.getViajeros());
        LOGGER.log(Level.INFO, "ENTRO ACA X222222222", listaFacturas.size());
        return listaFacturas;
    }


    /**
     * asigna un viajero
     *
     * @param idViajero
     * @param nuevoViajero
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @PUT
    public void setViajero(@PathParam("id") Long idViajero, ViajeroDTO nuevoViajero) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "updateViajero");
        logic.updateViajero(idViajero, nuevoViajero.toEntity());
    }


    /**
     * elimina un viajero
     *
     * @param pIdViajero
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @DELETE
    public void deleteViajero(Long pIdViajero) throws BusinessLogicException {
        logic.deleteViajero(pIdViajero);
    }

    /**
     * asigna path para tarjetaDeCredito
     *
     * @param facturasId
     * @param viajeroId
     * @return TarjetaDeCreditoResource
     * @throws BusinessLogicException
     */
  

       @Path(("{viajeroId: \\d+}/tarjetas"))
    public Class<TarjetaDeCreditoResource> getTarjetaDeCreditoResource(@PathParam("viajeroId") Long facturasId) throws BusinessLogicException {
        if (logic.getViajero(facturasId) == null) {
            throw new WebApplicationException("El recurso /facturas/" + facturasId + "/reviews no existe.", 404);
        }
        return TarjetaDeCreditoResource.class;
    }



    /**
     * asigna path preferencia
     *
     * @param viajeroId
     * @return PreferenciaResource
     * @throws BusinessLogicException
     */
    @Path("{viajeroId: \\d+}/preferencias")
    public Class<PreferenciaResource> getPreferenciaResource(@PathParam("viajeroId") Long viajeroId) throws BusinessLogicException {

        if (logic.getViajero(viajeroId) == null) {
            throw new WebApplicationException("El recurso /viajeros/" + viajeroId + " no existe.", 404);
        }
        return PreferenciaResource.class;
    }

    /**
     * asigna path planTuristico
     *
     * @param viajeroId
     * @return PlanTuristicoResource
     * @throws BusinessLogicException
     */
    @Path("{viajeroId: \\d+}/planesTuristicos")
    public Class<PlanTuristicoResource> getPlanTuristicoResource(@PathParam("viajeroId") Long viajeroId) throws BusinessLogicException {
        if (logic.getViajero(viajeroId) == null) {
            throw new WebApplicationException("El recurso /viajeros/" + viajeroId + " no existe.", 404);
        }
        return PlanTuristicoResource.class;
    }

    private List<ViajeroDetailDTO> listEntity2DTO(List<ViajeroEntity> entityList) {
        List<ViajeroDetailDTO> list = new ArrayList<>();
        for (ViajeroEntity entity : entityList) {
            list.add(new ViajeroDetailDTO(entity));
        }
        return list;
    }

    /**
     * asigna path factura
     *
     * @param viajeroId
     * @return FacturaResource
     * @throws BusinessLogicException
     */
    @Path("{viajeroId: \\d+}/facturas")
    public Class<FacturasResources> getFacturasResource(@PathParam("viajeroId") Long viajeroId) throws BusinessLogicException {
        if (logic.getViajero(viajeroId) == null) {
            throw new WebApplicationException("El recurso /viajeros/" + viajeroId + " no existe.", 404);
        }
        return FacturasResources.class;
    }


   

}
