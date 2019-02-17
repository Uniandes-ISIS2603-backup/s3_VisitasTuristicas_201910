/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.test.persistence;

import co.edu.uniandes.csw.turismo.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.turismo.persistence.TarjetaDeCreditoPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jd.castrellon 
 */
@RunWith(Arquillian.class)
public class TarjetaDeCretitoEntityPersistenceTest {
    
    @Inject
    private TarjetaDeCreditoPersistence tp;
    
    @PersistenceContext
    private EntityManager em;
    
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TarjetaDeCreditoEntity.class.getPackage())
                .addPackage(TarjetaDeCreditoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void createTarjetaDeCreditoEntity(){
        PodamFactory factory = new PodamFactoryImpl();
        TarjetaDeCreditoEntity newTarjetaDeCreditoEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        
        TarjetaDeCreditoEntity te = tp.create(newTarjetaDeCreditoEntity);
        
        Assert.assertNotNull(te);
        
        TarjetaDeCreditoEntity entity = em.find(TarjetaDeCreditoEntity.class, te.getId());
        
        Assert.assertEquals(newTarjetaDeCreditoEntity, entity);
    }
}
