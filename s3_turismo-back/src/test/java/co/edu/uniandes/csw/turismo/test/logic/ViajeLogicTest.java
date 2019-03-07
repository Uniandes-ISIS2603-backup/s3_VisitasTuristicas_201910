/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.test.logic;

import co.edu.uniandes.csw.turismo.ejb.ViajeLogic;
import co.edu.uniandes.csw.turismo.entities.PlanTuristicoEntity;
import co.edu.uniandes.csw.turismo.entities.ViajeEntity;
import co.edu.uniandes.csw.turismo.entities.ViajeroEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.ViajePersistence;
import java.sql.Date;
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
public class ViajeLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ViajeLogic viajeLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ViajeEntity> data = new ArrayList<ViajeEntity>();

    private List<PlanTuristicoEntity> planTuristicoData = new ArrayList();

    private List<ViajeroEntity> viajeroData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ViajeEntity.class.getPackage())
                .addPackage(ViajeLogic.class.getPackage())
                .addPackage(ViajePersistence.class.getPackage())
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
        em.createQuery("delete from ViajeEntity").executeUpdate();
        em.createQuery("delete from PlanTuristicoEntity").executeUpdate();
        em.createQuery("delete from ViajeroEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PlanTuristicoEntity planTuristico = factory.manufacturePojo(PlanTuristicoEntity.class);
            em.persist(planTuristico);
            planTuristicoData.add(planTuristico);
        }

        ViajeroEntity viajero = factory.manufacturePojo(ViajeroEntity.class);
        viajero.setPlanesTuristicos(planTuristicoData);
        em.persist(viajero);
        viajeroData.add(viajero);

        ViajeEntity viaje = factory.manufacturePojo(ViajeEntity.class);
        Date date = new Date(11, 5, 21);
        Date date2 = new Date(15, 1, 21);
        viaje.setFechaInicio(date);
        viaje.setFechaFin(date2);
        em.persist(viaje);
        viaje.setPlanTuristico(planTuristicoData.get(0));
        viaje.setViajero(viajeroData.get(0));

        data.add(viaje);

    }

    /**
     * Prueba para crear un Viaje
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void createViajeTest() throws BusinessLogicException {
        ViajeEntity newEntity = factory.manufacturePojo(ViajeEntity.class);

        newEntity.setPlanTuristico(planTuristicoData.get(0));
        newEntity.setViajero(viajeroData.get(0));
        ViajeEntity result = viajeLogic.createViaje(newEntity);
        Assert.assertNotNull(result);
        ViajeEntity entity = em.find(ViajeEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getFechaInicio(), entity.getFechaInicio());
        Assert.assertEquals(newEntity.getFechaFin(), entity.getFechaFin());

    }

    /**
     * Prueba para consultar la lista de viajes.
     */
    @Test
    public void getBooksTest() {
        List<ViajeEntity> list = viajeLogic.getViajes();
        Assert.assertEquals(data.size(), list.size());
        for (ViajeEntity entity : list) {
            boolean found = false;
            for (ViajeEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un viaje.
      * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void getBookTest() throws BusinessLogicException {
        ViajeEntity entity = data.get(0);
        ViajeEntity resultEntity = viajeLogic.getViaje(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getFechaInicio(), resultEntity.getFechaInicio());
        Assert.assertEquals(entity.getFechaFin(), resultEntity.getFechaFin());
    }
    
    /**
     * Prueba para actualizar un viaje.
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void updateBookTest() throws BusinessLogicException {
        ViajeEntity entity = data.get(0);
        ViajeEntity pojoEntity = factory.manufacturePojo(ViajeEntity.class);
        pojoEntity.setId(entity.getId());
        viajeLogic.updateViaje(pojoEntity.getId(), pojoEntity);
        ViajeEntity resp = em.find(ViajeEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getFechaInicio(), resp.getFechaInicio());
        Assert.assertEquals(pojoEntity.getFechaFin(), resp.getFechaFin());
       
    }
    
    
    @Test
    public void deleteBookTest() throws BusinessLogicException {
        ViajeEntity entity = data.get(0);
        viajeLogic.deleteViaje(entity.getId());
        ViajeEntity deleted = em.find(ViajeEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
