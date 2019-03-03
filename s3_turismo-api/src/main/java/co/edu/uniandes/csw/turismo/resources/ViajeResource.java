/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.ViajeDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
    
    @POST
    public ViajeDTO createViaje (ViajeDTO viaje){
        
        return viaje;
    }
    
    @GET
    public ViajeDTO getViaje (){
        return null;
    }
    
    
    @GET
    @Path("{idViaje: \\d+}")
    public ViajeDTO getViaje(@PathParam("idViaje") Long idViaje) {
        return null;
    }
    
  
    
    @PUT
    @Path("idViaje: \\d+}")
    public ViajeDTO updatViaje(@PathParam("idViaje") Long idViaje, ViajeDTO Viaje){
        return null;
    }
    
    @DELETE
    @Path("idViaje: \\d+}")
    public void deleteViaje(@PathParam("idViaje") Long idViaje){
        
    }

}
