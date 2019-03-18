/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;
import co.edu.uniandes.csw.turismo.entities.CiudadEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 *
 * @author David Fonseca
 */
public class CiudadDTO implements Serializable {
     

   
    /*
    *Atributo que representa el id de una ciudad
    */
    private Long id;
    
    /*
    *Atributo que representa el nombre de una ciudad
    */
    private String nombre;
    
    private PaisDTO pais;
    
    /*
    *Constructor de la clase ciudad
    */
   public CiudadDTO()
   {
       
   }
    
    /*
    *Retornar el id de la ciudad
    *@return id
    */
    public Long getID()
    {
        return id;
    }
    
    public void setID(Long pid)
    {
        id=pid;
    }
    
    /*
    *Retornar el nombre de la ciudad
    *@return nombre
    */
    public String getNombre()
    {
        return nombre;
    }
    
    /*
    *Actualizar el nombre de una ciudad por otro
    *@param pNombre
    */
    public void setNombre(String pNombre)
    {
        this.nombre=pNombre;
    }  
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
    public CiudadDTO(CiudadEntity e)
    {
        this.id=e.getId();
        
        this.nombre = e.darNombre();
        
        this.pais = new PaisDTO(e.darPais());
    }
    
    public CiudadEntity toEntity()
    {
        CiudadEntity ret = new CiudadEntity();
        
        ret.setId(this.id);  
        ret.actualizarNombre(this.nombre);
        ret.actualizarPais(this.pais.toEntity());
        
        return ret;
    }

    /**
     * @return the pais
     */
    public PaisDTO getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(PaisDTO pais) {
        this.pais = pais;
    }
}
