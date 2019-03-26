/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.FacturasDTO;
import co.edu.uniandes.csw.turismo.ejb.FacturaLogic;
import co.edu.uniandes.csw.turismo.entities.FacturaEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class FacturasResources {
    

    private static final Logger LOGGER = Logger.getLogger(FacturasResources.class.getName());
    
    @Inject
    private FacturaLogic facturaLogic;
    
    @POST
    public FacturasDTO createFactura( FacturasDTO factura, Long idUsuario) throws BusinessLogicException
    {
        return new FacturasDTO(facturaLogic.createFactura(idUsuario,factura.toEntity()));
        
    }
    
    @GET
    public List<FacturasDTO> getFacturas(@PathParam("usuarioId") Long booksId) {
        LOGGER.log(Level.INFO, "Facturas getFacturas: input: {0}", booksId);
        List<FacturasDTO> listaDTOs = listEntity2DTO(facturaLogic.getFacturas(booksId));
        LOGGER.log(Level.INFO, "Facturas getFacturas: output: {0}", listaDTOs);
        return listaDTOs;
    }
    
    @GET
    @Path("{facturasId: \\d+}")
    public FacturasDTO getFactura(@PathParam("viajeroId") Long viajeroId, @PathParam("facturaId") Long facturaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "factura resource getFactura: input: {0}", facturaId);
        FacturaEntity entity = facturaLogic.getFactura(viajeroId, facturaId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + viajeroId + "/reviews/" + facturaId + " no existe.", 404);
        }
        FacturasDTO facturaDTO = new FacturasDTO(entity);
        LOGGER.log(Level.INFO, "ReviewResource getReview: output: {0}", facturaDTO);
        return facturaDTO;
    }
    
    
        @DELETE
    @Path("{facturasId: \\d+}")
    public void deleteFactura(@PathParam("viajeroId") Long viajeroId, @PathParam("facturaId") Long facturaId) throws BusinessLogicException {
        FacturaEntity entity = facturaLogic.getFactura(viajeroId, facturaId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + viajeroId + "/reviews/" + facturaId + " no existe.", 404);
        }
        facturaLogic.deleteFactura(facturaId,viajeroId);
    }
    
    
     private List<FacturasDTO> listEntity2DTO(List<FacturaEntity> entityList) {
        List<FacturasDTO> list = new ArrayList<FacturasDTO>();
        for (FacturaEntity entity : entityList) {
            list.add(new FacturasDTO(entity));
        }
        return list;
    }
    
    
    

    
    
}
