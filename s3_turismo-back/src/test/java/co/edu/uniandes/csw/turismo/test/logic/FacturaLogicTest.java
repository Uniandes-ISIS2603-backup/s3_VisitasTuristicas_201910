/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.test.logic;

import co.edu.uniandes.csw.turismo.ejb.FacturaLogic;
import co.edu.uniandes.csw.turismo.entities.FacturaEntity;
import co.edu.uniandes.csw.turismo.entities.ViajeroEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.FacturaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jd.castrellon
 */
public class FacturaLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private FacturaLogic tarjetaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<FacturaEntity> data = new ArrayList<FacturaEntity>();

    private List<ViajeroEntity> editorialData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FacturaEntity.class.getPackage())
                .addPackage(FacturaLogic.class.getPackage())
                .addPackage(FacturaPersistence.class.getPackage())
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
        em.createQuery("delete from FacturaEntity").executeUpdate();
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
            FacturaEntity entity = factory.manufacturePojo(FacturaEntity.class);
            entity.setViajero(editorialData.get(0));

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Factura
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void createFacturaTest() throws BusinessLogicException {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        newEntity.setViajero(editorialData.get(0));
        FacturaEntity result = tarjetaLogic.createFactura(newEntity);
        Assert.assertNotNull(result);
        newEntity.setCosto(691);
        FacturaEntity entity = em.find(FacturaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getCosto(), entity.getCosto());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
    }

    /**
     * Prueba para crear un Factura con codigo inválido
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createFacturaTestConDescripcionInvalido() throws BusinessLogicException {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        newEntity.setViajero(editorialData.get(0));
        newEntity.setCosto(691);
        newEntity.setDescripcion("");
        tarjetaLogic.createFactura(newEntity);
    }

    /**
     * Prueba para crear un Factura con codigo inválido
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createFacturaTestConDescripcionInvalido2() throws BusinessLogicException {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        newEntity.setViajero(editorialData.get(0));
        newEntity.setCosto(691);
        newEntity.setDescripcion(null);
        tarjetaLogic.createFactura(newEntity);
    }

    /**
     * Prueba para crear un Factura con codigo inválido
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createFacturaTestConCostoInvalido() throws BusinessLogicException {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        newEntity.setViajero(editorialData.get(0));
        newEntity.setCosto(-1);
        tarjetaLogic.createFactura(newEntity);
    }

    /**
     * Prueba para crear un Factura con codigo existente.
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createFacturaTestConISBNExistente() throws BusinessLogicException {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        newEntity.setViajero(editorialData.get(0));
        newEntity.setDescripcion(data.get(0).getDescripcion());
        tarjetaLogic.createFactura(newEntity);
    }

    /**
     * Prueba para crear un Factura con un viajero que no existe.
     *
     *@throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createFacturaTestConViajeroInexistente() throws BusinessLogicException {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        ViajeroEntity editorialEntity = new ViajeroEntity();
        editorialEntity.setId(Long.MIN_VALUE);
        newEntity.setViajero(editorialEntity);
        tarjetaLogic.createFactura(newEntity);
    }

    /**
     * Prueba para crear un Factura sin viajero en null.
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createFacturaTestConNullViajero() throws BusinessLogicException {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        newEntity.setViajero(null);
        tarjetaLogic.createFactura(newEntity);
    }

    /**
     * Prueba para consultar la lista de Facturas.
     */
    @Test
    public void getFacturasTest() {
        List<FacturaEntity> list = tarjetaLogic.getFacturas();
        Assert.assertEquals(data.size(), list.size());
        for (FacturaEntity entity : list) {
            boolean found = false;
            for (FacturaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Factura.
     */
    @Test
    public void getFacturaTest() {
        FacturaEntity entity = data.get(0);
        FacturaEntity resultEntity = tarjetaLogic.getFactura(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(resultEntity.getId(), entity.getId());
        Assert.assertEquals(resultEntity.getCosto(), entity.getCosto());
        Assert.assertEquals(resultEntity.getDescripcion(), entity.getDescripcion());
    }

    /**
     * Prueba para actualizar un Factura.
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void updateFacturaTest() throws BusinessLogicException {
        FacturaEntity entity = data.get(0);
        FacturaEntity pojoEntity = factory.manufacturePojo(FacturaEntity.class);
        pojoEntity.setId(entity.getId());
        tarjetaLogic.updateFactura(pojoEntity.getId(), pojoEntity);
        FacturaEntity resp = em.find(FacturaEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getDescripcion(), resp.getDescripcion());
        Assert.assertEquals(pojoEntity.getCosto(), resp.getCosto());
    }

    /**
     * Prueba para actualizar un Factura con descripcion inválido.
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateFacturaConDescripcionInvalidoTest() throws BusinessLogicException {
        FacturaEntity entity = data.get(0);
        FacturaEntity pojoEntity = factory.manufacturePojo(FacturaEntity.class);
        pojoEntity.setDescripcion("");
        pojoEntity.setId(entity.getId());
        tarjetaLogic.updateFactura(pojoEntity.getId(), pojoEntity);
    }

    /**
     * Prueba para actualizar un Factura con descripcion inválido.
     *
     *@throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateFacturaConDescripcionInvalidoTest2() throws BusinessLogicException {
        FacturaEntity entity = data.get(0);
        FacturaEntity pojoEntity = factory.manufacturePojo(FacturaEntity.class);
        pojoEntity.setDescripcion(null);
        pojoEntity.setId(entity.getId());
        tarjetaLogic.updateFactura(pojoEntity.getId(), pojoEntity);
    }
    
        /**
     * Prueba para actualizar un Factura con costo inválido.
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateFacturaConCostoInvalidoTest() throws BusinessLogicException {
        FacturaEntity entity = data.get(0);
        FacturaEntity pojoEntity = factory.manufacturePojo(FacturaEntity.class);
        pojoEntity.setCosto(-1);
        pojoEntity.setId(entity.getId());
        tarjetaLogic.updateFactura(pojoEntity.getId(), pojoEntity);
    }

    /**
     * Prueba para eliminar un Factura.
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void deleteFacturaTest() throws BusinessLogicException {
        FacturaEntity entity = data.get(0);
        tarjetaLogic.deleteFactura(entity.getId());
        FacturaEntity deleted = em.find(FacturaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
