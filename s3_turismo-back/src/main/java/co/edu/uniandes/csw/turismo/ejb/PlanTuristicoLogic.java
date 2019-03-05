/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.PlanTuristicoEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.PlanTuristicoPersistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Christer Osorio
 */
@Stateless
public class PlanTuristicoLogic {

    @Inject
    private PlanTuristicoPersistence persistence;

    public PlanTuristicoEntity createPlanTuristico(PlanTuristicoEntity planTuristico) throws BusinessLogicException {
        if (persistence.findByName(planTuristico.getNombrePlan()) != null) {
            throw new BusinessLogicException("Ya existe un plan turistico con ese nombre");
        }
        if (!nombrePlanEsValido(planTuristico.getNombrePlan())) {
            throw new BusinessLogicException("El nombre del plan no es valido");
        }
        if (!tipoPlanEsValido(planTuristico.getTipoPlan())) {
            throw new BusinessLogicException("El tipo del plan no es valido");
        }
        if (!descripcionEsValido(planTuristico.getDescripcion())) {
            throw new BusinessLogicException("la descripcion del plan no es valida");
        }
        if (!duracionEsValido(planTuristico.getDuracion())) {
            throw new BusinessLogicException("La duracion del plan no es valida");
        }
        if (!idiomaValido(planTuristico.getIdioma())) {
            throw new BusinessLogicException("El idioma del plan no es valido");
        }
        if (!ubicacionEsValido(planTuristico.getUbicacion())) {
            throw new BusinessLogicException("La ubicacion del plan no es valido");
        }
        if (!costoPorPersonaEsValido(planTuristico.getCostoPorPersona())) {
            throw new BusinessLogicException("El costo del plan no es valido");
        }
        
        if (!guiaValido(planTuristico.getGuia())) {
            throw new BusinessLogicException("Guia no puede ser nulo");
        }
        planTuristico = persistence.create(planTuristico);
        return planTuristico;
    }

    public void deletePlanTuristico(Long planTuristicoId) throws BusinessLogicException {
        if (persistence.find(planTuristicoId) == null) {
            throw new BusinessLogicException("No existe un planturistico con ese id");
        }

        persistence.delete(planTuristicoId);

        if (persistence.find(planTuristicoId) != null) {
            throw new BusinessLogicException("No se pudo borrar el planturistico");
        }
    }

    public PlanTuristicoEntity getPlanTuristico(Long planTuristicoId)
    {
        PlanTuristicoEntity planTuristicolEntity = persistence.find(planTuristicoId);
        if (planTuristicolEntity == null) {
           
        }
       
        return planTuristicolEntity;
    }
    public List<PlanTuristicoEntity> getPlanesTuristicos() {
     
        List<PlanTuristicoEntity> planes = persistence.findAll();
        
        return planes;
    }

    public PlanTuristicoEntity updatePlanTuristico(Long planTuristicoId, PlanTuristicoEntity planTuristicoEntity) throws BusinessLogicException {
      
        if (!nombrePlanEsValido(planTuristicoEntity.getNombrePlan())) {
            throw new BusinessLogicException("El nombre del plan no es valido");
        }
        if (!tipoPlanEsValido(planTuristicoEntity.getTipoPlan())) {
            throw new BusinessLogicException("El tipo del plan no es valido");
        }
        if (!descripcionEsValido(planTuristicoEntity.getDescripcion())) {
            throw new BusinessLogicException("la descripcion del plan no es valida");
        }
        if (!duracionEsValido(planTuristicoEntity.getDuracion())) {
            throw new BusinessLogicException("La duracion del plan no es valida");
        }
        if (!idiomaValido(planTuristicoEntity.getIdioma())) {
            throw new BusinessLogicException("El idioma del plan no es valido");
        }
        if (!ubicacionEsValido(planTuristicoEntity.getUbicacion())) {
            throw new BusinessLogicException("La ubicacion del plan no es valido");
        }
        if (!costoPorPersonaEsValido(planTuristicoEntity.getCostoPorPersona())) {
            throw new BusinessLogicException("El costo del plan no es valido");
        }
        if (!guiaValido(planTuristicoEntity.getGuia())) {
            throw new BusinessLogicException("Guia no puede ser nulo");
        }
        PlanTuristicoEntity newEntity = persistence.update(planTuristicoEntity);
      
        return newEntity;
    }

    //metodos auxiliares para las verificaciones de los campos 
    private boolean nombrePlanEsValido(String pNombrePlan) {

        return !(pNombrePlan == null || pNombrePlan.isEmpty());
    }

    private boolean tipoPlanEsValido(String pTipoPlan) {

        return !(pTipoPlan == null || pTipoPlan.isEmpty());
    }

    private boolean costoPorPersonaEsValido(Double pCostoPorPersona) {

        return !(pCostoPorPersona < 0 || pCostoPorPersona == null);
    }

    private boolean descripcionEsValido(String pDescripcion) {

        return !(pDescripcion == null || pDescripcion.isEmpty());
    }

    private boolean ubicacionEsValido(String pUbicacion) {

        return !(pUbicacion == null || pUbicacion.isEmpty());
    }

    private boolean guiaValido(Boolean pGuia) {

        return !(pGuia == null);
    }

    private boolean duracionEsValido(String pDuracion) {

        return !(pDuracion == null || pDuracion.isEmpty());
    }

    private boolean idiomaValido(String pIdioma) {

        return !(pIdioma == null || pIdioma.isEmpty());
    }
}
