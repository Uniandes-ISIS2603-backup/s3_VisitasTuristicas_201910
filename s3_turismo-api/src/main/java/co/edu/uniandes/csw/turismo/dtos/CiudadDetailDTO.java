/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;
import co.edu.uniandes.csw.turismo.entities.BlogDeViajeroEntity;
import co.edu.uniandes.csw.turismo.entities.CiudadEntity;
import co.edu.uniandes.csw.turismo.entities.SitioTuristicoEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 *
 * @author David Fonseca
 */
public class CiudadDetailDTO extends CiudadDTO implements Serializable {
    
    
    /*
    *Atributo que representa la lista de sitios turisticos
    *
    */
    private ArrayList<SitiosTuristicosDTO> sitiosTuristicosDto;
    
    /*
    *Metodo Constructor
    */
    public CiudadDetailDTO()
    {
        super();
        sitiosTuristicosDto=new ArrayList<>();
    }
    
    public CiudadDetailDTO(CiudadEntity ciudadEntity)throws BusinessLogicException {
        super(ciudadEntity);
        if (ciudadEntity.darSitios() != null) {
            sitiosTuristicosDto = new ArrayList<>();
            for(SitioTuristicoEntity sitio: ciudadEntity.darSitios())
            {
                sitiosTuristicosDto.add(new SitiosTuristicosDTO(sitio));
            }
        }
    }
    
    public CiudadEntity toEntity()
    {
        CiudadEntity aRet = super.toEntity();
        ArrayList<SitioTuristicoEntity> trans = new ArrayList<>();
        if (sitiosTuristicosDto != null) {
            for(SitiosTuristicosDTO sitio : sitiosTuristicosDto)
            {
                trans.add(sitio.toEntity());
            }
        }
        aRet.actualizarSitios(trans);
        return aRet;
    }
    /*
*Retornar los sitios tuiristicos
*@return sitiosTuristicosDTO
*/
public ArrayList<SitiosTuristicosDTO> darSitiosTuristicos()
{
    return sitiosTuristicosDto;
}


/*
*Actualizar la lista de sitios turisticos
*@param psitios
*/
public void actualizarSitiosTuristicos(ArrayList<SitiosTuristicosDTO> psitios)
{
    this.sitiosTuristicosDto=psitios;
}



}