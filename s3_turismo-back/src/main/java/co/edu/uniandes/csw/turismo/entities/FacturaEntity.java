/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import java.io.Serializable;
import javax.persistence.Entity;

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
    private double costo;
    
    /**
     * crea una nueva factura
     */
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
    public double getCosto() {
        return costo;
    }

    /**
     * @param costo the costo to set
     */
    public void setCosto(double costo) {
        this.costo = costo;
    }
    
}
