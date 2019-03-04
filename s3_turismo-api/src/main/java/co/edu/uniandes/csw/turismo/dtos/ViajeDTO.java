/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

/**
 *
 * @author Christer Osorio
 */
public class ViajeDTO {

    private String fechaInicio;
    private String fechaFin;
    private Long idViaje;
    private ViajeroDTO viajero;
    private PlanTuristicoDTO planTuristico;

    //constructor vacio
    public ViajeDTO() {

    }

    /**
     * @return the fechaInicio
     */
    public String getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public String getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the idViaje
     */
    public Long getIdViaje() {
        return idViaje;
    }

    /**
     * @param idViaje the idViaje to set
     */
    public void setIdViaje(Long idViaje) {
        this.idViaje = idViaje;
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
     * @return the planTuristico
     */
    public PlanTuristicoDTO getPlanTuristico() {
        return planTuristico;
    }

    /**
     * @param planTuristico the planTuristico to set
     */
    public void setPlanTuristico(PlanTuristicoDTO planTuristico) {
        this.planTuristico = planTuristico;
    }

}
