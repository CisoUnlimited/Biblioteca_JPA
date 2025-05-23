package model.database.dao;

import model.dto.Libro;
import java.util.List;

/**
 * Interfaz DAO para gestionar libros en la base de datos.
 * @author Ciso
 */
public interface LibroDAO {

    boolean create(Libro libro);

    List<Libro> read();

    boolean update(int id, Libro libro);

    boolean delete(int id);

    boolean find(int id);

    Libro getLibro(int id);

}
