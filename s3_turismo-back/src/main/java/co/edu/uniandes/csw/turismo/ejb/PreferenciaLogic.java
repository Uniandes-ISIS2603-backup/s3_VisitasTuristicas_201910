/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.PreferenciaEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.PreferenciaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class PreferenciaLogic {
    
    @Inject
    PreferenciaPersistence persistence;
    
    private static final Logger LOGGER = Logger.getLogger(ViajeroLogic.class.getName());
    
    public PreferenciaEntity createPreferencia(PreferenciaEntity preferencia) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la preferencia");
        if(preferencia.getNombrePreferencia() == null || preferencia.getNombrePreferencia().isEmpty()) throw new BusinessLogicException("El nombre no es valido");
        preferencia = persistence.create(preferencia);
        return preferencia;
    }
    
    public PreferenciaEntity getPreferencia(Long pIdPreferencia) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia busqueda de la preferencia");
        PreferenciaEntity res = persistence.find(pIdPreferencia);
        if(res == null) throw new BusinessLogicException("No existe la preferencia");
        return res;
    }
    
    public List<PreferenciaEntity> getPreferencias() throws BusinessLogicException {
         LOGGER.log(Level.INFO, "Inicia retorno de todas las preferencias");
         List<PreferenciaEntity> res = persistence.findAll();
         if(res == null) throw new BusinessLogicException("No hay preferencias");
         return res;
    }
    
    public PreferenciaEntity updatePreferencia(Long pIdPreferencia, PreferenciaEntity nouveau) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia actualizacion de preferencia");
        if(nouveau.getNombrePreferencia() == null || nouveau.getNombrePreferencia().isEmpty()) throw new BusinessLogicException("La preferencia no tiene nombre valido");
        PreferenciaEntity preferenciaUpdate = persistence.find(pIdPreferencia);
        preferenciaUpdate = nouveau;
        persistence.update(preferenciaUpdate);
        return preferenciaUpdate;
    }
    
    public PreferenciaEntity deletePreferencia(long pIdPreferencia) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia eliminacion de preferencia");
        PreferenciaEntity aBorrar = persistence.find(pIdPreferencia);
        persistence.delete(pIdPreferencia);
        if(persistence.find(pIdPreferencia) != null) throw new BusinessLogicException("No se borro correctamente");
        return aBorrar;
    }
}
