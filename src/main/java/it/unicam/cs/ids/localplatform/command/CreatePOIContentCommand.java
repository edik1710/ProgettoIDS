package it.unicam.cs.ids.localplatform.command;

import it.unicam.cs.ids.localplatform.model.Content;
import it.unicam.cs.ids.localplatform.model.POI;

/**
 * This class implements the command pattern and is used to create a POI content.
 */
public class CreatePOIContentCommand implements Command {
    private POI poi;
    private Content content;

    public CreatePOIContentCommand(POI poi, Content content) {
        if (poi == null || content == null) throw new NullPointerException("Null parameters are not allowed.");

        this.poi = poi;
        this.content = content;
    }

    @Override
    public void execute() {
        this.poi.addContent(content);
    }
}
