/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.test.logic;

import co.edu.uniandes.csw.turismo.ejb.BlogDeViajeroLogic;
import co.edu.uniandes.csw.turismo.entities.BlogDeViajeroEntity;
import co.edu.uniandes.csw.turismo.entities.PlanTuristicoEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.BlogDeViajeroPersistence;
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
    
    @Inject
    private BlogDeViajeroPersistence blogPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private final List<BlogDeViajeroEntity> data = new ArrayList<>();

     private final List<PlanTuristicoEntity> dataPlan = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */

    
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
        em.createQuery("delete from PlanTuristicoEntity").executeUpdate();
    }

   /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    public void insertData() {

         for (int i = 0; i < 3; i++) {
            PlanTuristicoEntity plan = factory.manufacturePojo(PlanTuristicoEntity.class);
            em.persist(plan);
            dataPlan.add(plan);
        }
       for (int i = 0; i < 3; i++) {
            BlogDeViajeroEntity newBlogViajero = factory.manufacturePojo(BlogDeViajeroEntity.class);
            em.persist(newBlogViajero);
            data.add(newBlogViajero);
        }
    }

    /**
     * Prueba para crear un blog.
     *
     */
    @Test
    public void createViajeTest(){
        PodamFactory factory= new PodamFactoryImpl();
        BlogDeViajeroEntity newViajeEntity = factory.manufacturePojo(BlogDeViajeroEntity.class);
        BlogDeViajeroEntity result= blogPersistence.create(newViajeEntity);
        
        Assert.assertNotNull(result);
        
        BlogDeViajeroEntity  entity=em.find(BlogDeViajeroEntity.class, result.getId());
        
        Assert.assertEquals(newViajeEntity.getComentarios(),entity.getComentarios());
        Assert.assertEquals(newViajeEntity.getLikes(),entity.getLikes());
        Assert.assertEquals(newViajeEntity.getId(),entity.getId());
        Assert.assertEquals(newViajeEntity.getSugerencias(),entity.getSugerencias());
        Assert.assertEquals(newViajeEntity.getPlanTuristico(),entity.getPlanTuristico());

        
        
    }
     /**
     * Prueba para consultar la lista de blogs.
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void getBlogsTest() throws BusinessLogicException {
        List<BlogDeViajeroEntity> list = blogDeViajeroLogic.getBlogs(dataPlan.get(1).getId());
        Assert.assertEquals(data.size(), list.size());
        for (BlogDeViajeroEntity entity : list) {
            boolean found = false;
            for (BlogDeViajeroEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un blog.
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void getBlogTest()throws BusinessLogicException {
        BlogDeViajeroEntity entity = data.get(0);
        BlogDeViajeroEntity newEntity = blogPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getComentarios(), newEntity.getComentarios());
        Assert.assertEquals(entity.getId(), newEntity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getComentarios(), newEntity.getComentarios());
        Assert.assertEquals(entity.getLikes(), newEntity.getLikes());
                Assert.assertEquals(entity.getPlanTuristico(), newEntity.getPlanTuristico());

    }

    /**
     * Prueba para actualizar un blog.
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
   @Test
    public void updateBlogViajeroTest() throws BusinessLogicException {
        BlogDeViajeroEntity entity = data.get(0);
        entity.setLikes(100);
        BlogDeViajeroEntity pojoEntity = factory.manufacturePojo(BlogDeViajeroEntity.class);
        
        pojoEntity.setId(entity.getId());
        pojoEntity.setSugerencias(entity.getSugerencias());
        pojoEntity.setComentarios(entity.getComentarios());
        pojoEntity.setLikes(entity.getLikes());
        
        blogDeViajeroLogic.updateBlog(pojoEntity.getId(), pojoEntity);
        
        BlogDeViajeroEntity resp = em.find(BlogDeViajeroEntity.class, entity.getId());
        
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getComentarios(), resp.getComentarios());
        Assert.assertEquals(pojoEntity.getLikes(), resp.getLikes());
        Assert.assertEquals(pojoEntity.getSugerencias(), resp.getSugerencias());
        Assert.assertEquals(pojoEntity.getPlanTuristico(), resp.getPlanTuristico());

        

    }

    /**
     * Prueba para eliminar un blog.
     *
     */


    /**
     * Prueba para eliminarle un blog a un plan del cual no pertenece.
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
  /*  @Test(expected = BusinessLogicException.class)
    public void deleteBlogConPlanNoAsociadoTest() throws BusinessLogicException {
        BlogDeViajeroEntity entity = data.get(0);
        blogDeViajeroLogic.deleteBlog(dataPlan.get(0).getId(), entity.getId());
    }*/
    //Id alt shif f
    //comentar ctrl shift c



    @Test(expected = BusinessLogicException.class)
    public void createBlogDeViajeroIDExistenteTest() throws BusinessLogicException {
        BlogDeViajeroEntity nuevaEntidad = factory.manufacturePojo(BlogDeViajeroEntity.class);
        nuevaEntidad.setId(Long(1));
        blogDeViajeroLogic.createBlogDeViajero(dataPlan.get(1).getId(),nuevaEntidad);
        blogDeViajeroLogic.createBlogDeViajero(dataPlan.get(1).getId(),nuevaEntidad);
    }

    @Test(expected = BusinessLogicException.class)
    public void createBlogDeViajeroLikesInvalidosTest() throws BusinessLogicException {
        BlogDeViajeroEntity nuevaEntidad = factory.manufacturePojo(BlogDeViajeroEntity.class);
        nuevaEntidad.setLikes(-3);
        blogDeViajeroLogic.createBlogDeViajero(dataPlan.get(1).getId(),nuevaEntidad);
    }

   

}
