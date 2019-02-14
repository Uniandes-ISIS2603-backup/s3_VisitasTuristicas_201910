/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 *
 * @author David Fonseca
 */
public class PaisDTO implements Serializable
{
    /*
    *Atributo que representa el nombre de un pais
    */
   private String nombre;
  
   /*
   *Atributo que representa el id de un pais
   */
   private Long id;
 
  
   
  public PaisDTO()
  {
  
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
   *Retornar el di del pais
   *@return id
   */
   public Long darID()
   {
       return id;
   }
   
   /*
   *Cambia el id del pais
   *@param pid
   */
   public void cambiarID(Long pid)
   {
       this.id=pid;
   }
   
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
