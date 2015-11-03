package models;

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

    public void setEpisode(Integer episode) {
        episodeProperty().set(episode);
    }

    public Integer getEpisode() {
        return episodeProperty().get();
    }

    public IntegerProperty episodeProperty() {
        if (episode == null) {
            episode = new SimpleIntegerProperty(this, "episode");
        }

        return episode;
    }

}
