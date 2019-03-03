/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.ValoracionEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.ValoracionPersistence;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author lm.rodriguezc2
 */

@Stateless
public class ValoracionLogic {
    
    @Inject
    private ValoracionPersistence persistence;
    
     /*
        *Crea una valoracion
        *@param ValoracionEntity
        *@return ValoracionEntity
        *@throw BusinnesLogicException
        */
    public ValoracionEntity createValoracion(ValoracionEntity valoracion)throws BusinessLogicException{ 
         LOGGER.log(Level.INFO, "Inicia proceso de creación de la valoracion");
         
        if(persistence.find(valoracion.getId()) != null)
        {
            throw new BusinessLogicException("Ya existe una valoracion con el id \""+ valoracion.getId()+"\"");
        }        
        if(valoracion.getValoracion()<=0||valoracion.getValoracion()>5)
        {
             throw new BusinessLogicException("La valoración debe estar entre 0 y 5, el número\""+ valoracion.getValoracion()+" no es válido\"");
        }
        //if(valoracion.getComentario()==null)
        //{
        // throw new BusinessLogicException("La valoración debe tener un comentario");   
        //} 
        valoracion = persistence.create(valoracion);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la valoracion");
        return valoracion;
    }
    
       /*
         *Retorna una lista de valoraciones
         *@return listaValoracion
         */
         public List<ValoracionEntity> getValoraciones() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las valoraciones");
        List<ValoracionEntity> listaValoracion = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las valoraciones");
        return listaValoracion;
         }
         
         /*
        *Retorna una valoracion dado un id
        *@param valoracionId
        *@return valoracionEntity
        */
         public ValoracionEntity getValoracion(Long valoracionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la valoracion con id = {0}", valoracionId);
        ValoracionEntity valoracionEntity = persistence.find(valoracionId);
        if (valoracionEntity == null) {
            LOGGER.log(Level.SEVERE, "La valoracion con el id = {0} no existe", valoracionId);
            throw new BusinessLogicException("La valoracion es inválida");

        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la valoracion con id = {0}", valoracionId);
        return valoracionEntity;
    }
         
          /*
         *Actualiza una valoracion y retorna el actualizado dado un id
         *@param valoracionEntity
         *@param valoracionId
         *@return nuevaEntidad
         */
         public ValoracionEntity updateValoracion(Long valoracionId, ValoracionEntity valoracionEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la valoracion  con id = {0}", valoracionId);
        if (persistence.find(valoracionId)!=null) {
            throw new BusinessLogicException("No existe valoración a actualizar");
        }
          if(valoracionEntity.getValoracion()<=0||valoracionEntity.getValoracion()>5)
        {
             throw new BusinessLogicException("La valoración a actualizar debe estar entre 0 y 5, el número\""+ valoracionEntity.getValoracion()+" no es válido\"");
        }
        ValoracionEntity nuevaEntidad = persistence.update(valoracionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la valoracion con id = {0}", valoracionEntity.getId());
        return nuevaEntidad;
    }
    
}
