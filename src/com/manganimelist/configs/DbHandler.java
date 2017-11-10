package com.manganimelist.configs;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeprox
 */
public class DbHandler {
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
            Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setUpGenres() {
        String sql = "CREATE TABLE IF NOT EXISTS genres (\n"
                + "genres_id               INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "genre_id                 INTEGER,\n"
                + "manganime_id      CHAR(22)\n"
                + ");";

        try {
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.executeUpdate();
            preparedStmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setUpImages() {
        String sql = "CREATE TABLE IF NOT EXISTS image (\n"
                + "image_id                           CHAR(22) PRIMARY KEY,\n"
                + "image_category               VARCHAR   DEFAULT thumbnail,\n"
                + "image_file_name              VARCHAR,\n"
                + "image_file_type                CHAR,\n"
                + "image_file_extension       CHAR,\n"
                + "manganime_id                 CHAR(22)\n"
                + ");";

        try {
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.executeUpdate();
            preparedStmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertGenreValues() throws SQLException {

        if (isGenreValuesEmpty()) {
            try {
                String colName = "genre_name";
                ArrayList<String> colVals = new ArrayList<>();
                colVals.add("action");
                colVals.add("adult");
                colVals.add("adventure");
                colVals.add("comedy");
                colVals.add("doujinshi");
                colVals.add("drama");
                colVals.add("ecchi");
                colVals.add("fantasy");
                colVals.add("gender bender");
                colVals.add("harem");
                colVals.add("hentai");
                colVals.add("historical");
                colVals.add("horror");
                colVals.add("josei");
                colVals.add("lolicon");
                colVals.add("martial arts");
                colVals.add("mature");
                colVals.add("mecha");
                colVals.add("musical");
                colVals.add("mystery");
                colVals.add("psychological");
                colVals.add("romance");
                colVals.add("school life");
                colVals.add("sci-fi");
                colVals.add("seinen");
                colVals.add("shotacon");
                colVals.add("shoujo");
                colVals.add("shoujo ai");
                colVals.add("shounen");
                colVals.add("shounen ai");
                colVals.add("slice of life");
                colVals.add("smut");
                colVals.add("sports");
                colVals.add("supernatural");
                colVals.add("taboo");
                colVals.add("tragedy");
                colVals.add("yaoi");
                colVals.add("yuri");

                ListIterator<String> iteratorColVals = colVals.listIterator();
                StringBuilder buildStrVals = new StringBuilder();

                while (iteratorColVals.hasNext()) {

                    // Proceed to next column value
                    iteratorColVals.next();

                    // Build a string for the (?) value
                    buildStrVals.append("(?)");

                    // Check colVals if last if true break & don't append ","
                    if (iteratorColVals.nextIndex() == colVals.size()) {
                        break;
                    }

                    buildStrVals.append(',');

                }

                String sql = "INSERT INTO genre "
                        + "(" + colName + ") VALUES " + buildStrVals;

                preparedStmt = conn.prepareStatement(sql);

                int paramIndex = 1;
                // Re assign again the iterator to new iterator to reset the colVals
                ListIterator<String> iteratorColVals2 = colVals.listIterator();
                while (iteratorColVals2.hasNext()) {
                    String colVal = iteratorColVals2.next();
                    preparedStmt.setString(paramIndex, colVal);

                    paramIndex++;
                }

                preparedStmt.executeUpdate();
                preparedStmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private boolean isGenreValuesEmpty() throws SQLException {
        ResultSet rs = null;
        boolean isEmpty = false;
        String sql = "SELECT genre_name FROM genre ";
        try {
            preparedStmt = conn.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            rs = preparedStmt.executeQuery();

            // Check if result set is empty
            if (!rs.next()) {
                isEmpty = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preparedStmt != null) {
                preparedStmt.close();
            }
        }

        return isEmpty;
    }
}
