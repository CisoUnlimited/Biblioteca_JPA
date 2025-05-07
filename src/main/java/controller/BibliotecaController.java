package controller;

import model.database.ConnectionSingleton;
import model.database.dao.CategoriaDAO;
import model.database.dao.LibroDAO;
import model.database.dao.PrestamoDAO;
import model.database.dao.UsuarioDAO;
import model.database.daoimpl.CategoriaDAOImpl;
import model.database.daoimpl.LibroDAOImpl;
import model.database.daoimpl.PrestamoDAOImpl;
import model.database.daoimpl.UsuarioDAOImpl;
import model.dto.Categoria;
import model.dto.Libro;
import model.dto.Prestamo;
import model.dto.Usuario;
import utils.JPAUtil;
import view.Formatters;
import view.Menu;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BibliotecaController {
    private final LibroDAO libroDAO;
    private final PrestamoDAO prestamoDAO;
    private final UsuarioDAO usuarioDAO;
    private final CategoriaDAO categoriaDAO;
    private final Menu menu;

    public BibliotecaController(Connection connection) {
        this.libroDAO = new LibroDAOImpl(connection);
        this.prestamoDAO = new PrestamoDAOImpl(connection);
        this.usuarioDAO = new UsuarioDAOImpl(connection);
        this.categoriaDAO = new CategoriaDAOImpl(connection);
        this.menu = new Menu();
    }

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            Connection connection = ConnectionSingleton.getConnection(scanner);
            BibliotecaController controller = new BibliotecaController(connection);
            controller.logicaMenuBiblioteca(scanner);
            JPAUtil.close();
            ConnectionSingleton.closeConnection();
        }

    }

    public void logicaMenuBiblioteca(Scanner scanner) {
        boolean salir = false;
        while (!salir) {
            menu.menuBiblioteca();
            int opcion = scanner.nextInt();
            scanner.nextLine();
            try {
                switch (opcion) {
                    case 1 ->
                            logicaSubmenuUsuarios(scanner);
                    case 2 ->
                            logicaSubmenuLibros(scanner);
                    case 3 ->
                            logicaSubmenuPrestamos(scanner);
                    case 0 ->
                            salir = true;
                    default ->
                            System.out.println("Opción no válida. Por favor, intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error en el menú Biblioteca: Por favor, ingrese un número.");
                scanner.nextLine();
            }
        }
    }

    private void logicaSubmenuUsuarios(Scanner scanner) {
        boolean volver = false;
        while (!volver) {
            menu.submenuUsuarios();
            int opcion = scanner.nextInt();
            scanner.nextLine();
            try {
                switch (opcion) {
                    case 1 ->
                            consultarUsuarios();
                    case 2 ->
                            altaUsuario(scanner);
                    case 3 ->
                            bajaUsuario(scanner);
                    case 4 ->
                            modificarUsuario(scanner);
                    case 0 ->
                            volver = true;
                    default ->
                            System.out.println("Opción no válida. Por favor, intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error en el menú Usuarios: Por favor, ingrese un número.");
                scanner.nextLine();
            }
        }
    }

    private void logicaSubmenuLibros(Scanner scanner) {
        boolean volver = false;
        while (!volver) {
            menu.submenuLibros();
            int opcion = scanner.nextInt();
            scanner.nextLine();
            try {
                switch (opcion) {
                    case 1 ->
                            consultarLibros();
                    case 2 ->
                            altaLibro(scanner);
                    case 3 ->
                            bajaLibro(scanner);
                    case 4 ->
                            modificarLibro(scanner);
                    case 5 ->
                            logicaSubmenuCategorias(scanner);
                    case 0 ->
                            volver = true;
                    default ->
                            System.out.println("Opción no válida. Por favor, intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error en el menú Libros: Por favor, ingrese un número.");
                scanner.nextLine();
            }
        }
    }

    private void logicaSubmenuCategorias(Scanner scanner) {
        boolean volver = false;
        while (!volver) {
            menu.submenuCategorias();
            int opcion = scanner.nextInt();
            scanner.nextLine();
            try {
                switch (opcion) {
                    case 1 ->
                            consultarCategorias();
                    case 2 ->
                            altaCategoria(scanner);
                    case 3 ->
                            bajaCategoria(scanner);
                    case 4 ->
                            modificarCategoria(scanner);
                    case 0 ->
                            volver = true;
                    default ->
                            System.out.println("Opción no válida. Por favor, intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error en el menú Categorías: Por favor, ingrese un número.");
                scanner.nextLine();
            }
        }
    }

    private void logicaSubmenuPrestamos(Scanner scanner) {
        boolean volver = false;
        while (!volver) {
            menu.submenuPrestamos();
            int opcion = scanner.nextInt();
            scanner.nextLine();
            try {
                switch (opcion) {
                    case 1 ->
                            consultarPrestamos();
                    case 2 ->
                            altaPrestamo(scanner);
                    case 3 ->
                            bajaPrestamo(scanner);
                    case 4 ->
                            modificarPrestamo(scanner);
                    case 0 ->
                            volver = true;
                    default ->
                            System.out.println("Opción no válida. Por favor, intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error en el menú Préstamos: Por favor, ingrese un número.");
                scanner.nextLine();
            }
        }
    }

    private boolean consultarUsuarios() {
        System.out.println("\n--- Listar todos los usuarios ---");
        List<Usuario> usuarios = usuarioDAO.read();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return false;
        } else {
            for (Usuario usuario : usuarios) {
                System.out.println(usuario);
            }
            return true;
        }
    }

    private void altaUsuario(Scanner scanner) {
        consultarUsuarios();
        System.out.println("\n--- Dar de alta nuevo usario ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = scanner.nextLine();

        Usuario nuevoUsuario = new Usuario(nombre, apellidos);
        try {
            if (usuarioDAO.create(nuevoUsuario)) {
                System.out.println("Nuevo usuario agregado con éxito.");
            } else {
                System.out.println("Error al agregar el usuario.");
            }
        } catch (Exception e) {

        }
    }

    private void bajaUsuario(Scanner scanner) {
        if (consultarUsuarios()) {
            System.out.println("\n--- Eliminar usuario ---");
            System.out.print("ID del usuario a eliminar: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            if (usuarioDAO.delete(id)) {
                System.out.println("Usuario eliminado con éxito.");
            } else {
                System.out.println("El ID introducido no se encuentra en la base de datos.");
                System.out.println("Error al eliminar el usuario.");
            }
        }
    }

    private void modificarUsuario(Scanner scanner) {
        if (consultarUsuarios()) {
            System.out.println("\n--- Modificar usuario ---");
            System.out.print("ID del usuario a modificar: ");
            int id = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer
            // TODO: Método que busque un usuario por su ID y devuelva un bool si lo encuentra o no

            System.out.print("Nuevo nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Nuevos apellidos: ");
            String apellidos = scanner.nextLine();

            Usuario usuarioModificado = new Usuario(nombre, apellidos);
            if (usuarioDAO.update(id, usuarioModificado)) {
                System.out.println("Usuario modificado con éxito.");
            } else {
                System.out.println("Error al modificar el usuario.");
            }
        }
    }

    private boolean consultarLibros() {
        System.out.println("\n--- Listar todos los libros ---");
        List<Libro> libros = libroDAO.read();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return false;
        } else {
            for (Libro libro : libros) {
                System.out.println(libro);
            }
            return true;
        }
    }

    private void altaLibro(Scanner scanner) {
        consultarLibros();
        consultarCategorias();
        System.out.println("\n--- Dar de alta nuevo libro ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Editorial: ");
        String editorial = scanner.nextLine();
        System.out.print("Categoría (ID): ");
        // TODO: Método que, a partir de un ID proporcionado, devuelva una Categoria
        scanner.nextLine();

//        Libro nuevoLibro = new Libro(0, nombre, autor, editorial, categoria);
//        if (libroDAO.create(nuevoLibro)) {
//            System.out.println("Libro agregado con éxito.");
//        } else {
//            System.out.println("Error al agregar el libro.");
//        }
    }

    private void bajaLibro(Scanner scanner) {
        if (consultarLibros()) {
            System.out.println("\n--- Eliminar libro ---");
            System.out.print("ID del libro a borrar: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            if (libroDAO.delete(id)) {
                System.out.println("Libro eliminado con éxito.");
            } else {
                System.out.println("El ID introducido no se encuentra en la base de datos.");
                System.out.println("Error al eliminar el libro.");
            }
        }
    }

    private void modificarLibro(Scanner scanner) {
        if (consultarLibros()) {
            System.out.println("\n--- Modificar libro ---");
            System.out.print("ID del libro a modificar: ");
            int id = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer

            System.out.print("Nuevo nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Nuevo autor: ");
            String autor = scanner.nextLine();
            System.out.print("Nueva editorial: ");
            String editorial = scanner.nextLine();
            System.out.print("Nueva categoría (ID): ");
            int categoria = scanner.nextInt(); // Si aquí introduzco una letra, sube el error al menú y lanza inputMismatchException
            scanner.nextLine();

//            Libro libroModificado = new Libro(id, nombre, autor, editorial, categoria);
//            if (libroDAO.update(id, libroModificado)) {
//                System.out.println("Libro modificado con éxito.");
//            } else {
//                System.out.println("Al menos uno de los dos siguientes elementos no existe en la base de datos: 'ID del libro', 'ID de la categoría'.");
//                System.out.println("Error al modificar el libro.");
//            }
        }
    }

    private boolean consultarCategorias() {
        System.out.println("\n--- Listar todas las categorías ---");
        List<Categoria> categorias = categoriaDAO.read();
        if (categorias.isEmpty()) {
            System.out.println("No hay categorías registradas.");
            return false;
        } else {
            for (Categoria categoria : categorias) {
                System.out.println(categoria);
            }
            return true;
        }
    }

    private void altaCategoria(Scanner scanner) {
        consultarCategorias();
        System.out.println("\n--- Dar de alta nueva categoría ---");
        System.out.print("Nombre: ");
        String categoria = scanner.nextLine();

        Categoria nuevaCategoria = new Categoria(0, categoria);
        if (categoriaDAO.create(nuevaCategoria)) {
            System.out.println("Categoría agregada con éxito.");
        } else {
            System.out.println("Error al agregar la categoría.");
        }
    }

    private void bajaCategoria(Scanner scanner) {
        if (consultarCategorias()) {
            System.out.println("\n--- Eliminar categoría ---");
            System.out.print("ID de la categoría a eliminar: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            if (categoriaDAO.delete(id)) {
                System.out.println("Categoría eliminada con éxito.");
            } else {
                System.out.println("Error al eliminar la categoría.");
            }
        }
    }

    private void modificarCategoria(Scanner scanner) {
        if (consultarCategorias()) {
            System.out.println("\n--- Modificar categoría ---");
            System.out.print("ID de la categoría a modificar: ");
            int id = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer

            System.out.print("Nuevo nombre: ");
            String nombre = scanner.nextLine();

            Categoria categoriaModificada = new Categoria(id, nombre);
            if (categoriaDAO.update(id, categoriaModificada)) {
                System.out.println("Categoría modificada con éxito.");
            } else {

                System.out.println("Error al modificar la categoría.");
            }
        }
    }

    private boolean consultarPrestamos() {
        System.out.println("\n--- Listar todos los préstamos ---");
        List<Prestamo> prestamos = prestamoDAO.read();
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos activos.");
            return false;
        } else {
            for (Prestamo prestamo : prestamos) {
                System.out.println(prestamo);
            }
            return true;
        }
    }

    private void altaPrestamo(Scanner scanner) {
        consultarPrestamos();
        consultarUsuarios();
        consultarLibros();
        System.out.println("\n--- Gestionar préstamo ---");
        System.out.print("ID del libro: ");
        int idLibro = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        System.out.print("ID del usuario: ");
        int idUsuario = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        if (!prestamoDAO.isAvailable(idLibro)) {
            System.out.println("El libro ya está prestado.");
            return;
        }

        Timestamp fechaPrestamo = new Timestamp(System.currentTimeMillis());
//        Prestamo prestamo = new Prestamo(0, idLibro, idUsuario, fechaPrestamo);
//
//        if (prestamoDAO.create(prestamo)) {
//            System.out.println("Préstamo registrado con éxito.");
//        } else {
//            System.out.println("Al menos uno de los dos siguientes elementos no existe en la base de datos: 'ID del libro', 'ID del usuario'.");
//            System.out.println("Error al registrar el préstamo.");
//        }
    }

    private void bajaPrestamo(Scanner scanner) {
        if (consultarPrestamos()) {
            System.out.println("\n--- Gestionar devolución ---");
            System.out.print("ID del libro: ");
            int idLibro = scanner.nextInt();
            scanner.nextLine();

            // Verifica si el libro está prestado
            int idPrestamo = prestamoDAO.getIdPrestamo(idLibro);

            if (idPrestamo == -1) {
                System.out.println("El libro no está actualmente prestado.");
                return;
            }

            // Elimina el préstamo de la base de datos
            if (prestamoDAO.delete(idPrestamo)) {
                System.out.println("Devolución registrada con éxito.");
            } else {
                System.out.println("Error al registrar la devolución.");
            }
        }
    }

    private void modificarPrestamo(Scanner scanner) {
        if (consultarPrestamos()) {
            System.out.println("\n--- Modificar préstamo ---");
            Prestamo prestamo = null;
            System.out.print("ID del préstamo a modificar: ");
            int idPrestamo = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer
            prestamo = prestamoDAO.readOne(idPrestamo);

            System.out.print("Nuevo idLibro: ");
            int idLibro = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nuevo idUsuario: ");
            int idUsuario = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nueva fecha (Presione INTRO en caso de querer mantener la fecha anterior): ");
            String nuevaFechaPrestamo = scanner.nextLine();
            Timestamp fechaPrestamo = null;
            if (nuevaFechaPrestamo.isEmpty()) {
                fechaPrestamo = prestamo.getFechaPrestamo();
            } else {
                fechaPrestamo = Formatters.dateTimestampFormatter(nuevaFechaPrestamo);
            }
//            Prestamo newPrestamo = new Prestamo(idPrestamo, idLibro, idUsuario, fechaPrestamo);
//
//            if (prestamoDAO.update(idPrestamo, newPrestamo)) {
//                System.out.println("Préstamo modificado con éxito.");
//            } else {
//                System.out.println("Al menos uno de los dos siguientes elementos no existe en la base de datos: 'ID del libro', 'ID del usuario'.");
//                System.out.println("Error al modificar el préstamo.");
//            }
        }
    }

}
