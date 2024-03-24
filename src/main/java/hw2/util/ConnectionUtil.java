package hw2.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hw2.util.PropertiesUtil.getValue;

public final class ConnectionUtil {
    private static final String USERNAME = "db.username";
    private static final String PASSWORD = "db.password";
    private static final String URL = "db.url";
    private static final String DRIVER = "db.driver";

    private ConnectionUtil() {
    }

    static {
        loadDriver();
    }

    public static Connection open() {
        try {
            return DriverManager.getConnection(getValue(URL),
                    getValue(USERNAME),
                    getValue(PASSWORD));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadDriver() {
        try {
            Class.forName(getValue(DRIVER));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
