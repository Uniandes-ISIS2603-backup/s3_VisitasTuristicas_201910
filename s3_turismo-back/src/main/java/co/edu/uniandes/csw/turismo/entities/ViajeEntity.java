/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Christer Osorio
 */
@Entity
public class ViajeEntity extends BaseEntity implements Serializable {

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;

    @PodamExclude
    @OneToOne()
    private ViajeroEntity viajero;
    @PodamExclude
    @OneToOne()
    private PlanTuristicoEntity planTuristico;
    
    public ViajeEntity() {

    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the viajero
     */
    public ViajeroEntity getViajero() {
        return viajero;
    }

    /**
     * @param viajero the viajero to set
     */
    public void setViajero(ViajeroEntity viajero) {
        this.viajero = viajero;
    }

    /**
     * @return the planTuristico
     */
    public PlanTuristicoEntity getPlanTuristico() {
        return planTuristico;
    }

    /**
     * @param planTuristico the planTuristico to set
     */
    public void setPlanTuristico(PlanTuristicoEntity planTuristico) {
        this.planTuristico = planTuristico;
    }

}
