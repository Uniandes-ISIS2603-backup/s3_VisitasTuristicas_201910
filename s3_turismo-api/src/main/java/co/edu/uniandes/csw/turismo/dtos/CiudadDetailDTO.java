/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;
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
    private ArrayList<SitiosTuristicosDTO> sitiosTuristicosDto;
    
    /*
    *Metodo Constructor
    */
    public CiudadDetailDTO()
    {
        super();
        sitiosTuristicosDto=new ArrayList<>();
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