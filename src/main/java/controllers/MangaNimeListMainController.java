package main.java.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.java.models.MangaNimeModel;

/**
 * MangaNimeList main page loader
 *
 * @author jeprox
 */
public class MangaNimeListMainController implements Initializable {

    private final MangaNimeModel mangaNime = new MangaNimeModel();

    @FXML
    private TextField tfSearch;
    @FXML
    private Button tbrBtnAdd;
    @FXML
    private TableView<MangaNimeModel> tblViewAnime;
    @FXML
    private TableColumn<MangaNimeModel, String> colAniId;
    @FXML
    private TableColumn<MangaNimeModel, String> colAniTitle;
    @FXML
    private TableColumn<MangaNimeModel, Integer> colAniEpiStart;
    @FXML
    private TableColumn<MangaNimeModel, Integer> colAniEpiEnd;
    @FXML
    private TableColumn<MangaNimeModel, Integer> colAniEpiTotal;
    @FXML
    private TableColumn<MangaNimeModel, String> colAniAllWatched;
    @FXML
    private TableColumn<MangaNimeModel, String> colAniReleaseDate;
    @FXML
    private TableColumn<MangaNimeModel, String> colAniEndDate;
    @FXML
    private TableColumn<MangaNimeModel, String> colAniState;
    @FXML
    private TableColumn<MangaNimeModel, Object> colAniCreatedOn;
    @FXML
    private Label lblAniTblEntries;
    @FXML
    private TableView<MangaNimeModel> tblViewManga;
    @FXML
    private TableColumn<MangaNimeModel, String> colMangaId;
    @FXML
    private TableColumn<MangaNimeModel, String> colMangaTitle;
    @FXML
    private TableColumn<MangaNimeModel, Integer> colMangaChapStart;
    @FXML
    private TableColumn<MangaNimeModel, Integer> colMangaChapEnd;
    @FXML
    private TableColumn<MangaNimeModel, Integer> colMangaChapTotal;
    @FXML
    private TableColumn<MangaNimeModel, Integer> colMangaVolumes;
    @FXML
    private TableColumn<MangaNimeModel, String> colMangaAllRead;
    @FXML
    private TableColumn<MangaNimeModel, String> colMangaPublishDate;
    @FXML
    private TableColumn<MangaNimeModel, String> colMangaEndDate;
    @FXML
    private TableColumn<MangaNimeModel, String> colMangaState;
    @FXML
    private TableColumn<MangaNimeModel, String> colMangaCreatedOn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (mangaNime.isDbConnected()) {
            System.out.println("Connected Success!");
            createMangaNimeTbl("anime");
            createMangaNimeTbl("manga");
        } else {
            System.out.println("Connected Failed!");
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {

        if (event.getSource() == tbrBtnAdd) {
            Stage stage = new Stage();
            //load up other FXML document
            Parent root = FXMLLoader.load(getClass().getResource("/main/resources/views/anime/AddAnimeModal.fxml"));
//            Parent root = FXMLLoader.load(getClass().getResource("/main/resources/views/anime/UpdateAnimeModal.fxml"));
//            Parent root = FXMLLoader.load(getClass().getResource("/main/resources/views/anime/ViewAnimeModal.fxml"));
//            Parent root = FXMLLoader.load(getClass().getResource("/main/resources/views/manga/AddMangaModal.fxml"));
            //create a new scene with root and set the stage
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/main/resources/css/global.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Add New");
            stage.show();
        }
    }

    /**
     * Generate a table view for manga or anime
     *
     * @param listType the list type of manga or anime.
     */
    public void createMangaNimeTbl(String listType) {
        try {
            ObservableList olMangaNime = mangaNime.getAllMangaNime(listType);
            int numRows = olMangaNime.size();

            TableView tblViewList = tblViewAnime;
            TableColumn colMangaNimeId = colAniId;
            TableColumn colTitle = colAniTitle;
            TableColumn colEpiChapStart = colAniEpiStart;
            TableColumn colEpiChapEnd = colAniEpiEnd;
            TableColumn colTotalEpiChap = colAniEpiTotal;
            TableColumn colAllWatchedRead = colAniAllWatched;
            TableColumn colStartDate = colAniReleaseDate;
            TableColumn colEndDate = colAniEndDate;
            TableColumn colState = colAniState;
            Label lblTblEntries = lblAniTblEntries;

            if (listType.equals("manga")) {
                tblViewList = tblViewManga;
                colMangaNimeId = colMangaId;
                colTitle = colMangaTitle;
                colEpiChapStart = colMangaChapStart;
                colEpiChapEnd = colMangaChapEnd;
                colTotalEpiChap = colMangaChapTotal;
                colAllWatchedRead = colMangaAllRead;
                colStartDate = colMangaPublishDate;
                colEndDate = colMangaEndDate;
                colState = colMangaState;
                colMangaVolumes.setCellValueFactory(new PropertyValueFactory("volumes"));
            }

            tblViewList.setItems(olMangaNime);
            colMangaNimeId.setCellValueFactory(new PropertyValueFactory("mangaNimeId"));
            colTitle.setCellValueFactory(new PropertyValueFactory("title"));
            colEpiChapStart.setCellValueFactory(new PropertyValueFactory("epiChapStart"));
            colEpiChapEnd.setCellValueFactory(new PropertyValueFactory("epiChapEnd"));
            colTotalEpiChap.setCellValueFactory(new PropertyValueFactory("totalEpiChap"));
            colAllWatchedRead.setCellValueFactory(new PropertyValueFactory("allWatchedRead"));
            colStartDate.setCellValueFactory(new PropertyValueFactory("startDate"));
            colEndDate.setCellValueFactory(new PropertyValueFactory("endDate"));
            colState.setCellValueFactory(new PropertyValueFactory("state"));
            lblTblEntries.setText("Showing " + numRows + " entries");

        } catch (SQLException ex) {
            Logger.getLogger(MangaNimeListMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
