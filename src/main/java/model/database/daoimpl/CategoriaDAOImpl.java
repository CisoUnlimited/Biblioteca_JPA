package model.database.daoimpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.dto.Categoria;
import model.database.dao.CategoriaDAO;
import utils.JPAUtil;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para realizar las acciones relacionadas con la tabla categoria.
 * @author Ciso
 */
public class CategoriaDAOImpl implements CategoriaDAO {

    private final Connection con;

    public CategoriaDAOImpl(Connection con) {
        this.con = con;
    }

    @Override
    public boolean create(Categoria categoria) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                categoria.setCategoria(categoria.getCategoria());
                em.persist(categoria);
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
    public List<Categoria> read() {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            Query q = em.createQuery("SELECT c FROM Categoria c");
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public boolean update(int id, Categoria categoriaModificada) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Categoria categoria = em.find(Categoria.class, id);
            categoria.setCategoria(categoriaModificada.getCategoria());
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
            Categoria categoria = em.find(Categoria.class, id);
            if (categoria != null) {
                System.out.println(categoria);
                em.remove(categoria);
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
            return em.find(Categoria.class, id) != null;
        }
    }


    @Override
    public Categoria readOne(int id) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            Categoria categoria = em.find(Categoria.class, id);
            return categoria;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
