package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.io.FileUtils.write;

public class AddController {

    @FXML private Button addButton;
    @FXML private TextField textField;
    String url;

    @FXML
    public void initialize() {
        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (textField.getText() != null) {
                    url = textField.getText();
                    //onClickDownloadButton();
                    textField.clear();
                    DownloadLaterController downloadLaterController = new DownloadLaterController();
                    downloadLaterController.setUrl(url);
                    File file = new File("D:\\University\\проект\\скачать позже\\скачать.txt\\");
                    /*try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
                    try {
                        update(file, url);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    public static void write(File file, ObservableList<String> texts) throws FileNotFoundException {

            PrintWriter writer = new PrintWriter(file.getAbsoluteFile());

            for (String text : texts) {
                writer.write(text + "\n");
            }
            writer.close();

    }

    public static void update(File file, String newText) throws FileNotFoundException {
        List results = new ArrayList<String>(DownloadLaterController.read());
        System.out.println(results);
        results.add(newText);
        ObservableList result = FXCollections.observableArrayList(results);
        write(file, result);
    }
}
