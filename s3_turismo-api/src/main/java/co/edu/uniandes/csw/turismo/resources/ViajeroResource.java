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
import javax.ws.rs.*;

/**
 *
 * @author estudiante
 */
@Path("/viajeros")
@Produces("application/json" )
@Consumes("application/json" )
@RequestScoped
public class ViajeroResource {
    private static final Logger LOGGER=Logger.getLogger(PlanTuristicoResource.class.getName());
    
    @POST
    public ViajeroDTO createViajero(ViajeroDTO viajero) {
        return viajero;
    }
    
    @GET
    public ViajeroDTO getViajero(Long pIdViajero) {
        return new ViajeroDTO();
    }
    @GET 
    public String getViajeroNombre(Long pIdViajero) {
        return "";
    }
    @GET
    public String getViajeroIdioma(Long pIdViajero) {
        return "";
    }
    @GET
    public String getViajeroTipo(Long pIdViajero) {
        return "";
    }
    @GET
    public String getViajeroInformacion(Long pIdViajero) {
        return "";
    }
    
    @PUT
    public boolean setViajeroNombre(String pNombre) {
        return true;
    }
    @PUT
    public boolean setViajeroIdioma(String pIdioma) {
        return true;
    }
    @PUT
    public boolean setCantidadViajes(int n) {
        return true;
    }
    
    @DELETE
    public boolean deleteViajero(Long pIdViajero) {
        return true;
    }
    @DELETE
    public boolean deleteViajeroInformacion(Long pIdViajero) {
        return true;
    }
}
