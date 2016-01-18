package main.java.models;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ListIterator;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.configs.DbUtil;
import main.java.libraries.DateTimeFormatter;
import main.java.libraries.SqlUtil;

/**
 *
 * @author jeprox
 */
public class MangaNimeModel {

    /*
     * *****************************************
     *  PROPERTIES
     * *****************************************
     */
    private StringProperty mangaNimeIdStrProp;
    private StringProperty listTypeStrProp;
    private StringProperty titleStrProp;
    private IntegerProperty epiChapStartIntProp;
    private IntegerProperty epiChapEndIntProp;
    private IntegerProperty totalEpiChapIntProp;
    private StringProperty allWatchedReadStrProp;
    private StringProperty startDateStrProp;
    private StringProperty endDateStrProp;
    private StringProperty stateStrProp;
    private StringProperty summaryStrProp;
    private IntegerProperty volumesIntProp;
    private StringProperty authorStrProp;
    private StringProperty createdOnStrProp;

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
     * The property of the list type of manga or anime.
     *
     * @return the property of list type of manga or anime
     */
    public StringProperty listTypeProp() {
        if (listTypeStrProp == null) {
            listTypeStrProp = new SimpleStringProperty(this, "listType");
        }

        return listTypeStrProp;
    }

    /**
     * Set the list type of manga or anime.
     *
     * @param listType the type of list manga or anime
     */
    public void setListType(String listType) {
        listTypeProp().set(listType);
    }

    /**
     * Get the list type of manga or anime.
     *
     * @return the list type manga or anime
     */
    public String getListType() {
        return listTypeProp().get();
    }

    /**
     * The property of the title of manga or anime.
     *
     * @return the property of the title of manga or anime
     */
    public StringProperty titleProp() {
        if (titleStrProp == null) {
            titleStrProp = new SimpleStringProperty(this, "title");
        }

        return titleStrProp;
    }

    /**
     * Set the title of the manga or anime.
     *
     * @param title the title of manga or anime
     */
    public void setTitle(String title) {
        titleProp().set(title);
    }

    /**
     * Get the title of the manga or anime.
     *
     * @return the title of the manga or anime
     */
    public String getTitle() {
        return titleProp().get();
    }

    /**
     * The property of episode or chapter start of manga or anime.
     *
     * @return the property of episode or chapter start of manga or anime
     */
    public IntegerProperty epiChapStartProp() {
        if (epiChapStartIntProp == null) {
            epiChapStartIntProp = new SimpleIntegerProperty(this, "epiChapStart");
        }

        return epiChapStartIntProp;
    }

    /**
     * Set the episode or chapter start of the manga or anime.
     *
     * @param epiChapStart the episode or chapter start of the manga or anime
     */
    public void setEpiChapStart(int epiChapStart) {
        epiChapStartProp().set(epiChapStart);
    }

    /**
     * Get the episode or chapter start of the manga or anime.
     *
     * @return the episode or chapter start of the manga or anime
     */
    public Integer getEpiChapStart() {
        return epiChapStartProp().get();
    }

    /**
     * The property of episode or chapter end of manga or anime.
     *
     * @return the episode or chapter end of manga or anime
     */
    public IntegerProperty epiChapEndProp() {
        if (epiChapEndIntProp == null) {
            epiChapEndIntProp = new SimpleIntegerProperty(this, "epiChapEnd");
        }

        return epiChapEndIntProp;
    }

    /**
     * Set the episode or chapter end of the manga or anime.
     *
     * @param epiChapEnd the episode or chapter end of the manga or anime
     */
    public void setEpiChapEnd(int epiChapEnd) {
        epiChapEndProp().set(epiChapEnd);
    }

    /**
     * Get the episode or chapter end of the manga or anime.
     *
     * @return the episode or chapter end of manga or anime
     */
    public Integer getEpiChapEnd() {
        return epiChapEndProp().get();
    }

    /**
     * The property of total episode or chapter of manga or anime.
     *
     * @return the total episode or chapter of manga or anime
     */
    public IntegerProperty totalEpiChapProp() {
        if (totalEpiChapIntProp == null) {
            totalEpiChapIntProp = new SimpleIntegerProperty(this, "totalEpiChap");
        }

        return totalEpiChapIntProp;
    }

    /**
     * Set the total episode or chapter of manga or anime.
     *
     * @param totalEpiChap the total episode for manga or anime
     */
    public void setTotalEpiChap(int totalEpiChap) {
        totalEpiChapProp().set(totalEpiChap);
    }

    /**
     * Get the total episode or chapter of manga or anime.
     *
     * @return the total episode or chapter of manga or anime
     */
    public Integer getTotalEpiChap() {
        return totalEpiChapProp().get();
    }

    /**
     * The property of all watched or read manga or anime.
     *
     * @return the all watched or read manga or anime
     */
    public StringProperty allWatchedReadProp() {
        if (allWatchedReadStrProp == null) {
            allWatchedReadStrProp = new SimpleStringProperty(this, "allWatchedRead");
        }

        return allWatchedReadStrProp;
    }

    /**
     * Set the all watched or read manga or anime.
     *
     * @param allWatchedRead the all watched or read manga or anime
     */
    public void setAllWatchedRead(String allWatchedRead) {
        allWatchedReadProp().set(allWatchedRead);
    }

    /**
     * Get all watched or read manga or anime.
     *
     * @return the all watched or read manga or anime
     */
    public String getAllWatchedRead() {
        return allWatchedReadProp().get();
    }

    /**
     * The property of start date of manga or anime.
     *
     * @return the property of start date of manga or anime
     */
    public StringProperty startDateProp() {
        if (startDateStrProp == null) {
            startDateStrProp = new SimpleStringProperty(this, "startDate");
        }

        return startDateStrProp;
    }

    /**
     * Set the start date of the manga or anime.
     *
     * @param startDate the start date of manga or anime
     */
    public void setStartDate(String startDate) {
        startDateProp().set(startDate);
    }

    /**
     * Get the start date of manga or anime.
     *
     * @return the start date of manga or anime
     */
    public String getStartDate() {
        return startDateProp().get();
    }

    /**
     * The property of end date of manga or anime.
     *
     * @return the property of end date of manga or anime
     */
    public StringProperty endDateProp() {
        if (endDateStrProp == null) {
            endDateStrProp = new SimpleStringProperty(this, "endDate");
        }

        return endDateStrProp;
    }

    /**
     * Set the end date of manga or anime.
     *
     * @param endDate the end date of manga or anime
     */
    public void setEndDate(String endDate) {
        endDateProp().set(endDate);
    }

    /**
     * Get the end date of manga or anime.
     *
     * @return the end date of manga or manga
     */
    public String getEndDate() {
        return endDateProp().get();
    }

    /**
     * The property of state of manga or anime.
     *
     * @return the property of the state of manga or anime
     */
    public StringProperty stateProp() {
        if (stateStrProp == null) {
            stateStrProp = new SimpleStringProperty(this, "state");
        }

        return stateStrProp;
    }

    /**
     * Set the state of manga or anime.
     *
     * @param state the state of manga or anime either ongoing or completed
     */
    public void setState(String state) {
        stateProp().set(state);
    }

    /**
     * Get the state of manga or anime.
     *
     * @return the state of manga or anime
     */
    public String getState() {
        return stateProp().get();
    }

    /**
     * The property of summary of manga or anime.
     *
     * @return the property of summary of manga or anime
     */
    public StringProperty summaryProp() {
        if (summaryStrProp == null) {
            summaryStrProp = new SimpleStringProperty(this, "summary");
        }

        return summaryStrProp;
    }

    /**
     * Set the summary of manga or anime.
     *
     * @param summary the summary of manga or anime
     */
    public void setSummary(String summary) {
        summaryProp().set(summary);
    }

    /**
     * Get the summary of manga or anime.
     *
     * @return the summary of manga or anime
     */
    public String getSummary() {
        return summaryProp().get();
    }

    /**
     * The property of volumes of manga.
     *
     * @return the property of volumes of manga
     */
    public IntegerProperty volumesProp() {
        if (volumesIntProp == null) {
            volumesIntProp = new SimpleIntegerProperty(this, "volumes");
        }

        return volumesIntProp;
    }

    /**
     * Set the volumes of manga.
     *
     * @param volumes the volumes of manga
     */
    public void setVolumes(int volumes) {
        volumesProp().set(volumes);
    }

    /**
     * Get the volumes of manga.
     *
     * @return the volumes of manga
     */
    public Integer getVolumes() {
        return volumesProp().get();
    }

    /**
     * The property of author of manga.
     *
     * @return the property of author of manga
     */
    public StringProperty authorProp() {
        if (authorStrProp == null) {
            authorStrProp = new SimpleStringProperty(this, "author");
        }

        return authorStrProp;
    }

    /**
     * Set the author of manga.
     *
     * @param author the author of manga
     */
    public void setAuthor(String author) {
        authorProp().set(author);
    }

    /**
     * Get the author of manga.
     *
     * @return the author of manga
     */
    public String getAuthor() {
        return authorProp().get();
    }

    /**
     * The property of created on of manga or anime.
     *
     * @return the property of created on of manga or anime
     */
    public StringProperty createdOnProp() {
        if (createdOnStrProp == null) {
            createdOnStrProp = new SimpleStringProperty(this, "createdOn");
        }

        return createdOnStrProp;
    }

    /**
     * Set the created on of manga or anime.
     *
     * @param createdOn the date created of manga or anime
     */
    public void setCreatedOn(String createdOn) {
        createdOnProp().set(createdOn);
    }

    /**
     * Get the created on of manga or anime.
     *
     * @return the created on of manga or anime
     */
    public String getCreatedOn() {
        return createdOnProp().get();
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
     * Create MangaNimeModel with connection check in the database. If database
     * connection fails the system will close.
     */
    public MangaNimeModel() {
        conn = DbUtil.connector();
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
     * Insert manganime data.
     *
     * @return error or success insert manganime
     * @throws SQLException
     */
    public boolean insertMangaNime() throws SQLException {
        boolean isSuccess = false;
        try {
            ArrayList<String> cols = new ArrayList<>();
            cols.add("manganime_id");
            cols.add("list_type");
            cols.add("title");
            cols.add("epi_chap_start");
            cols.add("epi_chap_end");
            cols.add("total_epi_chap");
            cols.add("all_watched_read");
            cols.add("start_date");
            cols.add("end_date");
            cols.add("state");
            if (getSummary() != null) {
                cols.add("summary");
            }
            if (getVolumes() != null) {
                cols.add("volumes");
            }
            if (getAuthor() != null) {
                cols.add("author");
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
            String mangaNimeId = utilSql.getRandGenId("manganime", "manganime_id");
            setMangaNimeId(mangaNimeId);
            String query = "INSERT INTO manganime (" + buildStrCols.toString() + ") VALUES (" + buildStrVals + ")";
            preparedStmt = conn.prepareStatement(query);
            ArrayList<Object> objVals = new ArrayList<>();
            objVals.add(mangaNimeId);
            objVals.add(getListType());
            objVals.add(getTitle());
            objVals.add(getEpiChapStart());
            objVals.add(getEpiChapEnd());
            objVals.add(getTotalEpiChap());
            objVals.add(getAllWatchedRead());
            objVals.add(getStartDate());
            objVals.add(getEndDate());
            objVals.add(getState());
            if (getSummary() != null) {
                objVals.add(getSummary());
            }
            if (getVolumes() != null) {
                objVals.add(getVolumes());
            }
            if (getAuthor() != null) {
                objVals.add(getAuthor());
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
                LogModel log = new LogModel();
                log.tableNameProp().setValue("manganime");
                log.mangaNimeIdProp().setValue(mangaNimeId);
                if (log.insertLog()) {
                    isSuccess = true;
                }
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

    /**
     * Get all manganime details either manga or anime.
     *
     * @param listType the list type of manga or anime
     * @return all manganime details
     * @throws SQLException
     */
    public ObservableList<Object> getAllMangaNime(String listType) throws SQLException {

        ObservableList data = FXCollections.observableArrayList();
        String query = "SELECT manganime.*, log.created_on "
                + "FROM manganime "
                + "LEFT JOIN log "
                + "ON log.manganime_id = manganime.manganime_id "
                + "WHERE list_type = ? "
                + "AND log.action = ?"
                + "ORDER BY title ASC";
        // preparedStmt is already autocloseable so no need to add in the finally block
        try {
            preparedStmt = conn.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            preparedStmt.setString(1, listType);
            preparedStmt.setString(2, "created");
            rs = preparedStmt.executeQuery();
            // Iterate all result set
            while (rs.next()) {
                MangaNimeModel mangaNimeData = new MangaNimeModel();
                mangaNimeData.setMangaNimeId(rs.getString("manganime_id"));
                mangaNimeData.setTitle(rs.getString("title"));
                mangaNimeData.setEpiChapStart(rs.getInt("epi_chap_start"));
                mangaNimeData.setEpiChapEnd(rs.getInt("epi_chap_end"));
                mangaNimeData.setTotalEpiChap(rs.getInt("total_epi_chap"));

                if (listType.equals("manga")) {
                    mangaNimeData.setVolumes(rs.getInt("volumes"));
                    mangaNimeData.setAuthor(rs.getString("author"));
                }

                String isAllWacthedRead = "no";
                if (rs.getBoolean("all_watched_read")) {
                    isAllWacthedRead = "yes";
                }

                mangaNimeData.setAllWatchedRead(isAllWacthedRead);
                mangaNimeData.setStartDate(rs.getString("start_date"));
                mangaNimeData.setEndDate(rs.getString("end_date"));
                mangaNimeData.setState(rs.getString("state"));
                mangaNimeData.setCreatedOn(DateTimeFormatter.formatDateTime(rs.getLong("created_on")));
                data.add(mangaNimeData);
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

        return data;
    }
}
