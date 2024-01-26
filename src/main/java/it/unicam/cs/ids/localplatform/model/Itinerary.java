package it.unicam.cs.ids.localplatform.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * This class represents an Itinerary.
 */
public class Itinerary implements Info {
    private String title;
    private Date publicationDate;
    private User author;
    private List<POI> POIs;
    private String description;
    // private List<Photo> photos

    public Itinerary(String title, Date publicationDate, User author, List<POI> POIs, String description) {
        if (POIs.size() < 2) throw new IllegalArgumentException("An itinerary must have at least two POIs.");

        this.title = title;
        this.publicationDate = publicationDate;
        this.author = author;
        this.POIs = POIs;
        this.description = description;
        // this.photos = new ArrayList<>();
    }

    /*
    public void addPhoto(Photo photo) {
        this.photos.add(photo);
    }
     */

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Itinerary that = (Itinerary) o;
        return this.POIs.equals(that.POIs);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Itinerario creato il " + sdf.format(publicationDate) + " da " + author.getName() + " " + author.getSurname() + ".\nTitolo: " + title + "\nDescrizione: " + description + "\nNumero di punti di interesse: " + POIs.size() + "\n";
    }
}
