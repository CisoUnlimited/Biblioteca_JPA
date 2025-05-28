package model.database.daoimpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.dto.Categoria;
import model.dto.Libro;
import model.database.dao.LibroDAO;
import utils.JPAUtil;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para realizar las acciones relacionadas con la tabla libro.
 * @author Ciso
 */
public class LibroDAOImpl implements LibroDAO {

    private final Connection con;

    public LibroDAOImpl(Connection con) {
        this.con = con;
    }

    @Override
    public boolean create(Libro libro) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                libro.setNombre(libro.getNombre());
                libro.setAutor(libro.getAutor());
                libro.setEditorial(libro.getEditorial());
                libro.setCategoria(libro.getCategoria());
                em.persist(libro);
                tx.commit();
                return true;
            } catch (Exception e) {
                if (tx.isActive()) {
                    tx.rollback();
                }
                e.printStackTrace();
                return false;
            }
        }
    }

    @Override
    public List<Libro> read() {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            Query q = em.createQuery("SELECT l FROM Libro l");
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // TODO CREAR METODO PARA MOSTRAR TODOS LOS LIBROS DE UNA CATEGORIA DADA
    @Override
    public List<Libro> readByCategory(Categoria categoria) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            Query q = em.createQuery("SELECT l FROM Libro l where l.categoria = :categoria");
            q.setParameter("categoria", categoria);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public boolean update(int id, Libro libroModificado) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Libro libro = em.find(Libro.class, id);
            libro.setNombre(libroModificado.getNombre());
            libro.setAutor(libroModificado.getAutor());
            libro.setEditorial(libroModificado.getEditorial());
            libro.setCategoria(libroModificado.getCategoria());
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Libro libro = em.find(Libro.class, id);
            if (libro != null) {
                System.out.println(libro);
                em.remove(libro);
            }
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean exists(int id) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            return em.find(Libro.class, id) != null;
        }
    }

    @Override
    public Libro readOne(int id) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            Libro libro = em.find(Libro.class, id);
            return libro;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
