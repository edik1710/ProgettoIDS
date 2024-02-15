package it.unicam.cs.ids.localplatform.web;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class ItineraryTable {
    @Id
    private String title;
    private Date publicationDate;
    private String author;
    //private List<POI> POIs;
    private String description;
    private boolean pending;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    @Override
    public String toString() {
        return "ItineraryTable{" +
                "title='" + title + '\'' +
                ", publicationDate=" + publicationDate +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", pending=" + pending +
                '}';
    }
}