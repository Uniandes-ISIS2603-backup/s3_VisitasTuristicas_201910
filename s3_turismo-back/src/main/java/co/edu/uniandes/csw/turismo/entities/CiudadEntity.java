/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author David Fonseca
 */
@Entity
public class CiudadEntity extends BaseEntity implements Serializable {
    
    private String nombre;
    @PodamExclude
    @OneToMany
    private List<SitioTuristicoEntity> sitios;
    
    
    
    /*
    *Actualiza la lista de sitios turisticos
    *@param sitio
    */
    public void actualizarSitios(List<SitioTuristicoEntity> sitio)
    {
        this.sitios=sitio;
    }
    
    /*
    *Retorna la lista de sitios turisticos
    @return sitios
    *
    */
    public List<SitioTuristicoEntity> darSitios()
    {
        return sitios;
    }
    /*
    *Retorna el nombre de la ciudad
    *@return nombre
    */
    public String darNombre()
    {
        return nombre;
    }
    
    /*
    *Actualiza el nombre de la ciudad
    *@param pnombre
    */
    public void actualizarNombre(String pnombre)
    {
        this.nombre=pnombre;
    }
    
}
