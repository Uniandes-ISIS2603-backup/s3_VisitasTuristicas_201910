/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.ViajeroEntity;
import java.io.Serializable;
/**
 *
 * @author Juan Sebastian Gutierrez S. modificado por David Fonseca cambiados atributos por atributos serializables y agregado constructor entity
 */
public class ViajeroDTO implements Serializable {
    
  
    
    private Long idUsuario;
    private String nombreUsuario;
    private Integer codigoUnico;
    private String idioma;
    private String tipoDeUsuario;
    private Integer cantidadPlanes;
    private String informacionPersonal;
    private ViajeDTO viaje;

    

    

    

    /**
     * se retorna el viaje asignado
     * @return 
     */
    public ViajeDTO getViaje() {
        return viaje;
    }

    /**
     * se asigna el viaje
     * @param viaje 
     */
    public void setViaje(ViajeDTO viaje) {
        this.viaje = viaje;
    }
    /**
     * constructor vacío
     */
    public ViajeroDTO() {
        
    }
    /**
     * se retorna el Id
     * @return idUsuario
     */
    public Long getIdUsuario() {
        return idUsuario;
    }
    /**
     * se retorna el nombre del usuario
     * @return nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    /**
     * se asigna el nombre al usuario
     * @param nuevo 
     */
    public void setNombreUsuario(String nuevo) {
        nombreUsuario = nuevo;
    }

    /**
     * se retorna el codigo unico
     * @return codigoUnico
     */
    public Integer getCodigoUnico() {
        return codigoUnico;
    }

    /**
     * se asigna el codigo unico
     * @param codigoUnico 
     */
    public void setCodigoUnico(Integer codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    /**
     * se retorna el idioma del usuario
     * @return idioma
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * se asigna el idioma
     * @param idioma 
     */
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    /**
     * se retorna el tipo de usuario
     * @return tipoDeUsuario
     */
    public String getTipoDeUsuario() {
        return tipoDeUsuario;
    }

    /**
     * se asigna el tipo de usuario
     * @param tipoDeUsuario 
     */
    public void setTipoDeUsuario(String tipoDeUsuario) {
        this.tipoDeUsuario = tipoDeUsuario;
    }

    /**
     * se retorna la cantidad de planes
     * @return cantidadPlanes
     */
    public Integer getCantidadPlanes() {
        return cantidadPlanes;
    }

    /**
     * se asigna la cantidad de planes
     * @param cantidadPlanes 
     */
    public void setCantidadPlanes(Integer cantidadPlanes) {
        this.cantidadPlanes = cantidadPlanes;
    }

    /**
     * se retorna la informacion personal
     * @return informacionPersonal
     */
    public String getInformacionPersonal() {
        return informacionPersonal;
    }

    /**
     * se asigna la informacion personal
     * @param informacionPersonal 
     */
    public void setInformacionPersonal(String informacionPersonal) {
        this.informacionPersonal = informacionPersonal;
    }
    
    /**
     * se crea una nueva ViajeroEntity basado en los atributos de la clase actual
     * @return ViajeroEntity
     */
    public ViajeroEntity toEntity() {
        ViajeroEntity nuevo = new ViajeroEntity();
        
        nuevo.setId(this.idUsuario);
        
        nuevo.setNombreUsuario(nombreUsuario);
        nuevo.setCodigoUnico(codigoUnico);
        nuevo.setIdioma(idioma);
        nuevo.setTipoDeUsuario(tipoDeUsuario);
        nuevo.setCantidadPlanes(this.cantidadPlanes);
        nuevo.setInformacionPersonal(informacionPersonal);
       
        if(this.viaje!= null){            
            nuevo.setViaje(this.viaje.toEntity());
        }
        return nuevo;
    }
    
    /**
     * Constructor que genera un ViajeroDTO basado en una ViajeroEntity pasada por parametro
     * @param ent 
     */
    public ViajeroDTO(ViajeroEntity ent) {
        
        if(ent!=null)
        {    
            this.idUsuario = ent.getId();
            this.cantidadPlanes=ent.getCantidadPlanes();
            this.codigoUnico=ent.getCodigoUnico();
            this.tipoDeUsuario=ent.getTipoDeUsuario();
            if (ent.getViaje()!= null) {
                this.viaje = new ViajeDTO(ent.getViaje());
            } else {
                this.viaje = null;
            }
            this.nombreUsuario = ent.getNombreUsuario();
            this.idioma = ent.getIdioma();
            this.informacionPersonal = ent.getInformacionPersonal();
        }
    }


}
