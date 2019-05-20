/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.test.persistence;

import co.edu.uniandes.csw.turismo.entities.PreferenciaEntity;
import co.edu.uniandes.csw.turismo.persistence.PreferenciaPersistence;
import java.util.ArrayList;
import java.util.List;
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
public class PreferenciaPersistenceTest {
    @Inject
    private PreferenciaPersistence ep;
    
    @PersistenceContext
    private EntityManager em;
    
    
    
    @Deployment
    public static JavaArchive createDeployment() {
        
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PreferenciaEntity.class.getPackage())
                .addPackage(PreferenciaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
        
    }
    
    @Test
    public void createPreferenciaTest() {
        
        PodamFactory factory = new PodamFactoryImpl();
        PreferenciaEntity newEntity = factory.manufacturePojo(PreferenciaEntity.class);
        
        PreferenciaEntity ve = ep.create(newEntity);
        
        
        Assert.assertNotNull(ve);
    }
    
    @Test
    public void findPreferenciaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PreferenciaEntity newEntity = factory.manufacturePojo(PreferenciaEntity.class);
        
        PreferenciaEntity ve = ep.create(newEntity);
        
        PreferenciaEntity entity = em.find(PreferenciaEntity.class, ve.getId());
        
        Assert.assertEquals(newEntity.getNombrePreferencia(), entity.getNombrePreferencia());
    
    }
    
    public List<PreferenciaEntity> setUp() {
        List<PreferenciaEntity> datos = new ArrayList<>(); 
        PodamFactory factory = new PodamFactoryImpl();
        int i = 0;
        PreferenciaEntity newEntity = factory.manufacturePojo(PreferenciaEntity.class);
        datos.add(ep.create(newEntity));
        while (i < 5) {
            datos.add(ep.create(newEntity));
            i++;
        }
        return datos;
    }
    
   @Test
    public void updateTest() {
        
        List<PreferenciaEntity> datos = setUp();
        PreferenciaEntity entity = datos.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PreferenciaEntity newEntity = factory.manufacturePojo(PreferenciaEntity.class);
        newEntity.setId(entity.getId());
        ep.update(newEntity);
        PreferenciaEntity resp = em.find(PreferenciaEntity.class, newEntity.getId());
        Assert.assertEquals(newEntity.getId(), resp.getId());
        
    }
}
