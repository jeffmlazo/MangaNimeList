package main.java.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
import main.java.models.Anime;

/**
 * FXML Controller class
 *
 * @author jeprox
 */
public class MangaNimeListMainController implements Initializable {

    private final ObservableList<Anime> animeData = FXCollections.observableArrayList();

    @FXML
    private TextField tfSearch;
    @FXML
    private TableView<Anime> tblViewAnime;
    @FXML
    private TableColumn<Anime, String> colAnimeTitle;
    @FXML
    private TableColumn<Anime, Integer> colAnimeEpisode;
    @FXML
    private Button tbBtnAdd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Dummy values for anime data
        for (int i = 0; i < 100; i++) {
            Anime anime = new Anime();
            anime.setTitle("Anime" + i);
            anime.setEpisode(i);

            animeData.add(anime);
        }

        ObservableList<Anime> teamMembers = animeData;
        tblViewAnime.setItems(teamMembers);
        colAnimeTitle.setCellValueFactory(new PropertyValueFactory("title"));
        colAnimeEpisode.setCellValueFactory(new PropertyValueFactory("episode"));
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {

        if (event.getSource() == tbBtnAdd) {
            Stage stage = new Stage();
            //load up other FXML document
//            Parent root = FXMLLoader.load(getClass().getResource("/main/resources/views/anime/AddAnimeModal.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("/main/resources/views/manga/AddMangaModal.fxml"));
            //create a new scene with root and set the stage
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/main/resources/css/global.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Add New");
            stage.show();
        }
    }
}
