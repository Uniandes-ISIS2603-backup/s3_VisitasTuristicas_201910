/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author David Fonseca
 */
@Entity
public class SitioTuristicoEntity extends BaseEntity implements Serializable {
    
    public enum Tipo
    {
        IGLESIA,
        MONUMENTO,
        MUSEO,
        NATURALEZA,
        PATRIMONIO_HUMANIDAD,
        ENTRETENIMIENTO
    }
    
    @PodamExclude
    @ManyToOne
    private CiudadEntity ciudad;
      /**
     * Tipo de sitio turistico.
     */
    private Tipo tipo;

    
    /*
    *Atributo que representa el nombre de un sitio turistico
    */
    private String nombre;
    
    
    /*
    *Retorna la ciudad a la cual estan asociados los sitios turisticos
    *@return ciudad
    */
    public CiudadEntity darCiudad()
    {
        return ciudad;
    }
    
    public void actualizarCiudad(CiudadEntity pciudad)
    {
        this.ciudad=pciudad;
    }
    
    /*
    *Retornar el tipo de sitio turistico
    *@return tipo
    */

    public Tipo darTipo()
    {
        return tipo;
    }
    
    public void cambiarTipo(Tipo ptipo)
    {
        this.tipo=ptipo;
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
    

    


}
