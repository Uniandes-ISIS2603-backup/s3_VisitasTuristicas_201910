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
 * @author Juan Sebastian Gutierrez S.
 */
public class PreferenciaDTO implements Serializable {
    
    private Long idPreferencia;
    private String nombrePreferencia;

    /**
     * Constructor vacio
     */
    public PreferenciaDTO() {
        
    }
    
    /**
     * retorna el id de preferencia
     * @return idPreferencia
     */
    public Long getIdPreferencia() {
        return idPreferencia;
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
        return nuevo;
    }
    
    /**
     * Constructor que recibe una PreferenciaEntity y la transforma en PreferenciaDTO
     * @param ent 
     */
    public PreferenciaDTO(PreferenciaEntity ent) {
        
        this.idPreferencia = ent.getId();
        
        this.nombrePreferencia = ent.getNombrePreferencia();
    }
}