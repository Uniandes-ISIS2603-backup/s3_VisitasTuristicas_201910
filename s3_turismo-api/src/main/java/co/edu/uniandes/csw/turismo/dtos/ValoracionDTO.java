/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.ValoracionEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import java.io.Serializable;

public class ValoracionDTO implements Serializable {

       /*
    * Relación a un plan
    * dado que esta tiene cardinalidad 1.
     */
    private PlanTuristicoDTO plan;
    /*
	 *Atributo que representa el id del usuario que realiza la valoración
     */
    private Integer idUsuario;
    /*
	 *Atributo que representa la valoración numérica de un plan turístico
     */
    private Integer valoracion;
    /*
	 *Atributo que representa el comentario correspondiente a un plan turístico
     */
    private String comentario;

    //Métodos

    /*
	 *Método constructor de una valoración de un plan turístico
     */
    public ValoracionDTO() {
        
    }

    //Getters & Setters

    /*
	 *Retornar el id del usuario que realiza la valoración de un 
	 *blog de viajero
	 *@return idUsuario
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }

        public PlanTuristicoDTO getPlan() {
        return plan;
    }

    public void setPlan(PlanTuristicoDTO plan) {
        this.plan = plan;
    }
    /*
	 *Cambia el id del usuario que realiza la valoración de un
	 *blog de viajero
	 *@param idUsuario
     */
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    /*
	 *Retornar la valoración numérica de un plan turístico
	 *@return valoracion
     */
    public Integer getValoracion() {
        return valoracion;
    }

    /*
	 *Cambia la valoración numérica de un plan turístico
	 *@param valoracion
     */
    public void setValoracion(Integer valoracion) {
        this.valoracion = valoracion;
    }

    /*
	 *Retornar el comentario correspondiente a un plan turístico
	 *@return comentario
     */
    public String getComentario() {
        return comentario;
    }

    /*
	 *Cambia el comentario correspondiente a un plan turístico
	 *@param comentario
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    public ValoracionEntity toEntity()
    {
        ValoracionEntity respuesta =  new ValoracionEntity();
        respuesta.setComentario(this.comentario);
        respuesta.setIdUsuario(this.idUsuario);
        respuesta.setValoracion(this.valoracion);
        respuesta.setId(Long.MIN_VALUE);        
        if (this.plan != null) {
            respuesta.setPlanTuristico(this.plan.toEntity());
        }
        return respuesta;
    }
    public ValoracionDTO(ValoracionEntity entity)throws BusinessLogicException {
       if (entity != null) {
            this.comentario = entity.getComentario();
            this.idUsuario = entity.getIdUsuario();
            this.valoracion = entity.getValoracion();
            
             if (entity.getPlanTuristico() != null) {
                this.plan = new PlanTuristicoDTO(entity.getPlanTuristico());
            } else {
                this.plan = null;
            
        }
        }
    }
    
  
}
