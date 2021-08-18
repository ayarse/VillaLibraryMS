/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villalibraryms.Repositories;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import villalibraryms.Models.Author;
import villalibraryms.Util.DBUtils;
import villalibraryms.Models.Book;
import villalibraryms.Models.BookItem;
import villalibraryms.Models.Borrow;
import villalibraryms.Models.Rack;
import villalibraryms.Models.Subject;
import villalibraryms.Models.User;
import villalibraryms.Util.SqlStatements;

/**
 *
 * @author ayasnasih
 */
public class BookRepository {
    
    public static int MAX_BORROWS = 4;
    public static int MAX_BORROW_DAYS = 20;

    public static void addBook(
            String title,
            String authorName,
            String ISBN,
            String publicationYear,
            String publisher,
            String numberOfPages,
            int subjectId,
            int rackId,
            String[] barcodes,
            Boolean borrowable) {

        int authorId = AuthorRepository.getAuthorIdByNameOrCreate(authorName);

        DBUtils.setStmt(SqlStatements.INSERT_BOOK);
        DBUtils.setObject(1, title, Types.VARCHAR);
        DBUtils.setObject(2, authorId, Types.BIGINT);
        DBUtils.setObject(3, subjectId, Types.BIGINT);
        DBUtils.setObject(4, publicationYear, Types.INTEGER);
        DBUtils.setObject(5, rackId, Types.BIGINT);
        DBUtils.setObject(6, ISBN, Types.VARCHAR);
        DBUtils.setObject(7, publisher, Types.VARCHAR);
        DBUtils.setObject(8, !borrowable, Types.TINYINT);
        DBUtils.setObject(9, numberOfPages, Types.INTEGER);
        DBUtils.executeUpdate();

        int bookId = getBookIdByTitleAndISBN(title, ISBN);
        insertBookItems(bookId, barcodes);

    }

    private static void insertBookItems(int bookId, String[] barcodes) {
        for (String barcode : barcodes) {
            DBUtils.setStmt(SqlStatements.INSERT_BOOK_ITEM);
            DBUtils.setObject(1, bookId, Types.BIGINT);
            DBUtils.setObject(2, barcode, Types.VARCHAR);
            DBUtils.executeUpdate();
        }
    }

    public static ResultSet findBookBy(String column, String value) {
        DBUtils.setStmt(SqlStatements.BOOKITEMS_BY + " WHERE " + column + " LIKE ?");
        DBUtils.setObject(1, "%" + value + "%", Types.VARCHAR);
        return DBUtils.executeQuery();
    }

    public static ResultSet findBookByTitle(String title) {
        String sql = SqlStatements.BOOKITEMS_BY;
        sql = sql + "WHERE books.title LIKE ?";
        DBUtils.setStmt(sql);
        DBUtils.setObject(1, "%" + title + "%", Types.VARCHAR);
        return DBUtils.executeQuery();

    }

    public static ResultSet findBookByAuthor(String authorName) {
        DBUtils.setStmt(SqlStatements.BOOKITEMS_BY + " WHERE authors.name LIKE ?");
        DBUtils.setObject(1, "%" + authorName + "%", Types.VARCHAR);
        return DBUtils.executeQuery();
    }

    public static ResultSet findBookBySubject(String subjectName) {
        DBUtils.setStmt(SqlStatements.BOOKITEMS_BY + " WHERE subjects.name LIKE ?");
        DBUtils.setObject(1, "%" + subjectName + "%", Types.VARCHAR);
        return DBUtils.executeQuery();
    }

    public static BookItem findBookByBarcode(String barcode) {
        String sql = "SELECT book_items.id as bookItemId, book_id, barcode, borrows.id as borrowId, borrowed_date, returned_date FROM book_items "
                + "LEFT JOIN borrows ON borrows.book_item_id = book_items.id WHERE barcode = ? ORDER BY borrowed_date DESC LIMIT 1";
        DBUtils.setStmt(sql);
        DBUtils.setObject(1, barcode, Types.VARCHAR);
        ResultSet rs = DBUtils.executeQuery();
        try {
            if (rs.next()) {
                Book book = findBookById(rs.getInt("book_id"));
                return new BookItem(
                        book,
                        rs.getInt("bookItemId"),
                        rs.getString("barcode"),
                        rs.getDate("borrowed_date"),
                        rs.getDate("returned_date"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    public static ResultSet issuedBooks(boolean onlyReturned) {
        String sql = "SELECT borrows.id as ID, books.title as Title, authors.`name` as Author, books.isbn as ISBN, book_items.barcode as Barcode, "
                + "books.publisher as Publisher, users.display_name as \"Borrowed By\", borrows.borrowed_date as \"Borrowed Date\", "
                + "(CASE WHEN borrows.returned_date IS NOT NULL THEN \"Yes\" ELSE \"No\" END) as \"Returned\" FROM borrows "
                + "LEFT JOIN book_items ON borrows.book_item_id = book_items.id "
                + "LEFT JOIN books on book_items.book_id = books.id "
                + "LEFT JOIN authors ON books.author_id = authors.id "
                + "LEFT JOIN users ON users.id = borrows.user_id ";
        if (onlyReturned) {
            sql = sql + "WHERE borrows.returned_date IS NULL ORDER BY borrows.borrowed_date DESC";
        } else {
            sql = sql + "ORDER BY borrows.borrowed_date DESC";
        }

        DBUtils.setStmt(sql);
        return DBUtils.executeQuery();
    }

    public static ResultSet issuedBooks(String searchStr) {
        String sql = "SELECT borrows.id as ID, books.title as Title, authors.`name` as Author, books.isbn as ISBN, book_items.barcode as Barcode, "
                + "books.publisher as Publisher, users.display_name as \"Borrowed By\", borrows.borrowed_date as \"Borrowed Date\", "
                + "(CASE WHEN borrows.returned_date IS NOT NULL THEN \"Yes\" ELSE \"No\" END) as \"Returned\" FROM borrows "
                + "LEFT JOIN book_items ON borrows.book_item_id = book_items.id "
                + "LEFT JOIN books on book_items.book_id = books.id "
                + "LEFT JOIN authors ON books.author_id = authors.id "
                + "LEFT JOIN users ON users.id = borrows.user_id "
                + "WHERE users.display_name LIKE ? ORDER BY borrows.borrowed_date DESC";

        DBUtils.setStmt(sql);
        DBUtils.setObject(1, "%" + searchStr + "%", Types.VARCHAR);
        return DBUtils.executeQuery();
    }

    public static int borrowedCount(int userId) {
        DBUtils.setStmt("SELECT COUNT(*) AS count FROM `borrows` WHERE `user_id` = ? AND `returned_date` IS NULL");
        DBUtils.setObject(1, userId, Types.BIGINT);
        ResultSet rs = DBUtils.executeQuery();
        try {
            if (rs.next()) {
                int count = rs.getInt("count");
                return count;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static boolean bookItemAvailable(String barcode) {
        DBUtils.setStmt("SELECT COUNT(*) as count FROM `book_items` LEFT JOIN `borrows` ON `borrows`.`book_item_id` = `book_items`.`id` WHERE `barcode` = ? AND `returned_date` IS NULL AND `borrowed_date` IS NOT NULL");
        DBUtils.setObject(1, barcode, Types.VARCHAR);
        ResultSet rs = DBUtils.executeQuery();
        try {
            if (rs.next()) {
                int unreturned = rs.getInt("count");
                if (unreturned > 0) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (SQLException ex) {
            return false;
        }
        return false;
    }

    public static void issueBook(BookItem book, User user) {
        String sql = "INSERT INTO borrows (`book_item_id`, `user_id`, `borrowed_date`) "
                + "VALUES (?,?,?)";
        DBUtils.setStmt(sql);
        DBUtils.setObject(1, book.getBookItemId(), Types.BIGINT);
        DBUtils.setObject(2, user.getId(), Types.BIGINT);
        DBUtils.setObject(3, LocalDate.now(), Types.DATE);
        DBUtils.executeUpdate();
    }

    public static Borrow findBorrowedByBarcode(String barcode) {
        String sql = "SELECT  borrows.*, book_items.barcode  FROM borrows LEFT JOIN book_items ON borrows.book_item_id = book_items.id WHERE book_items.barcode = ? AND returned_date IS NULL LIMIT 1";
        DBUtils.setStmt(sql);
        DBUtils.setObject(1, barcode, Types.VARCHAR);
        ResultSet rs = DBUtils.executeQuery();
        try {
            if (rs.next()) {
                return new Borrow(
                        rs.getInt("id"),
                        findBookByBarcode(barcode),
                        UserRepository.find(rs.getInt("user_id")),
                        rs.getDate("borrowed_date"),
                        rs.getDate("returned_date")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Book findBookById(int bookId) {

        String bookColumns = "books.id as bookId, title, publication_year, isbn, publisher, borrowable, number_of_pages, author_id, rack_id, subject_id, "
                + "authors.id as authorId, authors.name as authorName, "
                + "racks.id as rackId, location, "
                + "subjects.id as subjectId, subjects.name as subjectName, "
                + "COUNT(`book_items`.`book_id`) as totalCopies";

        DBUtils.setStmt("SELECT " + bookColumns + " FROM `books` "
                + "LEFT JOIN `racks` ON `racks`.`id`=`books`.`rack_id` "
                + "LEFT JOIN `authors` ON `authors`.`id` = `books`.`author_id` "
                + "LEFT JOIN `subjects` ON `subjects`.`id` = `books`.`subject_id` "
                + "LEFT JOIN `book_items` ON `book_items`.`book_id` = `books`.`id`"
                + "WHERE `books`.`id` = ?");

        DBUtils.setObject(1, bookId, Types.VARCHAR);
        ResultSet rs = DBUtils.executeQuery();
        try {
            if (rs.next()) {
                return new Book(
                        rs.getInt("bookId"),
                        new Rack(
                                rs.getInt("rack_id"),
                                rs.getString("location")
                        ),
                        new Subject(
                                rs.getInt("subjectId"),
                                rs.getString("subjectName")
                        ),
                        new Author(
                                rs.getInt("authorId"),
                                rs.getString("authorName")
                        ),
                        rs.getString("title"),
                        rs.getString("publication_year"),
                        rs.getBoolean("borrowable"),
                        rs.getString("isbn"),
                        rs.getString("publisher"),
                        rs.getInt("number_of_pages"),
                        rs.getInt("totalCopies")
                );
            }
        } catch (SQLException ex) {
            return null;
        }
        return null;
    }

    public static Integer availableCopies(int bookId) {
        DBUtils.setStmt("SELECT (SELECT COUNT(book_items.id) FROM book_items LEFT JOIN borrows ON borrows.book_item_id = book_items.id WHERE book_id = ? AND borrows.returned_date IS NULL AND borrows.borrowed_date IS NOT NULL) as borrowedCopies, (SELECT COUNT(*) as bookCount FROM book_items WHERE book_id = ?) as totalCopies");
        DBUtils.setObject(1, bookId, Types.BIGINT);
        DBUtils.setObject(2, bookId, Types.BIGINT);
        ResultSet rs = DBUtils.executeQuery();

        try {
            if (rs.next()) {
                int borrowed = rs.getInt("borrowedCopies");
                int total = rs.getInt("totalCopies");
                return total - borrowed;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    private static Integer getBookIdByTitleAndISBN(String title, String ISBN) {
        DBUtils.setStmt(SqlStatements.BOOK_BY_TITLE_AND_ISBN);
        DBUtils.setObject(1, title, Types.VARCHAR);
        DBUtils.setObject(2, ISBN, Types.BIGINT);
        ResultSet rs = DBUtils.executeQuery();
        try {
            if (rs.next()) {
                int id = rs.getInt("id");
                return id;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookRepository.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }

    public static void deleteBook(int bookId) {
        DBUtils.setStmt("DELETE FROM books WHERE id = ?");
        DBUtils.setObject(1, bookId, Types.BIGINT);
        DBUtils.executeUpdate();
    }

    public static void returnBook(Borrow borrow, LocalDate date) {
        DBUtils.setStmt("UPDATE `borrows` SET `returned_date` = ? WHERE `id` = ?");
        DBUtils.setObject(1, date, Types.DATE);
        DBUtils.setObject(2, borrow.getId(), Types.BIGINT);
        DBUtils.executeUpdate();
    }

    public static void createFine(Borrow borrow, LocalDate fineDate, double fineAmount) {
        DBUtils.setStmt("INSERT INTO `fines` (`borrow_id`, `fine_date`, `fine_amount`) VALUES (?, ?, ?)");
        DBUtils.setObject(1, borrow.getId(), Types.BIGINT);
        DBUtils.setObject(2, fineDate, Types.DATE);
        DBUtils.setObject(3, fineAmount, Types.DOUBLE);
        DBUtils.executeUpdate();
    }

    public static void reserveBook(int bookItemId, int userId) {
        DBUtils.setStmt("INSERT INTO `reservations` (`book_item_id`, `user_id`, `reservation_date`) VALUES (?, ?, ?)");
        DBUtils.setObject(1, bookItemId, Types.BIGINT);
        DBUtils.setObject(2, userId, Types.BIGINT);
        DBUtils.setObject(3, LocalDate.now(), Types.DATE);
        DBUtils.executeUpdate();
    }

    public static boolean reservationExists(int bookItemId, int userId) {
        DBUtils.setStmt("SELECT id FROM `reservations` WHERE `book_item_id` = ? AND `user_id` = ?");
        DBUtils.setObject(1, bookItemId, Types.BIGINT);
        DBUtils.setObject(2, userId, Types.BIGINT);
        ResultSet rs = DBUtils.executeQuery();
        try {
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static ResultSet myBooks(int userId) {
//        String sql = "SELECT books.title, borrowed_date,  FROM borrows "
//                + "LEFT JOIN book_items ON book_items.id = borrows.book_item_id LEFT JOIN books ON books.id = book_items.book_id LEFT JOIN authors on "
//                + "WHERE borrows.user_id = ?";
        String sql = "SELECT borrows.id as ID, books.title as Title, authors.`name` as Author, books.isbn as ISBN, book_items.barcode as Barcode, "
                + "books.publisher as Publisher, users.display_name as \"Borrowed By\", borrows.borrowed_date as \"Borrowed Date\", "
                + "(CASE WHEN borrows.returned_date IS NOT NULL THEN \"Yes\" ELSE \"No\" END) as \"Returned\" FROM borrows "
                + "LEFT JOIN book_items ON borrows.book_item_id = book_items.id "
                + "LEFT JOIN books on book_items.book_id = books.id "
                + "LEFT JOIN authors ON books.author_id = authors.id "
                + "LEFT JOIN users ON users.id = borrows.user_id "
                + "WHERE users.id = ? ORDER BY borrows.borrowed_date DESC";
        DBUtils.setStmt(sql);
        DBUtils.setObject(1, userId, Types.BIGINT);
        return DBUtils.executeQuery();
    }

}
