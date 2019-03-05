/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.BlogDeViajeroEntity;
import co.edu.uniandes.csw.turismo.entities.ValoracionEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import java.io.Serializable;

public class BlogDeViajeroDetailDTO extends BlogDeViajeroDTO implements Serializable {

    /*
	    *Atributo que representa el Plan Turístico al que corresponde el
	    *Blog de viajero 
	    *
     */
    private PlanTuristicoDTO planTuristico;

    /*
	     *Método constructor de un Blog de Viajero
     */
    public BlogDeViajeroDetailDTO() {
        super();
    }
    /**
     * Crea un objeto BlogDeViajeroDetailDTO a partir de un objeto BlogDeViajeroEntity
     * incluyendo los atributos de BlogDeViajeroDTO
     * @param blogEntity Entidad BlogDeViajeroEntity desde la cual se va a crear el
     * nuevo objeto.
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     *
     */
    public BlogDeViajeroDetailDTO(BlogDeViajeroEntity blogEntity)throws BusinessLogicException {
        super(blogEntity);
        if (blogEntity.getPlanTuristico() != null) {
            this.planTuristico = new PlanTuristicoDTO(blogEntity.getPlanTuristico());
        }
    }

    /*
	     *Retornar el Plan Turístico al que corresponde el
	    *Blog de viajero 
	     *@return planTuristico
     */
    public PlanTuristicoDTO getPlanTuristico() {
        return planTuristico;
    }

    /*
	     *Cambia el Plan Turístico al que corresponde el
	    *Blog de viajero 
	     *@param pPlanTuristico
     */
    public void setPlanTuristico(PlanTuristicoDTO pPlanTuristico) {
        planTuristico = pPlanTuristico;
    }
    
     /**
     * Transformar un DTO a un Entity
     *
     * @return El DTO de la editorial para transformar a Entity
     */
    @Override
    public BlogDeViajeroEntity toEntity(){
         BlogDeViajeroEntity blogDeViajeroEntity = super.toEntity();
       if (planTuristico != null) {
            blogDeViajeroEntity.setPlanTuristico(planTuristico.toEntity());
        }
        return blogDeViajeroEntity;
        
    }
    
    
}
