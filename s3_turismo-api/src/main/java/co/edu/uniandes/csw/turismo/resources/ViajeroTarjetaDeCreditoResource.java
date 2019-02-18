/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.TarjetaDeCreditoDTO;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */
@Produces("application/json")
@Consumes("application/json")
public class ViajeroTarjetaDeCreditoResource {
    
    @POST
    public TarjetaDeCreditoDTO createTarjeta(TarjetaDeCreditoDTO tarjeta)
    {
        return tarjeta;
    }
}
