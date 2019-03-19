/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;


import co.edu.uniandes.csw.turismo.entities.ValoracionEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import java.io.Serializable;

public class ValoracionDetailDTO extends ValoracionDTO implements Serializable {

    /*
	    *Atributo que representa el Plan Turístico al que corresponde la valoración
	    *
     */
    private PlanTuristicoDTO planTuristico;

    /*
	     *Método constructor de una valoración de un plan turístico
     */
    public ValoracionDetailDTO() {
        super();
    }

    /*
	     *Retornar el Plan Turístico al que corresponde la valoración
	     *@return planTuristico
     */
    public PlanTuristicoDTO getPlanTuristico() {
        return planTuristico;
    }

    /*
	     *Cambia el Plan Turístico al que corresponde la valoración
	     *@param pPlanTuristico
     */
    public void setPlanTuristico(PlanTuristicoDTO pPlanTuristico) {
        this.planTuristico = pPlanTuristico;
    }
    
     /**
     * Crea un objeto ValoracionDetailDTO a partir de un objeto ValoracionEntity
     * incluyendo los atributos de ValoracionDTO
     * @param valoracionEntity Entidad ValoracionEntity desde la cual se va a crear el
     * nuevo objeto.
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     *
     */
    public ValoracionDetailDTO(ValoracionEntity valoracionEntity)throws BusinessLogicException {
        super(valoracionEntity);
        if (valoracionEntity.getPlanTuristico() != null) {
            this.planTuristico = new PlanTuristicoDTO(valoracionEntity.getPlanTuristico());
        }
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return El DTO de la editorial para transformar a Entity
     */
    @Override
    public ValoracionEntity toEntity(){
         ValoracionEntity valoracionEntity = super.toEntity();
       if (planTuristico != null) {
            valoracionEntity.setPlanTuristico(planTuristico.toEntity());
        }
        return valoracionEntity;
        
    }

}
