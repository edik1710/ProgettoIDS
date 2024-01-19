package it.unicam.cs.ids.localplatform.model;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;
import it.unicam.cs.ids.localplatform.command.CreateGeneralContentCommand;
import it.unicam.cs.ids.localplatform.command.CreateItineraryCommand;
import it.unicam.cs.ids.localplatform.command.CreatePOICommand;
import it.unicam.cs.ids.localplatform.singleton.CommandVerificationQueue;

import java.util.Date;
import java.util.List;

/**
 * This class represents a contributor of the platform.<br><br>
 * A Contributor has the ability to define points of interest, and itineraries, and to associate descriptive or multimedia content
 * with such points. The uploaded contents are placed in "pending" status until the platform {@link Curator} accepts them.
 */
public class Contributor extends User {

    public Contributor(String name, String surname, String email, String password, MunicipalTerritory residence, String cf) {
        super(name, surname, email, password, residence, cf);
    }

    /**
     * This method allows a contributor to submit a new point of interest to the platform.
     *
     * @param title       The title of the point of interest.
     * @param coordinates The coordinates of the point of interest.
     */
    public void submitPOI(String title, Coordinates coordinates) {
        CommandVerificationQueue.getInstance().getToBeVerified().add(new CreatePOICommand(title, new Date(), this, coordinates, this.getResidence()));
    }

    /**
     * This method allows a contributor to submit a new general content to the platform.
     *
     * @param content The content to be submitted.
     */
    public void submitGeneralContent(Content content) {
        CommandVerificationQueue.getInstance().getToBeVerified().add(new CreateGeneralContentCommand(content, this.getResidence()));
    }

    /**
     * This method allows a contributor to submit a new itinerary to the platform.
     *
     * @param title The title of the itinerary.
     * @param POIs  The list of points of interest that make up the itinerary.
     */
    public void submitItinerary(String title, List<POI> POIs) {
        CommandVerificationQueue.getInstance().getToBeVerified().add(new CreateItineraryCommand(title, new Date(), this, POIs, this.getResidence()));
    }
}
