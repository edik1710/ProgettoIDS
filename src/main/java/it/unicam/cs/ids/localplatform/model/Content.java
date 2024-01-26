package it.unicam.cs.ids.localplatform.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Content implements Info {
    private Date publicationDate;
    private User author;
    private String text;
    private int reports;
    // private Photo photo;  ->   da implementare con springBoot

    public Content(Date publicationDate, User author, String text) {
        this.publicationDate = publicationDate;
        this.author = author;
        this.text = text;
        this.reports = 0;
        // this.photo = photo;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public User getAuthor() {
        return author;
    }

    public String getText() {
        return text;
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

    public void reportContent() {
        this.reports++;
    }

    public void resetReportedContents() {
        this.reports = 0;
    }

    public int getReports() {
        return reports;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Contenuto pubblicato il " + sdf.format(publicationDate) + " da " + author.getName() + " " + author.getSurname() + ".\nTesto: " + text + "\nNumero di segnalazioni: " + reports + "\n";
    }
}
