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
 * @author estudiante
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

    

    public TarjetaDeCreditoDTO getTarjetaDeCredito() {
        return tarjetaDeCredito;
    }

    public void setTarjetaDeCredito(TarjetaDeCreditoDTO tarjetaDeCredito) {
        this.tarjetaDeCredito = tarjetaDeCredito;
    }

    

    public ViajeDTO getViaje() {
        return viaje;
    }

    public void setViaje(ViajeDTO viaje) {
        this.viaje = viaje;
    }
    
    public ViajeroDTO() {
        
    }
    
    public Long getIdUsuario() {
        return idUsuario;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nuevo) {
        nombreUsuario = nuevo;
    }

    public int getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(int codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public tipoUsuario getTipoDeUsuario() {
        return tipoDeUsuario;
    }

    public void setTipoDeUsuario(tipoUsuario tipoDeUsuario) {
        this.tipoDeUsuario = tipoDeUsuario;
    }

    public short getCantidadPlanes() {
        return cantidadPlanes;
    }

    public void setCantidadPlanes(short cantidadPlanes) {
        this.cantidadPlanes = cantidadPlanes;
    }

    public String getInformacionPersonal() {
        return informacionPersonal;
    }

    public void setInformacionPersonal(String informacionPersonal) {
        this.informacionPersonal = informacionPersonal;
    }
    
    public ViajeroEntity toEntity() {
        ViajeroEntity nuevo = new ViajeroEntity();
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
    
    public ViajeroDTO(ViajeroEntity ent) {
        this.nombreUsuario = ent.getNombreUsuario();
        this.idioma = ent.getIdioma();
        this.informacionPersonal = ent.getInformacionPersonal();
        //this.facturas = new Facturas(ent.getFacturas());
        this.tarjetaDeCredito = new TarjetaDeCreditoDTO(ent.getTarjetaDeCredito());
    }
}
