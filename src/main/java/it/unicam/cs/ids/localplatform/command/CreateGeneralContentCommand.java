package it.unicam.cs.ids.localplatform.command;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;
import it.unicam.cs.ids.localplatform.model.Content;

/**
 * This class implements the command pattern and is used to create a general content.
 */
public class CreateGeneralContentCommand implements Command {
    private Content content;
    private MunicipalTerritory municipality;

    public CreateGeneralContentCommand(Content content, MunicipalTerritory municipality) {
        if (content == null || municipality == null)
            throw new NullPointerException("Null parameters are not allowed.");

        this.content = content;
        this.municipality = municipality;
    }

    @Override
    public void execute() {
        this.municipality.addGeneralContent(this.content);
    }
}
