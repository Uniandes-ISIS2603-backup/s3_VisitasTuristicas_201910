/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.BlogDeViajeroEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.BlogDeViajeroPersistence;
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
public class BlogDeViajeroLogic {
      @Inject
    private BlogDeViajeroPersistence persistence;
    
       /*
        *Crea un blog de viajero
        *@param BlogDeViajeroEntity
        *@return BlogDeViajeroEntity
        *@throw BusinnesLogicException
        */
      
    public BlogDeViajeroEntity createBlogDeViajero(BlogDeViajeroEntity blogDeViajero)throws BusinessLogicException{ 
         LOGGER.log(Level.INFO, "Inicia proceso de creación del blog de viajero");
        if(persistence.find(blogDeViajero.getId()) != null)
        {
            throw new BusinessLogicException("Ya existe un blog de viajero con el id \""+ blogDeViajero.getId()+"\"");
        }
         if(blogDeViajero.getLikes()<=0)
        {
             throw new BusinessLogicException("El número de likes no pueden ser negativos, el número\""+ blogDeViajero.getLikes()+" no es válido\"");
        }
        blogDeViajero = persistence.create(blogDeViajero);
        LOGGER.log(Level.INFO, "Termina proceso de creación del blog de viajero");
        //condición del blog de viajero
        return blogDeViajero;
    }
    /*
         *Retorna una lista de blogs de viajero
         *@return listaBlogDeViajero
         */
         public List<BlogDeViajeroEntity> getBlogs() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los blogs de viajero");
        List<BlogDeViajeroEntity> listaBlogs = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los blogs de viajero");
        return listaBlogs;
         }
         
         /*
        *Retorna un blog de viajero dado un id
        *@param blogDeViajeroId
        *@return blogDeViajeroEntity
        */
         public BlogDeViajeroEntity getBlog(Long blogDeViajeroId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el blog de viajerocon id = {0}", blogDeViajeroId);
        BlogDeViajeroEntity blogDeViajeroEntity = persistence.find(blogDeViajeroId);
        if (blogDeViajeroEntity == null) {
            LOGGER.log(Level.SEVERE, "El blog de viajero con el id = {0} no existe", blogDeViajeroId);
            throw new BusinessLogicException("El blog de viajero es inválido");

        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el blog de viajero con id = {0}", blogDeViajeroId);
        return blogDeViajeroEntity;
    }
         
          /*
         *Actualiza un blog de viajero y retorna el actualizado dado un id
         *@param blogDeViajeroEntity
         *@param blogDeViajeroId
         *@return nuevaEntidad
         */
         public BlogDeViajeroEntity updateBlog(Long blogDeViajeroId, BlogDeViajeroEntity blogDeViajeroEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el blog de viajero con id = {0}", blogDeViajeroId);
        if (persistence.find(blogDeViajeroId)!=null) {
            throw new BusinessLogicException("No existe blog de viajero a actualizar");
        }
          if(blogDeViajeroEntity.getLikes()<=0)
        {
             throw new BusinessLogicException("El blog de viajero a actualizar debe tener un número de likes positivos, el número\""+ blogDeViajeroEntity.getLikes()+" no es válido\"");
        }
        BlogDeViajeroEntity nuevaEntidad = persistence.update(blogDeViajeroEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el blog de viajero con id = {0}", blogDeViajeroEntity.getId());
        return nuevaEntidad;
    }
}
