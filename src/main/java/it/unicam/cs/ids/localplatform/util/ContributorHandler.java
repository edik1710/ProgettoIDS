package it.unicam.cs.ids.localplatform.util;

import it.unicam.cs.ids.localplatform.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class represents a contributor handler.
 */
public class ContributorHandler {
    private final Contributor contributor;
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

    public ContributorHandler() {
        this.contributor = (Contributor) LoginController.getCurrentUser();
    }

    /**
     * This method allows the contributor to get the general contents of the residence.
     */
    @FXML
    public void getGeneralContents() {
        GeneralContentsList.getItems().clear();
        List<String> contents = this.contributor.getResidence().getGeneralContents().stream()
                .map(Content::toString)
                .toList();
        GeneralContentsList.getItems().addAll(contents);
    }

    /**
     * This method allows the contributor to get the POIs of the residence.
     */
    @FXML
    public void getPOIs() {
        POIList.getItems().clear();
        List<String> pois = this.contributor.getResidence().getPOIs().values().stream()
                .map(POI::toString)
                .toList();
        POIList.getItems().addAll(pois);
    }

    /**
     * This method allows the contributor to get the itineraries of the residence.
     */
    @FXML
    public void getItineraries() {
        ItinerariesList.getItems().clear();
        List<String> itineraries = this.contributor.getResidence().getItineraries().stream()
                .map(Itinerary::toString)
                .toList();
        ItinerariesList.getItems().addAll(itineraries);
    }

    /**
     * This method allows the contributor to create a POI.
     */
    @FXML
    public void createPOI() {
        this.contributor.submitPOI(title.getText(), new Coordinates(Double.parseDouble(POILatitude.getText()), Double.parseDouble(POILongitude.getText())), description.getText());
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
     * This method creates an itinerary.
     */
    @FXML
    public void createItinerary() {
        this.contributor.submitItinerary(ItineraryTitle.getText(), tempPOIList, ItineraryDescription.getText());
    }

    /**
     * This method creates a general content.
     */
    @FXML
    public void createGeneralContent() {
        this.contributor.submitGeneralContent(new Content(new Date(), this.contributor, GeneralContentText.getText()));
    }

    /**
     * This method allows the contributor to update a POI.
     */
    @FXML
    public void updatePOI() {
        Coordinates coordinates = new Coordinates(Double.parseDouble(UpdateLatitude.getText()), Double.parseDouble(UpdateLongitude.getText()));
        POI poi = findPOIByCoordinates(coordinates);
        if (poi != null) {
            this.contributor.submitChangesToPOI(poi, NewPOITitle.getText());
        }
    }

    /**
     * This method allows the contributor to update an itinerary.
     */
    @FXML
    public void updateItinerary() {
        Itinerary itinerary = findItineraryByTitle(CurrentItineraryTitle.getText());
        if (itinerary != null) {
            this.contributor.submitChangesToItinerary(itinerary, NewItineraryTitle.getText());
        }
    }

    /**
     * This method allows the contributor to update a general content.
     */
    @FXML
    public void updateGeneralContent() {
        Content content = findContentByText(CurrentText.getText());
        if (content != null) {
            this.contributor.submitChangesToExistingContent(content, NewText.getText());
        }
    }

    /**
     * This method allows the contributor to delete a POI.
     */
    @FXML
    public void deletePOI() {
        Coordinates coordinates = new Coordinates(Double.parseDouble(ToDeleteLatitude.getText()), Double.parseDouble(ToDeleteLongitude.getText()));
        POI poi = findPOIByCoordinates(coordinates);
        if (poi != null) {
            this.contributor.submitPOIDeletion(poi);
        }
    }

    /**
     * This method allows the contributor to delete an itinerary.
     */
    @FXML
    public void deleteItinerary() {
        Itinerary itinerary = findItineraryByTitle(ToDeleteItineraryTitle.getText());
        if (itinerary != null) {
            this.contributor.submitItineraryDeletion(itinerary);
        }
    }

    /**
     * This method allows the contributor to delete a general content.
     */
    @FXML
    public void deleteGeneralContent() {
        Content content = findContentByText(ToDeleteGeneralContentText.getText());
        if (content != null) {
            this.contributor.submitGeneralContentDeletion(content);
        }
    }

    private POI findPOIByCoordinates(Coordinates coordinates) {
        return this.contributor.getResidence().getPOIs().values().stream()
                .filter(p -> p.getCoordinates().equals(coordinates))
                .findFirst()
                .orElse(null);
    }

    private Itinerary findItineraryByTitle(String title) {
        return this.contributor.getResidence().getItineraries().stream()
                .filter(i -> i.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }

    private Content findContentByText(String text) {
        return this.contributor.getResidence().getGeneralContents().stream()
                .filter(c -> c.getText().equals(text))
                .findFirst()
                .orElse(null);
    }
}