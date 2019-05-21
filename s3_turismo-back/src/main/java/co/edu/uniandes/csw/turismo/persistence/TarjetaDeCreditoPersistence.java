/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.persistence;

import co.edu.uniandes.csw.turismo.entities.TarjetaDeCreditoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author jd.castrellon
 */

@Stateless
public class TarjetaDeCreditoPersistence {
    
private static final Logger LOGGER = Logger.getLogger(TarjetaDeCreditoPersistence.class.getName());


    @PersistenceContext(unitName = "turismoPU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param bookEntity objeto libro que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public TarjetaDeCreditoEntity create(TarjetaDeCreditoEntity bookEntity) {
        LOGGER.log(Level.INFO, "Creando un libro nuevo");
        em.persist(bookEntity);
        LOGGER.log(Level.INFO, "Libro creado");
        return bookEntity;
    }

    /**
     * Devuelve todos loslibros de la base de datos.
     *
     * @return una lista con todos los libros que encuentre en la base de datos,
     * "select u from TarjetaDeCreditoEntity u" es como un "select * from TarjetaDeCreditoEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
    public List<TarjetaDeCreditoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los libros");
        Query q = em.createQuery("select u from TarjetaDeCreditoEntity u");
        return q.getResultList();
    }

    /**
     * Busca si hay algun lubro con el id que se envía de argumento
     *
     * @param booksId: id correspondiente al libro buscado.
     * @return un libro.
     */
    public TarjetaDeCreditoEntity find(Long booksId) {
        LOGGER.log(Level.INFO, "Consultando el libro con id={0}", booksId);
        return em.find(TarjetaDeCreditoEntity.class, booksId);
    }

    /**
     * Actualiza un libro.
     *
     * @param bookEntity: el libro que viene con los nuevos cambios. Por ejemplo
     * el nombre pudo cambiar. En ese caso, se haria uso del método update.
     * @return un libro con los cambios aplicados.
     */
    public TarjetaDeCreditoEntity update(TarjetaDeCreditoEntity bookEntity) {
        LOGGER.log(Level.INFO, "Actualizando el libro con id={0}", bookEntity.getId());
        return em.merge(bookEntity);
    }

    /**
     *
     * Borra un libro de la base de datos recibiendo como argumento el id del
     * libro
     *
     * @param booksId: id correspondiente al libro a borrar.
     */
    public void delete(Long booksId) {
        LOGGER.log(Level.INFO, "Borrando el libro con id={0}", booksId);
        TarjetaDeCreditoEntity bookEntity = em.find(TarjetaDeCreditoEntity.class, booksId);
        em.remove(bookEntity);
    }

    /**
     * Busca si hay algun libro con el ISBN que se envía de argumento
     *
     * @param isbn: ISBN de la editorial que se está buscando
     * @return null si no existe ningun libro con el isbn del argumento. Si
     * existe alguno devuelve el primero.
     */
    public TarjetaDeCreditoEntity findByISBN(String isbn) {
        LOGGER.log(Level.INFO, "Consultando libros por isbn ", isbn);
        // Se crea un query para buscar libros con el isbn que recibe el método como argumento. ":isbn" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From TarjetaDeCreditoEntity e where e.isbn = :isbn", TarjetaDeCreditoEntity.class);
        // Se remplaza el placeholder ":isbn" con el valor del argumento 
        query = query.setParameter("isbn", isbn);
        // Se invoca el query se obtiene la lista resultado
        List<TarjetaDeCreditoEntity> sameISBN = query.getResultList();
        TarjetaDeCreditoEntity result;
        if (sameISBN == null) {
            result = null;
        } else if (sameISBN.isEmpty()) {
            result = null;
        } else {
            result = sameISBN.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar libros por isbn ", isbn);
        return result;
    }
}
