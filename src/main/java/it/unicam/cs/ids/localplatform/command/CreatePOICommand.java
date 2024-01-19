package it.unicam.cs.ids.localplatform.command;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;
import it.unicam.cs.ids.localplatform.model.Coordinates;
import it.unicam.cs.ids.localplatform.model.User;

import java.util.Date;

/**
 * This class implements the command pattern and is used to create a POI.
 */
public class CreatePOICommand implements Command {
    private String title;
    private Date publicationDate;
    private User author;
    private Coordinates coordinates;
    private MunicipalTerritory municipality;

    public CreatePOICommand(String title, Date publicationDate, User author, Coordinates coordinates, MunicipalTerritory municipality) {
        this.title = title;
        this.publicationDate = publicationDate;
        this.author = author;
        this.coordinates = coordinates;
        this.municipality = municipality;
    }

    @Override
    public void execute() {
        this.municipality.addPOI(this.title, this.publicationDate, this.author, this.coordinates);
    }
}
