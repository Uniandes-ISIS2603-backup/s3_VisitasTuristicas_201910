/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.turismo.entities.ViajeroEntity;
import co.edu.uniandes.csw.turismo.persistence.TarjetaDeCreditoPersistence;
import co.edu.uniandes.csw.turismo.persistence.ViajeroPersistence;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.util.List;
import java.util.logging.Level;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @tarjetaDeCredito estudiante
 */

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ViajeroTarjetaDeCreditoResource {


    @Inject
    private TarjetaDeCreditoPersistence tarjetaDeCreditoPersistence;

    @Inject
    private ViajeroPersistence viajeroPersistence;

    /**
     * Agregar un tarjetaDeCredito a la viajero
     *
     * @param tarjetaDeCreditosId El id libro a guardar
     * @param viajerosId El id de la viajero en la cual se va a guardar el
     * libro.
     * @return El libro creado.
     */
    public TarjetaDeCreditoEntity addTarjetaDeCredito(Long tarjetaDeCreditosId, Long viajerosId) {
        ViajeroEntity viajeroEntity = viajeroPersistence.find(viajerosId);
        TarjetaDeCreditoEntity tarjetaDeCreditoEntity = tarjetaDeCreditoPersistence.find(tarjetaDeCreditosId);
        tarjetaDeCreditoEntity.setViajero(viajeroEntity);
        return tarjetaDeCreditoEntity;
    }

    /**
     * Retorna todos los tarjetaDeCreditos asociados a una viajero
     *
     * @param viajerosId El ID de la viajero buscada
     * @return La lista de libros de la viajero
     */
    public List<TarjetaDeCreditoEntity> getTarjetaDeCreditos(Long viajerosId) {

        return viajeroPersistence.find(viajerosId).getTarjetas();
    }
}
