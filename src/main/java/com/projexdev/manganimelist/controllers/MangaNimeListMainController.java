package com.projexdev.manganimelist.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import com.projexdev.manganimelist.models.MangaNimeModel;
import com.projexdev.manganimelist.configs.DbHandler;
import javafx.collections.FXCollections;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;

/**
 * MangaNimeList main page loader
 *
 * @author jeprox
 */
public class MangaNimeListMainController implements Initializable {

    private final MangaNimeModel manganime = new MangaNimeModel();
    private final DbHandler setUpTable = new DbHandler();
    private ObservableList<Object> olAnime = FXCollections.observableArrayList();
    private ObservableList<Object> olManga = FXCollections.observableArrayList();

    @FXML
    private Tab tabAnime;
    @FXML
    private TextField tfSearch;
    @FXML
    private Button tbrBtnAdd, tbrBtnView, tbrBtnUpdate, tbrBtnDelete;
    @FXML
    private TableView<MangaNimeModel> tblViewAnime;
    @FXML
    private TableColumn<MangaNimeModel, String> colAniId, colAniTitle, colAniAllWatched,
            colAniReleaseDate, colAniEndDate, colAniState;
    @FXML
    private TableColumn<MangaNimeModel, Integer> colAniEpiStart, colAniEpiEnd, colAniEpiTotal;
    @FXML
    private TableColumn<MangaNimeModel, Object> colAniCreatedOn;
    @FXML
    private TableView<MangaNimeModel> tblViewManga;
    @FXML
    private TableColumn<MangaNimeModel, String> colMangaId, colMangaTitle, colMangaAllRead, colMangaPublishDate,
            colMangaEndDate, colMangaAuthor, colMangaState;
    @FXML
    private TableColumn<MangaNimeModel, Integer> colMangaChapStart, colMangaChapEnd, colMangaChapTotal,
            colMangaVolumes;
    @FXML
    private TableColumn<MangaNimeModel, Object> colMangaCreatedOn;
    @FXML
    private Label lblTblEntries;
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (manganime.isDbConnected()) {
            // Create tables in the database
            setUpTable.setUpManganime();
            setUpTable.setUpGenre();
            setUpTable.setUpGenres();
            setUpTable.setUpImages();
            setUpTable.setUpLog();

            // Set create cell value factories for anime & manga
            createCellValueFactories("anime");
            createCellValueFactories("manga");

            // Set table items for anime & manga
            olAnime = createTableItems("anime");
            olManga = createTableItems("manga");

            /*
             Add sort order column for anime & manga after loading the table
             items. It requires to set a sortType="DESCENDING" in the fxml.
             */
            tblViewAnime.getSortOrder().add(colAniCreatedOn);
            tblViewManga.getSortOrder().add(colMangaCreatedOn);

            // Lets just set the number of Anime numrow since it is the first tab that will be shown in the table view
            lblTblEntries.setText("Showing " + olAnime.size() + " entries");
        }
    }

    /**
     * Event handle for tool bar buttons.
     *
     * @param event
     * @throws IOException
     */
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
                loader.setLocation(getClass().getResource("/views/anime/AddAnimeModal.fxml"));

            } else {
                stageTitle = "Add Manga";
                loader.setLocation(getClass().getResource("/views/manga/AddMangaModal.fxml"));
            }

            AddController AddCtrl = new AddController();
            AddCtrl.setListType(listType);
            loader.setController(AddCtrl);

        } else if (event.getSource() == tbrBtnView) {
            // Check if what tab is actively selected
            if (tabAnime.isSelected()) {
                stageTitle = "View Anime";
                loader.setLocation(getClass().getResource("/views/anime/ViewAnimeModal.fxml"));
            } else {
                stageTitle = "View Manga";
                loader.setLocation(getClass().getResource("/views/manga/ViewMangaModal.fxml"));
            }
        } else if (event.getSource() == tbrBtnUpdate) {
            // Check if what tab is actively selected
            if (tabAnime.isSelected()) {
                stageTitle = "Update Anime";
                loader.setLocation(getClass().getResource("/views/anime/UpdateAnimeModal.fxml"));
            } else {
                stageTitle = "Update Manga";
                loader.setLocation(getClass().getResource("/views/manga/UpdateMangaModal.fxml"));
            }
        } else if (event.getSource() == tbrBtnDelete) {

            // FIXME: Need to implement this adding new row after saving anime or manga details
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
            olAnime.add(mangaNimeData);

            lblTblEntries.setText("Showing " + olAnime.size() + " entries");
            tblViewAnime.sort(); // Sort again the table after adding new data

//            System.out.println("Delete Button Triggered");
//            tblViewAnime.setItems(null); // This code works for clearing the tblView in anime
//            olMangaNime.clear();
        }

        // Check here if the button Delete was not triggered
        if (event.getSource() != tbrBtnDelete) {
            // FIXME: Need to fix the centering the modal after the parent element was minimize
            // Create a new scene with root and set the stage
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/styles/global.css").toExternalForm());
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); // Restrict duplicate window to be shown
            stage.initOwner(tbrBtnAdd.getScene().getWindow()); // Need to get the tbrBtnAdd parent window to be set as owner
            stage.setTitle(stageTitle);
//            stage.showAndWait();
            stage.show();
        }
    }

    /**
     * Event handler for tab selection.
     *
     * @param event handler for selecting tabs
     */
    @FXML
    private void onTabSelect(Event event) {
        Tab tab = (Tab) event.getSource();
        // Check if the tab was selected and the label lblTblEntries is not null 
        // this will prevent the error in runtime
        if (tab.isSelected() && lblTblEntries != null) {
            int entries = olAnime.size();
            if (tab.getId().equals("tabManga")) {
                entries = olManga.size();
            }

            lblTblEntries.setText("Showing " + entries + " entries");
        }
    }

    @FXML
    private void handleClose(ActionEvent event) {
        ((Stage) rootPane.getScene().getWindow()).close();
    }

    /**
     * Create a cell value factories for manga or anime
     *
     * @param listType the list type of manga or anime.
     */
    private void createCellValueFactories(String listType) {
        TableColumn colMangaNimeId = colAniId;
        TableColumn colTitle = colAniTitle;
        TableColumn colEpiChapStart = colAniEpiStart;
        TableColumn colEpiChapEnd = colAniEpiEnd;
        TableColumn colTotalEpiChap = colAniEpiTotal;
        TableColumn colAllWatchedRead = colAniAllWatched;
        TableColumn colStartDate = colAniReleaseDate;
        TableColumn colEndDate = colAniEndDate;
        TableColumn colState = colAniState;
        TableColumn colCreatedOn = colAniCreatedOn;

        if (listType.equals("manga")) {
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
            colMangaAuthor.setCellValueFactory(new PropertyValueFactory("author"));
            colCreatedOn = colMangaCreatedOn;
        }

        colMangaNimeId.setCellValueFactory(new PropertyValueFactory("mangaNimeId"));
        colTitle.setCellValueFactory(new PropertyValueFactory("title"));
        colEpiChapStart.setCellValueFactory(new PropertyValueFactory("epiChapStart"));
        colEpiChapEnd.setCellValueFactory(new PropertyValueFactory("epiChapEnd"));
        colTotalEpiChap.setCellValueFactory(new PropertyValueFactory("totalEpiChap"));
        colAllWatchedRead.setCellValueFactory(new PropertyValueFactory("allWatchedRead"));
        colStartDate.setCellValueFactory(new PropertyValueFactory("startDate"));
        colEndDate.setCellValueFactory(new PropertyValueFactory("endDate"));
        colState.setCellValueFactory(new PropertyValueFactory("state"));
        colCreatedOn.setCellValueFactory(new PropertyValueFactory("createdOn"));

    }

    /**
     * Create a table items for manga or anime
     *
     * @param listType the list type of manga or anime.
     * @return the number of rows
     */
    private ObservableList<Object> createTableItems(String listType) {
        ObservableList<Object> olMangaNime = null;
        try {
            olMangaNime = manganime.getAllMangaNime(listType);
            TableView tblViewList = tblViewAnime;
            if (listType.equals("manga")) {
                tblViewList = tblViewManga;
            }

            tblViewList.setItems(olMangaNime);
        } catch (SQLException ex) {
            Logger.getLogger(MangaNimeListMainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return olMangaNime;
    }

    /*
    This code can call from the AddController but the olAnime clear & add  isn't working.
     */
    public void addNewRow() {
        olAnime.clear();
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
        olAnime.add(mangaNimeData);

//        lblTblEntries.setText("Showing " + olAnime.size() + " entriestest");
//        tblViewAnime.sort(); // Sort again the table after adding new data
    }
}
