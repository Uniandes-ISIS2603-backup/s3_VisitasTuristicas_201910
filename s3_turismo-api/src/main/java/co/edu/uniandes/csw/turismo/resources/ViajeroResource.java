/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import co.edu.uniandes.csw.turismo.dtos.ViajeroDTO;
import co.edu.uniandes.csw.turismo.dtos.ViajeroDetailDTO;
import co.edu.uniandes.csw.turismo.ejb.ViajeroLogic;
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
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class ViajeroResource {
    private static final Logger LOGGER=Logger.getLogger(PlanTuristicoResource.class.getName());
    
    @Inject
    private ViajeroLogic logic;
    
    /**
     * crea un viajero
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
        ViajeroDTO nuevoViajeroDTO = new ViajeroDTO(logic.createViajero( viajero.toEntity()));
          LOGGER.log(Level.INFO, "PreferenciaResource crearBlog: output: {0}", nuevoViajeroDTO.toString());
        return nuevoViajeroDTO;
    }
    
    /**
     * retorna un viajero con un id
     * @return Viajero
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @GET
    public List<ViajeroDetailDTO> getViajeros() throws BusinessLogicException {
        LOGGER.info("ENTRO ACA");
        List<ViajeroDetailDTO> listaBooks = listEntity2DTO(logic.getViajeros());
        LOGGER.log(Level.INFO, "ENTRO ACA X222222222", listaBooks.size());
        return listaBooks;
    }
    
    /**
     *
     * @param booksId
     * @return
     * @throws BusinessLogicException
     */
    @GET
    @Path("{viajeroId: \\d+}")
    public ViajeroDetailDTO getBook(@PathParam("viajeroId") Long booksId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "BookResource getBook: input: {0}", booksId);
        ViajeroEntity bookEntity = logic.getViajero(booksId);
        if (bookEntity == null) {
            throw new WebApplicationException("El recurso /books/" + booksId + " no existe.", 404);
        }
        ViajeroDetailDTO bookDetailDTO = new ViajeroDetailDTO(bookEntity);
        LOGGER.log(Level.INFO, "BookResource getBook: output: {0}", bookDetailDTO);
        return bookDetailDTO;
    }

    
    //private List<ViajeroDTO> listEntity2DTO(List<ViajeroEntity> entityList)throws BusinessLogicException {
    //    List<ViajeroDTO> list = new ArrayList<ViajeroDTO>();
    //    for (ViajeroEntity entity : entityList) {
    //        list.add(new ViajeroDTO(entity));
    //    }
    //    return list;
    //}
    //@GET
    //public ViajeroDTO getViajero(@PathParam("id")Long pIdViajero) {
    //    return new ViajeroDTO();
    //}
    
    /**
     * asigna un viajero
     * @param idViajero
     * @param nuevoViajero 
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException 
     */
    @PUT
    public void setViajero(@PathParam("id") Long idViajero, ViajeroDTO nuevoViajero) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "updateViajero");
        logic.updateViajero(idViajero, nuevoViajero.toEntity());
    }
    
//    @PUT
//    public void setViajeros(ArrayList<ViajeroDTO> viajeros, ArrayList<ViajeroDTO> nuevosViajeros) {
//        viajeros = nuevosViajeros;
//    }
//    @PUT
//    public void setViajeroNombre(String pNombre) {
//        
//    }
//    @PUT
//    public void setViajeroIdioma(String pIdioma) {
        
//    }
//    @PUT
//    public void setCantidadViajes(int n) {
        
//    }
    
    /**
     * elimina un viajero
     * @param pIdViajero 
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException 
     */
    @DELETE
    public void deleteViajero(Long pIdViajero) throws BusinessLogicException {
        logic.deleteViajero(pIdViajero);
    }

    /**
     * asigna path para tarjetaDeCredito
     * @param viajeroId
     * @return TarjetaDeCreditoResource
     * @throws BusinessLogicException 
     */
    @Path("{viajeros: \\d+}/tarjetas")
    public Class<ViajeroTarjetaDeCreditoResource> getViajeroTarjetaDeCreditoResource(@PathParam("viajeros") Long editorialsId) throws BusinessLogicException {
        if (logic.getViajero(editorialsId) == null) {
            throw new WebApplicationException("El recurso /editorials/" + editorialsId + " no existe.", 404);
        }
        return ViajeroTarjetaDeCreditoResource.class;
    }
    
    /**
     * asigna path viaje
     * @param viajeroId
     * @return ViajeResource
     * @throws BusinessLogicException 
     */
    @Path("{viajeroId: \\d+/viaje}")
    public Class<ViajeResource> getViajeResource(@PathParam("viajeroId") Long viajeroId) throws BusinessLogicException {
        if(logic.getViajero(viajeroId) == null) {
            throw new WebApplicationException("El recurso /viajeros/" + viajeroId + "no existe.", 404);
        }
        return ViajeResource.class;
    }
    
    /**
     * asigna path preferencia
     * @param viajeroId
     * @return PreferenciaResource
     * @throws BusinessLogicException 
     */
    @Path("{viajero: \\d+/preferencia}")
    public Class<PreferenciaResource> getPreferenciaResource(@PathParam("viajero") Long viajeroId) throws BusinessLogicException {
        if (logic.getViajero(viajeroId) == null) {
            throw new WebApplicationException("El recurso /editorials/" + viajeroId + " no existe.", 404);
        }
        return PreferenciaResource.class;
    }
    
    /**
     * asigna path planTuristico
     * @param viajeroId
     * @return PlanTuristicoResource
     * @throws BusinessLogicException 
     */
    @Path("{viajeroId: \\d+/planesTuristicos}")
    public Class<PlanTuristicoResource> getPlanTuristicoResource(@PathParam("viajeroId") Long viajeroId) throws BusinessLogicException {
        if (logic.getViajero(viajeroId) == null) {
            throw new WebApplicationException("El recurso /editorials/" + viajeroId + " no existe.", 404);
        }
        return PlanTuristicoResource.class;
    }
            private List<ViajeroDetailDTO> listEntity2DTO(List<ViajeroEntity> entityList) {
        List<ViajeroDetailDTO> list = new ArrayList<>();
        for (ViajeroEntity entity : entityList) {
            list.add(new ViajeroDetailDTO(entity));
        }
        return list;}
    /**
     * asigna path factura
     * @param viajeroId
     * @return FacturaResource
     * @throws BusinessLogicException 
     */
    @Path("{viajeroId: \\d+/facturas}")
    public Class<FacturasResources> getFacturaResource(@PathParam("viajeroId") Long viajeroId) throws BusinessLogicException {
        if (logic.getViajero(viajeroId) == null) {
            throw new WebApplicationException("El recurso /editorials/" + viajeroId + " no existe.", 404);
        }
        return FacturasResources.class;
    }
    

    


}