/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.test.persistence;
import co.edu.uniandes.csw.turismo.entities.PaisEntity;
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
public class PaisTest {
     
    @Inject
    private PaisPersistence ciudadPersistence;

    @PersistenceContext
   private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<PaisEntity> data = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PaisEntity.class.getPackage ())
                .addPackage(PaisPersistence.class.getPackage())
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
        em.createQuery("delete from PaisEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PaisEntity entity = factory.manufacturePojo(PaisEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createPaisTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        PaisEntity newEntity = factory.manufacturePojo(PaisEntity.class);
        PaisEntity result = ciudadPersistence.create(newEntity);
        Assert.assertNotNull(result);
        PaisEntity entity = em.find(PaisEntity.class, result.getId());
        Assert.assertEquals(newEntity.darNombre(), entity.darNombre());
    }
    
     /**
     * Prueba para consultar la lista de ciudades.
    */
   
    @Test
    public void getPaisesTest() {
        List<PaisEntity> list = ciudadPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        System.out.println(data.size() + list.size());
        for(PaisEntity ent : list) {
            boolean found = false;
            for (PaisEntity entity : data) {
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
    public void getPaisTest() {
        PaisEntity entity = data.get(0);
        PaisEntity newEntity =ciudadPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.darNombre(), newEntity.darNombre());

    }
    
    /**
     * Prueba para actualizar una ciudad.
     */
    @Test
    public void updatePaisTest() {
        PaisEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PaisEntity newEntity = factory.manufacturePojo(PaisEntity.class);

        newEntity.setId(entity.getId());

        ciudadPersistence.update(newEntity);

        PaisEntity resp = em.find(PaisEntity.class, entity.getId());

        Assert.assertEquals(newEntity.darNombre(), resp.darNombre());

    }
    
    /**
     * Prueba para consultasr una ciudad por nombre.
     */
   // ERROR CORREGIR
    /*
    @Test
    public void findPaisByNameTest() {
        PaisEntity entity = data.get(0);
        PaisEntity newEntity = ciudadPersistence.findByName(entity.darNombre());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.darNombre(), newEntity.darNombre());

        newEntity = ciudadPersistence.findByName(null);
        Assert.assertNull(newEntity);
    }
*/
}
