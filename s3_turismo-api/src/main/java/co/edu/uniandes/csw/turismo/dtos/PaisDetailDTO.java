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
public class PaisDetailDTO extends PaisDTO implements Serializable
{
    /*
    *Atributo que representa el arreglo de ciudades
    */
private ArrayList<CiudadDTO> ciudadesDTO;

/*
*Metodo constructor
*/
public PaisDetailDTO()
{
    ciudadesDTO=new ArrayList<>();
         
}

/*
*Retornar las ciudades
*@return ciudadesDTO
*/
public ArrayList<CiudadDTO> darCiudades()
{
    return ciudadesDTO;
}


/*
*Actualizar la lista de ciudades
*@param pciudades
*/
public void actualizarCiudades(ArrayList<CiudadDTO> pciudades)
{
    ciudadesDTO=pciudades;
}

/*
*Actualizar el nombre de una ciudad por un nombre dado
*@param pNombreBuscar
*@param pNombreNuevo
*/
public void actualizarCiudadPorNombre(String pNombreBuscar, String pNombreNuevo)
{
    for(int i=0;i<ciudadesDTO.size();i++)
    {
        if(ciudadesDTO.get(i).darNombre().equalsIgnoreCase(pNombreBuscar))
        {
            ciudadesDTO.get(i).actualizarNombre(pNombreNuevo);
        }
    }
}

/*
*Actualizar el id de una ciudad por un id dado
*@param idBuscar
*@param idCambiar
*/
public void actualizarCiudadPorid(Long idBuscar, Long idCambiar)
{
    for(int i=0;i<ciudadesDTO.size();i++)
    {
        
        if(ciudadesDTO.get(i).darID().equals(idBuscar))
        {
            ciudadesDTO.get(i).actualizarID(idCambiar);
        }
    }
}


/*
*Actualizar el nombre de una ciudad por un id dado
*@param idBuscar
*@param pnombre
*/
public void actualizarNombreCiudadPorid(Long idBuscar, String pnombre)
{
    for(int i=0;i<ciudadesDTO.size();i++)
    {
        
        if(ciudadesDTO.get(i).darID().equals(idBuscar))
        {
            ciudadesDTO.get(i).actualizarNombre(pnombre);
        }
    }
}

/*
*Actualizar el id de una ciudad por un Nombre dado
*@param pnombre
*@param idCambiar
*/
public void actualizarIDCiudadPorNombre(String pnombre, Long idCambiar)
{
    for(int i=0;i<ciudadesDTO.size();i++)
    {
        
        if(ciudadesDTO.get(i).darNombre().equalsIgnoreCase(pnombre))
        {
            ciudadesDTO.get(i).actualizarID(idCambiar);
        }
    }
}
    
    
}
