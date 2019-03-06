/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.adapters.DateAdapter;
import co.edu.uniandes.csw.turismo.entities.ViajeEntity;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Christer Osorio
 */
public class ViajeDTO {

    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaInicio;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaFin;
    private Long idViaje;
    private ViajeroDTO viajero;
    private PlanTuristicoDTO planTuristico;

    //constructor vacio
    public ViajeDTO() {

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

    public ViajeDTO(ViajeEntity viajeEntity) {
        if (viajeEntity != null) {
            this.fechaFin = viajeEntity.getFechaFin();
            this.fechaInicio = viajeEntity.getFechaInicio();
            this.planTuristico= new PlanTuristicoDTO(viajeEntity.getPlanTuristico());
            this.viajero= new ViajeroDTO(viajeEntity.getViajero());
       }
    }
    
    
    public ViajeEntity toEntity(){
        ViajeEntity viajeEntity= new ViajeEntity();
        viajeEntity.setFechaFin(this.fechaFin);
        viajeEntity.setFechaInicio(this.fechaInicio);
        viajeEntity.setPlanTuristico(this.planTuristico.toEntity());
        viajeEntity.setViajero(this.viajero.toEntity());
        
        return viajeEntity;
    }

}
