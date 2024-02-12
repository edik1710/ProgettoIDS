package it.unicam.cs.ids.localplatform.util;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;
import it.unicam.cs.ids.localplatform.model.PlatformManager;
import it.unicam.cs.ids.localplatform.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * The Controller class is responsible for managing the interactions between the user and the application.
 * This class may include methods to handle user requests, update the application model,
 * and update the view based on changes in the model.
 *
 * @author EliaToma, EdoardoDottori
 * @version 1.0
 */
public class Controller {

    @FXML
    private TextField name;
    @FXML
    private TextField Cognome;
    @FXML
    private TextField Cf;
    @FXML
    private TextField email;
    @FXML
    private TextField Residence;
    @FXML
    private PasswordField psw;

    private MunicipalTerritory municipalTerritory;
    private final PlatformManager platformManager;
    private Scanner scanner = new Scanner(System.in);
    private User currentUser;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @FXML
    private Label RolesLabel;

    @FXML
    private ComboBox<String> Roles;

    // Constructor called by load() method from FXMLLoader

    /**
     * The default constructor.
     */
    public Controller() {
        this.municipalTerritory = new MunicipalTerritory("Camerino");
        this.platformManager = new PlatformManager("Elia", "Toma", "elia.toma@progetto.ids", "brodoCovid", this.municipalTerritory, "cf");
    }

    /**
     * This method initializes the controller.
     */
    public void initialize() {
        Residence.textProperty().addListener((observable, oldValue, newValue) -> {
            if ("Camerino".equals(newValue)) {
                RolesLabel.setVisible(true);
                Roles.setVisible(true);
            } else {
                RolesLabel.setVisible(false);
                Roles.setVisible(false);
            }
        });
    }

    @FXML
    private void Register(ActionEvent event) throws IOException {
        String nameValue = name.getText();
        String surnameValue = Cognome.getText();
        String fiscalCodeValue = Cf.getText();
        String emailValue = email.getText();
        String residenceValue = Residence.getText();
        String passwordValue = psw.getText();

        if (residenceValue.equals("Camerino")) {
            String roleValue = Roles.getValue();
            switch (roleValue) {
                case "Animator":
                    platformManager.addPendingUser(nameValue, surnameValue, emailValue, passwordValue, residenceValue, fiscalCodeValue, "ANIMATOR");
                    break;
                case "Curator":
                    platformManager.addPendingUser(nameValue, surnameValue, emailValue, passwordValue, residenceValue, fiscalCodeValue, "CURATOR");
                    break;
                case "Platform Manager":
                    platformManager.addPendingUser(nameValue, surnameValue, emailValue, passwordValue, residenceValue, fiscalCodeValue, "PLATFORM_MANAGER");
                    break;
                case "Contributor":
                    platformManager.addPendingUser(nameValue, surnameValue, emailValue, passwordValue, residenceValue, fiscalCodeValue, "CONTRIBUTOR");
                    break;
                case "Tourist":
                    platformManager.addPendingUser(nameValue, surnameValue, emailValue, passwordValue, residenceValue, fiscalCodeValue, "TOURIST");
                    break;
            }
        } else {
            platformManager.addPendingUser(nameValue, surnameValue, emailValue, passwordValue, residenceValue, fiscalCodeValue, "TOURIST");
        }
    }

    @FXML
    private void LoginView(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml")); // path to your Login.fxml
        Scene scene = new Scene(root, 800, 600);

        stage.setScene(scene);
        stage.show();
    }
}