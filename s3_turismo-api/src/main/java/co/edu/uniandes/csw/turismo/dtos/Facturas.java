/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.FacturaEntity;

/**
 *
 * @author estudiante
 */
public class Facturas {
    
    private String descripción;
    //En Entity está como Double; jsgs        
    //private Integer costo;
    private Double costo;
    
    private Integer id;

    /**
     * @return the descripción
     */
    public String getDescripción() {
        return descripción;
    }

    /**
     * @param descripción the descripción to set
     */
    public void setDescripción(String descripción) {
        this.descripción = descripción;
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
    public void setCosto(Double costo) {
        this.costo = costo;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    public FacturaEntity toEntity() {
        FacturaEntity newEntity = new FacturaEntity();
        newEntity.setCosto(this.costo);
        newEntity.setDescripcion(descripción);
        //newEntity.setViajero(viajero);
        return newEntity;
    }
    
    public Facturas(FacturaEntity ent) {
        costo = ent.getCosto();
        descripción = ent.getDescripcion();
    }
    
}
