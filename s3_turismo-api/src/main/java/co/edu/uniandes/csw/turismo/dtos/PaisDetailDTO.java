/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.CiudadEntity;
import co.edu.uniandes.csw.turismo.entities.PaisEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
         
}
//Falta conectar con entidad
    public PaisDetailDTO(PaisEntity paisEntity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

/*
*Retornar las ciudades
*@return ciudadesDTO
*/
public ArrayList<CiudadDTO> getCiudades()
{
    return ciudadesDTO;
}


/*
*Actualizar la lista de ciudades
*@param pciudades
*/
public void setCiudades(ArrayList<CiudadDTO> pciudades)
{
    this.ciudadesDTO=pciudades;
}

@Override
 public PaisEntity toEntity()
    {
        PaisEntity aRet = new PaisEntity();
        

        aRet.cambiarNombre(this.darNombre());
        aRet.setId(this.darID());
         if (ciudadesDTO != null) {

            List<CiudadEntity> ciudadEntity = new ArrayList<>();

            for (CiudadDTO dtoCiudad : getCiudades()) {

                ciudadEntity.add(dtoCiudad.toEntity());

            }

            aRet.actualizarCiudades(ciudadEntity);

        }
        
        return aRet;
    }
    
    
}
