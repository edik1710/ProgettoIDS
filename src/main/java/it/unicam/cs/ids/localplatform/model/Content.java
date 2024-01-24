package it.unicam.cs.ids.localplatform.model;

import java.util.Date;

public class Content implements Info {
    private Date publicationDate;
    private User author;
    private String text;
    // private Photo photo;  ->   da implementare con springBoot

    public Content(Date publicationDate, User author, String text) {
        this.publicationDate = publicationDate;
        this.author = author;
        this.text = text;
        // this.photo = photo;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public User getAuthor() {
        return author;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Content that = (Content) o;
        return this.text.equals(that.text);
    }
}
