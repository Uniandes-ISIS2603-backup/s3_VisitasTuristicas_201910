/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.BlogDeViajeroEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import java.io.Serializable;
import java.util.ArrayList;

public class BlogDeViajeroDTO implements Serializable {

     /*
    * Relación a un plan
    * dado que esta tiene cardinalidad 1.
     */
    private PlanTuristicoDTO plan;

    public PlanTuristicoDTO getPlan() {
        return plan;
    }

    public void setPlan(PlanTuristicoDTO plan) {
        this.plan = plan;
    }
    
    /*
	 *Atributo que representa los comentarios realizados en el
	 * blog de viajero correspondiente a un plan turístico
     */
    private String comentarios;

    /*
	 *Atributo que representa las sugerencias realizadas en el
	 * blog de viajero correspondiente a un plan turístico
     */
    private String sugerencias;

    /*
	 *Atributo que representa la cantidad de likes recibidos en el blog de viajero
	 * correspondiente a un plan turístico
     */
    private int likes;

    //Métodos

    /*
	 *Método constructor de un Blog de Viajero
     */
    public BlogDeViajeroDTO() {

    }

    //Getters & Setters

    /*
	 *Retornar la lista de comentarios de un blog de viajero
	 *@return comentarios
     */
    public String getComentarios() {
        return comentarios;
    }

    /*
	 *Cambia la lista de comentarios de un blog de viajero
	 *@param comentarios
     */
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    /*
	 *Retornar la lista de sugerencias de un blog de viajero
	 *@return sugerencias
     */
    public String getSugerencias() {
        return sugerencias;
    }

    /*
	 *Cambia la lista de sugerencias de un blog de viajero
	 *@param sugerencias
     */
    public void setSugerencias(String sugerencias) {
        this.sugerencias = sugerencias;
    }

    /*
	 *Retornar el número de likes de un blog de viajero
	 *@return likes
     */
    public int getLikes() {
        return likes;
    }

    /*
	 *Cambia el número de likes de un blog de viajero
	 *@param likes
     */
    public void setLikes(int likes) {
        this.likes = likes;
    }

    public BlogDeViajeroEntity toEntity()
    {
        BlogDeViajeroEntity respuesta =  new BlogDeViajeroEntity();
        respuesta.setComentarios(this.comentarios);
        respuesta.setLikes(this.likes);
        respuesta.setSugerencias(this.sugerencias);
        //respuesta.setId(Long.MIN_VALUE);
        
        if (this.plan != null) {
            respuesta.setPlanTuristico(this.plan.toEntity());
        }
        return respuesta;
    }
    public BlogDeViajeroDTO(BlogDeViajeroEntity entity)throws BusinessLogicException {
       if (entity != null) {
            this.comentarios = entity.getComentarios();
            this.likes = entity.getLikes();
            this.sugerencias = entity.getSugerencias();
       
            if (entity.getPlanTuristico() != null) {
                this.plan = new PlanTuristicoDTO(entity.getPlanTuristico());
            } else {
                this.plan = null;
            
        }
    }
}
}
