package model.database.daoimpl;

import jakarta.persistence.*;
import model.dto.Usuario;
import model.database.dao.UsuarioDAO;
import utils.JPAUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para realizar las acciones relacionadas con la tabla usuario.
 * @author Ciso
 */
public class UsuarioDAOImpl implements UsuarioDAO {

    private final Connection con;

    public UsuarioDAOImpl(Connection con) {
        this.con = con;
    }

    @Override
    public boolean create(Usuario usuario) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                usuario.setNombre(usuario.getNombre());
                usuario.setApellidos(usuario.getApellidos());
                em.persist(usuario);
                //em.flush();
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
    public List<Usuario> read() {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            Query q = em.createQuery("SELECT u FROM Usuario u");
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public boolean update(int id, Usuario usuarioModificado) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Usuario usuario = em.find(Usuario.class, id);
            usuario.setNombre(usuarioModificado.getNombre());
            usuario.setApellidos(usuarioModificado.getApellidos());
            em.merge(usuario);
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
            Usuario usuario = em.find(Usuario.class, id);
            if (usuario != null) {
                System.out.println(usuario);
                em.remove(usuario);
            }
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
