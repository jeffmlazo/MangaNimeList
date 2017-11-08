package com.manganimelist.configs;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeprox
 */
public class DbHandle {
// This connection url will be on read only 
//    private static final String CONN_URL = "jdbc:sqlite::resource:main/resources/db/manganimelist_db.mnldb";

    private static Connection conn = null;
    private PreparedStatement preparedStmt;

    public static Connection connector() {
        // During runtime this code will work but for distribution the database will still be created at the home directory
//        File dbFile = new File("db/manganimelist_db.mnldb");
//if(dbFile.createdNewFile())
//{
//    // File is created
//}
//else
//{
////    File is already existed
//}
//        String CONN_URL = "jdbc:sqlite:" + dbFile.getAbsolutePath();
//        Connection conn = null;
        try {

            String dbName = "manganimelist_db.mnldb";
            String folderName = ".manganimelist";
            String homeDirectory = System.getProperty("user.home");
            String folderPath = homeDirectory + File.separator + folderName;

            // Create a folder directory
            boolean isMkdir = new File(folderPath).mkdir();
            // Check if the folder was created if true prompt folder was created
            if (isMkdir) {
                System.out.println("Created Folder: " + folderPath);
            }

            Class.forName("org.sqlite.JDBC");
            String CONN_URL = "jdbc:sqlite:" + folderPath + File.separator + dbName;
            conn = DriverManager.getConnection(CONN_URL);
            return conn;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    // TODO: Need to fix here the creation of table
    public void setUpManganime() {
        String sql = "CREATE TABLE IF NOT EXISTS manganime (\n"
                + "manganime_id     CHAR(22) PRIMARY KEY NOT NULL,\n"
                + "list_type                 VARCHAR DEFAULT anime,\n"
                + "title                         VARCHAR,\n"
                + "epi_chap_start      INTEGER,\n"
                + "epi_chap_end        INTEGER,\n"
                + "total_epi_chap      INTEGER,\n"
                + "all_watched_read BIT DEFAULT (0),\n"
                + "start_date             TEXT,\n"
                + "end_date               TEXT,\n"
                + "state                      VARCHAR DEFAULT ongoing,\n"
                + "summary              TEXT,\n"
                + "volumes                INTEGER,\n"
                + "author                   VARCHAR,\n"
                + "publisher              VARCHAR\n"
                + ");";

        try {
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbHandle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setUpGenre() {
        String sql = "CREATE TABLE IF NOT EXISTS genre (\n"
                + "genre_id                 INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "genre_name          VARCHAR NOT NULL\n"
                + ");";

        try {
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbHandle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setUpGenres() {

    }

    public void setUpImages() {

    }

    public void setUpLog() {
        String sql = "CREATE TABLE IF NOT EXISTS log (\n"
                + "log_id                     CHAR(22) PRIMARY KEY NOT NULL,\n"
                + "table_name           VARCHAR NOT NULL,\n"
                + "[action]                  VARCHAR DEFAULT created,\n"
                + "created_on            INTEGER NOT NULL,\n"
                + "info                         VARCHAR,\n"
                + "manganime_id      CHAR(22),\n"
                + "image_id                CHAR(22),\n"
                + "genre_id                 INTEGER(10) DEFAULT(0),\n"
                + "genres_id               INTEGER(10) DEFAULT(0)\n"
                + ");";

        try {
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbHandle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // TODO: Add other genre names and insert is only once for creating the values
    public void insertGenreValues() {
        String sql = "INSERT INTO genre "
                + "(genre_name) VALUES"
                + "(?),"
                + "(?),"
                + "(?),"
                + "(?)";

        try {
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, "action");
            preparedStmt.setString(2, "adult");
            preparedStmt.setString(3, "adventure");
            preparedStmt.setString(4, "comedy");
            preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbHandle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
