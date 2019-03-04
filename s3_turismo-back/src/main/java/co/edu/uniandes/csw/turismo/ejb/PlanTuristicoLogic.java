/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.PlanTuristicoEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.PlanTuristicoPersistence;
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
    
    public PlanTuristicoEntity createPlanTuristico(PlanTuristicoEntity planTuristico) throws BusinessLogicException{
        if(persistence.findByName(planTuristico.getNombrePlan())!=null){
            throw new BusinessLogicException("Ya existe un plan turistico con ese nombre");
        }
        planTuristico= persistence.create(planTuristico);
        return planTuristico;
    }
}
