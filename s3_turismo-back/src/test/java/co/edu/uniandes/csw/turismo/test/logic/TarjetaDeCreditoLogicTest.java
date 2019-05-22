/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.test.logic;

import co.edu.uniandes.csw.turismo.ejb.TarjetaDeCreditoLogic;
import co.edu.uniandes.csw.turismo.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.turismo.entities.ViajeroEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
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
public class TarjetaDeCreditoLogicTest {

  private final PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private TarjetaDeCreditoLogic reviewLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private final List<TarjetaDeCreditoEntity> data = new ArrayList<>();

    private final List<ViajeroEntity> dataViajero = new ArrayList<>();


    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TarjetaDeCreditoEntity.class.getPackage())
                .addPackage(TarjetaDeCreditoLogic.class.getPackage())
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
            TarjetaDeCreditoEntity entity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
            entity.setViajero(dataViajero.get(1));
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un TarjetaDeCredito.
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test

    public void createTarjetaDeCreditoTest() throws BusinessLogicException {
        TarjetaDeCreditoEntity newEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        newEntity.setViajero(dataViajero.get(1));
        TarjetaDeCreditoEntity result = reviewLogic.createTarjetaDeCredito(dataViajero.get(1).getId(), newEntity);
        Assert.assertNotNull(result);
        TarjetaDeCreditoEntity entity = em.find(TarjetaDeCreditoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getBanco(), entity.getBanco());
        Assert.assertEquals(newEntity.getViajero(), entity.getViajero());
        Assert.assertEquals(newEntity.getCodigoSeguridad(), entity.getCodigoSeguridad());
    }

    /**
     * Prueba para consultar la lista de TarjetaDeCreditos.
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void getTarjetaDeCreditosTest() throws BusinessLogicException {
        List<TarjetaDeCreditoEntity> list = reviewLogic.getTarjetaDeCreditos(dataViajero.get(1).getId());
        Assert.assertEquals(data.size(), list.size());
        for (TarjetaDeCreditoEntity entity : list) {
            boolean found = false;
            for (TarjetaDeCreditoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
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
        TarjetaDeCreditoEntity resultEntity = reviewLogic.getTarjetaDeCredito(dataViajero.get(1).getId(), entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(resultEntity.getBanco(), entity.getBanco());
        Assert.assertEquals(resultEntity.getViajero(), entity.getViajero());
        Assert.assertEquals(resultEntity.getNumero(), entity.getNumero());
    }

    /**
     * Prueba para actualizar un TarjetaDeCredito.
     */
    @Test
    public void updateTarjetaDeCreditoTest() {
        TarjetaDeCreditoEntity entity = data.get(0);
        TarjetaDeCreditoEntity pojoEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);

        pojoEntity.setId(entity.getId());

        reviewLogic.updateTarjetaDeCredito(dataViajero.get(1).getId(), pojoEntity);

        TarjetaDeCreditoEntity resp = em.find(TarjetaDeCreditoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getBanco(), resp.getBanco());
        Assert.assertEquals(pojoEntity.getNumero(), resp.getNumero());
        Assert.assertEquals(pojoEntity.getViajero(), resp.getViajero());
    }

    /**
     * Prueba para eliminar un TarjetaDeCredito.
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void deleteTarjetaDeCreditoTest() throws BusinessLogicException {
        TarjetaDeCreditoEntity entity = data.get(0);
        reviewLogic.deleteTarjetaDeCredito(dataViajero.get(1).getId(), entity.getId());
        TarjetaDeCreditoEntity deleted = em.find(TarjetaDeCreditoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para eliminarle un review a un book del cual no pertenece.
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)

    public void deleteTarjetaDeCreditoConViajeroNoAsociadoTest() throws BusinessLogicException {
        TarjetaDeCreditoEntity entity = data.get(0);
        reviewLogic.deleteTarjetaDeCredito(dataViajero.get(0).getId(), entity.getId());
    }


    
     
 
}
