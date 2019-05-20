/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.CiudadEntity;
import co.edu.uniandes.csw.turismo.entities.PlanTuristicoEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.CiudadPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @ciudad David Fonseca
 */

@Stateless
public class CiudadLogic 
{
      private static final Logger LOGGER = Logger.getLogger(CiudadLogic.class.getName());
      
      @Inject
    private CiudadPersistence persistence;

    /**
     * Se encarga de crear un Ciudad en la base de datos.
     *
     * @param ciudadEntity Objeto de CiudadEntity con los datos nuevos
     * @return Objeto de CiudadEntity con los datos nuevos y su ID.
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    public CiudadEntity createCiudad(CiudadEntity ciudadEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del autor");
        
        if(ciudadEntity.darNombre().equals(""))
        {
            throw new BusinessLogicException("Error , no se pueden caracteres vacios");
        }
        if(ciudadEntity.darNombre()==null)
        {
                      throw new BusinessLogicException("Error , no se pueden caracteres nulos");
        }
        if(persistence.find(ciudadEntity.getId())!=null)
        {
            throw new BusinessLogicException("Error , no se pueden caracteres nulos");
                   
        }
        else{
             CiudadEntity newCiudadEntity = persistence.create(ciudadEntity);
            return newCiudadEntity;
            
        }
    }

    /**
     * Obtiene la lista de los registros de Ciudad.
     *
     * @return Colección de objetos de CiudadEntity.
     */
    public List<CiudadEntity> getCiudades() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los autores");
        List<CiudadEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los autores");
        return lista;
    }

    /**
     * Obtiene los datos de una instancia de Ciudad a partir de su ID.
     *
     * @param ciudadsId Identificador de la instancia a consultar
     * @return Instancia de CiudadEntity con los datos del Ciudad consultado.
     */
    public CiudadEntity getCiudad(Long ciudadsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el autor con id = {0}", ciudadsId);
        CiudadEntity ciudadEntity = persistence.find(ciudadsId);
        if (ciudadEntity == null) {
            LOGGER.log(Level.SEVERE, "La editorial con el id = {0} no existe", ciudadsId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el autor con id = {0}", ciudadsId);
        return ciudadEntity;
    }

    /**
     * Actualiza la información de una instancia de Ciudad.
     *
     * @param ciudadsId Identificador de la instancia a actualizar
     * @param ciudadEntity Instancia de CiudadEntity con los nuevos datos.
     * @return Instancia de CiudadEntity con los datos actualizados.
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    public CiudadEntity updateCiudad(Long ciudadsId, CiudadEntity ciudadEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el autor con id = {0}", ciudadsId);
        if(ciudadEntity.darNombre()==null)
        {
                                  throw new BusinessLogicException("Error , no se pueden caracteres nulos");

        }
        if(ciudadEntity.darNombre().equals(""))
        {
                                  throw new BusinessLogicException("Error , no se pueden caracteres nulos");

        }
        else
        {
        CiudadEntity newCiudadEntity = persistence.update(ciudadEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el autor con id = {0}", ciudadsId);
        return newCiudadEntity;
        }
    }

    /**
     * Elimina una instancia de Ciudad de la base de datos.
     *
     * @param ciudadsId Identificador de la instancia a eliminar.
     * @throws BusinessLogicException si el autor tiene libros asociados.
     */
    public void deleteCiudad(Long ciudadsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el autor con id = {0}", ciudadsId);
        List<PlanTuristicoEntity> plans = getCiudad(ciudadsId).darPlanes();
        if (plans != null && !plans.isEmpty()) {
            throw new BusinessLogicException("No se puede borrar el autor con id = " + ciudadsId + " porque tiene plans asociados");
        }
       
        persistence.delete(ciudadsId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el autor con id = {0}", ciudadsId);
    }
              
        
  
    
    
    
}
