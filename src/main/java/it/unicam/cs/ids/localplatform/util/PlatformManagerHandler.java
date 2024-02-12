package it.unicam.cs.ids.localplatform.util;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;
import it.unicam.cs.ids.localplatform.model.Contributor;
import it.unicam.cs.ids.localplatform.model.PlatformManager;
import it.unicam.cs.ids.localplatform.model.Tourist;
import it.unicam.cs.ids.localplatform.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.stream.Collectors;

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

        // per testare la lista di utenti in attesa
        User u = new User("diosignore", "cagnaccio", "email", "password", new MunicipalTerritory("Camerino"), "cf");
        this.platformManager.addPendingUser(u, "CONTRIBUTOR");

    }

    public void PendingUserList(ActionEvent actionEvent) {
        PendingUserList.getItems().clear();
        // Converti l'insieme di utenti in attesa in una lista di stringhe
        List<String> pendingUsers = this.platformManager.getPendingUsers().keySet().stream()
                .map(User::toString)
                .collect(Collectors.toList());

        // Aggiungi tutti gli utenti in attesa alla lista PendingUserList
        PendingUserList.getItems().addAll(pendingUsers);
    }

    public void AcceptUsers(ActionEvent actionEvent) {
        this.platformManager.assignRoles();
        this.platformManager.getPendingUsers().clear();

        PendingUserList(actionEvent);
    }

    public void RejectUsers(ActionEvent actionEvent) {
        this.platformManager.getPendingUsers().clear();
    }

    public void ContributorsAndTouristsList(ActionEvent actionEvent) {
        ContributorsAndTouristsList.getItems().clear();

        // Filtra gli utenti che sono Contributor o Tourist e convertili in una lista di stringhe
        List<String> contributorsAndTourists = this.platformManager.getResidence().getUsers().stream()
                .filter(u -> u instanceof Contributor || u instanceof Tourist)
                .map(User::toString)
                .collect(Collectors.toList());

        // Aggiungi tutti gli utenti che sono Contributor o Tourist alla lista ContributorsAndTouristsList
        ContributorsAndTouristsList.getItems().addAll(contributorsAndTourists);
    }

    public void autorize(ActionEvent actionEvent) {
        String email = getUserList.getText();
        User user = this.platformManager.getResidence().getUsers().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);

        if (user != null && user instanceof Tourist) {
            System.out.println("Autorizzo il turista");

            this.platformManager.authorizeTourist((Tourist) user);
        }
        if (user != null && user instanceof Contributor) {
            System.out.println("Autorizzo il contributor");

            this.platformManager.authorizeContributor((Contributor) user);
        }

        ContributorsAndTouristsList(actionEvent);
    }
}