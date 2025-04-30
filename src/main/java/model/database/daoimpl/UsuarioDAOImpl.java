package model.database.daoimpl;

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
