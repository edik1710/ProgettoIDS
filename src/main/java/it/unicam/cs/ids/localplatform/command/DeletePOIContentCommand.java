package it.unicam.cs.ids.localplatform.command;

import it.unicam.cs.ids.localplatform.model.Content;
import it.unicam.cs.ids.localplatform.model.POI;

public class DeletePOIContentCommand implements Command{
    private POI poi;
    private Content content;

    public DeletePOIContentCommand(POI poi, Content content) {
        this.poi = poi;
        this.content = content;
    }

    @Override
    public void execute() {
        this.poi.deleteContent(this.content);
    }
}
