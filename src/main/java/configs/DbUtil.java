package main.java.configs;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author jeprox
 */
public class DbUtil {

    private static final String CONN_URL = "jdbc:sqlite:manganimelist_db.mnldb";

    public static Connection connector() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(CONN_URL);
            return conn;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
