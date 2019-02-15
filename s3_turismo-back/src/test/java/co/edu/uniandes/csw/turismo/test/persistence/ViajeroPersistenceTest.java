/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.test.persistence;

import co.edu.uniandes.csw.turismo.entities.ViajeroEntity;
import co.edu.uniandes.csw.turismo.persistence.ViajeroPersistence;
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
public class ViajeroPersistenceTest {
    
    @Inject
    private ViajeroPersistence ep;
    
    @PersistenceContext
    private EntityManager em;
    
    
    
    @Deployment
    public static JavaArchive createDeployment() {
        
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ViajeroEntity.class.getPackage())
                .addPackage(ViajeroPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
        
    }
    
    @Test
    public void createViajeroTest() {
        
        PodamFactory factory = new PodamFactoryImpl();
        ViajeroEntity newEntity = factory.manufacturePojo(ViajeroEntity.class);
        
        ViajeroEntity ve = ep.create(newEntity);
        
        
        Assert.assertNotNull(ve);
        
//        ViajeroEntity entity = em.find(ViajeroEntity.class, ve.getId());
        
//        Assert.assertEquals(newEntity.getNombreUsuario(), entity.getNombreUsuario());
//        Assert.assertEquals(newEntity.getCodigoUnico(), entity.getCodigoUnico());
//        Assert.assertEquals(newEntity.getIdioma(), entity.getIdioma());
//        Assert.assertEquals(newEntity.getTipoDeUsuario(), entity.getTipoDeUsuario());
//        Assert.assertEquals(newEntity.getCantidadPlanes(), entity.getCantidadPlanes());
//        Assert.assertEquals(newEntity.getInformacionPersonal(), entity.getInformacionPersonal());
    }
    
    @Test
    public void findViajeroTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ViajeroEntity newEntity = factory.manufacturePojo(ViajeroEntity.class);
        
        ViajeroEntity ve = ep.create(newEntity);
        
        ViajeroEntity entity = em.find(ViajeroEntity.class, ve.getId());
        
        Assert.assertEquals(newEntity.getNombreUsuario(), entity.getNombreUsuario());
        Assert.assertEquals(newEntity.getCodigoUnico(), entity.getCodigoUnico());
        Assert.assertEquals(newEntity.getIdioma(), entity.getIdioma());
        Assert.assertEquals(newEntity.getTipoDeUsuario(), entity.getTipoDeUsuario());
        Assert.assertEquals(newEntity.getCantidadPlanes(), entity.getCantidadPlanes());
        Assert.assertEquals(newEntity.getInformacionPersonal(), entity.getInformacionPersonal());
    
    }
    
    
   
    public List<ViajeroEntity> setUp() {
        List<ViajeroEntity> datos = new ArrayList<ViajeroEntity>(); 
        PodamFactory factory = new PodamFactoryImpl();
        int i = 0;
        ViajeroEntity newEntity = factory.manufacturePojo(ViajeroEntity.class);
        datos.add(ep.create(newEntity));
        while (i < 5) {
            datos.add(ep.create(newEntity));
            i++;
        }
        return datos;
    }
    @Test
    public void findAllViajerosTest() {
        List<ViajeroEntity> list = ep.findAll();
        Assert.assertNotNull(list);
        //Assert.assertEquals((list.size() - 1), datos.size());
    }
    
    @Test
    public void deleteTest() {
        List<ViajeroEntity> datos =  setUp();
        ViajeroEntity ent = datos.get(0);
        ep.delete(ent.getId());
        ViajeroEntity deleted = em.find(ViajeroEntity.class, ent.getId());
        Assert.assertNull(deleted);
    }
    
//    @Test
//    public void deleteInfoTest() {
//        //List<ViajeroEntity> datos =  setUp();
//        //ViajeroEntity entity = datos.get(0);
//        PodamFactory factory = new PodamFactoryImpl();
//        ViajeroEntity newEntity = factory.manufacturePojo(ViajeroEntity.class);
//        newEntity.setInformacionPersonal("");
//        ep.update(newEntity);
//        ViajeroEntity s = em.find(ViajeroEntity.class, newEntity.getId());
//        Assert.assertEquals(s.getInformacionPersonal(), "");
//    }
    
    @Test
    public void updateTest() {
        List<ViajeroEntity> datos = setUp();
        ViajeroEntity entity = datos.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ViajeroEntity newEntity = factory.manufacturePojo(ViajeroEntity.class);
        newEntity.setId(entity.getId());
        ep.update(newEntity);
        ViajeroEntity resp = em.find(ViajeroEntity.class, newEntity.getId());
        Assert.assertEquals(newEntity.getId(), resp.getId());
        
    }
    
//    @Test
//    public void updateInfoTest() {
//        List<ViajeroEntity> datos = setUp();
//        ViajeroEntity entity = datos.get(0);
//        PodamFactory factory = new PodamFactoryImpl();
//        ViajeroEntity newEntity = factory.manufacturePojo(ViajeroEntity.class);
//        newEntity.setInformacionPersonal(entity.getInformacionPersonal());
//        ep.update(newEntity);
//        ViajeroEntity resp = em.find(ViajeroEntity.class, newEntity.getId());
//        Assert.assertEquals(newEntity.getInformacionPersonal(), resp.getInformacionPersonal());
        
//    }
}
