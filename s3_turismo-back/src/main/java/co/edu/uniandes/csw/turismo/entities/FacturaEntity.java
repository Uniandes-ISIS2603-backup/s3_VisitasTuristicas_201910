/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jd.castrellon
 */
@Entity
public class FacturaEntity extends BaseEntity implements Serializable{
    
    /**
     * representa la descripcion de la factura
     */
    private String descripcion;
    
    /**
     * representa el valor total de la factura
     */
    private Double costo;
    
    /**
     * crea una nueva factura
     */
    
    
    /**
     * Crea la relacion con viajero
     */
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private ViajeroEntity viajero;
    

    
    public FacturaEntity()
    {
        
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
     * @return the costo
     */
    public Double getCosto() {
        return costo;
    }

    /**
     * @param costo the costo to set
     */
    public void setCosto(double costo) {
        this.costo = costo;
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
    
}
