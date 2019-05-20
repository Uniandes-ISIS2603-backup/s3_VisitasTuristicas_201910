/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.CiudadEntity;
import co.edu.uniandes.csw.turismo.entities.PlanTuristicoEntity;
import co.edu.uniandes.csw.turismo.persistence.CiudadPersistence;
import co.edu.uniandes.csw.turismo.persistence.PlanTuristicoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @ciudad david fonseca
 */
@Stateless
public class PlanTuristicoCiudadLogic {
    
    private static final Logger LOGGER = Logger.getLogger(PlanTuristicoCiudadLogic.class.getName());

    @Inject
    private PlanTuristicoPersistence planTuristicoPersistence;

    @Inject
    private CiudadPersistence ciudadPersistence;

    /**
     * Asocia un Ciudad existente a un PlanTuristico
     *
     * @param planTuristicosId Identificador de la instancia de PlanTuristico
     * @param ciudadsId Identificador de la instancia de Ciudad
     * @return Instancia de CiudadEntity que fue asociada a PlanTuristico
     */
    public CiudadEntity addCiudad(Long planTuristicosId, Long ciudadsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un autor al libro con id = {0}", planTuristicosId);
        CiudadEntity ciudadEntity = ciudadPersistence.find(ciudadsId);
        PlanTuristicoEntity planTuristicoEntity = planTuristicoPersistence.find(planTuristicosId);
        planTuristicoEntity.getCiudades().add(ciudadEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un autor al libro con id = {0}", planTuristicosId);
        return ciudadPersistence.find(ciudadsId);
    }

    /**
     * Obtiene una colección de instancias de CiudadEntity asociadas a una
     * instancia de PlanTuristico
     *
     * @param planTuristicosId Identificador de la instancia de PlanTuristico
     * @return Colección de instancias de CiudadEntity asociadas a la instancia
     * de PlanTuristico
     */
    public List<CiudadEntity> getCiudads(Long planTuristicosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los autores del libro con id = {0}", planTuristicosId);
        return planTuristicoPersistence.find(planTuristicosId).getCiudades();
    }

    /**
     * Obtiene una instancia de CiudadEntity asociada a una instancia de PlanTuristico
     *
     * @param planTuristicosId Identificador de la instancia de PlanTuristico
     * @param ciudadsId Identificador de la instancia de Ciudad
     * @return La entidad del Autor asociada al libro
     */
    public CiudadEntity getCiudad(Long planTuristicosId, Long ciudadsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un autor del libro con id = {0}", planTuristicosId);
        List<CiudadEntity> ciudads = planTuristicoPersistence.find(planTuristicosId).getCiudades();
        CiudadEntity ciudadEntity = ciudadPersistence.find(ciudadsId);
        int index = ciudads.indexOf(ciudadEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar un autor del libro con id = {0}", planTuristicosId);
        if (index >= 0) {
            return ciudads.get(index);
        }
        return null;
    }

    /**
     * Remplaza las instancias de Ciudad asociadas a una instancia de PlanTuristico
     *
     * @param planTuristicosId Identificador de la instancia de PlanTuristico
     * @param list Colección de instancias de CiudadEntity a asociar a instancia
     * de PlanTuristico
     * @return Nueva colección de CiudadEntity asociada a la instancia de PlanTuristico
     */
    public List<CiudadEntity> replaceCiudads(Long planTuristicosId, List<CiudadEntity> list) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los autores del libro con id = {0}", planTuristicosId);
        PlanTuristicoEntity planTuristicoEntity = planTuristicoPersistence.find(planTuristicosId);
        planTuristicoEntity.setCiudades(list);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los autores del libro con id = {0}", planTuristicosId);
        return planTuristicoPersistence.find(planTuristicosId).getCiudades();
    }

    /**
     * Desasocia un Ciudad existente de un PlanTuristico existente
     *
     * @param planTuristicosId Identificador de la instancia de PlanTuristico
     * @param ciudadsId Identificador de la instancia de Ciudad
     */
    public void removeCiudad(Long planTuristicosId, Long ciudadsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un autor del libro con id = {0}", planTuristicosId);
        CiudadEntity ciudadEntity = ciudadPersistence.find(ciudadsId);
        PlanTuristicoEntity planTuristicoEntity = planTuristicoPersistence.find(planTuristicosId);
        planTuristicoEntity.getCiudades().remove(ciudadEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un autor del libro con id = {0}", planTuristicosId);
    }
    
}
