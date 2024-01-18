package it.unicam.cs.ids.localplatform.model;

import java.util.Date;

public abstract class Content implements Info {
    private Date publicationDate;
    private User author;

    public Content(Date publicationDate, User author) {
        this.publicationDate = publicationDate;
        this.author = author;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public User getAuthor() {
        return author;
    }
}
