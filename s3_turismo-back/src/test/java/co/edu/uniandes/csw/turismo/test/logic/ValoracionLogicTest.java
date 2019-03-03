/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.test.logic;

import co.edu.uniandes.csw.turismo.ejb.ValoracionLogic;
import co.edu.uniandes.csw.turismo.entities.ValoracionEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
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
public class ValoracionLogicTest {

    PodamFactory factory = new PodamFactoryImpl();
    @Inject
    private ValoracionLogic valoracionLogic;
    
       @PersistenceContext
    private EntityManager em;
       
       @Inject
    private UserTransaction utx;
       
       private List<ValoracionEntity> data = new ArrayList<ValoracionEntity>();
       
       @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ValoracionEntity.class.getPackage())
                .addPackage(ValoracionLogic.class.getPackage())
                .addPackage(ValoracionPersistence.class.getPackage())
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
        em.createQuery("delete from ValoracionEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ValoracionEntity entity = factory.manufacturePojo(ValoracionEntity.class);

            em.persist(entity);
            data.add(entity);

        }
    }



    @Test(expected = BusinessLogicException.class)
    public void createValoracionIDExistenteTest() throws BusinessLogicException {
        ValoracionEntity nuevaEntidad = factory.manufacturePojo(ValoracionEntity.class);
        nuevaEntidad.setId(Long(1));
        valoracionLogic.createValoracion(nuevaEntidad);
        valoracionLogic.createValoracion(nuevaEntidad);
    }

    @Test(expected = BusinessLogicException.class)
    public void createValoracionValoracionInvalidaMayorTest() throws BusinessLogicException {
        ValoracionEntity nuevaEntidad = factory.manufacturePojo(ValoracionEntity.class);
        nuevaEntidad.setValoracion(6);
        valoracionLogic.createValoracion(nuevaEntidad);
    }

    @Test(expected = BusinessLogicException.class)
    public void createValoracionValoracionInvalidaMenorTest() throws BusinessLogicException {
        ValoracionEntity nuevaEntidad = factory.manufacturePojo(ValoracionEntity.class);
        nuevaEntidad.setValoracion(-2);
        valoracionLogic.createValoracion(nuevaEntidad);
    }

      @Test
    public void createValoracionTest() throws BusinessLogicException {
        ValoracionEntity newEntity = factory.manufacturePojo(ValoracionEntity.class);
        ValoracionEntity result = valoracionLogic.createValoracion(newEntity);
        Assert.assertNotNull(result);
        ValoracionEntity entity = em.find(ValoracionEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        //Assert.assertEquals(newEntity.getName(), entity.getName());
    }
    
    /*@Test(expected = ValoracionEntity.class)
    public ValoracionEntity createValoracionIDNoExistenteTest() throws BusinessLogicException {
        ValoracionEntity nuevaEntidad = factory.manufacturePojo(ValoracionEntity.class);
        ValoracionEntity nuevaEntidad1 = factory.manufacturePojo(ValoracionEntity.class);
        nuevaEntidad.setId(Long(1));
        nuevaEntidad.setId(Long(2));
        valoracionLogic.createValoracion(nuevaEntidad);
        return valoracionLogic.createValoracion(nuevaEntidad1);
    }

    @Test(expected = ValoracionEntity.class)
    public ValoracionEntity createValoracionValoracionValidaTest() throws BusinessLogicException {
        ValoracionEntity nuevaEntidad = factory.manufacturePojo(ValoracionEntity.class);
        nuevaEntidad.setValoracion(3);
        return valoracionLogic.createValoracion(nuevaEntidad);
    }*/
}
