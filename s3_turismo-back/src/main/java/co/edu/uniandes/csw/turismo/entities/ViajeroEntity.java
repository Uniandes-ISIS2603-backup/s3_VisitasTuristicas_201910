/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */

@Entity
public class ViajeroEntity  extends BaseEntity  implements Serializable{
    
   
    
    private String nombreUsuario;
    private int codigoUnico;
    private String idioma;
    private String tipoDeUsuario;
    private int cantidadPlanes;
    private String informacionPersonal;
    
    @PodamExclude
    @javax.persistence.OneToMany(mappedBy ="viajero", cascade = CascadeType.PERSIST,orphanRemoval = true)
    List<PreferenciaEntity> preferencias;
    
    @PodamExclude
    @javax.persistence.OneToMany(mappedBy ="viajero", cascade = CascadeType.PERSIST,orphanRemoval = true)
    List<TarjetaDeCreditoEntity> tarjetas;
    
    @PodamExclude
    @javax.persistence.OneToMany(mappedBy = "viajero", fetch = javax.persistence.FetchType.LAZY)
    List<FacturaEntity> facturas;
    
    @PodamExclude
    @ManyToMany(mappedBy="viajeros",fetch=FetchType.LAZY)
    List<PlanTuristicoEntity> planesTuristicos;
    
    @PodamExclude
    @javax.persistence.OneToOne(mappedBy = "viajero", fetch = javax.persistence.FetchType.EAGER)
    ViajeEntity viaje;

    public List<PreferenciaEntity> getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(List<PreferenciaEntity> preferencias) {
        this.preferencias = preferencias;
    }

    public List<TarjetaDeCreditoEntity> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<TarjetaDeCreditoEntity> tarjetaDeCredito) {
        this.tarjetas = tarjetaDeCredito;
    }

    public List<FacturaEntity> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<FacturaEntity> facturas) {
        this.facturas = facturas;
    }

    public List<PlanTuristicoEntity> getPlanesTuristicos() {
        return planesTuristicos;
    }

    public void setPlanesTuristicos(List<PlanTuristicoEntity> planesTuristicos) {
        this.planesTuristicos = planesTuristicos;
    }

    public ViajeEntity getViaje() {
        return viaje;
    }

    public void setViaje(ViajeEntity viaje) {
        this.viaje = viaje;
    }



    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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

    public String getTipoDeUsuario() {
        return tipoDeUsuario;
    }

    public void setTipoDeUsuario(String tipoDeUsuario) {
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
    
    
}
