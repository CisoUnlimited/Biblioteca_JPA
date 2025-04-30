package model.database.daoimpl;

import model.dto.Libro;
import model.database.dao.LibroDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        return false;
    }

    @Override
    public List<Libro> read() {
        return null;
    }

    @Override
    public boolean update(int id, Libro libro) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

}
