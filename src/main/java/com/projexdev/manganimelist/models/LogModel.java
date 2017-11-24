package com.projexdev.manganimelist.models;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ListIterator;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import com.projexdev.manganimelist.configs.DbHandler;
import com.projexdev.manganimelist.libraries.SqlUtil;

/**
 *
 * @author jeprox
 */
public class LogModel {

    /*
     * *****************************************
     *  PROPERTIES
     * *****************************************
     */
    private StringProperty logIdStrProp;
    private StringProperty tableNameStrProp;
    private StringProperty actionStrProp;
    private LongProperty createdOnLongProp;
    private StringProperty infoStrProp;
    private StringProperty mangaNimeIdStrProp;
    private StringProperty imageIdStrProp;
    private IntegerProperty genreIdIntProp;
    private IntegerProperty genresIdIntProp;

    /**
     * The property of the log id.
     *
     * @return the property of the log id
     */
    public StringProperty logIdProp() {
        if (logIdStrProp == null) {
            logIdStrProp = new SimpleStringProperty(this, "logId");
        }

        return logIdStrProp;
    }

    /**
     * Set the log id.
     *
     * @param logId the log id
     */
    public void setLogId(String logId) {
        logIdProp().set(logId);
    }

    /**
     * Get the log id.
     *
     * @return the log id
     */
    public String getLogId() {
        return logIdProp().get();
    }

    /**
     * The property of the table name.
     *
     * @return the property of the table name
     */
    public StringProperty tableNameProp() {
        if (tableNameStrProp == null) {
            tableNameStrProp = new SimpleStringProperty(this, "tableName");
        }

        return tableNameStrProp;
    }

    /**
     * Set the table name.
     *
     * @param tableName the table name
     */
    public void setTableName(String tableName) {
        tableNameProp().set(tableName);
    }

    /**
     * Get the table name.
     *
     * @return the table name
     */
    public String getTableName() {
        return tableNameProp().get();
    }

    /**
     * The property of action.
     *
     * @return the property of the action
     */
    public StringProperty actionProp() {
        if (actionStrProp == null) {
            actionStrProp = new SimpleStringProperty(this, "action");
        }

        return actionStrProp;
    }

    /**
     * Set the action.
     *
     * @param action the action
     */
    public void setAction(String action) {
        actionProp().set(action);
    }

    /**
     * Get the action.
     *
     * @return the action
     */
    public String getAction() {
        return actionProp().get();
    }

    /**
     * The property of created on.
     *
     * @return the property of created on
     */
    public LongProperty createdOnProp() {
        if (createdOnLongProp == null) {
            createdOnLongProp = new SimpleLongProperty(this, "createdOn");
        }

        return createdOnLongProp;
    }

    /**
     * Set the created on.
     *
     * @param createdOn the date when it was created
     */
    public void setCreatedOn(long createdOn) {
        createdOnProp().set(createdOn);
    }

    /**
     * Get the created on.
     *
     * @return the created on the date when it was created
     */
    public long getCreatedOn() {
        return createdOnProp().get();
    }

    /**
     * The property of info.
     *
     * @return the property of info
     */
    public StringProperty infoProp() {
        if (infoStrProp == null) {
            infoStrProp = new SimpleStringProperty(this, "info");
        }

        return infoStrProp;
    }

    /**
     * Set the info an additional custom data to log.
     *
     * @param info the additional custom data to log
     */
    public void setInfo(String info) {
        infoProp().set(info);
    }

    /**
     * Get the info an additional custom data to log.
     *
     * @return the info an additional custom data to log
     */
    public String getInfo() {
        return infoProp().get();
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

    /**
     * The property of image id.
     *
     * @return the property of image id
     */
    public StringProperty imageIdProp() {
        if (imageIdStrProp == null) {
            imageIdStrProp = new SimpleStringProperty(this, "imageId");
        }

        return imageIdStrProp;
    }

    /**
     * Set the image id.
     *
     * @param imageId the image id
     */
    public void setImageId(String imageId) {
        imageIdProp().set(imageId);
    }

    /**
     * Get the image id.
     *
     * @return the image id
     */
    public String getImageId() {
        return imageIdProp().get();
    }

    /**
     * The property of genre id.
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
     * Set the genre id.
     *
     * @param genreId the genre id
     */
    public void setGenreId(int genreId) {
        genreIdProp().set(genreId);
    }

    /**
     * Get the genre id.
     *
     * @return the genre id
     */
    public Integer getGenreId() {
        return genreIdProp().get();
    }

    /**
     * The property of genres id.
     *
     * @return the property of genres id
     */
    public IntegerProperty genresIdProp() {
        if (genresIdIntProp == null) {
            genresIdIntProp = new SimpleIntegerProperty(this, "genresId");
        }

        return genresIdIntProp;
    }

    /**
     * Set the genres id.
     *
     * @param genresId the genres id
     */
    public void setGenresId(int genresId) {
        genresIdProp().set(genresId);
    }

    /**
     * Get the genres id.
     *
     * @return the genres id
     */
    public Integer getGenresId() {
        return genresIdProp().get();
    }

    /*
     * *****************************************
     * QUERIES
     * *****************************************
     */
    Connection conn;
    ResultSet rs = null;
    PreparedStatement preparedStmt = null;

    /**
     * Create LogModel with connection check in the database. If database
     * connection fails the system will close.
     */
    public LogModel() {
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
     * Insert log data.
     *
     * @return error or success insert log
     * @throws SQLException
     */
    public boolean insertLog() throws SQLException {
        boolean isSuccess = false;
        try {
            Calendar cal = Calendar.getInstance();
            long currentDateTime = cal.getTimeInMillis();
            setCreatedOn(currentDateTime);

            ArrayList<String> cols = new ArrayList<>();
            cols.add("log_id");
            cols.add("table_name");
            if (getAction() != null) {
                cols.add("action");
            }
            cols.add("created_on");
            if (getInfo() != null) {
                cols.add("info");
            }
            if (getMangaNimeId() != null) {
                cols.add("manganime_id");
            }
            if (getImageId() != null) {
                cols.add("image_id");
            }
            if (getGenreId() != null) {
                cols.add("genre_id");
            }
            if (getGenresId() != null) {
                cols.add("genres_id");
            }

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

            SqlUtil utilSql = new SqlUtil();
            String logId = utilSql.getRandGenId("log", "log_id");
            String query = "INSERT INTO log (" + buildStrCols.toString() + ") VALUES (" + buildStrVals + ")";
            preparedStmt = conn.prepareStatement(query);
            ArrayList<Object> objVals = new ArrayList<>();
            objVals.add(logId);
            objVals.add(getTableName());
            if (getAction() != null) {
                objVals.add(getAction());
            }
            objVals.add(getCreatedOn());
            if (getInfo() != null) {
                objVals.add(getInfo());
            }
            if (getMangaNimeId() != null) {
                objVals.add(getMangaNimeId());
            }
            if (getImageId() != null) {
                objVals.add(getImageId());
            }
            if (getGenreId() != null) {
                objVals.add(getGenreId());
            }
            if (getGenresId() != null) {
                objVals.add(getGenresId());
            }

            ListIterator<Object> iteratorVals = objVals.listIterator();
            int paramIndex = 1;
            while (iteratorVals.hasNext()) {

                Object obj = iteratorVals.next();
                if (obj.getClass().getSimpleName().toLowerCase().contains("string")) {
                    preparedStmt.setString(paramIndex, (String) obj);
                } else if (obj.getClass().getSimpleName().toLowerCase().contains("long")) {
                    preparedStmt.setLong(paramIndex, (long) obj);
                } else if (obj.getClass().getSimpleName().toLowerCase().contains("integer")) {
                    preparedStmt.setInt(paramIndex, (int) obj);
                }

                paramIndex++;
            }

            int affected = preparedStmt.executeUpdate();
            if (affected == 1) {
                //TODO: Need to figure out what to put here probably error stack trace method
                isSuccess = true;
            } else {
                System.err.println("No rows affected");
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
