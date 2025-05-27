package model.database.dao;

import model.dto.Libro;
import model.dto.Prestamo;
import java.util.List;

/**
 * Interfaz DAO para gestionar préstamos en la base de datos. *
 * @author Ciso
 */
public interface PrestamoDAO {

    boolean create(Prestamo prestamo);

    List<Prestamo> read();

    boolean update(int id, Prestamo prestamo);

    boolean delete(int idPrestamo);

    boolean exists(int id);

    Prestamo readOne(int id);

    boolean isAvailable(Libro libro);

    int getIdPrestamo(Libro libro);

}
