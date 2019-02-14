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
    private Tipo tipo;
    /*
    *Atributo que representa el id de un sitio turistico
    */
    private Long id;
    
    /*
    *Atributo que representa el nombre de un sitio turistico
    */
    private String nombre;
    
    /*
    *Atributo que representa el puntaje de un sitio turistico
    */
    private Integer puntaje;
    
    /*
    *Constructor de la clase
    */
    public SitiosTuristicosDTO()
    {
    }
    
    /*
    *Retornar el tipo de sitio turistico
    *@return tipo
    */
    public Tipo darTipo()
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
    
    /*
    *Retornar el puntaje de un sitio turistico
    *@return puntaje
    */
    public Integer darPuntaje()
    {
        return puntaje;
    }
    
    /*
    *Cambia el puntaje de un sitio turistico
    *@param ppuntaje
    */
    public void cambiarPuntaje(Integer ppuntaje)
    {
        puntaje=ppuntaje;
    }
    
     @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
