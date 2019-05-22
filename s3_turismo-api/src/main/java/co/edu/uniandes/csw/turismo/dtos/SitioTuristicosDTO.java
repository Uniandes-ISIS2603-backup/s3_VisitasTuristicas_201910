/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;
import co.edu.uniandes.csw.turismo.entities.SitioTuristicoEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 *
 * @author David Fonseca
 */
public class SitioTuristicosDTO implements Serializable 
{
    
    
    private Long id;
    /**
     * Tipo de sitio turistico.
     */
    private String tipo;
    /*
    *Atributo que representa el id de un sitio turistico
    */
    private String imagen;

    /*
    *Atributo que representa el nombre de un sitio turistico
    */
    private String nombre;
    
    private CiudadDTO ciudad;
    

    
    public String getImagen()
    {
        return imagen;
    }
    
    public void setImagen(String p)
    {
        this.imagen=p;
    }
    /*
    *Constructor de la clase
    */
    public SitioTuristicosDTO()
    {
        
    }
    
    public SitioTuristicosDTO(SitioTuristicoEntity e)
    {
        if(e!=null)
        {
            this.id=e.getId();
        
            this.nombre = e.darNombre();
        
            this.tipo = e.darTipo();
            this.imagen=e.getImagen();
            if(e.darCiudad()!=null)
            {
                this.ciudad = new CiudadDTO(e.darCiudad());  
            }else{
                this.ciudad = null;
            }
             
        }
        
    }
    
    public SitioTuristicoEntity toEntity()
    {
        SitioTuristicoEntity ret = new SitioTuristicoEntity();
        
        ret.setId(this.id);  
        ret.actualizarNombre(this.nombre);
        ret.actualizarTipo(this.tipo);
        ret.actualizarCiudad(this.ciudad.toEntity());
        ret.setImagen(this.imagen);
        return ret;
    }
    
    /*
    *Retornar el tipo de sitio turistico
    *@return tipo
    */
    public String getTipo()
    {
        return tipo;
    }
    /*
    *Retornar el id del sitio turistico
    *@return id
    */
    public Long getId()
    {
        return id;
    }
   
    /*
    *Cambia el id del sitio turistico
    *@param pid
    */
    public void setId(Long pid)
    {
        this.id=pid;
    }
    /*
    *Retornar el nombre del sitio turistico
    *@return nombre
    */
    public String getNombre()
    {
        return nombre;
    }
    
    /*
    *Cambia el nombre del sitio turistico
    *@param pnombre
    */
    public void setNombre(String pnombre)
    {
        this.nombre=pnombre;
    }
    
    public void setTipo(String p)
    {
        this.tipo=p;
    }
    
    public CiudadDTO getCiudad()
    {
        return ciudad;
    }
    
    public void setCiudad(CiudadDTO pciudad)
    {
        this.ciudad=pciudad;
    }
    
    
    
   
    
     @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
