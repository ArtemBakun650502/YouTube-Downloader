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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class DownloadLaterController {

    @FXML private ListView listVideo;
    @FXML private Button addButton;
    @FXML private Button downloadButton;
    @FXML private Button deleteButton;
    @FXML private Button updateButton;

    private static String fileName;

    @FXML
    public void initialize() {
        loadListVideo();

        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                onClickAddButton();
            }
        });

        listVideo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                fileName = newValue;
            }
        });

        deleteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                try {
                    onClickDeleteButton();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                loadListVideo();
            }
        });

        updateButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                loadListVideo();
            }
        });

        downloadButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                onClickDownloadButton();
            }
        });
    }

    private void loadListVideo() {
        ObservableList<String> result;
        List results;

        try {
            results = read();
            result = FXCollections.observableArrayList(results);
            listVideo.setItems(result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void onClickAddButton() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Add.fxml"));
            stage.setScene(new Scene(root, 500,500));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onClickDeleteButton() throws IOException {
        List results;
        results = read();
        final File file = new File("D:\\\\Университет\\\\проект\\\\скачать позже\\\\temp.txt");

        PrintWriter writer = new PrintWriter(file.getAbsoluteFile());
        ObservableList<String> result = FXCollections.observableArrayList(results);

        for (String res : result) {
            if (!res.equals(fileName)) {
                System.out.println(fileName);
                writer.write(res + "\n");
            }
        }

        if(file.renameTo(new File("буду.txt"))) {
            System.out.println("Файл переименован");
        } else {
            System.out.println("Файл не переименован");
        }

        writer.close();
    }

    private void onClickDownloadButton() {

        DownloadController downloadController = new DownloadController();
        downloadController.downloadVideo(fileName);
    }


    public static List read() throws FileNotFoundException {
        //Этот спец. объект для построения строки

        ObservableList<String> result;
        List results = new ArrayList<String>();

        File file = new File("D:\\Университет\\проект\\скачать позже\\скачать.txt\\");
        try {
            //Объект для чтения файла в буфер
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                //В цикле построчно считываем файл
                String s;
                while ((s = in.readLine()) != null) {
                    results.add(s);
                }
            } finally {
                //Также не забываем закрыть файл
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        return results;
    }

}
