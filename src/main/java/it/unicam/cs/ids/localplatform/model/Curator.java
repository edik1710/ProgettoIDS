package it.unicam.cs.ids.localplatform.model;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;
import it.unicam.cs.ids.localplatform.command.Command;
import it.unicam.cs.ids.localplatform.singleton.CommandVerificationQueue;

/**
 * This class represents a Curator of the platform.<br><br>
 * Curators can both contribute by uploading content, but at the same time, they are the ones who verify the compliance
 * of the content uploaded by contributors.
 */
public class Curator extends AuthorizedContributor {
    private static Command validatingCommand;

    public Curator(String name, String surname, String email, String password, MunicipalTerritory residence, String cf) {
        super(name, surname, email, password, residence, cf);
    }

    /**
     * This method allows the curator to view the next command to be verified.
     */
    public void viewNextCommand() {
        if (CommandVerificationQueue.getInstance().getToBeVerified().isEmpty()) return;

        validatingCommand = CommandVerificationQueue.getInstance().getToBeVerified().peek();
    }

    /**
     * This method allows the curator to authorize the execution of the command.
     */
    public void authorizeCommandExecution() {
        validatingCommand.execute();
        validatingCommand = null;
        CommandVerificationQueue.getInstance().getToBeVerified().remove();
    }

    /**
     * This method allows the curator to reject the execution of the command.
     */
    public void rejectCommandExecution() {
        validatingCommand = null;
        CommandVerificationQueue.getInstance().getToBeVerified().remove();
    }

    /**
     * This method allows the curator to remove the reported contents.
     */
    private void removeReportedContents() {
        this.getResidence().getGeneralContents().removeIf(content -> content.getReports() >= 10);
    }

    public static Command getValidatingCommand() {
        return validatingCommand;
    }
}
