/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.test.logic;

import co.edu.uniandes.csw.turismo.ejb.PreferenciaLogic;
import co.edu.uniandes.csw.turismo.entities.PreferenciaEntity;

import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.PreferenciaPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class PreferenciaLogicTest {
    
    @Inject
    private PreferenciaLogic preferenciaLogic;
    
     
    @PersistenceContext
    private EntityManager em;
    
    PodamFactory factory = new PodamFactoryImpl();
    
    @Deployment
    public static JavaArchive createDeployment() {
        
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PreferenciaEntity.class.getPackage())
                .addPackage(PreferenciaLogic.class.getPackage())
                .addPackage(PreferenciaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
        
    }
    
     @Test
    public void createPreferenciatest() throws BusinessLogicException {
        PreferenciaEntity newEntity = factory.manufacturePojo(PreferenciaEntity.class);
        PreferenciaEntity result = preferenciaLogic.createPreferencia(newEntity);
        Assert.assertNotNull(result);
        PreferenciaEntity ent = em.find(PreferenciaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), ent.getId());
        Assert.assertEquals(newEntity.getNombrePreferencia(), ent.getNombrePreferencia());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createEditorialConNombreVacioTest() throws BusinessLogicException {
        PreferenciaEntity newEntity = factory.manufacturePojo(PreferenciaEntity.class);
        //ViajeroEntity newerEntity = factory.manufacturePojo(ViajeroEntity.class);
        newEntity.setNombrePreferencia("");
        preferenciaLogic.createPreferencia(newEntity);
    }
    
     @Test
    public void getViajeroTest() throws BusinessLogicException {
        PreferenciaEntity newEntity = factory.manufacturePojo(PreferenciaEntity.class);
        newEntity = preferenciaLogic.createPreferencia(newEntity);
        PreferenciaEntity ent = em.find(PreferenciaEntity.class, newEntity.getId());
        Assert.assertNotNull(ent);
        Assert.assertEquals(newEntity.getNombrePreferencia(), ent.getNombrePreferencia());
    }
    
    @Test
    public void deleteViajeroTest() throws BusinessLogicException {
        PreferenciaEntity newEntity = factory.manufacturePojo(PreferenciaEntity.class);
        newEntity = preferenciaLogic.createPreferencia(newEntity);
        preferenciaLogic.deletePreferencia(newEntity.getId());
        PreferenciaEntity q = em.find(PreferenciaEntity.class, newEntity.getId());
        Assert.assertNull(q);
    }
}
