/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.ViajeDTO;
import co.edu.uniandes.csw.turismo.ejb.PlanTuristicoLogic;
import co.edu.uniandes.csw.turismo.ejb.ViajeLogic;
import co.edu.uniandes.csw.turismo.ejb.ViajeroLogic;
import co.edu.uniandes.csw.turismo.entities.ViajeEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import java.util.List;
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
 * @author Christer Osorio
 */

@Path("viajes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class ViajeResource {
    
    
    private static final Logger LOGGER=Logger.getLogger(ViajeResource.class.getName());
    
     @Inject
    private ViajeLogic viajeLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private ViajeroLogic viajeroLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private PlanTuristicoLogic planTuristicoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
   /**
     * Crea un nuevo viaje con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param viaje {@link ViajeDTO} - EL viaje que se desea guardar.
     * @return JSON {@link ViajeDTO} - El viaje guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el viaje
     */
    @POST
    public ViajeDTO createViaje(ViajeDTO viaje) throws BusinessLogicException {
      
        ViajeDTO nuevoViajeDTO = new ViajeDTO(viajeLogic.createViaje(viaje.toEntity()));
    
        return nuevoViajeDTO;
    }
    
   
    @GET
    @Path("{idViaje: \\d+}")
    public ViajeDTO getViaje(@PathParam("idViaje") Long idViaje) throws BusinessLogicException {
       if(viajeLogic.getViaje(idViaje)==null)
       {
           throw new WebApplicationException("El recurso /viaje/" + idViaje + " no existe.", 404);
       }
        return new ViajeDTO(viajeLogic.getViaje(idViaje));
    }
    
  
    
    @PUT
    @Path("idViaje: \\d+}")
    public ViajeDTO updatViaje(@PathParam("idViaje") Long idViaje, ViajeDTO viaje) throws BusinessLogicException{
      
        viaje.setIdViaje(idViaje);
        if (viajeLogic.getViaje(idViaje) == null) {
            throw new WebApplicationException("El recurso /viaje/" + idViaje + " no existe.", 404);
        }
        ViajeDTO viajeDTO = new ViajeDTO(viajeLogic.updateViaje(idViaje, viaje.toEntity()));
        
        return viajeDTO;
    }
    
    @DELETE
    @Path("idViaje: \\d+}")
    public void deleteViaje(@PathParam("idViaje") Long idViaje) throws BusinessLogicException{
        ViajeEntity entity = viajeLogic.getViaje(idViaje);
        if (entity == null) {
            throw new WebApplicationException("El recurso /viaje/" + idViaje + " no existe.", 404);
        }
        viajeLogic.deleteViaje(idViaje);
        
    }

}
