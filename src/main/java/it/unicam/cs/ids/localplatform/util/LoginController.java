package it.unicam.cs.ids.localplatform.util;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;
import it.unicam.cs.ids.localplatform.model.PlatformManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    private final PlatformManager platformManager;
    private final MunicipalTerritory municipalTerritory;

    @FXML
    private TextField email;
    @FXML
    private PasswordField psw;
    private PlatformManager currentUser;

    public LoginController() {
        this.municipalTerritory = new MunicipalTerritory("Camerino");
        this.platformManager = new PlatformManager("Elia", "Toma", "elia.toma@progetto.ids", "brodoCovid", this.municipalTerritory, "cf");
    }

    @FXML
    private void Login(ActionEvent event) throws IOException {
        String emailValue = email.getText();
        String passwordValue = psw.getText();

        if (this.platformManager.getEmail().equals(emailValue))
            if (this.platformManager.getPassword().equals(passwordValue)) {
                this.currentUser = this.platformManager;
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();

                Parent root = FXMLLoader.load(getClass().getResource("/Platform_Manager.fxml"));
                Scene scene = new Scene(root, 800, 600);

                stage.setScene(scene);
                stage.show();
            }
    }
}
