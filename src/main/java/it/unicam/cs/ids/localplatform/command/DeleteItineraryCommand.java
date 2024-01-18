package it.unicam.cs.ids.localplatform.command;

import it.unicam.cs.ids.localplatform.model.Itinerary;

public class DeleteItineraryCommand implements Command {
    private Itinerary itinerary;

    public DeleteItineraryCommand(Itinerary itinerary) {
        this.itinerary = itinerary;
    }

    @Override
    public void execute() {

    }
}
