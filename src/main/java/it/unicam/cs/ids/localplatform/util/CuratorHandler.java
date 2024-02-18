package it.unicam.cs.ids.localplatform.util;

import it.unicam.cs.ids.localplatform.command.Command;
import it.unicam.cs.ids.localplatform.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class represents a curator handler.
 */
public class CuratorHandler {
    private final Curator curator;
    @FXML
    public ListView<String> GeneralContentsList;
    @FXML
    public ListView<String> POIList;
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

    /**
     * This method allows the curator to get the general contents of the residence.
     */
    @FXML
    public void getGeneralContents() {
        GeneralContentsList.getItems().clear();
        List<String> contents = this.curator.getResidence().getGeneralContents().stream()
                .map(Content::toString)
                .toList();
        GeneralContentsList.getItems().addAll(contents);
    }

    /**
     * This method allows the curator to get the POIs of the residence.
     */
    @FXML
    public void getPOIs() {
        POIList.getItems().clear();
        List<String> pois = this.curator.getResidence().getPOIs().values().stream()
                .map(POI::toString)
                .toList();
        POIList.getItems().addAll(pois);
    }

    /**
     * This method allows the curator to get the itineraries of the residence.
     */
    @FXML
    public void getItineraries() {
        ItinerariesList.getItems().clear();
        List<String> itineraries = this.curator.getResidence().getItineraries().stream()
                .map(Itinerary::toString)
                .toList();
        ItinerariesList.getItems().addAll(itineraries);
    }

    /**
     * This method allows the curator to create a POI.
     */
    @FXML
    public void createPOI() {
        this.curator.publishPOI(title.getText(), new Coordinates(Double.parseDouble(POILatitude.getText()), Double.parseDouble(POILongitude.getText())), description.getText());
    }

    private static final List<POI> tempPOIList = new ArrayList<>();

    /**
     * This method adds a POI to the temporary list.
     */
    @FXML
    public void addPOIToTempList() {
        Coordinates coordinates = new Coordinates(Double.parseDouble(TempLatitude.getText()), Double.parseDouble(TempLongitude.getText()));
        POI poi = findPOIByCoordinates(coordinates);
        if (poi != null) {
            tempPOIList.add(poi);
        }
    }

    /**
     * This method allows the curator to create an itinerary.
     */
    @FXML
    public void createItinerary() {
        this.curator.publishItinerary(ItineraryTitle.getText(), tempPOIList, ItineraryDescription.getText());
    }

    /**
     * This method allows the curator to create a general content.
     */
    @FXML
    public void createGeneralContent() {
        this.curator.publishGeneralContent(new Content(new Date(), this.curator, GeneralContentText.getText()));
    }

    /**
     * This method allows the curator to update a POI.
     */
    @FXML
    public void updatePOI() {
        Coordinates coordinates = new Coordinates(Double.parseDouble(UpdateLatitude.getText()), Double.parseDouble(UpdateLongitude.getText()));
        POI poi = findPOIByCoordinates(coordinates);
        if (poi != null) {
            this.curator.publishChangesToPOI(poi, NewPOITitle.getText());
        }
    }

    /**
     * This method allows the curator to update an itinerary.
     */
    @FXML
    public void updateItinerary() {
        Itinerary itinerary = findItineraryByTitle(CurrentItineraryTitle.getText());
        if (itinerary != null) {
            this.curator.publishChangesToItinerary(itinerary, NewItineraryTitle.getText());
        }
    }

    /**
     * This method allows the curator to update a general content.
     */
    @FXML
    public void updateGeneralContent() {
        Content content = findContentByText(CurrentText.getText());
        if (content != null) {
            this.curator.publishChangesToExistingContent(content, NewText.getText());
        }
    }

    /**
     * This method allows the curator to delete a POI.
     */
    @FXML
    public void deletePOI() {
        Coordinates coordinates = new Coordinates(Double.parseDouble(ToDeleteLatitude.getText()), Double.parseDouble(ToDeleteLongitude.getText()));
        POI poi = findPOIByCoordinates(coordinates);
        if (poi != null) {
            this.curator.deletePOI(poi);
        }
    }

    /**
     * This method allows the curator to delete an itinerary.
     */
    @FXML
    public void deleteItinerary() {
        Itinerary itinerary = findItineraryByTitle(ToDeleteItineraryTitle.getText());
        if (itinerary != null) {
            this.curator.deleteItinerary(itinerary);
        }
    }

    /**
     * This method allows the curator to delete a general content.
     */
    @FXML
    public void deleteGeneralContent() {
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

    /**
     * This method allows the curator to view the next command.
     */
    @FXML
    public void viewNextCommand() {
        this.curator.viewNextCommand();
        ToApprove.getItems().clear();
        ToApprove.getItems().add(Curator.getValidatingCommand());
    }

    /**
     * This method allows the curator to reject a command.
     */
    @FXML
    public void reject() {
        this.curator.rejectCommandExecution();
    }

    /**
     * This method allows the curator to approve a command.
     */
    @FXML
    public void approved() {
        this.curator.authorizeCommandExecution();
    }
}