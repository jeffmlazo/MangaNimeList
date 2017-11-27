package com.projexdev.manganimelist.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import com.projexdev.manganimelist.models.GenreModel;
import com.projexdev.manganimelist.configs.Enums.MangaNimeIsWatchedRead;
import com.projexdev.manganimelist.configs.Enums.MangaNimeState;
import com.projexdev.manganimelist.libraries.FormUtil;
import com.projexdev.manganimelist.libraries.MsgBox;
import com.projexdev.manganimelist.models.MangaNimeModel;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.stage.Window;
import static java.lang.Integer.parseInt;
import com.projexdev.manganimelist.configs.DbHandler;
import com.projexdev.manganimelist.libraries.DateTimeFormatter;
import java.util.Calendar;
import java.util.function.Consumer;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;

/**
 * Add anime or manga controller
 *
 * @author jeprox
 */
public class AddController implements Initializable {

    private GenreModel genre = new GenreModel();
    private final MangaNimeModel manganime = new MangaNimeModel();
    private final DbHandler setUpTable = new DbHandler();
    private TableView table;
//    private Consumer<ObservableList> callback;
    private StringProperty listTypeProp = new SimpleStringProperty();

    @FXML
    private GridPane gridPaneForm;
    @FXML
    private GridPane gridPaneGenre;
    @FXML
    private TextField tfTitle;
    @FXML
    private ComboBox cboState;
    @FXML
    private TextField tfTotalEpiChap;
    @FXML
    private DatePicker dpStartDate;
    @FXML
    private DatePicker dpEndDate;
    @FXML
    private ComboBox cboAllWatchedRead;
    @FXML
    private TextField tfEpiChapStart;
    @FXML
    private TextField tfEpiChapEnd;
    @FXML
    private TextField tfVolumes;
    @FXML
    private TextField tfAuthor;
    @FXML
    private ImageView imgvEpiChapInfo;
    @FXML
    private TextArea taSummary;
    @FXML
    private ImageView imgvScreenshotsInfo;
    @FXML
    private Button btnScreenSampUpload;
    @FXML
    private ImageView imgvProfPic;
    @FXML
    private TextField tfThumbnail;
    @FXML
    private Button btnThumbUpload;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnClose;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Initialize for the column and row index in the gridpane
            int colIndex = 0;
            int rowIndex = 0;
            Iterator olGenre = genre.getAllGenre().iterator();

            while (olGenre.hasNext()) {

                genre = (GenreModel) olGenre.next();

                // Create checkbox with genre name
                CheckBox cb = new CheckBox(genre.genreNameProp().getValue());
                // Set each genre with their id
                cb.setId(genre.genreIdProp().getValue().toString());
                // Set the class with checkbox-genre
                cb.getStyleClass().add("checbox-genre");

                gridPaneGenre.add(cb, colIndex, rowIndex);
                rowIndex++;

                // Check if row index reaches 13 index
                if (rowIndex == 13) {
                    colIndex++; // Increase the columnIndex count to +1 for next column grid
                    rowIndex = 0; // Reset the rowIndex count to 0
                }
            }

            // Set State values
            MangaNimeState[] enumsState = MangaNimeState.values();
            for (MangaNimeState enumState : enumsState) {
                cboState.getItems().add(enumState.getMangaNimeState());
            }

            // Set AllWatchedRead values            
            MangaNimeIsWatchedRead[] enumsWatchedRead = MangaNimeIsWatchedRead.values();
            for (MangaNimeIsWatchedRead isWatchedRead : enumsWatchedRead) {
                cboAllWatchedRead.getItems().add(isWatchedRead.getWatchedRead());
            }

            // Set a default value for EpiChapStart to 1
            tfEpiChapStart.setText("1");
        } catch (SQLException ex) {
            Logger.getLogger(AddController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException, SQLException {

        if (event.getSource() == btnAdd) {
            String listType = listTypeProp.getValue();
            String title = tfTitle.getText().trim();
            Object state = cboState.valueProperty().getValue();
            String totalEpiChap = tfTotalEpiChap.getText().trim();
            LocalDate startDate = dpStartDate.getValue();
            LocalDate endDate = dpEndDate.getValue();
            Object allWatchedRead = cboAllWatchedRead.valueProperty().getValue();
            String epiChapStart = tfEpiChapStart.getText().trim();
            String epiChapEnd = tfEpiChapEnd.getText().trim();
            String summary = taSummary.getText().trim();

            boolean isChckGenre = false;
            ArrayList<Integer> arrGenres = new ArrayList<>();
            ObservableList<Node> chckBoxes;
            Iterator iteratorChckBoxes;
            CheckBox chckBox;
            chckBoxes = gridPaneGenre.getChildren();
            iteratorChckBoxes = chckBoxes.iterator();
            while (iteratorChckBoxes.hasNext()) {
                chckBox = (CheckBox) iteratorChckBoxes.next();
                if (chckBox.isSelected()) {
                    arrGenres.add(parseInt(chckBox.getId()));
                    isChckGenre = true;
                }
            }

            LinkedHashMap<String, Object> mapObjCtrls = new LinkedHashMap<>();
            String keyTotalEpiChap = "Total Episodes";
            String keyStartDate = "Release Date";
            String keyAllWatchedRead = "All Watched";
            String keyEpiChapStart = "Episode Start";
            String keyEpiChapEnd = "Episode End";

            if (listType.equals("manga")) {
                keyTotalEpiChap = "Total Chapters";
                keyStartDate = "Publish Date";
                keyAllWatchedRead = "All Read";
                keyEpiChapStart = "Chapter Start";
                keyEpiChapEnd = "Chapter End";
            }

            mapObjCtrls.put("Title", title);
            mapObjCtrls.put("State", state);
            mapObjCtrls.put(keyTotalEpiChap, totalEpiChap);
            mapObjCtrls.put(keyStartDate, startDate);
            mapObjCtrls.put("End Date", endDate);
            mapObjCtrls.put(keyAllWatchedRead, allWatchedRead);
            mapObjCtrls.put(keyEpiChapStart, epiChapStart);
            mapObjCtrls.put(keyEpiChapEnd, epiChapEnd);

            if (listType.equals("manga")) {
                mapObjCtrls.put("Volumes", tfVolumes.getText().trim());
            }

            mapObjCtrls.put("Genre", isChckGenre);

            ArrayList<String> errors = FormUtil.validateForm(mapObjCtrls);
            Iterator<String> iterator = errors.listIterator();
            StringBuilder strBuildErr = new StringBuilder();
            while (iterator.hasNext()) {
                String error = iterator.next();
                strBuildErr.append(error).append('\n');
            }

            // Check if no errors has been return means that all form fields have pass
            if (errors.isEmpty()) {
                // Check if the value was yes or no then reassign the new value to 1 or 0
                String newAllWatchedRead = "0";
                if (allWatchedRead.equals("yes")) {
                    newAllWatchedRead = "1";
                }

                manganime.titleProp().setValue(title);
                manganime.listTypeProp().setValue(listType);
                manganime.epiChapStartProp().setValue(parseInt(epiChapStart));
                manganime.epiChapEndProp().setValue(parseInt(epiChapEnd));
                manganime.totalEpiChapProp().setValue(parseInt(totalEpiChap));
                manganime.allWatchedReadProp().setValue(newAllWatchedRead.toString());
                manganime.startDateProp().setValue(startDate.toString());
                manganime.endDateProp().setValue(endDate.toString());
                manganime.stateProp().setValue(state.toString());
                manganime.summaryProp().setValue(summary);

                if (listType.equals("manga")) {
                    manganime.volumesProp().setValue(parseInt(tfVolumes.getText().trim()));
                    manganime.authorProp().setValue(tfAuthor.getText().trim());
                }

                if (manganime.insertMangaNime() && genre.insertGenres(arrGenres, manganime.mangaNimeIdProp().getValue())) {
                    //TODO: Reload manganime list tables after successfully added
                    ArrayList<String> success = new ArrayList<>();
                    String msgTitle = "Anime";
                    // Common control fields to be cleared after adding Anime or Manga
                    tfTitle.clear();
                    // Reset the State combobox with the prompText
                    cboState.getSelectionModel().clearSelection();
                    tfTotalEpiChap.clear();
                    dpStartDate.getEditor().clear();
                    dpEndDate.getEditor().clear();
                    // Reset the All Wached Read combobox with the prompText
                    cboAllWatchedRead.getSelectionModel().clearSelection();
                    tfEpiChapStart.setText("1");
                    tfEpiChapEnd.clear();
                    taSummary.clear();

                    // Get all checkboxes & uncheck them
                    chckBoxes = gridPaneGenre.getChildren();
                    iteratorChckBoxes = chckBoxes.iterator();
                    while (iteratorChckBoxes.hasNext()) {
                        chckBox = (CheckBox) iteratorChckBoxes.next();
                        chckBox.setSelected(false);
                    }

                    if (listType.equals("manga")) {
                        msgTitle = "Manga";
                        tfVolumes.clear();
                        tfAuthor.clear();
                    }

                    success.add(msgTitle + " was successfully added!");
                    MsgBox.showModal(success, "success");

                    // TODO: Need to implement the alternative code for refresing the table using the callback consumer
                    MangaNimeModel mangaNimeData = new MangaNimeModel();
                    mangaNimeData.mangaNimeIdProp().setValue(manganime.mangaNimeIdProp().getValue());
                    mangaNimeData.titleProp().setValue(title);
                    mangaNimeData.epiChapStartProp().setValue(parseInt(epiChapStart));
                    mangaNimeData.epiChapEndProp().setValue(parseInt(epiChapEnd));
                    mangaNimeData.totalEpiChapProp().setValue(parseInt(totalEpiChap));
                    mangaNimeData.allWatchedReadProp().setValue(allWatchedRead.toString());
                    mangaNimeData.startDateProp().setValue(DateTimeFormatter.formatDate(startDate.toString()));
                    mangaNimeData.endDateProp().setValue(DateTimeFormatter.formatDate(endDate.toString()));
                    mangaNimeData.stateProp().setValue(state.toString());
                    // Get the current date & time
                    Calendar cal = Calendar.getInstance();
                    long currentDateTime = cal.getTimeInMillis();
                    mangaNimeData.createdOnProp().setValue(DateTimeFormatter.formatDateTime(currentDateTime)); // Date is missing need to get the data from logs or just create a localdate here
                    table.getItems().add(mangaNimeData);
                    table.sort(); // FIXME: Sorting didn't work
                }
            } else {
                MsgBox.showModal(errors, "error");
            }
        } else if (event.getSource() == btnScreenSampUpload) {
            //TODO: button screen samp event codes
            System.out.println("Button Screen Samp Triggered");
        } else if (event.getSource() == btnThumbUpload) {
            //TODO: button upload event codes
            System.out.println("Button Thumbnail Triggered");

        } else if (event.getSource() == btnClose) {
            // Get the scene using the button close to close the window
//            btnClose.getScene().getWindow().hide();

            MangaNimeModel mangaNimeData = new MangaNimeModel();
            mangaNimeData.setMangaNimeId("089as0d808sd0a8s");
            mangaNimeData.setTitle("Dummy Title");
            mangaNimeData.setEpiChapStart(2);
            mangaNimeData.setEpiChapEnd(122);
            mangaNimeData.setTotalEpiChap(100);
            mangaNimeData.setAllWatchedRead("yes");
            mangaNimeData.setStartDate("12/01/2013");
            mangaNimeData.setEndDate("12/23/2014");
            mangaNimeData.setState("completed");
            mangaNimeData.setCreatedOn("12/22/2017 12:00 AM");
            table.getItems().add(mangaNimeData);
            table.sort();
        }
    }

//    public void setNewRowConsumer(Consumer<ObservableList> callback) {
//        this.callback = callback;
//    }
    public ObservableList<Object> setNewRow() {
        ObservableList<Object> rowData = FXCollections.observableArrayList();
        rowData.add(manganime.getMangaNimeId());
        rowData.add(manganime.getTitle());
        rowData.add(manganime.getEpiChapStart());
        rowData.add(manganime.getEpiChapEnd());
        rowData.add(manganime.getTotalEpiChap());
        rowData.add(manganime.getAllWatchedRead());
        rowData.add(manganime.getStartDate());
        rowData.add(manganime.getEndDate());
        rowData.add(manganime.getState());
        rowData.add(manganime.getCreatedOn());

        return rowData;
    }

    /**
     * Set the table from the MangaNimeListMainController.
     *
     * @param table the table from the parent controller
     */
    public void setTable(TableView table) {
        this.table = table;
    }

    /**
     * Set the list type of manga or anime.
     *
     * @param listType the type of list manga or anime
     */
    public void setListType(String listType) {
        listTypeProp.setValue(listType);
    }
}
