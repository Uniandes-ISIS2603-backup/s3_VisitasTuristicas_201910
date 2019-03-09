/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.test.logic;

import co.edu.uniandes.csw.turismo.ejb.SitioTuristicoLogic;
import co.edu.uniandes.csw.turismo.entities.SitioTuristicoEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.SitioTuristicoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author David Fonseca
 */

@RunWith(Arquillian.class)
public class SitioTuristicoLogicTest {
     private PodamFactory factory = new PodamFactoryImpl();

        @Inject
    private SitioTuristicoLogic sitioTuristicoLogic;
    @Inject
    private SitioTuristicoPersistence ep;
    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<SitioTuristicoEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SitioTuristicoEntity.class.getPackage())
                .addPackage(SitioTuristicoPersistence.class.getPackage())
                .addPackage(SitioTuristicoLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");

    }

    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    private void clearData() {
        em.createQuery("delete from SitioTuristicoEntity").executeUpdate();
    }

    private void insertData() {

        for (int i = 0; i < 3; i++) {
            SitioTuristicoEntity newSitioTuristicoEntity = factory.manufacturePojo(SitioTuristicoEntity.class);
            em.persist(newSitioTuristicoEntity);
            data.add(newSitioTuristicoEntity);
        }
    }
    
    
     @Test
    public void createSitioTuristicoTest() throws BusinessLogicException {

        SitioTuristicoEntity newSitioTuristicoEntity = factory.manufacturePojo(SitioTuristicoEntity.class);
        SitioTuristicoEntity result = ep.create(newSitioTuristicoEntity);

        Assert.assertNotNull(result);

        SitioTuristicoEntity entity = em.find(SitioTuristicoEntity.class, result.getId());

        Assert.assertEquals(newSitioTuristicoEntity.darNombre(), entity.darNombre());

    }

    @Test(expected = BusinessLogicException.class)
    public void createSitioTuristicoConMismoNombreTest() throws BusinessLogicException {

        SitioTuristicoEntity newSitioTuristicoEntity = factory.manufacturePojo(SitioTuristicoEntity.class);
        newSitioTuristicoEntity.actualizarNombre(data.get(0).darNombre());
        sitioTuristicoLogic.createSitio(newSitioTuristicoEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createSitioTuristicoConNombreInvalidoTest() throws BusinessLogicException {
        SitioTuristicoEntity newEntity = factory.manufacturePojo(SitioTuristicoEntity.class);
        newEntity.actualizarNombre("");
        sitioTuristicoLogic.createSitio(newEntity);
    }

    

    @Test
    public void updateSitioTuristicoTest() throws BusinessLogicException {
        SitioTuristicoEntity entity = data.get(0);
        entity.actualizarNombre("hola");
        SitioTuristicoEntity pojoEntity = factory.manufacturePojo(SitioTuristicoEntity.class);
        
        pojoEntity.setId(entity.getId());
        pojoEntity.actualizarNombre(entity.darNombre());
        
        sitioTuristicoLogic.updateSitio(pojoEntity.getId(), pojoEntity);
        
        SitioTuristicoEntity resp = em.find(SitioTuristicoEntity.class, entity.getId());
        
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.darNombre(), resp.darNombre());
        Assert.assertEquals(pojoEntity.darTipo(), resp.darTipo());


    }
    
     @Test(expected = BusinessLogicException.class)
    public void updateSitioTuristicoConNombreVacioTest() throws BusinessLogicException {
        SitioTuristicoEntity entity = data.get(0);
        SitioTuristicoEntity pojoEntity = factory.manufacturePojo(SitioTuristicoEntity.class);
        pojoEntity.actualizarNombre("");
        sitioTuristicoLogic.updateSitio(entity.getId(), pojoEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void updateSitioTuristicoConNombreNuloTest() throws BusinessLogicException {
        SitioTuristicoEntity entity = data.get(0);
        SitioTuristicoEntity pojoEntity = factory.manufacturePojo(SitioTuristicoEntity.class);
        pojoEntity.actualizarNombre(null);
        sitioTuristicoLogic.updateSitio(entity.getId(), pojoEntity);
    }
}
