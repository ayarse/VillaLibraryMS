/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villalibraryms.Repositories;

import com.github.javafaker.Faker;
import villalibraryms.Util.DBUtils;
import villalibraryms.Util.SqlStatements;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import villalibraryms.Models.MembershipCard;
import villalibraryms.Models.Role;
import villalibraryms.Models.User;

/**
 *
 * @author ayasnasih
 */
public class UserRepository {

    public static ResultSet getAllUsers() {
        DBUtils.setStmt(SqlStatements.ALL_USERS);
        ResultSet rs = DBUtils.executeQuery();
        return rs;
    }
    
    public static ResultSet getAllUsers(String search) {
        DBUtils.setStmt(SqlStatements.ALL_USERS_LIKE);
        DBUtils.setObject(1, "%"+search+"%", Types.VARCHAR);
        ResultSet rs = DBUtils.executeQuery();
        return rs;
    }

    public static List<Role> getAllRoles() {
        List<Role> allRoles = new ArrayList<>();
        DBUtils.setStmt(SqlStatements.ALL_ROLES);
        ResultSet rs = DBUtils.executeQuery();
        try {
            while (rs.next()) {
                allRoles.add(new Role(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allRoles;
    }

    public static void addUser(String username, String password, String displayName, Role role, Boolean isActive) {
        DBUtils.setStmt(SqlStatements.INSERT_USER);
        DBUtils.setObject(1, username, Types.VARCHAR);
        DBUtils.setObject(2, password, Types.VARCHAR);
        DBUtils.setObject(3, role.id, Types.BIGINT);
        DBUtils.setObject(4, displayName, Types.VARCHAR);
        DBUtils.setObject(5, isActive, Types.BOOLEAN);
        DBUtils.executeUpdate();
    }

    public static User find(int id) {
        DBUtils.setStmt(SqlStatements.SELECT_USER);
        DBUtils.setObject(1, id, Types.BIGINT);
        ResultSet rs = DBUtils.executeQuery();
        try {
            if (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("role_id"),
                        rs.getString("display_name"),
                        rs.getBoolean("is_active")
                );
                user.setMembershipCard(findCard(id));
                return user;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static User findUserByBarcode(String barcode) {
        DBUtils.setStmt("SELECT `user_id` FROM `membership_cards` WHERE `barcode` = ?");
        DBUtils.setObject(1, barcode, Types.VARCHAR);
        ResultSet rs = DBUtils.executeQuery();
        try {
            if(rs.next()) {
                return find(rs.getInt("user_id"));
            }
        } catch (SQLException ex) {
            return null;
        }
        return null;
    }

    public static void updateUser(User user) {
        DBUtils.setStmt(SqlStatements.UPDATE_USER);
        DBUtils.setObject(1, user.getUsername(), Types.VARCHAR);
        DBUtils.setObject(2, user.getPassword(), Types.VARCHAR);
        DBUtils.setObject(3, user.getRoleId(), Types.BIGINT);
        DBUtils.setObject(4, user.getDisplayName(), Types.VARCHAR);
        DBUtils.setObject(5, user.isIsActive(), Types.BOOLEAN);
        DBUtils.setObject(6, user.getId(), Types.BIGINT);
        DBUtils.executeUpdate();
    }

    public static void updateActiveStatus(int userId, int activeStatus) {
        DBUtils.setStmt(SqlStatements.UPDATE_USER_STATUS);
        DBUtils.setObject(1, activeStatus, Types.INTEGER);
        DBUtils.setObject(2, userId, Types.BIGINT);
        DBUtils.executeUpdate();
    }

    public static void deleteUser(int userId) {
        DBUtils.setStmt(SqlStatements.DELETE_USER);
        DBUtils.setObject(1, userId, Types.BIGINT);
        DBUtils.executeUpdate();
    }

    public static MembershipCard findCard(int userId) {
        DBUtils.setStmt(SqlStatements.GET_MEMBERSHIP_CARD);
        DBUtils.setObject(1, userId, Types.BIGINT);
        ResultSet rs = DBUtils.executeQuery();
        try {
            if (rs.next()) {
                return new MembershipCard(
                        rs.getInt("id"),
                        rs.getString("barcode"),
                        rs.getDate("issued_at"),
                        rs.getDate("expires_at"),
                        rs.getInt("user_id")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void newCard(int userId) {
        String barcode = new Faker().code().ean13();
        LocalDate issuedAt = LocalDate.now();
        LocalDate expiryDate = issuedAt.plusDays(180);

        DBUtils.setStmt(SqlStatements.INSERT_MEMBERSHIP_CARD);
        DBUtils.setObject(1, barcode, Types.VARCHAR);
        DBUtils.setObject(2, issuedAt, Types.DATE);
        DBUtils.setObject(3, expiryDate, Types.DATE);
        DBUtils.setObject(4, userId, Types.BIGINT);
        DBUtils.executeUpdate();
    }

    public static void cancelCard(int userId) {
        DBUtils.setStmt(SqlStatements.DELETE_MEMBERSHIP_CARD);
        DBUtils.setObject(1, userId, Types.BIGINT);
        DBUtils.executeUpdate();
    }

}
