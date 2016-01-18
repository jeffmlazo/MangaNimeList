package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.controllers.MangaNimeListMainController;

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/views/MangaNimeListMain.fxml"));
        MangaNimeListMainController manganimeCtrl = new MangaNimeListMainController();
        manganimeCtrl.setMainStage(stage);
        loader.setController(manganimeCtrl);
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/main/resources/css/global.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("MangaNimeList");
        stage.show();
    }

}
