package it.unicam.cs.ids.localplatform.command;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;
import it.unicam.cs.ids.localplatform.model.Content;

/**
 * This class implements the command pattern and it is used to delete a content.
 */
public class DeleteGeneralContentCommand implements Command {
    private MunicipalTerritory municipality;
    private Content content;

    public DeleteGeneralContentCommand(MunicipalTerritory residence, Content content) {
        if (residence == null || content == null) throw new NullPointerException("Null parameters are not allowed.");

        this.municipality = residence;
        this.content = content;
    }

    @Override
    public void execute() {
        this.municipality.deleteGeneralContent(this.content);
    }
}
