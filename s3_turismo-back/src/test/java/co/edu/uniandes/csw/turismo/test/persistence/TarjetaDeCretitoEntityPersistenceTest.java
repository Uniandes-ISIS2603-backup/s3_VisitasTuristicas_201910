/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.test.persistence;

import co.edu.uniandes.csw.turismo.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.turismo.persistence.TarjetaDeCreditoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.junit.Assert;
import org.junit.Before;
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
    private TarjetaDeCreditoPersistence tarjetaPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<TarjetaDeCreditoEntity> data = new ArrayList<TarjetaDeCreditoEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TarjetaDeCreditoEntity.class.getPackage())
                .addPackage(TarjetaDeCreditoPersistence.class.getPackage())
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

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from TarjetaDeCreditoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            TarjetaDeCreditoEntity entity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un TarjetaDeCredito.
     */
    @Test
    public void createTarjetaDeCreditoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        TarjetaDeCreditoEntity newEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        TarjetaDeCreditoEntity result = tarjetaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        TarjetaDeCreditoEntity entity = em.find(TarjetaDeCreditoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getBanco(), entity.getBanco());
        Assert.assertEquals(newEntity.getCodigoSeguridad(), entity.getCodigoSeguridad());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba para consultar la lista de TarjetaDeCreditos.
     */
    @Test
    public void getTarjetaDeCreditosTest() {
        List<TarjetaDeCreditoEntity> list = tarjetaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (TarjetaDeCreditoEntity ent : list) {
            boolean found = false;
            for (TarjetaDeCreditoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un TarjetaDeCredito.
     */
    @Test
    public void getTarjetaDeCreditoTest() {
        TarjetaDeCreditoEntity entity = data.get(0);
        TarjetaDeCreditoEntity newEntity = tarjetaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getCodigoSeguridad(), newEntity.getCodigoSeguridad());
        Assert.assertEquals(entity.getBanco(), newEntity.getBanco());
        Assert.assertEquals(entity.getNumero(), newEntity.getNumero());
    }

    /**
     * Prueba para eliminar un TarjetaDeCredito.
     */
    @Test
    public void deleteTarjetaDeCreditoTest() {
        TarjetaDeCreditoEntity entity = data.get(0);
        tarjetaPersistence.delete(entity.getId());
        TarjetaDeCreditoEntity deleted = em.find(TarjetaDeCreditoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un TarjetaDeCredito.
     */
    @Test
    public void updateTarjetaDeCreditoTest() {
        TarjetaDeCreditoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TarjetaDeCreditoEntity newEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);

        newEntity.setId(entity.getId());

        tarjetaPersistence.update(newEntity);

        TarjetaDeCreditoEntity resp = em.find(TarjetaDeCreditoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNumero(), resp.getNumero());
        Assert.assertEquals(newEntity.getCodigoSeguridad(), resp.getCodigoSeguridad());
        Assert.assertEquals(newEntity.getBanco(), resp.getBanco());
    }

    /**
     * Prueba para consultasr una tarjeta por su numero.
     */
    @Test
    public void findTajretaByNumeroTest() {
        TarjetaDeCreditoEntity entity = data.get(0);
        TarjetaDeCreditoEntity newEntity;
        newEntity = tarjetaPersistence.getByNumber(entity.getNumero());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNumero(), newEntity.getNumero());

        newEntity = tarjetaPersistence.getByNumber(null);
        Assert.assertNull(newEntity);
    }
}
