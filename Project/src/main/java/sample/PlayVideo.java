package sample;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;

public class PlayVideo {
    public void playVideo(File fileName) throws Exception{

        AnchorPane anchorPane = new AnchorPane();
        MediaView mediaview = new MediaView();
        anchorPane.getChildren().addAll(mediaview);
        Stage stage = new Stage();
        stage.setTitle("Linux Video Play Test");
        stage.setScene(new Scene(anchorPane, 1500, 800));
        stage.show();

        final Media media=new Media(fileName.toURI().toString());
        final MediaPlayer mediaplayer = new MediaPlayer(media);
        mediaview.setMediaPlayer(mediaplayer);
        mediaplayer.play();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                mediaplayer.stop();
            }
        });
    }
}
