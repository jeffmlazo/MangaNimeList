package com.projexdev.manganimelist.models;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ListIterator;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.projexdev.manganimelist.configs.DbHandler;

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
    private StringProperty mangaNimeIdStrProp;

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

    /**
     * The property of the manganime id of manga or anime.
     *
     * @return the property of manganime id
     */
    public StringProperty mangaNimeIdProp() {
        if (mangaNimeIdStrProp == null) {
            mangaNimeIdStrProp = new SimpleStringProperty(this, "mangaNimeId");
        }

        return mangaNimeIdStrProp;
    }

    /**
     * Set the manganime id of manga or anime.
     *
     * @param mangaNimeId the id of manga or anime
     */
    public void setMangaNimeId(String mangaNimeId) {
        mangaNimeIdProp().set(mangaNimeId);
    }

    /**
     * Get the manganime id of manga or anime.
     *
     * @return the id's manga or anime
     */
    public String getMangaNimeId() {
        return mangaNimeIdProp().get();
    }

    /*
     * *****************************************
     * QUERIES
     * *****************************************
     */
    Connection conn;
    ResultSet rs = null;
    PreparedStatement preparedStmt = null;

    public GenreModel() {
        conn = DbHandler.connector();
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
    public ObservableList<Object> getAllGenre() throws SQLException {

        ObservableList<Object> data = FXCollections.observableArrayList();
        String query = "SELECT * FROM genre";
        // preparedStmt is already autocloseable so no need to add in the finally block
        try {
            preparedStmt = conn.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
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

    /**
     * Insert batch genres data.
     *
     * @param arrGenreId the batch id's of genres
     * @param mangaNimeId the id of manganime
     * @return error or success insert genres
     * @throws SQLException
     */
    public boolean insertGenres(ArrayList<Integer> arrGenreId, String mangaNimeId) throws SQLException {
        boolean isSuccess = false;
        try {
            // Set the manganime id
            setMangaNimeId(mangaNimeId);

            ArrayList<String> cols = new ArrayList<>();
            cols.add("genre_id");
            cols.add("manganime_id");

            ListIterator<String> iteratorCols = cols.listIterator();
            StringBuilder buildStrCols = new StringBuilder();
            StringBuilder buildStrVals = new StringBuilder();
            while (iteratorCols.hasNext()) {

                String colsName = iteratorCols.next();
                buildStrCols.append(colsName);
                buildStrVals.append('?');

                if (iteratorCols.nextIndex() == cols.size()) {
                    break;
                }

                buildStrCols.append(',');
                buildStrVals.append(',');

            }

            ListIterator<Integer> iteratorGenreId = arrGenreId.listIterator();
            // Loop through all genreId
            while (iteratorGenreId.hasNext()) {
                int genreId = iteratorGenreId.next();
                // Set the genre id
                setGenreId(genreId);

                String query = "INSERT INTO genres (" + buildStrCols.toString() + ") VALUES (" + buildStrVals + ")";
                preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ArrayList<Object> objVals = new ArrayList<>();
                objVals.add(getGenreId());
                objVals.add(getMangaNimeId());

                ListIterator<Object> iteratorVals = objVals.listIterator();
                int paramIndex = 1;
                while (iteratorVals.hasNext()) {

                    Object obj = iteratorVals.next();
                    if (obj.getClass().getSimpleName().toLowerCase().contains("string")) {
                        preparedStmt.setString(paramIndex, (String) obj);
                    } else if (obj.getClass().getSimpleName().toLowerCase().contains("integer")) {
                        preparedStmt.setInt(paramIndex, (int) obj);
                    }

                    paramIndex++;
                }

                int affected = preparedStmt.executeUpdate();
                if (affected == 1) {
                    rs = preparedStmt.getGeneratedKeys();
                    rs.next();
                    int genGenresId = rs.getInt(1);

                    LogModel log = new LogModel();
                    log.tableNameProp().setValue("genres");
                    log.genresIdProp().setValue(genGenresId);
                    if (log.insertLog()) {
                        isSuccess = true;
                    }
                } else {
                    System.err.println("No rows affected");
                }
            }

        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preparedStmt != null) {
                preparedStmt.close();
            }
        }

        return isSuccess;
    }
}
