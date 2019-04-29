/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.BlogDeViajeroEntity;
import co.edu.uniandes.csw.turismo.entities.PaisEntity;
import co.edu.uniandes.csw.turismo.entities.PlanTuristicoEntity;
import co.edu.uniandes.csw.turismo.entities.ValoracionEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Christer Osorio
 */
public class PlanTuristicoDetailDTO extends PlanTuristicoDTO implements Serializable {

    private List<PaisDTO> paises;
    // relación cero o muchas valoraciones
    private List<ValoracionDTO> valoraciones;
    // relación cero o muchos blogs 
    private List<BlogDeViajeroDTO> blogs;
    //private List<TransporteDTO> transportes;
//Constructor por defecto

    public PlanTuristicoDetailDTO() {
        super();
    }

    //constructor
    public PlanTuristicoDetailDTO(PlanTuristicoEntity planEntity) throws BusinessLogicException {
        super(planEntity);
        if (planEntity.getBlogs() != null) {

            blogs = new ArrayList<>();

            for (BlogDeViajeroEntity entityBlog : planEntity.getBlogs()) {

                blogs.add(new BlogDeViajeroDTO(entityBlog));

            }

        }

        if (planEntity.getValoraciones() != null) {

            valoraciones = new ArrayList<>();

            for (ValoracionEntity entityValoracion : planEntity.getValoraciones()) {

                valoraciones.add(new ValoracionDTO(entityValoracion));

            }

        }
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
     *
     * Devuelve las valoraciones asociados a este plan
     *
     *
     *
     * @return Lista de DTOs de Valoraciones
     *
     */
    public List<ValoracionDTO> getValoraciones() {
        return valoraciones;
    }

    /**
     *
     * Modifica las valoraciones de este plan.
     *
     *
     *
     * @param valoraciones Las nuevas valoraciones
     *
     */
    public void setValoraciones(List<ValoracionDTO> valoraciones) {
        this.valoraciones = valoraciones;
    }

    /**
     *
     * Devuelve los blogs asociados a este plan
     *
     *
     *
     * @return Lista de DTOs de Blogs
     *
     */
    public List<BlogDeViajeroDTO> getBlogs() {
        return blogs;
    }

    /**
     *
     * Modifica los blogs de este plan.
     *
     *
     *
     * @param blogs Los nuevos blogs
     *
     */
    public void setBlogs(List<BlogDeViajeroDTO> blogs) {
        this.blogs = blogs;
    }


    @Override
    public PlanTuristicoEntity toEntity() {
        PlanTuristicoEntity planTuristicoEntity = super.toEntity();

        if (blogs != null) {

            List<BlogDeViajeroEntity> blogsEntity = new ArrayList<>();

            for (BlogDeViajeroDTO dtoBlog : getBlogs()) {

                blogsEntity.add(dtoBlog.toEntity());

            }

            planTuristicoEntity.setBlogs(blogsEntity);

        }

        if (valoraciones != null) {

            List<ValoracionEntity> valoracionesEntity = new ArrayList<>();

            for (ValoracionDTO dtoValoracion : getValoraciones()) {

                valoracionesEntity.add(dtoValoracion.toEntity());

            }

            planTuristicoEntity.setValoraciones(valoracionesEntity);

        }
        
        if (paises != null) {

            List<PaisEntity> paisEntity = new ArrayList<>();

            for (PaisDTO dtoPais : getPaises()) {

                paisEntity.add(dtoPais.toEntity());

            }

            planTuristicoEntity.setPaises(paisEntity);

        }

        return planTuristicoEntity;
    }

}
