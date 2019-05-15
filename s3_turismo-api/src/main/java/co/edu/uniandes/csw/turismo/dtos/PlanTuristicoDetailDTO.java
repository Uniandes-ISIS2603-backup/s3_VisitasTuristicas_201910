/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.BlogDeViajeroEntity;
import co.edu.uniandes.csw.turismo.entities.CiudadEntity;
import co.edu.uniandes.csw.turismo.entities.PlanTuristicoEntity;
import co.edu.uniandes.csw.turismo.entities.ValoracionEntity;
import co.edu.uniandes.csw.turismo.entities.ViajeroEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Christer Osorio
 */
public class PlanTuristicoDetailDTO extends PlanTuristicoDTO implements Serializable {

    // relación cero o muchas valoraciones
    private List<ValoracionDTO> valoraciones;
    // relación cero o muchos blogs 
    private List<BlogDeViajeroDTO> blogs;
    
    private List<ViajeroDTO> viajeros;
    
    
    private List<CiudadDTO> ciudades;
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
        if(planEntity.getViajeros() !=null)
        {
            viajeros=new ArrayList<>();
            for(ViajeroEntity entityViajero : planEntity.getViajeros())
            {
                viajeros.add(new ViajeroDTO(entityViajero));
            }
        }
        if(planEntity.getCiudades()!=null)
        {
            ciudades=new ArrayList();
            for(CiudadEntity entityCiudad: planEntity.getCiudades())
            {
                ciudades.add(new CiudadDTO(entityCiudad));
            }
        }
    }

   
    public List<CiudadDTO> getCiudades()
    {
        return ciudades;
    }
    
    public void setCiudades(List<CiudadDTO> pciudades)
    {
        this.ciudades=pciudades;
    }
 
    
    
    
    public void setViajeros(List<ViajeroDTO> via)
    {
        this.viajeros=via;
    }
    public List<ViajeroDTO> getViajeros()
    {
        return viajeros;
    }

    /**


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
        
        if(viajeros!=null)
        {
            List<ViajeroEntity> viajeroEntity= new ArrayList<>();
            for(ViajeroDTO dtoViajero:getViajeros())
            {
                viajeroEntity.add(dtoViajero.toEntity());
            }
            planTuristicoEntity.setViajero(viajeroEntity);
        }
        if(ciudades!=null)
        {
            List<CiudadEntity> ciudaddEntity=new ArrayList<>();
            for(CiudadDTO dtoCiudad:getCiudades())
            {
                ciudaddEntity.add(dtoCiudad.toEntity());
            }
        }

        return planTuristicoEntity;
    }

}
