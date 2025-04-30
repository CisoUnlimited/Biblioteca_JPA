package model.database.daoimpl;

import model.dto.Prestamo;
import model.database.dao.PrestamoDAO;
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
        return false;
    }

    @Override
    public List<Prestamo> read() {
        return null;
    }

    @Override
    public Prestamo readOne(int id) {
        return null;
    }

    @Override
    public boolean update(int id, Prestamo prestamo) {
        return false;
    }

    @Override
    public boolean delete(int idPrestamo) {
        return false;
    }

    @Override
    public boolean isAvailable(int idLibro) {
        return false;
    }

    @Override
    public int getIdPrestamo(int idLibro) {
        return 0;
    }

}
