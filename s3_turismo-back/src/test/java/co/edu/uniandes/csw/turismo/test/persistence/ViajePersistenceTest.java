/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.test.persistence;

import co.edu.uniandes.csw.turismo.entities.ViajeEntity;
import co.edu.uniandes.csw.turismo.persistence.ViajePersistence;
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
 * @author Christer Osorio
 */
@RunWith(Arquillian.class)
public class ViajePersistenceTest {
    
    @Inject
    private ViajePersistence ep;
    @PersistenceContext
    private EntityManager em;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ViajeEntity.class.getPackage())
                .addPackage(ViajePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");

    }
    
    @Test
    public void createViajeTest(){
        PodamFactory factory= new PodamFactoryImpl();
        ViajeEntity newViajeEntity = factory.manufacturePojo(ViajeEntity.class);
        ViajeEntity result= ep.create(newViajeEntity);
        
        Assert.assertNotNull(result);
        
        ViajeEntity  entity=em.find(ViajeEntity.class, result.getId());
        
        Assert.assertEquals(newViajeEntity.getFechaInicio(),entity.getFechaInicio());
        Assert.assertEquals(newViajeEntity.getFechaFin(),entity.getFechaFin());
        
        
    }
    
}
