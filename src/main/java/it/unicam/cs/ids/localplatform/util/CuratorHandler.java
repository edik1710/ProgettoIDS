package it.unicam.cs.ids.localplatform.util;

import it.unicam.cs.ids.localplatform.command.Command;
import it.unicam.cs.ids.localplatform.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents a curator handler.
 */
public class CuratorHandler {
    private final Curator curator;
    @FXML
    public ListView<String> GeneralContentsList;
    @FXML
    public ListView<String> POIlist;
    @FXML
    public ListView<String> ItinerariesList;
    @FXML
    public TextField title;
    @FXML
    public TextField description;
    @FXML
    public TextField POILatitude;
    @FXML
    public TextField POILongitude;
    @FXML
    public TextField TempLatitude;
    @FXML
    public TextField TempLongitude;
    @FXML
    public TextField ItineraryTitle;
    @FXML
    public TextField ItineraryDescription;
    @FXML
    public TextField GeneralContentText;
    @FXML
    public TextField UpdateLatitude;
    @FXML
    public TextField UpdateLongitude;
    @FXML
    public TextField NewPOITitle;
    @FXML
    public TextField CurrentItineraryTitle;
    @FXML
    public TextField NewItineraryTitle;
    @FXML
    public TextField CurrentText;
    @FXML
    public TextField NewText;
    @FXML
    public TextField ToDeleteLatitude;
    @FXML
    public TextField ToDeleteLongitude;
    @FXML
    public TextField ToDeleteItineraryTitle;
    @FXML
    public TextField ToDeleteGeneralContentText;
    @FXML
    public ListView<Command> ToApprove;

    public CuratorHandler() {
        this.curator = (Curator) LoginController.getCurrentUser();
    }

    @FXML
    public void getGeneralContents(ActionEvent actionEvent) {
        GeneralContentsList.getItems().clear();
        List<String> contents = this.curator.getResidence().getGeneralContents().stream()
                .map(Content::toString)
                .collect(Collectors.toList());
        GeneralContentsList.getItems().addAll(contents);
    }

    @FXML
    public void getPOIs(ActionEvent actionEvent) {
        POIlist.getItems().clear();
        List<String> pois = this.curator.getResidence().getPOIs().values().stream()
                .map(POI::toString)
                .collect(Collectors.toList());
        POIlist.getItems().addAll(pois);
    }

    @FXML
    public void getItineraries(ActionEvent actionEvent) {
        ItinerariesList.getItems().clear();
        List<String> itineraries = this.curator.getResidence().getItineraries().stream()
                .map(Itinerary::toString)
                .collect(Collectors.toList());
        ItinerariesList.getItems().addAll(itineraries);
    }

    @FXML
    public void createPOI(ActionEvent actionEvent) {
        this.curator.publishPOI(title.getText(), new Coordinates(Double.parseDouble(POILatitude.getText()), Double.parseDouble(POILongitude.getText())), description.getText());
    }

    private static List<POI> tempPOIList = new ArrayList<>();

    @FXML
    public void addPOIToTempList(ActionEvent actionEvent) {
        Coordinates coordinates = new Coordinates(Double.parseDouble(TempLatitude.getText()), Double.parseDouble(TempLongitude.getText()));
        POI poi = findPOIByCoordinates(coordinates);
        if (poi != null) {
            tempPOIList.add(poi);
        }
    }

    @FXML
    public void createItinerary(ActionEvent actionEvent) {
        this.curator.publishItinerary(ItineraryTitle.getText(), tempPOIList, ItineraryDescription.getText());
    }

    @FXML
    public void createGeneralContent(ActionEvent actionEvent) {
        this.curator.publishGeneralContent(new Content(new Date(), this.curator, GeneralContentText.getText()));
    }

    @FXML
    public void updatePOI(ActionEvent actionEvent) {
        Coordinates coordinates = new Coordinates(Double.parseDouble(UpdateLatitude.getText()), Double.parseDouble(UpdateLongitude.getText()));
        POI poi = findPOIByCoordinates(coordinates);
        if (poi != null) {
            this.curator.publishChangesToPOI(poi, NewPOITitle.getText());
        }
    }

    @FXML
    public void updateItinerary(ActionEvent actionEvent) {
        Itinerary itinerary = findItineraryByTitle(CurrentItineraryTitle.getText());
        if (itinerary != null) {
            this.curator.publishChangesToItinerary(itinerary, NewItineraryTitle.getText());
        }
    }

    @FXML
    public void updateGeneralContent(ActionEvent actionEvent) {
        Content content = findContentByText(CurrentText.getText());
        if (content != null) {
            this.curator.publishChangesToExistingContent(content, NewText.getText());
        }
    }

    @FXML
    public void deletePOI(ActionEvent actionEvent) {
        Coordinates coordinates = new Coordinates(Double.parseDouble(ToDeleteLatitude.getText()), Double.parseDouble(ToDeleteLongitude.getText()));
        POI poi = findPOIByCoordinates(coordinates);
        if (poi != null) {
            this.curator.deletePOI(poi);
        }
    }

    @FXML
    public void deleteItinerary(ActionEvent actionEvent) {
        Itinerary itinerary = findItineraryByTitle(ToDeleteItineraryTitle.getText());
        if (itinerary != null) {
            this.curator.deleteItinerary(itinerary);
        }
    }

    @FXML
    public void deleteGeneralContent(ActionEvent actionEvent) {
        Content content = findContentByText(ToDeleteGeneralContentText.getText());
        if (content != null) {
            this.curator.deleteGeneralContent(content);
        }
    }

    private POI findPOIByCoordinates(Coordinates coordinates) {
        return this.curator.getResidence().getPOIs().values().stream()
                .filter(p -> p.getCoordinates().equals(coordinates))
                .findFirst()
                .orElse(null);
    }

    private Itinerary findItineraryByTitle(String title) {
        return this.curator.getResidence().getItineraries().stream()
                .filter(i -> i.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }

    private Content findContentByText(String text) {
        return this.curator.getResidence().getGeneralContents().stream()
                .filter(c -> c.getText().equals(text))
                .findFirst()
                .orElse(null);
    }

    @FXML
    public void viewNextCommand(ActionEvent actionEvent) {
        this.curator.viewNextCommand();
        ToApprove.getItems().clear();
        ToApprove.getItems().add(Curator.getValidatingCommand());
    }

    @FXML
    public void reject(ActionEvent actionEvent) {
        this.curator.rejectCommandExecution();
    }

    @FXML
    public void approved(ActionEvent actionEvent) {
        this.curator.authorizeCommandExecution();
    }
}