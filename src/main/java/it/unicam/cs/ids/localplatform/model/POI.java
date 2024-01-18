package it.unicam.cs.ids.localplatform.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class POI implements Info {
    private String title;
    private Date publicationDate;
    private User author;
    private List<Content> contents;

    public POI(String title, Date publicationDate, User author) {
        this.title = title;
        this.publicationDate = publicationDate;
        this.author = author;
        this.contents = new ArrayList<>();
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
}
