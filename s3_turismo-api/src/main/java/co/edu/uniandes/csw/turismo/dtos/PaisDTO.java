/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;
import co.edu.uniandes.csw.turismo.entities.PaisEntity;
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
   
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
    public PaisDTO(PaisEntity e)
    {
        this.id = e.getId();
        this.nombre = e.getNombre();
    }
    
    public PaisEntity toEntity()
    {
        PaisEntity aRet = new PaisEntity();
        
        aRet.setId(getId());
        aRet.setNombre(getNombre());
        
        
        return aRet;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    
}
