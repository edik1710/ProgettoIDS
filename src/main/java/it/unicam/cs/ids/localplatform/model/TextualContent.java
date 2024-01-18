package it.unicam.cs.ids.localplatform.model;

import java.util.Date;

public class TextualContent extends Content {
    private String text;

    public TextualContent(Date publicationDate, User author, String text) {
        super(publicationDate, author);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
