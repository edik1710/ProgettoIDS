package it.unicam.cs.ids.localplatform.command;

import it.unicam.cs.ids.localplatform.model.POI;

/**
 * This class implements the command pattern and is used to change a POI.
 */
public class ChangePOICommand implements Command {
    private POI poi;
    private String title;

    public ChangePOICommand(POI poi, String title) {
        if (poi == null || title == null) throw new NullPointerException("Null parameters are not allowed.");

        this.poi = poi;
        this.title = title;
    }

    @Override
    public void execute() {
        this.poi.setTitle(this.title);
    }
}
