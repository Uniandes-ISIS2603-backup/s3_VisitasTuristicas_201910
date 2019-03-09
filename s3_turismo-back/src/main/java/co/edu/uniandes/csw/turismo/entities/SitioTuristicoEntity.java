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

    @PodamExclude
    @ManyToOne
    private CiudadEntity ciudad;
    /**
     * Tipo de sitio turistico.
     */
    private String tipo;

    /*
    *Atributo que representa el nombre de un sitio turistico
     */
    private String nombre;

    /*
    *Retorna la ciudad a la cual estan asociados los sitios turisticos
    *@return ciudad
     */
    public CiudadEntity darCiudad() {
        return ciudad;
    }

    /*
    *Actualiza la ciudad
    *@param pciudad
     */
    public void actualizarCiudad(CiudadEntity pciudad) {
        this.ciudad = pciudad;
    }

    /*
    *Actualiza el tipo de sitio turistico
    *@param p
     */
    public void actualizarTipo(String p) {
        this.tipo = p;
    }

    /*
    *Retornar el tipo de sitio turistico
    *@return tipo
     */
    public String darTipo() {
        return tipo;
    }

    /*
    *Retornar el nombre del sitio turistico
    *@return nombre
     */
    public String darNombre() {
        return nombre;
    }

    /*
    *Cambia el nombre del sitio turistico
    *@param pnombre
     */
    public void actualizarNombre(String pnombre) {
        nombre = pnombre;
    }

    /*
    *Retorna la ciudad
    *@return ciudad
    */
    public CiudadEntity getCiudad() {
        return ciudad;
    }

}
