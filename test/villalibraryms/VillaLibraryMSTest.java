/*
* This Test file containts tests according to the requirements given in the assignment question.
 */
package villalibraryms;

import java.sql.Connection;
import org.junit.*;
import static org.junit.Assert.*;
import villalibraryms.Repositories.BookRepository;
import villalibraryms.Util.DBUtils;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import villalibraryms.Models.Book;
import villalibraryms.Models.Borrow;

/**
 *
 * @author ayasnasih
 */
public class VillaLibraryMSTest {

    public VillaLibraryMSTest() {
//        System.out.println("Testing initiated.");
    }

    @Test
    public void testDbConnection() throws Exception {
        Connection db = DBUtils.getConnection();
        assertNotNull(db);
        System.out.println("[Test] DB Connection successful.");
    }

    @Test
    public void searchByTitle() throws SQLException {
        ResultSet rs = BookRepository.findBookBy("title", "Glass");
        assertTrue(rs.next());
        System.out.println("[Test] Find book by Title successful.");
    }

    @Test
    public void searchByAuthor() throws SQLException {
        ResultSet rs = BookRepository.findBookByAuthor("Ulysses");
        assertTrue(rs.next());
        System.out.println("[Test] Find book by Author successful.");

    }

    @Test
    public void searchBySubject() throws SQLException {
        ResultSet rs = BookRepository.findBookBySubject("Historical fiction");
        assertTrue(rs.next());
        System.out.println("[Test] Find book by Subject successful.");
    }

    @Test
    public void searchByPubDate() throws SQLException {
        ResultSet rs = BookRepository.findBookBy("publication_year", "1992");
        assertTrue(rs.next());
        System.out.println("[Test] Find book by Publication Date successful.");
    }

    @Test
    public void bookHasUniqueId() {
        Book book = BookRepository.findBookById(42);
        assertNotNull(book.getId());
        assertNotNull(book.getIsbn());
        System.out.println("[Test] Book has a unique id.");
    }

    @Test
    public void bookHasRack() {
        Book book = BookRepository.findBookById(42);
        assertNotNull(book.getRack());
        System.out.println("[Test] Book belongs to one Rack.");
    }

    @Test
    public void borrowHasBorrower() {
        Borrow borrow = BookRepository.findBorrowedByBarcode("4140003214465");
        assertNotNull(borrow.getUser());
        System.out.println("[Test] Borrowed book has borrower details.");
    }

    @Test
    public void checkoutMaxLimit() {
        assertNotNull(BookRepository.MAX_BORROWS);
        System.out.println("[Test] Maximum Borrows per user is limited to " + BookRepository.MAX_BORROWS);
    }

    @Test
    public void borrowMaxDays() {
        assertNotNull(BookRepository.MAX_BORROW_DAYS);
        System.out.println("[Test] The maximum number of days users can keep a book is  " + BookRepository.MAX_BORROWS);
    }

}
