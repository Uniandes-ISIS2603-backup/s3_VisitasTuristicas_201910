/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.CiudadEntity;
import co.edu.uniandes.csw.turismo.entities.PaisEntity;
import co.edu.uniandes.csw.turismo.entities.PlanTuristicoEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David Fonseca
 */
public class PaisDetailDTO extends PaisDTO implements Serializable {

    /*
    *Atributo que representa el arreglo de ciudades
     */
    private ArrayList<CiudadDTO> ciudadesDTO;
    private ArrayList<PlanTuristicoDTO> planTuristico;

    /*
*Metodo constructor
     */
    public PaisDetailDTO() {
        super();

    }

    public PaisDetailDTO(PaisEntity paisEntity) {
        super(paisEntity);
        if (paisEntity != null) {
            ArrayList<CiudadDTO> toAdd = new ArrayList<>();
            for (CiudadEntity a : paisEntity.getCiudades()) {
                toAdd.add(new CiudadDTO(a));
            }
            this.ciudadesDTO = toAdd;

            ArrayList<PlanTuristicoDTO> toAdd2 = new ArrayList<>();
            for (PlanTuristicoEntity b : paisEntity.getPlanesTuristicos()) {
                toAdd2.add(new PlanTuristicoDTO(b));
            }
            this.planTuristico = toAdd2;
        }
    }

    @Override
    public PaisEntity toEntity() {
        PaisEntity aRet = super.toEntity();
        if (getCiudadesDTO() != null) {

            List<CiudadEntity> ciudadEntity = new ArrayList<>();

            for (CiudadDTO dtoCiudad : getCiudadesDTO()) {

                ciudadEntity.add(dtoCiudad.toEntity());

            }

            aRet.setCiudades(ciudadEntity);

        }
        if (getPlanTuristico() != null) {

            List<PlanTuristicoEntity> planEntity = new ArrayList<>();

            for (PlanTuristicoDTO dtoCiudad : getPlanTuristico()) {

                planEntity.add(dtoCiudad.toEntity());

            }

            aRet.setPlanes(planEntity);

        }

        return aRet;
    }

    /**
     * @return the ciudadesDTO
     */
    public ArrayList<CiudadDTO> getCiudadesDTO() {
        return ciudadesDTO;
    }

    /**
     * @param ciudadesDTO the ciudadesDTO to set
     */
    public void setCiudadesDTO(ArrayList<CiudadDTO> ciudadesDTO) {
        this.ciudadesDTO = ciudadesDTO;
    }

    /**
     * @return the planTuristico
     */
    public ArrayList<PlanTuristicoDTO> getPlanTuristico() {
        return planTuristico;
    }

    /**
     * @param planTuristico the planTuristico to set
     */
    public void setPlanTuristico(ArrayList<PlanTuristicoDTO> planTuristico) {
        this.planTuristico = planTuristico;
    }

    
    
}
