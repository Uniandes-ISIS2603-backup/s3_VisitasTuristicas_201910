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
 private final PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private FacturaLogic facturaLogic;
    @Inject
    private FacturaPersistence ep;
    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private final List<FacturaEntity> data = new ArrayList<>();
    private final List<ViajeroEntity> dataViajero= new ArrayList<>();

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
            ViajeroEntity newFacturaEntity2 = factory.manufacturePojo(ViajeroEntity.class);
            
            em.persist(newFacturaEntity2);
            dataViajero.add(newFacturaEntity2);
        }
        for (int i = 0; i < 3; i++) {
            FacturaEntity entity = factory.manufacturePojo(FacturaEntity.class);
            entity.setViajero(dataViajero.get(0));
            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createFacturaTest() throws BusinessLogicException {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        newEntity.setViajero(dataViajero.get(0));
        FacturaEntity result = facturaLogic.createFactura(dataViajero.get(0).getId(), newEntity);
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
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void getFacturasTest() throws BusinessLogicException {
        List<FacturaEntity> list = facturaLogic.getFacturas(dataViajero.get(0).getId());

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
     * Prueba para consultar una factura.
     //TODO Arreglar este test :C:C 
     */
    @Test
    public void getFacturaTest() {
        FacturaEntity entity = data.get(0);
        ViajeroEntity viajero = dataViajero.get(0);
        FacturaEntity resultEntity = facturaLogic.getFactura(entity.getId(),viajero.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
    @Test
    public void deleteFacturaTest() throws BusinessLogicException {
        FacturaEntity entity = data.get(1);
        ViajeroEntity viajero= dataViajero.get(0);
        facturaLogic.deleteFactura(entity.getId(),viajero.getId());
        FacturaEntity deleted = em.find(FacturaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

 
    
     
 
}
