package com.manganimelist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author jeprox
 */
public class MangaNimeListMain extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/views/MangaNimeListMain.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("/resources/css/global.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("MangaNimeList");
        stage.show();
    }

}
