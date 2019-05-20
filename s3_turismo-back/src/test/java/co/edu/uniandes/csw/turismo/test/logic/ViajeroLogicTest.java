/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.test.logic;

import co.edu.uniandes.csw.turismo.ejb.ViajeroLogic;
import co.edu.uniandes.csw.turismo.entities.ViajeroEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.ViajeroPersistence;
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
public class ViajeroLogicTest {
    
    @Inject
    private ViajeroLogic viajeroLogic;
    
     
    @PersistenceContext
    private EntityManager em;
    
    PodamFactory factory = new PodamFactoryImpl();
    
    @Deployment
    public static JavaArchive createDeployment() {
        
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ViajeroEntity.class.getPackage())
                .addPackage(ViajeroLogic.class.getPackage())
                .addPackage(ViajeroPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
        
    }
    
    @Test
    public void createViajerotest() throws BusinessLogicException {
        ViajeroEntity newEntity = factory.manufacturePojo(ViajeroEntity.class);
        ViajeroEntity result = viajeroLogic.createViajero(newEntity);
        Assert.assertNotNull(result);
        ViajeroEntity ent = em.find(ViajeroEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), ent.getId());
        Assert.assertEquals(newEntity.getNombreUsuario(), ent.getNombreUsuario());
        Assert.assertEquals(newEntity.getIdioma(), ent.getIdioma());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createEditorialConNombreVacioTest() throws BusinessLogicException {
        ViajeroEntity newEntity = factory.manufacturePojo(ViajeroEntity.class);
        //ViajeroEntity newerEntity = factory.manufacturePojo(ViajeroEntity.class);
        newEntity.setNombreUsuario("");
        viajeroLogic.createViajero(newEntity);
    }
    
    @Test
    public void getViajeroTest() throws BusinessLogicException {
        ViajeroEntity newEntity = factory.manufacturePojo(ViajeroEntity.class);
        newEntity = viajeroLogic.createViajero(newEntity);
        ViajeroEntity ent = em.find(ViajeroEntity.class, newEntity.getId());
        Assert.assertNotNull(ent);
        Assert.assertEquals(newEntity.getNombreUsuario(), ent.getNombreUsuario());
    }
    
//    @Test(expected = BusinessLogicException.class)
//    public void getViajeroNoExisteTest() throws BusinessLogicException {
//        ViajeroEntity newEntity = factory.manufacturePojo(ViajeroEntity.class);
//        newEntity = viajeroLogic.createViajero(newEntity);
//        Long a = newEntity.getId();
//        viajeroLogic.deleteViajero(newEntity.getId());
//        em.find(ViajeroEntity.class, a);
//    }
    
//    @Test
//    public void updateViajeroTest() throws BusinessLogicException {
//        ViajeroEntity newEntity = factory.manufacturePojo(ViajeroEntity.class);
//        newEntity = viajeroLogic.createViajero(newEntity);
//        //Long a = newEntity.getId();
//        ViajeroEntity newerEntity = factory.manufacturePojo(ViajeroEntity.class);
//        //newEntity.setId(newerEntity.getId());
//        newerEntity.setId(newEntity.getId());
//        viajeroLogic.updateViajero(newEntity.getId(), newerEntity);
//        ViajeroEntity testing = em.find(ViajeroEntity.class, newEntity.getId());
//        Assert.assertEquals(testing.getNombreUsuario(), newerEntity.getNombreUsuario());
//        Assert.assertEquals(newEntity.getIdioma(), newerEntity.getIdioma());
//    }
    
    @Test
    public void deleteViajeroTest() throws BusinessLogicException {
        ViajeroEntity newEntity = factory.manufacturePojo(ViajeroEntity.class);
        newEntity = viajeroLogic.createViajero(newEntity);
        viajeroLogic.deleteViajero(newEntity.getId());
        ViajeroEntity q = em.find(ViajeroEntity.class, newEntity.getId());
        Assert.assertNull(q);
    }
}
