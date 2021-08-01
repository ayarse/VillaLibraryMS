/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villalibraryms.Util;

/**
 *
 * @author ayasnasih
 */
public class SqlStatements {

    // Meta
    public static final String USE_DB = "USE LIBRARY";
    public static final String DROP_DB = "DROP DATABASE library";

    // Auth
    public static final String AUTH = "SELECT * FROM users WHERE username=? and password=?";

    // Books
    public static final String ALL_BOOKS = "SELECT * FROM books";
    public static final String BOOKS_BY_TITLE = "SELECT * FROM books WHERE title LIKE ?";
    public static final String BOOKS_BY_COL = "SELECT * FROM books WHERE ? LIKE ?";
    public static final String ALL_ISSUED = "SELECT * FROM issued";
    public static final String INSERT_BOOK = "INSERT INTO `LIBRARY`.`books` " 
            +"(`title`, `author_id`, `subject_id`, `publication_year`, `rack_id`, `isbn`, `publisher`, `borrowable`, `number_of_pages`) "
            +"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String BOOK_BY_TITLE_AND_ISBN = "SELECT * FROM books WHERE title = ? AND isbn = ?";
    
    // Book Items
    public static final String INSERT_BOOK_ITEM = "INSERT INTO `LIBRARY`.`book_items` (`book_id`, `barcode`) VALUES (?, ?);";
    
    // Author
    public static final String FIND_AUTHOR = "SELECT * FROM authors WHERE `name` = ?";
    public static final String INSERT_AUTHOR = "INSERT INTO `authors` (`name`) VALUES(?);";

    // Misc
    public static final String ALL_SUBJECTS = "SELECT * FROM subjects";
    public static final String ALL_RACKS = "SELECT * FROM racks";

    // Users
    public static final String ALL_USERS = "SELECT users.id, display_name as \"Display Name\", username as Username, roles.name as Role, is_active as Active FROM users INNER JOIN roles ON users.role_id = roles.id";
    public static final String UPDATE_USER_ROLE = "UPDATE users SET role_id = ? WHERE id = ?";
    public static final String ALL_ROLES = "SELECT * FROM roles";
    public static final String INSERT_USER = "INSERT INTO users (`username`, `password`, `role_id`, `display_name`, `is_active`) VALUES (?,?,?,?,?)";
    public static final String DELETE_USER = "DELETE FROM users WHERE id = ?";

}
