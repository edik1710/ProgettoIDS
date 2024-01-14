package it.unicam.cs.ids.localplatform.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InfoData {
    private Date publicationDate;

    /*
    Il tipo è da definire
     */
    private User author;
    private List<Date> changeHistory;
    private boolean pending;
    private boolean awaitingVerification;

    public InfoData(Date publicationDate, User author, boolean pending) {
        this.publicationDate = publicationDate;
        this.author = author;
        this.changeHistory = new ArrayList<>(); // Quando viene creato un InfoData, la sua changeHistory è vuota
        this.pending = pending;
        this.awaitingVerification = false; // Quando viene creato un InfoData, non è in attesa di verifica
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public User getAuthor() {
        return author;
    }

    public List<Date> getChangeHistory() {
        return changeHistory;
    }

    public boolean isPending() {
        return pending;
    }

    public boolean isAwaitingVerification() {
        return awaitingVerification;
    }
}
