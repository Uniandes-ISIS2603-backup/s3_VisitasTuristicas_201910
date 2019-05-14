/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.test.logic;

import co.edu.uniandes.csw.turismo.ejb.CiudadLogic;
import co.edu.uniandes.csw.turismo.entities.CiudadEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.CiudadPersistence;
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
 * @author david Fonseca
 */

@RunWith(Arquillian.class)
public class CiudadLogicTest {
    
        private PodamFactory factory = new PodamFactoryImpl();

        @Inject
    private CiudadLogic paisLogic;
    @Inject
    private CiudadPersistence ep;
    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<CiudadEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CiudadEntity.class.getPackage())
                .addPackage(CiudadPersistence.class.getPackage())
                .addPackage(CiudadLogic.class.getPackage())
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
        em.createQuery("delete from CiudadEntity").executeUpdate();
    }

    private void insertData() {

        for (int i = 0; i < 3; i++) {
            CiudadEntity newCiudadEntity = factory.manufacturePojo(CiudadEntity.class);
            em.persist(newCiudadEntity);
            data.add(newCiudadEntity);
        }
    }
    
    
     @Test
    public void createCiudadTest() throws BusinessLogicException {

        CiudadEntity newCiudadEntity = factory.manufacturePojo(CiudadEntity.class);
        CiudadEntity result = ep.create(newCiudadEntity);

        Assert.assertNotNull(result);

        CiudadEntity entity = em.find(CiudadEntity.class, result.getId());

        Assert.assertEquals(newCiudadEntity.darNombre(), entity.darNombre());

    }


    
    @Test(expected = BusinessLogicException.class)
    public void createCiudadConNombreInvalidoTest() throws BusinessLogicException {
        CiudadEntity newEntity = factory.manufacturePojo(CiudadEntity.class);
        newEntity.actualizarNombre("");
        paisLogic.createCiudad(newEntity);
    }

    

    @Test
    public void updateCiudadTest() throws BusinessLogicException {
        CiudadEntity entity = data.get(0);
        entity.actualizarNombre("hola");
        CiudadEntity pojoEntity = factory.manufacturePojo(CiudadEntity.class);
        pojoEntity.actualizarNombre(entity.darNombre());
        pojoEntity.setId(entity.getId());
        
        paisLogic.updateCiudad(pojoEntity.getId(), pojoEntity);
        
        CiudadEntity resp = em.find(CiudadEntity.class, entity.getId());
        
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.darNombre(), resp.darNombre());
        Assert.assertEquals(pojoEntity.darPlanes(), resp.darPlanes());


    }
    
     @Test(expected = BusinessLogicException.class)
    public void updateCiudadConNombreVacioTest() throws BusinessLogicException {
        CiudadEntity entity = data.get(0);
        CiudadEntity pojoEntity = factory.manufacturePojo(CiudadEntity.class);
        pojoEntity.actualizarNombre("");
        paisLogic.updateCiudad(entity.getId(), pojoEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void updateCiudadConNombreNuloTest() throws BusinessLogicException {
        CiudadEntity entity = data.get(0);
        CiudadEntity pojoEntity = factory.manufacturePojo(CiudadEntity.class);
        pojoEntity.actualizarNombre(null);
        paisLogic.updateCiudad(entity.getId(), pojoEntity);
    }
}
