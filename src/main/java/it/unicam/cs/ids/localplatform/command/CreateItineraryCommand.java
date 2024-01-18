package it.unicam.cs.ids.localplatform.command;

import it.unicam.cs.ids.localplatform.model.POI;
import it.unicam.cs.ids.localplatform.model.User;

import java.util.Date;
import java.util.List;

public class CreateItineraryCommand implements Command {
    private String title;
    private Date publicationDate;
    private User author;
    private List<POI> POIs;

    public CreateItineraryCommand(String title, Date publicationDate, User author, List<POI> POIs) {
        this.title = title;
        this.publicationDate = publicationDate;
        this.author = author;
        this.POIs = POIs;
    }

    @Override
    public void execute() {

    }
}
