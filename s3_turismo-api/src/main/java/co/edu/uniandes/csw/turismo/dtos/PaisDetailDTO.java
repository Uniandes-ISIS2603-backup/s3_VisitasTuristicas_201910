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
    super();
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
    this.ciudadesDTO=pciudades;
}


    
    
}
