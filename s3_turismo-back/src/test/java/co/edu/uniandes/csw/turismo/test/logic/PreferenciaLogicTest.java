/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.test.logic;

import co.edu.uniandes.csw.turismo.ejb.PreferenciaLogic;
import co.edu.uniandes.csw.turismo.entities.PreferenciaEntity;
import co.edu.uniandes.csw.turismo.entities.ViajeroEntity;

import co.edu.uniandes.csw.turismo.persistence.PreferenciaPersistence;
import static org.glassfish.pfl.basic.tools.argparser.ElementParser.factory;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
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
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class PreferenciaLogicTest {
    
    private final PodamFactory factory = new PodamFactoryImpl();
    @Inject
    private PreferenciaLogic reviewLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private final List<PreferenciaEntity> data = new ArrayList<>();

    private final List<ViajeroEntity> dataViajero = new ArrayList<>();


    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PreferenciaEntity.class.getPackage())
                .addPackage(PreferenciaLogic.class.getPackage())
                .addPackage(PreferenciaPersistence.class.getPackage())
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
        em.createQuery("delete from PreferenciaEntity").executeUpdate();
        em.createQuery("delete from ViajeroEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        

        for (int i = 0; i < 3; i++) {
            ViajeroEntity entity = factory.manufacturePojo(ViajeroEntity.class);
            em.persist(entity);
            dataViajero.add(entity);
        }

        for (int i = 0; i < 3; i++) {
            PreferenciaEntity entity = factory.manufacturePojo(PreferenciaEntity.class);
            entity.setViajero(dataViajero.get(1));
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Preferencia.
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test

    public void createPreferenciaTest() throws BusinessLogicException {
        PreferenciaEntity newEntity = factory.manufacturePojo(PreferenciaEntity.class);
        newEntity.setViajero(dataViajero.get(1));
        PreferenciaEntity result = reviewLogic.createPreferencia(dataViajero.get(1).getId(), newEntity);
        Assert.assertNotNull(result);
        PreferenciaEntity entity = em.find(PreferenciaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombrePreferencia(), entity.getNombrePreferencia());
        Assert.assertEquals(newEntity.getViajero(), entity.getViajero());
    }

    /**
     * Prueba para consultar la lista de Preferencias.
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void getPreferenciasTest() throws BusinessLogicException {
        List<PreferenciaEntity> list = reviewLogic.getPreferencias(dataViajero.get(1).getId());
        Assert.assertEquals(data.size(), list.size());
        for (PreferenciaEntity entity : list) {
            boolean found = false;
            for (PreferenciaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Preferencia.
     */
    @Test
    public void getPreferenciaTest() {
        PreferenciaEntity entity = data.get(0);
        PreferenciaEntity resultEntity = reviewLogic.getPreferencia(dataViajero.get(1).getId(), entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(resultEntity.getNombrePreferencia(), entity.getNombrePreferencia());
        Assert.assertEquals(resultEntity.getViajero(), entity.getViajero());
    }

    /**
     * Prueba para actualizar un Preferencia.
     */
    @Test
    public void updatePreferenciaTest() {
        PreferenciaEntity entity = data.get(0);
        PreferenciaEntity pojoEntity = factory.manufacturePojo(PreferenciaEntity.class);

        pojoEntity.setId(entity.getId());

        reviewLogic.updatePreferencia(dataViajero.get(1).getId(), pojoEntity);

        PreferenciaEntity resp = em.find(PreferenciaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombrePreferencia(), resp.getNombrePreferencia());
        Assert.assertEquals(pojoEntity.getViajero(), resp.getViajero());
    }

    /**
     * Prueba para eliminar un Preferencia.
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void deletePreferenciaTest() throws BusinessLogicException {
        PreferenciaEntity entity = data.get(0);
        reviewLogic.deletePreferencia(dataViajero.get(1).getId(), entity.getId());
        PreferenciaEntity deleted = em.find(PreferenciaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para eliminarle un review a un book del cual no pertenece.
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)

    public void deletePreferenciaConViajeroNoAsociadoTest() throws BusinessLogicException {
        PreferenciaEntity entity = data.get(0);
        reviewLogic.deletePreferencia(dataViajero.get(0).getId(), entity.getId());
    }
}
