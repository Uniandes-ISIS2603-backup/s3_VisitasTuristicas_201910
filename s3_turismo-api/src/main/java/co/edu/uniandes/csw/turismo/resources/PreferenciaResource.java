/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.PreferenciaDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author estudiante
 */

    @Path("/preferencias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class PreferenciaResource {
    private static final Logger LOGGER=Logger.getLogger(PlanTuristicoResource.class.getName());
    
    @POST
    public PreferenciaDTO createPreferencia(PreferenciaDTO preferencia) {
        return preferencia;
    }
    
    @GET
    public PreferenciaDTO getPreferencia(Long pIdPreferencia) {
        return new PreferenciaDTO();
    }
    
    @PUT
    public boolean setPreferencia(PreferenciaDTO preferencia) {
        return true;
    }
    
    @DELETE
    public boolean deletePreferencia(Long pIdViajero) {
        return true;
    }
}
