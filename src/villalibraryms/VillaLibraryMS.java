package villalibraryms;

import villalibraryms.Forms.LoginForm;
import villalibraryms.Util.DBUtils;
import java.sql.Connection;
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

        db = DBUtils.getInstance();
        new LoginForm().setVisible(true);
    }
}
