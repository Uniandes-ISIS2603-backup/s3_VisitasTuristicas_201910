/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Christer Osorio
 */
@Entity
public class PlanTuristicoEntity extends BaseEntity implements Serializable {

    private String nombrePlan;
    private String tipoPlan;
    private Double costoPorPersona;
    private String descripcion;
    private String ubicacion;
    private Boolean guia;
    private String duracion;
    private String idioma;

    //Relacion con blogs
    @PodamExclude

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)

    private List<BlogDeViajeroEntity> blogs = new ArrayList<BlogDeViajeroEntity>();

    // Relacion con valoraciones
    @PodamExclude

    @OneToMany(mappedBy = "planTuristico", cascade = CascadeType.PERSIST, orphanRemoval = true)

    private List<ValoracionEntity> valoraciones = new ArrayList<ValoracionEntity>();

    @PodamExclude
    @ManyToMany
    private List<ViajeroEntity> viajeros;
    
    @PodamExclude
    @ManyToMany(mappedBy="planes")
    private List<CiudadEntity> ciudades= new ArrayList<CiudadEntity>();

    public PlanTuristicoEntity() 
    {

    }

    public List<CiudadEntity> getCiudades()
    {
        return ciudades;
    }
    public void setCiudades(List<CiudadEntity> paises)
    {
        this.ciudades=paises;
    }
    /**
     * @return the nombrePlan
     */
    public String getNombrePlan() {
        return nombrePlan;
    }

    /**
     * @param nombrePlan the nombrePlan to set
     */
    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    /**
     * @return the tipoPlan
     */
    public String getTipoPlan() {
        return tipoPlan;
    }

    /**
     * @param tipoPlan the tipoPlan to set
     */
    public void setTipoPlan(String tipoPlan) {
        this.tipoPlan = tipoPlan;
    }

    /**
     * @return the costoPorPersona
     */
    public Double getCostoPorPersona() {
        return costoPorPersona;
    }

    /**
     * @param costoPorPersona the costoPorPersona to set
     */
    public void setCostoPorPersona(Double costoPorPersona) {
        this.costoPorPersona = costoPorPersona;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the ubicacion
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * @param ubicacion the ubicacion to set
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * @return the guia
     */
    public Boolean getGuia() {
        return guia;
    }

    /**
     * @param guia the guia to set
     */
    public void setGuia(Boolean guia) {
        this.guia = guia;
    }

    /**
     * @return the duracion
     */
    public String getDuracion() {
        return duracion;
    }

    /**
     * @param duracion the duracion to set
     */
    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    /**
     * @return the idioma
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * @param idioma the idioma to set
     */
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    /**
     *
     * Devuelve los blogs del plan.
     *
     *
     *
     * @return Lista de entidades de tipo BlogDeViajero
     *
     */
    public List<BlogDeViajeroEntity> getBlogs() {

        return blogs;

    }

    /**
     *
     * Modifica los blogs de un plan.
     *
     *
     *
     * @param blogs Los nuevos blogs.
     *
     */
    public void setBlogs(List<BlogDeViajeroEntity> blogs) {

        this.blogs = blogs;

    }

    /**
     *
     * Devuelve las valoraciones del plan.
     *
     *
     *
     * @return Lista de entidades de tipo Valoracion
     *
     */
    public List<ValoracionEntity> getValoraciones() {

        return valoraciones;

    }

    /**
     *
     * Modifica las valoraciones de un plan.
     *
     *
     *
     * @param valoraciones Las nuevas valoraciones .
     *
     */
    public void setValoraciones(List<ValoracionEntity> valoraciones) {

        this.valoraciones = valoraciones;

    }

    /**
     * @return the viajero
     */
    public List<ViajeroEntity> getViajeros() {
        return viajeros;
    }

    /**
     * @param viajero the viajero to set
     */
    public void setViajero(List<ViajeroEntity> viajero) {
        this.viajeros = viajero;
    }

}
