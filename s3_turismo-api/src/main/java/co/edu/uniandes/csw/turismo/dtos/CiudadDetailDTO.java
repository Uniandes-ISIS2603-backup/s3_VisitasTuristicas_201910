/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;
import co.edu.uniandes.csw.turismo.entities.CiudadEntity;
import co.edu.uniandes.csw.turismo.entities.SitioTuristicoEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author David Fonseca
 */
public class CiudadDetailDTO extends CiudadDTO implements Serializable {
    
    
    /*
    *Atributo que representa la lista de sitios turisticos
    *
    */
    private ArrayList<SitioTuristicosDTO> sitiosTuristicosDto;
    
    /*
    *Metodo Constructor
    */
    public CiudadDetailDTO()
    {
        super();
    }
    
    public CiudadDetailDTO(CiudadEntity ciudadEntity)throws BusinessLogicException {
        super(ciudadEntity);
        if (ciudadEntity.darSitios() != null) {
            sitiosTuristicosDto = new ArrayList<>();
            for(SitioTuristicoEntity sitio: ciudadEntity.darSitios())
            {
                sitiosTuristicosDto.add(new SitioTuristicosDTO(sitio));
            }
        }
    }
    
    /**
     *
     * @return
     */
    @Override
    public CiudadEntity toEntity()
    {
        CiudadEntity aRet = super.toEntity();
        ArrayList<SitioTuristicoEntity> trans = new ArrayList<>();
        if (getSitiosTuristicosDto() != null) {
            for(SitioTuristicosDTO sitio : getSitiosTuristicosDto())
            {
                trans.add(sitio.toEntity());
            }
        }
        aRet.actualizarSitios(trans);
        return aRet;
    }

    /**
     * @return the sitiosTuristicosDto
     */
    public ArrayList<SitioTuristicosDTO> getSitiosTuristicosDto() {
        return sitiosTuristicosDto;
    }

    /**
     * @param sitiosTuristicosDto the sitiosTuristicosDto to set
     */
    public void setSitiosTuristicosDto(ArrayList<SitioTuristicosDTO> sitiosTuristicosDto) {
        this.sitiosTuristicosDto = sitiosTuristicosDto;
    }
    
    
}