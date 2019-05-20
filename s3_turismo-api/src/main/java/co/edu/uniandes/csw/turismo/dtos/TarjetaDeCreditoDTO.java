/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.TarjetaDeCreditoEntity;
import java.io.Serializable;


/**
 *
 * @author estudiante modificado por David Fonseca cambiados atributos por serializables y agregado constructor entity
 */
public class TarjetaDeCreditoDTO implements Serializable  {

    private String numero;
    
    private String banco;
    
    private Integer codigoSeguridad;
    
    private Long idTarjetaDeCredito;
    
    private ViajeroDTO viajero;


    
    public TarjetaDeCreditoDTO()
    {
        
    }

    /**
    * @return the viajero
    */
    public ViajeroDTO getViajero() {
        return viajero;
    }

    /**
     * @param viajero the viajero to set
     */
    public void setViajero(ViajeroDTO viajero) {
        this.viajero = viajero;
    }

    
    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the banco
     */
    public String getBanco() {
        return banco;
    }

    /**
     * @param banco the banco to set
     */
    public void setBanco(String banco) {
        this.banco = banco;
    }

    /**
     * @return the codigoSeguridad
     */
    public Integer getCodigoSeguridad() {
        return codigoSeguridad;
    }

    /**
     * @param codigoSeguridad the codigoSeguridad to set
     */
    public void setCodigoSeguridad(Integer codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    /**
     * @return the idTarjetaDeCredito
     */
    public Long getIdTarjetaDeCredito() {
        return idTarjetaDeCredito;
    }

    /**
     * @param idTarjetaDeCredito the idTarjetaDeCredito to set
     */
    public void setIdTarjetaDeCredito(Long idTarjetaDeCredito) {
        this.idTarjetaDeCredito = idTarjetaDeCredito;
    }
    
    public TarjetaDeCreditoEntity toEntity()
    {
        TarjetaDeCreditoEntity aRetornar = new TarjetaDeCreditoEntity();
        aRetornar.setNumero(numero);
        aRetornar.setBanco(banco);
        aRetornar.setCodigoSeguridad(codigoSeguridad);
        aRetornar.setViajero(viajero.toEntity());
        return aRetornar;
    }
    
    public TarjetaDeCreditoDTO(TarjetaDeCreditoEntity tarjeta)
    {
        if(tarjeta!=null)
        {    
            this.numero = tarjeta.getNumero();
            this.banco = tarjeta.getBanco();
            this.codigoSeguridad = tarjeta.getCodigoSeguridad();
            this.idTarjetaDeCredito=tarjeta.getId();
            if(tarjeta.getViajero()!=null)
            {  
                this.viajero=new ViajeroDTO(tarjeta.getViajero());
            }else{
                this.viajero = null;
            }
        }
    }
     
}
