package it.unicam.cs.ids.localplatform.command;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;
import it.unicam.cs.ids.localplatform.model.Itinerary;

/**
 * This class implements the command pattern and is used to create an itinerary.
 */
public class CreateItineraryCommand implements Command {
    private final Itinerary itinerary;
    private final MunicipalTerritory municipality;

    public CreateItineraryCommand(Itinerary itinerary, MunicipalTerritory municipality) {
        if (itinerary == null || municipality == null)
            throw new NullPointerException("Null parameters are not allowed.");

        this.itinerary = itinerary;
        this.municipality = municipality;
    }

    @Override
    public void execute() {
        this.municipality.addItinerary(this.itinerary);
    }
}
