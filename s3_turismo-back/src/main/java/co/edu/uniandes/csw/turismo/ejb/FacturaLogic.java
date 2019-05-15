/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.FacturaEntity;
import co.edu.uniandes.csw.turismo.entities.ViajeroEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.FacturaPersistence;
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
public class FacturaLogic {
     private static final Logger LOGGER = Logger.getLogger(FacturaLogic.class.getName());

    @Inject
    private FacturaPersistence persistence;

    @Inject
    private ViajeroPersistence viajeroPersistence;

    /**
     * Guardar una nueva factura
     *
     * @param booksId
     * @param reviewEntity
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el el costo o la descripcion son inválidos
     */
   public FacturaEntity createFactura(Long booksId, FacturaEntity reviewEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de crear review");
        ViajeroEntity book = viajeroPersistence.find(booksId);
        reviewEntity.setViajero(book);
        LOGGER.log(Level.INFO, "Termina proceso de creación del review");
        return persistence.create(reviewEntity);
    }

    /**
     * Devuelve todas las facturas.
     *
     * @param viajeroId
     * @return Lista de entidades de tipo factura.
     */
    public List<FacturaEntity> getFacturas(Long viajeroId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las facturas");
ViajeroEntity viajero = viajeroPersistence.find(viajeroId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las facturas");
        return viajero.getFacturas();
    }

    /**
     * Busca una factura dado su ID
     *
     * @param facturasId El id a buscar
     * @param viajeroId
     * @return La factura encontrado, null si no lo encuentra.
     */
    public FacturaEntity getFactura(Long facturasId, Long viajeroId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar una factura con id = {0}", facturasId);
        FacturaEntity facturaEntity = persistence.find(facturasId, viajeroId);
        if (facturaEntity == null) {
            LOGGER.log(Level.SEVERE, "La factura con el id = {0} no existe", facturasId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar una factura con id = {0}", facturasId);
        return facturaEntity;
    }

    /**
     * Actualizar una factura por ID
     *
     * @param facturasId El ID del libro a actualizar
     * @param facturaEntity La entidad del libro con los cambios deseados
     * @return La entidad del libro luego de actualizarla
     * @throws BusinessLogicException Si hay fallas en la descripcion o el costo
     */
    public FacturaEntity updateFactura(Long facturasId, FacturaEntity facturaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el libro con id = {0}", facturasId);
        if (!validateDescripcion(facturaEntity.getDescripcion())) {
            throw new BusinessLogicException("La descripcion es inválida");
        }
        if(!validateCosto(facturaEntity.getCosto()))
        {
            throw new BusinessLogicException("El costo no es valido");
        }  
        FacturaEntity newEntity = persistence.update(facturaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la factura con id = {0}", facturaEntity.getId());
        return newEntity;
    }

    /**
     * Eliminar una factura por ID
     *
     * @param facturasId El id a eliminar
     * @param viajeroId
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    public void deleteFactura(Long facturasId, Long viajeroId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el review con id = {0} del libro con id = " + viajeroId, facturasId);
        FacturaEntity old = getFactura(facturasId, viajeroId);
        if (old == null) {
            throw new BusinessLogicException("El review con id = " + viajeroId + " no esta asociado a el libro con id = " + facturasId);
        }
        persistence.delete(old.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar el review con id = {0} del libro con id = " + viajeroId, facturasId);
    }

    /**
     * Verifica que la descripcion no sea invalido.
     *
     * @param descripcion a verificar
     * @return true si la descripcion es valida.
     */
    private boolean validateDescripcion(String descripcion) {
        return !(descripcion == null || descripcion.isEmpty());
    }
    
    /**
     * Verifica que el costo no sea invalido.
     *
     * @param costo a verificar
     * @return true si el costo es valido.
     */
    private boolean validateCosto(double costo) {
        return costo>=0;
    }
}
