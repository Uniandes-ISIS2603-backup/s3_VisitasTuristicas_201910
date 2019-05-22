/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.PreferenciaEntity;
import co.edu.uniandes.csw.turismo.entities.ViajeroEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.PreferenciaPersistence;
import co.edu.uniandes.csw.turismo.persistence.ViajeroPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class PreferenciaLogic {
    
    @Inject
    private PreferenciaPersistence persistence;

    @Inject
    private ViajeroPersistence viajeroPersistence;

    /**
     * Se encarga de crear un SitioTuritico en la base de datos.
     *
     * @param reviewEntity Objeto de SitioTuriticoEntity con los datos nuevos
     * @param viajerosId id del Viajero el cual sera padre del nuevo SitioTuritico.
     * @return Objeto de SitioTuriticoEntity con los datos nuevos y su ID.
     * @throws BusinessLogicException si viajerosId no es el mismo que tiene el
     * entity.
     *
     */
    public PreferenciaEntity createPreferencia(Long viajerosId, PreferenciaEntity reviewEntity) throws BusinessLogicException {
        ViajeroEntity viajero = viajeroPersistence.find(viajerosId);
        reviewEntity.setViajero(viajero);
        return persistence.create(reviewEntity);
    }

    /**
     * Obtiene la lista de los registros de SitioTuritico que pertenecen a un Viajero.
     *
     * @param viajerosId id del Viajero el cual es padre de los SitioTuriticos.
     * @return Colección de objetos de SitioTuriticoEntity.
     */
    public List<PreferenciaEntity> getPreferencias(Long viajerosId) {
        ViajeroEntity viajeroEntity = viajeroPersistence.find(viajerosId);
        return viajeroEntity.getPreferencias();
    }

    /**
     * Obtiene los datos de una instancia de SitioTuritico a partir de su ID. La
     * existencia del elemento padre Viajero se debe garantizar.
     *
     * @param viajerosId El id del Libro buscado
     * @param reviewsId Identificador de la Reseña a consultar
     * @return Instancia de SitioTuriticoEntity con los datos del SitioTuritico consultado.
     *
     */
    public PreferenciaEntity getPreferencia(Long viajerosId, Long reviewsId) {
        return persistence.find(viajerosId, reviewsId);
    }

    /**
     * Actualiza la información de una instancia de SitioTuritico.
     *
     * @param reviewEntity Instancia de SitioTuriticoEntity con los nuevos datos.
     * @param viajerosId id del Viajero el cual sera padre del SitioTuritico actualizado.
     * @return Instancia de SitioTuriticoEntity con los datos actualizados.
     *
     */
    public PreferenciaEntity updatePreferencia(Long viajerosId, PreferenciaEntity reviewEntity) {
        ViajeroEntity viajeroEntity = viajeroPersistence.find(viajerosId);
        reviewEntity.setViajero(viajeroEntity);
        persistence.update(reviewEntity);
        return reviewEntity;
    }

    /**
     * Elimina una instancia de SitioTuritico de la base de datos.
     *
     * @param reviewsId Identificador de la instancia a eliminar.
     * @param viajerosId id del Viajero el cual es padre del SitioTuritico.
     * @throws BusinessLogicException Si la reseña no esta asociada al libro.
     *
     */
    public void deleteSitioTuritico(Long viajerosId, Long reviewsId) throws BusinessLogicException {
        PreferenciaEntity old = getPreferencia(viajerosId, reviewsId);
        if (old == null) {
            throw new BusinessLogicException("El review con id = " + reviewsId + " no esta asociado a el libro con id = " + viajerosId);
        }
        persistence.delete(old.getId());
    }
         
         
         /*
         *Valida si el nombre no es nullo o vacio
         *@return nombre
         */
    private boolean validateNombre(String nombre) {
        return !(nombre == null || nombre.isEmpty());
         }   
}
