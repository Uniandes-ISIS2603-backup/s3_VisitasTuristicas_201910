/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;
import co.edu.uniandes.csw.turismo.entities.CiudadEntity;
import co.edu.uniandes.csw.turismo.entities.PlanTuristicoEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
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
public class CiudadPlanTuristicosLogic 
{
    
    private static final Logger LOGGER = Logger.getLogger(CiudadPlanTuristicosLogic.class.getName());

    @Inject
    private PlanTuristicoPersistence planTuristicoPersistence;

    @Inject
    private CiudadPersistence ciudadPersistence;

    /**
     * Asocia un PlanTuristico existente a un Ciudad
     *
     * @param ciudadsId Identificador de la instancia de Ciudad
     * @param planTuristicosId Identificador de la instancia de PlanTuristico
     * @return Instancia de PlanTuristicoEntity que fue asociada a Ciudad
     */
    public PlanTuristicoEntity addPlanTuristico(Long ciudadsId, Long planTuristicosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un libro al autor con id = {0}", ciudadsId);
        CiudadEntity ciudadEntity = ciudadPersistence.find(ciudadsId);
        PlanTuristicoEntity planTuristicoEntity = planTuristicoPersistence.find(planTuristicosId);
        planTuristicoEntity.getCiudades().add(ciudadEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un libro al autor con id = {0}", ciudadsId);
        return planTuristicoPersistence.find(planTuristicosId);
    }

    /**
     * Obtiene una colección de instancias de PlanTuristicoEntity asociadas a una
     * instancia de Ciudad
     *
     * @param ciudadsId Identificador de la instancia de Ciudad
     * @return Colección de instancias de PlanTuristicoEntity asociadas a la instancia de
     * Ciudad
     */
    public List<PlanTuristicoEntity> getPlanTuristicos(Long ciudadsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los libros del autor con id = {0}", ciudadsId);
        return ciudadPersistence.find(ciudadsId).darPlanes();
    }

    /**
     * Obtiene una instancia de PlanTuristicoEntity asociada a una instancia de Ciudad
     *
     * @param ciudadsId Identificador de la instancia de Ciudad
     * @param planTuristicosId Identificador de la instancia de PlanTuristico
     * @return La entidadd de Libro del autor
     * @throws BusinessLogicException Si el libro no está asociado al autor
     */
    public PlanTuristicoEntity getPlanTuristico(Long ciudadsId, Long planTuristicosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro con id = {0} del autor con id = " + ciudadsId, planTuristicosId);
        List<PlanTuristicoEntity> planTuristicos = ciudadPersistence.find(ciudadsId).darPlanes();
        PlanTuristicoEntity planTuristicoEntity = planTuristicoPersistence.find(planTuristicosId);
        int index = planTuristicos.indexOf(planTuristicoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el libro con id = {0} del autor con id = " + ciudadsId, planTuristicosId);
        if (index >= 0) {
            return planTuristicos.get(index);
        }
        throw new BusinessLogicException("El libro no está asociado al autor");
    }

    /**
     * Remplaza las instancias de PlanTuristico asociadas a una instancia de Ciudad
     *
     * @param ciudadId Identificador de la instancia de Ciudad
     * @param planTuristicos Colección de instancias de PlanTuristicoEntity a asociar a instancia
     * de Ciudad
     * @return Nueva colección de PlanTuristicoEntity asociada a la instancia de Ciudad
     */
    public List<PlanTuristicoEntity> replacePlanTuristicos(Long ciudadId, List<PlanTuristicoEntity> planTuristicos) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los libros asocidos al ciudad con id = {0}", ciudadId);
        CiudadEntity ciudadEntity = ciudadPersistence.find(ciudadId);
        List<PlanTuristicoEntity> planTuristicoList = planTuristicoPersistence.findAll();
        for (PlanTuristicoEntity planTuristico : planTuristicoList) {
            if (planTuristicos.contains(planTuristico)) {
                if (!planTuristico.getCiudades().contains(ciudadEntity)) {
                    planTuristico.getCiudades().add(ciudadEntity);
                }
            } else {
                planTuristico.getCiudades().remove(ciudadEntity);
            }
        }
        ciudadEntity.actualizarPlanes(planTuristicos);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los libros asocidos al ciudad con id = {0}", ciudadId);
        return ciudadEntity.darPlanes();
    }

    /**
     * Desasocia un PlanTuristico existente de un Ciudad existente
     *
     * @param ciudadsId Identificador de la instancia de Ciudad
     * @param planTuristicosId Identificador de la instancia de PlanTuristico
     */
    public void removePlanTuristico(Long ciudadsId, Long planTuristicosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un libro del ciudad con id = {0}", ciudadsId);
        CiudadEntity ciudadEntity = ciudadPersistence.find(ciudadsId);
        PlanTuristicoEntity planTuristicoEntity = planTuristicoPersistence.find(planTuristicosId);
        planTuristicoEntity.getCiudades().remove(ciudadEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un libro del ciudad con id = {0}", ciudadsId);
    }
}
