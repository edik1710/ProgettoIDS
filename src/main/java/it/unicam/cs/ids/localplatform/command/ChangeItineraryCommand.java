package it.unicam.cs.ids.localplatform.command;

import it.unicam.cs.ids.localplatform.model.Itinerary;

/**
 * This class implements the command pattern and is used to change an itinerary.
 */
public class ChangeItineraryCommand implements Command {
    private final Itinerary itinerary;
    private final String title;

    public ChangeItineraryCommand(Itinerary itinerary, String title) {
        if (itinerary == null || title == null) throw new NullPointerException("Null parameters are not allowed.");

        this.itinerary = itinerary;
        this.title = title;
    }

    @Override
    public void execute() {
        this.itinerary.setTitle(this.title);
    }

    @Override
    public String toString() {
        return "ChangeItineraryCommand{" +
                "vecchio titolo=" + itinerary.getTitle() +
                ", nuovo titolo=" + title +
                '}';
    }
}
