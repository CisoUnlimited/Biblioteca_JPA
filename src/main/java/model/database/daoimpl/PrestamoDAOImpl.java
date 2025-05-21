package model.database.daoimpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.dto.Libro;
import model.dto.Prestamo;
import model.database.dao.PrestamoDAO;
import utils.JPAUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para realizar las acciones relacionadas con la tabla prestamos.
 * @author Ciso
 */
public class PrestamoDAOImpl implements PrestamoDAO {

    private final Connection con;

    public PrestamoDAOImpl(Connection connection) {
        this.con = connection;
    }

    @Override
    public boolean create(Prestamo prestamo) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                prestamo.setIdLibro(prestamo.getIdLibro());
                prestamo.setIdUsuario(prestamo.getIdUsuario());
                prestamo.setFechaPrestamo(prestamo.getFechaPrestamo());
                em.persist(prestamo);
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
    public List<Prestamo> read() {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            Query q = em.createQuery("SELECT p FROM Prestamo p");
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Prestamo readOne(int id) {
        return null;
    }

    // TODO POR AQUI ME HE QUEDADO REVISAR
    @Override
    public boolean update(int id, Prestamo prestamoModificado) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Prestamo prestamo = em.find(Prestamo.class, id);
            prestamo.setIdLibro(prestamoModificado.getIdLibro());
            prestamo.setIdUsuario(prestamoModificado.getIdUsuario());
            prestamo.setFechaPrestamo(prestamoModificado.getFechaPrestamo());
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int idPrestamo) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Prestamo prestamo = em.find(Prestamo.class, idPrestamo);
                System.out.println(prestamo);
                em.remove(prestamo);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isAvailable(Libro libro) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            Query q = em.createQuery("SELECT p FROM Prestamo p where p.idLibro = :idLibro");
            q.setParameter("idLibro", libro);
            return q.getResultList().isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int getIdPrestamo(Libro libro) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            int idPrestamo = 0;
            Query q = em.createQuery("SELECT p.id FROM Prestamo p where p.idLibro = :idLibro");
            q.setParameter("idLibro", libro);
            List<Integer> list = q.getResultList();
            if (list.size() > 0) {
                return list.get(0);
            } else {
                System.out.println("No se encontro el libro");
                return 9999;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 9999;
        }
    }

}
