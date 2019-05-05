/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.PlanTuristicoEntity;
import java.io.Serializable;

/**
 *
 * @author Christer Osorio
 */
public class PlanTuristicoDTO implements Serializable {
    
    private Long planTuristicoId;
    private String nombrePlan;
    private String tipoPlan;
    private Double costoPorPersona;
    private String descripcion;
    private String ubicacion;
    private Boolean guia;
    private String duracion;
    private String idioma;
 

    //Constructor vacio
    public PlanTuristicoDTO() {
        
    }

    

    /**
     * @return the planTuristicoId
     */
    public Long getPlanTuristicoId() {
        return planTuristicoId;
    }

    /**
     * @param planTuristicoId the planTuristicoId to set
     */
    public void setPlanTuristicoId(Long planTuristicoId) {
        this.planTuristicoId = planTuristicoId;
    }

    /**
     * @return the nombrePlan
     */
    public String getNombrePlan() {
        return nombrePlan;
    }

    /**
     * @param nombrePlan the nombrePlan to set
     */
    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    /**
     * @return the tipoPlan
     */
    public String getTipoPlan() {
        return tipoPlan;
    }

    /**
     * @param tipoPlan the tipoPlan to set
     */
    public void setTipoPlan(String tipoPlan) {
        this.tipoPlan = tipoPlan;
    }

    /**
     * @return the costoPorPersona
     */
    public Double getCostoPorPersona() {
        return costoPorPersona;
    }

    /**
     * @param costoPorPersona the costoPorPersona to set
     */
    public void setCostoPorPersona(Double costoPorPersona) {
        this.costoPorPersona = costoPorPersona;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the ubicacion
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * @param ubicacion the ubicacion to set
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * @return the guia
     */
    public Boolean getGuia() {
        return guia;
    }

    /**
     * @param guia the guia to set
     */
    public void setGuia(Boolean guia) {
        this.guia = guia;
    }

    /**
     * @return the duracion
     */
    public String getDuracion() {
        return duracion;
    }

    /**
     * @param duracion the duracion to set
     */
    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    /**
     * @return the idioma
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * @param idioma the idioma to set
     */
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    

    

    //
    public PlanTuristicoEntity toEntity() {
        PlanTuristicoEntity entity = new PlanTuristicoEntity();
        
        entity.setId(planTuristicoId);
        entity.setNombrePlan(nombrePlan);
        entity.setTipoPlan(tipoPlan);
        entity.setCostoPorPersona(costoPorPersona);
        entity.setDescripcion(descripcion);
        entity.setUbicacion(ubicacion);
        entity.setGuia(guia);
        entity.setDuracion(duracion);
        entity.setIdioma(idioma);
       
        
        return entity;
    }
    
    public PlanTuristicoDTO(PlanTuristicoEntity pEntity) {
        if(pEntity != null){
            this.planTuristicoId = pEntity.getId();
            this.nombrePlan = pEntity.getNombrePlan();
            this.tipoPlan = pEntity.getTipoPlan();
            this.costoPorPersona = pEntity.getCostoPorPersona();
            this.descripcion = pEntity.getDescripcion();
            this.ubicacion = pEntity.getUbicacion();
            this.guia = pEntity.getGuia();
            this.duracion = pEntity.getDuracion();
            this.idioma = pEntity.getIdioma();
        }
    }

    
}
