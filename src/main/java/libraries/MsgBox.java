package main.java.libraries;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author jeprox
 */
public class MsgBox {

    /**
     * Create a message box that it is either error or success.
     *
     * @param arrMsg the array list of message(s)
     * @param status the status of the message either error or success
     * @throws IOException
     */
    public void showModal(ArrayList<String> arrMsg, String status) throws IOException {
        Stage stage = new Stage();
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);

        String stageTitle = "Error(s)";
        if (status.equals("success")) {
            stageTitle = "Success";
        }

        // Create new labels for each msg
        ObservableList<Label> obListLbl = FXCollections.observableArrayList();
        Iterator<String> iteratorMsg = arrMsg.iterator();
        while (iteratorMsg.hasNext()) {
            String msg = iteratorMsg.next();
            obListLbl.add(new Label(msg));
        }

        // VBox wrapper for the center content area
        VBox vBoxContent = new VBox();
        vBoxContent.getChildren().addAll(obListLbl);
        root.setCenter(vBoxContent);

        // HBox Bottom  elements
        Button btnOK = new Button("OK");
        btnOK.setOnAction(e -> stage.close());
        // HBox wrapper for the bottom area
        HBox hBoxBottom = new HBox();
        hBoxBottom.getChildren().addAll(btnOK);
        hBoxBottom.alignmentProperty().setValue(Pos.CENTER_RIGHT);
        // Set margin of hBoxBottom top=10, right=0, bottom=10, left=0
        BorderPane.setMargin(hBoxBottom, new Insets(10, 0, 10, 0));
        root.setBottom(hBoxBottom);

        // Set padding top=0, right=10, bottom=0, left=10
        root.paddingProperty().setValue(new Insets(0, 10, 0, 10));
        root.prefWidth(400);
        stage.setScene(scene);
        stage.setTitle(stageTitle);
        stage.resizableProperty().setValue(false);
        stage.show();
    }
}
