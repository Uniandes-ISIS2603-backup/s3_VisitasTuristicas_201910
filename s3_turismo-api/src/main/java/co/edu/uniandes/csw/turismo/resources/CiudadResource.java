/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.CiudadDTO;
import co.edu.uniandes.csw.turismo.dtos.CiudadDetailDTO;
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
 * @author estudiante
 */

@Path("/ciudades")
@Produces(MediaType.APPLICATION_JSON )
@Consumes(MediaType.APPLICATION_JSON )
@RequestScoped
public class CiudadResource {
    private static final Logger LOGGER=Logger.getLogger(CiudadResource.class.getName());
    
    
    @POST
    public CiudadDTO crearCiudad(CiudadDTO ciudad)
    {
        return ciudad;
    }
    
    @GET
    @Path("{id: \\d+}")
    public CiudadDetailDTO darCiudad(@PathParam("id") Long idciudad) {
        LOGGER.log(Level.INFO, "CiudadResource darCiudad: input: {0}", idciudad);
        CiudadDetailDTO detailDTO = new CiudadDetailDTO();
        LOGGER.log(Level.INFO, "CiudadResource darCiudad: output: {0}", detailDTO);
        return detailDTO;
    }
}
