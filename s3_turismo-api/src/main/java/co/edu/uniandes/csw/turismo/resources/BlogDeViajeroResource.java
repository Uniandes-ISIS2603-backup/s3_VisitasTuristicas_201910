/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import java.util.logging.Logger;
import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.turismo.dtos.BlogDeViajeroDTO;
import co.edu.uniandes.csw.turismo.dtos.BlogDeViajeroDetailDTO;
import co.edu.uniandes.csw.turismo.ejb.BlogDeViajeroLogic;
import javax.inject.Inject;

@Path("blogDeViajero")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class BlogDeViajeroResource {

    @Inject
    private BlogDeViajeroLogic blogDeViajeroLogic;
    
    private static final Logger LOGGER = Logger.getLogger(BlogDeViajeroResource.class.getName());

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public BlogDeViajeroDTO crearBlogDeViajero(BlogDeViajeroDTO blogDeViajero) {
        return blogDeViajero;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("blogsDeViajeroPorPlan")
    public BlogDeViajeroDetailDTO darBlogDeViajero() {
          BlogDeViajeroDetailDTO bv = new BlogDeViajeroDetailDTO();
        bv.setComentarios(new ArrayList());
        bv.setLikes(5);
        bv.setSugerencias(new ArrayList());
        return bv;
    }
}
