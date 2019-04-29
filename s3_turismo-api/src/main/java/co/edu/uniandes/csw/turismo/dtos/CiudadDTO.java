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
    
    public CiudadDTO(CiudadEntity e)
    {
        if(e!=null)
        {    
            this.id=e.getId();
        
            this.nombre = e.darNombre();
        
            this.pais = new PaisDTO(e.darPais());
        }
    }
    
    public CiudadEntity toEntity()
    {
        CiudadEntity ret = new CiudadEntity();
        
        ret.setId(this.getId());  
        ret.actualizarNombre(this.getNombre());
        ret.actualizarPais(this.getPais().toEntity());
        
        return ret;
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
