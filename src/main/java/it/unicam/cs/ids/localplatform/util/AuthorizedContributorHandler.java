package it.unicam.cs.ids.localplatform.util;

import it.unicam.cs.ids.localplatform.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class represents an authorized contributor handler.
 */
public class AuthorizedContributorHandler {
    private final AuthorizedContributor authorizedContributor;
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

    public AuthorizedContributorHandler() {
        this.authorizedContributor = (AuthorizedContributor) LoginController.getCurrentUser();
    }

    /**
     * This method gets the general contents of the residence.
     */
    @FXML
    public void getGeneralContents() {
        GeneralContentsList.getItems().clear();
        List<String> contents = this.authorizedContributor.getResidence().getGeneralContents().stream()
                .map(Content::toString)
                .toList();
        GeneralContentsList.getItems().addAll(contents);
    }

    /**
     * This method gets the POIs of the residence.
     */
    @FXML
    public void getPOIs() {
        POIList.getItems().clear();
        List<String> pois = this.authorizedContributor.getResidence().getPOIs().values().stream()
                .map(POI::toString)
                .toList();
        POIList.getItems().addAll(pois);
    }

    /**
     * This method gets the itineraries of the residence.
     */
    @FXML
    public void getItineraries() {
        ItinerariesList.getItems().clear();
        List<String> itineraries = this.authorizedContributor.getResidence().getItineraries().stream()
                .map(Itinerary::toString)
                .toList();
        ItinerariesList.getItems().addAll(itineraries);
    }

    /**
     * This method creates a POI.
     */
    @FXML
    public void createPOI() {
        this.authorizedContributor.publishPOI(title.getText(), new Coordinates(Double.parseDouble(POILatitude.getText()), Double.parseDouble(POILongitude.getText())), description.getText());
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
        this.authorizedContributor.publishItinerary(ItineraryTitle.getText(), tempPOIList, ItineraryDescription.getText());
    }

    /**
     * This method creates a general content.
     */
    @FXML
    public void createGeneralContent() {
        this.authorizedContributor.publishGeneralContent(new Content(new Date(), this.authorizedContributor, GeneralContentText.getText()));
    }

    /**
     * This method updates a POI.
     */
    @FXML
    public void updatePOI() {
        Coordinates coordinates = new Coordinates(Double.parseDouble(UpdateLatitude.getText()), Double.parseDouble(UpdateLongitude.getText()));
        POI poi = findPOIByCoordinates(coordinates);
        if (poi != null) {
            this.authorizedContributor.publishChangesToPOI(poi, NewPOITitle.getText());
        }
    }

    /**
     * This method updates an itinerary.
     */
    @FXML
    public void updateItinerary() {
        Itinerary itinerary = findItineraryByTitle(CurrentItineraryTitle.getText());
        if (itinerary != null) {
            this.authorizedContributor.publishChangesToItinerary(itinerary, NewItineraryTitle.getText());
        }
    }

    /**
     * This method updates a general content.
     */
    @FXML
    public void updateGeneralContent() {
        Content content = findContentByText(CurrentText.getText());
        if (content != null) {
            this.authorizedContributor.publishChangesToExistingContent(content, NewText.getText());
        }
    }

    /**
     * This method deletes a POI.
     */
    @FXML
    public void deletePOI() {
        Coordinates coordinates = new Coordinates(Double.parseDouble(ToDeleteLatitude.getText()), Double.parseDouble(ToDeleteLongitude.getText()));
        POI poi = findPOIByCoordinates(coordinates);
        if (poi != null) {
            this.authorizedContributor.deletePOI(poi);
        }
    }

    /**
     * This method deletes an itinerary.
     */
    @FXML
    public void deleteItinerary() {
        Itinerary itinerary = findItineraryByTitle(ToDeleteItineraryTitle.getText());
        if (itinerary != null) {
            this.authorizedContributor.deleteItinerary(itinerary);
        }
    }

    /**
     * This method deletes a general content.
     */
    @FXML
    public void deleteGeneralContent() {
        Content content = findContentByText(ToDeleteGeneralContentText.getText());
        if (content != null) {
            this.authorizedContributor.deleteGeneralContent(content);
        }
    }

    private POI findPOIByCoordinates(Coordinates coordinates) {
        return this.authorizedContributor.getResidence().getPOIs().values().stream()
                .filter(p -> p.getCoordinates().equals(coordinates))
                .findFirst()
                .orElse(null);
    }

    private Itinerary findItineraryByTitle(String title) {
        return this.authorizedContributor.getResidence().getItineraries().stream()
                .filter(i -> i.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }

    private Content findContentByText(String text) {
        return this.authorizedContributor.getResidence().getGeneralContents().stream()
                .filter(c -> c.getText().equals(text))
                .findFirst()
                .orElse(null);
    }
}