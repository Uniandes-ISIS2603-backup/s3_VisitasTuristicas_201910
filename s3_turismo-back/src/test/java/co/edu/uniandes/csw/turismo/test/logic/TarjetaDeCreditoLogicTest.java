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
    @Inject
    private TarjetaDeCreditoPersistence ep;
    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<TarjetaDeCreditoEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TarjetaDeCreditoEntity.class.getPackage())
                .addPackage(TarjetaDeCreditoPersistence.class.getPackage())
                .addPackage(TarjetaDeCreditoLogic.class.getPackage())
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
        em.createQuery("delete from TarjetaDeCreditoEntity").executeUpdate();
    }

    private void insertData() {

        for (int i = 0; i < 3; i++) {
            TarjetaDeCreditoEntity newTarjetaDeCreditoEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
            em.persist(newTarjetaDeCreditoEntity);
            data.add(newTarjetaDeCreditoEntity);
        }
    }

    @Test
    public void createTarjetaDeCreditoTest() throws BusinessLogicException {

        TarjetaDeCreditoEntity newTarjetaDeCreditoEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        TarjetaDeCreditoEntity result = ep.create(newTarjetaDeCreditoEntity);

        Assert.assertNotNull(result);

        TarjetaDeCreditoEntity entity = em.find(TarjetaDeCreditoEntity.class, result.getId());

        Assert.assertEquals(newTarjetaDeCreditoEntity.getId(), entity.getId());

    }

    @Test(expected = BusinessLogicException.class)
    public void createTarjetaDeCreditoConMismoNumeroTest() throws BusinessLogicException {

        TarjetaDeCreditoEntity newTarjetaDeCreditoEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        newTarjetaDeCreditoEntity.setNumero(data.get(0).getNumero());
        tarjetaLogic.createTarjetaDeCredito(newTarjetaDeCreditoEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createTarjetaDeCreditoConDescripcionInvalidoTest() throws BusinessLogicException {
        TarjetaDeCreditoEntity newEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        newEntity.setNumero("");
        tarjetaLogic.createTarjetaDeCredito(newEntity);
    }

    @Test
    public void deleteTarjetaDeCreditoTest() throws BusinessLogicException {
        TarjetaDeCreditoEntity newTarjetaDeCreditoEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        TarjetaDeCreditoEntity result = ep.create(newTarjetaDeCreditoEntity);
        Assert.assertNotNull(result);
        TarjetaDeCreditoEntity entity = em.find(TarjetaDeCreditoEntity.class, result.getId());
        Assert.assertNotNull(entity);
        ep.delete(entity.getId());
        Assert.assertNull(ep.find(result.getId()));

    }

    @Test
    public void updateTarjetaDeCreditoTest() throws BusinessLogicException {
        TarjetaDeCreditoEntity entity = data.get(0);
        entity.setCodigoSeguridad(15543);
        TarjetaDeCreditoEntity pojoEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        
        pojoEntity.setId(entity.getId());
        pojoEntity.setCodigoSeguridad(entity.getCodigoSeguridad());
        
        tarjetaLogic.updateTarjetaDeCredito(pojoEntity.getId(), pojoEntity);
        
        TarjetaDeCreditoEntity resp = em.find(TarjetaDeCreditoEntity.class, entity.getId());
        
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getBanco(), resp.getBanco());
        Assert.assertEquals(pojoEntity.getCodigoSeguridad(), resp.getCodigoSeguridad());
        Assert.assertEquals(pojoEntity.getBanco(), resp.getBanco());
        Assert.assertEquals(pojoEntity.getViajero(), resp.getViajero());

    }
    
     @Test(expected = BusinessLogicException.class)
    public void updateTarjetaDeCreditoConNumeroVacioTest() throws BusinessLogicException {
        TarjetaDeCreditoEntity entity = data.get(0);
        TarjetaDeCreditoEntity pojoEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        pojoEntity.setNumero("");
        tarjetaLogic.updateTarjetaDeCredito(entity.getId(), pojoEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void updateTarjetaDeCreditoConNumeroNuloTest() throws BusinessLogicException {
        TarjetaDeCreditoEntity entity = data.get(0);
        TarjetaDeCreditoEntity pojoEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        pojoEntity.setNumero(null);
        tarjetaLogic.updateTarjetaDeCredito(entity.getId(), pojoEntity);
    }
    
    
     @Test(expected = BusinessLogicException.class)
    public void updateTarjetaDeCreditoConCodigoNegativoTest() throws BusinessLogicException {
        TarjetaDeCreditoEntity entity = data.get(0);
        TarjetaDeCreditoEntity pojoEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        pojoEntity.setCodigoSeguridad(-1);
        tarjetaLogic.updateTarjetaDeCredito(entity.getId(), pojoEntity);
    }

    @Test(expected = BusinessLogicException.class)
    public void updateTarjetaDeCreditoConNumeroVaciaTest() throws BusinessLogicException {
        TarjetaDeCreditoEntity entity = data.get(0);
        TarjetaDeCreditoEntity pojoEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        pojoEntity.setNumero("");
        tarjetaLogic.updateTarjetaDeCredito(entity.getId(), pojoEntity);
    }
    
     @Test(expected = BusinessLogicException.class)
    public void updateTarjetaDeCreditoConNumeroNula() throws BusinessLogicException {
        TarjetaDeCreditoEntity entity = data.get(0);
        TarjetaDeCreditoEntity pojoEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        pojoEntity.setNumero(null);
        tarjetaLogic.updateTarjetaDeCredito(entity.getId(), pojoEntity);
    }
    
     
 
}
