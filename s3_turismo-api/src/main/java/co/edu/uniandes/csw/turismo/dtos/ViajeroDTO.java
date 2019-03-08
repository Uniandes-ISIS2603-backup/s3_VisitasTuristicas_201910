/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.ViajeroEntity;
import java.io.Serializable;
import java.util.List;
/**
 *
 * @author Juan Sebastian Gutierrez S.
 */
public class ViajeroDTO implements Serializable {
    
    enum tipoUsuario {
        TURISTA, GUIA, ADMIN
    }
    
    private Long idUsuario;
    private String nombreUsuario;
    private int codigoUnico;
    private String idioma;
    private tipoUsuario tipoDeUsuario;
    private short cantidadPlanes;
    private String informacionPersonal;
    //necesito un detail de preferencia?
    //private List<PreferenciaDTO> preferencias;
    private TarjetaDeCreditoDTO tarjetaDeCredito;
    //private List<Facturas> facturas;
    //private List<PlanTuristicoDTO> planesTuristicos;
    private ViajeDTO viaje;

    

    /**
     * se retorna la tarjeta de credito
     * @return tarjetaDeCredito
     */
    public TarjetaDeCreditoDTO getTarjetaDeCredito() {
        return tarjetaDeCredito;
    }

    /**
     * se asigna una tarjeta de credito
     * @param tarjetaDeCredito 
     */
    public void setTarjetaDeCredito(TarjetaDeCreditoDTO tarjetaDeCredito) {
        this.tarjetaDeCredito = tarjetaDeCredito;
    }

    

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
    public int getCodigoUnico() {
        return codigoUnico;
    }

    /**
     * se asigna el codigo unico
     * @param codigoUnico 
     */
    public void setCodigoUnico(int codigoUnico) {
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
    public tipoUsuario getTipoDeUsuario() {
        return tipoDeUsuario;
    }

    /**
     * se asigna el tipo de usuario
     * @param tipoDeUsuario 
     */
    public void setTipoDeUsuario(tipoUsuario tipoDeUsuario) {
        this.tipoDeUsuario = tipoDeUsuario;
    }

    /**
     * se retorna la cantidad de planes
     * @return cantidadPlanes
     */
    public short getCantidadPlanes() {
        return cantidadPlanes;
    }

    /**
     * se asigna la cantidad de planes
     * @param cantidadPlanes 
     */
    public void setCantidadPlanes(short cantidadPlanes) {
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
        nuevo.setCantidadPlanes(this.cantidadPlanes);
        nuevo.setIdioma(idioma);
        //nuevo.setFacturas(facturas.toEntity());
        //nuevo.setPlanesTuristicos(planesTuristicos.toEntity());
        nuevo.setTarjetaDeCredito(tarjetaDeCredito.toEntity());
        
        //nuevo.setTipoDeUsuario(tipoDeUsuario);
        nuevo.setInformacionPersonal(informacionPersonal);
        return nuevo;
    }
    
    /**
     * Constructor que genera un ViajeroDTO basado en una ViajeroEntity pasada por parametro
     * @param ent 
     */
    public ViajeroDTO(ViajeroEntity ent) {
        
        this.idUsuario = ent.getId();
        
        this.nombreUsuario = ent.getNombreUsuario();
        this.idioma = ent.getIdioma();
        this.informacionPersonal = ent.getInformacionPersonal();
        //this.facturas = new Facturas(ent.getFacturas());
        this.tarjetaDeCredito = new TarjetaDeCreditoDTO(ent.getTarjetaDeCredito());
    }
}
