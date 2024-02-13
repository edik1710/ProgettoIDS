package it.unicam.cs.ids.localplatform.util;

import it.unicam.cs.ids.localplatform.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents a tourist handler.
 */
public class TouristHandler {
    @FXML
    public ListView<String> GeneralContentsList;
    @FXML
    public ListView<String> POIlist;
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

    @FXML
    public void getGeneralContents(ActionEvent actionEvent) {
        GeneralContentsList.getItems().clear();
        List<String> contents = this.tourist.getResidence().getGeneralContents().stream()
                .map(Content::toString)
                .collect(Collectors.toList());
        GeneralContentsList.getItems().addAll(contents);
    }

    @FXML
    public void getPOIs(ActionEvent actionEvent) {
        POIlist.getItems().clear();
        List<String> pois = this.tourist.getResidence().getPOIs().values().stream()
                .map(POI::toString)
                .collect(Collectors.toList());
        POIlist.getItems().addAll(pois);
    }

    @FXML
    public void getItineraries(ActionEvent actionEvent) {
        ItinerariesList.getItems().clear();
        List<String> itineraries = this.tourist.getResidence().getItineraries().stream()
                .map(Itinerary::toString)
                .collect(Collectors.toList());
        ItinerariesList.getItems().addAll(itineraries);
    }

    @FXML
    public void getContests(ActionEvent actionEvent) {
        ContestList.getItems().clear();
        List<String> contests = this.tourist.getResidence().getContests().stream()
                .map(Contest::toString)
                .collect(Collectors.toList());
        ContestList.getItems().addAll(contests);
    }

    @FXML
    public void Report(ActionEvent actionEvent) {
        Content content = this.tourist.getResidence().getGeneralContents().stream()
                .filter(c -> c.getText().equals(Contenuto.getText()))
                .findFirst()
                .orElse(null);
        if (content != null) {
            this.tourist.reportContent(content);
        }
    }
}