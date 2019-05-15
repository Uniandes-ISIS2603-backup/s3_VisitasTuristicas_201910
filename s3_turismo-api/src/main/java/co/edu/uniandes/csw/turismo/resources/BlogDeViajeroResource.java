/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import java.util.logging.Logger;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.turismo.dtos.BlogDeViajeroDTO;
import co.edu.uniandes.csw.turismo.ejb.BlogDeViajeroLogic;
import co.edu.uniandes.csw.turismo.entities.BlogDeViajeroEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Level;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BlogDeViajeroResource {

    @Inject
    private BlogDeViajeroLogic blogDeViajeroLogic;
    
    private static final Logger LOGGER = Logger.getLogger(BlogDeViajeroResource.class.getName());

     /**
     * Crea un nuevo blog con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param planTuristicoId El ID del plan del cual se le agrega el blog
     * @param blog {@link BlogDeViajeroDTO} - El blog que se desea guardar.
     * @return JSON {@link BlogDeViajeroDTO} - La reseña guardada con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el blog.
     */
    @POST
    public BlogDeViajeroDTO crearBlog(@PathParam("planTuristicoId")Long planTuristicoId, BlogDeViajeroDTO blog)throws BusinessLogicException {
        LOGGER.log(Level.INFO, "BlogDeViajeroResource crearBlog: input: {0}", blog.toString());
        BlogDeViajeroDTO nuevoBlogDeViajeroDTO = new BlogDeViajeroDTO(blogDeViajeroLogic.createBlogDeViajero(planTuristicoId, blog.toEntity()));
          LOGGER.log(Level.INFO, "BlogDeViajeroResource crearBlog: output: {0}", nuevoBlogDeViajeroDTO.toString());
        return nuevoBlogDeViajeroDTO;
    }
    

    
 /**
     * Busca y devuelve todos los blogs que existen en un plan.
     *
     * @param planTuristicoId El ID del plan del cual se buscan los blogs
     * @return JSONArray {@link BlogDeViajeroDTO} - Los blogs encontrados en el
     * plam. Si no hay ninguno retorna una lista vacía.
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @GET
    public List<BlogDeViajeroDTO> getBlogs(@PathParam("planTuristicoId") Long planTuristicoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "BlogDeViajeroResource getBlogs: input: {0}", planTuristicoId);
        List<BlogDeViajeroDTO> listaDTOs = listEntity2DTO(blogDeViajeroLogic.getBlogs(planTuristicoId));
        LOGGER.log(Level.INFO, "PlanTuristicoResource getBlogs: output: {0}", listaDTOs.toString());
        return listaDTOs;
    }

    /**
     * Busca y devuelve el blog con el ID recibido en la URL, relativa a un
     * plan.
     *
     * @param planTuristicoId El ID del libro del cual se buscan las reseñas
     * @param blogId El ID del blog que se busca
     * @return {@link BlogDeViajeroDTO} - El Blog encontrado en el plan.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el plan.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el blog.
     */
    @GET
    @Path("{blogId: \\d+}")
    public BlogDeViajeroDTO getBlog(@PathParam("planTuristicoId") Long planTuristicoId, @PathParam("blogId") Long blogId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "BlogDeViajeroResource getBlog: input: {0}", blogId);
        BlogDeViajeroEntity entity = blogDeViajeroLogic.getBlog(planTuristicoId, blogId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /plan/" + planTuristicoId + "/blog/" + blogId + " no existe.", 404);
        }
        BlogDeViajeroDTO blogDTO;
        blogDTO = new BlogDeViajeroDTO(entity);
       // LOGGER.log(Level.INFO, "ValoracionResource getValoracion: output: {0}", ValoracionDTO.toString());
        return blogDTO;
    }

    /**
     * Actualiza un blog con la informacion que se recibe en el cuerpo de la
     * petición y se regresa el objeto actualizado.
     *
     * @param planId El ID del plan del cual se guarda el blog
     * @param blogId El ID del blog que se va a actualizar
     * @param blog {@link BlogDeViajeroDTO} - El blog que se desea guardar.
     * @return JSON {@link BlogDeViajeroDTO} - El blog actualizada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el blog.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el blog.
     */
    @PUT
    @Path("{blogId: \\d+}")
    public BlogDeViajeroDTO updateBlog(@PathParam("planId") Long planId, @PathParam("blogId") Long blogId, BlogDeViajeroDTO blog) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "BlogDeViajeroResource updateBlog: input: planId: {0} , blogId: {1} , blog:{2}", new Object[]{planId, blogId, blog.toString()});
        if (blogId.equals(blog.toEntity().getId())) {
            throw new BusinessLogicException("Los ids de los blogs no coinciden.");
        }
        BlogDeViajeroEntity entity = blogDeViajeroLogic.getBlog(planId, blogId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /plan/" + planId + "/blog/" + blogId + " no existe.", 404);

        }
        BlogDeViajeroDTO blogDTO = new BlogDeViajeroDTO(blogDeViajeroLogic.updateBlog(planId,blog.toEntity()));
        //LOGGER.log(Level.INFO, "BlogDeViajeroResource updateBlog: output:{0}", BlogDeViajeroDTO.toString());
        return blogDTO;

    }

    /**
     * Borra el blog con el id asociado recibido en la URL.
     *
     * @param planId El ID del plan del cual se va a eliminar la valoracion.
     * @param blogId El ID del blog que se va a eliminar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar el blog.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el blog.
     */
    @DELETE
    @Path("{blogId: \\d+}")
    public void deleteBlog(@PathParam("planId") Long planId, @PathParam("blogId") Long blogId) throws BusinessLogicException {
        BlogDeViajeroEntity entity = blogDeViajeroLogic.getBlog(planId,blogId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /plan/" + planId + "/blog/" + blogId + " no existe.", 404);
        }
        blogDeViajeroLogic.deleteBlog(planId, blogId);
    }

    /**
     * Lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos BlogDeViajeroEntity a una lista de
     * objetos BlogDeViajeroDTO (json)
     *
     * @param entityList corresponde a la lista de blogs de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de blogs en forma DTO (json)
     */
    private List<BlogDeViajeroDTO> listEntity2DTO(List<BlogDeViajeroEntity> entityList)throws BusinessLogicException {
        List<BlogDeViajeroDTO> list = new ArrayList<>();
        for (BlogDeViajeroEntity entity : entityList) {
            list.add(new BlogDeViajeroDTO(entity));
        }
        return list;
    }
}

