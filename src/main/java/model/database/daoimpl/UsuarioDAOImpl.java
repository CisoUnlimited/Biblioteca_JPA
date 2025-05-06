package model.database.daoimpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.dto.Usuario;
import model.database.dao.UsuarioDAO;
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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            usuario.setNombre(usuario.getNombre());
            usuario.setApellidos(usuario.getApellidos());
            em.persist(usuario);
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            em.close();
            emf.close();
        }
        return false;
    }

    @Override
    public List<Usuario> read() {
        return null;
    }

    @Override
    public boolean update(int id, Usuario usuario) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

}
