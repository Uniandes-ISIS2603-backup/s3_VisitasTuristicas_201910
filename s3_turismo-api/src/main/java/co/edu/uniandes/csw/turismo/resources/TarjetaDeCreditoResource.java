/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.TarjetaDeCreditoDTO;
import co.edu.uniandes.csw.turismo.ejb.TarjetaDeCreditoLogic;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author estudiante
 */
@Path("tarjetasDeCredito")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class TarjetaDeCreditoResource {
    private static final Logger LOGGER = Logger.getLogger(TarjetaDeCreditoResource.class.getName());
    
    @Inject
    private TarjetaDeCreditoLogic tarjetaLogic;

    @POST
    public TarjetaDeCreditoDTO createTarjeta(TarjetaDeCreditoDTO tarjeta) throws BusinessLogicException
    {
        tarjetaLogic.createTarjetaDeCredito(tarjeta.toEntity());
        return tarjeta;
    }
    
    @GET
    @Path("{tarjetaId: \\d+}")
    public TarjetaDeCreditoDTO darTarjeta(@PathParam("viajeroId") Long viajeroId, @PathParam("tarjetaId") Long tarjetaId)
    {
        if(tarjetaLogic.getTarjetaDeCredito(tarjetaId)==null)
        {
            throw new WebApplicationException("el recurso /viajero/" + tarjetaId + "/tarjetaDeCredito/"+tarjetaId+ " no existe", 404);
        }
        TarjetaDeCreditoDTO tarjetaDeCredito = new TarjetaDeCreditoDTO(tarjetaLogic.getTarjetaDeCredito(tarjetaId));
        return tarjetaDeCredito;
    }
}
