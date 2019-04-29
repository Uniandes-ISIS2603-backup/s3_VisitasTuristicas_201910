/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.FacturaEntity;
import co.edu.uniandes.csw.turismo.entities.PlanTuristicoEntity;
import co.edu.uniandes.csw.turismo.entities.PreferenciaEntity;
import co.edu.uniandes.csw.turismo.entities.ViajeroEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Juan Sebastian Gutierrez S.
 */
public class ViajeroDetailDTO extends ViajeroDTO implements Serializable {
    private List<PreferenciaDTO> preferencias;
    private List<FacturaDTO> facturas;
    private List<PlanTuristicoDTO> planesTuristicos;

    
        /**
     * constructor vacio; llama a ViajeroDTO
     */
    public ViajeroDetailDTO() {
        super();
    }
    
    /**
     * se retorna la lista de facturas que tiene el viajero
     * @return 
     */
    public List<FacturaDTO> getFacturas() {
        return facturas;
    }

    /**
     * se asigna la lista de facturas
     * @param facturas 
     */
    public void setFacturas(List<FacturaDTO> facturas) {
        this.facturas = facturas;
    }

    /**
     * se retorna la lista de planes turisticos
     * @return planesTuristicos
     */
    public List<PlanTuristicoDTO> getPlanesTuristicos() {
        return planesTuristicos;
    }

    /**
     * se asigna la lista de planes turisticos
     * @param planesTuristicos 
     */
    public void setPlanesTuristicos(List<PlanTuristicoDTO> planesTuristicos) {
        this.planesTuristicos = planesTuristicos;
    }
    

    /**
     * se retorna la lista de preferencias
     * @return preferencias
     */
    public List<PreferenciaDTO> getPreferencias() {
        return preferencias;
    }
    
    /**
     * se asigna la lista de preferencias
     * @param nuevo 
     */
    public void setPreferencias(List<PreferenciaDTO> nuevo) {
        this.preferencias = nuevo;
    }
    
    /**
     * constructor que recibe una Entity por parametro
     * Llama al padre, transforma y asigna todas las listas necesarias
     * @param ent 
     */
    public ViajeroDetailDTO(ViajeroEntity ent) {
        super(ent);
        if(ent != null) {
            List<PreferenciaDTO> toAdd = new ArrayList<>();
            for(PreferenciaEntity a : ent.getPreferencias()) {
                toAdd.add(new PreferenciaDTO(a));
            }
            this.preferencias = toAdd;
            List<FacturaDTO> toAdd2 = new ArrayList<>();
            for(FacturaEntity b : ent.getFacturas()) {
                toAdd2.add(new FacturaDTO(b));
            }
            this.facturas = toAdd2;
            List<PlanTuristicoDTO> toAdd3 = new ArrayList<>();
            for(PlanTuristicoEntity c : ent.getPlanesTuristicos()) {
                toAdd3.add(new PlanTuristicoDTO(c));
            }
            this.planesTuristicos = toAdd3;
        }
    }
    
    /**
     * Transforma todos los atributos a un  nuevo ViajeroEntity
     * @return ViajeroEntity
     */
    @Override
    public ViajeroEntity toEntity() {
        ViajeroEntity toReturn = super.toEntity();
        if(preferencias != null){
            List<PreferenciaEntity> preferenciaEntity = new ArrayList<>();
            for(PreferenciaDTO preferencia: getPreferencias()){
                preferenciaEntity.add(preferencia.toEntity());
            } 
            toReturn.setPreferencias(preferenciaEntity);
        }
        if(facturas != null){
            List<FacturaEntity> facturaEntity = new ArrayList<>();
            for(FacturaDTO factura: getFacturas()){
                facturaEntity.add(factura.toEntity());
            } 
            toReturn.setFacturas(facturaEntity);
        }
        if(planesTuristicos != null){
            List<PlanTuristicoEntity> planEntity = new ArrayList<>();
            for(PlanTuristicoDTO plan: getPlanesTuristicos()){
                planEntity.add(plan.toEntity());
            } 
            toReturn.setPlanesTuristicos(planEntity);
        }
        return toReturn;
    }
}
