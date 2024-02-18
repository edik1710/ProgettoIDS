package it.unicam.cs.ids.localplatform;

import it.unicam.cs.ids.localplatform.util.Controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainLocal extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/interface.fxml"));

            Parent root = fxmlLoader.load();

            Controller controller = fxmlLoader.getController();
            controller.initialize();

            Scene scene = new Scene(root, 800, 600);
            stage.setTitle("CameCity");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Errore durante il caricamento dell'interfaccia: " + e.getMessage());
            Platform.exit();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}