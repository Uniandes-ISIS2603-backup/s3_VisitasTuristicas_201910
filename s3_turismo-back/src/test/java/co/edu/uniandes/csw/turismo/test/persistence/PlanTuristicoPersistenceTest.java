/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.test.persistence;

import co.edu.uniandes.csw.turismo.entities.PlanTuristicoEntity;
import co.edu.uniandes.csw.turismo.persistence.PlanTuristicoPersistence;
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
    
    @Test
    public void createPlanTuristicoTest(){
        PodamFactory factory= new PodamFactoryImpl();
        PlanTuristicoEntity newPlanTuristicoEntity = factory.manufacturePojo(PlanTuristicoEntity.class);
        PlanTuristicoEntity result= ep.create(newPlanTuristicoEntity);
        
        Assert.assertNotNull(result);
        
        PlanTuristicoEntity  entity=em.find(PlanTuristicoEntity.class, result.getId());
        
        Assert.assertEquals(newPlanTuristicoEntity.getNombrePlan(),entity.getNombrePlan());
        
        
    }

}
