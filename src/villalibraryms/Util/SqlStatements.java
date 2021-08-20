/*
 * Library Management System - Ayas Nasih, S1600655
 * 
 * 
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
    public static final String SEARCH_BOOKS_COLUMNS = "SELECT books.id as ID, title as Title, authors.name as Author, isbn as ISBN, publisher as Publisher, number_of_pages as \"Number of Pages\", publication_year as \"Publication Year\" FROM books ";
    
    // Book Items
    public static final String INSERT_BOOK_ITEM = "INSERT INTO `LIBRARY`.`book_items` (`book_id`, `barcode`) VALUES (?, ?);";
    public static final String BOOKITEMS_BY = "SELECT book_items.id as \"Copy ID\", books.id as \"Book ID\", books.title as Title, authors.name as Author, books.isbn as ISBN, book_items.barcode as Barcode, "
                + "publication_year as \"Publication Year\", books.publisher as Publisher, number_of_pages as \"Pages\", racks.`location` as \"Rack\", subjects.name as Subject "
                + "FROM book_items\n"
                + "LEFT JOIN books ON books.id = book_items.book_id "
                + "LEFT JOIN authors ON books.author_id = authors.id "
                + "LEFT JOIN racks ON racks.id = books.rack_id "
                + "LEFT JOIN subjects on books.subject_id = subjects.id";
    
    // Author
    public static final String FIND_AUTHOR = "SELECT * FROM authors WHERE `name` = ?";
    public static final String INSERT_AUTHOR = "INSERT INTO `authors` (`name`) VALUES(?);";

    // Misc
    public static final String ALL_SUBJECTS = "SELECT * FROM subjects";
    public static final String ALL_RACKS = "SELECT * FROM racks";

    // Users
    public static final String ALL_USERS = "SELECT users.id as ID, display_name as \"Display Name\", username as Username, roles.name as Role, is_active as Active FROM users INNER JOIN roles ON users.role_id = roles.id";
    public static final String ALL_USERS_LIKE = "SELECT users.id as ID, display_name as \"Display Name\", username as Username, roles.name as Role, is_active as Active FROM users INNER JOIN roles ON users.role_id = roles.id WHERE `display_name` LIKE ?";
    public static final String UPDATE_USER_ROLE = "UPDATE users SET role_id = ? WHERE id = ?";
    public static final String ALL_ROLES = "SELECT * FROM roles";
    public static final String INSERT_USER = "INSERT INTO users (`username`, `password`, `role_id`, `display_name`, `is_active`) VALUES (?,?,?,?,?)";
    public static final String DELETE_USER = "DELETE FROM users WHERE id = ?";
    public static final String UPDATE_USER = "UPDATE `users` SET `username` = ?, `password` = ?, `role_id` = ?, `display_name` = ?, `is_active` = ? WHERE id = ?";
    public static final String UPDATE_USER_STATUS = "UPDATE users SET is_active = ? WHERE id = ?";
    public static final String SELECT_USER = "SELECT * FROM `users` WHERE `id` = ?";
    
    // Membership Cards
    public static final String GET_MEMBERSHIP_CARD = "SELECT * FROM `membership_cards` WHERE `user_id` = ? ORDER BY `issued_at` DESC LIMIT 1";
    public static final String INSERT_MEMBERSHIP_CARD = "INSERT INTO `membership_cards` (`barcode`, `issued_at`, `expires_at`, `user_id`) VALUES (?,?,?,?)";
    public static final String DELETE_MEMBERSHIP_CARD = "DELETE FROM `membership_cards` WHERE user_id = ?";

}
