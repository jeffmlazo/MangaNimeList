package main.java.models;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.configs.DBUtil;

/**
 *
 * @author jeprox
 */
public class GenreModel {

    /*
     * *****************************************
     *  PROPERTIES
     * *****************************************
     */
    private IntegerProperty genreIdIntProp;
    private StringProperty genreNameStrProp;

    /**
     * The property of the genre id.
     *
     * @return the property of genre id
     */
    public IntegerProperty genreIdProp() {
        if (genreIdIntProp == null) {
            genreIdIntProp = new SimpleIntegerProperty(this, "genreId");
        }

        return genreIdIntProp;
    }

    /**
     * Set genre id of manga or anime.
     *
     * @param genreId the genre id of manga or anime
     */
    public void setGenreId(int genreId) {
        genreIdProp().set(genreId);
    }

    /**
     * Get the genre id of manga or anime.
     *
     * @return the id's manga or anime
     */
    public Integer getGenreId() {
        return genreIdProp().get();
    }

    /**
     * The property of episode or chapter start of manga or anime.
     *
     * @return the property of episode or chapter start of manga or anime
     */
    public StringProperty genreNameProp() {
        if (genreNameStrProp == null) {
            genreNameStrProp = new SimpleStringProperty(this, "genreName");
        }

        return genreNameStrProp;
    }

    /**
     * Set the genre of manga or anime.
     *
     * @param genreName the genre name of manga or anime
     */
    public void setGenreName(String genreName) {
        genreNameProp().set(genreName);
    }

    /**
     * Get the genre name of manga or anime.
     *
     * @return the genre name of manga or anime
     */
    public String getGenreName() {
        return genreNameProp().get();
    }

    /*
     * *****************************************
     * QUERIES
     * *****************************************
     */
    Connection conn;
    ResultSet rs = null;

    public GenreModel() {
        conn = DBUtil.Connector();
        if (conn == null) {
            System.out.println("Database connection failed!");
            System.exit(1);
        }
    }

    /**
     * Check if Database connection is OK
     *
     * @return true if connection is OK else false connection is close
     */
    public boolean isDbConnected() {
        try {
            return !conn.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get all genre details for manga or anime.
     *
     * @return all genre details
     * @throws SQLException
     */
    public ObservableList getAllGenre() throws SQLException {

        ObservableList data = FXCollections.observableArrayList();
        String query = "SELECT * FROM genre";
        // preparedStmt is already autocloseable so no need to add in the finally block
        try (PreparedStatement preparedStmt = conn.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);) {
            rs = preparedStmt.executeQuery();
            // Iterate all result set
            while (rs.next()) {
                GenreModel genreData = new GenreModel();
                genreData.setGenreId(rs.getInt("genre_id"));
                genreData.setGenreName(rs.getString("genre_name"));
                data.add(genreData);
            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
        }

        return data;
    }
}
