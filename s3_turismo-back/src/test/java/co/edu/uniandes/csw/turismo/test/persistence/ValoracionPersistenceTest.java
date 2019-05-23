/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.test.persistence;

import co.edu.uniandes.csw.turismo.entities.ValoracionEntity;

import co.edu.uniandes.csw.turismo.persistence.ValoracionPersistence;

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
 * @author lm.rodriguezc2
 */
@RunWith(Arquillian.class)

public class ValoracionPersistenceTest {

    @Inject
    private ValoracionPersistence valoracionPersistence;

    @PersistenceContext
    private EntityManager em;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ValoracionEntity.class.getPackage())
                .addPackage(ValoracionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Test
    public void createValoracionTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ValoracionEntity newValoracionEntity = factory.manufacturePojo(ValoracionEntity.class);
        ValoracionEntity result = valoracionPersistence.create(newValoracionEntity);

        Assert.assertNotNull(result);

        ValoracionEntity entity = em.find(ValoracionEntity.class, result.getId());

        Assert.assertEquals(newValoracionEntity, entity);

    }
    
    

}
