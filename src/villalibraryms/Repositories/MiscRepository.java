/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villalibraryms.Repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
