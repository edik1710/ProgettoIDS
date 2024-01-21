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

    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        POI that = (POI) o;
        return this.coordinates.equals(that.coordinates);
    }

    /**
     * This method allows to add a content to the POI.
     *
     * @param content The content to be added.
     */
    public void addContent(Content content) {
        // Check if the content is already associated with the POI
        if (this.contents.contains(content))
            throw new IllegalArgumentException("The content is already associated with the POI.");

        this.contents.add(content);
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
