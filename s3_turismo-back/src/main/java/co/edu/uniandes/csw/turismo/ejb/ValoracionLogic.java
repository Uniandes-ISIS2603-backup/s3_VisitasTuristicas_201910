/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.PlanTuristicoEntity;
import co.edu.uniandes.csw.turismo.entities.ValoracionEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.PlanTuristicoPersistence;
import co.edu.uniandes.csw.turismo.persistence.ValoracionPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author lm.rodriguezc2
 */

@Stateless
public class ValoracionLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ValoracionLogic.class.getName());
    
    @Inject
    private ValoracionPersistence persistence;
    
     @Inject
    private PlanTuristicoPersistence planPersistence;
    
     /*
        *Crea una valoracion
        *@param ValoracionEntity
        *@return ValoracionEntity
        *@throw BusinnesLogicException
        */
    public ValoracionEntity createValoracion(Long planTuristicoId, ValoracionEntity valoracion)throws BusinessLogicException{ 
         LOGGER.log(Level.INFO, "Inicia proceso de creación de la valoracion");
          PlanTuristicoEntity plan = planPersistence.find(planTuristicoId);
          if(plan==null)
          {
              throw new BusinessLogicException("No existe el plan a valorar ");
          }
          valoracion.setPlanTuristico(plan);
        if(persistence.find(planTuristicoId, valoracion.getId()) != null)
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
         public List<ValoracionEntity> getValoraciones(Long planId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las valoraciones");
            PlanTuristicoEntity planEntity = planPersistence.find(planId);
            //return planEntity.getValoraciones(); Se debe crear el método grtValoracionesen planEntity
        List<ValoracionEntity> listaValoracion = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las valoraciones");
        return listaValoracion;
         }
         
         /*
        *Retorna una valoracion dado un id
        *@param valoracionId
        *@return valoracionEntity
        */
         public ValoracionEntity getValoracion(Long planId, Long valoracionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la valoracion con id = {0}", valoracionId);
        ValoracionEntity valoracionEntity = persistence.find(planId, valoracionId);
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
         public ValoracionEntity updateValoracion(Long planId,ValoracionEntity valoracionEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la valoracion  con id = {0}", valoracionEntity.getId());
        if (persistence.find(planId, valoracionEntity.getId())!=null) {
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
         
          /**
     * Elimina una instancia de valoracion de la base de datos.
     *
     * @param valoracionId Identificador de la instancia a eliminar.
     * @param planId id del plan el cual es padre de la valoracion.
     * @throws BusinessLogicException Si la valoracion no esta asociada al plan.
     *
     */
    public void deleteValoracion(Long planId, Long valoracionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la valoracion con id = {0} del plan con id = " + planId, valoracionId);
        ValoracionEntity old = getValoracion(planId, valoracionId);
        if (old == null) {
            throw new BusinessLogicException("La valoracion con id = " + valoracionId + " no esta asociado a el plan con id = " + planId);
        }
        persistence.delete(old.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar la valoracion con id = {0} del plan con id = " + planId, valoracionId);
    }
    
}
