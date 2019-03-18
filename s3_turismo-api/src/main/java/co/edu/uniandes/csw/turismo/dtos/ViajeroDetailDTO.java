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
    private List<FacturasDTO> facturas;
    private List<PlanTuristicoDTO> planesTuristicos;

    /**
     * se retorna la lista de facturas que tiene el viajero
     * @return 
     */
    public List<FacturasDTO> getFacturas() {
        return facturas;
    }

    /**
     * se asigna la lista de facturas
     * @param facturas 
     */
    public void setFacturas(List<FacturasDTO> facturas) {
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
     * constructor vacio; llama a ViajeroDTO
     */
    public ViajeroDetailDTO() {
        super();
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
        preferencias = nuevo;
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
            List<FacturasDTO> toAdd2 = new ArrayList<>();
            for(FacturaEntity b : ent.getFacturas()) {
                toAdd2.add(new FacturasDTO(b));
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
    public ViajeroEntity toEntity() {
        ViajeroEntity toReturn = new ViajeroEntity();
        toReturn.setCantidadPlanes(this.getCantidadPlanes());
        toReturn.setIdioma(this.getIdioma());
        toReturn.setInformacionPersonal(this.getInformacionPersonal());
        toReturn.setNombreUsuario(this.getNombreUsuario());
        toReturn.setTarjetaDeCredito(this.getTarjetaDeCredito().toEntity());
        toReturn.setViaje(this.getViaje().toEntity());
        List<FacturaEntity> toAdd1 = new ArrayList<>();
        for(FacturasDTO f : facturas) {
            toAdd1.add(f.toEntity());
        }
        toReturn.setFacturas(toAdd1);
        List<PlanTuristicoEntity> toAdd2 = new ArrayList<>();
        for(PlanTuristicoDTO p : planesTuristicos) {
            toAdd2.add(p.toEntity());
        }
        toReturn.setPlanesTuristicos(toAdd2);
        List<PreferenciaEntity> toAdd3 = new ArrayList<>();
        for(PreferenciaDTO pr : preferencias) {
            toAdd3.add(pr.toEntity());
        }
        toReturn.setPreferencias(toAdd3);
        
        return toReturn;
    }
}
