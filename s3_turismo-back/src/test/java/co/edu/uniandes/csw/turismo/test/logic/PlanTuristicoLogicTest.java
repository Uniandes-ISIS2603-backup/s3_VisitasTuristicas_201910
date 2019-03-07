/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.test.logic;

import co.edu.uniandes.csw.turismo.ejb.PlanTuristicoLogic;
import co.edu.uniandes.csw.turismo.entities.PlanTuristicoEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.PlanTuristicoPersistence;
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
 * @author Christer Osorio
 */
@RunWith(Arquillian.class)
public class PlanTuristicoLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private PlanTuristicoLogic planTuristicoLogic;
    @Inject
    private PlanTuristicoPersistence ep;
    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<PlanTuristicoEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PlanTuristicoEntity.class.getPackage())
                .addPackage(PlanTuristicoPersistence.class.getPackage())
                .addPackage(PlanTuristicoLogic.class.getPackage())
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
        em.createQuery("delete from PlanTuristicoEntity").executeUpdate();
    }

    private void insertData() {

        for (int i = 0; i < 3; i++) {
            PlanTuristicoEntity newPlanTuristicoEntity = factory.manufacturePojo(PlanTuristicoEntity.class);
            em.persist(newPlanTuristicoEntity);
            data.add(newPlanTuristicoEntity);
        }
    }

    @Test
    public void createPlanTuristicoTest() throws BusinessLogicException {

        PlanTuristicoEntity newPlanTuristicoEntity = factory.manufacturePojo(PlanTuristicoEntity.class);
        PlanTuristicoEntity result = ep.create(newPlanTuristicoEntity);

        Assert.assertNotNull(result);

        PlanTuristicoEntity entity = em.find(PlanTuristicoEntity.class, result.getId());

        Assert.assertEquals(newPlanTuristicoEntity.getNombrePlan(), entity.getNombrePlan());

    }

    @Test(expected = BusinessLogicException.class)
    public void createPlanTuristicoConMismoNombreTest() throws BusinessLogicException {

        PlanTuristicoEntity newPlanTuristicoEntity = factory.manufacturePojo(PlanTuristicoEntity.class);
        newPlanTuristicoEntity.setNombrePlan(data.get(0).getNombrePlan());
        planTuristicoLogic.createPlanTuristico(newPlanTuristicoEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createPlanTuristicoConNombreInvalidoTest() throws BusinessLogicException {
        PlanTuristicoEntity newEntity = factory.manufacturePojo(PlanTuristicoEntity.class);
        newEntity.setNombrePlan("");
        planTuristicoLogic.createPlanTuristico(newEntity);
    }

    @Test
    public void deletePlanTuristicoTest() throws BusinessLogicException {
        PlanTuristicoEntity newPlanTuristicoEntity = factory.manufacturePojo(PlanTuristicoEntity.class);
        PlanTuristicoEntity result = ep.create(newPlanTuristicoEntity);
        Assert.assertNotNull(result);
        PlanTuristicoEntity entity = em.find(PlanTuristicoEntity.class, result.getId());
        Assert.assertNotNull(entity);
        ep.delete(entity.getId());
        Assert.assertNull(ep.find(result.getId()));

    }

    @Test(expected = BusinessLogicException.class)
    public void deletePlanTuristicoNoExisteTest() throws BusinessLogicException {
        PlanTuristicoEntity newPlanTuristicoEntity = factory.manufacturePojo(PlanTuristicoEntity.class);
        PlanTuristicoEntity result = ep.create(newPlanTuristicoEntity);
        Assert.assertNotNull(result);
        PlanTuristicoEntity entity = em.find(PlanTuristicoEntity.class, result.getId());
        ep.delete(entity.getId());
        planTuristicoLogic.deletePlanTuristico(result.getId());
    }

    @Test
    public void updatePlanTuristicoTest() throws BusinessLogicException {
        PlanTuristicoEntity entity = data.get(0);
        PlanTuristicoEntity pojoEntity = factory.manufacturePojo(PlanTuristicoEntity.class);
        
        pojoEntity.setId(entity.getId());
        
        planTuristicoLogic.updatePlanTuristico(pojoEntity.getId(), pojoEntity);
        
        PlanTuristicoEntity resp = em.find(PlanTuristicoEntity.class, entity.getId());
        
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombrePlan(), resp.getNombrePlan());
        Assert.assertEquals(pojoEntity.getTipoPlan(), resp.getTipoPlan());
        Assert.assertEquals(pojoEntity.getCostoPorPersona(), resp.getCostoPorPersona());
        Assert.assertEquals(pojoEntity.getUbicacion(), resp.getUbicacion());
        Assert.assertEquals(pojoEntity.getDuracion(), resp.getDuracion());
        Assert.assertEquals(pojoEntity.getDescripcion(), resp.getDescripcion());

    }
    
     @Test(expected = BusinessLogicException.class)
    public void updatePlanTuristicoConNombreVacioTest() throws BusinessLogicException {
        PlanTuristicoEntity entity = data.get(0);
        PlanTuristicoEntity pojoEntity = factory.manufacturePojo(PlanTuristicoEntity.class);
        pojoEntity.setNombrePlan("");
        planTuristicoLogic.updatePlanTuristico(entity.getId(), pojoEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void updatePlanTuristicoConNombreNuloTest() throws BusinessLogicException {
        PlanTuristicoEntity entity = data.get(0);
        PlanTuristicoEntity pojoEntity = factory.manufacturePojo(PlanTuristicoEntity.class);
        pojoEntity.setNombrePlan(null);
        planTuristicoLogic.updatePlanTuristico(entity.getId(), pojoEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void updatePlanTuristicoConDuracionVaciaTest() throws BusinessLogicException {
        PlanTuristicoEntity entity = data.get(0);
        PlanTuristicoEntity pojoEntity = factory.manufacturePojo(PlanTuristicoEntity.class);
        pojoEntity.setDuracion("");
        planTuristicoLogic.updatePlanTuristico(entity.getId(), pojoEntity);
    }
    
     @Test(expected = BusinessLogicException.class)
    public void updatePlanTuristicoConDuracionNulaTest() throws BusinessLogicException {
        PlanTuristicoEntity entity = data.get(0);
        PlanTuristicoEntity pojoEntity = factory.manufacturePojo(PlanTuristicoEntity.class);
        pojoEntity.setDuracion(null);
        planTuristicoLogic.updatePlanTuristico(entity.getId(), pojoEntity);
    }

    @Test(expected = BusinessLogicException.class)
    public void updatePlanTuristicoConDescripcionVaciaTest() throws BusinessLogicException {
        PlanTuristicoEntity entity = data.get(0);
        PlanTuristicoEntity pojoEntity = factory.manufacturePojo(PlanTuristicoEntity.class);
        pojoEntity.setDescripcion("");
        planTuristicoLogic.updatePlanTuristico(entity.getId(), pojoEntity);
    }
    
     @Test(expected = BusinessLogicException.class)
    public void updatePlanTuristicoConDescripcionNula() throws BusinessLogicException {
        PlanTuristicoEntity entity = data.get(0);
        PlanTuristicoEntity pojoEntity = factory.manufacturePojo(PlanTuristicoEntity.class);
        pojoEntity.setDescripcion(null);
        planTuristicoLogic.updatePlanTuristico(entity.getId(), pojoEntity);
    }
    
     @Test(expected = BusinessLogicException.class)
    public void updatePlanTuristicoConUbicacionVaciaTest() throws BusinessLogicException {
        PlanTuristicoEntity entity = data.get(0);
        PlanTuristicoEntity pojoEntity = factory.manufacturePojo(PlanTuristicoEntity.class);
        pojoEntity.setUbicacion("");
        planTuristicoLogic.updatePlanTuristico(entity.getId(), pojoEntity);
    }
    
     @Test(expected = BusinessLogicException.class)
    public void updatePlanTuristicoConUbicacionNula() throws BusinessLogicException {
        PlanTuristicoEntity entity = data.get(0);
        PlanTuristicoEntity pojoEntity = factory.manufacturePojo(PlanTuristicoEntity.class);
        pojoEntity.setUbicacion(null);
        planTuristicoLogic.updatePlanTuristico(entity.getId(), pojoEntity);
    }
    
     @Test(expected = BusinessLogicException.class)
    public void updatePlanTuristicoConCostoNegativoTest() throws BusinessLogicException {
        PlanTuristicoEntity entity = data.get(0);
        PlanTuristicoEntity pojoEntity = factory.manufacturePojo(PlanTuristicoEntity.class);
        pojoEntity.setCostoPorPersona(-5.5);
        planTuristicoLogic.updatePlanTuristico(entity.getId(), pojoEntity);
    }
    
     @Test(expected = BusinessLogicException.class)
    public void updatePlanTuristicoConCostoNulo() throws BusinessLogicException {
        PlanTuristicoEntity entity = data.get(0);
        PlanTuristicoEntity pojoEntity = factory.manufacturePojo(PlanTuristicoEntity.class);
        pojoEntity.setCostoPorPersona(-100.2);
        planTuristicoLogic.updatePlanTuristico(entity.getId(), pojoEntity);
    }
}
