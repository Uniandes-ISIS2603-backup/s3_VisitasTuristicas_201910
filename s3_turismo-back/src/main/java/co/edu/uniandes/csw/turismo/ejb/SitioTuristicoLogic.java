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
import co.edu.uniandes.csw.turismo.persistence.SitioTuristicoPersistence;
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
public class SitioTuristicoLogic {

    private static final Logger LOGGER = Logger.getLogger(SitioTuristicoLogic.class.getName());

    @Inject
    private SitioTuristicoPersistence sitio;

    @Inject
    private CiudadPersistence ciudad;

    /*
       *Crea un sitio turistico
       *@param sitioEntity
       *@return sitioEntity
       *@throw BusinessLogicException
     */
    public SitioTuristicoEntity createSitio(SitioTuristicoEntity sitioEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la ciudad");
        if (sitioEntity.darCiudad() == null || ciudad.find(sitioEntity.darCiudad().getId()) == null) {
            throw new BusinessLogicException("El sitio turistico es inválida");
        }
        if (!validateNombre(sitioEntity.darNombre())) {
            throw new BusinessLogicException("El Nombre es inválido");
        }
        if (ciudad.findByName(sitioEntity.darNombre()) != null) {
            throw new BusinessLogicException("El sitio turistico ya existe");
        }
        sitio.create(sitioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del sitio");
        return sitioEntity;
    }

    /*
         *Retorna una lista de sitios turisticos
         *@return listaSitio
     */
    public List<SitioTuristicoEntity> getSitios() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los Sitios turisticos");
        List<SitioTuristicoEntity> listaSitio = sitio.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los Sitios turisticos");
        return listaSitio;
    }

    /*
        *Retorna un sitio dado un id
        *@param sitioId
        *@return ciudadEntity
     */
    public SitioTuristicoEntity getSitio(Long sitioId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el sitio turistico con id = {0}", sitioId);
        SitioTuristicoEntity sitioEntity = sitio.find(sitioId);
        if (sitioEntity == null) {
            LOGGER.log(Level.SEVERE, "El sitio con el id = {0} no existe", sitioId);
            throw new BusinessLogicException("El sitio turistico es inválida");

        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el sitio turistico con id = {0}", sitioId);
        return sitioEntity;
    }

    /*
         *Actualiza un sitio turistico y retorna el actualizado dado un id
         *@param sitioEntity
         *@param sitioId
         *@return newEntity
     */
    public SitioTuristicoEntity updateSitio(Long sitioId, SitioTuristicoEntity sitioEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el sitio turistico con id = {0}", sitioId);
        if (!validateNombre(sitioEntity.darNombre())) {
            throw new BusinessLogicException("El nombre es inválido");
        }
        SitioTuristicoEntity newEntity = sitio.update(sitioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el sitio turistico con id = {0}", sitioEntity.getId());
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
