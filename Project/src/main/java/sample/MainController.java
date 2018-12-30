package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    @FXML private Button downloadButton;
    @FXML private Button downloadLaterButton;
    @FXML private Button viewVideo;
    @FXML private Button deleteButton;

    @FXML private ListView<String> listVideo;

    ObservableList<String> result;

    private File fileName;

    @FXML
    public void initialize() throws IOException {

        downloadButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                onClickDownloadButton();
            }
        });

        downloadLaterButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                onClickDownloadLaterButton();
            }
        });

        viewVideo.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                onClickViewVideo();
            }
        });

        loadListVideo();

        listVideo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                fileName = new File("D:\\\\Университет\\\\проект\\\\видос\\\\" + newValue);
            }
        });

        deleteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                fileName.delete();
                loadListVideo();
            }
        });
    }

    public void loadListVideo() {
        List results = new ArrayList<String>();

        File[] files = new File("D:\\\\Университет\\\\проект\\\\видос\\\\").listFiles();
//If this pathname does not denote a directory, then listFiles() returns null.

        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName());
            }
        }
        result = FXCollections.observableArrayList(results);
        listVideo.setItems(result);
    }

    private void onClickDownloadButton() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Download.fxml"));
            stage.setScene(new Scene(root, 500,500));
            stage.show();
            loadListVideo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onClickDownloadLaterButton() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("DownloadLater.fxml"));
            stage.setScene(new Scene(root, 600,500));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onClickViewVideo() {
        PlayVideo playVideo = new PlayVideo();
        if (fileName == null) {
            final Stage stage = new Stage();
            stage.setTitle("Error");
            AnchorPane anchorPane = new AnchorPane();

            Text text = new Text();
            text.setText("Выберите видео, которое хотите посмотреть!");
            text.setLayoutX(20.0);
            text.setLayoutY(20.0);

            Button button = new Button();
            button.setText("Ок");
            button.setPrefWidth(50.0);
            button.setLayoutX(150.0);
            button.setLayoutY(70.0);

            anchorPane.getChildren().addAll(text, button);
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    stage.close();
                }
            });

            stage.setScene(new Scene(anchorPane,350,110));
            stage.show();
        }
        else {
            try {
                playVideo.playVideo(fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
