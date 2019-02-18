/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

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
        planTuristico = new PlanTuristicoDTO();
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
        planTuristico = pPlanTuristico;
    }

}
