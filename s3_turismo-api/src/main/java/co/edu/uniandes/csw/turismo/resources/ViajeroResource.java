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
import java.util.ArrayList;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author estudiante
 */
@Path("/viajeros")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ViajeroResource {
    private static final Logger LOGGER=Logger.getLogger(PlanTuristicoResource.class.getName());
    
    @POST
    public ViajeroDTO createViajero(ViajeroDTO viajero) {
        return viajero;
    }
    
    @GET
    public ViajeroDTO getViajero(@PathParam("id")Long pIdViajero) {
        return new ViajeroDTO();
    }
    
    @PUT
    public void setViajero(@PathParam("id") Long idViajero, ViajeroDTO nuevoViajero) {
        
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
    
    @DELETE
    public void deleteViajero(Long pIdViajero) {
        
    }
//    @DELETE
//    public void deleteViajeroInformacion(Long pIdViajero) {
        
//    }
}
