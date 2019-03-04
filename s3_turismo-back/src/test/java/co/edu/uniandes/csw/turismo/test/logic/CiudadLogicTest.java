/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.test.logic;

import co.edu.uniandes.csw.turismo.ejb.CiudadLogic;
import co.edu.uniandes.csw.turismo.entities.CiudadEntity;
import co.edu.uniandes.csw.turismo.entities.PaisEntity;
import co.edu.uniandes.csw.turismo.entities.SitioTuristicoEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.CiudadPersistence;
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
 * @author David Fonseca
 */

@RunWith(Arquillian.class)
public class CiudadLogicTest {
    
     private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private CiudadLogic ciudadLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<CiudadEntity> data = new ArrayList<>();

    private List<PaisEntity> paisData = new ArrayList<PaisEntity>();
    
    
     /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CiudadEntity.class.getPackage())
                .addPackage(CiudadLogic.class.getPackage())
                .addPackage(CiudadPersistence.class.getPackage())
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
        em.createQuery("delete from CiudadEntity").executeUpdate();
        em.createQuery("delete from PaisEntity").executeUpdate();
        em.createQuery("delete from SitioTuristicoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PaisEntity pais = factory.manufacturePojo(PaisEntity.class);
            em.persist(pais);
            paisData.add(pais);
        }
        for (int i = 0; i < 3; i++) {
            CiudadEntity entity = factory.manufacturePojo(CiudadEntity.class);
            entity.actualizarPais(paisData.get(0));

            em.persist(entity);
            data.add(entity);
        }
        SitioTuristicoEntity sitiot = factory.manufacturePojo(SitioTuristicoEntity.class);
        em.persist(sitiot);
        sitiot.actualizarCiudad(data.get(1));
        data.get(1).darSitios().add(sitiot);
    }
    
     @Test
    public void createCiudadTest() throws BusinessLogicException {
        CiudadEntity newEntity = factory.manufacturePojo(CiudadEntity.class);
        newEntity.actualizarPais(paisData.get(0));
        CiudadEntity result = ciudadLogic.createCiudad(newEntity);
        Assert.assertNotNull(result);
        CiudadEntity entity = em.find(CiudadEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.darNombre(), entity.darNombre());
        Assert.assertEquals(newEntity.darPais(), entity.darPais());
    }
    
    
    /**
     * Prueba para crear una ciudad con nombre inválido
     *@throws BusinessLogicException
     */     

    @Test(expected = BusinessLogicException.class)
    public void createBookTestConNombreInvalido() throws BusinessLogicException {
        CiudadEntity newEntity = factory.manufacturePojo(CiudadEntity.class);
        newEntity.actualizarPais(paisData.get(0));
        newEntity.actualizarNombre("");
        ciudadLogic.createCiudad(newEntity);
    }
    
    /**
     * Prueba para crear una ciudad con nombre inválido 3
     *@throws BusinessLogicException
     */     

    @Test(expected = BusinessLogicException.class)
    public void createBookTestConNombreInvalido3() throws BusinessLogicException {
        CiudadEntity newEntity = factory.manufacturePojo(CiudadEntity.class);
        newEntity.actualizarPais(paisData.get(0));
        newEntity.actualizarNombre(paisData.get(0).darNombre());
        ciudadLogic.createCiudad(newEntity);
    }

 /**
     * Prueba para actualizar una ciudad.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void updateCiudadTest() throws BusinessLogicException {
        CiudadEntity entity = data.get(0);
        CiudadEntity pojoEntity = factory.manufacturePojo(CiudadEntity.class);
        pojoEntity.setId(entity.getId());
        ciudadLogic.updateCiudad(pojoEntity.getId(), pojoEntity);
        CiudadEntity resp = em.find(CiudadEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.darNombre(), resp.darNombre());
        Assert.assertEquals(pojoEntity.darPais(), resp.darPais());

    }
    
    
    /**
     * Prueba para actualizar una ciudad con nombre inválido.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateBookConISBNInvalidoTest() throws BusinessLogicException {
        CiudadEntity entity = data.get(0);
        CiudadEntity pojoEntity = factory.manufacturePojo(CiudadEntity.class);
        pojoEntity.actualizarNombre("");
        pojoEntity.setId(entity.getId());
        ciudadLogic.updateCiudad(pojoEntity.getId(), pojoEntity);
    }
    
    
    /**
     * Prueba para actualizar una ciudad con nombre inválido.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateBookConISBNInvalidoTest2() throws BusinessLogicException {
        CiudadEntity entity = data.get(0);
        CiudadEntity pojoEntity = factory.manufacturePojo(CiudadEntity.class);
        pojoEntity.actualizarNombre(null);
        pojoEntity.setId(entity.getId());
        ciudadLogic.updateCiudad(pojoEntity.getId(), pojoEntity);
    }
    
    
   /**
     * Prueba para consultar una ciudad.
     * @throws BusinessLogicException
     */
    @Test
    public void getBookTest() throws BusinessLogicException {
        CiudadEntity entity = data.get(0);
        CiudadEntity resultEntity = ciudadLogic.getCiudad(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.darNombre(), resultEntity.darNombre());
        Assert.assertEquals(entity.darNombre(), resultEntity.darNombre());

        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
