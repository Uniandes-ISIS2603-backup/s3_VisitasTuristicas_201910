/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;import javax.persistence.OneToMany;
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
 

   @PodamExclude
   @OneToMany( mappedBy ="pais", cascade = CascadeType.PERSIST,orphanRemoval = true)
   private List<CiudadEntity> ciudades;
   
   
   @PodamExclude
   @ManyToMany 
   private List<PlanTuristicoEntity> planes;
   
   
   public void setPlanes(List<PlanTuristicoEntity> pplan)
   {
       this.planes=pplan;
   }
   public List<PlanTuristicoEntity> getPlanesTuristicos()
   {
     return planes;  
   }
   /*
   *Retorna las ciudades
   *@return ciudades
   */
public List<CiudadEntity> getCiudades()
{
    return ciudades;
}

/*
*Actualiza la lista de ciudades
*@param pciudad
*/
public void setCiudades(List<CiudadEntity> pciudad)
{
    this.ciudades=pciudad;
}
   /*
   *Retornar el nombre del pais
   *@return nombre
   */
   public String getNombre()
   {
       return nombre;
   }

   /*
   *Cambia el nombre del pais
   *@param pNombre
   */
   public void setNombre(String pNombre)
   {
       this.nombre=pNombre;
   }
   
    
}
