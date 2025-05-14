package model.database.daoimpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import model.dto.Categoria;
import model.database.dao.CategoriaDAO;
import utils.JPAUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        return true;
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
    public boolean update(int id, Categoria categoria) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean find(int id) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            return em.find(Categoria.class, id) != null;
        }
    }


    @Override
    public Categoria getCategoria(int id) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            Categoria categoria = em.find(Categoria.class, id);
            return categoria;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
