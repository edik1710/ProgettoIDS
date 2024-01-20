package it.unicam.cs.ids.localplatform.command;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;
import it.unicam.cs.ids.localplatform.model.POI;
import it.unicam.cs.ids.localplatform.model.User;

import java.util.Date;
import java.util.List;

/**
 * This class implements the command pattern and is used to create an itinerary.
 */
public class CreateItineraryCommand implements Command {
    private String title;
    private Date publicationDate;
    private User author;
    private List<POI> POIs;
    private MunicipalTerritory municipality;

    public CreateItineraryCommand(String title, Date publicationDate, User author, List<POI> POIs, MunicipalTerritory municipality) {
        if (title == null || publicationDate == null || author == null || POIs == null || municipality == null)
            throw new NullPointerException("Null parameters are not allowed.");

        this.title = title;
        this.publicationDate = publicationDate;
        this.author = author;
        this.POIs = POIs;
        this.municipality = municipality;
    }

    @Override
    public void execute() {
        this.municipality.addItinerary(this.title, this.publicationDate, this.author, this.POIs);
    }
}
