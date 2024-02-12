package it.unicam.cs.ids.localplatform.util;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;
import it.unicam.cs.ids.localplatform.model.PlatformManager;
import it.unicam.cs.ids.localplatform.model.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class PlatformManagerHandler {
    private final MunicipalTerritory municipalTerritory;
    @FXML
    private ListView<User> PendingUserList;

    private final PlatformManager platformManager;

    public PlatformManagerHandler() {
        this.municipalTerritory = new MunicipalTerritory("Camerino");
        this.platformManager = new PlatformManager("Elia", "Toma", "elia.toma@progetto.ids", "brodoCovid", this.municipalTerritory, "cf");
    }

    public void PendingUserList(ActionEvent actionEvent) {
        PendingUserList.setItems(FXCollections.observableArrayList(platformManager.getPendingUsers().keySet()));
    }
}