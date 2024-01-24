package it.unicam.cs.ids.localplatform.model;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;
import it.unicam.cs.ids.localplatform.command.*;

import java.util.Date;
import java.util.List;

/**
 * This class represents an authorized contributor.<br><br>
 * Authorized Contributors are special contributors whose reliability has been verified and for whom the uploaded contents
 * are not subject to validation by the {@link Curator}.
 */
public class AuthorizedContributor extends User {

    public AuthorizedContributor(String name, String surname, String email, String password, MunicipalTerritory residence, String cf) {
        super(name, surname, email, password, residence, cf);
    }

    /**
     * This method allows an authorized contributor to publish a new point of interest to the platform.
     *
     * @param title       The title of the point of interest.
     * @param coordinates The coordinates of the point of interest.
     */
    public void publishPOI(String title, Coordinates coordinates) {
        new CreatePOICommand(title, new Date(), this, coordinates, this.getResidence()).execute();
    }

    /**
     * This method allows an authorized contributor to publish a new general content to the platform.
     *
     * @param content The content to be published.
     */
    public void publishGeneralContent(Content content) {
        new CreateGeneralContentCommand(content, this.getResidence()).execute();
    }

    /**
     * This method allows an authorized contributor to publish a new itinerary to the platform.
     *
     * @param title The title of the itinerary.
     * @param POIs  The list of points of interest that make up the itinerary.
     */
    public void publishItinerary(String title, List<POI> POIs) {
        new CreateItineraryCommand(title, new Date(), this, POIs, this.getResidence()).execute();
    }

    /**
     * This method allows an authorized contributor to publish a new content to a point of interest.
     *
     * @param poi     The point of interest to which the content is to be associated.
     * @param content The content to be published.
     */
    public void publishPOIContent(POI poi, Content content) {
        new CreatePOIContentCommand(poi, content).execute();
    }

    /**
     * This method allows an authorized contributor to publish a change to a point of interest.
     *
     * @param poi   The point of interest to be changed.
     * @param title The new title of the point of interest.
     */
    public void publishChangesToPOI(POI poi, String title) {
        new ChangePOICommand(poi, title).execute();
    }

    /**
     * This method allows an authorized contributor to publish a change to a content.
     *
     * @param content The content to be changed.
     * @param text    The new text of the content.
     */
    public void publishChangesToExistingContent(Content content, String text) {
        new ChangeContentCommand(content, text).execute();
    }

    /**
     * This method allows an authorized contributor to publish a change to an itinerary.
     *
     * @param itinerary The itinerary to be changed.
     * @param title     The new title of the itinerary.
     */
    public void publishChangesToItinerary(Itinerary itinerary, String title) {
        new ChangeItineraryCommand(itinerary, title).execute();
    }

    /**
     * This method allows an authorized contributor to delete a point of interest from the platform.
     *
     * @param poi The point of interest to be deleted.
     */
    public void deletePOI(POI poi) {
        new DeletePOICommand(this.getResidence(), poi.getCoordinates()).execute();
    }

    /**
     * This method allows an authorized contributor to delete a content from the platform.
     */
    public void deleteGeneralContent(Content content) {
        new DeleteGeneralContentCommand(this.getResidence(), content).execute();
    }

    /**
     * This method allows an authorized contributor to delete a content from a point of interest.
     *
     * @param poi     The point of interest to which the content is associated.
     * @param content The content to be deleted.
     */
    public void deletePOIContent(POI poi, Content content) {
        new DeletePOIContentCommand(poi, content).execute();
    }

    /**
     * This method allows an authorized contributor to delete an itinerary from the platform.
     *
     * @param itinerary The itinerary to be deleted.
     */
    public void deleteItinerary(Itinerary itinerary) {
        new DeleteItineraryCommand(this.getResidence(), itinerary).execute();
    }
}
