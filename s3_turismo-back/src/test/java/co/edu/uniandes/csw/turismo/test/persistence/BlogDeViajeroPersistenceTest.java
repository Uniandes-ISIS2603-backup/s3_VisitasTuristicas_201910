/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.test.persistence;

import co.edu.uniandes.csw.turismo.entities.BlogDeViajeroEntity;

import co.edu.uniandes.csw.turismo.persistence.BlogDeViajeroPersistence;

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
public class BlogDeViajeroPersistenceTest {
      @Inject
    private BlogDeViajeroPersistence blogDeViajeroPersistence;

    @PersistenceContext
    private EntityManager em;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(BlogDeViajeroEntity.class.getPackage())
                .addPackage(BlogDeViajeroPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Test
    public void createBlogDeViajeroTest() {
        PodamFactory factory = new PodamFactoryImpl();
        BlogDeViajeroEntity newBlogDeViajeroEntity = factory.manufacturePojo(BlogDeViajeroEntity.class);
        BlogDeViajeroEntity result = blogDeViajeroPersistence.create(newBlogDeViajeroEntity);

        Assert.assertNotNull(result);

        BlogDeViajeroEntity entity = em.find(BlogDeViajeroEntity.class, result.getId());

        Assert.assertEquals(newBlogDeViajeroEntity, entity);

    }
}
