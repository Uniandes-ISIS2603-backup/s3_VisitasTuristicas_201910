/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.PreferenciaEntity;
import java.io.Serializable;
/**
 *
 * @author Juan Sebastian Gutierrez S. modificado por David Fonseca agregado set de id.
 */
public class PreferenciaDTO implements Serializable {
    
    private Long idPreferencia;
    private String nombrePreferencia;
    private ViajeroDTO viajero;

    /**
     * Constructor vacio
     */
    public PreferenciaDTO() {
        
    }
    
    public ViajeroDTO getViajero()
    {
        return viajero;
    }
    public void setViajero(ViajeroDTO v)
    {
       this.viajero=v; 
    }
    /**
     * retorna el id de preferencia
     * @return idPreferencia
     */
    public Long getIdPreferencia() {
        return idPreferencia;
    }
    
    /*
    *Actualizar id
    *@param pid
    */
    public void setIdPreferencia(Long pid)
    {
        this.idPreferencia=pid;
    }

    /**
     * retorna el nombre de la preferencia
     * @return nombrePreferencia
     */
    public String getNombrePreferencia() {
        return nombrePreferencia;
    }

    /**
     * asigna el nombre a una preferencia
     * @param nombrePreferencia 
     */
    public void setNombrePreferencia(String nombrePreferencia) {
        this.nombrePreferencia = nombrePreferencia;
    }
    
    /**
     * metodo que transforma la clase actual en una PreferenciaEntity
     * @return PreferenciaEntity
     */
    public PreferenciaEntity toEntity() {
        
        PreferenciaEntity nuevo = new PreferenciaEntity();
        
        nuevo.setId(this.getIdPreferencia());
        nuevo.setNombrePreferencia(nombrePreferencia);
            if(this.viajero!= null){            
            nuevo.setViajero(this.viajero.toEntity());
        }        return nuevo;
    }
    
    /**
     * Constructor que recibe una PreferenciaEntity y la transforma en PreferenciaDTO
     * @param ent 
     */
    public PreferenciaDTO(PreferenciaEntity ent) {
        if(ent!=null){
            this.idPreferencia = ent.getId();
        
            this.nombrePreferencia = ent.getNombrePreferencia();
            if (ent.getViajero()!= null) {
                this.viajero = new ViajeroDTO(ent.getViajero());
            } else {
                this.viajero = null;
            }
        }
        
    }
}