package it.unicam.cs.ids.localplatform.command;

import it.unicam.cs.ids.localplatform.model.Coordinates;
import it.unicam.cs.ids.localplatform.model.User;

import java.util.Date;

public class CreatePOICommand implements Command {
    private String title;
    private Date publicationDate;
    private User author;
    private Coordinates coordinates;

    public CreatePOICommand(String title, Date publicationDate, User author, Coordinates coordinates) {
        this.title = title;
        this.publicationDate = publicationDate;
        this.author = author;
        this.coordinates = coordinates;
    }

    @Override
    public void execute() {

    }
}
