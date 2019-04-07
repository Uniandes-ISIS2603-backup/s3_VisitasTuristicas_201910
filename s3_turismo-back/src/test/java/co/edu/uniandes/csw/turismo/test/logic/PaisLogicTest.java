/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.test.logic;

import co.edu.uniandes.csw.turismo.ejb.PaisLogic;
import co.edu.uniandes.csw.turismo.ejb.PaisLogic;
import co.edu.uniandes.csw.turismo.entities.PaisEntity;
import co.edu.uniandes.csw.turismo.entities.PaisEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.PaisPersistence;
import co.edu.uniandes.csw.turismo.persistence.PaisPersistence;
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
public class PaisLogicTest {
    
        private PodamFactory factory = new PodamFactoryImpl();

        @Inject
    private PaisLogic paisLogic;
    @Inject
    private PaisPersistence ep;
    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<PaisEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PaisEntity.class.getPackage())
                .addPackage(PaisPersistence.class.getPackage())
                .addPackage(PaisLogic.class.getPackage())
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
        em.createQuery("delete from PaisEntity").executeUpdate();
    }

    private void insertData() {

        for (int i = 0; i < 3; i++) {
            PaisEntity newPaisEntity = factory.manufacturePojo(PaisEntity.class);
            em.persist(newPaisEntity);
            data.add(newPaisEntity);
        }
    }
    
    
     @Test
    public void createPaisTest() throws BusinessLogicException {

        PaisEntity newPaisEntity = factory.manufacturePojo(PaisEntity.class);
        PaisEntity result = ep.create(newPaisEntity);

        Assert.assertNotNull(result);

        PaisEntity entity = em.find(PaisEntity.class, result.getId());

        Assert.assertEquals(newPaisEntity.getNombre(), entity.getNombre());

    }

    @Test(expected = BusinessLogicException.class)
    public void createPaisConMismoNombreTest() throws BusinessLogicException {

        PaisEntity newPaisEntity = factory.manufacturePojo(PaisEntity.class);
        newPaisEntity.setNombre(data.get(0).getNombre());
        paisLogic.createPais(newPaisEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createPaisConNombreInvalidoTest() throws BusinessLogicException {
        PaisEntity newEntity = factory.manufacturePojo(PaisEntity.class);
        newEntity.setNombre("");
        paisLogic.createPais(newEntity);
    }

    

    @Test
    public void updatePaisTest() throws BusinessLogicException {
        PaisEntity entity = data.get(0);
        entity.setNombre("hola");
        PaisEntity pojoEntity = factory.manufacturePojo(PaisEntity.class);
        
        pojoEntity.setId(entity.getId());
        pojoEntity.setNombre(entity.getNombre());
        
        paisLogic.updatePais(pojoEntity.getId(), pojoEntity);
        
        PaisEntity resp = em.find(PaisEntity.class, entity.getId());
        
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(pojoEntity.getPlanesTuristicos(), resp.getPlanesTuristicos());


    }
    
     @Test(expected = BusinessLogicException.class)
    public void updatePaisConNombreVacioTest() throws BusinessLogicException {
        PaisEntity entity = data.get(0);
        PaisEntity pojoEntity = factory.manufacturePojo(PaisEntity.class);
        pojoEntity.setNombre("");
        paisLogic.updatePais(entity.getId(), pojoEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void updatePaisConNombreNuloTest() throws BusinessLogicException {
        PaisEntity entity = data.get(0);
        PaisEntity pojoEntity = factory.manufacturePojo(PaisEntity.class);
        pojoEntity.setNombre(null);
        paisLogic.updatePais(entity.getId(), pojoEntity);
    }
    
   
    
   
}
