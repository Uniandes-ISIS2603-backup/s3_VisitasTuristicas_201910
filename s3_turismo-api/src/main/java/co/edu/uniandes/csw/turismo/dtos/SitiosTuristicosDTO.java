/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;
import co.edu.uniandes.csw.turismo.entities.CiudadEntity;
import co.edu.uniandes.csw.turismo.entities.SitioTuristicoEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 *
 * @author David Fonseca
 */
public class SitiosTuristicosDTO implements Serializable 
{
    
    public enum Tipo
    {
        IGLESIA,
        MONUMENTO,
        MUSEO,
        NATURALEZA,
        PATRIMONIO_HUMANIDAD,
        ENTRETENIMIENTO
    }
    
    /**
     * Tipo de sitio turistico.
     */
    private SitioTuristicoEntity.Tipo tipo;
    /*
    *Atributo que representa el id de un sitio turistico
    */
    private Long id;
    
    /*
    *Atributo que representa el nombre de un sitio turistico
    */
    private String nombre;
    
    private CiudadDTO ciudad;
    
    /*
    *Constructor de la clase
    */
    public SitiosTuristicosDTO()
    {
        
    }
    
    public SitiosTuristicosDTO(SitioTuristicoEntity e)
    {
        this.id=e.getId();
        
        this.nombre = e.darNombre();
        
        this.tipo = e.darTipo();
        
        this.ciudad = new CiudadDTO(e.darCiudad());
        
    }
    
    public SitioTuristicoEntity toEntity()
    {
        SitioTuristicoEntity ret = new SitioTuristicoEntity();
        
        ret.setId(this.id);  
        ret.actualizarNombre(this.nombre);
        ret.actualizarTipo(this.darTipo());
        ret.actualizarCiudad(this.ciudad.toEntity());
        
        return ret;
    }
    
    /*
    *Retornar el tipo de sitio turistico
    *@return tipo
    */
    public SitioTuristicoEntity.Tipo darTipo()
    {
        return tipo;
    }
    /*
    *Retornar el id del sitio turistico
    *@return id
    */
    public Long darID()
    {
        return id;
    }
   
    /*
    *Cambia el id del sitio turistico
    *@param pid
    */
    public void actualizarID(Long pid)
    {
        id=pid;
    }
    /*
    *Retornar el nombre del sitio turistico
    *@return nombre
    */
    public String darNombre()
    {
        return nombre;
    }
    
    /*
    *Cambia el nombre del sitio turistico
    *@param pnombre
    */
    public void actualizarNombre(String pnombre)
    {
        nombre=pnombre;
    }
    
    
    
   
    
     @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
