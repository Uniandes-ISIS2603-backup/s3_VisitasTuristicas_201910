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

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private TarjetaDeCreditoLogic tarjetaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<TarjetaDeCreditoEntity> data = new ArrayList<TarjetaDeCreditoEntity>();

    private List<ViajeroEntity> editorialData = new ArrayList();

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
        em.createQuery("delete from AuthorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ViajeroEntity editorial = factory.manufacturePojo(ViajeroEntity.class);
            em.persist(editorial);
            editorialData.add(editorial);
        }
        for (int i = 0; i < 3; i++) {
            TarjetaDeCreditoEntity entity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
            entity.setViajero(editorialData.get(0));

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear una TarjetaDeCredito
     *
     * @throws co.edu.uniandes.csw.tarjetastore.exceptions.BusinessLogicException
     */
    @Test
    public void createTarjetaDeCreditoTest() throws BusinessLogicException {
        TarjetaDeCreditoEntity newEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        newEntity.setViajero(editorialData.get(0));
        TarjetaDeCreditoEntity result = tarjetaLogic.createTarjetaDeCredito(newEntity);
        Assert.assertNotNull(result);
        newEntity.setCodigoSeguridad(691);
        TarjetaDeCreditoEntity entity = em.find(TarjetaDeCreditoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getBanco(), entity.getBanco());
        Assert.assertEquals(newEntity.getNumero(), entity.getNumero());
        Assert.assertEquals(newEntity.getCodigoSeguridad(), entity.getCodigoSeguridad());
    }

    /**
     * Prueba para crear una TarjetaDeCredito con Numero inválido
     *
     * @throws co.edu.uniandes.csw.tarjetastore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createTarjetaDeCreditoTestConNumeroInvalido() throws BusinessLogicException {
        TarjetaDeCreditoEntity newEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        newEntity.setViajero(editorialData.get(0));
        newEntity.setCodigoSeguridad(691);
        newEntity.setNumero("");
        tarjetaLogic.createTarjetaDeCredito(newEntity);
    }

    /**
     * Prueba para crear un TarjetaDeCredito con Numero inválido
     *
     * @throws co.edu.uniandes.csw.tarjetastore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createTarjetaDeCreditoTestConNumeroInvalido2() throws BusinessLogicException {
        TarjetaDeCreditoEntity newEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        newEntity.setViajero(editorialData.get(0));
        newEntity.setCodigoSeguridad(691);
        newEntity.setNumero(null);
        tarjetaLogic.createTarjetaDeCredito(newEntity);
    }

    /**
     * Prueba para crear un TarjetaDeCredito con Codigo de seguridad inválido
     *
     * @throws co.edu.uniandes.csw.tarjetastore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createTarjetaDeCreditoTestConCodigoInvalido() throws BusinessLogicException {
        TarjetaDeCreditoEntity newEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        newEntity.setViajero(editorialData.get(0));
        newEntity.setCodigoSeguridad(-1);
        tarjetaLogic.createTarjetaDeCredito(newEntity);
    }

    /**
     * Prueba para crear un TarjetaDeCredito con Numero existente.
     *
     * @throws co.edu.uniandes.csw.tarjetastore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createTarjetaDeCreditoTestConISBNExistente() throws BusinessLogicException {
        TarjetaDeCreditoEntity newEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        newEntity.setViajero(editorialData.get(0));
        newEntity.setNumero(data.get(0).getNumero());
        tarjetaLogic.createTarjetaDeCredito(newEntity);
    }

    /**
     * Prueba para crear un TarjetaDeCredito con un viajero que no existe.
     *
     * @throws co.edu.uniandes.csw.tarjetastore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createTarjetaDeCreditoTestConViajeroInexistente() throws BusinessLogicException {
        TarjetaDeCreditoEntity newEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        ViajeroEntity editorialEntity = new ViajeroEntity();
        editorialEntity.setId(Long.MIN_VALUE);
        newEntity.setViajero(editorialEntity);
        tarjetaLogic.createTarjetaDeCredito(newEntity);
    }

    /**
     * Prueba para crear un TarjetaDeCredito con viajero en null.
     *
     * @throws co.edu.uniandes.csw.tarjetastore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createTarjetaDeCreditoTestConNullViajero() throws BusinessLogicException {
        TarjetaDeCreditoEntity newEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        newEntity.setViajero(null);
        tarjetaLogic.createTarjetaDeCredito(newEntity);
    }

    /**
     * Prueba para consultar la lista de TarjetaDeCreditos.
     */
    @Test
    public void getTarjetaDeCreditosTest() {
        List<TarjetaDeCreditoEntity> list = tarjetaLogic.getTarjetaDeCreditos();
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
     * Prueba para consultar una TarjetaDeCredito.
     */
    @Test
    public void getTarjetaDeCreditoTest() {
        TarjetaDeCreditoEntity entity = data.get(0);
        TarjetaDeCreditoEntity resultEntity = tarjetaLogic.getTarjetaDeCredito(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(resultEntity.getId(), entity.getId());
        Assert.assertEquals(resultEntity.getBanco(), entity.getBanco());
        Assert.assertEquals(resultEntity.getNumero(), entity.getNumero());
        Assert.assertEquals(resultEntity.getCodigoSeguridad(), entity.getCodigoSeguridad());
    }

    /**
     * Prueba para actualizar una TarjetaDeCredito.
     *
     * @throws co.edu.uniandes.csw.tarjetastore.exceptions.BusinessLogicException
     */
    @Test
    public void updateTarjetaDeCreditoTest() throws BusinessLogicException {
        TarjetaDeCreditoEntity entity = data.get(0);
        TarjetaDeCreditoEntity pojoEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        pojoEntity.setId(entity.getId());
        tarjetaLogic.updateTarjetaDeCredito(pojoEntity.getId(), pojoEntity);
        TarjetaDeCreditoEntity resp = em.find(TarjetaDeCreditoEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getBanco(), resp.getBanco());
        Assert.assertEquals(pojoEntity.getNumero(), resp.getNumero());
        Assert.assertEquals(pojoEntity.getCodigoSeguridad(), resp.getCodigoSeguridad());
    }

    /**
     * Prueba para actualizar una TarjetaDeCredito con numero inválido.
     *
     * @throws co.edu.uniandes.csw.tarjetastore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateTarjetaDeCreditoConNumeroInvalidoTest() throws BusinessLogicException {
        TarjetaDeCreditoEntity entity = data.get(0);
        TarjetaDeCreditoEntity pojoEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        pojoEntity.setNumero("");
        pojoEntity.setId(entity.getId());
        tarjetaLogic.updateTarjetaDeCredito(pojoEntity.getId(), pojoEntity);
    }

    /**
     * Prueba para actualizar una TarjetaDeCredito con numero inválido.
     *
     * @throws co.edu.uniandes.csw.tarjetastore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateTarjetaDeCreditoConNumeroInvalidoTest2() throws BusinessLogicException {
        TarjetaDeCreditoEntity entity = data.get(0);
        TarjetaDeCreditoEntity pojoEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        pojoEntity.setNumero(null);
        pojoEntity.setId(entity.getId());
        tarjetaLogic.updateTarjetaDeCredito(pojoEntity.getId(), pojoEntity);
    }
    
        /**
     * Prueba para actualizar un TarjetaDeCredito con numero inválido.
     *
     * @throws co.edu.uniandes.csw.tarjetastore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateTarjetaDeCreditoConCodigoInvalidoTest() throws BusinessLogicException {
        TarjetaDeCreditoEntity entity = data.get(0);
        TarjetaDeCreditoEntity pojoEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        pojoEntity.setCodigoSeguridad(-1);
        pojoEntity.setId(entity.getId());
        tarjetaLogic.updateTarjetaDeCredito(pojoEntity.getId(), pojoEntity);
    }

    /**
     * Prueba para eliminar una TarjetaDeCredito.
     *
     * @throws co.edu.uniandes.csw.tarjetastore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteTarjetaDeCreditoTest() throws BusinessLogicException {
        TarjetaDeCreditoEntity entity = data.get(0);
        tarjetaLogic.deleteTarjetaDeCredito(entity.getId());
        TarjetaDeCreditoEntity deleted = em.find(TarjetaDeCreditoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
