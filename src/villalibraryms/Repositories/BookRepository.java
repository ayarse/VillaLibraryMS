/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villalibraryms.Repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import villalibraryms.Util.DBUtils;
import villalibraryms.Models.Book;
import villalibraryms.Util.SqlStatements;

/**
 *
 * @author ayasnasih
 */
public class BookRepository {

    public static List<Book> getAllBooks() {
        List<Book> books = new ArrayList<Book>();

        DBUtils.setStmt(SqlStatements.ALL_BOOKS);
        ResultSet rs = DBUtils.executeQuery();
        try {
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getInt("rack_id"),
                        rs.getInt("subject_id"),
                        rs.getInt("author_id"),
                        rs.getString("title"),
                        rs.getString("publication_year"),
                        rs.getBoolean("borrowable"),
                        rs.getString("isbn"),
                        rs.getString("publisher"),
                        rs.getInt("number_of_pages")
                ));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        return books;
    }

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
        DBUtils.setStmt(SqlStatements.SEARCH_BOOKS_COLUMNS + "INNER JOIN authors ON books.author_id = authors.id WHERE " + column + " LIKE ?");
        DBUtils.setObject(1, "%" + value + "%", Types.VARCHAR);
        return DBUtils.executeQuery();
    }

    public static ResultSet findBookByTitle(String title) {
        DBUtils.setStmt(SqlStatements.BOOKS_BY_TITLE);
        DBUtils.setObject(1, "%" + title + "%", Types.VARCHAR);
        return DBUtils.executeQuery();

    }

    public static ResultSet findBookByAuthor(String authorName) {
        DBUtils.setStmt(SqlStatements.SEARCH_BOOKS_COLUMNS
                + "LEFT JOIN authors ON books.author_id = authors.id WHERE authors.name LIKE ?");
        DBUtils.setObject(1, "%" + authorName + "%", Types.VARCHAR);
        return DBUtils.executeQuery();
    }

    public static ResultSet findBookBySubject(String subjectName) {
        DBUtils.setStmt(SqlStatements.SEARCH_BOOKS_COLUMNS
                + "LEFT JOIN authors ON books.author_id = authors.id LEFT JOIN subjects ON books.subject_id = subjects.id WHERE subjects.name LIKE ?");
        DBUtils.setObject(1, "%" + subjectName + "%", Types.VARCHAR);
        return DBUtils.executeQuery();
    }

    private static int getBookIdByTitleAndISBN(String title, String ISBN) {
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
        }
        return -1;
    }

    public static void deleteBook(int bookId) {
        DBUtils.setStmt("DELETE FROM books WHERE id = ?");
        DBUtils.setObject(1, bookId, Types.BIGINT);
        DBUtils.executeUpdate();
    }

}
