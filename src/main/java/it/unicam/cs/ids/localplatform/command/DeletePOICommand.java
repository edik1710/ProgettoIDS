package it.unicam.cs.ids.localplatform.command;

import it.unicam.cs.ids.localplatform.model.Coordinates;

/**
 * This class implements the command pattern and it is used to delete a POI.
 */
public class DeletePOICommand implements Command {
    private Coordinates coordinates;

    public DeletePOICommand(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public void execute() {

    }
}
