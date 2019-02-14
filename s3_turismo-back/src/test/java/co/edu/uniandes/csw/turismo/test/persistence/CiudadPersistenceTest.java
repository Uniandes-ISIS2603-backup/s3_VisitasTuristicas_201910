/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.test.persistence;

import co.edu.uniandes.csw.turismo.entities.CiudadEntity;
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
 * @author David Fonseca
 */

@RunWith(Arquillian.class)
public class CiudadPersistenceTest {
    
    
    @Inject
    private CiudadPersistence ciudadPersistence;

    @PersistenceContext
   private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<CiudadEntity> data = new ArrayList<CiudadEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CiudadEntity.class.getPackage ())
                .addPackage(CiudadPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
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

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CiudadEntity entity = factory.manufacturePojo(CiudadEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createCiudadTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        CiudadEntity newEntity = factory.manufacturePojo(CiudadEntity.class);
        CiudadEntity result = ciudadPersistence.create(newEntity);
        Assert.assertNotNull(result);
        CiudadEntity entity = em.find(CiudadEntity.class, result.getId());
        Assert.assertEquals(newEntity.darNombre(), entity.darNombre());
    }
    
     /**
     * Prueba para consultar la lista de ciudades.
    */
   /*
    @Test
    public void getCiudadesTest() {
        List<CiudadEntity> list = ciudadPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        System.out.println(data.size() + list.size());
        for (CiudadEntity ent : list) {
            boolean found = false;
            for (CiudadEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    
    /**
     * Prueba para consultar una ciudad.
     */
    @Test
    public void getCiudadTest() {
        CiudadEntity entity = data.get(0);
        CiudadEntity newEntity =ciudadPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.darNombre(), newEntity.darNombre());

    }
    
    /**
     * Prueba para actualizar una ciudad.
     */
    @Test
    public void updateCiudadTest() {
        CiudadEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CiudadEntity newEntity = factory.manufacturePojo(CiudadEntity.class);

        newEntity.setId(entity.getId());

        ciudadPersistence.update(newEntity);

        CiudadEntity resp = em.find(CiudadEntity.class, entity.getId());

        Assert.assertEquals(newEntity.darNombre(), resp.darNombre());

    }
    
    /**
     * Prueba para consultasr una ciudad por nombre.
     */
   
    /*
    @Test
    public void findCiudadByNameTest() {
        CiudadEntity entity = data.get(0);
        CiudadEntity newEntity = ciudadPersistence.findByName(entity.darNombre());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.darNombre(), newEntity.darNombre());

        newEntity = ciudadPersistence.findByName(null);
        Assert.assertNull(newEntity);
    }
*/
    
}
