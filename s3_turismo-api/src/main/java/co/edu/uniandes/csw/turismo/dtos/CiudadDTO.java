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
 * @author estudiante
 */
public class CiudadDTO implements Serializable {
     /*
    *Atributo que representa la cantidad de sitios turisticos
    */
    private int cantidadSitiosTuristicos;
   
    /*
    *Atributo que representa el id de una ciudad
    */
    private int id;
    
    /*
    *Atributo que representa el nombre de una ciudad
    */
    private String nombre;
    
    /*
    *Constructor de la clase ciudad
    */
    public CiudadDTO(int pid, String pnombre)
    {
        id=pid;
        nombre=pnombre;
    }
    
    /*
    *Retornar el id de la ciudad
    *@return id
    */
    public int darID()
    {
        return id;
    }
    
    /*
    *Retornar el nombre de la ciudad
    *@return nombre
    */
    public String darNombre()
    {
        return nombre;
    }
    
    /*
    *Actualizar el nombre de una ciudad por otro
    *@param pNombre
    */
    public void actualizarNombre(String pNombre)
    {
        this.nombre=pNombre;
    }  
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
