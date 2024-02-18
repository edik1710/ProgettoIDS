package it.unicam.cs.ids.localplatform.util;

import it.unicam.cs.ids.localplatform.model.Contributor;
import it.unicam.cs.ids.localplatform.model.PlatformManager;
import it.unicam.cs.ids.localplatform.model.Tourist;
import it.unicam.cs.ids.localplatform.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;

/**
 * This class represents a platform manager handler.
 */
public class PlatformManagerHandler {

    @FXML
    private ListView<String> PendingUserList;
    @FXML
    private ListView<String> ContributorsAndTouristsList;
    private final PlatformManager platformManager;
    @FXML
    private TextField getUserList;

    public PlatformManagerHandler() {
        this.platformManager = Controller.getPlatformManager();
    }

    /**
     * Mostra la lista degli utenti in attesa.
     */
    @FXML
    public void PendingUserList() {
        PendingUserList.getItems().clear();
        // Convert the set of waiting users into a list of strings.
        List<String> pendingUsers = this.platformManager.getPendingUsers().keySet().stream()
                .map(User::toString)
                .toList();

        // Add all the users on hold to the PendingUserList.
        PendingUserList.getItems().addAll(pendingUsers);
    }

    /**
     * Accetta gli utenti in attesa.
     */
    @FXML
    public void AcceptUsers() {
        this.platformManager.assignRoles();
        this.platformManager.getPendingUsers().clear();

        PendingUserList();
    }

    /**
     * Rifiuta gli utenti in attesa.
     */
    @FXML
    public void RejectUsers() {
        this.platformManager.getPendingUsers().clear();
    }

    /**
     * Mostra la lista degli utenti che sono Contributor o Tourist.
     */
    @FXML
    public void ContributorsAndTouristsList() {
        ContributorsAndTouristsList.getItems().clear();

        // Filter the users who are Contributors or Tourists and convert them into a list of strings.
        List<String> contributorsAndTourists = this.platformManager.getResidence().getUsers().stream()
                .filter(u -> u instanceof Contributor || u instanceof Tourist)
                .map(User::toString)
                .toList();

        // Add all the users who are Contributors or Tourists to the ContributorsAndTouristsList.
        ContributorsAndTouristsList.getItems().addAll(contributorsAndTourists);
    }

    /**
     * Autorizza gli utenti.
     */
    @FXML
    public void authorize() {
        String email = getUserList.getText();
        User user = this.platformManager.getResidence().getUsers().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);

        if (user instanceof Tourist) {
            System.out.println("Autorizzo il turista");

            this.platformManager.authorizeTourist((Tourist) user);
        }
        if (user instanceof Contributor) {
            System.out.println("Autorizzo il contributor");

            this.platformManager.authorizeContributor((Contributor) user);
        }

        ContributorsAndTouristsList();
    }
}