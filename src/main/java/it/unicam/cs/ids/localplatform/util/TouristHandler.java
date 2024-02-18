package it.unicam.cs.ids.localplatform.util;

import it.unicam.cs.ids.localplatform.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;

/**
 * This class represents a tourist handler.
 */
public class TouristHandler {
    @FXML
    public ListView<String> GeneralContentsList;
    @FXML
    public ListView<String> POIList;
    @FXML
    public ListView<String> ItinerariesList;
    @FXML
    public ListView<String> ContestList;
    private final Tourist tourist;
    @FXML
    public TextField Contenuto;

    public TouristHandler() {
        this.tourist = (Tourist) LoginController.getCurrentUser();
    }

    /**
     * This method allows the tourist to get the general contents of the residence.
     */
    @FXML
    public void getGeneralContents() {
        GeneralContentsList.getItems().clear();
        List<String> contents = this.tourist.getResidence().getGeneralContents().stream()
                .map(Content::toString)
                .toList();
        GeneralContentsList.getItems().addAll(contents);
    }

    /**
     * This method allows the tourist to get the POIs of the residence.
     */
    @FXML
    public void getPOIs() {
        POIList.getItems().clear();
        List<String> pois = this.tourist.getResidence().getPOIs().values().stream()
                .map(POI::toString)
                .toList();
        POIList.getItems().addAll(pois);
    }

    /**
     * This method allows the tourist to get the itineraries of the residence.
     */
    @FXML
    public void getItineraries() {
        ItinerariesList.getItems().clear();
        List<String> itineraries = this.tourist.getResidence().getItineraries().stream()
                .map(Itinerary::toString)
                .toList();
        ItinerariesList.getItems().addAll(itineraries);
    }

    /**
     * This method allows the tourist to get the contests of the residence.
     */
    @FXML
    public void getContests() {
        ContestList.getItems().clear();
        List<String> contests = this.tourist.getResidence().getContests().stream()
                .map(Contest::toString)
                .toList();
        ContestList.getItems().addAll(contests);
    }

    /**
     * This method allows the tourist to report a content.
     */
    @FXML
    public void Report() {
        this.tourist.getResidence().getGeneralContents().stream()
                .filter(c -> c.getText().equals(Contenuto.getText()))
                .findFirst().ifPresent(this.tourist::reportContent);
    }
}