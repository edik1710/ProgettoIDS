package it.unicam.cs.ids.localplatform.command;

import it.unicam.cs.ids.localplatform.model.Content;

/**
 * This class implements the command pattern and is used to change an existing content.
 */
public class ChangeContentCommand implements Command {
    private final Content content;
    private final String text;

    public ChangeContentCommand(Content content, String text) {
        if (content == null || text == null)
            throw new NullPointerException("Null parameters are not allowed.");

        this.content = content;
        this.text = text;
    }

    @Override
    public void execute() {
        this.content.setText(this.text);
    }
}
