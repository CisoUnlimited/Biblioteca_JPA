package model.database.dao;

import model.dto.Libro;
import model.dto.Usuario;
import java.util.List;

/**
 * Interfaz DAO para gestionar usarios en la base de datos.
 * @author Ciso
 */
public interface UsuarioDAO {

    boolean create(Usuario usuario);

    List<Usuario> read();

    boolean update(int id, Usuario usuario);

    boolean delete(int id);

    boolean find(int id);

    Usuario getUsuario(int id);

}
