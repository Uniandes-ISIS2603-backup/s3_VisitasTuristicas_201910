/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.CiudadDetailDTO;
import co.edu.uniandes.csw.turismo.dtos.PaisDTO;
import co.edu.uniandes.csw.turismo.dtos.PaisDetailDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */

@Path("/paises")
@Produces("application/json")
@Consumes("application/json" )
@RequestScoped
public class PaisResource {
    private static final Logger LOGGER=Logger.getLogger(PaisResource.class.getName());
    
    
    @POST
    public PaisDTO crearPais(PaisDTO pais)
    {
        return pais;
    }
     @GET
    @Path("{id: \\d+}")
    public PaisDetailDTO darPais(@PathParam("id") Long idPais) {
        LOGGER.log(Level.INFO, "PaisResource darPais: input: {0}", idPais);
        PaisDetailDTO detailDTO = new PaisDetailDTO();
        LOGGER.log(Level.INFO, "PaisResource darCiudad: output: {0}", detailDTO);
        return detailDTO;
    }
}
