/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;
import co.edu.uniandes.csw.turismo.entities.CiudadEntity;
import java.io.Serializable;

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
    
    private String descripcion;
    
        private String imagen;

    

    
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
            this.imagen=e.getImagen();
            this.descripcion=e.getDescripcion();
        
        }
    }
    
    public CiudadEntity toEntity()
    {
        CiudadEntity ret = new CiudadEntity();
        
        ret.setId(this.getId());  
        ret.actualizarNombre(this.getNombre());
        ret.setImagen(this.getImagen());
        ret.setDescripcion(this.getDescripcion());
        
        return ret;
    }

        public String getDescripcion()
    {
        return descripcion;
    }
    public void setDescripcion(String p)
    {
        this.descripcion=p;
    }
    
    public String getImagen()
    {
        return imagen;
    }
    
    public void setImagen(String im)
    {
        this.imagen=im;
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


    
}
