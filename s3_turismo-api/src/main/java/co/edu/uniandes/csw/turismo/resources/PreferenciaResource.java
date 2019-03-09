/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.PreferenciaDTO;
import co.edu.uniandes.csw.turismo.ejb.PreferenciaLogic;
import co.edu.uniandes.csw.turismo.entities.PreferenciaEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Juan Sebastian Gutierrez S.
 */

    @Path("/preferencias")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PreferenciaResource {
    private static final Logger LOGGER=Logger.getLogger(PlanTuristicoResource.class.getName());
    
    @Inject
    private PreferenciaLogic logic;
    
    /**
     * crea una preferencia
     * @param preferencia
     * @return Preferencia
     * @throws BusinessLogicException
     */
    @POST
    public PreferenciaDTO createPreferencia(PreferenciaDTO preferencia) throws BusinessLogicException {
         LOGGER.log(Level.INFO, "CrearPreferenciaResource crearViajero", "a");
        PreferenciaDTO nuevoViajeroDTO = new PreferenciaDTO(logic.createPreferencia( preferencia.toEntity()));
          LOGGER.log(Level.INFO, "PreferenciaResource crearBlog: output: {0}", nuevoViajeroDTO.toString());
        return nuevoViajeroDTO;
    }
    
    /**
     * retorna un viajero con un id
     * @param pIdPreferencia
     * @return PreferenciaDTO
     * @throws BusinessLogicException
     */
    @GET
    public PreferenciaDTO getPreferencia(@PathParam("id")Long pIdPreferencia) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "getPreferenciaResource");
        PreferenciaDTO listaDTOs = new PreferenciaDTO(logic.getPreferencia(pIdPreferencia));
        LOGGER.log(Level.INFO, "getViajeroResource", listaDTOs.toString());
        return listaDTOs;
    }
    
    /**
     * consigue todos los blogs
     * @param planTuristicoId
     * @return
     * @throws BusinessLogicException 
     */
    //@GET
    //public List<PreferenciaDTO> getPreferencias() throws BusinessLogicException {
    //    LOGGER.log(Level.INFO, "PreferenciaResource getBlogs: input: {0}");
    //    List<PreferenciaDTO> listaDTOs = listEntity2DTO(logic.getPreferencias());
    //    LOGGER.log(Level.INFO, "PreferenciaResource getBlogs: output: {0}", listaDTOs.toString());
    //    return listaDTOs;
    //}
    
    //private List<PreferenciaDTO> listEntity2DTO(List<PreferenciaEntity> entityList)throws BusinessLogicException {
    //    List<PreferenciaDTO> list = new ArrayList<>();
    //    for (PreferenciaEntity entity : entityList) {
    //        list.add(new PreferenciaDTO(entity));
    //    }
    //    return list;
    //}
    
    /**
     * asigna un viajero
     * @param idPreferencia
     * @param nuevaPreferencia
     * @throws BusinessLogicException
     */
    @PUT
    public void setPreferencia(@PathParam("id") Long idPreferencia, PreferenciaDTO nuevaPreferencia) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "updatePreferencia");
        logic.updatePreferencia(idPreferencia, nuevaPreferencia.toEntity());
    }
    
//    @PUT
//    public void setPreferencias(ArrayList<PreferenciaDTO> preferencias, ArrayList<PreferenciaDTO> nuevasPreferencias) {
//        preferencias = nuevasPreferencias;
//    }
    
    /**
     * elimina un viajero
     * @param pIdPreferencia
     * @throws BusinessLogicException
     */
    @DELETE
    public void deletePreferencia(Long pIdPreferencia) throws BusinessLogicException {
       logic.deletePreferencia(pIdPreferencia);
    }
}
