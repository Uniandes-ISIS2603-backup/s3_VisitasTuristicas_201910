/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;


import co.edu.uniandes.csw.turismo.dtos.SitiosTuristicosDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author David Fonseca
 */

@Path("/sitios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class SitioTuristicoResource {
    private static final Logger LOGGER=Logger.getLogger(SitioTuristicoResource.class.getName());
    
    
    @POST
    public SitiosTuristicosDTO crearSitioTuristico(SitiosTuristicosDTO sitio)
    {

        return sitio;
    }
    
     @GET
    @Path("{id: \\d+}")
    public SitiosTuristicosDTO darSitiosTuristicos(@PathParam("id") Long idsitio) {
        LOGGER.log(Level.INFO, "CiudadResource darCiudad: input: {0}", idsitio);
        SitiosTuristicosDTO detailDTO = new SitiosTuristicosDTO();
        LOGGER.log(Level.INFO, "CiudadResource darCiudad: output: {0}", detailDTO);
        return detailDTO;
    }
    
}
