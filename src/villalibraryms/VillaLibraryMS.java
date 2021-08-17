package villalibraryms;

import villalibraryms.Util.SqlStatements;
import villalibraryms.Forms.LoginForm;
import villalibraryms.Util.DBUtils;
import com.github.javafaker.Faker;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import villalibraryms.Models.User;

/**
 *
 * @author ayasnasih
 */
public class VillaLibraryMS {

    public static class ex {

        public static int days = 0;
    }

    public static class SessionData {

        public static String loggedInUsername;
        public static String Uid;
        public static User currentUser;
    }

    protected static DBUtils db;
    private static Connection connection;

    public static void main(String[] args) {
        
//        Faker faker = new Faker();
//        System.out.println(faker.book().author());

        db = DBUtils.getInstance();
        new LoginForm().setVisible(true);
    }
}
