package it.unicam.cs.ids.localplatform.util;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;
import it.unicam.cs.ids.localplatform.model.*;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private static PlatformManager platformManager = null;

    public static PlatformManager getPlatformManager() {
        return platformManager;
    }

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

        // per testing
        User u = new User("diosignore", "cagnaccio", "email", "password", new MunicipalTerritory("Camerino"), "cf");
        Coordinates c1 = new Coordinates(1, 1);
        Coordinates c2 = new Coordinates(2, 2);
        POI poi1 = new POI("POI1", new Date(), u, c1, "descrizione");
        POI poi2 = new POI("POI2", new Date(), u, c2, "descrizione");
        List<POI> pois = new ArrayList<>();
        pois.add(poi1);
        pois.add(poi2);
        this.municipalTerritory.addPOI(poi1);
        this.municipalTerritory.addPOI(poi2);
        Itinerary i = new Itinerary("Itinerario1", new Date(), u, pois, "descrizione");
        this.municipalTerritory.addItinerary(i);
        Date startDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24); // 1 day from now
        Date endDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 48); // 2 days from now
        Contest c = new Contest("Contest1", "Titolo", startDate, endDate);
        this.municipalTerritory.addContest(c);
        this.municipalTerritory.addGeneralContent(new Content(new Date(), u, "descrizione"));
        // per testing

        platformManager = new PlatformManager("Elia", "Toma", "elia.toma@progetto.ids", "brodoCovid", this.municipalTerritory, "cf");
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
                    User u1 = new User(nameValue, surnameValue, emailValue, passwordValue, new MunicipalTerritory(residenceValue), fiscalCodeValue);
                    platformManager.addPendingUser(u1, "ANIMATOR");
                    break;
                case "Curator":
                    User u2 = new User(nameValue, surnameValue, emailValue, passwordValue, new MunicipalTerritory(residenceValue), fiscalCodeValue);
                    platformManager.addPendingUser(u2, "CURATOR");
                    break;
                case "Platform Manager":
                    User u3 = new User(nameValue, surnameValue, emailValue, passwordValue, new MunicipalTerritory(residenceValue), fiscalCodeValue);
                    platformManager.addPendingUser(u3, "PLATFORM_MANAGER");
                    break;
                case "Contributor":
                    User u4 = new User(nameValue, surnameValue, emailValue, passwordValue, new MunicipalTerritory(residenceValue), fiscalCodeValue);
                    platformManager.addPendingUser(u4, "CONTRIBUTOR");
                    break;
                case "Tourist":
                    User u5 = new User(nameValue, surnameValue, emailValue, passwordValue, new MunicipalTerritory(residenceValue), fiscalCodeValue);
                    platformManager.addPendingUser(u5, "TOURIST");
                    break;
            }
        } else {
            User u = new User(nameValue, surnameValue, emailValue, passwordValue, new MunicipalTerritory(residenceValue), fiscalCodeValue);
            platformManager.addPendingUser(u, "TOURIST");
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