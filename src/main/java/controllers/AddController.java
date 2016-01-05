package main.java.controllers;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
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
import main.java.models.GenreModel;
import main.java.configs.Enums.MangaNimeIsWatchedRead;
import main.java.configs.Enums.MangaNimeState;
import main.java.models.MangaNimeModel;

/**
 * Add anime or manga controller
 *
 * @author jeprox
 */
public class AddController implements Initializable {

    private GenreModel genre = new GenreModel();
    private MangaNimeModel manganime = new MangaNimeModel();

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
            cboState.setPromptText("State");
            MangaNimeState[] enumsState = MangaNimeState.values();
            for (MangaNimeState state : enumsState) {
                cboState.getItems().add(state.getMangaNimeState());
            }

            // Set AllWatchedRead values
            cboAllWatchedRead.setPromptText("All Watched");
            MangaNimeIsWatchedRead[] enumsWatchedRead = MangaNimeIsWatchedRead.values();
            for (MangaNimeIsWatchedRead isWatchedRead : enumsWatchedRead) {
                cboAllWatchedRead.getItems().add(isWatchedRead.getWatchedRead());
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException, SQLException {

        // Failed value state, allWatchedRead, 
        if (event.getSource() == btnAdd) {
            String title = tfTitle.getText().trim();
            String state = cboState.valueProperty().getValue().toString();
            String totalEpiChap = tfTotalEpiChap.getText().trim();
            String startDate = dpStartDate.getValue().toString();
            String endDate = dpEndDate.getValue().toString();
            String allWatchedRead = cboAllWatchedRead.valueProperty().getValue().toString();
            String epiChapStart = tfEpiChapStart.getText().trim();
            String epiChapEnd = tfEpiChapEnd.getText().trim();
            String summary = taSummary.getText().trim();

            // Check if the value was yes or no then reassign the new value to 1 or 0 
            if (allWatchedRead.equals("yes")) {
                allWatchedRead = "1";
            } else {
                allWatchedRead = "0";
            }

            manganime.titleProp().setValue(title);
            manganime.listTypeProp().setValue("anime");
            manganime.epiChapStartProp().setValue(parseInt(epiChapStart));
            manganime.epiChapEndProp().setValue(parseInt(epiChapEnd));
            manganime.totalEpiChapProp().setValue(parseInt(totalEpiChap));
            manganime.allWatchedReadProp().setValue(allWatchedRead);
            manganime.startDateProp().setValue(startDate);
            manganime.endDateProp().setValue(endDate);
            manganime.stateProp().setValue(state);
            manganime.summaryProp().setValue(summary);
//            manganime.volumesProp().setValue(2);

            boolean isSuccess = manganime.insertMangaNime();
            if (isSuccess) {
                //TODO: Reload form to empty all fields and reload manganime list tables
            }
        } else if (event.getSource() == btnScreenSampUpload) {
            //TODO: button screen samp event codes
            System.out.println("Button Screen Samp Triggered");
        } else if (event.getSource() == btnThumbUpload) {
            //TODO: button upload event codes
            System.out.println("Button Thumbnail Triggered");
        } else if (event.getSource() == btnClose) {
//            ObservableList test = gridPaneGenre.getChildren();

            //TODO: button close event codes
            System.out.println("Button Close Triggered");
        }
    }

}
