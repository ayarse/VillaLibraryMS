/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villalibraryms.Repositories;

import villalibraryms.Util.DBUtils;
import villalibraryms.Util.SqlStatements;
import java.sql.*;

/**
 *
 * @author ayasnasih
 */
public class AuthorRepository {

    public static int getAuthorIdByNameOrCreate(String authorName) {
        DBUtils.setStmt(SqlStatements.FIND_AUTHOR);
        DBUtils.setObject(1, authorName, Types.VARCHAR);
        ResultSet rs = DBUtils.executeQuery();

        try {
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                DBUtils.setStmt(SqlStatements.INSERT_AUTHOR);
                DBUtils.setObject(1, authorName, Types.VARCHAR);
                DBUtils.executeUpdate();
                return getAuthorIdByNameOrCreate(authorName);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

}
