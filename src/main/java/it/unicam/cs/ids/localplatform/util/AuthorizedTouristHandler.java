package it.unicam.cs.ids.localplatform.util;

import it.unicam.cs.ids.localplatform.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents a handler for an authorized tourist.
 */
public class AuthorizedTouristHandler {

    @FXML
    public ListView<String> GeneralContentsList;
    @FXML
    public ListView<String> POIlist;
    @FXML
    public ListView<String> ItinerariesList;

    private final AuthorizedTourist authorizedTourist;
    @FXML
    public ListView<String> saveList;
    @FXML
    public TextField saveInfo;

    public AuthorizedTouristHandler() {
        this.authorizedTourist = (AuthorizedTourist) LoginController.getCurrentUser();
    }


    @FXML
    public void getGeneralContents(ActionEvent actionEvent) {
        GeneralContentsList.getItems().clear();
        List<String> contents = this.authorizedTourist.getResidence().getGeneralContents().stream()
                .map(Content::toString)
                .collect(Collectors.toList());
        GeneralContentsList.getItems().addAll(contents);
    }

    @FXML
    public void getPOIs(ActionEvent actionEvent) {
        POIlist.getItems().clear();
        List<String> pois = this.authorizedTourist.getResidence().getPOIs().values().stream()
                .map(POI::toString)
                .collect(Collectors.toList());
        POIlist.getItems().addAll(pois);
    }

    @FXML
    public void getItineraries(ActionEvent actionEvent) {
        ItinerariesList.getItems().clear();
        List<String> itineraries = this.authorizedTourist.getResidence().getItineraries().stream()
                .map(Itinerary::toString)
                .collect(Collectors.toList());
        ItinerariesList.getItems().addAll(itineraries);
    }


    /**
     * This method allows the authorized tourist to save an information for future visits.
     */
    public void saveInfo(ActionEvent actionEvent) {
        Info info = this.authorizedTourist.getResidence().getGeneralContents().stream()
                .filter(content -> content.getText().equals(saveInfo.getText()))
                .findFirst()
                .orElse(null);
        if (info == null) {
            info = this.authorizedTourist.getResidence().getPOIs().values().stream()
                    .filter(poi -> poi.getTitle().equals(saveInfo.getText()))
                    .findFirst()
                    .orElse(null);
        }
        if (info == null) {
            info = this.authorizedTourist.getResidence().getItineraries().stream()
                    .filter(itinerary -> itinerary.getTitle().equals(saveInfo.getText()))
                    .findFirst()
                    .orElse(null);
        }
        if (info != null) {
            this.authorizedTourist.saveInfo(info);
        }
        infoList(actionEvent);
    }

    public void infoList(ActionEvent actionEvent) {
        saveList.getItems().clear();
        saveList.getItems().addAll(authorizedTourist.getSavedInfo().stream()
                .map(Object::toString)
                .toList());
    }
}
