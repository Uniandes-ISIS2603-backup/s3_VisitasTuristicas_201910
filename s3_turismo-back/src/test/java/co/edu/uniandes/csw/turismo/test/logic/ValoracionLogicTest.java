/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.test.logic;

import co.edu.uniandes.csw.turismo.ejb.ValoracionLogic;
import co.edu.uniandes.csw.turismo.entities.PlanTuristicoEntity;
import co.edu.uniandes.csw.turismo.entities.ValoracionEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.ValoracionPersistence;
import static com.ctc.wstx.util.DataUtil.Long;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

      private final Random rand = new Random();
    PodamFactory factory = new PodamFactoryImpl();
    @Inject
    private ValoracionLogic valoracionLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private final List<ValoracionEntity> data = new ArrayList<>();

    private final List<PlanTuristicoEntity> dataPlan = new ArrayList<PlanTuristicoEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ValoracionEntity.class.getPackage())
                .addPackage(ValoracionLogic.class.getPackage())
                .addPackage(ValoracionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");

    }

    /**
     * Configuración inicial de la prueba.
     */
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
        em.createQuery("delete from PlanTuristicoEntity").executeUpdate();
        //em.createQuery("delete from EditorialEntity").executeUpdate();
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
            ValoracionEntity entity = factory.manufacturePojo(ValoracionEntity.class);
            entity.setPlanTuristico(dataPlan.get(1));
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear una valoracion.
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void createValoracionTest() throws BusinessLogicException {
        ValoracionEntity newEntity = factory.manufacturePojo(ValoracionEntity.class);
        newEntity.setPlanTuristico(dataPlan.get(1));
        // Obtain a number between [0 - 2000].
        int n = rand.nextInt(5);
        newEntity.setValoracion(n);
        ValoracionEntity result = valoracionLogic.createValoracion(dataPlan.get(1).getId(), newEntity);
        Assert.assertNotNull(result);
        ValoracionEntity entity = em.find(ValoracionEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getComentario(), entity.getComentario());
        Assert.assertEquals(newEntity.getIdUsuario(), entity.getIdUsuario());
        Assert.assertEquals(newEntity.getValoracion(), entity.getValoracion());
                Assert.assertEquals(newEntity.getPlanTuristico(), entity.getPlanTuristico());

    }
     /**
     * Prueba para consultar la lista de valoraciones.
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void getValoracionesTest() throws BusinessLogicException {
        List<ValoracionEntity> list = valoracionLogic.getValoraciones(dataPlan.get(1).getId());
        Assert.assertEquals(data.size(), list.size());
        for (ValoracionEntity entity : list) {
            boolean found = false;
            for (ValoracionEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una valoracion.
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void getValoracionTest()throws BusinessLogicException {
        ValoracionEntity entity = data.get(0);
        ValoracionEntity resultEntity = valoracionLogic.getValoracion(dataPlan.get(1).getId(), entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getComentario(), resultEntity.getComentario());
        Assert.assertEquals(entity.getIdUsuario(), resultEntity.getIdUsuario());
        Assert.assertEquals(entity.getValoracion(), resultEntity.getValoracion());
                Assert.assertEquals(entity.getPlanTuristico(), resultEntity.getPlanTuristico());

    }

    /**
     * Prueba para actualizar una valoracion.
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateValoracionTest() throws BusinessLogicException {
        ValoracionEntity entity = data.get(0);
        ValoracionEntity pojoEntity = factory.manufacturePojo(ValoracionEntity.class);

        pojoEntity.setId(entity.getId());

        valoracionLogic.updateValoracion(dataPlan.get(1).getId(), pojoEntity);

        ValoracionEntity resp = em.find(ValoracionEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getComentario(), resp.getComentario());
        Assert.assertEquals(pojoEntity.getIdUsuario(), resp.getIdUsuario());
        Assert.assertEquals(pojoEntity.getValoracion(), resp.getValoracion());
    }

    /**
     * Prueba para eliminar una valoracion.
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void deleteValoracionTest() throws BusinessLogicException {
        ValoracionEntity entity = data.get(0);
        valoracionLogic.deleteValoracion(dataPlan.get(1).getId(), entity.getId());
        ValoracionEntity deleted = em.find(ValoracionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para eliminarle una valoracion a un plan del cual no pertenece.
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void deleteValoracionConPlanNoAsociadoTest() throws BusinessLogicException {
        ValoracionEntity entity = data.get(0);
        valoracionLogic.deleteValoracion(dataPlan.get(0).getId(), entity.getId());
    }
    //Id alt shif f
    //comentar ctrl shift c

    @Test(expected = BusinessLogicException.class)
    public void createValoracionIDExistenteTest() throws BusinessLogicException {
        ValoracionEntity nuevaEntidad = factory.manufacturePojo(ValoracionEntity.class);
        nuevaEntidad.setId(Long(1));
        valoracionLogic.createValoracion(dataPlan.get(1).getId(),nuevaEntidad);
        valoracionLogic.createValoracion(dataPlan.get(1).getId(),nuevaEntidad);
    }

    @Test(expected = BusinessLogicException.class)
    public void createValoracionValoracionInvalidaMayorTest() throws BusinessLogicException {
        ValoracionEntity nuevaEntidad = factory.manufacturePojo(ValoracionEntity.class);
        nuevaEntidad.setValoracion(6);
        valoracionLogic.createValoracion(dataPlan.get(1).getId(), nuevaEntidad);
    }

    @Test(expected = BusinessLogicException.class)
    public void createValoracionValoracionInvalidaMenorTest() throws BusinessLogicException {
        ValoracionEntity nuevaEntidad = factory.manufacturePojo(ValoracionEntity.class);
        nuevaEntidad.setValoracion(-2);
        valoracionLogic.createValoracion(dataPlan.get(1).getId(), nuevaEntidad);
    }

//      @Test (expected = BusinessLogicException.class)
//    public void createValoracionTest() throws BusinessLogicException {
//        ValoracionEntity newEntity = factory.manufacturePojo(ValoracionEntity.class);
//        ValoracionEntity result = valoracionLogic.createValoracion(newEntity);
//        Assert.assertNotNull(result);
//        ValoracionEntity entity = em.find(ValoracionEntity.class, result.getId());
//        Assert.assertEquals(newEntity.getId(), entity.getId());
        //Assert.assertEquals(newEntity.getName(), entity.getName());
//    }
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
