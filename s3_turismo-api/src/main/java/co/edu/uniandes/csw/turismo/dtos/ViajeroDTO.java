/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.PreferenciaEntity;
import co.edu.uniandes.csw.turismo.entities.ViajeroEntity;
import java.io.Serializable;
import java.util.ArrayList;
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
    private int cantidadPlanes;
    private String informacionPersonal;
    //necesito un detail de preferencia?
    private List<PreferenciaDTO> preferencias;
    private TarjetaDeCreditoDTO tarjetaDeCredito;
    private List<Facturas> facturas;
    private List<PlanTuristicoDTO> planesTuristicos;
    private ViajeDTO viaje;

    public List<PreferenciaDTO> getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(List<PreferenciaDTO> preferencias) {
        this.preferencias = preferencias;
    }

    public TarjetaDeCreditoDTO getTarjetaDeCredito() {
        return tarjetaDeCredito;
    }

    public void setTarjetaDeCredito(TarjetaDeCreditoDTO tarjetaDeCredito) {
        this.tarjetaDeCredito = tarjetaDeCredito;
    }

    public List<Facturas> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Facturas> facturas) {
        this.facturas = facturas;
    }

    public List<PlanTuristicoDTO> getPlanesTuristicos() {
        return planesTuristicos;
    }

    public void setPlanesTuristicos(List<PlanTuristicoDTO> planesTuristicos) {
        this.planesTuristicos = planesTuristicos;
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

    public int getCantidadPlanes() {
        return cantidadPlanes;
    }

    public void setCantidadPlanes(int cantidadPlanes) {
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
        nuevo.setIdioma(idioma);
        nuevo.setInformacionPersonal(informacionPersonal);
        nuevo.setCantidadPlanes(this.cantidadPlanes);
        //nuevo.setFacturas(facturas.toEntity());
        //nuevo.setPlanesTuristicos(planesTuristicos.toEntity());
        nuevo.setTarjetaDeCredito(tarjetaDeCredito.toEntity());
        List<PreferenciaEntity> qwerty = new ArrayList<>();
        for(PreferenciaDTO prefs : preferencias) {
            qwerty.add(prefs.toEntity());
        }
        nuevo.setPreferencias(qwerty);
        //nuevo.setViaje(viaje.toEntity());
        //nuevo.setTipoDeUsuario(tipoDeUsuario);
        
        return nuevo;
    }
    
    public ViajeroDTO(ViajeroEntity ent) {
        this.nombreUsuario = ent.getNombreUsuario();
        this.idioma = ent.getIdioma();
        this.informacionPersonal = ent.getInformacionPersonal();
        this.cantidadPlanes = ent.getCantidadPlanes();
        //this.facturas = new Facturas(ent.getFacturas());
        //this.planesTuristicos = ent.getPlanesTuristicos();
        //this.tarjetaDeCredito = new TarjetaDeCreditoDTO(ent.getTarjetaDeCredito());
        List<PreferenciaDTO> prefs = new ArrayList<>();
        for(PreferenciaEntity pr : ent.getPreferencias()) {
            prefs.add(new PreferenciaDTO(pr));
        }
        preferencias = prefs;
    }
}
