/*
 * Library Management System - Ayas Nasih, S1600655
 * 
 * 
 */
package villalibraryms.Repositories;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import villalibraryms.Models.Borrow;
import villalibraryms.Util.DBUtils;
import villalibraryms.Models.Rack;
import villalibraryms.Models.Subject;
import villalibraryms.Util.SqlStatements;

/**
 *
 * @author ayasnasih
 */
public class MiscRepository {

    public static List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<Subject>();
        try {
            DBUtils.setStmt(SqlStatements.ALL_SUBJECTS);
            ResultSet rs = DBUtils.executeQuery();
            while (rs.next()) {
                subjects.add(new Subject(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return subjects;
    }

    public static List<Rack> getAllRacks() {
        List<Rack> racks = new ArrayList<Rack>();
        try {
            DBUtils.setStmt(SqlStatements.ALL_RACKS);
            ResultSet rs = DBUtils.executeQuery();
            while (rs.next()) {
                racks.add(new Rack(
                        rs.getInt("id"),
                        rs.getString("location")
                ));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return racks;
    }

    public static ResultSet getAllFines(String searchStr) {
        String sql = "SELECT fines.id as ID, books.title as Title, book_items.barcode as Barcode, fines.fine_date as \"Fined Date\", "
                + "borrows.borrowed_date as \"Borrowed Date\", borrows.returned_date as \"Returned Date\", fine_amount as \"Fine Amount\", "
                + "fines.paid as Paid, fines.paid_date as \"Paid Date\", users.display_name as \"Member Name\" FROM fines "
                + "LEFT JOIN borrows ON borrows.id = fines.borrow_id "
                + "LEFT JOIN book_items ON borrows.book_item_id = book_items.id "
                + "LEFT JOIN books ON book_items.book_id = books.id "
                + "LEFT JOIN users ON borrows.user_id = users.id";
        if (!searchStr.equals("")) {
            sql = sql + " WHERE users.display_name LIKE ?";
        }
        DBUtils.setStmt(sql);
        if (!searchStr.equals("")) {
            DBUtils.setObject(1, "%" + searchStr + "%", Types.VARCHAR);
        }
        return DBUtils.executeQuery();
    }

    public static void settleFine(int fineId) {
        String sql = "UPDATE `fines` SET `paid` = true, paid_date = ? WHERE id = ?";
        DBUtils.setStmt(sql);
        DBUtils.setObject(1, LocalDate.now(), Types.DATE);
        DBUtils.setObject(2, fineId, Types.BIGINT);
        DBUtils.executeUpdate();
    }

    public static ResultSet myFines(int userId) {
        String sql = "SELECT fines.id as ID, books.title as Title, book_items.barcode as Barcode, fines.fine_date as \"Fined Date\", "
                + "borrows.borrowed_date as \"Borrowed Date\", borrows.returned_date as \"Returned Date\", fine_amount as \"Fine Amount\", "
                + "fines.paid as Paid, fines.paid_date as \"Paid Date\", users.display_name as \"Member Name\" FROM fines "
                + "LEFT JOIN borrows ON borrows.id = fines.borrow_id "
                + "LEFT JOIN book_items ON borrows.book_item_id = book_items.id "
                + "LEFT JOIN books ON book_items.book_id = books.id "
                + "LEFT JOIN users ON borrows.user_id = users.id "
                + "WHERE users.id = ?";
        DBUtils.setStmt(sql);
        DBUtils.setObject(1, userId, Types.BIGINT);
        return DBUtils.executeQuery();
    }

    public static ResultSet myReservations(int userId) {
        String sql = "SELECT reservations.id as ID, books.title as Title, book_items.barcode as Barcode, reservation_date as \"Reservation Date\", "
                + "users.display_name as \"Member Name\" FROM reservations "
                + "LEFT JOIN book_items ON reservations.book_item_id = book_items.id "
                + "LEFT JOIN books ON book_items.book_id = books.id "
                + "LEFT JOIN users ON reservations.user_id = users.id "
                + "WHERE users.id = ?";
        DBUtils.setStmt(sql);
        DBUtils.setObject(1, userId, Types.BIGINT);
        return DBUtils.executeQuery();
    }

    public static void cancelReservation(int reservationId) {
        DBUtils.setStmt("DELETE FROM reservations WHERE id = ?");
        DBUtils.setObject(1, reservationId, Types.BIGINT);
        DBUtils.executeUpdate();
    }

    public static List<String> getNotifications(int userId) {
        List<String> notifications = new ArrayList<String>();
        DBUtils.setStmt("SELECT * FROM `notifications` WHERE user_id = ? ORDER BY created_at DESC");
        DBUtils.setObject(1, userId, Types.BIGINT);
        ResultSet rs = DBUtils.executeQuery();
        try {
            while (rs.next()) {
                notifications.add(rs.getString("content"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MiscRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notifications;
    }

    public static List<Integer> getUsersWhoReservedBookItem(int bookItemId) {
        List<Integer> users = new ArrayList<>();
        DBUtils.setStmt("SELECT user_id FROM reservations WHERE book_item_id = ?");
        DBUtils.setObject(1, bookItemId, Types.BIGINT);
        ResultSet rs = DBUtils.executeQuery();
        try {
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                users.add(userId);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MiscRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    public static void generateReturnNotifications(Borrow borrow) {
        int bookItemId = borrow.getBook().getBookItemId();
        String bookTitle = borrow.getBook().getTitle();
        List<Integer> users = getUsersWhoReservedBookItem(bookItemId);

        System.out.println(bookItemId);
        System.out.println(bookTitle);

        for (Integer userId : users) {
            System.out.println(userId);
            System.out.println(bookItemId);
            System.out.println(bookTitle);
            DBUtils.setStmt("INSERT INTO notifications (user_id, book_item_id, content, created_at, notification_type_id) VALUES (?,?,?,?,?)");
            DBUtils.setObject(1, userId, Types.BIGINT);
            DBUtils.setObject(2, bookItemId, Types.BIGINT);
            DBUtils.setObject(3, "The book you reserved, " + bookTitle + ", is now available!", Types.VARCHAR);
            DBUtils.setObject(4, LocalDate.now(), Types.DATE);
            DBUtils.setObject(5, 1, Types.BIGINT); // Notification Type: RESERVED_BOOK_AVAILABLE
            DBUtils.executeUpdate();

        }
    }

}
