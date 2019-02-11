/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author David Fonseca
 */
@Entity
public class CiudadEntity extends BaseEntity implements Serializable {
    
    private String nombre;
    
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
