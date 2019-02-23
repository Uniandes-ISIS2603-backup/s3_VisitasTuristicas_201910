/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.CiudadEntity;
import co.edu.uniandes.csw.turismo.entities.PaisEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.PaisPersistence;
import co.edu.uniandes.csw.turismo.persistence.PlanTuristicoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author David Fonseca
 */

@Stateless
public class PaisLogic 
{
        private static final Logger LOGGER = Logger.getLogger(PaisLogic.class.getName());
        
        @Inject
        private PaisPersistence pais;
        
        @Inject
        private PlanTuristicoPersistence plan;
        
        
        /*
        *Retorna una lista de paises
        *@return listaPais
        */
         public List<PaisEntity> getPaises() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas los paises");
        List<PaisEntity> listaPais = pais.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas los paises");
        return listaPais;
         }
         
                  /*
        *Retorna un pais dado un id
        *@param paisId
        *@return paisEntity
        */
         public PaisEntity getPais(Long paisId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el pais con id = {0}", paisId);
        PaisEntity paisEntity = pais.find(paisId);
        if (paisEntity == null) {
            LOGGER.log(Level.SEVERE, "el pais con el id = {0} no existe", paisId);
            throw new BusinessLogicException("el pais es inválido");

        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el pais con id = {0}", paisId);
        return paisEntity;
         }
         
         
                   /*
         *Actualiza un pais y retorna el actualizado dado un id
         *@param paisEntity
         *@param paisId
         *@return newEntity
         */
         public PaisEntity updatePais(Long paisId, PaisEntity paisEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el pais  con id = {0}", paisId);
        if (!validateNombre(paisEntity.darNombre())) {
            throw new BusinessLogicException("El nombre es inválido");
        }
        PaisEntity newEntity = pais.update(paisEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el pais con id = {0}", paisEntity.getId());
        return newEntity;
    }

    
    /*
         *Valida si el nombre no es nullo o vacio
         *@return nombre
         */
    private boolean validateNombre(String nombre) {
        return !(nombre == null || nombre.isEmpty());
         }
    
    
    
    
}
