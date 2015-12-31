package main.java.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import main.java.models.GenreModel;
import main.java.configs.Enums;

/**
 * Add anime or manga controller
 *
 * @author jeprox
 */
public class AddController implements Initializable {

    private GenreModel genre = new GenreModel();

    @FXML
    private GridPane gridPAniGenre;
    @FXML
    private ComboBox cboAniState;
    @FXML
    private ComboBox cboAniAllWatched;

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

                CheckBox cb = new CheckBox(genre.genreNameProp().getValue());
                cb.setId(genre.genreIdProp().getValue().toString());

                gridPAniGenre.add(cb, colIndex, rowIndex);
                rowIndex++;

                // Check if row index reaches 13 index
                if (rowIndex == 13) {
                    colIndex++; // Increase the columnIndex count to +1 for next column grid
                    rowIndex = 0; // Reset the rowIndex count to 0
                }
            }

            cboAniState.setPromptText("State");
            Enums.MangaNimeState[] enumsState = Enums.MangaNimeState.values();
            for (Enums.MangaNimeState state : enumsState) {
                cboAniState.setId(state.getMangaNimeState());
                cboAniState.getItems().add(state);
            }

            cboAniAllWatched.setPromptText("All Watched");
            Enums.MangaNimeIsWatchedRead[] enumsWatchedRead = Enums.MangaNimeIsWatchedRead.values();
            for (Enums.MangaNimeIsWatchedRead isWatchedRead : enumsWatchedRead) {
                cboAniAllWatched.setId(isWatchedRead.getWatchedRead());
                cboAniAllWatched.getItems().add(isWatchedRead);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AddController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
