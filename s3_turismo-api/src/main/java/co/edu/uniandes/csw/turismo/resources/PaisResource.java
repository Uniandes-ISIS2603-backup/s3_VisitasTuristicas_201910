/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.PaisDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */

@Path("paises")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PaisResource {
    private static final Logger LOGGER=Logger.getLogger(PaisResource.class.getName());
    
    
    @POST
    public PaisDTO crearPais(PaisDTO pais)
    {
        return pais;
    }
}
