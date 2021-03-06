package villalibraryms;

import villalibraryms.Forms.LoginForm;
import villalibraryms.Util.DBUtils;
import java.sql.Connection;
import java.sql.SQLSyntaxErrorException;
import villalibraryms.Forms.SetupSystem;
import villalibraryms.Models.User;

/**
 *
 * @author ayasnasih
 */
public class VillaLibraryMS {

    public static class SessionData {

        public static String loggedInUsername;
        public static String Uid;
        public static User currentUser;
    }

    protected static DBUtils db;
    private static Connection connection;

    public static void main(String[] args) {

        try {
            db = DBUtils.getInstance();
            new LoginForm().setVisible(true);
        } catch (Exception ex) {
            new SetupSystem().setVisible(true);
        }

    }

    public static void logout() {
        SessionData.loggedInUsername = null;
        SessionData.Uid = null;
        SessionData.currentUser = null;

        new LoginForm().setVisible(true);
    }
}
