/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villalibraryms.Repositories;

import villalibraryms.Util.DBUtils;
import villalibraryms.Util.SqlStatements;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import villalibraryms.Models.Role;

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
    
    public static void updateRole(int userId, int roleId) {
        DBUtils.setStmt(SqlStatements.UPDATE_USER_ROLE);
        DBUtils.setObject(1, roleId, Types.INTEGER);
        DBUtils.setObject(2, userId, Types.BIGINT);
        DBUtils.executeUpdate();
    }
    
    public static List<Role> getAllRoles() {
        List<Role> allRoles = new ArrayList<>();
        DBUtils.setStmt(SqlStatements.ALL_ROLES);
        ResultSet rs = DBUtils.executeQuery();
        try {
            while(rs.next()) {
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
    
    public static void deleteUser(int userId) {
        DBUtils.setStmt(SqlStatements.DELETE_USER);
        DBUtils.setObject(1, userId, Types.BIGINT);
        DBUtils.executeUpdate();
    }
    
    public static void updateActiveStatus(int userId, int activeStatus) {
        DBUtils.setStmt("UPDATE users SET is_active = ? WHERE id = ?");
        DBUtils.setObject(1, activeStatus, Types.INTEGER);
        DBUtils.setObject(2, userId, Types.BIGINT);
        DBUtils.executeUpdate();
    }
    
}
