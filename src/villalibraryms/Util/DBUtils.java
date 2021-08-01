/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villalibraryms.Util;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author ayasnasih
 */
public class DBUtils {

    private static DBUtils instance = null;
    private static Connection con;
    private static PreparedStatement stmt = null;
    private static ResultSet rs = null;

    private DBUtils() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Loaded driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/mysql?user=root&password=");
            System.out.println("Connected to MySQL");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    // Singleton
    public static DBUtils getInstance() {
        if (instance == null) {
            instance = new DBUtils();
        }

        return instance;
    }

    public static Connection getConnection() {
        if (con == null) {
            instance = new DBUtils();
        }

        return con;
    }

    public static ResultSet executeQuery() {
        try {
            rs = stmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    public static void setStmt(String sql) {
        try {
            stmt = con.prepareStatement(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static void setObject(int parameterIndex, Object x, int targetSqlType) {
        try {
            stmt.setObject(parameterIndex, x, targetSqlType);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static int executeUpdate() {
        try {
            int ret = stmt.executeUpdate();
            stmt.close();
            return ret;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }

    public static void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void seed() {
        DBUtils db = DBUtils.getInstance();
        Connection connection = db.con;

        try {
            ResultSet resultSet = connection.getMetaData().getCatalogs();
            //iterate each catalog in the ResultSet
            while (resultSet.next()) {
                // Get the database name, which is at position 1
                String databaseName = resultSet.getString(1);
                if (databaseName.equals("library")) {
                    //System.out.print("yes");
                    Statement stmt = connection.createStatement();
                    //Drop database if it pre-exists to reset the complete database
                    String sql = SqlStatements.DROP_DB;
                    stmt.executeUpdate(sql);
                }
            }
            Statement stmt = connection.createStatement();

            String sql = "CREATE DATABASE LIBRARY"; //Create Database
            stmt.executeUpdate(sql);
            stmt.executeUpdate(SqlStatements.USE_DB); //Use Database
            //Create Users Table
            String sql1 = "CREATE TABLE USERS(UID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, USERNAME VARCHAR(30), PASSWORD VARCHAR(30), ADMIN BOOLEAN)";
            stmt.executeUpdate(sql1);
            //Insert into users table
            stmt.executeUpdate("INSERT INTO USERS(USERNAME, PASSWORD, ADMIN) VALUES('admin','admin',TRUE)");
            //Create Books table
            stmt.executeUpdate("CREATE TABLE BOOKS(BID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, BNAME VARCHAR(50), GENRE VARCHAR(20), PRICE INT)");
            //Create Issued Table
            stmt.executeUpdate("CREATE TABLE ISSUED(IID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, UID INT, BID INT, ISSUED_DATE VARCHAR(20), RETURN_DATE VARCHAR(20), PERIOD INT, FINE INT)");
            //Insert into books table
            stmt.executeUpdate("INSERT INTO BOOKS(BNAME, GENRE, PRICE) VALUES ('War and Peace', 'Mystery', 200),  ('The Guest Book', 'Fiction', 300), ('The Perfect Murder','Mystery', 150), ('Accidental Presidents', 'Biography', 250), ('The Wicked King','Fiction', 350)");

            resultSet.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
