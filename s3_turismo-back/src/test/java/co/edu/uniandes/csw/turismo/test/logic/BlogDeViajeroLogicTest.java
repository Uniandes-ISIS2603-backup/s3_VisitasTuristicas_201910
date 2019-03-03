/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.test.logic;

import co.edu.uniandes.csw.turismo.ejb.BlogDeViajeroLogic;
import co.edu.uniandes.csw.turismo.ejb.ValoracionLogic;
import co.edu.uniandes.csw.turismo.entities.BlogDeViajeroEntity;
import co.edu.uniandes.csw.turismo.entities.ValoracionEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.BlogDeViajeroPersistence;
import co.edu.uniandes.csw.turismo.persistence.ValoracionPersistence;
import static com.ctc.wstx.util.DataUtil.Long;
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
 * @author lm.rodriguezc2
 */
@RunWith(Arquillian.class)
public class BlogDeViajeroLogicTest {

    PodamFactory factory = new PodamFactoryImpl();
    @Inject
    private BlogDeViajeroLogic blogDeViajeroLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<BlogDeViajeroEntity> data = new ArrayList<BlogDeViajeroEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(BlogDeViajeroEntity.class.getPackage())
                .addPackage(BlogDeViajeroLogic.class.getPackage())
                .addPackage(BlogDeViajeroPersistence.class.getPackage())
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

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from BlogDeViajeroEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            BlogDeViajeroEntity entity = factory.manufacturePojo(BlogDeViajeroEntity.class);

            em.persist(entity);
            data.add(entity);

        }

    }

    @Test(expected = BusinessLogicException.class)
    public void createBlogDeViajeroIDExistenteTest() throws BusinessLogicException {
        BlogDeViajeroEntity nuevaEntidad = factory.manufacturePojo(BlogDeViajeroEntity.class);
        nuevaEntidad.setId(Long(1));
        blogDeViajeroLogic.createBlogDeViajero(nuevaEntidad);
        blogDeViajeroLogic.createBlogDeViajero(nuevaEntidad);
    }

    @Test(expected = BusinessLogicException.class)
    public void createBlogDeViajeroLikesInvalidosTest() throws BusinessLogicException {
        BlogDeViajeroEntity nuevaEntidad = factory.manufacturePojo(BlogDeViajeroEntity.class);
        nuevaEntidad.setLikes(-3);
        blogDeViajeroLogic.createBlogDeViajero(nuevaEntidad);
    }

    @Test
    public void createBlogDeViajeroTest() throws BusinessLogicException {
        BlogDeViajeroEntity newEntity = factory.manufacturePojo(BlogDeViajeroEntity.class);
        BlogDeViajeroEntity result = blogDeViajeroLogic.createBlogDeViajero(newEntity);
        Assert.assertNotNull(result);
        BlogDeViajeroEntity entity = em.find(BlogDeViajeroEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        //Assert.assertEquals(newEntity.getName(), entity.getName());
    }

}
