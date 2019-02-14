/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author David FOnseca
 */
@Entity
public class PaisEntity extends BaseEntity implements Serializable {
     /*
    *Atributo que representa el nombre de un pais
    */
   private String nombre;
 
   /*
   *Atributo que representa la cantidad de ciudades en un pais
   */
   private int cantidadCiudades;

   @PodamExclude
   @OneToMany
   private List<CiudadEntity> ciudades;
   
   /*
   *Retorna las ciudades
   *@return ciudades
   */
public List<CiudadEntity> darCiudades()
{
    return ciudades;
}

/*
*Actualiza la lista de ciudades
*@param pciudad
*/
public void actualizarCiudades(List<CiudadEntity> pciudad)
{
    this.ciudades=pciudad;
}
   /*
   *Retornar el nombre del pais
   *@return nombre
   */
   public String darNombre()
   {
       return nombre;
   }

   /*
   *Cambia el nombre del pais
   *@param pNombre
   */
   public void cambiarNombre(String pNombre)
   {
       this.nombre=pNombre;
   }
   
   
   /*
   *Retornar la cantidad de ciudades del pais
   *@return cantidadCiudades
   */
   public int darCantidadCiudades()
   {
       return cantidadCiudades;
   }
   
   /*
   *Cambia la cantidad de ciudades
   *@param pcantidadciudades
   */
   public void cambiarCantidadCiudades(int pcantidadciudades)
   {
       cantidadCiudades=pcantidadciudades;
   }
    
}
