/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.test.persistence;

import co.edu.uniandes.csw.turismo.entities.ViajeEntity;
import co.edu.uniandes.csw.turismo.persistence.ViajePersistence;
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
 * @author Christer Osorio
 */
@RunWith(Arquillian.class)
public class ViajePersistenceTest {

    @Inject
    private ViajePersistence viajePersistence;
    @PersistenceContext
    private EntityManager em;
    @Inject
    UserTransaction utx;

    private final List<ViajeEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ViajeEntity.class.getPackage())
                .addPackage(ViajePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");

    }

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

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ViajeEntity entity = factory.manufacturePojo(ViajeEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from ViajeEntity").executeUpdate();
    }

    /**
     *
     * Prueba que el viaje haya quedado en la base de datos
     *
     */
    @Test
    public void createViajeTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ViajeEntity newViajeEntity = factory.manufacturePojo(ViajeEntity.class);
        ViajeEntity result = viajePersistence.create(newViajeEntity);

        Assert.assertNotNull(result);

        ViajeEntity entity = em.find(ViajeEntity.class, result.getId());

        Assert.assertEquals(newViajeEntity.getFechaInicio(), entity.getFechaInicio());
        Assert.assertEquals(newViajeEntity.getFechaFin(), entity.getFechaFin());

    }

    /**
     *
     * Prueba que el viaje se pueda encontrar en la base de datos
     *
     */
    @Test
    public void getViajeTest() {
        ViajeEntity entity = data.get(0);
        ViajeEntity newEntity = viajePersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getFechaInicio(), newEntity.getFechaInicio());
        Assert.assertEquals(entity.getFechaFin(), newEntity.getFechaFin());

    }

    /**
     *
     * Prueba que el viaje haya sido borrado la base de datos
     *
     */
    @Test
    public void deleteViajeTest() {
        ViajeEntity entity = data.get(0);
        viajePersistence.delete(entity.getId());
        ViajeEntity deleted = em.find(ViajeEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     *
     * Prueba que el viaje haya sido modificado en la base de datos
     *
     */
    @Test
    public void updateBookTest() {
        ViajeEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ViajeEntity newEntity = factory.manufacturePojo(ViajeEntity.class);

        newEntity.setId(entity.getId());

        viajePersistence.update(newEntity);

        ViajeEntity resp = em.find(ViajeEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getFechaInicio(), resp.getFechaInicio());
        Assert.assertEquals(newEntity.getFechaFin(), resp.getFechaFin());

    }
}
