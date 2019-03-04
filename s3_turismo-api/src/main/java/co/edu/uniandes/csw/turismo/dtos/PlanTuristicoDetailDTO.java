/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.PlanTuristicoEntity;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Christer Osorio
 */
public class PlanTuristicoDetailDTO extends PlanTuristicoDTO implements Serializable {

    private List<PaisDTO> paises;
    private List<ValoracionDTO> valoraciones;
    private List<BlogDeViajeroDTO> blogs;
    //private List<TransporteDTO> transportes;
    private List<PreferenciaDTO> preferencias;

    public PlanTuristicoDetailDTO() {
        super();
    }

    /**
     * @return the paises
     */
    public List<PaisDTO> getPaises() {
        return paises;
    }

    /**
     * @param paises the paises to set
     */
    public void setPaises(List<PaisDTO> paises) {
        this.paises = paises;
    }

    /**
     * @return the valoraciones
     */
    public List<ValoracionDTO> getValoraciones() {
        return valoraciones;
    }

    /**
     * @param valoraciones the valoraciones to set
     */
    public void setValoraciones(List<ValoracionDTO> valoraciones) {
        this.valoraciones = valoraciones;
    }

    /**
     * @return the blogs
     */
    public List<BlogDeViajeroDTO> getBlogs() {
        return blogs;
    }

    /**
     * @param blogs the blogs to set
     */
    public void setBlogs(List<BlogDeViajeroDTO> blogs) {
        this.blogs = blogs;
    }

    /**
     * @return the preferencias
     */
    public List<PreferenciaDTO> getPreferencias() {
        return preferencias;
    }

    /**
     * @param preferencias the preferencias to set
     */
    public void setPreferencias(List<PreferenciaDTO> preferencias) {
        this.preferencias = preferencias;
    }
//

    public PlanTuristicoDetailDTO(PlanTuristicoEntity planTuristicoEntity) {
        
        super (planTuristicoEntity);

    }

}
