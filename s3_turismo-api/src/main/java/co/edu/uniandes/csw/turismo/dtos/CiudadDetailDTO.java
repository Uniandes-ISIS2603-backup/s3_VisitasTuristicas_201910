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
 * @author estudiante
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
    sitiosTuristicosDto=psitios;
}

/*
*Actualizar el nombre de un sitio turistico por un nombre dado
*@param pNombreBuscar
*@param pNombreNuevo
*/
public void actualizarSitioTuristicoPorNombre(String pNombreBuscar, String pNombreNuevo)
{
    for(int i=0;i<sitiosTuristicosDto.size();i++)
    {
        if(sitiosTuristicosDto.get(i).darNombre().equalsIgnoreCase(pNombreBuscar))
        {
            sitiosTuristicosDto.get(i).actualizarNombre(pNombreNuevo);
        }
    }
}

/*
*Actualizar el id de un sitio turistico por un id dado
*@param idBuscar
*@param idCambiar
*/
public void actualizarSitioTuristicoPorid(Long idBuscar, Long idCambiar)
{
    for(int i=0;i<sitiosTuristicosDto.size();i++)
    {
        
        if(sitiosTuristicosDto.get(i).darID().equals(idBuscar))
        {
            sitiosTuristicosDto.get(i).actualizarID(idCambiar);
        }
    }
}


/*
*Actualizar el nombre de un sitio turistico por un id dado
*@param idBuscar
*@param pnombre
*/
public void actualizarNombreStitioTuristicoPorid(Long idBuscar, String pnombre)
{
    for(int i=0;i<sitiosTuristicosDto.size();i++)
    {
        
        if(sitiosTuristicosDto.get(i).darID().equals(idBuscar))
        {
            sitiosTuristicosDto.get(i).actualizarNombre(pnombre);
        }
    }
}

/*
*Actualizar el id de un sitio turistico por un Nombre dado
*@param pnombre
*@param idCambiar
*/
public void actualizarIDSitioTuristicoPorNombre(String pnombre, Long idCambiar)
{
    for(int i=0;i<sitiosTuristicosDto.size();i++)
    {
        
        if(sitiosTuristicosDto.get(i).darNombre().equalsIgnoreCase(pnombre))
        {
            sitiosTuristicosDto.get(i).actualizarID(idCambiar);
        }
    }
}

/*
*Cambia el puntaje de un sitio turistico dado un nombre
*@param ppuntaje
*@param nombre
*/
public void actualizarPuntajePorNombre(String nombre, int ppuntaje)
{
    for(int i=0;i<sitiosTuristicosDto.size();i++)
    {
        if(sitiosTuristicosDto.get(i).darNombre().equalsIgnoreCase(nombre))
        {
            sitiosTuristicosDto.get(i).cambiarPuntaje(ppuntaje);
        }
    }
    
}

/*
*Cambia el puntaje de un sitio turistico dado un nombre
*@param ppuntaje
*@param nombre
*/
public void actualizarPuntajePorID(Long pid, int ppuntaje)
{
    for(int i=0;i<sitiosTuristicosDto.size();i++)
    {
        if(sitiosTuristicosDto.get(i).darID().equals(pid))
        {
            sitiosTuristicosDto.get(i).cambiarPuntaje(ppuntaje);
        }
    }
    
}

}