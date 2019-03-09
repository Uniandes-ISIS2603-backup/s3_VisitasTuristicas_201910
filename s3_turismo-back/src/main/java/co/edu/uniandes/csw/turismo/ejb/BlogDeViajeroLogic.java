/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.BlogDeViajeroEntity;
import co.edu.uniandes.csw.turismo.entities.PlanTuristicoEntity;
import co.edu.uniandes.csw.turismo.entities.ValoracionEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.BlogDeViajeroPersistence;
import co.edu.uniandes.csw.turismo.persistence.PlanTuristicoPersistence;
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
public class BlogDeViajeroLogic {
    
    private static final Logger LOGGER = Logger.getLogger(BlogDeViajeroLogic.class.getName());
    
    @Inject
    private BlogDeViajeroPersistence persistence;
      
    @Inject
    private PlanTuristicoPersistence planPersistence;
    
       /*
        *Crea un blog de viajero
        *@param BlogDeViajeroEntity
        *@return BlogDeViajeroEntity
        *@throw BusinnesLogicException
        */
    public BlogDeViajeroEntity createBlogDeViajero(Long planTuristicoId,BlogDeViajeroEntity blogDeViajero)throws BusinessLogicException{ 
         PlanTuristicoEntity plan = planPersistence.find(planTuristicoId);
          if(plan==null)
          {
              throw new BusinessLogicException("No existe el plan para hacer el blog ");
          }
          blogDeViajero.setPlanTuristico(plan);
        if(persistence.find(planTuristicoId) != null)
        {
            throw new BusinessLogicException("Ya existe un blog de viajero con el id \""+ blogDeViajero.getId()+"\"");
        }
         if(blogDeViajero.getLikes()<=0)
        {
             throw new BusinessLogicException("El número de likes no pueden ser negativos, el número\""+ blogDeViajero.getLikes()+" no es válido\"");
        }
        blogDeViajero = persistence.create(blogDeViajero);
       
        //condición del blog de viajero
        return blogDeViajero;
    }
    /*
         *Retorna una lista de blogs de viajero
         *@return listaBlogDeViajero
         */
         public List<BlogDeViajeroEntity> getBlogs(Long planId) {
         PlanTuristicoEntity planEntity = planPersistence.find(planId);
            //return planEntity.getBlogs(); Se debe crear el método getBlogs en planEntity
        List<BlogDeViajeroEntity> listaBlogs = persistence.findAll();

        return listaBlogs;
         }
         
         /*
        *Retorna un blog de viajero dado un id
        *@param blogDeViajeroId
        *@return blogDeViajeroEntity
        */
         public BlogDeViajeroEntity getBlog(Long planId, Long blogDeViajeroId) throws BusinessLogicException {

        BlogDeViajeroEntity blogDeViajeroEntity = persistence.find(planId);
        if (blogDeViajeroEntity == null) {

            throw new BusinessLogicException("El blog de viajero es inválido");

        }

        return blogDeViajeroEntity;
    }
         
          /*
         *Actualiza un blog de viajero y retorna el actualizado dado un id
         *@param blogDeViajeroEntity
         *@param blogDeViajeroId
         *@return nuevaEntidad
         */
         public BlogDeViajeroEntity updateBlog(Long planId, BlogDeViajeroEntity blogDeViajeroEntity) throws BusinessLogicException {

        if (persistence.find(blogDeViajeroEntity.getId())==null ){
            throw new BusinessLogicException("No existe blog de viajero a actualizar");
        }
        if(blogDeViajeroEntity.getLikes()<0)
        {
             throw new BusinessLogicException("El blog de viajero a actualizar debe tener un número de likes positivos, el número\""+ blogDeViajeroEntity.getLikes()+" no es válido\"");
        }
        BlogDeViajeroEntity nuevaEntidad = persistence.update(blogDeViajeroEntity);
       
        return nuevaEntidad;
    }
         /**
     * Elimina una instancia de blog de la base de datos.
     *
     * @param blogId Identificador de la instancia a eliminar.
     * @param planId id del plan el cual es padre del blog.
     * @throws BusinessLogicException Si el blog no esta asociado al plan.
     *
     */
    public void deleteBlog(Long planId, Long blogId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el blog con id = {0} del plan con id = " + planId, blogId);
        BlogDeViajeroEntity old = getBlog(planId, blogId);
        if (old == null) {
            throw new BusinessLogicException("El blog con id = " + blogId + " no esta asociado a el plan con id = " + planId);
        }
        persistence.delete(old.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar el blog con id = {0} del plan con id = " + planId, blogId);
    }
    
    
    public void delete(Long id)
    {
        persistence.delete(id);
    }
}
