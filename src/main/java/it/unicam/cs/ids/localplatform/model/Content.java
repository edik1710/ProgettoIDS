package it.unicam.cs.ids.localplatform.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class represents a content.
 */
public class Content implements Info {
    private final Date publicationDate;
    private final User author;
    private String text;
    private int reports;

    public Content(Date publicationDate, User author, String text) {
        this.publicationDate = publicationDate;
        this.author = author;
        this.text = text;
        this.reports = 0;
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

    /**
     * This method allows to report a content.
     */
    public void reportContent() {
        this.reports++;
    }

    public int getReports() {
        return reports;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Contenuto pubblicato il " + sdf.format(publicationDate)
                + " da " + author.getName() + " " + author.getSurname()
                + ".\nTesto: " + text
                + "\nNumero di segnalazioni: " + reports + "\n";
    }
}
