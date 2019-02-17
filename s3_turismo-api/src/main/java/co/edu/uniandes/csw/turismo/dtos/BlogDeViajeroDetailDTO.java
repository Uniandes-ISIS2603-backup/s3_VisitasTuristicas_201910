/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

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
        planTuristico = new PlanTuristicoDTO();
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

}
