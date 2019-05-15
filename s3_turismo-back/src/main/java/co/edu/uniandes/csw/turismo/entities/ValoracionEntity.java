/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */

@Entity
public class ValoracionEntity extends BaseEntity implements Serializable {


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
	 *Atributo que representa el id del usuario que realiza la valoración
     */
    private int idUsuario;
    /*
	 *Atributo que representa la valoración numérica de un plan turístico
     */
    private int valoracion;
    /*
	 *Atributo que representa el comentario correspondiente a un plan turístico
     */
    private String comentario;


    //Getters & Setters

    /*
	 *Retornar el id del usuario que realiza la valoración de un 
	 *blog de viajero
	 *@return idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /*
	 *Cambia el id del usuario que realiza la valoración de un
	 *blog de viajero
	 *@param idUsuario
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /*
	 *Retornar la valoración numérica de un plan turístico
	 *@return valoracion
     */
    public int getValoracion() {
        return valoracion;
    }

    /*
	 *Cambia la valoración numérica de un plan turístico
	 *@param valoracion
     */
    public void setValoracion(int valoracion) {
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

}
