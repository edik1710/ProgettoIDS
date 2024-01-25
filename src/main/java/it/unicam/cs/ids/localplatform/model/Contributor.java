package it.unicam.cs.ids.localplatform.model;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;
import it.unicam.cs.ids.localplatform.command.*;
import it.unicam.cs.ids.localplatform.singleton.CommandVerificationQueue;

import java.util.Date;
import java.util.List;

/**
 * This class represents a contributor of the platform.<br><br>
 * A Contributor has the ability to define points of interest, and itineraries, and to associate descriptive or multimedia content
 * with such points. The uploaded contents are placed in "pending" status until the platform {@link Curator} accepts them.
 */
public class Contributor extends User {
    private int submittedInfo;

    public Contributor(String name, String surname, String email, String password, MunicipalTerritory residence, String cf) {
        super(name, surname, email, password, residence, cf);
        this.submittedInfo = 0;
    }

    /**
     * This method allows a contributor to submit a new point of interest to the platform.
     *
     * @param title       The title of the point of interest.
     * @param coordinates The coordinates of the point of interest.
     */
    public void submitPOI(String title, Coordinates coordinates, String description) {
        POI poi = new POI(title, new Date(), this, coordinates, description);

        this.submittedInfo++;
        CommandVerificationQueue.getInstance().getToBeVerified().add(new CreatePOICommand(poi, this.getResidence()));
    }

    /**
     * This method allows a contributor to submit a new general content to the platform.
     *
     * @param content The content to be submitted.
     */
    public void submitGeneralContent(Content content) {
        this.submittedInfo++;
        CommandVerificationQueue.getInstance().getToBeVerified().add(new CreateGeneralContentCommand(content, this.getResidence()));
    }

    /**
     * This method allows a contributor to submit a new itinerary to the platform.
     *
     * @param title The title of the itinerary.
     * @param POIs  The list of points of interest that make up the itinerary.
     */
    public void submitItinerary(String title, List<POI> POIs, String description) {
        Itinerary itinerary = new Itinerary(title, new Date(), this, POIs, description);

        this.submittedInfo++;
        CommandVerificationQueue.getInstance().getToBeVerified().add(new CreateItineraryCommand(itinerary, this.getResidence()));
    }

    /**
     * This method allows a contributor to submit a new content to a point of interest.
     *
     * @param poi     The point of interest to which the content is to be associated.
     * @param content The content to be submitted.
     */
    public void submitPOIContent(POI poi, Content content) {
        CommandVerificationQueue.getInstance().getToBeVerified().add(new CreatePOIContentCommand(poi, content));
    }

    /**
     * This method allows a contributor to submit a change to a point of interest.
     *
     * @param poi   The point of interest to be changed.
     * @param title The new title of the point of interest.
     */
    public void submitChangesToPOI(POI poi, String title) {
        CommandVerificationQueue.getInstance().getToBeVerified().add(new ChangePOICommand(poi, title));
    }

    /**
     * This method allows a contributor to submit a change to a content.
     *
     * @param content The content to be changed.
     * @param text    The new text of the content.
     */
    public void submitChangesToExistingContent(Content content, String text) {
        CommandVerificationQueue.getInstance().getToBeVerified().add(new ChangeContentCommand(content, text));
    }

    /**
     * This method allows a contributor to submit a change to an itinerary.
     *
     * @param itinerary The itinerary to be changed.
     * @param title     The new title of the itinerary.
     */
    public void submitChangesToItinerary(Itinerary itinerary, String title) {
        CommandVerificationQueue.getInstance().getToBeVerified().add(new ChangeItineraryCommand(itinerary, title));
    }

    /**
     * This method allows a contributor to submit a deletion request for a point of interest.
     *
     * @param poi The point of interest to be deleted.
     */
    public void submitPOIDeletion(POI poi) {
        CommandVerificationQueue.getInstance().getToBeVerified().add(new DeletePOICommand(this.getResidence(), poi.getCoordinates()));
    }

    /**
     * This method allows a contributor to submit a deletion request for a content.
     *
     * @param content The content to be deleted.
     */
    public void submitGeneralContentDeletion(Content content) {
        CommandVerificationQueue.getInstance().getToBeVerified().add(new DeleteGeneralContentCommand(this.getResidence(), content));
    }

    /**
     * This method submits a deletion request for a content associated with a point of interest.
     *
     * @param poi     The point of interest to which the content is associated.
     * @param content The content to be deleted.
     */
    public void submitPOIContentDeletion(POI poi, Content content) {
        CommandVerificationQueue.getInstance().getToBeVerified().add(new DeletePOIContentCommand(poi, content));
    }

    /**
     * This method allows a contributor to submit a deletion request for an itinerary.
     *
     * @param itinerary The itinerary to be deleted.
     */
    public void submitItineraryDeletion(Itinerary itinerary) {
        CommandVerificationQueue.getInstance().getToBeVerified().add(new DeleteItineraryCommand(this.getResidence(), itinerary));
    }

    public int getSubmittedInfo() {
        return submittedInfo;
    }
}
