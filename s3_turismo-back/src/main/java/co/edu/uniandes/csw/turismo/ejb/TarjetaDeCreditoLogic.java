/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.TarjetaDeCreditoPersistence;
import co.edu.uniandes.csw.turismo.persistence.ViajeroPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jd.castrellon
 */
@Stateless
public class TarjetaDeCreditoLogic {
    
    
    private static final Logger LOGGER = Logger.getLogger(TarjetaDeCreditoLogic.class.getName());

    @Inject
    private TarjetaDeCreditoPersistence persistence;

    @Inject
    private ViajeroPersistence editorialPersistence;

    /**
     * Guardar una nueva tarjeta
     *
     * @param tarjetaEntity La entidad de tipo tarjeta a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el Viajero es inválido, el codido de seguridad es invalido,  o el numero
     * o ya existe en la persistencia.
     */
    public TarjetaDeCreditoEntity createTarjetaDeCredito(TarjetaDeCreditoEntity tarjetaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la tarjeta");
        if (tarjetaEntity.getViajero() == null || editorialPersistence.find(tarjetaEntity.getViajero().getId()) == null) {
            throw new BusinessLogicException("El viajero es inválido");
        }
        if (persistence.getByNumber(tarjetaEntity.getNumero()) != null) {
            throw new BusinessLogicException("El Numero ya existe");
        }
        if (!validateNumber(tarjetaEntity.getNumero())) {
            throw new BusinessLogicException("El Numero es inválido");
        }
        if (!validateCodigo(tarjetaEntity.getCodigoSeguridad())) {
            throw new BusinessLogicException("El codigo es inválido");
        }
        persistence.create(tarjetaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación");
        return tarjetaEntity;
    }

    /**
     * Devuelve todas las tarjetas.
     *
     * @return Lista de entidades de tipo tarjeta.
     */
    public List<TarjetaDeCreditoEntity> getTarjetaDeCreditos() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las tarjetas");
        List<TarjetaDeCreditoEntity> tarjetas = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consulta");
        return tarjetas;
    }

    /**
     * Busca una Tarjeta por su Id
     *
     * @param tarjetasId El id de la tarjeta a buscar
     * @return La tarjeta encontrada, null si no la encuentra.
     */
    public TarjetaDeCreditoEntity getTarjetaDeCredito(Long tarjetasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar una tarjeta con id = {0}", tarjetasId);
        TarjetaDeCreditoEntity tarjetaEntity = persistence.find(tarjetasId);
        if (tarjetaEntity == null) {
            LOGGER.log(Level.SEVERE, "El libro con el id = {0} no existe", tarjetasId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la tarjeta con id = {0}", tarjetasId);
        return tarjetaEntity;
    }

    /**
     * Actualizar una tarjeta dado un ID
     *
     * @param tarjetasId El ID de la tarjeta a actualizar
     * @param tarjetaEntity La entidad de la tarjeta con los cambios deseados
     * @return La entidad del libro luego de actualizarla
     * @throws BusinessLogicException Si el numero o el codigo es invalido
     */
    public TarjetaDeCreditoEntity updateTarjetaDeCredito(Long tarjetasId, TarjetaDeCreditoEntity tarjetaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la tarjeta con id = {0}", tarjetasId);
        if (!validateNumber(tarjetaEntity.getNumero())) {
            throw new BusinessLogicException("La tarjeta es inválida");
        }
        if (!validateCodigo(tarjetaEntity.getCodigoSeguridad())) {
            throw new BusinessLogicException("El codigo es inválido");
        }
        TarjetaDeCreditoEntity newEntity = persistence.update(tarjetaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la tarjeta con id = {0}", tarjetaEntity.getId());
        return newEntity;
    }

    /**
     * Eliminar una tarjeta por ID
     *
     * @param tarjetasId El ID de la tarjeta
     */
    public void deleteTarjetaDeCredito(Long tarjetasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la tarjeta con id = {0}", tarjetasId);
        persistence.delete(tarjetasId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la tarjeta con id = {0}", tarjetasId);
    }

    /**
     * Verifica que el Numero no sea invalido.
     *
     * @param number a verificar
     * @return true si el numero es valido.
     */
    private boolean validateNumber(String number) {
        return !(number == null || number.isEmpty());
    }
      
    
    /**
     * Verifica que el codigo no sea invalido.
     *
     * @param codigo a verificar
     * @return true si el codigo es valido.
     */
     private boolean validateCodigo(int codigo) {
        return codigo>0;
    }
}
