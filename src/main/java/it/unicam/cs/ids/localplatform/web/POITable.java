package it.unicam.cs.ids.localplatform.web;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class POITable {
    @Id
    private String title;
    private String publicationDate;
    private String author;
    private String contents;
    private String coordinates;
    private String description;
    private boolean pending;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public String toString() {
        return "title=" + title +
                ", publicationDate=" + publicationDate +
                ", author=" + author +
                ", contents=" + contents +
                ", coordinates=" + coordinates +
                ", description=" + description +
                ", pending=" + pending;
    }

}
