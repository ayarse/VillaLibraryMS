/*
 * Library Management System - Ayas Nasih, S1600655
 * 
 * 
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
