/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.turismo.dtos.ValoracionDTO;
import co.edu.uniandes.csw.turismo.dtos.ValoracionDetailDTO;
import co.edu.uniandes.csw.turismo.ejb.ValoracionLogic;
import javax.inject.Inject;

@Path("valoracion")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class ValoracionResource {

    @Inject
    private ValoracionLogic valoracionLogic;
    
    private static final Logger LOGGER = Logger.getLogger(ValoracionResource.class.getName());

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ValoracionDTO crearValoracion(ValoracionDTO valoracion) {
        return valoracion;
    }

    @GET
    @Path("valoracion")
    @Produces("application/json")
    public ValoracionDetailDTO darValoracion() {
        ValoracionDetailDTO vd = new ValoracionDetailDTO();
        vd.setIdUsuario(1);
        vd.setValoracion(5);
        vd.setComentario("muy Bueno");
        return vd;
    }
}
