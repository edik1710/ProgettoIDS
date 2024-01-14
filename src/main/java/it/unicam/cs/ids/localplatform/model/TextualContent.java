package it.unicam.cs.ids.localplatform.model;

public class TextualContent extends Content {
    private String text;

    public TextualContent(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
