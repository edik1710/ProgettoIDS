package it.unicam.cs.ids.localplatform.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class represents a Point of Interest.
 */
public class POI implements Info {
    private String title;
    private  Date publicationDate;
    private  User author;
    private  List<Content> contents;
    private  Coordinates coordinates;

    public POI() {}

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private  String description;

    public POI(String title, Date publicationDate, User author, Coordinates coordinates, String description) {
        this.title = title;
        this.publicationDate = publicationDate;
        this.author = author;
        this.contents = new ArrayList<>();
        this.coordinates = coordinates;
        this.description = description;
    }

    public String getTitle() {
        return title;
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


    /**
     * This method allows to remove a content from the POI.
     *
     * @param content The content to be removed.
     */
    public void deleteContent(Content content) {
        if (!this.contents.contains(content)) throw new IllegalArgumentException("The content does not exist.");

        this.contents.remove(content);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Punto di Interesse pubblicato il " + sdf.format(publicationDate) + " da " + author.getName() + " " + author.getSurname() + ".\nTitolo: " + title + "\nCoordinate: " + coordinates + "\nDescrizione: " + description + "\nNumero di contenuti: " + contents.size() + "\n";
    }
}
