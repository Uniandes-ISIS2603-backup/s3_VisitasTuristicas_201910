/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.test.logic;

import co.edu.uniandes.csw.turismo.ejb.FacturaLogic;
import co.edu.uniandes.csw.turismo.ejb.FacturaLogic;
import co.edu.uniandes.csw.turismo.entities.FacturaEntity;
import co.edu.uniandes.csw.turismo.entities.FacturaEntity;
import co.edu.uniandes.csw.turismo.entities.FacturaEntity;
import co.edu.uniandes.csw.turismo.entities.FacturaEntity;
import co.edu.uniandes.csw.turismo.entities.ViajeroEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.FacturaPersistence;
import co.edu.uniandes.csw.turismo.persistence.FacturaPersistence;
import com.gs.collections.impl.list.fixed.ArrayAdapter;
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
 * @author jd.castrellon
 */
@RunWith(Arquillian.class)
public class FacturaLogicTest {
 private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private FacturaLogic facturaLogic;
    @Inject
    private FacturaPersistence ep;
    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<FacturaEntity> data = new ArrayList<>();
    private List<ViajeroEntity> dataViajero= new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FacturaEntity.class.getPackage())
                .addPackage(FacturaPersistence.class.getPackage())
                .addPackage(FacturaLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");

    }

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

    private void clearData() {
        em.createQuery("delete from FacturaEntity").executeUpdate();
    }

    private void insertData() {

        for (int i = 0; i < 3; i++) {
            FacturaEntity newFacturaEntity = factory.manufacturePojo(FacturaEntity.class);
            em.persist(newFacturaEntity);
            data.add(newFacturaEntity);
        }
        for (int i = 0; i < 3; i++) {
            ViajeroEntity newFacturaEntity2 = factory.manufacturePojo(ViajeroEntity.class);
            em.persist(newFacturaEntity2);
            dataViajero.add(newFacturaEntity2);
        }
    }

    @Test
    public void createFacturaTest() throws BusinessLogicException {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        newEntity.setViajero(dataViajero.get(1));
        FacturaEntity result = facturaLogic.createFactura(dataViajero.get(1).getId(), newEntity);
        Assert.assertNotNull(result);
        FacturaEntity entity = em.find(FacturaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getCosto(), entity.getCosto());
        Assert.assertEquals(newEntity.getViajero(), entity.getViajero());
    }

    /**
     * Prueba para consultar la lista de Reviews.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void getFacturasTest() throws BusinessLogicException {
        List<FacturaEntity> list = facturaLogic.getFacturas(dataViajero.get(1).getId());

        Assert.assertEquals(data.size(),list.size() );
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
     * Prueba para consultar un Review.
     */
    @Test
    public void getFacturaTest() {
        FacturaEntity entity = data.get(0);
        FacturaEntity resultEntity = facturaLogic.getFactura(dataViajero.get(1).getId(), entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getCosto(), resultEntity.getCosto());
        Assert.assertEquals(entity.getDescripcion(), resultEntity.getDescripcion());
        Assert.assertEquals(entity.getViajero(), resultEntity.getViajero());
    }
    
        @Test
    public void deleteFacturaTest() throws BusinessLogicException {
        FacturaEntity entity = data.get(0);
        facturaLogic.deleteFactura(dataViajero.get(1).getId(), entity.getId());
        FacturaEntity deleted = em.find(FacturaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para eliminarle un review a un book del cual no pertenece.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void deleteReviewConBookNoAsociadoTest() throws BusinessLogicException {
        FacturaEntity entity = data.get(0);
        facturaLogic.deleteFactura(dataViajero.get(0).getId(), entity.getId());
    }
    
     
 
}
