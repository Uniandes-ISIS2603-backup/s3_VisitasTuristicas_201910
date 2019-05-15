/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * prueba
 * @author jd.castrellon
 */
@Entity
public class TarjetaDeCreditoEntity extends BaseEntity implements Serializable{
    
    /**
     * El número de la tarjeta
    */
    private String numero;
    
    /**
     * El banco al cual esta asociado la tarjeta
     */
    private String banco;
    
    /**
     * El codigo de seguridad de la tarjeta
    */
    private int codigoSeguridad;

    /**
     * Crea la relacion con viajero
     */
    @PodamExclude
    @OneToOne
    private ViajeroEntity viajero;
    
    /**
     * Crea una tarjeta de credito
     */
    public TarjetaDeCreditoEntity(){
        
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
    public int getCodigoSeguridad() {
        return codigoSeguridad;
    }

    /**
     * @param codigoSeguridad the codigoSeguridad to set
     */
    public void setCodigoSeguridad(int codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    public Object getNumber() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the viajero
     */
    public ViajeroEntity getViajero() {
        return viajero;
    }

    /**
     * @param viajero the viajero to set
     */
    public void setViajero(ViajeroEntity viajero) {
        this.viajero = viajero;
    }
}
