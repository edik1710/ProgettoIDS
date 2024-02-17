package it.unicam.cs.ids.localplatform.command;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;
import it.unicam.cs.ids.localplatform.model.Itinerary;

/**
 * This class implements the command pattern and it is used to delete an itinerary.
 */
public class DeleteItineraryCommand implements Command {
    private final MunicipalTerritory municipality;
    private final Itinerary itinerary;

    public DeleteItineraryCommand(MunicipalTerritory municipality, Itinerary itinerary) {
        if (municipality == null || itinerary == null)
            throw new NullPointerException("Null parameters are not allowed.");

        this.municipality = municipality;
        this.itinerary = itinerary;
    }

    @Override
    public void execute() {
        this.municipality.deleteItinerary(this.itinerary);
    }

    @Override
    public String toString() {
        return "DeleteItineraryCommand{" +
                itinerary +
                '}';
    }
}
