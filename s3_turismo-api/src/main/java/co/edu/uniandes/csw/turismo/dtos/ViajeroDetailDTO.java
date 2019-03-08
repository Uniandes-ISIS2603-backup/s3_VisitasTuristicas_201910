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
 * @author estudiante
 */
public class ViajeroDetailDTO extends ViajeroDTO implements Serializable {
    private List<PreferenciaDTO> preferencias;
    private List<Facturas> facturas;
    private List<PlanTuristicoDTO> planesTuristicos;

    public List<Facturas> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Facturas> facturas) {
        this.facturas = facturas;
    }

    public List<PlanTuristicoDTO> getPlanesTuristicos() {
        return planesTuristicos;
    }

    public void setPlanesTuristicos(List<PlanTuristicoDTO> planesTuristicos) {
        this.planesTuristicos = planesTuristicos;
    }
    
    public ViajeroDetailDTO() {
        super();
    }
    
    public List<PreferenciaDTO> getPreferencias() {
        return preferencias;
    }
    
    public void setPreferencias(List<PreferenciaDTO> nuevo) {
        preferencias = nuevo;
    }
    
    public ViajeroDetailDTO(ViajeroEntity ent) {
        super(ent);
        if(ent != null) {
            List<PreferenciaDTO> toAdd = new ArrayList<>();
            for(PreferenciaEntity a : ent.getPreferencias()) {
                toAdd.add(new PreferenciaDTO(a));
            }
            this.preferencias = toAdd;
            List<Facturas> toAdd2 = new ArrayList<>();
            for(FacturaEntity b : ent.getFacturas()) {
                toAdd2.add(new Facturas(b));
            }
            this.facturas = toAdd2;
            List<PlanTuristicoDTO> toAdd3 = new ArrayList<>();
            for(PlanTuristicoEntity c : ent.getPlanesTuristicos()) {
                toAdd3.add(new PlanTuristicoDTO(c));
            }
            this.planesTuristicos = toAdd3;
        }
    }
    
    public ViajeroEntity toEntity() {
        return new ViajeroEntity();
    }
}
