package it.unicam.cs.ids.localplatform;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class MainLocal extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        displayStartMessage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/interface.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("SE APRI QUESTO PROGETTO MORIARI ENTRO 7 SECONDI!");
        stage.setScene(scene);
        stage.show();
    }

    private void displayStartMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("DIOCANE");
        alert.setHeaderText(null);
        alert.setContentText("MUORIPORCODIO");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}
