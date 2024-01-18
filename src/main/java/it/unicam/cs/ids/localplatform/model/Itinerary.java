package it.unicam.cs.ids.localplatform.model;

import java.util.Date;
import java.util.List;

public class Itinerary implements Info {
    private String title;
    private Date publicationDate;
    private User author;
    private List<POI> POIs;

    public Itinerary(String title, Date publicationDate, User author, List<POI> POIs) {
        this.title = title;
        this.publicationDate = publicationDate;
        this.author = author;
        this.POIs = POIs;
    }

    public String getTitle() {
        return title;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public User getAuthor() {
        return author;
    }

    public List<POI> getPOIs() {
        return POIs;
    }
}
