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
        Connection conn = DBUtils.getInstance().getConnection();
        List<Subject> subjects = new ArrayList<Subject>();
        try {
            ResultSet rs = conn.prepareStatement(SqlStatements.ALL_SUBJECTS).executeQuery();
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
        Connection conn = DBUtils.getInstance().getConnection();
        List<Rack> racks = new ArrayList<Rack>();
        try {
            ResultSet rs = conn.prepareStatement(SqlStatements.ALL_RACKS).executeQuery();
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
}
