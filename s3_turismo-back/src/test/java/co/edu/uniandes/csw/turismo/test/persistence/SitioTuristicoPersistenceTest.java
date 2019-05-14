/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.test.persistence;

import co.edu.uniandes.csw.turismo.entities.CiudadEntity;
import co.edu.uniandes.csw.turismo.entities.SitioTuristicoEntity;
import co.edu.uniandes.csw.turismo.entities.SitioTuristicoEntity;
import co.edu.uniandes.csw.turismo.persistence.SitioTuristicoPersistence;
import co.edu.uniandes.csw.turismo.persistence.SitioTuristicoPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
public class SitioTuristicoPersistenceTest {
    
    
    
    
        @Inject
    private SitioTuristicoPersistence reviewPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<SitioTuristicoEntity> data = new ArrayList<SitioTuristicoEntity>();
	
    private List<CiudadEntity> dataCiudad = new ArrayList<CiudadEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SitioTuristicoEntity.class.getPackage())
                .addPackage(SitioTuristicoPersistence.class.getPackage())
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
        em.createQuery("delete from SitioTuristicoEntity").executeUpdate();
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
            dataCiudad.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            SitioTuristicoEntity entity = factory.manufacturePojo(SitioTuristicoEntity.class);
            if (i == 0) {
                entity.actualizarCiudad(dataCiudad.get(0));
            }
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un SitioTuristico.
     */
    @Test
    public void createSitioTuristicoTest() {

        PodamFactory factory = new PodamFactoryImpl();
        SitioTuristicoEntity newEntity = factory.manufacturePojo(SitioTuristicoEntity.class);
        SitioTuristicoEntity result = reviewPersistence.create(newEntity);

        Assert.assertNotNull(result);

        SitioTuristicoEntity entity = em.find(SitioTuristicoEntity.class, result.getId());

        Assert.assertEquals(newEntity.darNombre(), entity.darNombre());
        Assert.assertEquals(newEntity.darCiudad(), entity.darCiudad());
        Assert.assertEquals(newEntity.darTipo(), entity.darTipo());
    }

    /**
     * Prueba para consultar un SitioTuristico.
     */
    @Test
    public void getSitioTuristicoTest() {
        SitioTuristicoEntity entity = data.get(0);
        SitioTuristicoEntity newEntity = reviewPersistence.find(dataCiudad.get(0).getId(), entity.getId());
        Assert.assertNotNull(newEntity);
         Assert.assertEquals(newEntity.darNombre(), entity.darNombre());
        Assert.assertEquals(newEntity.darCiudad(), entity.darCiudad());
        Assert.assertEquals(newEntity.darTipo(), entity.darTipo());
    }

    /**
     * Prueba para eliminar un SitioTuristico.
     */
    @Test
    public void deleteSitioTuristicoTest() {
        SitioTuristicoEntity entity = data.get(0);
        reviewPersistence.delete(entity.getId());
        SitioTuristicoEntity deleted = em.find(SitioTuristicoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un SitioTuristico.
     */
    @Test
    public void updateSitioTuristicoTest() {
        SitioTuristicoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        SitioTuristicoEntity newEntity = factory.manufacturePojo(SitioTuristicoEntity.class);

        newEntity.setId(entity.getId());

        reviewPersistence.update(newEntity);

        SitioTuristicoEntity resp = em.find(SitioTuristicoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.darNombre(), entity.darNombre());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.darTipo(), entity.darTipo());
    }
}
