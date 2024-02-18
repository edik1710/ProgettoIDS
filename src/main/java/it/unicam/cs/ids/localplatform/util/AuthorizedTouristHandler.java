package it.unicam.cs.ids.localplatform.util;

import it.unicam.cs.ids.localplatform.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;

/**
 * This class represents a handler for an authorized tourist.
 */
public class AuthorizedTouristHandler {

    @FXML
    public ListView<String> GeneralContentsList;
    @FXML
    public ListView<String> POIList;
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

    /**
     * This method allows the authorized tourist to get the general contents of the residence.
     */
    @FXML
    public void getGeneralContents() {
        GeneralContentsList.getItems().clear();
        List<String> contents = this.authorizedTourist.getResidence().getGeneralContents().stream()
                .map(Content::toString)
                .toList();
        GeneralContentsList.getItems().addAll(contents);
    }

    /**
     * This method allows the authorized tourist to get the POIs of the residence.
     */
    @FXML
    public void getPOIs() {
        POIList.getItems().clear();
        List<String> pois = this.authorizedTourist.getResidence().getPOIs().values().stream()
                .map(POI::toString)
                .toList();
        POIList.getItems().addAll(pois);
    }

    /**
     * This method allows the authorized tourist to get the itineraries of the residence.
     */
    @FXML
    public void getItineraries() {
        ItinerariesList.getItems().clear();
        List<String> itineraries = this.authorizedTourist.getResidence().getItineraries().stream()
                .map(Itinerary::toString)
                .toList();
        ItinerariesList.getItems().addAll(itineraries);
    }

    /**
     * This method allows the authorized tourist to save an information for future visits.
     */
    @FXML
    public void saveInfo() {
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
        infoList();
    }

    /**
     * This method allows the authorized tourist to get the saved information.
     */
    @FXML
    public void infoList() {
        saveList.getItems().clear();
        saveList.getItems().addAll(authorizedTourist.getSavedInfo().stream()
                .map(Object::toString)
                .toList());
    }
}