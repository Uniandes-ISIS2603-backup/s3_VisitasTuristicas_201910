/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import java.io.Serializable;
import javax.persistence.Entity;

import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author lm.rodriguezc2
 */
@Entity
public class BlogDeViajeroEntity extends BaseEntity implements Serializable {


    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private PlanTuristicoEntity planTuristico;

    public PlanTuristicoEntity getPlanTuristico() {
        return planTuristico;
    }

    public void setPlanTuristico(PlanTuristicoEntity planTuristico) {
        this.planTuristico = planTuristico;
    }
    /*
     *Atributo que representa los comentarios realizados en el
     * blog de viajero correspondiente a un plan turístico
     */
    private  String comentarios;

    /*
	 *Atributo que representa las sugerencias realizadas en el
	 * blog de viajero correspondiente a un plan turístico
     */
    private  String sugerencias;

    /*
	 *Atributo que representa la cantidad de likes recibidos en el blog de viajero
	 * correspondiente a un plan turístico
     */
    private int likes;
    //Métodos

    /*
	 *Método constructor de un Blog de Viajero
     */
    public BlogDeViajeroEntity() {

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

}
