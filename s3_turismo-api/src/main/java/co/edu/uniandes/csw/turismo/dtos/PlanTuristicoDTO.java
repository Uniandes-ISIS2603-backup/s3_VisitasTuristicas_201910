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
    
    private String nombrePlan;
    private String tipoPlan;
    private String costoPorPersona;
    private String descripcion;
    private String ubicacion;
    private Boolean guia;
    private String duracion;
    private String idioma;
    private Long idPlanTuristico;
    private ViajeroDTO viajero;
    private ViajeDTO viaje;
    private SitiosTuristicosDTO sitioTuristico;
    
    //Constructor vacio
    public PlanTuristicoDTO(){
        
    }

    PlanTuristicoDTO(PlanTuristicoEntity planTuristico) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public String getCostoPorPersona() {
        return costoPorPersona;
    }

    /**
     * @param costoPorPersona the costoPorPersona to set
     */
    public void setCostoPorPersona(String costoPorPersona) {
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

    /**
     * @return the idPlanTuristico
     */
    public Long getIdPlanTuristico() {
        return idPlanTuristico;
    }

    /**
     * @param idPlanTuristico the idPlanTuristico to set
     */
    public void setIdPlanTuristico(Long idPlanTuristico) {
        this.idPlanTuristico = idPlanTuristico;
    }

    /**
     * @return the viajero
     */
    public ViajeroDTO getViajero() {
        return viajero;
    }

    /**
     * @param viajero the viajero to set
     */
    public void setViajero(ViajeroDTO viajero) {
        this.viajero = viajero;
    }

    /**
     * @return the viaje
     */
    public ViajeDTO getViaje() {
        return viaje;
    }

    /**
     * @param viaje the viaje to set
     */
    public void setViaje(ViajeDTO viaje) {
        this.viaje = viaje;
    }

    /**
     * @return the sitioTuristico
     */
    public SitiosTuristicosDTO getSitioTuristico() {
        return sitioTuristico;
    }

    /**
     * @param sitioTuristico the sitioTuristico to set
     */
    public void setSitioTuristico(SitiosTuristicosDTO sitioTuristico) {
        this.sitioTuristico = sitioTuristico;
    }

   
    
    
}
