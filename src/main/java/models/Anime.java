package main.java.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author jeprox
 */
public class Anime {

    private StringProperty title;
    private IntegerProperty episode;
    private IntegerProperty totalEpisode;

    public void setTitle(String title) {
        titleProperty().set(title);
    }

    public String getTitle() {
        return titleProperty().get();
    }

    public StringProperty titleProperty() {
        if (title == null) {
            title = new SimpleStringProperty(this, "title");
        }

        return title;
    }

    public void setEpisode(int episode) {
        episodeProperty().set(episode);
    }

    public int getEpisode() {
        return episodeProperty().get();
    }

    public IntegerProperty episodeProperty() {
        if (episode == null) {
            episode = new SimpleIntegerProperty(this, "episode");
        }

        return episode;
    }

    public void setTotalEpisode(int totalEpisode) {
        episodeProperty().set(totalEpisode);
    }

}
