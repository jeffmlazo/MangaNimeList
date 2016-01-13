package main.java.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.java.models.MangaNimeModel;

/**
 * MangaNimeList main page loader
 *
 * @author jeprox
 */
public class MangaNimeListMainController implements Initializable {

    private final MangaNimeModel manganime = new MangaNimeModel();

    @FXML
    private Tab tabAnime;
    @FXML
    private Tab tabManga;
    @FXML
    private TextField tfSearch;
    @FXML
    private Button tbrBtnAdd;
    @FXML
    private Button tbrBtnView;
    @FXML
    private Button tbrBtnUpdate;
    @FXML
    private Button tbrBtnDelete;
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

        if (manganime.isDbConnected()) {
            createMangaNimeTbl("anime");
            createMangaNimeTbl("manga");
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        String stageTitle = "";

        String listType = "manga";
        if (tabAnime.isSelected()) {
            listType = "anime";
        }

        if (event.getSource() == tbrBtnAdd) {
            // Check if what tab is actively selected
            if (tabAnime.isSelected()) {
                stageTitle = "Add Anime";
                loader.setLocation(getClass().getResource("/main/resources/views/anime/AddAnimeModal.fxml"));

            } else {
                stageTitle = "Add Manga";
                loader.setLocation(getClass().getResource("/main/resources/views/manga/AddMangaModal.fxml"));
            }

            AddController controller = new AddController();
            controller.setListType(listType);
            loader.setController(controller);

        } else if (event.getSource() == tbrBtnView) {
            // Check if what tab is actively selected
            if (tabAnime.isSelected()) {
                stageTitle = "View Anime";
                loader.setLocation(getClass().getResource("/main/resources/views/anime/ViewAnimeModal.fxml"));
            } else {
                stageTitle = "View Manga";
                loader.setLocation(getClass().getResource("/main/resources/views/manga/ViewMangaModal.fxml"));
            }
        } else if (event.getSource() == tbrBtnUpdate) {
            // Check if what tab is actively selected
            if (tabAnime.isSelected()) {
                stageTitle = "Update Anime";
                loader.setLocation(getClass().getResource("/main/resources/views/anime/UpdateAnimeModal.fxml"));
            } else {
                stageTitle = "UpdateManga";
                loader.setLocation(getClass().getResource("/main/resources/views/manga/UpdateMangaModal.fxml"));
            }
        } else if (event.getSource() == tbrBtnDelete) {
            System.out.println("Delete Button Triggered");
        }

        //create a new scene with root and set the stage
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/main/resources/css/global.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle(stageTitle);
        stage.show();
    }

    /**
     * Generate a table view for manga or anime
     *
     * @param listType the list type of manga or anime.
     */
    public void createMangaNimeTbl(String listType) {
        try {
            ObservableList<Object> olMangaNime = manganime.getAllMangaNime(listType);
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
