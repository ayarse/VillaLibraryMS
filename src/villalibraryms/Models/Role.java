/*
 * Library Management System - Ayas Nasih, S1600655
 * 
 * 
 */
package villalibraryms.Models;

import java.sql.*;
import villalibraryms.Util.DBUtils;

/**
 *
 * @author ayasnasih
 */
public class Role {

    public int id;
    public String name;

    public Role() {

    }

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Role find(int id) {
        Connection conn = DBUtils.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT name FROM `roles` WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Role role = new Role(
                        id,
                        rs.getString("name")
                );
                return role;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new Role();
    }

    @Override
    public String toString() {
        return (name.substring(0,1).toUpperCase() + name.substring(1));
    }

}
