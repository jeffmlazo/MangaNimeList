package manganimelist;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import models.Anime;

/**
 * The main class of the system
 *
 * @author jeprox
 */
public class MangaNimeList extends Application {

    private final ObservableList<Anime> animeData = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {

        // Dummy values for anime data
        for (int i = 0; i < 100; i++) {
            Anime anime = new Anime();
            anime.setTitle("Anime" + i);
            anime.setEpisode(i);

            animeData.add(anime);
        }

        // Create FlowPane and set its properties
        FlowPane pane = new FlowPane();
        pane.getChildren().addAll(
                getHboxHeader1(),
                getHBoxHeader2(),
                getHBoxBody(),
                getHBoxFooter()
        );

        // The scene properties that dictates the window size
        Scene scene = new Scene(pane, 800, 700);

        // Set propeties for primaryStage
        primaryStage.setTitle("MangaNimeList");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox getHboxHeader1() {

        // Constant variables for menus
        final Menu menuFile = new Menu("File");
        final Menu menuEdit = new Menu("Edit");
        final Menu menuNew = new Menu("New");
        final Menu menuHelp = new Menu("Help");

        /* Menubars */
        MenuBar mainMenuBar = new MenuBar();
        mainMenuBar.getMenus().addAll(menuFile, menuEdit, menuNew, menuHelp);

        /* MenuItems*/
        // File Menu Items
        MenuItem menuItemFileOpen = new MenuItem("Open");
        MenuItem menuItemFileOpenRecent = new MenuItem("Open Recent");
        MenuItem menuItemFileQuit = new MenuItem("Quit");
        menuFile.getItems().addAll(menuItemFileOpen, menuItemFileOpenRecent, menuItemFileQuit);

        // Edit Menu Items
        MenuItem menuItemEditEdit = new MenuItem("Edit");
        MenuItem menuItemEditDelete = new MenuItem("Delete");
        menuEdit.getItems().addAll(menuItemEditEdit, menuItemEditDelete);

        // New Menu Items
        MenuItem menuItemNewAnime = new MenuItem("Anime");
        MenuItem menuItemNewManga = new MenuItem("Manga");
        menuNew.getItems().addAll(menuItemNewAnime, menuItemNewManga);

        // Help Menu Items
        MenuItem menuItemHelpHelpContents = new MenuItem("Help Contents");
        MenuItem menuItemHelpAbout = new MenuItem("About");
        menuHelp.getItems().addAll(menuItemHelpHelpContents, menuItemHelpAbout);

        /* Text Fields */
        // Create search text field and set its properties
        TextField tfSearch = new TextField("Enter Title");
        tfSearch.setId("tfSearch");
        tfSearch.setPrefColumnCount(15);
        tfSearch.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                tfSearch.clear();
            }
        });

        /* ComboBox */
        // Create combobox field and set its properties
        ComboBox<String> cboListType = new ComboBox<>();
        cboListType.getItems().addAll("Anime", "Manga");
        cboListType.setStyle("-fx-color: blue"); // Set the background of the combobox
        cboListType.setValue("Anime"); // Set the default value

        // Create a HBox and set its properties
        HBox hBox = new HBox();
        hBox.setHgrow(mainMenuBar, Priority.ALWAYS);
        hBox.setPrefWidth(810);
        hBox.getChildren().addAll(
                mainMenuBar,
                tfSearch,
                cboListType
        );

        return hBox;
    }

    private HBox getHBoxHeader2() {
        /* Toolbars */
        ToolBar toolbars = new ToolBar(
                new Button("View"),
                new Button("Edit"),
                new Button("Delete")
        );

        // Create a HBox and set its properties
        HBox hBox = new HBox();
        // Set the padding top, right, bottom, left
        hBox.setPadding(new Insets(0, 0, 10, 0));
        hBox.setHgrow(toolbars, Priority.ALWAYS);
        hBox.setPrefWidth(810);
        hBox.getChildren().addAll(
                toolbars
        );

        return hBox;
    }

    private HBox getHBoxBody() {
        TableView<Anime> table = new TableView<>();
        ObservableList<Anime> teamMembers = animeData;
        table.setItems(teamMembers);

        TableColumn<Anime, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory("title"));

        TableColumn<Anime, Integer> episodeCol = new TableColumn<>("Episode");
        episodeCol.setCellValueFactory(new PropertyValueFactory("episode"));

        table.getColumns().setAll(titleCol, episodeCol);
        /* Tabs*/
        TabPane mainTab = new TabPane();
        // Create anime tab and set its properties
        Tab animeTab = new Tab();
        animeTab.setText("Anime");
        animeTab.setContent(table);

        // Create mangaTab and set its properties
        Tab mangaTab = new Tab();
        mangaTab.setText("Manga");
        mangaTab.setContent(new Rectangle(760, 500, Color.DARKGRAY));

        // Set the properties for mainTab TabPane
        mainTab.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        mainTab.setBorder(new Border(
                new BorderStroke(
                        Color.GREY,
                        BorderStrokeStyle.SOLID,
                        CornerRadii.EMPTY,
                        new BorderWidths(1)
                )
        ));
        mainTab.getTabs().addAll(animeTab, mangaTab);

        HBox hBox = new HBox();
        // Set the padding top, right, bottom, left
        hBox.setPadding(new Insets(0, 0, 0, 20));
        hBox.getChildren().addAll(mainTab);

        return hBox;
    }

    private HBox getHBoxFooter() {
        /* Buttons */
        // Create button ok and set its properties
        Button btnOk = new Button();
        btnOk.setText("OK");
        btnOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                LocalDateTime ldt = LocalDateTime.now();
                DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                System.out.println("Time is: " + dtFormat.format(ldt));
            }
        });

        // Create button close and set its properties
        Button btnClose = new Button();
        btnClose.setText("Close");
        btnClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Platform.exit();
            }
        });

        HBox hBox = new HBox(4);
        // Set the padding top, right, bottom, left
        hBox.setPadding(new Insets(20, 20, 20, 0));
        hBox.setAlignment(Pos.BASELINE_RIGHT);
        hBox.setPrefWidth(810);
        hBox.getChildren().addAll(
                btnOk,
                btnClose
        );

        return hBox;
    }
}
