/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.CiudadEntity;
import co.edu.uniandes.csw.turismo.entities.SitioTuristicoEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.CiudadPersistence;
import co.edu.uniandes.csw.turismo.persistence.PaisPersistence;
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
public class CiudadLogic 
{
      private static final Logger LOGGER = Logger.getLogger(CiudadLogic.class.getName());
      
        @Inject
        private PaisPersistence pais;
        
        @Inject
        private CiudadPersistence ciudad;
        
        
        /*
        *Crea una ciudad
        *@param ciudadEntity
        *@return ciudadEntity
        *@throw BusinnesLogicException
        */
         public CiudadEntity createCiudad(CiudadEntity ciudadEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la ciudad");
        if (ciudadEntity.darPais() == null || pais.find(ciudadEntity.darPais().getId()) == null) {
            throw new BusinessLogicException("La ciudad es inválida");
        }
        if (!validateNombre(ciudadEntity.darNombre())) {
            throw new BusinessLogicException("El Nombre es inválido");
        }
        if (ciudad.findByName(ciudadEntity.darNombre()) != null) {
            throw new BusinessLogicException("La ciudad ya existe");
        }
        ciudad.create(ciudadEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la ciudad");
        return ciudadEntity;
    }
         
         /*
         *Retorna una lista de ciudades
         *@return listaSitio
         */
         public List<CiudadEntity> getCiudades() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las ciudades");
        List<CiudadEntity> listaSitio = ciudad.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las ciudades");
        return listaSitio;
         }
         
         /*
        *Retorna una ciudad dado un id
        *@param ciudadId
        *@return ciudadEntity
        */
         public CiudadEntity getCiudad(Long ciudadId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la con id = {0}", ciudadId);
        CiudadEntity ciudadEntity = ciudad.find(ciudadId);
        if (ciudadEntity == null) {
            LOGGER.log(Level.SEVERE, "La ciudad con el id = {0} no existe", ciudadId);
            throw new BusinessLogicException("La ciudad es inválida");

        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la ciudad con id = {0}", ciudadId);
        return ciudadEntity;
    }
         
          /*
         *Actualiza una ciudad y retorna el actualizado dado un id
         *@param ciudadEntity
         *@param ciudadId
         *@return newEntity
         */
         public CiudadEntity updateCiudad(Long ciudadId, CiudadEntity ciudadEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la ciudad  con id = {0}", ciudadId);
        if (!validateNombre(ciudadEntity.darNombre())) {
            throw new BusinessLogicException("El nombre es inválido");
        }
        CiudadEntity newEntity = ciudad.update(ciudadEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la ciudad con id = {0}", ciudadEntity.getId());
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
