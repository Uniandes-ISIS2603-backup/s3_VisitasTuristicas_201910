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

    /*
*Retornar las ciudades
*@return ciudadesDTO
     */
    public ArrayList<CiudadDTO> getCiudadesDTO() {
        return ciudadesDTO;
    }


    /*
*Actualizar la lista de ciudades
*@param pciudades
     */
    public void setCiudadesDTO(ArrayList<CiudadDTO> pciudades) {
        this.ciudadesDTO = pciudades;
    }

    public ArrayList<PlanTuristicoDTO> getPlanesTuristicos() {
        return planTuristico;
    }

    public void setPlanesTuristicos(ArrayList<PlanTuristicoDTO> plan) {
        this.planTuristico = plan;
    }

    @Override
    public PaisEntity toEntity() {
        PaisEntity aRet = new PaisEntity();

        aRet.setNombre(this.getNombre());
        aRet.setId(this.getID());
        if (ciudadesDTO != null) {

            List<CiudadEntity> ciudadEntity = new ArrayList<>();

            for (CiudadDTO dtoCiudad : getCiudadesDTO()) {

                ciudadEntity.add(dtoCiudad.toEntity());

            }

            aRet.setCiudades(ciudadEntity);

        }
        if (planTuristico != null) {

            List<PlanTuristicoEntity> planEntity = new ArrayList<>();

            for (PlanTuristicoDTO dtoCiudad : getPlanesTuristicos()) {

                planEntity.add(dtoCiudad.toEntity());

            }

            aRet.setPlanes(planEntity);

        }

        return aRet;
    }

}
