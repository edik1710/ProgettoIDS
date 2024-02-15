package it.unicam.cs.ids.localplatform.web;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class GeneralContentTable {
    private Date publicationDate;
    private String author;
    @Id
    private String text;
    private int reports;
    private boolean pending;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getReports() {
        return reports;
    }

    public void setReports(int reports) {
        this.reports = reports;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    @Override
    public String toString() {
        return "GeneralContentTable{" +
                "publicationDate=" + publicationDate +
                ", author='" + author + '\'' +
                ", text='" + text + '\'' +
                ", reports=" + reports +
                ", pending=" + pending +
                '}';
    }
}