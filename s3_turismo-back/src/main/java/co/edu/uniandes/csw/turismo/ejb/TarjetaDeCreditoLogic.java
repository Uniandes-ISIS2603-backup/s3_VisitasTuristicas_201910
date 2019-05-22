/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.turismo.entities.ViajeroEntity;
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
    private TarjetaDeCreditoPersistence prizePersistence;



    /**
     * Guardar un nuevo premio
     *
     * @param prizeEntity La entidad de tipo premio del nuevo premio a
     * persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException si la organizacion no existe o ya tiene
     * premio.
     */
    public TarjetaDeCreditoEntity createTarjetaDeCredito(TarjetaDeCreditoEntity prizeEntity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de premio");
        prizeEntity = prizePersistence.create(prizeEntity);
        LOGGER.info("Termina proceso de creación de premio");
        return prizeEntity;
    }

    /**
     * Devuelve todos los premios que hay en la base de datos.
     *
     * @return Lista de entidades de tipo premio.
     */
    public List<TarjetaDeCreditoEntity> getTarjetaDeCreditos() {
        LOGGER.info("Inicia proceso de consultar todos los premios");
        List<TarjetaDeCreditoEntity> prizes = prizePersistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los premios");
        return prizes;
    }

    /**
     * Busca un premio por ID
     *
     * @param prizesId El id del premio a buscar
     * @return El premio encontrado, null si no lo encuentra.
     */
    public TarjetaDeCreditoEntity getTarjetaDeCredito(Long prizesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar premio con id = {0}", prizesId);
        TarjetaDeCreditoEntity prize = prizePersistence.find(prizesId);
        if (prize == null) {
            LOGGER.log(Level.SEVERE, "El premio con el id = {0} no existe", prizesId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar premio con id = {0}", prizesId);
        return prize;
    }

    /**
     * Actualizar un premio por ID
     *
     * @param prizesId El ID del premio a actualizar
     * @param prizeEntity La entidad del premio con los cambios deseados
     * @return La entidad del premio luego de actualizarla
     */
    public TarjetaDeCreditoEntity updateTarjetaDeCredito(Long prizesId, TarjetaDeCreditoEntity prizeEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar premio con id = {0}", prizesId);
        TarjetaDeCreditoEntity newEntity = prizePersistence.update(prizeEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar premio con id = {0}", prizeEntity.getId());
        return newEntity;
    }

    /**
     * Eliminar un premio por ID
     *
     * @param prizesId El ID del premio a eliminar
     * @throws BusinessLogicException si el premio tiene un autor asociado.
     */
    public void deleteTarjetaDeCredito(Long prizesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar premio con id = {0}", prizesId);
        if (prizePersistence.find(prizesId).getViajero()!= null) {
            throw new BusinessLogicException("No se puede borrar el premio con id = " + prizesId + " porque tiene un autor asociado");
        }
        prizePersistence.delete(prizesId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar premio con id = {0}", prizesId);
    }
}
