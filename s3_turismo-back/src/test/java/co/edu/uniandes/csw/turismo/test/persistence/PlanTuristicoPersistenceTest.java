/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.test.persistence;

import co.edu.uniandes.csw.turismo.entities.PlanTuristicoEntity;
import co.edu.uniandes.csw.turismo.persistence.PlanTuristicoPersistence;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Christer Osorio
 */
@RunWith(Arquillian.class)
public class PlanTuristicoPersistenceTest {

    @Inject
    private PlanTuristicoPersistence ep;
    @PersistenceContext
    private EntityManager em;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PlanTuristicoEntity.class.getPackage())
                .addPackage(PlanTuristicoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");

    }

    /**
     *
     * Prueba que el plan turistico haya quedado en la base de datos
     *
     */
    @Test
    public void createPlanTuristicoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PlanTuristicoEntity newPlanTuristicoEntity = factory.manufacturePojo(PlanTuristicoEntity.class);
        PlanTuristicoEntity result = ep.create(newPlanTuristicoEntity);

        Assert.assertNotNull(result);

        PlanTuristicoEntity entity = em.find(PlanTuristicoEntity.class, result.getId());

        Assert.assertEquals(newPlanTuristicoEntity.getNombrePlan(), entity.getNombrePlan());

    }

    /**
     *
     * Prueba que el plan turistico pueda ser encontrado
     *
     */
    @Test
    public void findViajeroTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PlanTuristicoEntity newEntity = factory.manufacturePojo(PlanTuristicoEntity.class);

        PlanTuristicoEntity planTuristicoEntity = ep.create(newEntity);

        PlanTuristicoEntity entity = em.find(PlanTuristicoEntity.class, planTuristicoEntity.getId());

        Assert.assertEquals(newEntity.getNombrePlan(), entity.getNombrePlan());
        Assert.assertEquals(newEntity.getTipoPlan(), entity.getTipoPlan());
        Assert.assertEquals(newEntity.getIdioma(), entity.getIdioma());
        Assert.assertEquals(newEntity.getCostoPorPersona(), entity.getCostoPorPersona());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getUbicacion(), entity.getUbicacion());

    }

    /**
     *
     * Prueba que el plan turistico pueda ser actualizado en la base de datos
     *
     */
    @Test
    public void updateNameTest() {

        PodamFactory factory = new PodamFactoryImpl();
        PlanTuristicoEntity newEntity = factory.manufacturePojo(PlanTuristicoEntity.class);
        PlanTuristicoEntity planTuristicoEntity = ep.create(newEntity);
        planTuristicoEntity.setNombrePlan("super plan");
        ep.update(planTuristicoEntity);
        PlanTuristicoEntity resp = em.find(PlanTuristicoEntity.class, planTuristicoEntity.getId());
        Assert.assertEquals("super plan", resp.getNombrePlan());

    }

    /**
     *
     * Prueba que se puedan encontrar los planes turisticos en la base de datos
     *
     */
    @Test
    public void findAllPlanTuristicoTest() {
        List<PlanTuristicoEntity> list = ep.findAll();
        Assert.assertNotNull(list);
        Assert.assertNotNull(list.get(0));

    }

    /**
     *
     * Prueba que la actualizacion del plan turistico sea correcta
     *
     */
    @Test
    public void updateTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PlanTuristicoEntity newEntity = factory.manufacturePojo(PlanTuristicoEntity.class);
        PlanTuristicoEntity planTuristicoEntity = ep.create(newEntity);

        PodamFactory factory2 = new PodamFactoryImpl();
        PlanTuristicoEntity newEntity2 = factory2.manufacturePojo(PlanTuristicoEntity.class);
        PlanTuristicoEntity planTuristicoEntity2 = ep.create(newEntity2);

        planTuristicoEntity.setId(planTuristicoEntity2.getId());
        ep.update(planTuristicoEntity);
        PlanTuristicoEntity resp;
        resp = em.find(PlanTuristicoEntity.class, planTuristicoEntity.getId());
        Assert.assertEquals(planTuristicoEntity2.getId(), resp.getId());

    }

    /**
     *
     * Prueba que el plan turistico haya sido borrado de la base de datos
     *
     */
    @Test
    public void deleteTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PlanTuristicoEntity newPlanTuristicoEntity = factory.manufacturePojo(PlanTuristicoEntity.class);
        PlanTuristicoEntity planTuristicoEntity = ep.create(newPlanTuristicoEntity);
        ep.delete(planTuristicoEntity.getId());
        PlanTuristicoEntity planBorrado = em.find(PlanTuristicoEntity.class, planTuristicoEntity.getId());
        Assert.assertNull(planBorrado);
    }

}
