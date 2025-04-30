package model.database.daoimpl;

import model.dto.Categoria;
import model.database.dao.CategoriaDAO;
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
        return null;
    }

    @Override
    public boolean update(int id, Categoria categoria) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

}
