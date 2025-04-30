package model.database.dao;

import model.dto.Prestamo;
import java.util.List;

/**
 * Interfaz DAO para gestionar préstamos en la base de datos. *
 * @author Ciso
 */
public interface PrestamoDAO {

    boolean create(Prestamo prestamo);

    List<Prestamo> read();

    Prestamo readOne(int id);

    boolean update(int id, Prestamo prestamo);

    boolean delete(int idPrestamo);

    boolean isAvailable(int idLibro);

    int getIdPrestamo(int idLibro);

}
