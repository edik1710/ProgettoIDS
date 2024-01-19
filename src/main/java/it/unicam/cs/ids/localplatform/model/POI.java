package it.unicam.cs.ids.localplatform.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class represents a Point of Interest.
 */
public class POI implements Info {
    private String title;
    private Date publicationDate;
    private User author;
    private List<Content> contents;
    private Coordinates coordinates;

    public POI(String title, Date publicationDate, User author, Coordinates coordinates) {
        this.title = title;
        this.publicationDate = publicationDate;
        this.author = author;
        this.contents = new ArrayList<>();
        this.coordinates = coordinates;
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

    public List<Content> getContents() {
        return contents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        POI that = (POI) o;
        return this.coordinates.equals(that.coordinates);
    }
}
