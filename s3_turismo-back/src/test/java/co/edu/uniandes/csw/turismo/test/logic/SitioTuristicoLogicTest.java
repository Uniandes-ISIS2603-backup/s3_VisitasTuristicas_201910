/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.test.logic;

import co.edu.uniandes.csw.turismo.ejb.SitioTuristicoLogic;
import co.edu.uniandes.csw.turismo.entities.CiudadEntity;
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
    private SitioTuristicoLogic reviewLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

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
                .addPackage(SitioTuristicoLogic.class.getPackage())
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
        

        for (int i = 0; i < 3; i++) {
            CiudadEntity entity = factory.manufacturePojo(CiudadEntity.class);
            em.persist(entity);
            dataCiudad.add(entity);
        }

        for (int i = 0; i < 3; i++) {
            SitioTuristicoEntity entity = factory.manufacturePojo(SitioTuristicoEntity.class);
            entity.actualizarCiudad(dataCiudad.get(1));
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un SitioTuristico.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void createSitioTuristicoTest() throws BusinessLogicException {
        SitioTuristicoEntity newEntity = factory.manufacturePojo(SitioTuristicoEntity.class);
        newEntity.actualizarCiudad(dataCiudad.get(1));
        SitioTuristicoEntity result = reviewLogic.createSitioTuritico(dataCiudad.get(1).getId(), newEntity);
        Assert.assertNotNull(result);
        SitioTuristicoEntity entity = em.find(SitioTuristicoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.darNombre(), entity.darNombre());
        Assert.assertEquals(newEntity.darCiudad(), entity.darCiudad());
        Assert.assertEquals(newEntity.darTipo(), entity.darTipo());
    }

    /**
     * Prueba para consultar la lista de SitioTuristicos.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void getSitioTuristicosTest() throws BusinessLogicException {
        List<SitioTuristicoEntity> list = reviewLogic.getSitioTuriticos(dataCiudad.get(1).getId());
        Assert.assertEquals(data.size(), list.size());
        for (SitioTuristicoEntity entity : list) {
            boolean found = false;
            for (SitioTuristicoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un SitioTuristico.
     */
    @Test
    public void getSitioTuristicoTest() {
        SitioTuristicoEntity entity = data.get(0);
        SitioTuristicoEntity resultEntity = reviewLogic.getSitioTuritico(dataCiudad.get(1).getId(), entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(resultEntity.darNombre(), entity.darNombre());
        Assert.assertEquals(resultEntity.darCiudad(), entity.darCiudad());
        Assert.assertEquals(resultEntity.darTipo(), entity.darTipo());
    }

    /**
     * Prueba para actualizar un SitioTuristico.
     */
    @Test
    public void updateSitioTuristicoTest() {
        SitioTuristicoEntity entity = data.get(0);
        SitioTuristicoEntity pojoEntity = factory.manufacturePojo(SitioTuristicoEntity.class);

        pojoEntity.setId(entity.getId());

        reviewLogic.updateSitioTuritico(dataCiudad.get(1).getId(), pojoEntity);

        SitioTuristicoEntity resp = em.find(SitioTuristicoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.darNombre(), resp.darNombre());
        Assert.assertEquals(pojoEntity.darTipo(), resp.darTipo());
        Assert.assertEquals(pojoEntity.darCiudad(), resp.darCiudad());
    }

    /**
     * Prueba para eliminar un SitioTuristico.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteSitioTuristicoTest() throws BusinessLogicException {
        SitioTuristicoEntity entity = data.get(0);
        reviewLogic.deleteSitioTuritico(dataCiudad.get(1).getId(), entity.getId());
        SitioTuristicoEntity deleted = em.find(SitioTuristicoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para eliminarle un review a un book del cual no pertenece.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void deleteSitioTuristicoConCiudadNoAsociadoTest() throws BusinessLogicException {
        SitioTuristicoEntity entity = data.get(0);
        reviewLogic.deleteSitioTuritico(dataCiudad.get(0).getId(), entity.getId());
    }
}
